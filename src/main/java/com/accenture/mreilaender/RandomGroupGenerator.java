package com.accenture.mreilaender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author manuel
 * @version 11/15/16
 */
public class RandomGroupGenerator extends Application {
    public void start(Stage stage) throws Exception {
        URL main = RandomGroupGenerator.class.getClassLoader().getResource("com.accenture.mreilaender/main.fxml");
        URL tab = RandomGroupGenerator.class.getClassLoader().getResource("com.accenture.mreilaender/tab.fxml");
        Parent root = FXMLLoader.load(main);
        Parent tabRoot = FXMLLoader.load(tab);

        stage.setTitle("FXML Welcome");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();


        Scene scene = root.getScene();
        TabPane tabPane = (TabPane) scene.lookup("#tabPane");


        BorderPane content = (BorderPane) tabRoot;
    }

}
