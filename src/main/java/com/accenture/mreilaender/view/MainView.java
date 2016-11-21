package com.accenture.mreilaender.view;

import com.accenture.mreilaender.DialogManager;
import com.accenture.mreilaender.model.MainViewModel;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
public class MainView implements FxmlView<MainViewModel>, Initializable {
    /* static */
    // private
    private final static Logger logger = LogManager.getLogger(MainView.class);

    private MainViewModel mainViewModel;

    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setup tabs
        mainViewModel = new MainViewModel();
    }

    @FXML
    protected void addTab() {
        Tab tab = new Tab();
            tab.setText("New Tab");
        int index = tabPane.getSelectionModel().getSelectedIndex();
        tabPane.getTabs().add(index, tab);
        tabPane.getSelectionModel().select(index);
    }

    @FXML
    protected void onOpen() {
        Tab tab = new Tab();
        int index = -1;
        try {
            File file = DialogManager.showChooseFileDialog(tabPane.getScene());
            if (file==null)
                return;

            switch (DialogManager.showConfirmationDialog()) {
                case OPEN_IN_CURRENT_TAB:
                    tab = tabPane.getSelectionModel().getSelectedItem();
                    index = tabPane.getSelectionModel().getSelectedIndex();
                    break;
                case OPEN_IN_NEW_TAB:
                    index = tabPane.getSelectionModel().getSelectedIndex()+1;
                    break;
                case CANCEL:
                    return;
            }
            if (index == -1)
                return;

            tab.setText(file.getName());

            mainViewModel.setupTab(file, tab, index);
            tabPane.getTabs().add(index, tab);
        } catch (IOException e) {
            logger.error(e);
            tabPane.getTabs().remove(tab);
            DialogManager.showExceptionDialog(e).show();
        }
    }
}
