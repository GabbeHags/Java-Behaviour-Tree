package com.behaviour.tree.nodes;

import com.behaviour.tree.NodeStates;

import java.util.function.Function;


public class Action<T> implements Node<T> {

    private final ActionFunction<T> function;

    public Action(ActionFunction<T> function) {
        this.function = function;
    }

    @Override
    public NodeStates tick(T blackboard) {
        return function.apply(blackboard);
    }
}
