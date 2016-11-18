package com.accenture.mreilaender.controller;

import com.accenture.mreilaender.DialogManager;
import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.exceptions.NoDataLoadedException;
import com.accenture.mreilaender.model.tabPane.PersonTableModel;
import com.accenture.mreilaender.model.tabPane.TabHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabController implements Initializable {
    public static final URL FXML_RESOURCE = TabController.class.getClassLoader().getResource("tab.fxml");
    final static Logger LOGGER = LogManager.getLogger(TabController.class);
    private static Scene scene;

    @FXML
    private TextField groupSize;

    @FXML
    private TextField groupOutput;

    private TabPane tabPane;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private Button generateRandomGroup;

    @FXML
    private Label textFieldLabel;

    private TabHandler tabHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get TabPane from MainController
        this.tabHandler = new TabHandler(tabPane);
        tabPane = (TabPane) MainController.getScene().lookup("#tabPane");
    }

    @FXML
    public void generateRandomGroup() {
        groupOutput.clear();
        ArrayList<Person> persons = new ArrayList<>();
        try {
            PersonTableModel personTableModel = TabHandler.getPersonTableModel(tabPane.getSelectionModel().getSelectedIndex());
            int numberRandom = Integer.parseInt(groupSize.getText());
            for (; numberRandom != 0; --numberRandom)
                persons.add(personTableModel.getRandom());
        } catch (NoDataLoadedException e) {
            LOGGER.error(e);
            DialogManager.showExceptionDialog(e).show();
        } catch (NumberFormatException e) {
            IllegalArgumentException tmp = new IllegalArgumentException("Invalid group size!");
            LOGGER.error(tmp);
            DialogManager.showExceptionDialog(tmp).show();
        }
        for (Person person:persons)
            groupOutput.setText(groupOutput.getText() + person.getFirstName() + ", ");
    }

    public TableView<Person> getTableView() {
        return tableView;
    }

    public Button getGenerateRandomGroup() {
        return generateRandomGroup;
    }

    public Label getTextFieldLabel() {
        return textFieldLabel;
    }
}
