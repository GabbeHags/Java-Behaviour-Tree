package com.behaviour.tree.nodes;

import com.behaviour.tree.NodeStates;
import com.behaviour.tree.utils.Function;


public class Condition implements Node {

    private final Function<Boolean> function;

    public Condition(Function<Boolean> function) {
        this.function = function;
    }

    @Override
    public NodeStates tick() {
        if (Boolean.TRUE.equals(function.apply())) {
            return NodeStates.SUCCESS;
        }
        return NodeStates.FAILURE;
    }
}
