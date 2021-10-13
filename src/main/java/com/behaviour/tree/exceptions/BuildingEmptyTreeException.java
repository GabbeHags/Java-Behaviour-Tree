package com.behaviour.tree.exceptions;

public class BuildingEmptyTreeException extends RuntimeException {

    private static final String ERROR_MSG = "Trying to build a tree without any nodes.";

    public BuildingEmptyTreeException() {
        super(ERROR_MSG);
    }

    public BuildingEmptyTreeException(String message) {
        super(
                ERROR_MSG + "\n" +
                message
        );
    }


}
