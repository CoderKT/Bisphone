package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

@Deprecated
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final SessionOutputBuffer f7688a;
    private final Wire f7689b;
    private final String f7690c;

    public LoggingSessionOutputBuffer(SessionOutputBuffer sessionOutputBuffer, Wire wire, String str) {
        this.f7688a = sessionOutputBuffer;
        this.f7689b = wire;
        if (str == null) {
            str = Consts.f7198b.name();
        }
        this.f7690c = str;
    }

    public void m12286a(byte[] bArr, int i, int i2) {
        this.f7688a.m12280a(bArr, i, i2);
        if (this.f7689b.m12325a()) {
            this.f7689b.m12324a(bArr, i, i2);
        }
    }

    public void m12283a(int i) {
        this.f7688a.m12277a(i);
        if (this.f7689b.m12325a()) {
            this.f7689b.m12322a(i);
        }
    }

    public void m12282a() {
        this.f7688a.m12276a();
    }

    public void m12284a(CharArrayBuffer charArrayBuffer) {
        this.f7688a.m12278a(charArrayBuffer);
        if (this.f7689b.m12325a()) {
            this.f7689b.m12323a((new String(charArrayBuffer.m12756b(), 0, charArrayBuffer.m12757c()) + "\r\n").getBytes(this.f7690c));
        }
    }

    public void m12285a(String str) {
        this.f7688a.m12279a(str);
        if (this.f7689b.m12325a()) {
            this.f7689b.m12323a((str + "\r\n").getBytes(this.f7690c));
        }
    }

    public HttpTransportMetrics m12287b() {
        return this.f7688a.m12281b();
    }
}
