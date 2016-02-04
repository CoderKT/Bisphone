package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class StringEntity extends AbstractHttpEntity implements Cloneable {
    protected final byte[] f7282d;

    public StringEntity(String str, ContentType contentType) {
        Args.m12722a((Object) str, "Source string");
        Charset a = contentType != null ? contentType.m11827a() : null;
        if (a == null) {
            a = HTTP.f7912a;
        }
        try {
            this.f7282d = str.getBytes(a.name());
            if (contentType != null) {
                m11500a(contentType.toString());
            }
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedCharsetException(a.name());
        }
    }

    public StringEntity(String str) {
        this(str, ContentType.f7411m);
    }

    public boolean m11510d() {
        return true;
    }

    public long m11509b() {
        return (long) this.f7282d.length;
    }

    public InputStream m11507a() {
        return new ByteArrayInputStream(this.f7282d);
    }

    public void m11508a(OutputStream outputStream) {
        Args.m12722a((Object) outputStream, "Output stream");
        outputStream.write(this.f7282d);
        outputStream.flush();
    }

    public boolean m11511f() {
        return false;
    }

    public Object clone() {
        return super.clone();
    }
}
