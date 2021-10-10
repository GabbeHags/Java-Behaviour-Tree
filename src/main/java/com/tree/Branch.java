package com.tree;

import java.util.List;

/**
 * The Branch interface represent a collection of {@link Node}s, extends {@link Node}.
 */
public interface Branch extends Node {

    /**
     * Adds the node to the current branch.
     *
     * @param node the node to be added to the current Branch
     */
    void addNode(Node node);

    /**
     * Returns a list with all the nodes in it.
     *
     * @return a list of all the nodes in this
     */
    List<Node> getNodes();
}
