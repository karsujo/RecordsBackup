package studentRecordsBackupTree.bst;

import java.util.ArrayList;
import studentRecordsBackupTree.util.ExceptionHandler;
import studentRecordsBackupTree.util.FileProcessorInterface;
import studentRecordsBackupTree.util.ResultsInterface;

public class BSTBuilder implements BSTBuilderInterface {

    public BSTInterface mainTree;
    public BSTInterface backupTree1;
    public BSTInterface backupTree2;

    public BSTBuilder(ResultsInterface outputFileProcessorIn) {

        // Create 3 BST instances with according names
        mainTree = new BST(outputFileProcessorIn, "BST");
        backupTree1 = new BST(outputFileProcessorIn, "Backup-1");
        backupTree2 = new BST(outputFileProcessorIn, "Backup-2");

    }

    public BSTInterface getMainTree() {
        return this.mainTree;
    }

    public void setMainTree(BSTInterface mainTree) {
        this.mainTree = mainTree;
    }

    public BSTInterface getBackupTree1() {
        return this.backupTree1;
    }

    public void setBackupTree1(BSTInterface backupTree1) {
        this.backupTree1 = backupTree1;
    }

    public BSTInterface getBackupTree2() {
        return this.backupTree2;
    }

    public void setBackupTree2(BSTInterface backupTree2) {
        this.backupTree2 = backupTree2;
    }

    public void buildTreesFromFile(String inputFileName, FileProcessorInterface fileProcessor) {
        try {
            ArrayList<String> inputs = fileProcessor.readInputFile();
            for (String st : inputs) {

                Integer element = Integer.valueOf(st);

                NodeInterface mainNode = new Node(element);

                NodeInterface backupNode1 = new Node(element);
                NodeInterface backupNode2 = new Node(element);

                // Create Observers
                mainNode.subscribe(backupNode1);
                mainNode.subscribe(backupNode2);

                // Insert into each respective tree
                mainTree.buildBST(mainNode);
                backupTree1.buildBST(backupNode1);
                backupTree2.buildBST(backupNode2);
            }
        } catch (Exception exception) {
            ExceptionHandler.handleException(exception, "");
        } finally {

        }

    }

}
