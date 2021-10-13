package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;
import com.behaviour.tree.NodeStates;

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
            NodeStates nodeState = node.tick(blackBoard);
            if (nodeState != NodeStates.FAILURE) {
                return nodeState;
            }
        }
        return NodeStates.FAILURE;
    }
}
