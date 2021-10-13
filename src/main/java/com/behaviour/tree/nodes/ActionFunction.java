package com.behaviour.tree.nodes;

import com.behaviour.tree.NodeStates;

import java.util.function.Function;

public interface ActionFunction<T> extends Function<T, NodeStates> {
}
