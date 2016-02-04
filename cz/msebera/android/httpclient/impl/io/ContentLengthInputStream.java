package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.ConnectionClosedException;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;

public class ContentLengthInputStream extends InputStream {
    private final long f7835a;
    private long f7836b;
    private boolean f7837c;
    private SessionInputBuffer f7838d;

    public ContentLengthInputStream(SessionInputBuffer sessionInputBuffer, long j) {
        this.f7836b = 0;
        this.f7837c = false;
        this.f7838d = null;
        this.f7838d = (SessionInputBuffer) Args.m12722a((Object) sessionInputBuffer, "Session input buffer");
        this.f7835a = Args.m12720a(j, "Content length");
    }

    public void close() {
        if (!this.f7837c) {
            try {
                if (this.f7836b < this.f7835a) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
                this.f7837c = true;
            } catch (Throwable th) {
                this.f7837c = true;
            }
        }
    }

    public int available() {
        if (this.f7838d instanceof BufferInfo) {
            return Math.min(((BufferInfo) this.f7838d).m12521e(), (int) (this.f7835a - this.f7836b));
        }
        return 0;
    }

    public int read() {
        if (this.f7837c) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f7836b >= this.f7835a) {
            return -1;
        } else {
            int a = this.f7838d.m12265a();
            if (a != -1) {
                this.f7836b++;
            } else if (this.f7836b < this.f7835a) {
                throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.f7835a + "; received: " + this.f7836b);
            }
            return a;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f7837c) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f7836b >= this.f7835a) {
            return -1;
        } else {
            if (this.f7836b + ((long) i2) > this.f7835a) {
                i2 = (int) (this.f7835a - this.f7836b);
            }
            int a = this.f7838d.m12267a(bArr, i, i2);
            if (a != -1 || this.f7836b >= this.f7835a) {
                if (a > 0) {
                    this.f7836b += (long) a;
                }
                return a;
            }
            throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.f7835a + "; received: " + this.f7836b);
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public long skip(long j) {
        if (j <= 0) {
            return 0;
        }
        byte[] bArr = new byte[2048];
        long min = Math.min(j, this.f7835a - this.f7836b);
        long j2 = 0;
        while (min > 0) {
            int read = read(bArr, 0, (int) Math.min(2048, min));
            if (read == -1) {
                break;
            }
            j2 += (long) read;
            min -= (long) read;
        }
        return j2;
    }
}
