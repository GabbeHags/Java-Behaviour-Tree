package com.behaviour.tree;

import com.behaviour.tree.branches.Branch;
import com.behaviour.tree.branches.Selector;
import com.behaviour.tree.branches.Sequence;
import com.behaviour.tree.nodes.Action;
import com.behaviour.tree.nodes.Condition;
import com.behaviour.tree.nodes.Node;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public class TreeBuilder<T> {

    private Branch<T> parent;
    private final Deque<Branch<T>> branchStack;

    public TreeBuilder() {
        branchStack = new ArrayDeque<>();
    }


    public TreeBuilder<T> addAction(Function<T, NodeStates> function) {
        return add(new Action<>(function));
    }


    public TreeBuilder<T> addCondition(Function<T, Boolean> function) {
        return add(new Condition<>(function));
    }


    public TreeBuilder<T> sequence() {
        return add(new Sequence<>());
    }


    public TreeBuilder<T> selector() {
        return add(new Selector<>());
    }

    public TreeBuilder<T> add(Branch<T> parent) {
        if (this.parent != null && branchStack.isEmpty()) {
            throw new NullPointerException("MismatchedEndException"); // TODO throw a custom error e.g MismatchedEndException
        }
        if(!branchStack.isEmpty()) {
            branchStack.peek().addNode(parent);
        }
        branchStack.push(parent);
        return this;
    }

    public TreeBuilder<T> add(Node<T> node) {
        if (branchStack.peek() == null) {
            throw new NullPointerException("MissingRootBranchException"); // TODO throw a custom error e.g MissingRootBranchException
        }
        branchStack.peek().addNode(node);
        return this;
    }

    @SafeVarargs
    public final TreeBuilder<T> add(Node<T>... nodes) {
        privateAdd(Arrays.asList(nodes));
        return this;
    }

    private void privateAdd(List<Node<T>> nodes) {
        for (Node<T> node : nodes) {
            add(node);
        }
    }

    public TreeBuilder<T> end() {
        if (branchStack.isEmpty()) {
            throw new NullPointerException("TooManyEndsException"); // TODO throw a custom error e.g EndedRootBranchException
        }
        parent = branchStack.pop();
        return this;
    }

    public Node<T> buildTree() {
        if (parent == null ) {
            throw new NullPointerException("BuildingEmptyTreeException"); // TODO add custom error BuildingEmptyTreeException
        }
        if (!branchStack.isEmpty()) {
            throw new NullPointerException("MissingEndException"); // TODO add custom error MissingEndException
        }
        return parent;
    }
}
