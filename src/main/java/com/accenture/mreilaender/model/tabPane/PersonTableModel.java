package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.exceptions.NoDataLoadedException;
import com.accenture.mreilaender.model.Randomizable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;

/**
 * Handles the tableView and all it's columns
 * @author manuel
 * @version 11/16/16
 */
public class PersonTableModel implements Randomizable<Person> {
    private TableColumn<Person, String> firtNameColumn;
    private TableColumn<Person, String> lastNameColumn;
    private ObservableList<Person> data;
    private ArrayList<Integer> randomIndexes;

    public PersonTableModel() {
        this.firtNameColumn = new TableColumn<>("First Name");
        this.lastNameColumn = new TableColumn<>("Last Name");
        this.data = FXCollections.observableArrayList();
        this.randomIndexes = new ArrayList<>();
    }

    public void initialize(TableView<Person> tableView) {
        firtNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        tableView.getColumns().addAll(firtNameColumn, lastNameColumn);
        tableView.setItems(data);

        // Set columns editable
        firtNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void addPerson(Person person) {
        this.data.add(person);
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

    public ObservableList<Person> getData() {
        return data;
    }
}
