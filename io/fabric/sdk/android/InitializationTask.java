package io.fabric.sdk.android;

import io.fabric.sdk.android.services.common.TimingMetric;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityAsyncTask;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;

class InitializationTask<Result> extends PriorityAsyncTask<Void, Void, Result> {
    final Kit<Result> f8185a;

    public InitializationTask(Kit<Result> kit) {
        this.f8185a = kit;
    }

    protected void m12947a() {
        super.m12928a();
        TimingMetric a = m12944a("onPreExecute");
        try {
            boolean a2 = this.f8185a.m7868a();
            a.m13108b();
            if (!a2) {
                m12930a(true);
            }
        } catch (UnmetDependencyException e) {
            throw e;
        } catch (Throwable e2) {
            Fabric.m12905g().m12874d("Fabric", "Failure onPreExecute()", e2);
            a.m13108b();
            m12930a(true);
        } catch (Throwable th) {
            a.m13108b();
            m12930a(true);
        }
    }

    protected Result m12946a(Void... voidArr) {
        TimingMetric a = m12944a("doInBackground");
        Result result = null;
        if (!m12933e()) {
            result = this.f8185a.m7873z();
        }
        a.m13108b();
        return result;
    }

    protected void m12948a(Result result) {
        this.f8185a.m7867a((Object) result);
        this.f8185a.f5325e.m12890a((Object) result);
    }

    protected void m12950b(Result result) {
        this.f8185a.m7869b((Object) result);
        this.f8185a.f5325e.m12889a(new InitializationException(this.f8185a.m7871c() + " Initialization was cancelled"));
    }

    public Priority m12949b() {
        return Priority.HIGH;
    }

    private TimingMetric m12944a(String str) {
        TimingMetric timingMetric = new TimingMetric(this.f8185a.m7871c() + "." + str, "KitInitialization");
        timingMetric.m13107a();
        return timingMetric;
    }
}
