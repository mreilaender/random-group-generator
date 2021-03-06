package com.accenture.mreilaender.model.groupbuilder;

import java.util.List;

/**
 * @author manuel
 * @version 11/17/16
 */
public interface AbstractGroupGenerator<T> {
    List<List<T>> generateRandomGroups();
    void add(T t);
}
