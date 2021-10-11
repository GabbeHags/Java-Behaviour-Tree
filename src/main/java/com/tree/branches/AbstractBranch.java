package com.tree.branches;

import com.tree.nodes.Node;
import com.tree.nodes.AbstractNode;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBranch represent a Branch, but do not implement execution from {@link Node}.
 */
public abstract class AbstractBranch extends AbstractNode implements Branch {

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
