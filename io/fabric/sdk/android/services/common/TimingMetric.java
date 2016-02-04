package io.fabric.sdk.android.services.common;

import android.os.SystemClock;
import android.util.Log;

@Deprecated
public class TimingMetric {
    private final String f8279a;
    private final String f8280b;
    private final boolean f8281c;
    private long f8282d;
    private long f8283e;

    public TimingMetric(String str, String str2) {
        this.f8279a = str;
        this.f8280b = str2;
        this.f8281c = !Log.isLoggable(str2, 2);
    }

    public synchronized void m13107a() {
        if (!this.f8281c) {
            this.f8282d = SystemClock.elapsedRealtime();
            this.f8283e = 0;
        }
    }

    public synchronized void m13108b() {
        if (!this.f8281c) {
            if (this.f8283e == 0) {
                this.f8283e = SystemClock.elapsedRealtime() - this.f8282d;
                m13106c();
            }
        }
    }

    private void m13106c() {
        Log.v(this.f8280b, this.f8279a + ": " + this.f8283e + "ms");
    }
}
