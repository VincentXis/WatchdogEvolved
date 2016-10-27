package com.gmail.rickard.bernal.fileManagement;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import com.gmail.rickard.bernal.downloader.DownloaderWE;

public class FileHandler {
    private DownloaderWE downloaderWE = new DownloaderWE(0);

    private File original = null;
    private File updateState = null;

    public FileHandler(String url){
        createSavedStateFile();
        updateState = getUpdateState(url);
        copyContentFromUpdateFileToSavedFile(updateState,original);

    }


    private void createSavedStateFile(){
        String savedStateFilePath = "files\\savedState.html";
        original = new File(savedStateFilePath);
        if(!original.exists()){
            System.out.println("savedState.html did not exist,\na new file was created");
        }else{
            System.out.println("New data will be compared to\nexisting file: savedState.html");
        }
    }

    private File getUpdateState(String url){
        String updateStateFilePath = "files\\updateState.html";
        updateState = downloaderWE.stableDownload(url, updateStateFilePath);
        return updateState;
    }

    /**
     * The method compares the updateState to the savedState file, if content is different
     * between the two: The old file is overwritten with the information from
     * the old.
     *
     * @param file1
     *            - updateState
     * @param file2
     *            - savedState
     */
    private void copyContentFromUpdateFileToSavedFile(File file1, File file2) {
        try {
            if (!fileContentComparator(file1, file2)) {
                System.out.println("The state of target url was different,\nnew data was saved");
                Files.copy(file1.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println("Content is identical, file was not copied.");
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * This method compares the contents of two files and returns a boolean
     * value the return boolean value returns true if the contents is equal and
     * false if the contents is different. the content of each file is read into
     * a byte array and the data is compared between these two arrays.
     *
     * @param file1 -
     * @param file2 -
     * @return true if equal, false if not.
     */
    private boolean fileContentComparator(File file1, File file2) {
        boolean retval = false;
        // files are read into two byteArrays here
        try {
            byte[] f1 = Files.readAllBytes(file1.toPath());
            byte[] f2 = Files.readAllBytes(file2.toPath());

            if (Arrays.equals(f1, f2)) {
                retval = true;
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        } catch (Exception e) {
            // Fail!
        }
        return retval;
    }
}
