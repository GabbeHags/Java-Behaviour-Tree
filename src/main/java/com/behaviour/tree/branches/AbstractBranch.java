package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBranch represent a Branch, but do not implement execution from {@link Node}.
 */
public abstract class AbstractBranch implements Branch {

    private final List<Node> nodes;

    protected AbstractBranch() {
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

}
