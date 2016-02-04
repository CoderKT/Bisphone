package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.config.MessageConstraints;
import cz.msebera.android.httpclient.io.HttpMessageParser;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.message.BasicLineParser;
import cz.msebera.android.httpclient.message.LineParser;
import cz.msebera.android.httpclient.params.HttpParamConfig;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class AbstractMessageParser<T extends HttpMessage> implements HttpMessageParser<T> {
    private final SessionInputBuffer f7661a;
    protected final LineParser f7662b;
    private final MessageConstraints f7663c;
    private final List<CharArrayBuffer> f7664d;
    private int f7665e;
    private T f7666f;

    protected abstract T m12245b(SessionInputBuffer sessionInputBuffer);

    @Deprecated
    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpParams httpParams) {
        Args.m12722a((Object) sessionInputBuffer, "Session input buffer");
        Args.m12722a((Object) httpParams, "HTTP parameters");
        this.f7661a = sessionInputBuffer;
        this.f7663c = HttpParamConfig.m12678a(httpParams);
        if (lineParser == null) {
            lineParser = BasicLineParser.f7880b;
        }
        this.f7662b = lineParser;
        this.f7664d = new ArrayList();
        this.f7665e = 0;
    }

    public static Header[] m12242a(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser) {
        List arrayList = new ArrayList();
        if (lineParser == null) {
            lineParser = BasicLineParser.f7880b;
        }
        return m12243a(sessionInputBuffer, i, i2, lineParser, arrayList);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cz.msebera.android.httpclient.Header[] m12243a(cz.msebera.android.httpclient.io.SessionInputBuffer r9, int r10, int r11, cz.msebera.android.httpclient.message.LineParser r12, java.util.List<cz.msebera.android.httpclient.util.CharArrayBuffer> r13) {
        /*
        r8 = 9;
        r4 = 0;
        r7 = 32;
        r2 = 0;
        r0 = "Session input buffer";
        cz.msebera.android.httpclient.util.Args.m12722a(r9, r0);
        r0 = "Line parser";
        cz.msebera.android.httpclient.util.Args.m12722a(r12, r0);
        r0 = "Header line list";
        cz.msebera.android.httpclient.util.Args.m12722a(r13, r0);
        r3 = r4;
        r0 = r4;
    L_0x0017:
        if (r0 != 0) goto L_0x0049;
    L_0x0019:
        r0 = new cz.msebera.android.httpclient.util.CharArrayBuffer;
        r1 = 64;
        r0.<init>(r1);
    L_0x0020:
        r1 = r9.m12266a(r0);
        r5 = -1;
        if (r1 == r5) goto L_0x002e;
    L_0x0027:
        r1 = r0.m12757c();
        r5 = 1;
        if (r1 >= r5) goto L_0x004d;
    L_0x002e:
        r0 = r13.size();
        r1 = new cz.msebera.android.httpclient.Header[r0];
    L_0x0034:
        r0 = r13.size();
        if (r2 >= r0) goto L_0x00b6;
    L_0x003a:
        r0 = r13.get(r2);
        r0 = (cz.msebera.android.httpclient.util.CharArrayBuffer) r0;
        r0 = r12.m12614a(r0);	 Catch:{ ParseException -> 0x00ab }
        r1[r2] = r0;	 Catch:{ ParseException -> 0x00ab }
        r2 = r2 + 1;
        goto L_0x0034;
    L_0x0049:
        r0.m12747a();
        goto L_0x0020;
    L_0x004d:
        r1 = r0.m12744a(r2);
        if (r1 == r7) goto L_0x0059;
    L_0x0053:
        r1 = r0.m12744a(r2);
        if (r1 != r8) goto L_0x00a2;
    L_0x0059:
        if (r3 == 0) goto L_0x00a2;
    L_0x005b:
        r1 = r2;
    L_0x005c:
        r5 = r0.m12757c();
        if (r1 >= r5) goto L_0x006a;
    L_0x0062:
        r5 = r0.m12744a(r1);
        if (r5 == r7) goto L_0x0082;
    L_0x0068:
        if (r5 == r8) goto L_0x0082;
    L_0x006a:
        if (r11 <= 0) goto L_0x0085;
    L_0x006c:
        r5 = r3.m12757c();
        r5 = r5 + 1;
        r6 = r0.m12757c();
        r5 = r5 + r6;
        r5 = r5 - r1;
        if (r5 <= r11) goto L_0x0085;
    L_0x007a:
        r0 = new cz.msebera.android.httpclient.MessageConstraintException;
        r1 = "Maximum line length limit exceeded";
        r0.<init>(r1);
        throw r0;
    L_0x0082:
        r1 = r1 + 1;
        goto L_0x005c;
    L_0x0085:
        r3.m12748a(r7);
        r5 = r0.m12757c();
        r5 = r5 - r1;
        r3.m12750a(r0, r1, r5);
        r1 = r0;
        r0 = r3;
    L_0x0092:
        if (r10 <= 0) goto L_0x00a7;
    L_0x0094:
        r3 = r13.size();
        if (r3 < r10) goto L_0x00a7;
    L_0x009a:
        r0 = new cz.msebera.android.httpclient.MessageConstraintException;
        r1 = "Maximum header count exceeded";
        r0.<init>(r1);
        throw r0;
    L_0x00a2:
        r13.add(r0);
        r1 = r4;
        goto L_0x0092;
    L_0x00a7:
        r3 = r0;
        r0 = r1;
        goto L_0x0017;
    L_0x00ab:
        r0 = move-exception;
        r1 = new cz.msebera.android.httpclient.ProtocolException;
        r0 = r0.getMessage();
        r1.<init>(r0);
        throw r1;
    L_0x00b6:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.io.AbstractMessageParser.a(cz.msebera.android.httpclient.io.SessionInputBuffer, int, int, cz.msebera.android.httpclient.message.LineParser, java.util.List):cz.msebera.android.httpclient.Header[]");
    }

    public T m12244a() {
        switch (this.f7665e) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                try {
                    this.f7666f = m12245b(this.f7661a);
                    this.f7665e = 1;
                    break;
                } catch (Throwable e) {
                    throw new ProtocolException(e.getMessage(), e);
                }
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                break;
            default:
                throw new IllegalStateException("Inconsistent parser state");
        }
        this.f7666f.m10611a(m12243a(this.f7661a, this.f7663c.m11614b(), this.f7663c.m11613a(), this.f7662b, this.f7664d));
        T t = this.f7666f;
        this.f7666f = null;
        this.f7664d.clear();
        this.f7665e = 0;
        return t;
    }
}
