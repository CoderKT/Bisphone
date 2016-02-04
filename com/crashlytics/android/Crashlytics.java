package com.crashlytics.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.beta.Beta;
import com.crashlytics.android.internal.CrashEventDataProvider;
import com.crashlytics.android.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitGroup;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash.FatalException;
import io.fabric.sdk.android.services.common.Crash.LoggedException;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityCallable;
import io.fabric.sdk.android.services.concurrency.Task;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.network.PinningInfoProvider;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import io.fabric.sdk.android.services.settings.PromptSettingsData;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.Settings.SettingsAccess;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@DependsOn(a = {CrashEventDataProvider.class})
public class Crashlytics extends Kit<Void> implements KitGroup {
    private final long f5327a;
    private final ConcurrentHashMap<String, String> f5328g;
    private final Collection<Kit<Boolean>> f5329h;
    private File f5330i;
    private CrashlyticsListener f5331j;
    private CrashlyticsUncaughtExceptionHandler f5332k;
    private String f5333l;
    private String f5334m;
    private String f5335n;
    private String f5336o;
    private String f5337p;
    private String f5338q;
    private String f5339r;
    private String f5340s;
    private float f5341t;
    private boolean f5342u;
    private final PinningInfoProvider f5343v;
    private HttpRequestFactory f5344w;
    private CrashlyticsExecutorServiceWrapper f5345x;
    private CrashEventDataProvider f5346y;

    /* renamed from: com.crashlytics.android.Crashlytics.1 */
    class C05911 extends PriorityCallable<Void> {
        final /* synthetic */ Crashlytics f5305a;

        C05911(Crashlytics crashlytics) {
            this.f5305a = crashlytics;
        }

        public /* synthetic */ Object call() {
            return m7845a();
        }

        public Void m7845a() {
            return this.f5305a.m7897b();
        }

        public Priority m7846b() {
            return Priority.IMMEDIATE;
        }
    }

    /* renamed from: com.crashlytics.android.Crashlytics.2 */
    class C05922 implements Callable<Void> {
        final /* synthetic */ Crashlytics f5306a;

        C05922(Crashlytics crashlytics) {
            this.f5306a = crashlytics;
        }

        public /* synthetic */ Object call() {
            return m7847a();
        }

        public Void m7847a() {
            this.f5306a.f5330i.createNewFile();
            Fabric.m12905g().m12867a("Fabric", "Initialization marker file created.");
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.Crashlytics.3 */
    class C05933 implements Callable<Boolean> {
        final /* synthetic */ Crashlytics f5307a;

        C05933(Crashlytics crashlytics) {
            this.f5307a = crashlytics;
        }

        public /* synthetic */ Object call() {
            return m7848a();
        }

        public Boolean m7848a() {
            try {
                boolean delete = this.f5307a.f5330i.delete();
                Fabric.m12905g().m12867a("Fabric", "Initialization marker file removed: " + delete);
                return Boolean.valueOf(delete);
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Problem encountered deleting Crashlytics initialization marker.", e);
                return Boolean.valueOf(false);
            }
        }
    }

    /* renamed from: com.crashlytics.android.Crashlytics.4 */
    class C05944 implements Callable<Boolean> {
        final /* synthetic */ Crashlytics f5308a;

        C05944(Crashlytics crashlytics) {
            this.f5308a = crashlytics;
        }

        public /* synthetic */ Object call() {
            return m7849a();
        }

        public Boolean m7849a() {
            return Boolean.valueOf(this.f5308a.f5330i.exists());
        }
    }

    /* renamed from: com.crashlytics.android.Crashlytics.5 */
    class C05955 implements SettingsAccess<Boolean> {
        final /* synthetic */ Crashlytics f5309a;

        C05955(Crashlytics crashlytics) {
            this.f5309a = crashlytics;
        }

        public /* synthetic */ Object m7852b(SettingsData settingsData) {
            return m7851a(settingsData);
        }

        public Boolean m7851a(SettingsData settingsData) {
            boolean z = false;
            if (!settingsData.f8442d.f8409a) {
                return Boolean.valueOf(false);
            }
            if (!this.f5309a.m7917w()) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: com.crashlytics.android.Crashlytics.6 */
    class C05966 implements SettingsAccess<Boolean> {
        final /* synthetic */ Crashlytics f5310a;

        C05966(Crashlytics crashlytics) {
            this.f5310a = crashlytics;
        }

        public /* synthetic */ Object m7854b(SettingsData settingsData) {
            return m7853a(settingsData);
        }

        public Boolean m7853a(SettingsData settingsData) {
            boolean z = true;
            Activity b = this.f5310a.m7861D().m12912b();
            if (!(b == null || b.isFinishing() || !this.f5310a.m7916v())) {
                z = this.f5310a.m7883a(b, settingsData.f8441c);
            }
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: com.crashlytics.android.Crashlytics.7 */
    class C06007 implements Runnable {
        final /* synthetic */ Activity f5314a;
        final /* synthetic */ OptInLatch f5315b;
        final /* synthetic */ DialogStringResolver f5316c;
        final /* synthetic */ PromptSettingsData f5317d;
        final /* synthetic */ Crashlytics f5318e;

        /* renamed from: com.crashlytics.android.Crashlytics.7.1 */
        class C05971 implements OnClickListener {
            final /* synthetic */ C06007 f5311a;

            C05971(C06007 c06007) {
                this.f5311a = c06007;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f5311a.f5315b.m7855a(true);
                dialogInterface.dismiss();
            }
        }

        /* renamed from: com.crashlytics.android.Crashlytics.7.2 */
        class C05982 implements OnClickListener {
            final /* synthetic */ C06007 f5312a;

            C05982(C06007 c06007) {
                this.f5312a = c06007;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f5312a.f5315b.m7855a(false);
                dialogInterface.dismiss();
            }
        }

        /* renamed from: com.crashlytics.android.Crashlytics.7.3 */
        class C05993 implements OnClickListener {
            final /* synthetic */ C06007 f5313a;

            C05993(C06007 c06007) {
                this.f5313a = c06007;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f5313a.f5318e.m7894a(true);
                this.f5313a.f5315b.m7855a(true);
                dialogInterface.dismiss();
            }
        }

        C06007(Crashlytics crashlytics, Activity activity, OptInLatch optInLatch, DialogStringResolver dialogStringResolver, PromptSettingsData promptSettingsData) {
            this.f5318e = crashlytics;
            this.f5314a = activity;
            this.f5315b = optInLatch;
            this.f5316c = dialogStringResolver;
            this.f5317d = promptSettingsData;
        }

        public void run() {
            Builder builder = new Builder(this.f5314a);
            OnClickListener c05971 = new C05971(this);
            float f = this.f5314a.getResources().getDisplayMetrics().density;
            int a = this.f5318e.m7877a(f, 5);
            View textView = new TextView(this.f5314a);
            textView.setAutoLinkMask(15);
            textView.setText(this.f5316c.m8000b());
            textView.setTextAppearance(this.f5314a, 16973892);
            textView.setPadding(a, a, a, a);
            textView.setFocusable(false);
            View scrollView = new ScrollView(this.f5314a);
            scrollView.setPadding(this.f5318e.m7877a(f, 14), this.f5318e.m7877a(f, 2), this.f5318e.m7877a(f, 10), this.f5318e.m7877a(f, 12));
            scrollView.addView(textView);
            builder.setView(scrollView).setTitle(this.f5316c.m7999a()).setCancelable(false).setNeutralButton(this.f5316c.m8001c(), c05971);
            if (this.f5317d.f8420d) {
                builder.setNegativeButton(this.f5316c.m8003e(), new C05982(this));
            }
            if (this.f5317d.f8422f) {
                builder.setPositiveButton(this.f5316c.m8002d(), new C05993(this));
            }
            builder.show();
        }
    }

    class OptInLatch {
        final /* synthetic */ Crashlytics f5319a;
        private boolean f5320b;
        private final CountDownLatch f5321c;

        private OptInLatch(Crashlytics crashlytics) {
            this.f5319a = crashlytics;
            this.f5320b = false;
            this.f5321c = new CountDownLatch(1);
        }

        void m7855a(boolean z) {
            this.f5320b = z;
            this.f5321c.countDown();
        }

        boolean m7856a() {
            return this.f5320b;
        }

        void m7857b() {
            try {
                this.f5321c.await();
            } catch (InterruptedException e) {
            }
        }
    }

    protected /* synthetic */ Object m7920z() {
        return m7897b();
    }

    public Crashlytics() {
        this(1.0f, null, null, false);
    }

    Crashlytics(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z) {
        this(f, crashlyticsListener, pinningInfoProvider, z, ExecutorUtils.m13046a("Crashlytics Exception Handler"));
    }

    Crashlytics(float f, CrashlyticsListener crashlyticsListener, PinningInfoProvider pinningInfoProvider, boolean z, ExecutorService executorService) {
        this.f5333l = null;
        this.f5334m = null;
        this.f5335n = null;
        this.f5328g = new ConcurrentHashMap();
        this.f5327a = System.currentTimeMillis();
        this.f5341t = f;
        this.f5331j = crashlyticsListener;
        this.f5343v = pinningInfoProvider;
        this.f5342u = z;
        this.f5345x = new CrashlyticsExecutorServiceWrapper(executorService);
        this.f5329h = Collections.unmodifiableCollection(Arrays.asList(new Kit[]{new Answers(), new Beta()}));
    }

    protected boolean m7895a() {
        return m7896a(super.m7860C());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean m7896a(android.content.Context r9) {
        /*
        r8 = this;
        r7 = 0;
        r0 = r8.f5342u;
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        r0 = r7;
    L_0x0006:
        return r0;
    L_0x0007:
        r0 = new io.fabric.sdk.android.services.common.ApiKey;
        r0.<init>();
        r0 = r0.m12991a(r9);
        if (r0 != 0) goto L_0x0014;
    L_0x0012:
        r0 = r7;
        goto L_0x0006;
    L_0x0014:
        r1 = io.fabric.sdk.android.Fabric.m12905g();
        r2 = "Fabric";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Initializing Crashlytics ";
        r3 = r3.append(r4);
        r4 = r8.m7899d();
        r3 = r3.append(r4);
        r3 = r3.toString();
        r1.m12870b(r2, r3);
        r1 = new java.io.File;
        r2 = r8.m7915u();
        r3 = "initialization_marker";
        r1.<init>(r2, r3);
        r8.f5330i = r1;
        r8.m7880a(r9, r0);	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        r5 = new com.crashlytics.android.SessionDataWriter;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = r8.m7860C();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = r8.f5336o;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r2 = r8.m7902h();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r5.<init>(r0, r1, r2);	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = io.fabric.sdk.android.Fabric.m12905g();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = "Fabric";
        r2 = "Installing exception handler...";
        r0.m12867a(r1, r2);	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = new com.crashlytics.android.CrashlyticsUncaughtExceptionHandler;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r2 = r8.f5331j;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r3 = r8.f5345x;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r4 = r8.m7859B();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r6 = r8;
        r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r8.f5332k = r0;	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r1 = r8.m7913s();	 Catch:{ Exception -> 0x0099, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = r8.f5332k;	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0.m7982b();	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = r8.f5332k;	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r0 = io.fabric.sdk.android.Fabric.m12905g();	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
        r2 = "Fabric";
        r3 = "Successfully installed exception handler.";
        r0.m12867a(r2, r3);	 Catch:{ Exception -> 0x00c0, CrashlyticsMissingDependencyException -> 0x00a7 }
    L_0x008b:
        if (r1 == 0) goto L_0x00ae;
    L_0x008d:
        r0 = io.fabric.sdk.android.services.common.CommonUtils.m13039n(r9);	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        if (r0 == 0) goto L_0x00ae;
    L_0x0093:
        r8.m7875H();	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        r0 = r7;
        goto L_0x0006;
    L_0x0099:
        r0 = move-exception;
        r1 = r7;
    L_0x009b:
        r2 = io.fabric.sdk.android.Fabric.m12905g();	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        r3 = "Fabric";
        r4 = "There was a problem installing the exception handler.";
        r2.m12874d(r3, r4, r0);	 Catch:{ CrashlyticsMissingDependencyException -> 0x00a7, Exception -> 0x00b1 }
        goto L_0x008b;
    L_0x00a7:
        r0 = move-exception;
        r1 = new io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
        r1.<init>(r0);
        throw r1;
    L_0x00ae:
        r0 = 1;
        goto L_0x0006;
    L_0x00b1:
        r0 = move-exception;
        r1 = io.fabric.sdk.android.Fabric.m12905g();
        r2 = "Fabric";
        r3 = "Crashlytics was not started due to an exception during initialization";
        r1.m12874d(r2, r3, r0);
        r0 = r7;
        goto L_0x0006;
    L_0x00c0:
        r0 = move-exception;
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.Crashlytics.a(android.content.Context):boolean");
    }

    private void m7880a(Context context, String str) {
        PinningInfoProvider crashlyticsPinningInfoProvider = this.f5343v != null ? new CrashlyticsPinningInfoProvider(this.f5343v) : null;
        this.f5344w = new DefaultHttpRequestFactory(Fabric.m12905g());
        this.f5344w.m13163a(crashlyticsPinningInfoProvider);
        try {
            this.f5337p = context.getPackageName();
            this.f5338q = m7859B().m13068h();
            Fabric.m12905g().m12867a("Fabric", "Installer package name is: " + this.f5338q);
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.f5337p, 0);
            this.f5339r = Integer.toString(packageInfo.versionCode);
            this.f5340s = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            this.f5336o = CommonUtils.m13038m(context);
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Error setting up app properties", e);
        }
        m7859B().m13073m();
        m7892a(this.f5336o, m7887b(context)).m7765a(str, this.f5337p);
    }

    protected Void m7897b() {
        m7911q();
        this.f5332k.m7987g();
        Object obj = 1;
        try {
            SettingsData b = Settings.m13293a().m13297b();
            if (b == null) {
                Fabric.m12905g().m12871c("Fabric", "Received null settings, skipping initialization!");
                m7912r();
                return null;
            }
            if (b.f8442d.f8411c) {
                obj = null;
                this.f5332k.m7984d();
                CreateReportSpiCall a = m7893a(b);
                if (a != null) {
                    new ReportUploader(a).m8055a(this.f5341t);
                } else {
                    Fabric.m12905g().m12871c("Fabric", "Unable to create a call to upload reports.");
                }
            }
            if (obj != null) {
                try {
                    Fabric.m12905g().m12867a("Fabric", "Crash reporting disabled.");
                } catch (Throwable e) {
                    Fabric.m12905g().m12874d("Fabric", "Problem encountered during Crashlytics initialization.", e);
                } finally {
                    m7912r();
                }
            }
            m7912r();
            return null;
        } catch (Throwable e2) {
            Throwable th = e2;
            Object obj2 = obj;
            Fabric.m12905g().m12874d("Fabric", "Error dealing with settings", th);
            obj = obj2;
        }
    }

    public String m7898c() {
        return "com.crashlytics.sdk.android:crashlytics";
    }

    public String m7899d() {
        return "2.2.3.41";
    }

    public Collection<? extends Kit> m7900e() {
        return this.f5329h;
    }

    public static Crashlytics m7891f() {
        try {
            return (Crashlytics) Fabric.m12897a(Crashlytics.class);
        } catch (IllegalStateException e) {
            Fabric.m12905g().m12874d("Fabric", "Crashlytics must be initialized by calling Fabric.with(Context) prior to calling Crashlytics.getInstance()", null);
            throw e;
        }
    }

    public static void m7882a(Throwable th) {
        if (!m7876I()) {
            Crashlytics f = m7891f();
            if (!m7885a("prior to logging exceptions.", f)) {
                return;
            }
            if (th == null) {
                Fabric.m12905g().m12866a(5, "Fabric", "Crashlytics is ignoring a request to log a null exception.");
            } else {
                f.f5332k.m7979a(Thread.currentThread(), th);
            }
        }
    }

    public static void m7881a(String str) {
        if (!m7876I()) {
            m7891f().f5333l = m7890e(str);
        }
    }

    public static void m7886b(String str) {
        if (!m7876I()) {
            m7891f().f5335n = m7890e(str);
        }
    }

    private void m7875H() {
        Callable c05911 = new C05911(this);
        for (Task a : m7864G()) {
            c05911.m7837a(a);
        }
        Future submit = m7861D().m12915e().submit(c05911);
        Fabric.m12905g().m12867a("Fabric", "Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(4, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Crashlytics was interrupted during initialization.", e);
        } catch (Throwable e2) {
            Fabric.m12905g().m12874d("Fabric", "Problem encountered during Crashlytics initialization.", e2);
        } catch (Throwable e22) {
            Fabric.m12905g().m12874d("Fabric", "Crashlytics timed out during initialization.", e22);
        }
    }

    static void m7888c(String str) {
        Answers answers = (Answers) Fabric.m12897a(Answers.class);
        if (answers != null) {
            answers.m8110a(new LoggedException(str));
        }
    }

    static void m7889d(String str) {
        Answers answers = (Answers) Fabric.m12897a(Answers.class);
        if (answers != null) {
            answers.m8109a(new FatalException(str));
        }
    }

    Map<String, String> m7901g() {
        return Collections.unmodifiableMap(this.f5328g);
    }

    BuildIdValidator m7892a(String str, boolean z) {
        return new BuildIdValidator(str, z);
    }

    String m7902h() {
        return this.f5337p;
    }

    String m7903i() {
        return this.f5338q;
    }

    String m7904j() {
        return this.f5340s;
    }

    String m7905k() {
        return this.f5339r;
    }

    String m7906l() {
        return CommonUtils.m13023b(m7891f().m7860C(), "com.crashlytics.ApiEndpoint");
    }

    CrashlyticsUncaughtExceptionHandler m7907m() {
        return this.f5332k;
    }

    String m7908n() {
        return m7859B().m13061a() ? this.f5333l : null;
    }

    String m7909o() {
        return m7859B().m13061a() ? this.f5334m : null;
    }

    String m7910p() {
        return m7859B().m13061a() ? this.f5335n : null;
    }

    void m7911q() {
        this.f5345x.m7921a(new C05922(this));
    }

    void m7912r() {
        this.f5345x.m7923b(new C05933(this));
    }

    boolean m7913s() {
        return ((Boolean) this.f5345x.m7921a(new C05944(this))).booleanValue();
    }

    SessionEventData m7914t() {
        if (this.f5346y != null) {
            return this.f5346y.m8220a();
        }
        return null;
    }

    File m7915u() {
        return new FileStoreImpl(this).m13245a();
    }

    boolean m7916v() {
        return ((Boolean) Settings.m13293a().m13296a(new C05955(this), Boolean.valueOf(false))).booleanValue();
    }

    boolean m7917w() {
        return new PreferenceStoreImpl(this).m13247a().getBoolean("always_send_reports_opt_in", false);
    }

    @SuppressLint({"CommitPrefEdits"})
    void m7894a(boolean z) {
        PreferenceStore preferenceStoreImpl = new PreferenceStoreImpl(this);
        preferenceStoreImpl.m13248a(preferenceStoreImpl.m13249b().putBoolean("always_send_reports_opt_in", z));
    }

    boolean m7918x() {
        return ((Boolean) Settings.m13293a().m13296a(new C05966(this), Boolean.valueOf(true))).booleanValue();
    }

    CreateReportSpiCall m7893a(SettingsData settingsData) {
        if (settingsData != null) {
            return new DefaultCreateReportSpiCall(this, m7906l(), settingsData.f8439a.f8396d, this.f5344w);
        }
        return null;
    }

    private boolean m7883a(Activity activity, PromptSettingsData promptSettingsData) {
        DialogStringResolver dialogStringResolver = new DialogStringResolver(activity, promptSettingsData);
        OptInLatch optInLatch = new OptInLatch();
        activity.runOnUiThread(new C06007(this, activity, optInLatch, dialogStringResolver, promptSettingsData));
        Fabric.m12905g().m12867a("Fabric", "Waiting for user opt-in.");
        optInLatch.m7857b();
        return optInLatch.m7856a();
    }

    SessionSettingsData m7919y() {
        SettingsData b = Settings.m13293a().m13297b();
        return b == null ? null : b.f8440b;
    }

    private boolean m7887b(Context context) {
        return CommonUtils.m13019a(context, "com.crashlytics.RequireBuildId", true);
    }

    private static boolean m7885a(String str, Crashlytics crashlytics) {
        if (crashlytics != null && crashlytics.f5332k != null) {
            return true;
        }
        Fabric.m12905g().m12874d("Fabric", "Crashlytics must be initialized by calling Fabric.with(Context) " + str, null);
        return false;
    }

    private static String m7890e(String str) {
        if (str == null) {
            return str;
        }
        str = str.trim();
        if (str.length() > 1024) {
            return str.substring(0, 1024);
        }
        return str;
    }

    private static boolean m7876I() {
        Crashlytics f = m7891f();
        return f == null || f.f5342u;
    }

    private int m7877a(float f, int i) {
        return (int) (((float) i) * f);
    }
}
