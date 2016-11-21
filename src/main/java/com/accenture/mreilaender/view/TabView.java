package com.accenture.mreilaender.view;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.model.GroupViewModel;
import com.accenture.mreilaender.model.TabViewModel;
import com.accenture.mreilaender.model.groupbuilder.AbstractGroupGenerator;
import com.accenture.mreilaender.model.groupbuilder.FixedGroupSizeGenerator;
import com.accenture.mreilaender.model.tabPane.PersonTableModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabView implements FxmlView<TabViewModel>, Initializable {
    final static Logger LOGGER = LogManager.getLogger(TabView.class);

    @InjectViewModel
    private TabViewModel tabViewModel;

    @FXML
    private TextField groupSize;

    @FXML
    private TextField groupOutput;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private Button generateRandomGroup;

    @FXML
    private Label textFieldLabel;

    private ViewTuple<GroupView, GroupViewModel> groupView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get TabPane from MainView
        generateRandomGroup.disableProperty().bind(tabViewModel.generateButtonDisabledProperty());
    }

    @FXML
    public void generateRandomGroup() {
        // Fill group generator with data
        AbstractGroupGenerator<Person> groupGenerator = new FixedGroupSizeGenerator<>(Integer.parseInt(groupSize.getText()));
        tableView.getItems().forEach(groupGenerator::add);
        tabViewModel.setupGroupView(groupGenerator);
    }

    public TableView<Person> getTableView() {
        return tableView;
    }

    @FXML
    protected void groupSizeChanged() {
        tabViewModel.setGroupSize(groupSize.getText());
    }

}
