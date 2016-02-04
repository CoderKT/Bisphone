package com.nostra13.universalimageloader.core.assist;

import java.io.InputStream;

public class ContentLengthInputStream extends InputStream {
    private final InputStream f7008a;
    private final int f7009b;

    public ContentLengthInputStream(InputStream inputStream, int i) {
        this.f7008a = inputStream;
        this.f7009b = i;
    }

    public int available() {
        return this.f7009b;
    }

    public void close() {
        this.f7008a.close();
    }

    public void mark(int i) {
        this.f7008a.mark(i);
    }

    public int read() {
        return this.f7008a.read();
    }

    public int read(byte[] bArr) {
        return this.f7008a.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.f7008a.read(bArr, i, i2);
    }

    public void reset() {
        this.f7008a.reset();
    }

    public long skip(long j) {
        return this.f7008a.skip(j);
    }

    public boolean markSupported() {
        return this.f7008a.markSupported();
    }
}
