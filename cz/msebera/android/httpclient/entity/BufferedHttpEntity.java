package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedHttpEntity extends HttpEntityWrapper {
    private final byte[] f7398a;

    public BufferedHttpEntity(HttpEntity httpEntity) {
        super(httpEntity);
        if (!httpEntity.m10544d() || httpEntity.m10542b() < 0) {
            this.f7398a = EntityUtils.m12765b(httpEntity);
        } else {
            this.f7398a = null;
        }
    }

    public long m11819b() {
        if (this.f7398a != null) {
            return (long) this.f7398a.length;
        }
        return super.m10551b();
    }

    public InputStream m11817a() {
        if (this.f7398a != null) {
            return new ByteArrayInputStream(this.f7398a);
        }
        return super.m10549a();
    }

    public boolean m11821e() {
        return this.f7398a == null && super.m10554e();
    }

    public boolean m11820d() {
        return true;
    }

    public void m11818a(OutputStream outputStream) {
        Args.m12722a((Object) outputStream, "Output stream");
        if (this.f7398a != null) {
            outputStream.write(this.f7398a);
        } else {
            super.m10550a(outputStream);
        }
    }

    public boolean m11822f() {
        return this.f7398a == null && super.m10555f();
    }
}
