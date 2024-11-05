package com.ACS560.HW7.views.patient;

import com.ACS560.HW7.entities.PatientEntity;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class PatientForm extends FormLayout {
    private PatientEntity patient;
    
    TextField name = new TextField("Name");
    TextField address = new TextField("Address");

    Button save = new Button("Save", new Icon(VaadinIcon.CHECK));
    Button delete = new Button("Delete", new Icon(VaadinIcon.TRASH));
    Button cancel = new Button("Cancel", new Icon(VaadinIcon.CLOSE));

    Binder<PatientEntity> binder = new BeanValidationBinder<>(PatientEntity.class);

    public PatientForm() {
        addClassName("patient-form");
        
        // Configure fields
        name.setRequired(true);
        name.setErrorMessage("Name is required");
        address.setRequired(true);
        address.setErrorMessage("Address is required");

        // Bind fields using naming convention
        binder.bindInstanceFields(this);

        add(
            name,
            address,
            createButtonsLayout()
        );
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, patient)));
        cancel.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, cancel);
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
        binder.readBean(patient);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(patient);
            fireEvent(new SaveEvent(this, patient));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class PatientFormEvent extends ComponentEvent<PatientForm> {
        private final PatientEntity patient;

        protected PatientFormEvent(PatientForm source, PatientEntity patient) {
            super(source, false);
            this.patient = patient;
        }

        public PatientEntity getPatient() {
            return patient;
        }
    }

    public static class SaveEvent extends PatientFormEvent {
        SaveEvent(PatientForm source, PatientEntity patient) {
            super(source, patient);
        }
    }

    public static class DeleteEvent extends PatientFormEvent {
        DeleteEvent(PatientForm source, PatientEntity patient) {
            super(source, patient);
        }
    }

    public static class CloseEvent extends PatientFormEvent {
        CloseEvent(PatientForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}