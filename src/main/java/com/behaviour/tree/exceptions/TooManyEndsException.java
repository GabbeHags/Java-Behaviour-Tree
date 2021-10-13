package com.behaviour.tree.exceptions;

public class TooManyEndsException extends RuntimeException {

    private static final String ERROR_MSG = "Too many ends added to the tree.";

    public TooManyEndsException() {
        super(ERROR_MSG);
    }

    public TooManyEndsException(String message) {
        super(
                ERROR_MSG + "\n" +
                message
        );
    }


}
