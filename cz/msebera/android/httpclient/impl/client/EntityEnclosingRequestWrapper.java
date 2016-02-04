package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
public class EntityEnclosingRequestWrapper extends RequestWrapper implements HttpEntityEnclosingRequest {
    private HttpEntity f7617c;
    private boolean f7618d;

    class EntityWrapper extends HttpEntityWrapper {
        final /* synthetic */ EntityEnclosingRequestWrapper f7611a;

        EntityWrapper(EntityEnclosingRequestWrapper entityEnclosingRequestWrapper, HttpEntity httpEntity) {
            this.f7611a = entityEnclosingRequestWrapper;
            super(httpEntity);
        }

        public void m12131c() {
            this.f7611a.f7618d = true;
            super.m10552c();
        }

        public InputStream m12129a() {
            this.f7611a.f7618d = true;
            return super.m10549a();
        }

        public void m12130a(OutputStream outputStream) {
            this.f7611a.f7618d = true;
            super.m10550a(outputStream);
        }
    }

    public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        super(httpEntityEnclosingRequest);
        m12145a(httpEntityEnclosingRequest.m10657c());
    }

    public HttpEntity m12147c() {
        return this.f7617c;
    }

    public void m12145a(HttpEntity httpEntity) {
        this.f7617c = httpEntity != null ? new EntityWrapper(this, httpEntity) : null;
        this.f7618d = false;
    }

    public boolean m12146b() {
        Header c = m10631c("Expect");
        return c != null && "100-continue".equalsIgnoreCase(c.m11362d());
    }

    public boolean m12148l() {
        return this.f7617c == null || this.f7617c.m10544d() || !this.f7618d;
    }
}
