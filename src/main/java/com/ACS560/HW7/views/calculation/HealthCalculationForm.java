package com.ACS560.HW7.views.calculation;

import com.ACS560.HW7.models.HealthCalculationModel;
import com.ACS560.HW7.models.PatientModel;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class HealthCalculationForm extends FormLayout {
    private HealthCalculationModel calculation;
    
    Binder<HealthCalculationModel> binder = new BeanValidationBinder<>(HealthCalculationModel.class);

    IntegerField duration = new IntegerField("Duration (minutes)");
    IntegerField currentPulse = new IntegerField("Current Pulse");
    IntegerField maxPulse = new IntegerField("Max Pulse");
    NumberField calories = new NumberField("Calories");
    ComboBox<PatientModel> patient = new ComboBox<>("Patient");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public HealthCalculationForm(List<PatientModel> patients) {
        addClassName("calculation-form");

        // Configure fields with validation
        duration.setMin(0);
        duration.setRequiredIndicatorVisible(true);
        
        currentPulse.setMin(0);
        currentPulse.setMax(250);  // Reasonable max heart rate
        currentPulse.setRequiredIndicatorVisible(true);
        
        maxPulse.setMin(0);
        maxPulse.setMax(250);
        maxPulse.setRequiredIndicatorVisible(true);
        
        calories.setMin(0);
        calories.setStep(0.1);
        calories.setRequiredIndicatorVisible(true);

        patient.setItems(patients);
        patient.setItemLabelGenerator(PatientModel::getName);
        patient.setRequiredIndicatorVisible(true);

        binder.bindInstanceFields(this);

        add(
            duration,
            currentPulse,
            maxPulse,
            calories,
            patient,
            createButtonsLayout()
        );
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, calculation)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, cancel);
    }

    public void setCalculation(HealthCalculationModel calculation)