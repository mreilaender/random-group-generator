package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Handles the tableView and all it's columns
 * @author manuel
 * @version 11/16/16
 */
public class PersonTableModel implements TableModel<Person> {
    private TableColumn<Person, String> firstNameColumn;
    private TableColumn<Person, String> lastNameColumn;
    private ObservableList<Person> data;
    private ObservableList<TableColumn<Person, ?>> columns;

    public PersonTableModel() {
        this.firstNameColumn = new TableColumn<>("First Name");
        this.lastNameColumn = new TableColumn<>("Last Name");
        this.data = FXCollections.observableArrayList();

        // Save all columns
        columns = FXCollections.observableArrayList();
            columns.add(firstNameColumn);
            columns.add(lastNameColumn);
    }

    @Override
    public void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Set columns editable
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @Override
    public void add(Person person) {
        this.data.add(person);
    }

    @Override
    public ObservableList<Person> getData() {
        return data;
    }

    @Override
    public ObservableList<TableColumn<Person, ?>> getColumns() {
        return columns;
    }
}