package cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

class HttpEntityDigester extends OutputStream {
    private final MessageDigest f7474a;
    private boolean f7475b;
    private byte[] f7476c;

    HttpEntityDigester(MessageDigest messageDigest) {
        this.f7474a = messageDigest;
        this.f7474a.reset();
    }

    public void write(int i) {
        if (this.f7475b) {
            throw new IOException("Stream has been already closed");
        }
        this.f7474a.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f7475b) {
            throw new IOException("Stream has been already closed");
        }
        this.f7474a.update(bArr, i, i2);
    }

    public void close() {
        if (!this.f7475b) {
            this.f7475b = true;
            this.f7476c = this.f7474a.digest();
            super.close();
        }
    }

    public byte[] m11915a() {
        return this.f7476c;
    }
}
