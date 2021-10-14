package com.behaviour.tree.nodes;

import com.behaviour.tree.NodeStates;

import java.util.Objects;


public class Condition<T> implements Node<T> {

    private final ConditionFunction<T> function;

    public Condition(ConditionFunction<T> function) {
        this.function = Objects.requireNonNull(function, "ConditionFunction must not be null");
    }

    @Override
    public NodeStates tick(T blackboard) {
        if (Boolean.TRUE.equals(Objects.requireNonNull(function.apply(blackboard),
                "ConditionFunction return value must not be null"))) {
            return NodeStates.SUCCESS;
        }
        return NodeStates.FAILURE;
    }
}
