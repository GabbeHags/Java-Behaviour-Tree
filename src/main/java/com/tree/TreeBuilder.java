package com.tree;

import com.tree.branches.Selector;
import com.tree.branches.Sequence;
import com.tree.nodes.Action;
import com.tree.nodes.ActionToNode;
import com.tree.nodes.Condition;
import com.tree.nodes.ConditionToNode;

/**
 * TreeBuilder is a class to build a behaviour tree with {@link Node}s and {@link Branch}es.
 */
public class TreeBuilder {

    private Node rootNode;
    private Branch currentNode;

    /**
     * Creates a new {@link Sequence} and adds it to the end of the current {@link Branch}.
     *
     * @return this
     */
    public TreeBuilder sequence() {
        if (rootNode == null) {
            rootNode = new Sequence();
            currentNode = (Branch) rootNode;
        }
        else {
            Branch newCurrent = new Sequence();
            currentNode.addNode(newCurrent);
            currentNode = newCurrent;
        }
        return this;
    }

    /**
     * Creates a new {@link Selector} and adds it to the end of the current {@link Branch}.
     *
     * @return this
     */
    public TreeBuilder selector() {
        if (rootNode == null) {
            rootNode = new Selector();
            currentNode = (Branch) rootNode;
        }
        else {
            Branch newCurrent = new Selector();
            currentNode.addNode(newCurrent);
            currentNode = newCurrent;
        }
        return this;
    }

    /**
     * Adds the node to the current {@link Node}.
     *
     * @param node to be added to the current {@link Node}
     *
     * @return this
     */
    public TreeBuilder addNode(Node node) {
        if (currentNode == null) {
            rootNode = node;
        }
        else {
            currentNode.addNode(node);
        }
        return this;
    }

    /**
     * Adds all the nodes to the current {@link Branch}.
     * <p>
     * If {@link Branch} do not exist, {@link NullPointerException} will be given.
     *
     * @param nodes to be added to the current {@link Node}
     *
     * @return this
     */
    public TreeBuilder addNodes(Node... nodes) {
        if (currentNode == null) throw new NullPointerException("Can't add multiple nodes to a leaf");
        for (Node node : nodes) {
            currentNode.addNode(node);
        }
        return this;
    }

    public TreeBuilder addAction(Action action) {
        Node node = new ActionToNode(action);
        addNode(node);
        return this;
    }

    public TreeBuilder addActions(Action... actions) {
        if (currentNode == null) throw new NullPointerException("Can't add multiple nodes to a leaf");
        for (Action action : actions) {
            addAction(action);
        }
        return this;
    }

    public TreeBuilder addCondition(Condition condition) {
        Node node = new ConditionToNode(condition);
        addNode(node);
        return this;
    }

    public TreeBuilder addConditions(Condition... conditions) {
        if (currentNode == null) throw new NullPointerException("Can't add multiple nodes to a leaf");
        for (Condition condition : conditions) {
            addCondition(condition);
        }
        return this;
    }


    /**
     * Builds the tree.
     *
     * @return the tree as a node
     */
    public Node buildTree() {
        return rootNode;
    }

}
