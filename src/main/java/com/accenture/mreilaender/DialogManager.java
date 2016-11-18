package com.accenture.mreilaender;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.File;

/**
 * @author manuel
 * @version 11/17/16
 */
public class DialogManager {

    public static File showChooseFileDialog(Scene scene) {
        return new FileChooser().showOpenDialog(scene.getWindow());
    }

    public static Alert showExceptionDialog(Exception e) {
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
        alert.getDialogPane().setPrefSize(1000, 600);
        return alert;
    }

}
