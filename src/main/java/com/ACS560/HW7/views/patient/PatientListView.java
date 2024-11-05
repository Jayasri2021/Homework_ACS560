package com.ACS560.HW7.views.patient;

import com.ACS560.HW7.entities.PatientEntity;
import com.ACS560.HW7.services.PatientService;
import com.ACS560.HW7.views.MainLayout;
import com.ACS560.HW7.views.components.NotificationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "patients", layout = MainLayout.class)
@PageTitle("Patients | Health Calculation System")
public class PatientListView extends VerticalLayout {
    private final PatientService patientService;
    private final Grid<PatientEntity> grid = new Grid<>(PatientEntity.class);
    private final TextField filterText = new TextField();
    private PatientForm form;

    public PatientListView(PatientService patientService) {
        this.patientService = patientService;
		this.form = new PatientForm();
        addClassName("patient-list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
            getToolbar(),
            getContent()
        );

        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("patient-grid");
        grid.setSizeFull();
        grid.setColumns("name", "address");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editPatient(event.getValue()));
    }

    private void configureForm() {
        form = new PatientForm();
        form.setWidth("25em");
        form.addSaveListener(this::savePatient);
        form.addDeleteListener(this::deletePatient);
        form.addCloseListener(e -> closeEditor());
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
        filterText.setPrefixComponent(new Icon(VaadinIcon.SEARCH));

        Button addButton = new Button("Add Patient", new Icon(VaadinIcon.PLUS));
        addButton.addClickListener(click -> addPatient());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addButton);
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

    private void savePatient(PatientForm.SaveEvent event) {
        try {
            patientService.savePatient(event.getPatient());
            updateList();
            closeEditor();
            NotificationUtil.showSuccess("Patient saved successfully");
        } catch (Exception e) {
            NotificationUtil.showError("Failed to save patient: " + e.getMessage());
        }
    }

    private void deletePatient(PatientForm.DeleteEvent event) {
        try {
            patientService.deletePatient(event.getPatient());
            updateList();
            closeEditor();
            NotificationUtil.showSuccess("Patient deleted successfully");
        } catch (Exception e) {
            NotificationUtil.showError("Failed to delete patient: " + e.getMessage());
        }
    }

    private void addPatient() {
        grid.asSingleSelect().clear();
        editPatient(new PatientEntity());
    }

    private void editPatient(PatientEntity patient) {
        if (patient == null) {
            closeEditor();
        } else {
            form.setPatient(patient);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setPatient(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setId(patientService.getAllPatients());
    }
}