package com.ACS560.HW7.views;

import com.ACS560.HW7.views.patient.PatientListView;
import com.ACS560.HW7.views.calculation.HealthCalculationListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Health Calculation System");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Logout", e -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation("/logout"));
        });
        logout.addClassName("logout-button");

        HorizontalLayout header = new HorizontalLayout(
            new DrawerToggle(),
            logo,
            logout
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        H2 navTitle = new H2("Navigation");
        navTitle.addClassNames("text-l", "m-m");

        RouterLink patientsLink = new RouterLink("Patients", PatientListView.class);
        patientsLink.setHighlightCondition(HighlightConditions.sameLocation());
        patientsLink.addClassName("nav-link");

        RouterLink calculationsLink = new RouterLink("Health Calculations", 
            HealthCalculationListView.class);
        calculationsLink.setHighlightCondition(HighlightConditions.sameLocation());
        calculationsLink.addClassName("nav-link");

        VerticalLayout navLayout = new VerticalLayout(
            navTitle,
            patientsLink,
            calculationsLink
        );
        navLayout.addClassName("nav-layout");

        addToDrawer(navLayout);
    }
}