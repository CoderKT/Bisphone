package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;

public class BufferedHeader implements FormattedHeader, Serializable, Cloneable {
    private final String f7898a;
    private final CharArrayBuffer f7899b;
    private final int f7900c;

    public BufferedHeader(CharArrayBuffer charArrayBuffer) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        int c = charArrayBuffer.m12758c(58);
        if (c == -1) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        String b = charArrayBuffer.m12754b(0, c);
        if (b.length() == 0) {
            throw new ParseException("Invalid header: " + charArrayBuffer.toString());
        }
        this.f7899b = charArrayBuffer;
        this.f7898a = b;
        this.f7900c = c + 1;
    }

    public String m12647c() {
        return this.f7898a;
    }

    public String m12648d() {
        return this.f7899b.m12754b(this.f7900c, this.f7899b.m12757c());
    }

    public HeaderElement[] m12649e() {
        ParserCursor parserCursor = new ParserCursor(0, this.f7899b.m12757c());
        parserCursor.m12662a(this.f7900c);
        return BasicHeaderValueParser.f7865b.m12592a(this.f7899b, parserCursor);
    }

    public int m12646b() {
        return this.f7900c;
    }

    public CharArrayBuffer m12645a() {
        return this.f7899b;
    }

    public String toString() {
        return this.f7899b.toString();
    }

    public Object clone() {
        return super.clone();
    }
}
