package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.client.utils.CloneUtils;
import cz.msebera.android.httpclient.concurrent.Cancellable;
import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.HeaderGroup;
import cz.msebera.android.httpclient.params.HttpParams;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractExecutionAwareRequest extends AbstractHttpMessage implements HttpRequest, AbortableHttpRequest, Cloneable {
    private final AtomicBoolean f6561c;
    private final AtomicReference<Cancellable> f6562d;

    /* renamed from: cz.msebera.android.httpclient.client.methods.AbstractExecutionAwareRequest.1 */
    class C09311 implements Cancellable {
        final /* synthetic */ ClientConnectionRequest f7283a;
        final /* synthetic */ AbstractExecutionAwareRequest f7284b;

        C09311(AbstractExecutionAwareRequest abstractExecutionAwareRequest, ClientConnectionRequest clientConnectionRequest) {
            this.f7284b = abstractExecutionAwareRequest;
            this.f7283a = clientConnectionRequest;
        }

        public boolean m11513a() {
            this.f7283a.m11641a();
            return true;
        }
    }

    /* renamed from: cz.msebera.android.httpclient.client.methods.AbstractExecutionAwareRequest.2 */
    class C09322 implements Cancellable {
        final /* synthetic */ ConnectionReleaseTrigger f7285a;
        final /* synthetic */ AbstractExecutionAwareRequest f7286b;

        C09322(AbstractExecutionAwareRequest abstractExecutionAwareRequest, ConnectionReleaseTrigger connectionReleaseTrigger) {
            this.f7286b = abstractExecutionAwareRequest;
            this.f7285a = connectionReleaseTrigger;
        }

        public boolean m11514a() {
            try {
                this.f7285a.m11617j();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
    }

    protected AbstractExecutionAwareRequest() {
        this.f6561c = new AtomicBoolean(false);
        this.f6562d = new AtomicReference(null);
    }

    @Deprecated
    public void m10641a(ClientConnectionRequest clientConnectionRequest) {
        m10640a(new C09311(this, clientConnectionRequest));
    }

    @Deprecated
    public void m10642a(ConnectionReleaseTrigger connectionReleaseTrigger) {
        m10640a(new C09322(this, connectionReleaseTrigger));
    }

    public void m10643i() {
        if (this.f6561c.compareAndSet(false, true)) {
            Cancellable cancellable = (Cancellable) this.f6562d.getAndSet(null);
            if (cancellable != null) {
                cancellable.m11512a();
            }
        }
    }

    public boolean m10644j() {
        return this.f6561c.get();
    }

    public void m10640a(Cancellable cancellable) {
        if (!this.f6561c.get()) {
            this.f6562d.set(cancellable);
        }
    }

    public Object clone() {
        AbstractExecutionAwareRequest abstractExecutionAwareRequest = (AbstractExecutionAwareRequest) super.clone();
        abstractExecutionAwareRequest.a = (HeaderGroup) CloneUtils.m11565a(this.a);
        abstractExecutionAwareRequest.b = (HttpParams) CloneUtils.m11565a(this.b);
        return abstractExecutionAwareRequest;
    }
}
