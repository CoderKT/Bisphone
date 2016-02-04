package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public interface LineFormatter {
    CharArrayBuffer m12603a(CharArrayBuffer charArrayBuffer, Header header);

    CharArrayBuffer m12604a(CharArrayBuffer charArrayBuffer, RequestLine requestLine);
}
