package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.RequestLine;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

public class BasicLineFormatter implements LineFormatter {
    @Deprecated
    public static final BasicLineFormatter f7877a;
    public static final BasicLineFormatter f7878b;

    static {
        f7877a = new BasicLineFormatter();
        f7878b = new BasicLineFormatter();
    }

    protected CharArrayBuffer m12606a(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer == null) {
            return new CharArrayBuffer(64);
        }
        charArrayBuffer.m12747a();
        return charArrayBuffer;
    }

    public CharArrayBuffer m12608a(CharArrayBuffer charArrayBuffer, ProtocolVersion protocolVersion) {
        Args.m12722a((Object) protocolVersion, "Protocol version");
        int a = m12605a(protocolVersion);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(a);
        } else {
            charArrayBuffer.m12755b(a);
        }
        charArrayBuffer.m12751a(protocolVersion.m11396a());
        charArrayBuffer.m12748a('/');
        charArrayBuffer.m12751a(Integer.toString(protocolVersion.m11398b()));
        charArrayBuffer.m12748a('.');
        charArrayBuffer.m12751a(Integer.toString(protocolVersion.m11400c()));
        return charArrayBuffer;
    }

    protected int m12605a(ProtocolVersion protocolVersion) {
        return protocolVersion.m11396a().length() + 4;
    }

    public CharArrayBuffer m12609a(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        Args.m12722a((Object) requestLine, "Request line");
        CharArrayBuffer a = m12606a(charArrayBuffer);
        m12612b(a, requestLine);
        return a;
    }

    protected void m12612b(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        String a = requestLine.m11406a();
        String c = requestLine.m11408c();
        charArrayBuffer.m12755b((((a.length() + 1) + c.length()) + 1) + m12605a(requestLine.m11407b()));
        charArrayBuffer.m12751a(a);
        charArrayBuffer.m12748a(' ');
        charArrayBuffer.m12751a(c);
        charArrayBuffer.m12748a(' ');
        m12608a(charArrayBuffer, requestLine.m11407b());
    }

    public CharArrayBuffer m12610a(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        Args.m12722a((Object) statusLine, "Status line");
        CharArrayBuffer a = m12606a(charArrayBuffer);
        m12613b(a, statusLine);
        return a;
    }

    protected void m12613b(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        int a = ((m12605a(statusLine.m11409a()) + 1) + 3) + 1;
        String c = statusLine.m11411c();
        if (c != null) {
            a += c.length();
        }
        charArrayBuffer.m12755b(a);
        m12608a(charArrayBuffer, statusLine.m11409a());
        charArrayBuffer.m12748a(' ');
        charArrayBuffer.m12751a(Integer.toString(statusLine.m11410b()));
        charArrayBuffer.m12748a(' ');
        if (c != null) {
            charArrayBuffer.m12751a(c);
        }
    }

    public CharArrayBuffer m12607a(CharArrayBuffer charArrayBuffer, Header header) {
        Args.m12722a((Object) header, "Header");
        if (header instanceof FormattedHeader) {
            return ((FormattedHeader) header).m11364a();
        }
        CharArrayBuffer a = m12606a(charArrayBuffer);
        m12611b(a, header);
        return a;
    }

    protected void m12611b(CharArrayBuffer charArrayBuffer, Header header) {
        String c = header.m11361c();
        String d = header.m11362d();
        int length = c.length() + 2;
        if (d != null) {
            length += d.length();
        }
        charArrayBuffer.m12755b(length);
        charArrayBuffer.m12751a(c);
        charArrayBuffer.m12751a(": ");
        if (d != null) {
            charArrayBuffer.m12751a(d);
        }
    }
}
