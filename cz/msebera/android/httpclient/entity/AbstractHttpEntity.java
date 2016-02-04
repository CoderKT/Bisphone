package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.message.BasicHeader;

public abstract class AbstractHttpEntity implements HttpEntity {
    protected Header f7279a;
    protected Header f7280b;
    protected boolean f7281c;

    protected AbstractHttpEntity() {
    }

    public Header m11506h() {
        return this.f7279a;
    }

    public Header m11505g() {
        return this.f7280b;
    }

    public boolean m11504e() {
        return this.f7281c;
    }

    public void m11499a(Header header) {
        this.f7279a = header;
    }

    public void m11500a(String str) {
        Header header = null;
        if (str != null) {
            header = new BasicHeader("Content-Type", str);
        }
        m11499a(header);
    }

    public void m11502b(Header header) {
        this.f7280b = header;
    }

    public void m11501a(boolean z) {
        this.f7281c = z;
    }

    @Deprecated
    public void m11503c() {
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        if (this.f7279a != null) {
            stringBuilder.append("Content-Type: ");
            stringBuilder.append(this.f7279a.m11362d());
            stringBuilder.append(',');
        }
        if (this.f7280b != null) {
            stringBuilder.append("Content-Encoding: ");
            stringBuilder.append(this.f7280b.m11362d());
            stringBuilder.append(',');
        }
        long b = m10542b();
        if (b >= 0) {
            stringBuilder.append("Content-Length: ");
            stringBuilder.append(b);
            stringBuilder.append(',');
        }
        stringBuilder.append("Chunked: ");
        stringBuilder.append(this.f7281c);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
