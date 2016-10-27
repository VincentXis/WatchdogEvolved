package com.gmail.rickard.bernal.networkTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLMock {
    private URL myURL = null;

    public URLMock(String sURL) throws MalformedURLException {
        myURL = new URL(sURL);
    }

    /**
     * This method has a 20% chance to throw an exception or execute
     * successfully the point of this is to force exception handling, without
     * that the code would break.
     *
     * @return myUrl.openStream() if successful
     * @throws IOException -
     * @see java.net.URL#openStream()
     */
    public final InputStream openStream() throws IOException {
        if (Math.random() < 0.2) {
            throw new IOException("Mock exception");
        }
        return myURL.openStream();
    }
}
