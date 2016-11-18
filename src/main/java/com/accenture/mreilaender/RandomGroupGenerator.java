package com.accenture.mreilaender;

import com.accenture.mreilaender.controller.MainController;
import com.accenture.mreilaender.controller.TabController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;

/**
 * @author manuel
 * @version 11/15/16
 */
public class RandomGroupGenerator extends Application implements EventHandler<WindowEvent>{
    private Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(MainController.FXML_RESOURCE);

        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, this);
        stage.setTitle("Random Group Generator");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Override
    public void handle(WindowEvent windowEvent) {
        MainController.setScene(stage.getScene());
    }
}
