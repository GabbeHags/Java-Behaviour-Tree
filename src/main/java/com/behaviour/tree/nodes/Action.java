package com.behaviour.tree.nodes;

import com.behaviour.tree.NodeStates;

import java.util.Objects;


public class Action<T> implements Node<T> {

    private final ActionFunction<T> function;

    public Action(ActionFunction<T> function) {
        this.function = Objects.requireNonNull(function, "ActionFunction must not be null");
    }

    @Override
    public NodeStates tick(T blackboard) {
        return Objects.requireNonNull(function.apply(blackboard), "ActionFunction return value must not be null");
    }
}
