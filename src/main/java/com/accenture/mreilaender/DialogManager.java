package com.accenture.mreilaender;

import com.accenture.mreilaender.model.ConfirmationDialog;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    private static Alert openConfirmation;
    private static ButtonType openInNewTab, openInCurrentTab, cancel;

    static {
        // Setup confirmation dialog
        openConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        openConfirmation.setTitle("Open new file");
        openConfirmation.setHeaderText("Do you want to open the file in a new Tab?");
        openConfirmation.getDialogPane().setPrefSize(500, 100);

        openInNewTab = new ButtonType("Open in new tab");
        openInCurrentTab = new ButtonType("Open in current tab");
        cancel = new ButtonType("Cancel");

        openConfirmation.getButtonTypes().setAll(openInNewTab, openInCurrentTab, cancel);
    }

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

    public static ConfirmationDialog showConfirmationDialog() {
        ButtonType result = openConfirmation.showAndWait().orElse(cancel);
        if (result == openInNewTab)
            return ConfirmationDialog.OPEN_IN_NEW_TAB;
        else if (result == openInCurrentTab)
            return ConfirmationDialog.OPEN_IN_CURRENT_TAB;
        return ConfirmationDialog.CANCEL;
    }

}
