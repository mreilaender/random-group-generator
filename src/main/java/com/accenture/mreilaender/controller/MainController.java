package com.accenture.mreilaender.controller;

import com.accenture.mreilaender.model.FileManager;
import com.accenture.mreilaender.model.tabPane.TabHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/15/16
 */
public class MainController implements Initializable {
    public static URL FXML_RESOURCE = TabController.class.getClassLoader().getResource("com.accenture.mreilaender/main.fxml");

    @FXML
    private TabPane tabPane;
    private TabHandler tabHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setup tabs
        tabHandler = new TabHandler(tabPane);
    }

    @FXML
    protected void addNewTab() {
        tabHandler.addTab(false);
    }

    @FXML
    protected void onOpen() {
        try {
            tabHandler.setupTab(FileManager.chooseFile(this.tabPane.getScene()), tabHandler.addTab(true));
        } catch (IOException e) {
            showExceptionDialog(e);
        }
    }

    public Alert showExceptionDialog(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getClass().getName());
            alert.setContentText(e.getMessage());

        TextArea exceptionField = new TextArea(ExceptionUtils.getStackTrace(e));
            exceptionField.setEditable(false);
            exceptionField.setWrapText(true);
            exceptionField.setMaxHeight(Double.MAX_VALUE);
            exceptionField.setMaxWidth(Double.MAX_VALUE);

        GridPane.setVgrow(exceptionField, Priority.ALWAYS);
        GridPane.setHgrow(exceptionField, Priority.ALWAYS);

        alert.getDialogPane().setExpandableContent(exceptionField);
        alert.getDialogPane().setPrefSize(800, 600);
        return alert;
    }
}
