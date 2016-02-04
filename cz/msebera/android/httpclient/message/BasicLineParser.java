package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public class BasicLineParser implements LineParser {
    @Deprecated
    public static final BasicLineParser f7879a;
    public static final BasicLineParser f7880b;
    protected final ProtocolVersion f7881c;

    static {
        f7879a = new BasicLineParser();
        f7880b = new BasicLineParser();
    }

    public BasicLineParser(ProtocolVersion protocolVersion) {
        if (protocolVersion == null) {
            protocolVersion = HttpVersion.f7210c;
        }
        this.f7881c = protocolVersion;
    }

    public BasicLineParser() {
        this(null);
    }

    public ProtocolVersion m12619a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Object obj = 1;
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        String a = this.f7881c.m11396a();
        int length = a.length();
        int b = parserCursor.m12663b();
        int a2 = parserCursor.m12661a();
        m12623d(charArrayBuffer, parserCursor);
        int b2 = parserCursor.m12663b();
        if ((b2 + length) + 4 > a2) {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.m12746a(b, a2));
        }
        int i = 0;
        Object obj2 = 1;
        while (obj2 != null && i < length) {
            if (charArrayBuffer.m12744a(b2 + i) == a.charAt(i)) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            i++;
        }
        if (obj2 == null) {
            obj = obj2;
        } else if (charArrayBuffer.m12744a(b2 + length) != '/') {
            obj = null;
        }
        if (obj == null) {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.m12746a(b, a2));
        }
        int i2 = (length + 1) + b2;
        int a3 = charArrayBuffer.m12745a(46, i2, a2);
        if (a3 == -1) {
            throw new ParseException("Invalid protocol version number: " + charArrayBuffer.m12746a(b, a2));
        }
        try {
            int parseInt = Integer.parseInt(charArrayBuffer.m12754b(i2, a3));
            a3++;
            i2 = charArrayBuffer.m12745a(32, a3, a2);
            if (i2 == -1) {
                i2 = a2;
            }
            try {
                a3 = Integer.parseInt(charArrayBuffer.m12754b(a3, i2));
                parserCursor.m12662a(i2);
                return m12618a(parseInt, a3);
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid protocol minor version number: " + charArrayBuffer.m12746a(b, a2));
            }
        } catch (NumberFormatException e2) {
            throw new ParseException("Invalid protocol major version number: " + charArrayBuffer.m12746a(b, a2));
        }
    }

    protected ProtocolVersion m12618a(int i, int i2) {
        return this.f7881c.m11395a(i, i2);
    }

    public boolean m12621b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        boolean z = true;
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        int b = parserCursor.m12663b();
        String a = this.f7881c.m11396a();
        int length = a.length();
        if (charArrayBuffer.m12757c() < length + 4) {
            return false;
        }
        if (b < 0) {
            b = (charArrayBuffer.m12757c() - 4) - length;
        } else if (b == 0) {
            while (b < charArrayBuffer.m12757c() && HTTP.m12703a(charArrayBuffer.m12744a(b))) {
                b++;
            }
        }
        if ((b + length) + 4 > charArrayBuffer.m12757c()) {
            return false;
        }
        int i = 0;
        boolean z2 = true;
        while (z2 && i < length) {
            z2 = charArrayBuffer.m12744a(b + i) == a.charAt(i);
            i++;
        }
        if (!z2) {
            z = z2;
        } else if (charArrayBuffer.m12744a(b + length) != '/') {
            z = false;
        }
        return z;
    }

    public StatusLine m12622c(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        int b = parserCursor.m12663b();
        int a = parserCursor.m12661a();
        try {
            int i;
            String b2;
            ProtocolVersion a2 = m12619a(charArrayBuffer, parserCursor);
            m12623d(charArrayBuffer, parserCursor);
            int b3 = parserCursor.m12663b();
            int a3 = charArrayBuffer.m12745a(32, b3, a);
            if (a3 < 0) {
                i = a;
            } else {
                i = a3;
            }
            String b4 = charArrayBuffer.m12754b(b3, i);
            a3 = 0;
            while (a3 < b4.length()) {
                if (Character.isDigit(b4.charAt(a3))) {
                    a3++;
                } else {
                    throw new ParseException("Status line contains invalid status code: " + charArrayBuffer.m12746a(b, a));
                }
            }
            b3 = Integer.parseInt(b4);
            if (i < a) {
                b2 = charArrayBuffer.m12754b(i, a);
            } else {
                b2 = "";
            }
            return m12620a(a2, b3, b2);
        } catch (NumberFormatException e) {
            throw new ParseException("Status line contains invalid status code: " + charArrayBuffer.m12746a(b, a));
        } catch (IndexOutOfBoundsException e2) {
            throw new ParseException("Invalid status line: " + charArrayBuffer.m12746a(b, a));
        }
    }

    protected StatusLine m12620a(ProtocolVersion protocolVersion, int i, String str) {
        return new BasicStatusLine(protocolVersion, i, str);
    }

    public Header m12617a(CharArrayBuffer charArrayBuffer) {
        return new BufferedHeader(charArrayBuffer);
    }

    protected void m12623d(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        int b = parserCursor.m12663b();
        int a = parserCursor.m12661a();
        while (b < a && HTTP.m12703a(charArrayBuffer.m12744a(b))) {
            b++;
        }
        parserCursor.m12662a(b);
    }
}
