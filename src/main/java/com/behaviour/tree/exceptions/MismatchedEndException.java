package com.behaviour.tree.exceptions;

public class MismatchedEndException extends RuntimeException {

    private static final String ERROR_MSG = "Trying to add node(s) to a already ended tree.";

    public MismatchedEndException() {
        super(ERROR_MSG);
    }

    public MismatchedEndException(String message) {
        super(
                ERROR_MSG + "\n" +
                message
        );
    }
}
