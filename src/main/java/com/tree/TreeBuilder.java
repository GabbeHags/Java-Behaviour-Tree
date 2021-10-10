package com.tree;

public class TreeBuilder {

    private Node rootNode;
    private Branch currentNode;

    public TreeBuilder sequence() {
        if (rootNode == null) {
            rootNode = new Sequence();
            currentNode = (Branch) rootNode;
        }
        else {
            AbstractBranch newCurrent = new Sequence();
            currentNode.addNode(newCurrent);
            currentNode = newCurrent;
        }
        return this;
    }

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

    public TreeBuilder add(Node node) {
        if (currentNode == null) {
            rootNode = node;
        }
        else {
            currentNode.addNode(node);
        }
        return this;
    }

    public TreeBuilder add(Node... nodes) {
        if (currentNode == null) throw new NullPointerException("Can't add multiple nodes to a leaf");
        for (Node node : nodes) {
            currentNode.addNode(node);
        }
        return this;
    }

    public Node buildTree() {
        return rootNode;
    }

}
