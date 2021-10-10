package com.tree.branches;

import com.tree.Branch;
import com.tree.Node;
import com.tree.NodeStates;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBranch represent a Branch, but do not implement execution from {@link Node}.
 */
public abstract class AbstractBranch implements Branch {

    private NodeStates state;
    private final List<Node> nodes;

    protected AbstractBranch() {
        state = NodeStates.FAILURE;
        nodes = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }


    /**
     * Sets the state of this.
     *
     * @param state the new state
     */
    public void setState(NodeStates state) {
        this.state = state;
    }


    /**
     * Returns the current state of this {@link Branch}.
     *
     * @return the current state of this {@link Branch}
     */
    public NodeStates getState() {
        return state;
    }
}
