package cz.msebera.android.httpclient.conn;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.HttpEntityWrapper;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.EntityUtils;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

@Deprecated
public class BasicManagedEntity extends HttpEntityWrapper implements ConnectionReleaseTrigger, EofSensorWatcher {
    protected ManagedClientConnection f7336a;
    protected final boolean f7337b;

    public BasicManagedEntity(HttpEntity httpEntity, ManagedClientConnection managedClientConnection, boolean z) {
        super(httpEntity);
        Args.m12722a((Object) managedClientConnection, "Connection");
        this.f7336a = managedClientConnection;
        this.f7337b = z;
    }

    public boolean m11628d() {
        return false;
    }

    public InputStream m11622a() {
        return new EofSensorInputStream(this.d.m10540a(), this);
    }

    private void m11621l() {
        if (this.f7336a != null) {
            try {
                if (this.f7337b) {
                    EntityUtils.m12764a(this.d);
                    this.f7336a.m11660k();
                } else {
                    this.f7336a.m11661l();
                }
                m11631k();
            } catch (Throwable th) {
                m11631k();
            }
        }
    }

    @Deprecated
    public void m11626c() {
        m11621l();
    }

    public void m11623a(OutputStream outputStream) {
        super.m10550a(outputStream);
        m11621l();
    }

    public void m11629i() {
        m11621l();
    }

    public void m11630j() {
        if (this.f7336a != null) {
            try {
                this.f7336a.m11617j();
            } finally {
                this.f7336a = null;
            }
        }
    }

    public boolean m11624a(InputStream inputStream) {
        try {
            if (this.f7336a != null) {
                if (this.f7337b) {
                    inputStream.close();
                    this.f7336a.m11660k();
                } else {
                    this.f7336a.m11661l();
                }
            }
            m11631k();
            return false;
        } catch (Throwable th) {
            m11631k();
        }
    }

    public boolean m11625b(InputStream inputStream) {
        boolean c;
        try {
            if (this.f7336a != null) {
                if (this.f7337b) {
                    c = this.f7336a.m11375c();
                    inputStream.close();
                    this.f7336a.m11660k();
                } else {
                    this.f7336a.m11661l();
                }
            }
        } catch (SocketException e) {
            if (c) {
                throw e;
            }
        } catch (Throwable th) {
            m11631k();
        }
        m11631k();
        return false;
    }

    public boolean m11627c(InputStream inputStream) {
        if (this.f7336a != null) {
            this.f7336a.m11617j();
        }
        return false;
    }

    protected void m11631k() {
        if (this.f7336a != null) {
            try {
                this.f7336a.m11616i();
            } finally {
                this.f7336a = null;
            }
        }
    }
}
