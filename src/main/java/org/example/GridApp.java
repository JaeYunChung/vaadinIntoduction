package org.example;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Route("")
public class GridApp extends Div {
    Grid<Person> grid;
    List<Person> people = new ArrayList<>();

    public GridApp()
    {
        this.setGridComponent();
        this.setInfoForm();
        this.refreshGrid();
    }

    private void setInfoForm()
    {
        TextField name = new TextField("Name");
        //TextField age = new TextField("Age");
        Select<Integer> age = new Select<>();
        TextField email = new TextField("Email");
        TextField school = new TextField("School");

        final List<Integer> AGES = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        age.setItems(AGES); age.setLabel("Age");
        FormLayout formLayout = new FormLayout();
        formLayout.add(name, age, email, school);
        formLayout.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new ResponsiveStep("500px", 2));
        formLayout.setColspan(email, 2);
        Button button = new Button("click");
        button.addClickListener(e -> {
            Optional<Person> person = DataService.savePerson(new Person(name.getValue(), Integer.parseInt(age.getValue().toString()),
                    email.getValue(), school.getValue()));
            if(person.isPresent()){
                people.add(person.get());
                this.refreshGrid();
            }
            name.clear();age.clear();email.clear();school.clear();
        });button.addClickShortcut(Key.ENTER);
        VerticalLayout layout = new VerticalLayout(formLayout, button);
        layout.setPadding(true);
        layout.setWidth("80%");
        layout.getStyle().set("margin", "auto");
        layout.getStyle().set("display", "flex");
        layout.getStyle().set("justify-content", "center");
        layout.getStyle().set("align-items", "center");

        add(layout);
    }
    private void setGridComponent()
    {
        grid = new Grid<>(Person.class, false);
        grid.addColumn(Person::getName).setHeader("Name").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(Person::getAge).setHeader("Age").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(Person::getEmail).setHeader("Email").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(Person::getSchool).setHeader("School").setTextAlign(ColumnTextAlign.CENTER);

        grid.setItems(people);

        //grid.getStyle().set("align-items", "center");

        add(grid);
    }
    private void refreshGrid()
    {
        grid.getDataProvider().refreshAll();
    }
}
