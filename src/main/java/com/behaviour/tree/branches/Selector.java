package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;
import com.behaviour.tree.NodeStates;

/**
 * The Selector class represent an OR-GATE but for a behaviour tree.
 */
public class Selector extends AbstractBranch {

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeStates tick() {
        for (Node node : getNodes()) {
            NodeStates nodeState = node.tick();
            if (nodeState != NodeStates.FAILURE) {
                return nodeState;
            }
        }
        return NodeStates.FAILURE;
    }
}