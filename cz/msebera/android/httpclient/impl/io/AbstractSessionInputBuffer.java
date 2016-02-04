package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.io.BufferInfo;
import cz.msebera.android.httpclient.io.HttpTransportMetrics;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.ByteArrayBuffer;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@Deprecated
public abstract class AbstractSessionInputBuffer implements BufferInfo, SessionInputBuffer {
    private InputStream f7797a;
    private byte[] f7798b;
    private ByteArrayBuffer f7799c;
    private Charset f7800d;
    private boolean f7801e;
    private int f7802f;
    private int f7803g;
    private HttpTransportMetricsImpl f7804h;
    private CodingErrorAction f7805i;
    private CodingErrorAction f7806j;
    private int f7807k;
    private int f7808l;
    private CharsetDecoder f7809m;
    private CharBuffer f7810n;

    protected void m12530a(InputStream inputStream, int i, HttpParams httpParams) {
        Args.m12722a((Object) inputStream, "Input stream");
        Args.m12726b(i, "Buffer size");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        this.f7797a = inputStream;
        this.f7798b = new byte[i];
        this.f7807k = 0;
        this.f7808l = 0;
        this.f7799c = new ByteArrayBuffer(i);
        String str = (String) httpParams.m12084a("http.protocol.element-charset");
        this.f7800d = str != null ? Charset.forName(str) : Consts.f7198b;
        this.f7801e = this.f7800d.equals(Consts.f7198b);
        this.f7809m = null;
        this.f7802f = httpParams.m12081a("http.connection.max-line-length", -1);
        this.f7803g = httpParams.m12081a("http.connection.min-chunk-limit", 512);
        this.f7804h = m12532d();
        CodingErrorAction codingErrorAction = (CodingErrorAction) httpParams.m12084a("http.malformed.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.f7805i = codingErrorAction;
        codingErrorAction = (CodingErrorAction) httpParams.m12084a("http.unmappable.input.action");
        if (codingErrorAction == null) {
            codingErrorAction = CodingErrorAction.REPORT;
        }
        this.f7806j = codingErrorAction;
    }

    protected HttpTransportMetricsImpl m12532d() {
        return new HttpTransportMetricsImpl();
    }

    public int m12533e() {
        return this.f7808l - this.f7807k;
    }

    protected int m12534f() {
        int i;
        if (this.f7807k > 0) {
            i = this.f7808l - this.f7807k;
            if (i > 0) {
                System.arraycopy(this.f7798b, this.f7807k, this.f7798b, 0, i);
            }
            this.f7807k = 0;
            this.f7808l = i;
        }
        int i2 = this.f7808l;
        i = this.f7797a.read(this.f7798b, i2, this.f7798b.length - i2);
        if (i == -1) {
            return -1;
        }
        this.f7808l = i2 + i;
        this.f7804h.m12560a((long) i);
        return i;
    }

    protected boolean m12535g() {
        return this.f7807k < this.f7808l;
    }

    public int m12527a() {
        while (!m12535g()) {
            if (m12534f() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.f7798b;
        int i = this.f7807k;
        this.f7807k = i + 1;
        return bArr[i] & 255;
    }

    public int m12529a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return 0;
        }
        int min;
        if (m12535g()) {
            min = Math.min(i2, this.f7808l - this.f7807k);
            System.arraycopy(this.f7798b, this.f7807k, bArr, i, min);
            this.f7807k += min;
            return min;
        } else if (i2 > this.f7803g) {
            min = this.f7797a.read(bArr, i, i2);
            if (min <= 0) {
                return min;
            }
            this.f7804h.m12560a((long) min);
            return min;
        } else {
            while (!m12535g()) {
                if (m12534f() == -1) {
                    return -1;
                }
            }
            min = Math.min(i2, this.f7808l - this.f7807k);
            System.arraycopy(this.f7798b, this.f7807k, bArr, i, min);
            this.f7807k += min;
            return min;
        }
    }

    private int m12526c() {
        for (int i = this.f7807k; i < this.f7808l; i++) {
            if (this.f7798b[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    public int m12528a(CharArrayBuffer charArrayBuffer) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            int c = m12526c();
            if (c == -1) {
                if (m12535g()) {
                    this.f7799c.m12734a(this.f7798b, this.f7807k, this.f7808l - this.f7807k);
                    this.f7807k = this.f7808l;
                }
                i = m12534f();
                if (i == -1) {
                    obj = null;
                }
            } else if (this.f7799c.m12741f()) {
                return m12522a(charArrayBuffer, c);
            } else {
                this.f7799c.m12734a(this.f7798b, this.f7807k, (c + 1) - this.f7807k);
                this.f7807k = c + 1;
                obj = null;
            }
            if (this.f7802f > 0 && this.f7799c.m12739d() >= this.f7802f) {
                throw new IOException("Maximum line length limit exceeded");
            }
        }
        if (i == -1 && this.f7799c.m12741f()) {
            return -1;
        }
        return m12525b(charArrayBuffer);
    }

    private int m12525b(CharArrayBuffer charArrayBuffer) {
        int d = this.f7799c.m12739d();
        if (d > 0) {
            if (this.f7799c.m12736b(d - 1) == 10) {
                d--;
            }
            if (d > 0 && this.f7799c.m12736b(d - 1) == 13) {
                d--;
            }
        }
        if (this.f7801e) {
            charArrayBuffer.m12749a(this.f7799c, 0, d);
        } else {
            d = m12523a(charArrayBuffer, ByteBuffer.wrap(this.f7799c.m12740e(), 0, d));
        }
        this.f7799c.m12731a();
        return d;
    }

    private int m12522a(CharArrayBuffer charArrayBuffer, int i) {
        int i2 = this.f7807k;
        this.f7807k = i + 1;
        if (i > i2 && this.f7798b[i - 1] == 13) {
            i--;
        }
        int i3 = i - i2;
        if (!this.f7801e) {
            return m12523a(charArrayBuffer, ByteBuffer.wrap(this.f7798b, i2, i3));
        }
        charArrayBuffer.m12752a(this.f7798b, i2, i3);
        return i3;
    }

    private int m12523a(CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) {
        int i = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.f7809m == null) {
            this.f7809m = this.f7800d.newDecoder();
            this.f7809m.onMalformedInput(this.f7805i);
            this.f7809m.onUnmappableCharacter(this.f7806j);
        }
        if (this.f7810n == null) {
            this.f7810n = CharBuffer.allocate(1024);
        }
        this.f7809m.reset();
        while (byteBuffer.hasRemaining()) {
            i += m12524a(this.f7809m.decode(byteBuffer, this.f7810n, true), charArrayBuffer, byteBuffer);
        }
        i += m12524a(this.f7809m.flush(this.f7810n), charArrayBuffer, byteBuffer);
        this.f7810n.clear();
        return i;
    }

    private int m12524a(CoderResult coderResult, CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.f7810n.flip();
        int remaining = this.f7810n.remaining();
        while (this.f7810n.hasRemaining()) {
            charArrayBuffer.m12748a(this.f7810n.get());
        }
        this.f7810n.compact();
        return remaining;
    }

    public HttpTransportMetrics m12531b() {
        return this.f7804h;
    }
}
