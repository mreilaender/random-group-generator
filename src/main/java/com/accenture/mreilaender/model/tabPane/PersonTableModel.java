package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Handles the tableView and all it's columns
 * @author manuel
 * @version 11/16/16
 */
public class PersonTableModel {
    private TableView<Person> tableView;
    private TableColumn<Person, String> firtNameColumn;
    private TableColumn<Person, String> lastNameColumn;
    private ObservableList<Person> data;

    public PersonTableModel(TableView<Person> tableView) {
        this.tableView = tableView;
        this.firtNameColumn = new TableColumn<>("First Name");
        this.lastNameColumn = new TableColumn<>("Last Name");
        this.data = FXCollections.observableArrayList();
    }

    public void initialize() {
        firtNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        tableView.getColumns().addAll(firtNameColumn, lastNameColumn);
        tableView.setItems(data);
        tableView.setItems(data);

    }

    public void addPerson(Person person) {
        this.data.add(person);
    }
}
