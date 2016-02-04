package org.jivesoftware.smack.compression;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class XMPPInputOutputStream {
    protected static FlushMethod flushMethod;
    protected final String compressionMethod;

    public enum FlushMethod {
        FULL_FLUSH,
        SYNC_FLUSH
    }

    public abstract InputStream getInputStream(InputStream inputStream);

    public abstract OutputStream getOutputStream(OutputStream outputStream);

    public abstract boolean isSupported();

    public static void setFlushMethod(FlushMethod flushMethod) {
        flushMethod = flushMethod;
    }

    protected XMPPInputOutputStream(String str) {
        this.compressionMethod = str;
    }

    public String getCompressionMethod() {
        return this.compressionMethod;
    }
}
