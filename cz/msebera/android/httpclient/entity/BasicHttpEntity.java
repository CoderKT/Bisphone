package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.InputStream;
import java.io.OutputStream;

public class BasicHttpEntity extends AbstractHttpEntity {
    private InputStream f7396d;
    private long f7397e;

    public BasicHttpEntity() {
        this.f7397e = -1;
    }

    public long m11814b() {
        return this.f7397e;
    }

    public InputStream m11810a() {
        Asserts.m12729a(this.f7396d != null, "Content has not been provided");
        return this.f7396d;
    }

    public boolean m11815d() {
        return false;
    }

    public void m11811a(long j) {
        this.f7397e = j;
    }

    public void m11812a(InputStream inputStream) {
        this.f7396d = inputStream;
    }

    public void m11813a(OutputStream outputStream) {
        Args.m12722a((Object) outputStream, "Output stream");
        InputStream a = m11810a();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = a.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            a.close();
        }
    }

    public boolean m11816f() {
        return this.f7396d != null;
    }
}
