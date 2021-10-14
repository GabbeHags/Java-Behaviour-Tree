package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;
import com.behaviour.tree.NodeStates;

import java.util.Objects;

/**
 * The Selector class represent an OR-GATE but for a behaviour tree.
 */
public class Selector<T> extends AbstractBranch<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeStates tick(T blackBoard) {
        for (Node<T> node : getNodes()) {
            NodeStates nodeState = Objects.requireNonNull(node.tick(blackBoard), "tick return value must not be null");
            if (nodeState != NodeStates.FAILURE) {
                return nodeState;
            }
        }
        return NodeStates.FAILURE;
    }
}
