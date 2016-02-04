package org.jivesoftware.smackx.bytestreams;

import java.io.InputStream;
import java.io.OutputStream;

public interface BytestreamSession {
    void close();

    InputStream getInputStream();

    OutputStream getOutputStream();

    int getReadTimeout();

    void setReadTimeout(int i);
}
