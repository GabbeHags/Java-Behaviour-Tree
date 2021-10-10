package com.tree;


/**
 * The Node interface is the building blocks for {@link TreeBuilder}.
 */
public interface Node {

    /**
     * Represents the behaviour of this Node.
     *
     * @return the current state of the execution
     */
    NodeStates execute();

}
