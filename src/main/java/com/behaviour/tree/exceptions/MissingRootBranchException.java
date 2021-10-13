package com.behaviour.tree.exceptions;

public class MissingRootBranchException extends RuntimeException {

    private static final String ERROR_MSG = "Can't add a node to the tree without starting with a branch e.g selector(), sequence().";

    public MissingRootBranchException() {
        super(ERROR_MSG);
    }

    public MissingRootBranchException(String message) {
        super(
                ERROR_MSG + "\n" +
                message
        );
    }


}
