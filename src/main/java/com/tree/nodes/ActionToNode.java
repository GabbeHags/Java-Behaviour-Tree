package com.tree.nodes;

import com.tree.NodeStates;

public class ActionToNode extends AbstractNode{

    private final Action action;

    public ActionToNode(Action action) {
        this.action = action;
    }

    @Override
    public NodeStates execute() {
        return action.doAction();
    }
}
