package com.accenture.mreilaender.model.groupbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manuel
 * @version 11/21/16
 */
public class FixedGroupAmountGenerator<T> implements AbstractGroupGenerator<T> {
    private int amountGroups;
    private List<T> objects;

    public FixedGroupAmountGenerator(int amountGroups) {
        this.amountGroups = amountGroups;
        objects = new ArrayList<T>();
    }

    @Override
    public List<List<T>> generateRandomGroups() {
        for (T t:objects) {
            // TODO
        }
        return null;
    }

    @Override
    public void add(T t) {

    }
}
