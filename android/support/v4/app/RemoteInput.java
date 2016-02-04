package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput.Factory;

public class RemoteInput extends android.support.v4.app.RemoteInputCompatBase.RemoteInput {
    public static final Factory f330a;
    private static final Impl f331g;
    private final String f332b;
    private final CharSequence f333c;
    private final CharSequence[] f334d;
    private final boolean f335e;
    private final Bundle f336f;

    /* renamed from: android.support.v4.app.RemoteInput.1 */
    final class C00131 implements Factory {
        C00131() {
        }
    }

    interface Impl {
    }

    class ImplApi20 implements Impl {
        ImplApi20() {
        }
    }

    class ImplBase implements Impl {
        ImplBase() {
        }
    }

    class ImplJellybean implements Impl {
        ImplJellybean() {
        }
    }

    public String m588a() {
        return this.f332b;
    }

    public CharSequence m589b() {
        return this.f333c;
    }

    public CharSequence[] m590c() {
        return this.f334d;
    }

    public boolean m591d() {
        return this.f335e;
    }

    public Bundle m592e() {
        return this.f336f;
    }

    static {
        if (VERSION.SDK_INT >= 20) {
            f331g = new ImplApi20();
        } else if (VERSION.SDK_INT >= 16) {
            f331g = new ImplJellybean();
        } else {
            f331g = new ImplBase();
        }
        f330a = new C00131();
    }
}
