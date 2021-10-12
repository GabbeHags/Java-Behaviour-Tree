package com.behaviour.tree.nodes;

import com.behaviour.tree.utils.Function;
import com.behaviour.tree.NodeStates;

public class Action implements Node{

    private final Function<NodeStates> function;

    public Action(Function<NodeStates> function) {
        this.function = function;
    }

    @Override
    public NodeStates tick() {
        return function.apply();
    }
}
