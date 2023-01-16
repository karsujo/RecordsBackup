## ANT Commands 
 **Note: build.xml is present in studentRecordsBackupTree/src folder.**

#### Instruction to clean:

**Command: ant -buildfile studentRecordsBackupTree/src/build.xml clean**
Description: It cleans up all the .class files that were generated when you
compiled your code.

#### Instruction to compile:

 **Command: ant -buildfile studentRecordsBackupTree/src/build.xml all**

Description: Compiles your code and generates .class files inside the BUILD folder.

#### Instruction to run:

 **Command: ant -buildfile studentRecordsBackupTree/src/build.xml run -Darg0=bstInput.txt -Darg1=bstOutput.txt -Darg2=errorLog.txt -Darg3=1 -Darg4=1**

Format: InputFile, OutputFile, ErrorFile, DEBUG_LEVEL, UPDATE_VALUE

Note : all the input/output files are expected to be at the level of the src/ directory. Example:
studentRecordsBackupTree/src
studentRecordsBackupTree/bstInput.txt
studentRecordsBackupTree/bstOutput.txt
studentRecordsBackupTree/errLog.txt

-----------------------------------------------------------------------
## Description

### Code Design :

**Overall Architecture**

The ProjectManager class acts as the driver for this program, where the actual driver class simply checks for boundary conditions, and then passes the cmdline arguments to the ProjectManager class. 

ProjectManager is responsible for creating the required file objects, and calling the BSTBuilder. Apart from this, the sequence of steps to be performed in order to generate the output as defined in the requirements is done by this class (calling the various print functions, as well as performing the **node level update**)

BSTBuilder class creates and manages the three Binary Search trees. It uses the FileProcessor object to read each element from the input, and later calls the relevant BST methods to insert the node into the tree, as well as print inorder traversals, etc.

**Core Workings**


**BSTBuilder**

When the BSTBuilder class reads each input line by line, it creates three Node instances, one for the main BST and the other two for BST-1 and BST-2. The nodes for BST1,2 are then subscribed to the main BST node. This is where the Publisher-Subscriber (Observer) relationship is established between the nodes of all three trees. 

Post this, each node is inserted into its respective tree by calling methods in the BST Class. 

**Node**
The Node class implements the Subject and Observer interfaces, and thereby has methods to subscribe, notify and update. When node A subscribes to node B, node B has a data structure that stores A. Every time B has to notify its subscribers, B iteratres through the data structure and calls the update method on each object. 

**The Observer Pattern**

````

 ProjectManager.java:
 --------------------
 manager.getMainTree().updateAllNodes(updateValue);

 BST.java:
 -----------

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

 Node.java:
 -----------

    public void updateAndBackup(int updateValueIn) {

        updateNodeValue(updateValueIn);
        notifyAllSubscribers(updateValueIn);

    }

    public void updateNodeValue(int updateValueIn) {
        value += updateValueIn;
    }

````
On the Main BST, the **updateAllNodes** method is called. This method traverses through each node in the main tree and calls each nodes **updateAndBackup** method. This method updates the value of the current node (on main BST), and then proceeds to call the **notifyAllSubscribers** method. This in turn iterates through the ArrayList and for each node present, it calls the **updateNodeValue** method. The updateNodeValue method only updates the value of the current node. 


### Algorithm :

No particular algorithm is used for the observer itself, however, inorder traversal is used to print the trees. 
 
### Data-Structure :

The (Subject) Nodes use an **ArrayList** to store all their subscribers. Assuming the input file will grow, the BST is built line by line to prevent storing the entire input in memory into  as ingle data structure. If the number of nodes keeps growing in the BST, the number of observers will also grow at the same rate. Since we are not doing any searach operations/ other comolext list manipulation with the observers, an ArrayList is enough to contain them. 

With increasing growth, the arraylist grows linearly. Although an ArrayList is not as performant as a Vector, for this scenario, a vector is overkill since the number of observers isn't likely to grow exponentially. Vectors double space alocation for every increase in the preallocated size, and are expensive in terms of space complexity. 

Considering this, for this scenario, for a largely linear growth, the ArrayList will be sufficient to handle inputs that grow overtime. 

**Since debug scheme is optional, although the debug value is set,  the MyLogger class is not implemented**

### Exception Handling :

All exceptions are handled either by the ExceptionHandler class or the handleException() method implemented in the Results class. 
For any exception,
 - A message is constructed either by using Java's default exception message or a custom message by the user is used.
 - This message is written into the errorLog file
 - This message is written to standard out (console)
 - The stackTrace of the exception is printed onto the console if program is running in DEBUG mode.
 - Program control is relinquished by exiting (System.exit(1))

### Program Outputs :

**If invalid input file**:
Program outputs to console, log : EXCEPTION : Unable to locate file : {file}
Program exits:(1)

**If no input in bstInput file**:
Program outputs result with zeroes.
