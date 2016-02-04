package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@Deprecated
public abstract class AbstractSessionOutputBuffer implements BufferInfo, SessionOutputBuffer {
    private static final byte[] f7811a;
    private OutputStream f7812b;
    private ByteArrayBuffer f7813c;
    private Charset f7814d;
    private boolean f7815e;
    private int f7816f;
    private HttpTransportMetricsImpl f7817g;
    private CodingErrorAction f7818h;
    private CodingErrorAction f7819i;
    private CharsetEncoder f7820j;
    private ByteBuffer f7821k;

    static {
        f7811a = new byte[]{(byte) 13, (byte) 10};
    }

    protected void m12541a(OutputStream outputStream, int i, HttpParams httpParams) {
        Args.m12722a((Object) outputStream, "Input stream");
        Args.m12726b(i, "Buffer size");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        this.f7812b = outputStream;
        this.f7813c = new ByteArrayBuffer(i);
        String str = (String) httpParams.m12084a("http.protocol.element-charset");
        this.f7814d = str != null ? Charset.forName(str) : Consts.f7198b;
        this.f7815e = this.f7814d.equals(Consts.f7198b);
        this.f7820j = null;
        this.f7816f = httpParams.m12081a("http.connection.min-chunk-limit", 512);
        this.f7817g = m12546c();
        CodingErrorAction codingErrorAction = (CodingErrorAction) httpParams.m12084a("http.malformed.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.f7818h = codingErrorAction;
        codingErrorAction = (CodingErrorAction) httpParams.m12084a("http.unmappable.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.f7819i = codingErrorAction;
    }

    protected HttpTransportMetricsImpl m12546c() {
        return new HttpTransportMetricsImpl();
    }

    public int m12548e() {
        return this.f7813c.m12739d();
    }

    protected void m12547d() {
        int d = this.f7813c.m12739d();
        if (d > 0) {
            this.f7812b.write(this.f7813c.m12740e(), 0, d);
            this.f7813c.m12731a();
            this.f7817g.m12560a((long) d);
        }
    }

    public void m12538a() {
        m12547d();
        this.f7812b.flush();
    }

    public void m12544a(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i2 > this.f7816f || i2 > this.f7813c.m12738c()) {
                m12547d();
                this.f7812b.write(bArr, i, i2);
                this.f7817g.m12560a((long) i2);
                return;
            }
            if (i2 > this.f7813c.m12738c() - this.f7813c.m12739d()) {
                m12547d();
            }
            this.f7813c.m12734a(bArr, i, i2);
        }
    }

    public void m12543a(byte[] bArr) {
        if (bArr != null) {
            m12544a(bArr, 0, bArr.length);
        }
    }

    public void m12539a(int i) {
        if (this.f7813c.m12742g()) {
            m12547d();
        }
        this.f7813c.m12732a(i);
    }

    public void m12542a(String str) {
        if (str != null) {
            if (str.length() > 0) {
                if (this.f7815e) {
                    for (int i = 0; i < str.length(); i++) {
                        m12539a(str.charAt(i));
                    }
                } else {
                    m12536a(CharBuffer.wrap(str));
                }
            }
            m12543a(f7811a);
        }
    }

    public void m12540a(CharArrayBuffer charArrayBuffer) {
        int i = 0;
        if (charArrayBuffer != null) {
            if (this.f7815e) {
                int c = charArrayBuffer.m12757c();
                while (c > 0) {
                    int min = Math.min(this.f7813c.m12738c() - this.f7813c.m12739d(), c);
                    if (min > 0) {
                        this.f7813c.m12733a(charArrayBuffer, i, min);
                    }
                    if (this.f7813c.m12742g()) {
                        m12547d();
                    }
                    i += min;
                    c -= min;
                }
            } else {
                m12536a(CharBuffer.wrap(charArrayBuffer.m12756b(), 0, charArrayBuffer.m12757c()));
            }
            m12543a(f7811a);
        }
    }

    private void m12536a(CharBuffer charBuffer) {
        if (charBuffer.hasRemaining()) {
            if (this.f7820j == null) {
                this.f7820j = this.f7814d.newEncoder();
                this.f7820j.onMalformedInput(this.f7818h);
                this.f7820j.onUnmappableCharacter(this.f7819i);
            }
            if (this.f7821k == null) {
                this.f7821k = ByteBuffer.allocate(1024);
            }
            this.f7820j.reset();
            while (charBuffer.hasRemaining()) {
                m12537a(this.f7820j.encode(charBuffer, this.f7821k, true));
            }
            m12537a(this.f7820j.flush(this.f7821k));
            this.f7821k.clear();
        }
    }

    private void m12537a(CoderResult coderResult) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.f7821k.flip();
        while (this.f7821k.hasRemaining()) {
            m12539a(this.f7821k.get());
        }
        this.f7821k.compact();
    }

    public HttpTransportMetrics m12545b() {
        return this.f7817g;
    }
}
