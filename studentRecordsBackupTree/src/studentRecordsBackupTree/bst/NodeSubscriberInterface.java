package studentRecordsBackupTree.bst;

import java.util.ArrayList;

public interface NodeSubscriberInterface {

    ArrayList<NodeInterface> getSubscribers();

    public boolean subscribe(NodeInterface nodeIn);

    public boolean unsubscribe(NodeInterface nodeIn);

    public boolean notifyAllSubscribers(int updateValueIn);

}
