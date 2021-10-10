package com.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBranch implements Branch {

    protected NodeStates state = NodeStates.FAILURE;
    protected final List<Node> nodes;

    protected AbstractBranch() {
        nodes = new ArrayList<>();
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

}
