package com.accenture.mreilaender.model;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.model.tabPane.PersonTableModel;
import com.accenture.mreilaender.view.TabView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.commons.csv.CSVFormat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author manuel
 * @version 11/18/16
 */
public class MainViewModel implements ViewModel {

    private HashMap<Integer, PersonTableModel> tableModels;
    private HashMap<Integer, ViewTuple<TabView, TabViewModel>> views;

    public MainViewModel() {
        tableModels = new HashMap<>();
        views = new HashMap<>();
    }

    public void setupTab(File file, Tab tab, int index) throws IOException {
        views.put(index, FluentViewLoader.fxmlView(TabView.class).load());
        tab.setContent(views.get(index).getView());
        tab.setText(file.getName());
        loadData(file, index);
    }

    private void loadData(File file, int index) throws IOException {
        List<Person> persons = Person.readCSV(file, CSVFormat.RFC4180.withFirstRecordAsHeader().withDelimiter(';'));
        PersonTableModel personTableModel = new PersonTableModel();
        if (tableModels.containsKey(index))
            personTableModel = tableModels.get(index);
        else
            tableModels.put(index, personTableModel);
        persons.forEach(personTableModel::add);
        personTableModel.initialize();

        TableView<Person> tableView = getTableView(index);
            tableView.getColumns().addAll(getColumns(index));
            tableView.setItems(getData(index));
            tableView.setEditable(true);
    }

    private ObservableList<Person> getData(int index) {
        return tableModels.get(index).getData();
    }

    private ObservableList<TableColumn<Person, ?>> getColumns(int index) {
        return tableModels.get(index).getColumns();

    }

    private TableView<Person> getTableView(int index) {
        return views.get(index).getCodeBehind().getTableView();
    }
}