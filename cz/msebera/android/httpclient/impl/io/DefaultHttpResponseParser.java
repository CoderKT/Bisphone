package cz.msebera.android.httpclient.impl.io;

import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseFactory;
import cz.msebera.android.httpclient.NoHttpResponseException;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.message.LineParser;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public class DefaultHttpResponseParser extends AbstractMessageParser<HttpResponse> {
    private final HttpResponseFactory f7843a;
    private final CharArrayBuffer f7844c;

    protected /* synthetic */ HttpMessage m12557b(SessionInputBuffer sessionInputBuffer) {
        return m12556a(sessionInputBuffer);
    }

    @Deprecated
    public DefaultHttpResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        this.f7843a = (HttpResponseFactory) Args.m12722a((Object) httpResponseFactory, "Response factory");
        this.f7844c = new CharArrayBuffer(128);
    }

    protected HttpResponse m12556a(SessionInputBuffer sessionInputBuffer) {
        this.f7844c.m12747a();
        if (sessionInputBuffer.m12266a(this.f7844c) == -1) {
            throw new NoHttpResponseException("The target server failed to respond");
        }
        return this.f7843a.m11394a(this.b.m12616c(this.f7844c, new ParserCursor(0, this.f7844c.m12757c())), null);
    }
}
