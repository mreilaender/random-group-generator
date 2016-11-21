package com.accenture.mreilaender.model.tabPane;

import com.accenture.mreilaender.model.Randomizable;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @author manuel
 * @version 11/18/16
 */
public interface TableModel<T> extends Randomizable {
    void initialize();
    void add(T t);
    ObservableList<T> getData();
    ObservableList<TableColumn<T, ?>> getColumns();

}
