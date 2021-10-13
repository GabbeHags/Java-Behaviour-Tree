package com.behaviour.tree.exceptions;

public class MissingEndException extends RuntimeException {

    private static final String ERROR_MSG = "Missing a end at the end of the tree.";

    public MissingEndException() {
        super(ERROR_MSG);
    }

    public MissingEndException(String message) {
        super(
                ERROR_MSG + "\n" +
                message
        );
    }


}
