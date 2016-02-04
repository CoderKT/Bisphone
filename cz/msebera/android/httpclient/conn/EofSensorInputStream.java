package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;
import java.io.InputStream;

public class EofSensorInputStream extends InputStream implements ConnectionReleaseTrigger {
    protected InputStream f7339a;
    private boolean f7340b;
    private final EofSensorWatcher f7341c;

    public EofSensorInputStream(InputStream inputStream, EofSensorWatcher eofSensorWatcher) {
        Args.m12722a((Object) inputStream, "Wrapped stream");
        this.f7339a = inputStream;
        this.f7340b = false;
        this.f7341c = eofSensorWatcher;
    }

    protected boolean m11645a() {
        if (!this.f7340b) {
            return this.f7339a != null;
        } else {
            throw new IOException("Attempted read on closed stream.");
        }
    }

    public int read() {
        if (!m11645a()) {
            return -1;
        }
        try {
            int read = this.f7339a.read();
            m11644a(read);
            return read;
        } catch (IOException e) {
            m11647c();
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!m11645a()) {
            return -1;
        }
        try {
            int read = this.f7339a.read(bArr, i, i2);
            m11644a(read);
            return read;
        } catch (IOException e) {
            m11647c();
            throw e;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int available() {
        int i = 0;
        if (m11645a()) {
            try {
                i = this.f7339a.available();
            } catch (IOException e) {
                m11647c();
                throw e;
            }
        }
        return i;
    }

    public void close() {
        this.f7340b = true;
        m11646b();
    }

    protected void m11644a(int i) {
        if (this.f7339a != null && i < 0) {
            boolean z = true;
            try {
                if (this.f7341c != null) {
                    z = this.f7341c.m11618a(this.f7339a);
                }
                if (z) {
                    this.f7339a.close();
                }
                this.f7339a = null;
            } catch (Throwable th) {
                this.f7339a = null;
            }
        }
    }

    protected void m11646b() {
        if (this.f7339a != null) {
            boolean z = true;
            try {
                if (this.f7341c != null) {
                    z = this.f7341c.m11619b(this.f7339a);
                }
                if (z) {
                    this.f7339a.close();
                }
                this.f7339a = null;
            } catch (Throwable th) {
                this.f7339a = null;
            }
        }
    }

    protected void m11647c() {
        if (this.f7339a != null) {
            boolean z = true;
            try {
                if (this.f7341c != null) {
                    z = this.f7341c.m11620c(this.f7339a);
                }
                if (z) {
                    this.f7339a.close();
                }
                this.f7339a = null;
            } catch (Throwable th) {
                this.f7339a = null;
            }
        }
    }

    public void m11648i() {
        close();
    }

    public void m11649j() {
        this.f7340b = true;
        m11647c();
    }
}
