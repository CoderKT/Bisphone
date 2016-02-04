package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.InputStream;

public class IdentityInputStream extends InputStream {
    private final SessionInputBuffer f7846a;
    private boolean f7847b;

    public IdentityInputStream(SessionInputBuffer sessionInputBuffer) {
        this.f7847b = false;
        this.f7846a = (SessionInputBuffer) Args.m12722a((Object) sessionInputBuffer, "Session input buffer");
    }

    public int available() {
        if (this.f7846a instanceof BufferInfo) {
            return ((BufferInfo) this.f7846a).m12521e();
        }
        return 0;
    }

    public void close() {
        this.f7847b = true;
    }

    public int read() {
        if (this.f7847b) {
            return -1;
        }
        return this.f7846a.m12265a();
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f7847b) {
            return -1;
        }
        return this.f7846a.m12267a(bArr, i, i2);
    }
}
