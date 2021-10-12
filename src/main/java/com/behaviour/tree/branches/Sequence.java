package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;
import com.behaviour.tree.NodeStates;

/**
 * The Sequence class represent an AND-GATE but for a behaviour tree.
 */
public class Sequence extends AbstractBranch {

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeStates tick() {
        for (Node node : getNodes()) {
            NodeStates nodeState = node.tick();
            if (nodeState != NodeStates.SUCCESS) {
                return nodeState;
            }
        }
        return NodeStates.SUCCESS;
    }
}
