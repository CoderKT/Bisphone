package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.io.EofSensor;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

@Deprecated
public class LoggingSessionInputBuffer implements EofSensor, SessionInputBuffer {
    private final SessionInputBuffer f7684a;
    private final EofSensor f7685b;
    private final Wire f7686c;
    private final String f7687d;

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire, String str) {
        this.f7684a = sessionInputBuffer;
        this.f7685b = sessionInputBuffer instanceof EofSensor ? (EofSensor) sessionInputBuffer : null;
        this.f7686c = wire;
        if (str == null) {
            str = Consts.f7198b.name();
        }
        this.f7687d = str;
    }

    public boolean m12273a(int i) {
        return this.f7684a.m12268a(i);
    }

    public int m12272a(byte[] bArr, int i, int i2) {
        int a = this.f7684a.m12267a(bArr, i, i2);
        if (this.f7686c.m12325a() && a > 0) {
            this.f7686c.m12328b(bArr, i, a);
        }
        return a;
    }

    public int m12270a() {
        int a = this.f7684a.m12265a();
        if (this.f7686c.m12325a() && a != -1) {
            this.f7686c.m12326b(a);
        }
        return a;
    }

    public int m12271a(CharArrayBuffer charArrayBuffer) {
        int a = this.f7684a.m12266a(charArrayBuffer);
        if (this.f7686c.m12325a() && a >= 0) {
            this.f7686c.m12327b((new String(charArrayBuffer.m12756b(), charArrayBuffer.m12757c() - a, a) + "\r\n").getBytes(this.f7687d));
        }
        return a;
    }

    public HttpTransportMetrics m12274b() {
        return this.f7684a.m12269b();
    }

    public boolean m12275c() {
        if (this.f7685b != null) {
            return this.f7685b.m12264c();
        }
        return false;
    }
}
