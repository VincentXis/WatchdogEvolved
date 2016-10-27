package com.gmail.rickard.bernal.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.gmail.rickard.bernal.networkTest.URLMock;

public class DownloaderWE {
    private int choice = 0;
    private File tmpFile = null;

    public DownloaderWE(int choice) {
        this.choice = choice;
    }

    /**
     * This method either (downloadActual or downloadMock) depending on which
     * integer the choice contains. the choice integers' value is set by the
     * constructor: 0 for: downloadActual, 1 for: downloadMock anything else is
     * an invalid choice.
     * <p>
     * To ensure download stability the method uses a loop that runs 5 times,
     * sleeps for 0.2 seconds and fetches the data each time.
     * <p>
     * This is somehow useful except i don't see why at the moment.
     *
     * @param url         - target URL to download data from is sent the chosen method.
     * @param pathWriteTo -
     * @return - file with new data
     */
    public File stableDownload(String url, String pathWriteTo) {
        File stableTmpFileFile = null;
        for (int i = 0; i < 5; i++) {

            try {
                if (i > 0) {
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            switch (choice) {
                case 0:
                    System.out.println("Stability run number: " + (i + 1));
                    stableTmpFileFile = downloadActual(url, pathWriteTo);
                    break;
                case 1:
                    System.out.println("Stability run number: " + (i + 1));
                    stableTmpFileFile = downloadMock(url, pathWriteTo);
                    break;
                default:
                    System.out.println("Not a valid choise, 0 for downloadActual or 0 for downloadMock");
                    break;
            }
        }
        return stableTmpFileFile;
    }

    /**
     * This method downloads the content of the target page and saves it to a
     * temporary file object which is and returns it.
     *
     * @param url  - target URL to with data to be saved.
     * @param path -
     * @return tempFile - object that contains the data from target URL.
     */
    public File downloadActual(String url, String path) {
        tmpFile = new File(path);
        URL website = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try {
            website = new URL(url);
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(tmpFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return tmpFile;
    }

    /**
     * Method uses a mock URL, URLMock throws and exception when Math.random is
     * less than 0.2
     *
     * @param url -
     * @param path -
     * @return tempFile - object that contains the data from target URL.
     */
    public File downloadMock(String url, String path) {
        tmpFile = new File(path);
        URLMock website = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try {
            website = new URLMock(url);
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(tmpFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            System.out.println("Exception was thrown by openStream();\n" + e);
        }
        return tmpFile;
    }
}
