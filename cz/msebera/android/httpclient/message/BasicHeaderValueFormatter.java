package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public class BasicHeaderValueFormatter {
    @Deprecated
    public static final BasicHeaderValueFormatter f7862a;
    public static final BasicHeaderValueFormatter f7863b;

    static {
        f7862a = new BasicHeaderValueFormatter();
        f7863b = new BasicHeaderValueFormatter();
    }

    public CharArrayBuffer m12579a(CharArrayBuffer charArrayBuffer, HeaderElement headerElement, boolean z) {
        Args.m12722a((Object) headerElement, "Header element");
        int a = m12576a(headerElement);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.m12755b(a);
        }
        charArrayBuffer.m12751a(headerElement.m11368a());
        String b = headerElement.m11369b();
        if (b != null) {
            charArrayBuffer.m12748a('=');
            m12582a(charArrayBuffer, b, z);
        }
        int d = headerElement.m11371d();
        if (d > 0) {
            for (a = 0; a < d; a++) {
                charArrayBuffer.m12751a("; ");
                m12580a(charArrayBuffer, headerElement.m11366a(a), z);
            }
        }
        return charArrayBuffer;
    }

    protected int m12576a(HeaderElement headerElement) {
        int i = 0;
        if (headerElement == null) {
            return 0;
        }
        int length = headerElement.m11368a().length();
        String b = headerElement.m11369b();
        if (b != null) {
            length += b.length() + 3;
        }
        int d = headerElement.m11371d();
        if (d <= 0) {
            return length;
        }
        while (i < d) {
            length += m12577a(headerElement.m11366a(i)) + 2;
            i++;
        }
        return length;
    }

    public CharArrayBuffer m12581a(CharArrayBuffer charArrayBuffer, NameValuePair[] nameValuePairArr, boolean z) {
        Args.m12722a((Object) nameValuePairArr, "Header parameter array");
        int a = m12578a(nameValuePairArr);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.m12755b(a);
        }
        for (a = 0; a < nameValuePairArr.length; a++) {
            if (a > 0) {
                charArrayBuffer.m12751a("; ");
            }
            m12580a(charArrayBuffer, nameValuePairArr[a], z);
        }
        return charArrayBuffer;
    }

    protected int m12578a(NameValuePair[] nameValuePairArr) {
        int i = 0;
        if (nameValuePairArr != null && nameValuePairArr.length >= 1) {
            int length = (nameValuePairArr.length - 1) * 2;
            i = length;
            length = 0;
            while (length < nameValuePairArr.length) {
                int a = m12577a(nameValuePairArr[length]) + i;
                length++;
                i = a;
            }
        }
        return i;
    }

    public CharArrayBuffer m12580a(CharArrayBuffer charArrayBuffer, NameValuePair nameValuePair, boolean z) {
        Args.m12722a((Object) nameValuePair, "Name / value pair");
        int a = m12577a(nameValuePair);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.m12755b(a);
        }
        charArrayBuffer.m12751a(nameValuePair.m11403a());
        String b = nameValuePair.m11404b();
        if (b != null) {
            charArrayBuffer.m12748a('=');
            m12582a(charArrayBuffer, b, z);
        }
        return charArrayBuffer;
    }

    protected int m12577a(NameValuePair nameValuePair) {
        if (nameValuePair == null) {
            return 0;
        }
        int length = nameValuePair.m11403a().length();
        String b = nameValuePair.m11404b();
        if (b != null) {
            return length + (b.length() + 3);
        }
        return length;
    }

    protected void m12582a(CharArrayBuffer charArrayBuffer, String str, boolean z) {
        int i = 0;
        if (!z) {
            for (int i2 = 0; i2 < str.length() && !r7; i2++) {
                z = m12583a(str.charAt(i2));
            }
        }
        if (z) {
            charArrayBuffer.m12748a('\"');
        }
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (m12584b(charAt)) {
                charArrayBuffer.m12748a('\\');
            }
            charArrayBuffer.m12748a(charAt);
            i++;
        }
        if (z) {
            charArrayBuffer.m12748a('\"');
        }
    }

    protected boolean m12583a(char c) {
        return " ;,:@()<>\\\"/[]?={}\t".indexOf(c) >= 0;
    }

    protected boolean m12584b(char c) {
        return "\"\\".indexOf(c) >= 0;
    }
}
