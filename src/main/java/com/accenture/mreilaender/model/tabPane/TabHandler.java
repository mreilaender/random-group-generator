package com.accenture.mreilaender.model.tabPane;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabHandler {
    private TabPane tabPane;

    public TabHandler(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void addNewTab(String title) {
        Tab tmp = new Tab();
        tmp.setText(title);
        tabPane.getTabs().add(tabPane.getSelectionModel().getSelectedIndex(), tmp);
        tabPane.getSelectionModel().select(tmp);
    }

    public void setupTabPane() {

    }
}
