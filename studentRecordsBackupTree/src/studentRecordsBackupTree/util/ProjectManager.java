package studentRecordsBackupTree.util;

import studentRecordsBackupTree.bst.BSTBuilder;
import studentRecordsBackupTree.bst.BSTBuilderInterface;

public class ProjectManager implements ProjectManagerInterface {

    public void runProject(String inputFileName, String outputFileName, String errorFileName, int DebugLevel,
            int updateValue) {

        ResultsInterface errorFileProcessor = new Results(errorFileName);
        ExceptionHandler.errorLogProcessor = errorFileProcessor;

        FileProcessorInterface inputFileProcessor = new FileProcessor(inputFileName);

        if (!inputFileProcessor.fileExists()) {
            ExceptionHandler.handleException(null, "Unable to locate file : " + inputFileProcessor.getFileName());
        }

        ResultsInterface outputFileProcessor = new Results(outputFileName);
        BSTBuilderInterface manager = new BSTBuilder(outputFileProcessor);

        MyLogger.setDebugValue(DebugLevel);

        manager.buildTreesFromFile(inputFileName, inputFileProcessor);

        // Inorder Traversal
        outputFileProcessor.writeToOutput("Inorder Traversal: ");

        manager.getMainTree().printTreeInorder();
        manager.getBackupTree1().printTreeInorder();
        manager.getBackupTree2().printTreeInorder();

        outputFileProcessor.writeToOutput("\n");

        // Sum of all the B-Numbers in each tree
        outputFileProcessor.writeToOutput("Sum of all the B-Numbers in each tree: ");

        manager.getMainTree().printSumOfAllNodes();
        manager.getBackupTree1().printSumOfAllNodes();
        manager.getBackupTree2().printSumOfAllNodes();

        outputFileProcessor.writeToOutput("\n");

        //Update Main and Backup Trees with UpdateValue
        manager.getMainTree().updateAllNodes(updateValue);

        // Sum of all the B-Numbers after increment
        outputFileProcessor.writeToOutput("Sum of all the B-Numbers after increment: ");

        manager.getMainTree().printSumOfAllNodes();
        manager.getBackupTree1().printSumOfAllNodes();
        manager.getBackupTree2().printSumOfAllNodes();

        errorFileProcessor.closeInterface();
        outputFileProcessor.closeInterface();

    }

}
