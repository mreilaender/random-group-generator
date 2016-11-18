package com.accenture.mreilaender.controller;

import com.accenture.mreilaender.DialogManager;
import com.accenture.mreilaender.model.tabPane.TabHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/15/16
 */
public class MainController implements Initializable {
    public final static String TAG = MainController.class.getSimpleName();

    public static URL FXML_RESOURCE = TabController.class.getClassLoader().getResource("main.fxml");
    private final static Logger logger = LogManager.getLogger(MainController.class);

    private static Scene scene;

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
        Tab tab = null;
        try {
            File file = DialogManager.showChooseFileDialog(this.tabPane.getScene());
            if (file==null)
                return;
            tab = tabHandler.addTab(true);
            tabHandler.setupTab(file, tab);
        } catch (IOException e) {
            logger.error(e);
            tabHandler.removeTab(tab);
            DialogManager.showExceptionDialog(e).show();
        }
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        MainController.scene = scene;
    }
}
