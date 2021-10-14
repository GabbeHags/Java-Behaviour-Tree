package com.behaviour.tree.nodes;


import com.behaviour.tree.TreeBuilder;
import com.behaviour.tree.NodeStates;

/**
 * The Node interface is the building blocks for {@link TreeBuilder}.
 */
public interface Node<T> {

    /**
     * Represents the behaviour of this Node.
     *
     * @param blackBoard is the data that all nodes will get access to.
     *
     * @return the current state of the execution
     */
    NodeStates tick(T blackBoard);

}
