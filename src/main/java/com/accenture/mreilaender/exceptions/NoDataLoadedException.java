package com.accenture.mreilaender.exceptions;

/**
 * @author manuel
 * @version 11/17/16
 */
public class NoDataLoadedException extends Exception {
    public NoDataLoadedException(String s) {
        super(s);
    }

    public NoDataLoadedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
