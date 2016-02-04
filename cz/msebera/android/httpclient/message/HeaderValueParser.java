package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public interface HeaderValueParser {
    HeaderElement[] m12585a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor);

    HeaderElement m12586b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor);
}
