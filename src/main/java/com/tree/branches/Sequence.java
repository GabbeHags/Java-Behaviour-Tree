package com.tree.branches;

import com.tree.nodes.Node;
import com.tree.NodeStates;

/**
 * The Sequence class represent an AND-GATE but for a behaviour tree.
 */
public class Sequence extends AbstractBranch {

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeStates execute() {
        for (Node node : getNodes()) {
            NodeStates nodeState = node.execute();
            if (nodeState == NodeStates.FAILURE || nodeState == NodeStates.RUNNING) {
                setState(nodeState);
                return nodeState;
            }
            else if (nodeState == NodeStates.SUCCESS) {
                setState(nodeState);
            }
        }
        return getState();
    }
}
