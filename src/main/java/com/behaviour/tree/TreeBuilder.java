package com.behaviour.tree;

import com.behaviour.tree.branches.Branch;
import com.behaviour.tree.branches.Selector;
import com.behaviour.tree.branches.Sequence;
import com.behaviour.tree.nodes.Action;
import com.behaviour.tree.nodes.Condition;
import com.behaviour.tree.nodes.Node;
import com.behaviour.tree.utils.Function;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeBuilder {

    private Branch parent;
    private final Deque<Branch> branchStack;

    public TreeBuilder() {
        branchStack = new ArrayDeque<>();
    }


    public TreeBuilder addAction(Function<NodeStates> function) {
        return add(new Action(function));
    }


    public TreeBuilder addCondition(Function<Boolean> function) {
        return add(new Condition(function));
    }


    public TreeBuilder sequence() {
        return add(new Sequence());
    }


    public TreeBuilder selector() {
        return add(new Selector());
    }

    public TreeBuilder add(Branch parent) {
        if (this.parent != null && branchStack.isEmpty()) {
            throw new NullPointerException("MismatchedEndException"); // TODO throw a custom error e.g MismatchedEndException
        }
        if(!branchStack.isEmpty()) {
            branchStack.peek().addNode(parent);
        }
        branchStack.push(parent);
        return this;
    }

    public TreeBuilder add(Node node) {
        if (branchStack.peek() == null) {
            throw new NullPointerException("MissingRootBranchException"); // TODO throw a custom error e.g MissingRootBranchException
        }
        branchStack.peek().addNode(node);
        return this;
    }

    public TreeBuilder add(Node... nodes) {
        for (Node node : nodes) {
            add(node);
        }
        return this;
    }

    public TreeBuilder end() {
        if (branchStack.isEmpty()) {
            throw new NullPointerException("TooManyEndsException"); // TODO throw a custom error e.g EndedRootBranchException
        }
        parent = branchStack.pop();
        return this;
    }

    public Node buildTree() {
        if (parent == null ) {
            throw new NullPointerException("BuildingEmptyTreeException"); // TODO add custom error BuildingEmptyTreeException
        }
        if (!branchStack.isEmpty()) {
            throw new NullPointerException("MissingEndException"); // TODO add custom error MissingEndException
        }
        return parent;
    }
}
