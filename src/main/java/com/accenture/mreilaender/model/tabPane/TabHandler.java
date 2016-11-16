package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.controller.MainController;
import com.accenture.mreilaender.controller.TabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
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
    private TabPane tabPane;

    public TabHandler(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    /**
     * Add new tab and select it
     * @param tab Tab to be added
     * @return Index of the new Tab
     */
    public int addNewTab(Tab tab) {
        tabPane.getTabs().add(tabPane.getSelectionModel().getSelectedIndex(), tab);
        tabPane.getSelectionModel().select(tab);
        return tabPane.getSelectionModel().getSelectedIndex();
    }

    /**
     * Open file dialog to choose a CSV file and ask if it's supposed to be loaded
     * in the active tab. If not open a new tab and load the file in the new tab.
     */
    public void loadCSV() {
        // Open file dialog
        File file = new FileChooser().showOpenDialog(tabPane.getScene().getWindow());

        // Confirmation Dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Open new file");
            alert.setHeaderText("Do you want to open the file in a new Tab?");
        ButtonType openInNewTab = new ButtonType("Open in new tab"),
                openInCurrentTab = new ButtonType("Open in current tab"),
                cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(openInNewTab, openInCurrentTab, cancel);
        ButtonType result = alert.showAndWait().orElse(cancel);


        Tab tab = null;
        if (result == openInNewTab) {
            tab = new Tab();
        } else if (result == openInCurrentTab) {
            tab = this.tabPane.getSelectionModel().getSelectedItem();
        } else {
            return;
        }
        tab.setText(file.getName());

        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            tab.setContent((Node) fxmlLoader.load(TabController.FXML_RESOURCE));
        } catch (IOException e) {
            ((MainController) fxmlLoader.getController()).showExceptionDialog(e);
        }

        addNewTab(tab);

        /*
        try {
            Iterable<CSVRecord> records = CSVFormat.EXCEL
                    .withFirstRecordAsHeader()
                    .withDelimiter(';')
                    .parse(new FileReader(file));
            for (CSVRecord record:records) {

                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
