package com.tree;

public class Selector extends AbstractBranch {

    @Override
    public NodeStates execute() {
        for (Node node : nodes) {
            NodeStates nodeState = node.execute();
            if (nodeState == NodeStates.FAILURE) {
                state = NodeStates.FAILURE;
            }
            else if (nodeState == NodeStates.RUNNING) {
                state = NodeStates.RUNNING;
                return state;
            }
            else if (nodeState == NodeStates.SUCCESS) {
                state = NodeStates.SUCCESS;
                return state;
            }
        }
        return state;
    }
}
