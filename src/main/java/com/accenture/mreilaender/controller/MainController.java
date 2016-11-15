package com.accenture.mreilaender.controller;

import com.accenture.mreilaender.model.tabPane.TabHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

/**
 * @author manuel
 * @version 11/15/16
 */
public class MainController {
    @FXML
    private TabPane tabPane;
    private TabHandler tabHandler;

    @FXML
    protected void initialize() {
        // setup tabs
        this.tabHandler = new TabHandler(tabPane);
        this.tabHandler.setupTabPane();
    }

    @FXML
    protected void addNewTab() {
        tabHandler.addNewTab("Test1");
    }

    @FXML
    protected void openFile() {

    }
}
