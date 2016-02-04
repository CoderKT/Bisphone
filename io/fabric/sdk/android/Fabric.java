package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.ActivityLifecycleManager.Callbacks;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fabric {
    static volatile Fabric f8154a;
    static final Logger f8155b;
    final Logger f8156c;
    final boolean f8157d;
    private final Context f8158e;
    private final Map<Class<? extends Kit>, Kit> f8159f;
    private final ExecutorService f8160g;
    private final Handler f8161h;
    private final InitializationCallback<Fabric> f8162i;
    private final InitializationCallback<?> f8163j;
    private final IdManager f8164k;
    private ActivityLifecycleManager f8165l;
    private WeakReference<Activity> f8166m;
    private AtomicBoolean f8167n;

    /* renamed from: io.fabric.sdk.android.Fabric.1 */
    class C09501 extends Callbacks {
        final /* synthetic */ Fabric f8140a;

        C09501(Fabric fabric) {
            this.f8140a = fabric;
        }

        public void m12887a(Activity activity, Bundle bundle) {
            this.f8140a.m12908a(activity);
        }

        public void m12886a(Activity activity) {
            this.f8140a.m12908a(activity);
        }

        public void m12888b(Activity activity) {
            this.f8140a.m12908a(activity);
        }
    }

    /* renamed from: io.fabric.sdk.android.Fabric.2 */
    class C09512 implements InitializationCallback {
        final CountDownLatch f8142a;
        final /* synthetic */ int f8143b;
        final /* synthetic */ Fabric f8144c;

        C09512(Fabric fabric, int i) {
            this.f8144c = fabric;
            this.f8143b = i;
            this.f8142a = new CountDownLatch(this.f8143b);
        }

        public void m12892a(Object obj) {
            this.f8142a.countDown();
            if (this.f8142a.getCount() == 0) {
                this.f8144c.f8167n.set(true);
                this.f8144c.f8162i.m12890a(this.f8144c);
            }
        }

        public void m12891a(Exception exception) {
            this.f8144c.f8162i.m12889a(exception);
        }
    }

    public class Builder {
        private final Context f8145a;
        private Kit[] f8146b;
        private PriorityThreadPoolExecutor f8147c;
        private Handler f8148d;
        private Logger f8149e;
        private boolean f8150f;
        private String f8151g;
        private String f8152h;
        private InitializationCallback<Fabric> f8153i;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.f8145a = context.getApplicationContext();
        }

        public Builder m12893a(Kit... kitArr) {
            if (kitArr == null) {
                throw new IllegalArgumentException("Kits must not be null.");
            } else if (kitArr.length == 0) {
                throw new IllegalArgumentException("Kits must not be empty.");
            } else if (this.f8146b != null) {
                throw new IllegalStateException("Kits already set.");
            } else {
                this.f8146b = kitArr;
                return this;
            }
        }

        public Fabric m12894a() {
            if (this.f8146b == null) {
                throw new IllegalStateException("Kits must not be null.");
            }
            if (this.f8147c == null) {
                this.f8147c = PriorityThreadPoolExecutor.m13134a();
            }
            if (this.f8148d == null) {
                this.f8148d = new Handler(Looper.getMainLooper());
            }
            if (this.f8149e == null) {
                if (this.f8150f) {
                    this.f8149e = new DefaultLogger(3);
                } else {
                    this.f8149e = new DefaultLogger();
                }
            }
            if (this.f8152h == null) {
                this.f8152h = this.f8145a.getPackageName();
            }
            if (this.f8153i == null) {
                this.f8153i = InitializationCallback.f8141d;
            }
            Map a = Fabric.m12903b(Arrays.asList(this.f8146b));
            return new Fabric(this.f8145a, a, this.f8147c, this.f8148d, this.f8149e, this.f8150f, this.f8153i, new IdManager(this.f8145a, this.f8152h, this.f8151g, a.values()));
        }
    }

    static {
        f8155b = new DefaultLogger();
    }

    static Fabric m12895a() {
        if (f8154a != null) {
            return f8154a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    Fabric(Context context, Map<Class<? extends Kit>, Kit> map, PriorityThreadPoolExecutor priorityThreadPoolExecutor, Handler handler, Logger logger, boolean z, InitializationCallback initializationCallback, IdManager idManager) {
        this.f8158e = context;
        this.f8159f = map;
        this.f8160g = priorityThreadPoolExecutor;
        this.f8161h = handler;
        this.f8156c = logger;
        this.f8157d = z;
        this.f8162i = initializationCallback;
        this.f8167n = new AtomicBoolean(false);
        this.f8163j = m12909a(map.size());
        this.f8164k = idManager;
    }

    public static Fabric m12896a(Context context, Kit... kitArr) {
        if (f8154a == null) {
            synchronized (Fabric.class) {
                if (f8154a == null) {
                    m12904c(new Builder(context).m12893a(kitArr).m12894a());
                }
            }
        }
        return f8154a;
    }

    private static void m12904c(Fabric fabric) {
        f8154a = fabric;
        fabric.m12907i();
    }

    public Fabric m12908a(Activity activity) {
        this.f8166m = new WeakReference(activity);
        return this;
    }

    public Activity m12912b() {
        if (this.f8166m != null) {
            return (Activity) this.f8166m.get();
        }
        return null;
    }

    private void m12907i() {
        m12908a(m12901b(this.f8158e));
        this.f8165l = new ActivityLifecycleManager(this.f8158e);
        this.f8165l.m12865a(new C09501(this));
        m12910a(this.f8158e);
    }

    public String m12913c() {
        return "1.3.0.41";
    }

    public String m12914d() {
        return "io.fabric.sdk.android:fabric";
    }

    void m12910a(Context context) {
        StringBuilder append;
        Collection f = m12916f();
        Onboarding onboarding = new Onboarding(f);
        List<Kit> arrayList = new ArrayList(f);
        Collections.sort(arrayList);
        onboarding.m7866a(context, this, InitializationCallback.f8141d, this.f8164k);
        for (Kit a : arrayList) {
            a.m7866a(context, this, this.f8163j, this.f8164k);
        }
        onboarding.m7858A();
        if (m12905g().m12869a("Fabric", 3)) {
            append = new StringBuilder("Initializing ").append(m12914d()).append(" [Version: ").append(m12913c()).append("], with the following kits:\n");
        } else {
            append = null;
        }
        for (Kit a2 : arrayList) {
            a2.f5323c.m12934a(onboarding.c);
            m12911a(this.f8159f, a2);
            a2.m7858A();
            if (append != null) {
                append.append(a2.m7871c()).append(" [Version: ").append(a2.m7872d()).append("]\n");
            }
        }
        if (append != null) {
            m12905g().m12867a("Fabric", append.toString());
        }
    }

    void m12911a(Map<Class<? extends Kit>, Kit> map, Kit kit) {
        DependsOn dependsOn = (DependsOn) kit.getClass().getAnnotation(DependsOn.class);
        if (dependsOn != null) {
            for (Class cls : dependsOn.m13120a()) {
                if (cls.isInterface()) {
                    for (Kit kit2 : map.values()) {
                        if (cls.isAssignableFrom(kit2.getClass())) {
                            kit.f5323c.m12934a(kit2.f5323c);
                        }
                    }
                } else if (((Kit) map.get(cls)) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    kit.f5323c.m12934a(((Kit) map.get(cls)).f5323c);
                }
            }
        }
    }

    private Activity m12901b(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public ExecutorService m12915e() {
        return this.f8160g;
    }

    public Collection<Kit> m12916f() {
        return this.f8159f.values();
    }

    public static <T extends Kit> T m12897a(Class<T> cls) {
        return (Kit) m12895a().f8159f.get(cls);
    }

    public static Logger m12905g() {
        if (f8154a == null) {
            return f8155b;
        }
        return f8154a.f8156c;
    }

    public static boolean m12906h() {
        if (f8154a == null) {
            return false;
        }
        return f8154a.f8157d;
    }

    private static Map<Class<? extends Kit>, Kit> m12903b(Collection<? extends Kit> collection) {
        Map hashMap = new HashMap(collection.size());
        m12900a(hashMap, (Collection) collection);
        return hashMap;
    }

    private static void m12900a(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> collection) {
        for (Kit kit : collection) {
            map.put(kit.getClass(), kit);
            if (kit instanceof KitGroup) {
                m12900a((Map) map, ((KitGroup) kit).m7874e());
            }
        }
    }

    InitializationCallback<?> m12909a(int i) {
        return new C09512(this, i);
    }
}
