package studentRecordsBackupTree.driver;

import studentRecordsBackupTree.util.ExceptionHandler;
import studentRecordsBackupTree.util.ProjectManager;
import studentRecordsBackupTree.util.ProjectManagerInterface;

/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {
			String errorMessage = "Error: Incorrect number of arguments. Program accepts 5 argumnets.";

			ExceptionHandler.handleException(null, errorMessage);
		}

		String inputFileName = args[0];
		String outputFileName = args[1];
		String errorFileName = args[2];

		int DEBUG_VALUE = 0;
		int UPDATE_VALUE = 0;

		try {

			DEBUG_VALUE = Integer.valueOf(args[3]);
			UPDATE_VALUE = Integer.valueOf(args[4]);

		} catch (Exception exceptionIn) {
			ExceptionHandler.handleException(exceptionIn, "Enter valid Integers as args for DebugLevel and DebugValue");
		} finally {

		}

		try {

			ProjectManagerInterface projectManager = new ProjectManager();

			projectManager.runProject(
					inputFileName,
					outputFileName,
					errorFileName,
					DEBUG_VALUE,
					UPDATE_VALUE);

		} catch (Exception exceptionIn) {
			ExceptionHandler.handleException(exceptionIn, "");
		} finally {

		}

	}
}
