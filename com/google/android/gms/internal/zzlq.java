package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.common.internal.zzx;

public class zzlq<R extends Result> extends zze<R> implements ResultCallback<R> {
    private zzb<? super R, ? extends Result> f6107a;
    private zzlq<? extends Result> f6108b;
    private ResultCallbacks<? super R> f6109c;
    private PendingResult<R> f6110d;
    private final Object f6111e;

    private void m9232a() {
        if (this.f6110d == null) {
            return;
        }
        if (this.f6107a != null || this.f6109c != null) {
            this.f6110d.m8390a(this);
        }
    }

    private void m9233b(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).m8391a();
            } catch (Throwable e) {
                Log.w("TransformedResultImpl", "Unable to release " + result, e);
            }
        }
    }

    public void m9234a(PendingResult<?> pendingResult) {
        synchronized (this.f6111e) {
            this.f6110d = pendingResult;
            m9232a();
        }
    }

    public void m9235a(R r) {
        synchronized (this.f6111e) {
            if (!r.m8392a().m8402e()) {
                m9236a(r.m8392a());
                m9233b(r);
            } else if (this.f6107a != null) {
                PendingResult a = this.f6107a.m8405a((Result) r);
                if (a == null) {
                    m9236a(new Status(13, "Transform returned null"));
                } else {
                    this.f6108b.m9234a(a);
                }
                m9233b(r);
            } else if (this.f6109c != null) {
                this.f6109c.m8395b(r);
            }
        }
    }

    public void m9236a(Status status) {
        synchronized (this.f6111e) {
            if (this.f6107a != null) {
                Status a = this.f6107a.m8406a(status);
                zzx.m8719a((Object) a, (Object) "onFailure must not return null");
                this.f6108b.m9236a(a);
            } else if (this.f6109c != null) {
                this.f6109c.m8394a(status);
            }
        }
    }
}
