package com.tree.branches;

import com.tree.Node;
import com.tree.NodeStates;

/**
 * The Selector class represent an OR-GATE but for a behaviour tree.
 */
public class Selector extends AbstractBranch {

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeStates execute() {
        for (Node node : getNodes()) {
            NodeStates nodeState = node.execute();
            if (nodeState == NodeStates.FAILURE) {
                setState(nodeState);
            }
            else if (nodeState == NodeStates.RUNNING || nodeState == NodeStates.SUCCESS) {
                setState(nodeState);
                return nodeState;
            }
        }
        return getState();
    }
}
