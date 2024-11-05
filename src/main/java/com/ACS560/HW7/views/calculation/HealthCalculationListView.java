package com.ACS560.HW7.views.calculation;

import com.ACS560.HW7.models.HealthCalculationModel;
import com.ACS560.HW7.services.HealthCalculationService;
import com.ACS560.HW7.services.PatientService;
import com.ACS560.HW7.views.MainLayout;
import com.ACS560.HW7.views.components.NotificationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "calculations", layout = MainLayout.class)
@PageTitle("Health Calculations | Health Calculation System")
public class HealthCalculationListView extends VerticalLayout {
    private final HealthCalculationService calculationService;
    private final Grid<HealthCalculationModel> grid = new Grid<>(HealthCalculationModel.class);
    private final HealthCalculationForm form;

    public HealthCalculationListView(HealthCalculationService calculationService,
                                   PatientService patientService) {
        this.calculationService = calculationService;
        addClassName("calculation-list-view");
        setSizeFull();

        configureGrid();
        form = new HealthCalculationForm(patientService.getAllPatients());
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("calculation-grid");
        grid.setSizeFull();
        
        grid.setColumns(); // Clear default columns
        grid.addColumn(HealthCalculationModel::getDuration)
            .setHeader("Duration (min)")
            .setSortable(true);
        grid.addColumn(HealthCalculationModel::getCurrentPulse)
            .setHeader("Current Pulse")
            .setSortable(true);
        grid.addColumn(HealthCalculationModel::getMaxPulse)
            .setHeader("Max Pulse")
            .setSortable(true);
        grid.addColumn(HealthCalculationModel::getCalories)
            .setHeader("Calories")
            .setSortable(true);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        grid.asSingleSelect().addValueChangeListener(event -> 
            editCalculation(event.getValue()));
    }

    private void configureForm() {
        form.setWidth("25em");
        form.addSaveListener(this::saveCalculation);
        form.addDeleteListener(this::deleteCalculation);
        form.addCloseListener(e -> closeEditor());
    }

    private HorizontalLayout getToolbar() {
        Button addButton = new Button("New Calculation", new Icon(VaadinIcon.PLUS));
        addButton.addClickListener(click -> addCalculation());

        HorizontalLayout toolbar = new HorizontalLayout(addButton);
        toolbar.addClassName("toolbar");
        toolbar.setAlignItems(Alignment.BASELINE);
        return toolbar;
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void saveCalculation(HealthCalculationForm.SaveEvent event) {
        try {
            calculationService.createCalculation(event.getCalculation());
            updateList();
            closeEditor();
            NotificationUtil.showSuccess("Health calculation saved successfully");
        } catch (Exception e) {
            NotificationUtil.showError("Failed to save calculation: " + e.getMessage());
        }
    }

    private void deleteCalculation(HealthCalculationForm.DeleteEvent event) {
        try {
            calculationService.deleteCalculation(
                event.getCalculation().getId(), 
                event.getCalculation().getPatientId()
            );
            updateList();
            closeEditor();
            NotificationUtil.showSuccess("Health calculation deleted successfully");
        } catch (Exception e) {
            NotificationUtil.showError("Failed to delete calculation: " + e.getMessage());
        }
    }

    private void addCalculation() {
        grid.asSingleSelect().clear();
        editCalculation(new HealthCalculationModel());
    }

    private void editCalculation(HealthCalculationModel calculation) {
        if (calculation == null) {
            closeEditor();
        } else {
            form.setCalculation(calculation);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setCalculation(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(calculationService.getAllCalculations());
    }
}