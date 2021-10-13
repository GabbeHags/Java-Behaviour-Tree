package com.behaviour.tree;

import com.behaviour.tree.branches.Selector;
import com.behaviour.tree.branches.Sequence;
import com.behaviour.tree.nodes.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeBuilderTest {

    private class MockBlackBoard {}
    
    Node<MockBlackBoard> successNode;
    Node<MockBlackBoard> failureNode;
    Node<MockBlackBoard> runningNode;
    TreeBuilder<MockBlackBoard> treeBuilder;
    MockBlackBoard blackBoard;

    @Before
    public void setUp() {
        blackBoard = new MockBlackBoard();
        treeBuilder = new TreeBuilder<>();
        successNode = (a) -> NodeStates.SUCCESS;
        failureNode = (a) -> NodeStates.FAILURE;
        runningNode = (a) -> NodeStates.RUNNING;
    }

    @Test
    public void sequenceNoNodes() {
        Node<MockBlackBoard> node = treeBuilder.sequence().end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick(blackBoard));
    }

    @Test
    public void sequenceSuccessWithOneNode() {
        Node<MockBlackBoard> node = treeBuilder.sequence().add(successNode).end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick(blackBoard));
    }

    @Test
    public void sequenceSuccessWithManyNodes() {
        Node<MockBlackBoard> node = treeBuilder.sequence().add(successNode, successNode, successNode).sequence().add(successNode, successNode).end().end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick(blackBoard));
    }

    @Test
    public void sequenceRunningWithOneNode() {
        Node<MockBlackBoard> node = treeBuilder.sequence().add(runningNode).end().buildTree();
        assertEquals(NodeStates.RUNNING, node.tick(blackBoard));
    }

    @Test
    public void sequenceRunningWithManyNodes() {
        Node<MockBlackBoard> node = treeBuilder.sequence().add(successNode, successNode, successNode).sequence().add(successNode, runningNode).end().end().buildTree();
        assertEquals(NodeStates.RUNNING, node.tick(blackBoard));
    }

    @Test
    public void sequenceFailureWithOneNode() {
        Node<MockBlackBoard> node = treeBuilder.sequence().add(failureNode).end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick(blackBoard));
    }

    @Test
    public void sequenceFailureWithManyNodes() {
        Node<MockBlackBoard> node = treeBuilder.sequence().add(successNode, successNode, successNode).sequence().add(successNode, failureNode).end().end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick(blackBoard));
    }

    @Test
    public void selectorNoNodes() {
        Node<MockBlackBoard> node = treeBuilder.selector().end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick(blackBoard));
    }

    @Test
    public void selectorOnSelector() {
        Node<MockBlackBoard> node = treeBuilder.selector().selector().add(successNode).end().end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick(blackBoard));
    }

    @Test
    public void selectorSuccessWithOneNode() {
        Node<MockBlackBoard> node = treeBuilder.selector().add(successNode).end().buildTree();
        assertEquals(NodeStates.SUCCESS, node.tick(blackBoard));
    }

    @Test
    public void selectorSuccessWithManyNodes() {
        Node<MockBlackBoard> node1 = new TreeBuilder<MockBlackBoard>().selector().add(failureNode, successNode, successNode).end().buildTree();
        Node<MockBlackBoard> mainNode = treeBuilder.selector().add(node1, failureNode, runningNode).end().buildTree();
        assertEquals(NodeStates.SUCCESS, mainNode.tick(blackBoard));
    }

    @Test
    public void selectorRunningWithOneNode() {
        Node<MockBlackBoard> node = treeBuilder.selector().add(runningNode).end().buildTree();
        assertEquals(NodeStates.RUNNING, node.tick(blackBoard));
    }

    @Test
    public void selectorRunningWithManyNodes() {
        Node<MockBlackBoard> node1 = new TreeBuilder<MockBlackBoard>().selector().add(failureNode, runningNode, failureNode).end().buildTree();
        Node<MockBlackBoard> mainNode = treeBuilder.selector().add(node1, failureNode, runningNode).end().buildTree();
        assertEquals(NodeStates.RUNNING, mainNode.tick(blackBoard));
    }

    @Test
    public void selectorFailureWithOneNode() {
        Node<MockBlackBoard> node = treeBuilder.selector().add(failureNode).end().buildTree();
        assertEquals(NodeStates.FAILURE, node.tick(blackBoard));
    }

    @Test
    public void selectorFailureWithManyNodes() {
        Node<MockBlackBoard> node1 = new TreeBuilder<MockBlackBoard>().selector().add(failureNode, failureNode, failureNode).end().buildTree();
        Node<MockBlackBoard> mainNode = treeBuilder.selector().add(node1, failureNode, failureNode).end().buildTree();
        assertEquals(NodeStates.FAILURE, mainNode.tick(blackBoard));
    }

    @Test
    public void addNodeToEmptyTree() {
        TreeBuilder<MockBlackBoard> treeBuilder = new TreeBuilder<>();
        try {
            treeBuilder.add(successNode).end();
            fail();
        }
        catch (NullPointerException ignored){} // TODO change to correct error
    }

    @Test
    public void buildTreeSequence() {
        Node<MockBlackBoard> node = treeBuilder.sequence().end().buildTree();
        assertTrue(node instanceof Sequence);
    }

    @Test
    public void buildTreeSelector() {
        Node<MockBlackBoard> node = treeBuilder.selector().end().buildTree();
        assertTrue(node instanceof Selector);
    }
}