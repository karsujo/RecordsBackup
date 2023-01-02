package studentRecordsBackupTree.bst;

public interface NodeInterface extends NodeObserverInterface, NodeSubscriberInterface {

    public NodeInterface getLeftNode();

    public void setLeftNode(NodeInterface nodeIn);

    public NodeInterface getRightNode();

    public void setRightNode(NodeInterface nodeIn);

    public void updateAndBackup(int updateValueIn);

    public void setValue(int valueIn);

    public int getValue();

}
