package com.accenture.mreilaender.view;

import com.accenture.mreilaender.DialogManager;
import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.exceptions.NoAlgorithmSpecifiedException;
import com.accenture.mreilaender.model.TabViewModel;
import com.accenture.mreilaender.model.groupbuilder.AbstractGroupGenerator;
import com.accenture.mreilaender.model.groupbuilder.FixedGroupAmountGenerator;
import com.accenture.mreilaender.model.groupbuilder.FixedGroupSizeGenerator;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
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
    private TextField amountGroups;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private Button generateRandomGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get TabPane from MainView
        generateRandomGroup.disableProperty().bind(tabViewModel.generateButtonDisabledProperty());
        groupSize.disableProperty().bind(tabViewModel.groupSizeDisabledProperty());
        amountGroups.disableProperty().bind(tabViewModel.amountGroupsDisabledProperty());
    }

    @FXML
    public void generateRandomGroup() {
        // Fill group generator with data
        AbstractGroupGenerator<Person> groupGenerator;
        if (!tabViewModel.isAmountGroupsDisabled())
            groupGenerator = new FixedGroupAmountGenerator<>(Integer.parseInt(amountGroups.getText()));
        if (!tabViewModel.isGroupSizeDisabled())
            groupGenerator = new FixedGroupSizeGenerator<>(Integer.parseInt(groupSize.getText()));
        else {
            DialogManager.showExceptionDialog(new NoAlgorithmSpecifiedException("No algorithm has been specified"));
            return;
        }
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

    @FXML
    protected void amountGroupsChanged() {
        tabViewModel.setAmountGroups(amountGroups.getText());
    }
}
