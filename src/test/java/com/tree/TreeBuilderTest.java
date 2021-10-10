package com.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeBuilderTest {

    Node successNode;
    Node failureNode;
    Node runningNode;

    @Before
    public void setUp() throws Exception {
        successNode = () -> NodeStates.SUCCESS;
        failureNode = () -> NodeStates.FAILURE;
        runningNode = () -> NodeStates.RUNNING;
    }

    @Test
    public void sequenceNoNodes() {
        Node node = new TreeBuilder().sequence().buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void sequenceSuccessWithOneNode() {
        Node node = new TreeBuilder().sequence().add(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void sequenceSuccessWithManyNodes() {
        Node node = new TreeBuilder().sequence().add(successNode, successNode, successNode).sequence().add(successNode, successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void sequenceRunningWithOneNode() {
        Node node = new TreeBuilder().sequence().add(runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void sequenceRunningWithManyNodes() {
        Node node = new TreeBuilder().sequence().add(successNode, successNode, successNode).sequence().add(successNode, runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void sequenceFailureWithOneNode() {
        Node node = new TreeBuilder().sequence().add(failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void sequenceFailureWithManyNodes() {
        Node node = new TreeBuilder().sequence().add(successNode, successNode, successNode).sequence().add(successNode, failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void selectorNoNodes() {
        Node node = new TreeBuilder().selector().buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void selectorSuccessWithOneNode() {
        Node node = new TreeBuilder().selector().add(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void selectorSuccessWithManyNodes() {
        Node node1 = new TreeBuilder().selector().add(failureNode, successNode, successNode).buildTree();
        Node mainNode = new TreeBuilder().selector().add(node1, failureNode, runningNode).buildTree();
        assertEquals(NodeStates.SUCCESS, mainNode.execute());
    }

    @Test
    public void selectorRunningWithOneNode() {
        Node node = new TreeBuilder().selector().add(runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void selectorRunningWithManyNodes() {
        Node node1 = new TreeBuilder().selector().add(failureNode, runningNode, failureNode).buildTree();
        Node mainNode = new TreeBuilder().selector().add(node1, failureNode, runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, mainNode.execute());
    }

    @Test
    public void selectorFailureWithOneNode() {
        Node node = new TreeBuilder().selector().add(failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void selectorFailureWithManyNodes() {
        Node node1 = new TreeBuilder().selector().add(failureNode, failureNode, failureNode).buildTree();
        Node mainNode = new TreeBuilder().selector().add(node1, failureNode, failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, mainNode.execute());
    }

    @Test
    public void addSuccessOneNode() {
        Node node = new TreeBuilder().add(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void addFailureOneNode() {
        Node node = new TreeBuilder().add(failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void addRunningOneNode() {
        Node node = new TreeBuilder().add(runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void addSuccessManyNodes() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.add(successNode, successNode);
            fail("Can't add multiple nodes to a leaf");
        }
        catch (NullPointerException ignored){}
    }

    @Test
    public void addFailureManyNodes() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.add(failureNode, failureNode);
            fail("Can't add multiple nodes to a leaf");
        }
        catch (NullPointerException ignored){}
    }

    @Test
    public void addRunningManyNodes() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.add(runningNode, runningNode);
            fail("Can't add multiple nodes to a leaf");
        }
        catch (NullPointerException ignored){}
    }

    @Test
    public void buildTreeSequence() {
        Node node = new TreeBuilder().sequence().buildTree();
        assertTrue(node instanceof Sequence);
    }

    @Test
    public void buildTreeSelector() {
        Node node = new TreeBuilder().selector().buildTree();
        assertTrue(node instanceof Selector);
    }
}