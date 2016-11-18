package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.controller.TabController;
import com.accenture.mreilaender.entities.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabHandler {
    private TabPane tabPane;

    private ButtonType openInNewTab, openInCurrentTab, cancel;
    private Alert openConfirmation;

    private static HashMap<Integer, PersonTableModel> models;

    static {
        models = new HashMap<>();
        System.out.println("Initialize HashMap");
    }

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
    public Tab addTab(boolean showConfirmDialog) {
        ButtonType result = null;
        int index = 0;
        Tab tab = null;

        // Show confirm dialog
        if (showConfirmDialog)
            result = showOpenConfirmationDialog();

        if (result == openInNewTab) {
            tab = new Tab();
            index = tabPane.getSelectionModel().getSelectedIndex() + 1;
        } else if (result == openInCurrentTab) {
            tab = this.tabPane.getSelectionModel().getSelectedItem();
        } else if (result == null) {
            tab = new Tab();
            index = tabPane.getSelectionModel().getSelectedIndex();
        }
        tabPane.getTabs().add(index, tab);
        tabPane.getSelectionModel().select(tab);
        PersonTableModel tableModel = new PersonTableModel();
        models.put(tabPane.getSelectionModel().getSelectedIndex(), tableModel);
        return tab;
    }

    public void removeTab(Tab tab) {
        tabPane.getTabs().remove(tab);
    }

    public void setupTab(File file, Tab tab) throws IOException {
        if (!tabPane.getTabs().contains(tab))
            return;
        tab.setText(file.getName());

        FXMLLoader fxmlLoader = new FXMLLoader(TabController.FXML_RESOURCE);
        tab.setContent(fxmlLoader.load());

        TabController tabController = fxmlLoader.getController();
        TableView<Person> tableView = tabController.getTableView();

        Iterable<CSVRecord> records = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .withDelimiter(';')
                .parse(new FileReader(file));

        PersonTableModel tmp = getPersonTableModel(tabPane.getSelectionModel().getSelectedIndex());
        for (CSVRecord record:records) {
            Person person = new Person(record.get(0), record.get(1));
            tmp.addPerson(person);
        }
        tableView.setEditable(true);
        tmp.initialize(tableView);
    }

    public static PersonTableModel getPersonTableModel(int index) {
        return models.get(index);
    }
}
