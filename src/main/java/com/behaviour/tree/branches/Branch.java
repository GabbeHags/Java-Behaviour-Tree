package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;

import java.util.List;

/**
 * The Branch interface represent a collection of {@link Node}s, extends {@link Node}.
 */
public interface Branch<T> extends Node<T> {

    /**
     * Adds the node to the current branch.
     *
     * @param node the node to be added to the current Branch
     */
    void addNode(Node<T> node);

    /**
     * Returns a list with all the nodes in it.
     *
     * @return a list of all the nodes in this
     */
    List<Node<T>> getNodes();
}
