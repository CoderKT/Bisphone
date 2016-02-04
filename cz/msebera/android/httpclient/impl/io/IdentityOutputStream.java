package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.OutputStream;

public class IdentityOutputStream extends OutputStream {
    private final SessionOutputBuffer f7848a;
    private boolean f7849b;

    public IdentityOutputStream(SessionOutputBuffer sessionOutputBuffer) {
        this.f7849b = false;
        this.f7848a = (SessionOutputBuffer) Args.m12722a((Object) sessionOutputBuffer, "Session output buffer");
    }

    public void close() {
        if (!this.f7849b) {
            this.f7849b = true;
            this.f7848a.m12276a();
        }
    }

    public void flush() {
        this.f7848a.m12276a();
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f7849b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f7848a.m12280a(bArr, i, i2);
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(int i) {
        if (this.f7849b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f7848a.m12277a(i);
    }
}
