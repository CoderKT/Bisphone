package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicHeaderElement;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.List;

public class NetscapeDraftHeaderParser {
    public static final NetscapeDraftHeaderParser f7777a;

    static {
        f7777a = new NetscapeDraftHeaderParser();
    }

    public HeaderElement m12460a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.m12722a((Object) charArrayBuffer, "Char array buffer");
        Args.m12722a((Object) parserCursor, "Parser cursor");
        NameValuePair b = m12459b(charArrayBuffer, parserCursor);
        List arrayList = new ArrayList();
        while (!parserCursor.m12664c()) {
            arrayList.add(m12459b(charArrayBuffer, parserCursor));
        }
        return new BasicHeaderElement(b.m11403a(), b.m11404b(), (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]));
    }

    private NameValuePair m12459b(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        String b;
        Object obj;
        Object obj2 = 1;
        Object obj3 = null;
        int b2 = parserCursor.m12663b();
        int b3 = parserCursor.m12663b();
        int a = parserCursor.m12661a();
        while (b2 < a) {
            char a2 = charArrayBuffer.m12744a(b2);
            if (a2 == '=') {
                break;
            } else if (a2 == ';') {
                int i = 1;
                break;
            } else {
                b2++;
            }
        }
        if (b2 == a) {
            b = charArrayBuffer.m12754b(b3, a);
            obj = 1;
        } else {
            String b4 = charArrayBuffer.m12754b(b3, b2);
            b2++;
            b = b4;
            obj = obj3;
        }
        if (obj != null) {
            parserCursor.m12662a(b2);
            return new BasicNameValuePair(b, null);
        }
        int i2;
        i = b2;
        while (i < a) {
            if (charArrayBuffer.m12744a(i) == ';') {
                break;
            }
            i++;
        }
        obj2 = obj;
        while (b2 < i && HTTP.m12703a(charArrayBuffer.m12744a(b2))) {
            b2++;
        }
        b3 = i;
        while (b3 > b2 && HTTP.m12703a(charArrayBuffer.m12744a(b3 - 1))) {
            b3--;
        }
        b4 = charArrayBuffer.m12746a(b2, b3);
        if (obj2 != null) {
            i2 = i + 1;
        } else {
            i2 = i;
        }
        parserCursor.m12662a(i2);
        return new BasicNameValuePair(b, b4);
    }
}
