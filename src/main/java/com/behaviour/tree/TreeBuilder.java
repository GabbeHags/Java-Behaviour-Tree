package com.behaviour.tree;

import com.behaviour.tree.branches.Branch;
import com.behaviour.tree.branches.Selector;
import com.behaviour.tree.branches.Sequence;
import com.behaviour.tree.exceptions.*;
import com.behaviour.tree.nodes.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


/**
 * TreeBuilder is a class to build a behaviour tree with {@link Node}s and {@link Branch}es.
 */
public class TreeBuilder<T> {

    private Branch<T> parent;
    private final Deque<Branch<T>> branchStack;

    public TreeBuilder() {
        branchStack = new ArrayDeque<>();
    }


    public TreeBuilder<T> addAction(ActionFunction<T> function) {
        return add(new Action<>(function));
    }


    public TreeBuilder<T> addCondition(ConditionFunction<T> function) {
        return add(new Condition<>(function));
    }

    /**
     * Creates a new {@link Sequence} and adds it to the end of the current {@link Branch}.
     *
     * @return this
     */
    public TreeBuilder<T> sequence() {
        return add(new Sequence<>());
    }

    /**
     * Creates a new {@link Selector} and adds it to the end of the current {@link Branch}.
     *
     * @return this
     */
    public TreeBuilder<T> selector() {
        return add(new Selector<>());
    }

    public TreeBuilder<T> add(Branch<T> parent) {
        if (this.parent != null && branchStack.isEmpty()) {
            throw new MismatchedEndException();
        }
        if(!branchStack.isEmpty()) {
            branchStack.peek().addNode(parent);
        }
        branchStack.push(parent);
        return this;
    }

    /**
     * Adds the node to the current {@link Branch}.
     * <p>
     * If root {@link Branch} do not exist, {@link MissingRootBranchException} will be given.
     *
     * @param node to be added to the current {@link Branch}
     *
     * @return this
     */
    public TreeBuilder<T> add(Node<T> node) {
        if (branchStack.peek() == null) {
            throw new MissingRootBranchException();
        }
        branchStack.peek().addNode(node);
        return this;
    }

    /**
     * Adds all the nodes to the current {@link Branch}.
     *<p>
     * If root {@link Branch} do not exist, {@link MissingRootBranchException} will be given.
     *
     * @param nodes to be added to the current {@link Branch}
     *
     * @return this
     */
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

    /**
     * Ends the current branch.
     * <p>
     * If to many ends are given, {@link TooManyEndsException} will be given.
     *
     * @return this
     */
    public TreeBuilder<T> end() {
        if (branchStack.isEmpty()) {
            throw new TooManyEndsException();
        }
        parent = branchStack.pop();
        return this;
    }

    /**
     * Builds the tree.
     *
     * @return the tree as a node
     */
    public Node<T> buildTree() {
        if (!branchStack.isEmpty()) {
            throw new MissingEndException();
        }
        if (parent == null ) {
            throw new BuildingEmptyTreeException();
        }
        return parent;
    }
}
