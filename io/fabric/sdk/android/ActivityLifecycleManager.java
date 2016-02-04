package io.fabric.sdk.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

public class ActivityLifecycleManager {
    private final Application f8137a;
    private ActivityLifecycleCallbacksWrapper f8138b;

    class ActivityLifecycleCallbacksWrapper {
        private final Set<ActivityLifecycleCallbacks> f8135a;
        private final Application f8136b;

        /* renamed from: io.fabric.sdk.android.ActivityLifecycleManager.ActivityLifecycleCallbacksWrapper.1 */
        class C09491 implements ActivityLifecycleCallbacks {
            final /* synthetic */ Callbacks f8133a;
            final /* synthetic */ ActivityLifecycleCallbacksWrapper f8134b;

            C09491(ActivityLifecycleCallbacksWrapper activityLifecycleCallbacksWrapper, Callbacks callbacks) {
                this.f8134b = activityLifecycleCallbacksWrapper;
                this.f8133a = callbacks;
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                this.f8133a.m12859a(activity, bundle);
            }

            public void onActivityStarted(Activity activity) {
                this.f8133a.m12858a(activity);
            }

            public void onActivityResumed(Activity activity) {
                this.f8133a.m12860b(activity);
            }

            public void onActivityPaused(Activity activity) {
                this.f8133a.m12862c(activity);
            }

            public void onActivityStopped(Activity activity) {
                this.f8133a.m12863d(activity);
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                this.f8133a.m12861b(activity, bundle);
            }

            public void onActivityDestroyed(Activity activity) {
                this.f8133a.m12864e(activity);
            }
        }

        ActivityLifecycleCallbacksWrapper(Application application) {
            this.f8135a = new HashSet();
            this.f8136b = application;
        }

        @TargetApi(14)
        private boolean m12857a(Callbacks callbacks) {
            if (this.f8136b == null) {
                return false;
            }
            ActivityLifecycleCallbacks c09491 = new C09491(this, callbacks);
            this.f8136b.registerActivityLifecycleCallbacks(c09491);
            this.f8135a.add(c09491);
            return true;
        }
    }

    public abstract class Callbacks {
        public void m12859a(Activity activity, Bundle bundle) {
        }

        public void m12858a(Activity activity) {
        }

        public void m12860b(Activity activity) {
        }

        public void m12862c(Activity activity) {
        }

        public void m12863d(Activity activity) {
        }

        public void m12861b(Activity activity, Bundle bundle) {
        }

        public void m12864e(Activity activity) {
        }
    }

    public ActivityLifecycleManager(Context context) {
        this.f8137a = (Application) context.getApplicationContext();
        if (VERSION.SDK_INT >= 14) {
            this.f8138b = new ActivityLifecycleCallbacksWrapper(this.f8137a);
        }
    }

    public boolean m12865a(Callbacks callbacks) {
        return this.f8138b != null && this.f8138b.m12857a(callbacks);
    }
}
