package com.accenture.mreilaender.model.tabPane;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 * @author manuel
 * @version 11/18/16
 */
public interface TableModel<T> {
    void initialize();
    void add(T t);
    ObservableList<T> getData();
    ObservableList<TableColumn<T, ?>> getColumns();
}
