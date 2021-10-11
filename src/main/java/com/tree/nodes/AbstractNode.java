package com.tree.nodes;

import com.tree.NodeStates;

public abstract class AbstractNode implements Node {

    protected NodeStates state = NodeStates.FAILURE;

    /**
     * Sets the state of this.
     *
     * @param state the new state
     */
    public void setState(NodeStates state) {
        this.state = state;
    }


    /**
     * Returns the current state of this {@link Node}.
     *
     * @return the current state of this {@link Node}
     */
    public NodeStates getState() {
        return state;
    }
}
