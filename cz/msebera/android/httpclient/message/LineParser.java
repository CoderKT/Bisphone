package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public interface LineParser {
    Header m12614a(CharArrayBuffer charArrayBuffer);

    boolean m12615b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor);

    StatusLine m12616c(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor);
}
