package com.accenture.mreilaender.controller;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.model.tabPane.TabHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabController implements Initializable {
    public static final URL FXML_RESOURCE = TabController.class.getClassLoader().getResource("com.accenture.mreilaender/tab.fxml");

    private static Scene scene;

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<Person> tableView;

    private TabHandler tabHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tabHandler = new TabHandler(tabPane);
    }

    public TableView<Person> getTableView() {
        return tableView;
    }
}
