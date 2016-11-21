package com.accenture.mreilaender.model;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;

/**
 * @author manuel
 * @version 11/18/16
 */
public class TabViewModel implements ViewModel {

    private StringProperty groupSize;
    private BooleanProperty generateButtonDisabled;

    public TabViewModel() {
        this.groupSize = new SimpleStringProperty();
        this.generateButtonDisabled = new SimpleBooleanProperty(true);

        generateButtonDisabled.bind(groupSize.isEmpty());
    }

    public String getGroupSize() {
        return groupSize.get();
    }

    public StringProperty groupSizeProperty() {
        return groupSize;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize.set(groupSize);
    }

    public boolean isGenerateButtonDisabled() {
        return generateButtonDisabled.get();
    }

    public BooleanProperty generateButtonDisabledProperty() {
        return generateButtonDisabled;
    }
}
