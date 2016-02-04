package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.OutputStream;

public class ContentLengthOutputStream extends OutputStream {
    private final SessionOutputBuffer f7839a;
    private final long f7840b;
    private long f7841c;
    private boolean f7842d;

    public ContentLengthOutputStream(SessionOutputBuffer sessionOutputBuffer, long j) {
        this.f7841c = 0;
        this.f7842d = false;
        this.f7839a = (SessionOutputBuffer) Args.m12722a((Object) sessionOutputBuffer, "Session output buffer");
        this.f7840b = Args.m12720a(j, "Content length");
    }

    public void close() {
        if (!this.f7842d) {
            this.f7842d = true;
            this.f7839a.m12276a();
        }
    }

    public void flush() {
        this.f7839a.m12276a();
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f7842d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.f7841c < this.f7840b) {
            long j = this.f7840b - this.f7841c;
            if (((long) i2) > j) {
                i2 = (int) j;
            }
            this.f7839a.m12280a(bArr, i, i2);
            this.f7841c += (long) i2;
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(int i) {
        if (this.f7842d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.f7841c < this.f7840b) {
            this.f7839a.m12277a(i);
            this.f7841c++;
        }
    }
}
