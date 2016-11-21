package com.accenture.mreilaender.model.groupbuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author manuel
 * @version 11/21/16
 */
public class FixedGroupAmountGenerator<T> implements AbstractGroupGenerator<T> {
    private List<T> objects;
    private int numberGroups;

    public FixedGroupAmountGenerator(int numberGroups) {
        objects = new ArrayList<T>();
        this.numberGroups = numberGroups;
    }

    @Override
    public List<List<T>> getRandomGroup() {
        List<List<T>> groups = new ArrayList<>();
        for (int i = objects.size() ; i > 0 ; ) {
            List<T> group = new ArrayList<>();
            for (int j = numberGroups; j > 0; --j) {
                if (i == 0)
                    continue;
                int randomIndex = (int)(Math.random() * i-1);
                T random = objects.get(randomIndex);
                group.add(random);
                objects.remove(random);
                --i;
            }
            groups.add(group);
        }
        return groups;
    }

    @Override
    public void add(T t) {
        objects.add(t);
    }
}
