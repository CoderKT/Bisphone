package cz.msebera.android.httpclient.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.util.Args;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpEntityWrapper implements HttpEntity {
    protected HttpEntity f6514d;

    public HttpEntityWrapper(HttpEntity httpEntity) {
        this.f6514d = (HttpEntity) Args.m12722a((Object) httpEntity, "Wrapped entity");
    }

    public boolean m10553d() {
        return this.f6514d.m10544d();
    }

    public boolean m10554e() {
        return this.f6514d.m10545e();
    }

    public long m10551b() {
        return this.f6514d.m10542b();
    }

    public Header m10557h() {
        return this.f6514d.m10548h();
    }

    public Header m10556g() {
        return this.f6514d.m10547g();
    }

    public InputStream m10549a() {
        return this.f6514d.m10540a();
    }

    public void m10550a(OutputStream outputStream) {
        this.f6514d.m10541a(outputStream);
    }

    public boolean m10555f() {
        return this.f6514d.m10546f();
    }

    @Deprecated
    public void m10552c() {
        this.f6514d.m10543c();
    }
}
