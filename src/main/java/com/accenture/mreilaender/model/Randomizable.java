package com.accenture.mreilaender.model;

import com.accenture.mreilaender.exceptions.NoDataLoadedException;

/**
 * @author manuel
 * @version 11/17/16
 */
public interface Randomizable<T> {
    T getRandom() throws NoDataLoadedException;
}
