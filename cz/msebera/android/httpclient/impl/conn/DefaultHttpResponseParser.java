package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.impl.io.AbstractMessageParser;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.message.LineParser;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public class DefaultHttpResponseParser extends AbstractMessageParser<HttpResponse> {
    public HttpClientAndroidLog f7667a;
    private final HttpResponseFactory f7668c;
    private final CharArrayBuffer f7669d;

    protected /* synthetic */ HttpMessage m12248b(SessionInputBuffer sessionInputBuffer) {
        return m12246a(sessionInputBuffer);
    }

    @Deprecated
    public DefaultHttpResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.f7667a = new HttpClientAndroidLog(getClass());
        Args.m12722a((Object) httpResponseFactory, "Response factory");
        this.f7668c = httpResponseFactory;
        this.f7669d = new CharArrayBuffer(128);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected cz.msebera.android.httpclient.HttpResponse m12246a(cz.msebera.android.httpclient.io.SessionInputBuffer r8) {
        /*
        r7 = this;
        r1 = 0;
        r6 = -1;
        r0 = r1;
    L_0x0003:
        r2 = r7.f7669d;
        r2.m12747a();
        r2 = r7.f7669d;
        r2 = r8.m12266a(r2);
        if (r2 != r6) goto L_0x001a;
    L_0x0010:
        if (r0 != 0) goto L_0x001a;
    L_0x0012:
        r0 = new cz.msebera.android.httpclient.NoHttpResponseException;
        r1 = "The target server failed to respond";
        r0.<init>(r1);
        throw r0;
    L_0x001a:
        r3 = new cz.msebera.android.httpclient.message.ParserCursor;
        r4 = r7.f7669d;
        r4 = r4.m12757c();
        r3.<init>(r1, r4);
        r4 = r7.b;
        r5 = r7.f7669d;
        r4 = r4.m12615b(r5, r3);
        if (r4 == 0) goto L_0x003f;
    L_0x002f:
        r0 = r7.b;
        r1 = r7.f7669d;
        r0 = r0.m12616c(r1, r3);
        r1 = r7.f7668c;
        r2 = 0;
        r0 = r1.m11394a(r0, r2);
        return r0;
    L_0x003f:
        if (r2 == r6) goto L_0x0049;
    L_0x0041:
        r2 = r7.f7669d;
        r2 = r7.m12247a(r2, r0);
        if (r2 == 0) goto L_0x0051;
    L_0x0049:
        r0 = new cz.msebera.android.httpclient.ProtocolException;
        r1 = "The server failed to respond with a valid HTTP response";
        r0.<init>(r1);
        throw r0;
    L_0x0051:
        r2 = r7.f7667a;
        r2 = r2.m11836a();
        if (r2 == 0) goto L_0x0077;
    L_0x0059:
        r2 = r7.f7667a;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Garbage in response: ";
        r3 = r3.append(r4);
        r4 = r7.f7669d;
        r4 = r4.toString();
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.m11834a(r3);
    L_0x0077:
        r0 = r0 + 1;
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.conn.DefaultHttpResponseParser.a(cz.msebera.android.httpclient.io.SessionInputBuffer):cz.msebera.android.httpclient.HttpResponse");
    }

    protected boolean m12247a(CharArrayBuffer charArrayBuffer, int i) {
        return false;
    }
}
