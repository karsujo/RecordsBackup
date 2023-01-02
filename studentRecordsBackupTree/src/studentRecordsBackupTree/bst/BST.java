
package studentRecordsBackupTree.bst;

import studentRecordsBackupTree.util.ResultsInterface;

public class BST implements BSTInterface {

    public NodeInterface root;

    public String name;

    private int sumOfNodes = 0;

    private ResultsInterface _outputFileProcessor;

    public BST(ResultsInterface outputFileProcessorIn, String nameIn) {
        root = null;
        _outputFileProcessor = outputFileProcessorIn;
        name = nameIn;
    }

    //Insert node into BST
    public void buildBST(NodeInterface nodeIn) {
        NodeInterface rootCopy = root;
        rootCopy = insertNodeIntoBST(rootCopy, nodeIn);
        root = rootCopy;
    }

    private NodeInterface insertNodeIntoBST(NodeInterface nodeIn, NodeInterface insertNodeIn) {
        if (nodeIn == null) {
            nodeIn = insertNodeIn;
            return nodeIn;
        } else if ((nodeIn.getRightNode() == null) && (insertNodeIn.getValue() > nodeIn.getValue())) {
            nodeIn.setRightNode(insertNodeIn);
            return nodeIn;
        } else if ((nodeIn.getLeftNode() == null) && (insertNodeIn.getValue() < nodeIn.getValue())) {
            nodeIn.setLeftNode(insertNodeIn);
            return nodeIn;
        }

        if (insertNodeIn.getValue() < nodeIn.getValue()) {
            insertNodeIntoBST(nodeIn.getLeftNode(), insertNodeIn);
        } else {
            insertNodeIntoBST(nodeIn.getRightNode(), insertNodeIn);
        }

        return nodeIn;

    }

    public void updateAllNodes(int updateValue) {

        NodeInterface rootCopy = root;
        updateEachNodeRecursively(rootCopy, updateValue);

    }

    private void updateEachNodeRecursively(NodeInterface nodeIn, int updateValueIn) {
        if (nodeIn == null) {
            return;
        }

        nodeIn.updateAndBackup(updateValueIn);
        updateEachNodeRecursively(nodeIn.getLeftNode(), updateValueIn);
        updateEachNodeRecursively(nodeIn.getRightNode(), updateValueIn);
    }

    public void printTreeInorder() {
        NodeInterface rootCopy = root;
        String printResult = printInorderRecursively(rootCopy);
        printResult = printResult.substring(0, printResult.length() - 1); // remove last ,
        String resultString = name + ": " + printResult;
        _outputFileProcessor.writeToConsole(resultString);
        _outputFileProcessor.writeToFile(resultString);

    }

    // TODO: Check if really Inorder
    private String printInorderRecursively(NodeInterface nodeIn) {
        if (nodeIn == null)
            return "";

        String result = "";
        result += printInorderRecursively(nodeIn.getLeftNode());
        result += String.valueOf(nodeIn.getValue()) + ",";
        result += printInorderRecursively(nodeIn.getRightNode());

        return result;

    }

    public void printSumOfAllNodes() {
        sumOfNodes = 0;

        NodeInterface rootCopy = root;

        getSumOfAllNodesRecursively(rootCopy);

        String resultString = name + ": " + String.valueOf(sumOfNodes);

        _outputFileProcessor.writeToConsole(resultString);

        _outputFileProcessor.writeToFile(resultString);

    }

    private void getSumOfAllNodesRecursively(NodeInterface nodeIn) {

        if (nodeIn == null)
            return;

        getSumOfAllNodesRecursively(nodeIn.getLeftNode());

        sumOfNodes += nodeIn.getValue();

        getSumOfAllNodesRecursively(nodeIn.getRightNode());
    }

}
