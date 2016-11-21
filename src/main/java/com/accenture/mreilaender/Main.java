package com.accenture.mreilaender;

import com.accenture.mreilaender.model.MainViewModel;
import com.accenture.mreilaender.view.MainView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author manuel
 * @version 11/15/16
 */
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    private Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        ViewTuple<MainView, MainViewModel> mainView = FluentViewLoader.fxmlView(MainView.class).load();

        Parent root = mainView.getView();
        stage.setTitle("Random Group Generator");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
