
package studentRecordsBackupTree.bst;

import java.util.ArrayList;

import studentRecordsBackupTree.util.ExceptionHandler;

public class Node implements NodeInterface {
    public int value;
    public String description;
    public NodeInterface left;
    public NodeInterface right;
    private ArrayList<NodeInterface> subscribers = new ArrayList<NodeInterface>();

    public Node(int valueIn) {
        value = valueIn;
        description = "The b-Number of the student is : " + value;
    }

    public ArrayList<NodeInterface> getSubscribers() {
        return subscribers;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int valueIn) {
        value = valueIn;
    }

    public boolean subscribe(NodeInterface nodeIn) {

        return subscribers.add(nodeIn);

    }

    public boolean unsubscribe(NodeInterface nodeIn) {

        return subscribers.remove(nodeIn);

    }

    public boolean notifyAllSubscribers(int updateValueIn) {

        try {

            subscribers.forEach((subs) -> {
                subs.updateNodeValue(updateValueIn);
            });

        } catch (Exception exceptionIn) {
            ExceptionHandler.handleException(exceptionIn, "Failed to notify subscribers");
            return false;
        } finally {

        }
        return true;
    }

    // Notify all subscribers
    public void updateAndBackup(int updateValueIn) {

        updateNodeValue(updateValueIn);
        notifyAllSubscribers(updateValueIn);

    }

    public void updateNodeValue(int updateValueIn) {
        value += updateValueIn;
    }

    public NodeInterface getLeftNode() {
        return left;
    }

    public void setLeftNode(NodeInterface nodeIn) {
        left = nodeIn;
    }

    public NodeInterface getRightNode() {
        return right;
    }

    public void setRightNode(NodeInterface nodeIn) {
        right = nodeIn;
    }

}
