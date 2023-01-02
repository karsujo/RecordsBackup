package studentRecordsBackupTree.util;

import java.util.ArrayList;

public interface FileProcessorInterface {

    public ArrayList<String> readInputFile();

    public String getFileName();

    public boolean fileExists();

    public String readLine();

    public String toString();

}
