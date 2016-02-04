package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.List;

public class BasicHeaderValueParser implements HeaderValueParser {
    @Deprecated
    public static final BasicHeaderValueParser f7864a;
    public static final BasicHeaderValueParser f7865b;
    private static final char[] f7866c;

    static {
        f7864a = new BasicHeaderValueParser();
        f7865b = new BasicHeaderValueParser();
        f7866c = new char[]{';', ','};
    }

    public static HeaderElement[] m12588a(String str, HeaderValueParser headerValueParser) {
        Args.m12722a((Object) str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.m12751a(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (headerValueParser == null) {
            headerValueParser = f7865b;
        }
        return headerValueParser.m12585a(charArrayBuffer, parserCursor);
    }

    public HeaderElement[] m12592a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        List arrayList = new ArrayList();
        while (!parserCursor.m12664c()) {
            HeaderElement b = m12593b(charArrayBuffer, parserCursor);
            if (b.m11368a().length() != 0 || b.m11369b() != null) {
                arrayList.add(b);
            }
        }
        return (HeaderElement[]) arrayList.toArray(new HeaderElement[arrayList.size()]);
    }

    public HeaderElement m12593b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        NameValuePair d = m12595d(charArrayBuffer, parserCursor);
        NameValuePair[] nameValuePairArr = null;
        if (!(parserCursor.m12664c() || charArrayBuffer.m12744a(parserCursor.m12663b() - 1) == ',')) {
            nameValuePairArr = m12594c(charArrayBuffer, parserCursor);
        }
        return m12589a(d.m11403a(), d.m11404b(), nameValuePairArr);
    }

    protected HeaderElement m12589a(String str, String str2, NameValuePair[] nameValuePairArr) {
        return new BasicHeaderElement(str, str2, nameValuePairArr);
    }

    public NameValuePair[] m12594c(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        int b = parserCursor.m12663b();
        int a = parserCursor.m12661a();
        while (b < a && HTTP.m12703a(charArrayBuffer.m12744a(b))) {
            b++;
        }
        parserCursor.m12662a(b);
        if (parserCursor.m12664c()) {
            return new NameValuePair[0];
        }
        List arrayList = new ArrayList();
        while (!parserCursor.m12664c()) {
            arrayList.add(m12595d(charArrayBuffer, parserCursor));
            if (charArrayBuffer.m12744a(parserCursor.m12663b() - 1) == ',') {
                break;
            }
        }
        return (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]);
    }

    public NameValuePair m12595d(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        return m12590a(charArrayBuffer, parserCursor, f7866c);
    }

    private static boolean m12587a(char c, char[] cArr) {
        if (cArr == null) {
            return false;
        }
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public NameValuePair m12590a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor, char[] cArr) {
        Object obj;
        String b;
        Object obj2 = 1;
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        int b2 = parserCursor.m12663b();
        int b3 = parserCursor.m12663b();
        int a = parserCursor.m12661a();
        while (b2 < a) {
            char a2 = charArrayBuffer.m12744a(b2);
            if (a2 == '=') {
                obj = null;
                break;
            } else if (m12587a(a2, cArr)) {
                int i = 1;
                break;
            } else {
                b2++;
            }
        }
        obj = null;
        if (b2 == a) {
            b = charArrayBuffer.m12754b(b3, a);
            obj = 1;
        } else {
            String b4 = charArrayBuffer.m12754b(b3, b2);
            b2++;
            b = b4;
        }
        if (obj != null) {
            parserCursor.m12662a(b2);
            return m12591a(b, null);
        }
        Object obj3 = null;
        Object obj4 = null;
        int i2 = b2;
        while (i2 < a) {
            Object obj5;
            char a3 = charArrayBuffer.m12744a(i2);
            if (a3 == '\"' && obj3 == null) {
                obj5 = obj4 == null ? 1 : null;
            } else {
                obj5 = obj4;
            }
            if (obj5 == null && obj3 == null && m12587a(a3, cArr)) {
                break;
            }
            if (obj3 != null) {
                obj4 = null;
            } else if (obj5 == null || a3 != '\\') {
                obj4 = null;
            } else {
                b3 = 1;
            }
            i2++;
            obj3 = obj4;
            obj4 = obj5;
        }
        obj2 = obj;
        int i3 = b2;
        while (i3 < i2 && HTTP.m12703a(charArrayBuffer.m12744a(i3))) {
            i3++;
        }
        b3 = i2;
        while (b3 > i3 && HTTP.m12703a(charArrayBuffer.m12744a(b3 - 1))) {
            b3--;
        }
        if (b3 - i3 >= 2 && charArrayBuffer.m12744a(i3) == '\"' && charArrayBuffer.m12744a(b3 - 1) == '\"') {
            i3++;
            b3--;
        }
        String a4 = charArrayBuffer.m12746a(i3, b3);
        if (obj2 != null) {
            b3 = i2 + 1;
        } else {
            b3 = i2;
        }
        parserCursor.m12662a(b3);
        return m12591a(b, a4);
    }

    protected NameValuePair m12591a(String str, String str2) {
        return new BasicNameValuePair(str, str2);
    }
}
