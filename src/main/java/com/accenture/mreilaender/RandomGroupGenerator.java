package com.accenture.mreilaender;

import com.accenture.mreilaender.controller.MainController;
import com.accenture.mreilaender.controller.TabController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author manuel
 * @version 11/15/16
 */
public class RandomGroupGenerator extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MainController.FXML_RESOURCE);

        stage.setTitle("Random Group Generator");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

}
