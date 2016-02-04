package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import java.io.IOException;
import java.io.OutputStream;

public class ChunkedOutputStream extends OutputStream {
    private final SessionOutputBuffer f7830a;
    private final byte[] f7831b;
    private int f7832c;
    private boolean f7833d;
    private boolean f7834e;

    @Deprecated
    public ChunkedOutputStream(SessionOutputBuffer sessionOutputBuffer) {
        this(2048, sessionOutputBuffer);
    }

    public ChunkedOutputStream(int i, SessionOutputBuffer sessionOutputBuffer) {
        this.f7832c = 0;
        this.f7833d = false;
        this.f7834e = false;
        this.f7831b = new byte[i];
        this.f7830a = sessionOutputBuffer;
    }

    protected void m12552a() {
        if (this.f7832c > 0) {
            this.f7830a.m12279a(Integer.toHexString(this.f7832c));
            this.f7830a.m12280a(this.f7831b, 0, this.f7832c);
            this.f7830a.m12279a("");
            this.f7832c = 0;
        }
    }

    protected void m12553a(byte[] bArr, int i, int i2) {
        this.f7830a.m12279a(Integer.toHexString(this.f7832c + i2));
        this.f7830a.m12280a(this.f7831b, 0, this.f7832c);
        this.f7830a.m12280a(bArr, i, i2);
        this.f7830a.m12279a("");
        this.f7832c = 0;
    }

    protected void m12554b() {
        this.f7830a.m12279a("0");
        this.f7830a.m12279a("");
    }

    public void m12555c() {
        if (!this.f7833d) {
            m12552a();
            m12554b();
            this.f7833d = true;
        }
    }

    public void write(int i) {
        if (this.f7834e) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f7831b[this.f7832c] = (byte) i;
        this.f7832c++;
        if (this.f7832c == this.f7831b.length) {
            m12552a();
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f7834e) {
            throw new IOException("Attempted write to closed stream.");
        } else if (i2 >= this.f7831b.length - this.f7832c) {
            m12553a(bArr, i, i2);
        } else {
            System.arraycopy(bArr, i, this.f7831b, this.f7832c, i2);
            this.f7832c += i2;
        }
    }

    public void flush() {
        m12552a();
        this.f7830a.m12276a();
    }

    public void close() {
        if (!this.f7834e) {
            this.f7834e = true;
            m12555c();
            this.f7830a.m12276a();
        }
    }
}
