package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.controller.MainController;
import com.accenture.mreilaender.controller.TabController;
import com.accenture.mreilaender.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabHandler {
    public static final String addTabLabel = "+";
    private TabPane tabPane;

    private ButtonType openInNewTab, openInCurrentTab, cancel;
    private Alert openConfirmation;

    public TabHandler(TabPane tabPane) {
        this.tabPane = tabPane;

        // Setup confirmation dialog
        openConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            openConfirmation.setTitle("Open new file");
            openConfirmation.setHeaderText("Do you want to open the file in a new Tab?");
            openConfirmation.getDialogPane().setPrefSize(500, 100);

        openInNewTab = new ButtonType("Open in new tab");
        openInCurrentTab = new ButtonType("Open in current tab");
        cancel = new ButtonType("Cancel");
    }

    public ButtonType showOpenConfirmationDialog() {
        openConfirmation.getButtonTypes().setAll(openInNewTab, openInCurrentTab, cancel);
        return openConfirmation.showAndWait().orElse(cancel);
    }

    /**
     * Add new tab and select it
     * @return Index of the new Tab
     */
    public Tab addTab(boolean confirmDialog) {
        if (!confirmDialog) {
            Tab tab = new Tab();
            tabPane.getTabs().add(tabPane.getSelectionModel().getSelectedIndex(), tab);
            tabPane.getSelectionModel().select(tab);
            return tab;
        }
        ButtonType result = showOpenConfirmationDialog();
        Tab tab = null;
        int index = 0;
        if (result == openInNewTab) {
            tab = new Tab();
            index = tabPane.getSelectionModel().getSelectedIndex() + 1;
        } else if (result == openInCurrentTab) {
            tab = this.tabPane.getSelectionModel().getSelectedItem();
        } else {
            return tab;
        }
        tabPane.getTabs().add(index, tab);
        tabPane.getSelectionModel().select(tab);
        return tab;
    }

    public void setupTab(File file, Tab tab) throws IOException {
        tab.setText(file.getName());

        FXMLLoader fxmlLoader = new FXMLLoader(TabController.FXML_RESOURCE);
        try {
            tab.setContent(fxmlLoader.load());
        } catch (IOException e) {
            ((MainController) fxmlLoader.getController()).showExceptionDialog(e);
        }

        TabController tabController = fxmlLoader.getController();
        TableView<Person> tableView = tabController.getTableView();

        Iterable<CSVRecord> records = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .withDelimiter(';')
                .parse(new FileReader(file));

        PersonTableModel personTableModel = new PersonTableModel(tableView);
        for (CSVRecord record:records) {
            Person person = new Person(record.get(0), record.get(1));
            personTableModel.addPerson(person);
        }
        personTableModel.initialize();
    }
}
