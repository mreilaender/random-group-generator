package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.exceptions.NoDataLoadedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;

/**
 * Handles the tableView and all it's columns
 * @author manuel
 * @version 11/16/16
 */
public class PersonTableModel implements TableModel<Person> {
    private TableColumn<Person, String> firstNameColumn;
    private TableColumn<Person, String> lastNameColumn;
    private ObservableList<Person> data;
    private ArrayList<Integer> randomIndexes;
    private ObservableList<TableColumn<Person, ?>> columns;

    public PersonTableModel() {
        this.firstNameColumn = new TableColumn<>("First Name");
        this.lastNameColumn = new TableColumn<>("Last Name");
        this.data = FXCollections.observableArrayList();
        this.randomIndexes = new ArrayList<>();

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

    @Override
    public Person getRandom() throws NoDataLoadedException {
        int index = 0;
        for (; randomIndexes.contains(index) ; index=(int)(Math.random()*data.size()));
        randomIndexes.add(index);
        try {
            return data.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoDataLoadedException("No data has been loaded to the table yet");
        }
    }
}