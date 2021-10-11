package com.tree;

import com.tree.branches.Selector;
import com.tree.branches.Sequence;
import com.tree.nodes.Node;
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
        Node node = new TreeBuilder().sequence().addNode(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void sequenceSuccessWithManyNodes() {
        Node node = new TreeBuilder().sequence().addNodes(successNode, successNode, successNode).sequence().addNodes(successNode, successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void sequenceRunningWithOneNode() {
        Node node = new TreeBuilder().sequence().addNode(runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void sequenceRunningWithManyNodes() {
        Node node = new TreeBuilder().sequence().addNodes(successNode, successNode, successNode).sequence().addNodes(successNode, runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void sequenceFailureWithOneNode() {
        Node node = new TreeBuilder().sequence().addNode(failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void sequenceFailureWithManyNodes() {
        Node node = new TreeBuilder().sequence().addNodes(successNode, successNode, successNode).sequence().addNodes(successNode, failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void selectorNoNodes() {
        Node node = new TreeBuilder().selector().buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void selectorOnSelector() {
        Node node = new TreeBuilder().selector().selector().addNode(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void selectorSuccessWithOneNode() {
        Node node = new TreeBuilder().selector().addNode(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void selectorSuccessWithManyNodes() {
        Node node1 = new TreeBuilder().selector().addNodes(failureNode, successNode, successNode).buildTree();
        Node mainNode = new TreeBuilder().selector().addNodes(node1, failureNode, runningNode).buildTree();
        assertEquals(NodeStates.SUCCESS, mainNode.execute());
    }

    @Test
    public void selectorRunningWithOneNode() {
        Node node = new TreeBuilder().selector().addNode(runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void selectorRunningWithManyNodes() {
        Node node1 = new TreeBuilder().selector().addNodes(failureNode, runningNode, failureNode).buildTree();
        Node mainNode = new TreeBuilder().selector().addNodes(node1, failureNode, runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, mainNode.execute());
    }

    @Test
    public void selectorFailureWithOneNode() {
        Node node = new TreeBuilder().selector().addNode(failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void selectorFailureWithManyNodes() {
        Node node1 = new TreeBuilder().selector().addNodes(failureNode, failureNode, failureNode).buildTree();
        Node mainNode = new TreeBuilder().selector().addNodes(node1, failureNode, failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, mainNode.execute());
    }

    @Test
    public void addSuccessOneNode() {
        Node node = new TreeBuilder().addNode(successNode).buildTree();
        assertEquals(NodeStates.SUCCESS, node.execute());
    }

    @Test
    public void addFailureOneNode() {
        Node node = new TreeBuilder().addNode(failureNode).buildTree();
        assertEquals(NodeStates.FAILURE, node.execute());
    }

    @Test
    public void addRunningOneNode() {
        Node node = new TreeBuilder().addNode(runningNode).buildTree();
        assertEquals(NodeStates.RUNNING, node.execute());
    }

    @Test
    public void addSuccessManyNodes() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.addNodes(successNode, successNode);
            fail("Can't add multiple nodes to a leaf");
        }
        catch (NullPointerException ignored){}
    }

    @Test
    public void addFailureManyNodes() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.addNodes(failureNode, failureNode);
            fail("Can't add multiple nodes to a leaf");
        }
        catch (NullPointerException ignored){}
    }

    @Test
    public void addRunningManyNodes() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.addNodes(runningNode, runningNode);
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