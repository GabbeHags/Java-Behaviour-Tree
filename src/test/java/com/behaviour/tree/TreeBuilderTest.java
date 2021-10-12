package com.behaviour.tree;

import com.behaviour.tree.branches.Selector;
import com.behaviour.tree.branches.Sequence;
import com.behaviour.tree.nodes.Node;
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
        Node node = new TreeBuilder().sequence().end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick());
    }

    @Test
    public void sequenceSuccessWithOneNode() {
        Node node = new TreeBuilder().sequence().add(successNode).end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick());
    }

    @Test
    public void sequenceSuccessWithManyNodes() {
        Node node = new TreeBuilder().sequence().add(successNode, successNode, successNode).sequence().add(successNode, successNode).end().end().buildTree();
        System.out.println();
        assertEquals(NodeStates.SUCCESS, node.tick());
    }

    @Test
    public void sequenceRunningWithOneNode() {
        Node node = new TreeBuilder().sequence().add(runningNode).end().buildTree();
        assertEquals(NodeStates.RUNNING, node.tick());
    }

    @Test
    public void sequenceRunningWithManyNodes() {
        Node node = new TreeBuilder().sequence().add(successNode, successNode, successNode).sequence().add(successNode, runningNode).end().end().buildTree();
        assertEquals(NodeStates.RUNNING, node.tick());
    }

    @Test
    public void sequenceFailureWithOneNode() {
        Node node = new TreeBuilder().sequence().add(failureNode).end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick());
    }

    @Test
    public void sequenceFailureWithManyNodes() {
        Node node = new TreeBuilder().sequence().add(successNode, successNode, successNode).sequence().add(successNode, failureNode).end().end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick());
    }

    @Test
    public void selectorNoNodes() {
        Node node = new TreeBuilder().selector().end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick());
    }

    @Test
    public void selectorOnSelector() {
        Node node = new TreeBuilder().selector().selector().add(successNode).end().end().buildTree();
        System.out.println();
        assertEquals(NodeStates.SUCCESS, node.tick());
    }

    @Test
    public void selectorSuccessWithOneNode() {
        Node node = new TreeBuilder().selector().add(successNode).end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick());
    }

    @Test
    public void selectorSuccessWithManyNodes() {
        Node node1 = new TreeBuilder().selector().add(failureNode, successNode, successNode).end().buildTree();
        Node mainNode = new TreeBuilder().selector().add(node1, failureNode, runningNode).end().buildTree();
        assertEquals(NodeStates.SUCCESS, mainNode.tick());
    }

    @Test
    public void selectorRunningWithOneNode() {
        Node node = new TreeBuilder().selector().add(runningNode).end().buildTree();
        assertEquals(NodeStates.RUNNING, node.tick());
    }

    @Test
    public void selectorRunningWithManyNodes() {
        Node node1 = new TreeBuilder().selector().add(failureNode, runningNode, failureNode).end().buildTree();
        Node mainNode = new TreeBuilder().selector().add(node1, failureNode, runningNode).end().buildTree();
        assertEquals(NodeStates.RUNNING, mainNode.tick());
    }

    @Test
    public void selectorFailureWithOneNode() {
        Node node = new TreeBuilder().selector().add(failureNode).end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick());
    }

    @Test
    public void selectorFailureWithManyNodes() {
        Node node1 = new TreeBuilder().selector().add(failureNode, failureNode, failureNode).end().buildTree();
        Node mainNode = new TreeBuilder().selector().add(node1, failureNode, failureNode).end().buildTree();
        assertEquals(NodeStates.FAILURE, mainNode.tick());
    }

    @Test
    public void addNodeToEmptyTree() {
        TreeBuilder treeBuilder = new TreeBuilder();
        try {
            treeBuilder.add(successNode).end();
            fail();
        }
        catch (NullPointerException ignored){} // TODO change to correct error
    }

    @Test
    public void buildTreeSequence() {
        Node node = new TreeBuilder().sequence().end().buildTree();
        assertTrue(node instanceof Sequence);
    }

    @Test
    public void buildTreeSelector() {
        Node node = new TreeBuilder().selector().end().buildTree();
        assertTrue(node instanceof Selector);
    }
}