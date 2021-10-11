package com.tree.nodes;

import com.tree.NodeStates;


public class ConditionToNode extends AbstractNode{

    private final Condition condition;

    public ConditionToNode(Condition condition) {
        this.condition = condition;
    }

    @Override
    public NodeStates execute() {
        if (condition.condition()) {
            setState(NodeStates.SUCCESS);
        }
        else {
            setState(NodeStates.FAILURE);
        }
        return getState();
    }
}
