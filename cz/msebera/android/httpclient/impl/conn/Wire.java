package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.util.Args;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Wire {
    public HttpClientAndroidLog f7697a;
    private final String f7698b;

    public Wire(HttpClientAndroidLog httpClientAndroidLog, String str) {
        this.f7697a = httpClientAndroidLog;
        this.f7698b = str;
    }

    public Wire(HttpClientAndroidLog httpClientAndroidLog) {
        this(httpClientAndroidLog, "");
    }

    private void m12321a(String str, InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            } else if (read == 13) {
                stringBuilder.append("[\\r]");
            } else if (read == 10) {
                stringBuilder.append("[\\n]\"");
                stringBuilder.insert(0, "\"");
                stringBuilder.insert(0, str);
                this.f7697a.m11834a(this.f7698b + " " + stringBuilder.toString());
                stringBuilder.setLength(0);
            } else if (read < 32 || read > 127) {
                stringBuilder.append("[0x");
                stringBuilder.append(Integer.toHexString(read));
                stringBuilder.append("]");
            } else {
                stringBuilder.append((char) read);
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append('\"');
            stringBuilder.insert(0, '\"');
            stringBuilder.insert(0, str);
            this.f7697a.m11834a(this.f7698b + " " + stringBuilder.toString());
        }
    }

    public boolean m12325a() {
        return this.f7697a.m11836a();
    }

    public void m12324a(byte[] bArr, int i, int i2) {
        Args.m12722a((Object) bArr, "Output");
        m12321a(">> ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void m12328b(byte[] bArr, int i, int i2) {
        Args.m12722a((Object) bArr, "Input");
        m12321a("<< ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void m12323a(byte[] bArr) {
        Args.m12722a((Object) bArr, "Output");
        m12321a(">> ", new ByteArrayInputStream(bArr));
    }

    public void m12327b(byte[] bArr) {
        Args.m12722a((Object) bArr, "Input");
        m12321a("<< ", new ByteArrayInputStream(bArr));
    }

    public void m12322a(int i) {
        m12323a(new byte[]{(byte) i});
    }

    public void m12326b(int i) {
        m12327b(new byte[]{(byte) i});
    }
}
