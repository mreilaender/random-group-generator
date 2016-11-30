package com.accenture.mreilaender.exceptions;

/**
 * @author manuel
 * @version 11/21/16
 */
public class NoAlgorithmSpecifiedException extends Exception {
    public NoAlgorithmSpecifiedException(String s) {
        super(s);
    }

    public NoAlgorithmSpecifiedException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
