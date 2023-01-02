package studentRecordsBackupTree.bst;

import studentRecordsBackupTree.util.FileProcessorInterface;

public interface BSTBuilderInterface {
    public void buildTreesFromFile(String inputFileName, FileProcessorInterface fileProcessor);

    public BSTInterface getMainTree();

    public void setMainTree(BSTInterface mainTree);

    public BSTInterface getBackupTree1();

    public void setBackupTree1(BSTInterface backupTree1);

    public BSTInterface getBackupTree2();

    public void setBackupTree2(BSTInterface backupTree2);
}
