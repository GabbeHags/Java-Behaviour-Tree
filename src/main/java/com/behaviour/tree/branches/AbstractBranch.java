package com.behaviour.tree.branches;

import com.behaviour.tree.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBranch represent a Branch, but do not implement execution from {@link Node}.
 */
public abstract class AbstractBranch<T> implements Branch<T> {

    private final List<Node<T>> nodes;

    protected AbstractBranch() {
        nodes = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNode(Node<T> node) {
        nodes.add(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node<T>> getNodes() {
        return new ArrayList<>(nodes);
    }

}
