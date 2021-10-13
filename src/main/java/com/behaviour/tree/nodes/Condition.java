package com.behaviour.tree.nodes;

import com.behaviour.tree.NodeStates;

import java.util.function.Function;


public class Condition<T> implements Node<T> {

    private final ConditionFunction<T> function;

    public Condition(ConditionFunction<T> function) {
        this.function = function;
    }

    @Override
    public NodeStates tick(T blackBoard) {
        if (Boolean.TRUE.equals(function.apply(blackBoard))) {
            return NodeStates.SUCCESS;
        }
        return NodeStates.FAILURE;
    }
}
