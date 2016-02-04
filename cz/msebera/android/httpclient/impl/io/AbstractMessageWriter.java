package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.io.HttpMessageWriter;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.message.BasicLineFormatter;
import cz.msebera.android.httpclient.message.LineFormatter;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public abstract class AbstractMessageWriter<T extends HttpMessage> implements HttpMessageWriter<T> {
    protected final SessionOutputBuffer f7794a;
    protected final CharArrayBuffer f7795b;
    protected final LineFormatter f7796c;

    protected abstract void m12519a(T t);

    @Deprecated
    public AbstractMessageWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter, HttpParams httpParams) {
        Args.m12722a((Object) sessionOutputBuffer, "Session input buffer");
        this.f7794a = sessionOutputBuffer;
        this.f7795b = new CharArrayBuffer(128);
        if (lineFormatter == null) {
            lineFormatter = BasicLineFormatter.f7878b;
        }
        this.f7796c = lineFormatter;
    }

    public void m12520b(T t) {
        Args.m12722a((Object) t, "HTTP message");
        m12519a(t);
        HeaderIterator f = t.m10621f();
        while (f.hasNext()) {
            this.f7794a.m12278a(this.f7796c.m12603a(this.f7795b, f.m11373a()));
        }
        this.f7795b.m12747a();
        this.f7794a.m12278a(this.f7795b);
    }
}
