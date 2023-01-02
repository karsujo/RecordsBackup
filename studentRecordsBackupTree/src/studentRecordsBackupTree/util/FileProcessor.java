package studentRecordsBackupTree.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileProcessor implements FileProcessorInterface {

    private BufferedReader _reader;

    public String fileName;

    public File file;

    public FileProcessor(String fileNameIn) {
        fileName = fileNameIn;
        InitializeFile();
    }

    private void InitializeFile() {
        try {
            String fullFileName = System.getProperty("user.dir") + "/" + fileName;
            file = new File(fullFileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            _reader = bufferedReader;

        } catch (FileNotFoundException exceptionIn) {
            ExceptionHandler.handleException(exceptionIn, "Unable to locate file : " + fileName);
        } finally {
        }
    }

    public ArrayList<String> readInputFile() {
        ArrayList<String> result = new ArrayList<String>();
        String str;
        try {
            str = _reader.readLine();
            while (str != null) {
                result.add(str);
                str = _reader.readLine();
            }
        } catch (IOException exceptionIn) {

            ExceptionHandler.handleException(exceptionIn, "Unable to locate/read from file : " + fileName);

        } finally {
        }

        return result;

    }

    public boolean fileExists() {
        return file.exists();
    }

    public String getFileName() {
        return fileName;
    }

    public String readLine() {
        String result = null;

        try {
            result = _reader.readLine();
        } catch (IOException exceptionIn) {

            ExceptionHandler.handleException(exceptionIn, "Unable to locate/read from file : " + fileName);

        } finally {
        }
        return result;
    }

    @Override
    public String toString() {
        return "{" +
        // " _reader='" + get_reader() + "'" +
        // ", _writer='" + get_writer() + "'" +
                "}";
    }

}
