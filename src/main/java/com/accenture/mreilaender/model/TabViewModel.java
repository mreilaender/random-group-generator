package com.accenture.mreilaender.model;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.model.groupbuilder.AbstractGroupGenerator;
import com.accenture.mreilaender.model.groupbuilder.FixedGroupSizeGenerator;
import com.accenture.mreilaender.model.tabPane.PersonTableModel;
import com.accenture.mreilaender.view.GroupView;
import com.sun.org.apache.xpath.internal.operations.Bool;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author manuel
 * @version 11/18/16
 */
public class TabViewModel implements ViewModel {

    private StringProperty groupSize;
    private StringProperty amountGroups;
    private BooleanProperty generateButtonDisabled, amountGroupsDisabled, groupSizeDisabled;

    public TabViewModel() {
        groupSize = new SimpleStringProperty();
        amountGroups = new SimpleStringProperty();
        generateButtonDisabled = new SimpleBooleanProperty(true);
        amountGroupsDisabled = new SimpleBooleanProperty(false);
        groupSizeDisabled = new SimpleBooleanProperty(false);

        //generateButtonDisabled.bind(groupSize.isEmpty());
        generateButtonDisabled.bind(Bindings.and(amountGroups.isEmpty(), groupSize.isEmpty()));
        amountGroupsDisabled.bind(groupSize.isNotEmpty());
        groupSizeDisabled.bind(amountGroups.isNotEmpty());
    }

    public void setGroupSize(String groupSize) {
        this.groupSize.set(groupSize);
    }

    public void setAmountGroups(String amountGroups) {
        this.amountGroups.set(amountGroups);
    }

    public BooleanProperty generateButtonDisabledProperty() {
        return generateButtonDisabled;
    }

    public boolean isAmountGroupsDisabled() {
        return amountGroupsDisabled.get();
    }

    public BooleanProperty amountGroupsDisabledProperty() {
        return amountGroupsDisabled;
    }

    public boolean isGroupSizeDisabled() {
        return groupSizeDisabled.get();
    }

    public BooleanProperty groupSizeDisabledProperty() {
        return groupSizeDisabled;
    }

    public void setupGroupView(AbstractGroupGenerator<Person> groupGenerator) {
        ViewTuple<GroupView, GroupViewModel>  groupView = FluentViewLoader.fxmlView(GroupView.class).load();
        Parent root = groupView.getView();

        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Groups");
        stage.setScene(scene);

        // Generate random groups
        TabPane tabPane = new TabPane();
        List<List<Person>> groups = groupGenerator.generateRandomGroups();
        int groupNumber = 1;
        for (List<Person> group:groups) {
            Tab tab = new Tab();
                tab.setText("Group " + groupNumber);

            TableView<Person> tableView = new TableView<>();
            PersonTableModel personTableModel = new PersonTableModel();
            group.forEach(personTableModel::add);
            personTableModel.initialize();

            tableView.setItems(personTableModel.getData());
            tableView.getColumns().addAll(personTableModel.getColumns());
            tableView.setEditable(true);

            tab.setContent(tableView);
            tabPane.getTabs().add(tab);

            ((Pane)root).getChildren().add(tableView);
            tableView.setFixedCellSize(25);
            tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()).add(30));

            ++groupNumber;
        }
        ((Pane)root).getChildren().add(tabPane);
        stage.show();
    }
}
