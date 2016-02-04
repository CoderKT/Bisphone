package com.crashlytics.android;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.internal.models.SessionEventData;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.settings.SessionSettingsData;
import io.fabric.sdk.android.services.settings.Settings;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.OutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jivesoftware.smack.packet.XMPPError;

class CrashlyticsUncaughtExceptionHandler implements UncaughtExceptionHandler {
    static final FilenameFilter f5372a;
    static final Comparator<File> f5373b;
    static final Comparator<File> f5374c;
    static final FilenameFilter f5375d;
    private static final Pattern f5376e;
    private static final Map<String, String> f5377f;
    private final AtomicInteger f5378g;
    private final AtomicBoolean f5379h;
    private final UncaughtExceptionHandler f5380i;
    private final File f5381j;
    private final AtomicBoolean f5382k;
    private final BroadcastReceiver f5383l;
    private final BroadcastReceiver f5384m;
    private final CrashlyticsExecutorServiceWrapper f5385n;
    private final IdManager f5386o;
    private boolean f5387p;
    private final Crashlytics f5388q;
    private final LogFileManager f5389r;
    private final SessionDataWriter f5390s;

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.13 */
    class AnonymousClass13 implements FilenameFilter {
        final /* synthetic */ String f5356a;
        final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5357b;

        AnonymousClass13(CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler, String str) {
            this.f5357b = crashlyticsUncaughtExceptionHandler;
            this.f5356a = str;
        }

        public boolean accept(File file, String str) {
            return str.startsWith(this.f5356a);
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.14 */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ File f5358a;
        final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5359b;

        AnonymousClass14(CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler, File file) {
            this.f5359b = crashlyticsUncaughtExceptionHandler;
            this.f5358a = file;
        }

        public void run() {
            if (CommonUtils.m13039n(this.f5359b.f5388q.m7860C())) {
                Fabric.m12905g().m12867a("Fabric", "Attempting to send crash report at time of crash...");
                CreateReportSpiCall a = this.f5359b.f5388q.m7893a(Settings.m13293a().m13297b());
                if (a != null) {
                    new ReportUploader(a).m8056a(new SessionReport(this.f5358a, CrashlyticsUncaughtExceptionHandler.f5377f));
                }
            }
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.1 */
    final class C06031 implements FilenameFilter {
        C06031() {
        }

        public boolean accept(File file, String str) {
            return str.length() == ".cls".length() + 35 && str.endsWith(".cls");
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.2 */
    final class C06042 implements Comparator<File> {
        C06042() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m7936a((File) obj, (File) obj2);
        }

        public int m7936a(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.3 */
    final class C06053 implements Comparator<File> {
        C06053() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m7937a((File) obj, (File) obj2);
        }

        public int m7937a(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.4 */
    final class C06064 implements FilenameFilter {
        C06064() {
        }

        public boolean accept(File file, String str) {
            return CrashlyticsUncaughtExceptionHandler.f5376e.matcher(str).matches();
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.5 */
    class C06075 extends BroadcastReceiver {
        final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5360a;

        C06075(CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler) {
            this.f5360a = crashlyticsUncaughtExceptionHandler;
        }

        public void onReceive(Context context, Intent intent) {
            this.f5360a.f5387p = true;
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.6 */
    class C06086 extends BroadcastReceiver {
        final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5361a;

        C06086(CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler) {
            this.f5361a = crashlyticsUncaughtExceptionHandler;
        }

        public void onReceive(Context context, Intent intent) {
            this.f5361a.f5387p = false;
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.7 */
    class C06097 implements Callable<Void> {
        final /* synthetic */ Date f5362a;
        final /* synthetic */ Thread f5363b;
        final /* synthetic */ Throwable f5364c;
        final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5365d;

        C06097(CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler, Date date, Thread thread, Throwable th) {
            this.f5365d = crashlyticsUncaughtExceptionHandler;
            this.f5362a = date;
            this.f5363b = thread;
            this.f5364c = th;
        }

        public /* synthetic */ Object call() {
            return m7938a();
        }

        public Void m7938a() {
            this.f5365d.m7955a(this.f5362a, this.f5363b, this.f5364c);
            return null;
        }
    }

    /* renamed from: com.crashlytics.android.CrashlyticsUncaughtExceptionHandler.9 */
    class C06109 implements Runnable {
        final /* synthetic */ Date f5366a;
        final /* synthetic */ Thread f5367b;
        final /* synthetic */ Throwable f5368c;
        final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5369d;

        C06109(CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler, Date date, Thread thread, Throwable th) {
            this.f5369d = crashlyticsUncaughtExceptionHandler;
            this.f5366a = date;
            this.f5367b = thread;
            this.f5368c = th;
        }

        public void run() {
            if (!this.f5369d.f5382k.get()) {
                this.f5369d.m7966c(this.f5366a, this.f5367b, this.f5368c);
            }
        }
    }

    class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return !CrashlyticsUncaughtExceptionHandler.f5372a.accept(file, str) && CrashlyticsUncaughtExceptionHandler.f5376e.matcher(str).matches();
        }
    }

    class FileNameContainsFilter implements FilenameFilter {
        private final String f5370a;

        public FileNameContainsFilter(String str) {
            this.f5370a = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.f5370a) && !str.endsWith(".cls_temp");
        }
    }

    class SessionPartFileFilter implements FilenameFilter {
        private final String f5371a;

        public SessionPartFileFilter(String str) {
            this.f5371a = str;
        }

        public boolean accept(File file, String str) {
            if (str.equals(this.f5371a + ".cls") || !str.contains(this.f5371a) || str.endsWith(".cls_temp")) {
                return false;
            }
            return true;
        }
    }

    static {
        f5372a = new C06031();
        f5373b = new C06042();
        f5374c = new C06053();
        f5375d = new C06064();
        f5376e = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
        f5377f = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    }

    CrashlyticsUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsListener crashlyticsListener, CrashlyticsExecutorServiceWrapper crashlyticsExecutorServiceWrapper, IdManager idManager, SessionDataWriter sessionDataWriter, Crashlytics crashlytics) {
        this.f5378g = new AtomicInteger(0);
        this.f5379h = new AtomicBoolean(false);
        this.f5380i = uncaughtExceptionHandler;
        this.f5385n = crashlyticsExecutorServiceWrapper;
        this.f5386o = idManager;
        this.f5388q = crashlytics;
        this.f5390s = sessionDataWriter;
        this.f5382k = new AtomicBoolean(false);
        this.f5381j = crashlytics.m7915u();
        this.f5389r = new LogFileManager(crashlytics.m7860C(), this.f5381j);
        m7947a(crashlyticsListener);
        this.f5384m = new C06075(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        this.f5383l = new C06086(this);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        Context C = crashlytics.m7860C();
        C.registerReceiver(this.f5384m, intentFilter);
        C.registerReceiver(this.f5383l, intentFilter2);
        this.f5379h.set(true);
    }

    public synchronized void uncaughtException(Thread thread, Throwable th) {
        this.f5382k.set(true);
        try {
            Fabric.m12905g().m12867a("Fabric", "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
            if (!this.f5379h.getAndSet(true)) {
                Fabric.m12905g().m12867a("Fabric", "Unregistering power receivers.");
                Context C = this.f5388q.m7860C();
                C.unregisterReceiver(this.f5384m);
                C.unregisterReceiver(this.f5383l);
            }
            this.f5385n.m7921a(new C06097(this, new Date(), thread, th));
            Fabric.m12905g().m12867a("Fabric", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.f5380i.uncaughtException(thread, th);
            this.f5382k.set(false);
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "An error occurred in the uncaught exception handler", e);
            Fabric.m12905g().m12867a("Fabric", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.f5380i.uncaughtException(thread, th);
            this.f5382k.set(false);
        } catch (Throwable th2) {
            Fabric.m12905g().m12867a("Fabric", "Crashlytics completed exception processing. Invoking default exception handler.");
            this.f5380i.uncaughtException(thread, th);
            this.f5382k.set(false);
        }
    }

    private void m7955a(Date date, Thread thread, Throwable th) {
        m7961b(date, thread, th);
        m7975l();
        m7974k();
        m7986f();
        if (!this.f5388q.m7916v()) {
            m7978o();
        }
    }

    boolean m7981a() {
        return this.f5382k.get();
    }

    private void m7947a(CrashlyticsListener crashlyticsListener) {
        Fabric.m12905g().m12867a("Fabric", "Checking for previous crash marker.");
        File file = new File(this.f5388q.m7915u(), "crash_marker");
        if (file.exists()) {
            file.delete();
            if (crashlyticsListener != null) {
                try {
                    crashlyticsListener.m7924a();
                } catch (Throwable e) {
                    Fabric.m12905g().m12874d("Fabric", "Exception thrown by CrashlyticsListener while notifying of previous crash.", e);
                }
            }
        }
    }

    void m7979a(Thread thread, Throwable th) {
        this.f5385n.m7922a(new C06109(this, new Date(), thread, th));
    }

    private void m7961b(Date date, Thread thread, Throwable th) {
        Closeable clsFileOutputStream;
        Throwable e;
        Flushable flushable = null;
        try {
            Closeable closeable;
            new File(this.f5381j, "crash_marker").createNewFile();
            String j = m7973j();
            if (j != null) {
                Crashlytics.m7889d(j);
                clsFileOutputStream = new ClsFileOutputStream(this.f5381j, j + "SessionCrash");
                try {
                    flushable = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                    m7945a(flushable, date, thread, th, "crash", true);
                    closeable = clsFileOutputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Fabric.m12905g().m12874d("Fabric", "An error occurred in the fatal exception logger", e);
                        ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
                        CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m13016a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th2) {
                        e = th2;
                        CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m13016a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
                        throw e;
                    }
                }
            }
            Fabric.m12905g().m12874d("Fabric", "Tried to write a fatal exception while no session was open.", null);
            closeable = null;
            CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            Fabric.m12905g().m12874d("Fabric", "An error occurred in the fatal exception logger", e);
            ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
            CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable th3) {
            e = th3;
            clsFileOutputStream = null;
            CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw e;
        }
    }

    private void m7950a(SessionEventData sessionEventData) {
        Throwable th;
        Closeable closeable;
        Flushable flushable;
        Throwable th2;
        Flushable flushable2;
        Flushable flushable3 = null;
        try {
            Closeable clsFileOutputStream;
            String j = m7973j();
            if (j != null) {
                Crashlytics.m7889d(j);
                clsFileOutputStream = new ClsFileOutputStream(this.f5381j, j + "SessionCrash");
                try {
                    flushable3 = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                } catch (Throwable e) {
                    th = e;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    try {
                        Fabric.m12905g().m12874d("Fabric", "An error occurred in the native crash logger", th2);
                        ExceptionUtils.m8005a(th2, (OutputStream) closeable);
                        CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
                    } catch (Throwable th3) {
                        th2 = th3;
                        CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                        CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
                        throw th2;
                    }
                } catch (Throwable e2) {
                    th = e2;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
                    throw th2;
                }
                try {
                    NativeCrashWriter.m8039a(sessionEventData, flushable3);
                } catch (Throwable e22) {
                    th = e22;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    Fabric.m12905g().m12874d("Fabric", "An error occurred in the native crash logger", th2);
                    ExceptionUtils.m8005a(th2, (OutputStream) closeable);
                    CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
                } catch (Throwable e222) {
                    th = e222;
                    closeable = clsFileOutputStream;
                    flushable = flushable3;
                    th2 = th;
                    CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
                    throw th2;
                }
            }
            Fabric.m12905g().m12874d("Fabric", "Tried to write a native crash while no session was open.", null);
            Object obj = flushable3;
            CommonUtils.m13017a(flushable3, "Failed to flush to session begin file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Throwable e3) {
            closeable = flushable3;
            flushable2 = flushable3;
            th2 = e3;
            flushable = flushable2;
            Fabric.m12905g().m12874d("Fabric", "An error occurred in the native crash logger", th2);
            ExceptionUtils.m8005a(th2, (OutputStream) closeable);
            CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
        } catch (Throwable e32) {
            closeable = flushable3;
            flushable2 = flushable3;
            th2 = e32;
            flushable = flushable2;
            CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m13016a(closeable, "Failed to close fatal exception file output stream.");
            throw th2;
        }
    }

    void m7982b() {
        this.f5385n.m7923b(new Callable<Void>() {
            final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5353a;

            {
                this.f5353a = r1;
            }

            public /* synthetic */ Object call() {
                return m7934a();
            }

            public Void m7934a() {
                if (!this.f5353a.m7983c()) {
                    this.f5353a.m7974k();
                }
                return null;
            }
        });
    }

    private String m7973j() {
        File[] a = m7958a(new FileNameContainsFilter("BeginSession"));
        Arrays.sort(a, f5373b);
        return a.length > 0 ? m7939a(a[0]) : null;
    }

    private String m7939a(File file) {
        return file.getName().substring(0, 35);
    }

    boolean m7983c() {
        return m7985e().length > 0;
    }

    boolean m7984d() {
        return ((Boolean) this.f5385n.m7921a(new Callable<Boolean>() {
            final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5354a;

            {
                this.f5354a = r1;
            }

            public /* synthetic */ Object call() {
                return m7935a();
            }

            public Boolean m7935a() {
                if (this.f5354a.f5382k.get()) {
                    Fabric.m12905g().m12867a("Fabric", "Skipping session finalization because a crash has already occurred.");
                    return Boolean.valueOf(false);
                }
                SessionEventData t = this.f5354a.f5388q.m7914t();
                if (t != null) {
                    this.f5354a.m7950a(t);
                }
                this.f5354a.m7975l();
                this.f5354a.m7974k();
                Fabric.m12905g().m12867a("Fabric", "Open sessions were closed and a new session was opened.");
                return Boolean.valueOf(true);
            }
        })).booleanValue();
    }

    private void m7974k() {
        Date date = new Date();
        String clsuuid = new CLSUUID(this.f5386o).toString();
        Fabric.m12905g().m12867a("Fabric", "Opening an new session with ID " + clsuuid);
        m7954a(clsuuid, date);
        m7965c(clsuuid);
        m7968d(clsuuid);
        m7969e(clsuuid);
    }

    private void m7975l() {
        m7941a(8);
        String j = m7973j();
        if (j != null) {
            m7970f(j);
            SessionSettingsData y = this.f5388q.m7919y();
            if (y != null) {
                int i = y.f8426c;
                Fabric.m12905g().m12867a("Fabric", "Closing all open sessions.");
                File[] e = m7985e();
                if (e != null && e.length > 0) {
                    for (File file : e) {
                        String a = m7939a(file);
                        Fabric.m12905g().m12867a("Fabric", "Closing session: " + a);
                        m7951a(file, a, i);
                    }
                    return;
                }
                return;
            }
            Fabric.m12905g().m12867a("Fabric", "Unable to close session. Settings are not loaded.");
            return;
        }
        Fabric.m12905g().m12867a("Fabric", "No open sessions exist.");
    }

    private void m7942a(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.m7777a();
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    private void m7952a(String str) {
        for (File delete : m7962b(str)) {
            delete.delete();
        }
    }

    private File[] m7962b(String str) {
        return m7958a(new SessionPartFileFilter(str));
    }

    private File[] m7976m() {
        return m7958a(f5372a);
    }

    File[] m7985e() {
        return m7958a(new FileNameContainsFilter("BeginSession"));
    }

    private File[] m7958a(FilenameFilter filenameFilter) {
        return m7963b(this.f5381j.listFiles(filenameFilter));
    }

    private File[] m7963b(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private void m7953a(String str, int i) {
        Utils.m8093a(this.f5381j, new FileNameContainsFilter(str + "SessionEvent"), i, f5374c);
    }

    void m7986f() {
        Utils.m8093a(this.f5381j, f5372a, 4, f5374c);
    }

    private void m7941a(int i) {
        int i2 = 0;
        Set hashSet = new HashSet();
        File[] e = m7985e();
        Arrays.sort(e, f5373b);
        int min = Math.min(i, e.length);
        for (int i3 = 0; i3 < min; i3++) {
            hashSet.add(m7939a(e[i3]));
        }
        File[] a = m7958a(new AnySessionPartFileFilter());
        int length = a.length;
        while (i2 < length) {
            File file = a[i2];
            Object name = file.getName();
            Matcher matcher = f5376e.matcher(name);
            matcher.matches();
            if (!hashSet.contains(matcher.group(1))) {
                Fabric.m12905g().m12867a("Fabric", "Trimming open session file: " + name);
                file.delete();
            }
            i2++;
        }
    }

    void m7987g() {
        this.f5385n.m7922a(new Runnable() {
            final /* synthetic */ CrashlyticsUncaughtExceptionHandler f5355a;

            {
                this.f5355a = r1;
            }

            public void run() {
                this.f5355a.m7980a(this.f5355a.m7958a(ClsFileOutputStream.f5293a));
            }
        });
    }

    void m7980a(File[] fileArr) {
        m7977n();
        for (File file : fileArr) {
            Fabric.m12905g().m12867a("Fabric", "Found invalid session part file: " + file);
            String a = m7939a(file);
            FilenameFilter anonymousClass13 = new AnonymousClass13(this, a);
            Fabric.m12905g().m12867a("Fabric", "Deleting all part files for invalid session: " + a);
            for (File file2 : m7958a(anonymousClass13)) {
                Fabric.m12905g().m12867a("Fabric", "Deleting session file: " + file2);
                file2.delete();
            }
        }
    }

    private void m7977n() {
        File file = new File(this.f5388q.m7915u(), "invalidClsFiles");
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
            file.delete();
        }
    }

    private void m7954a(String str, Date date) {
        Throwable e;
        OutputStream outputStream;
        Flushable flushable = null;
        Closeable clsFileOutputStream;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.f5381j, str + "BeginSession");
            try {
                flushable = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                this.f5390s.m8084a((CodedOutputStream) flushable, str, String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{this.f5388q.m7899d()}), date.getTime() / 1000);
                CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                CommonUtils.m13016a(clsFileOutputStream, "Failed to close begin session file.");
            } catch (Exception e2) {
                e = e2;
                Closeable closeable = clsFileOutputStream;
                try {
                    ExceptionUtils.m8005a(e, outputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    clsFileOutputStream = outputStream;
                    CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                    CommonUtils.m13016a(clsFileOutputStream, "Failed to close begin session file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
                CommonUtils.m13016a(clsFileOutputStream, "Failed to close begin session file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            outputStream = null;
            ExceptionUtils.m8005a(e, outputStream);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            clsFileOutputStream = null;
            CommonUtils.m13017a(flushable, "Failed to flush to session begin file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close begin session file.");
            throw e;
        }
    }

    private void m7965c(String str) {
        Closeable clsFileOutputStream;
        Throwable e;
        OutputStream outputStream;
        Flushable flushable = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.f5381j, str + "SessionApp");
            try {
                flushable = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                this.f5390s.m8086a((CodedOutputStream) flushable, this.f5388q.m7902h(), this.f5388q.m7905k(), this.f5388q.m7904j(), this.f5386o.m13062b(), DeliveryMechanism.m13042a(this.f5388q.m7903i()).m13043a());
                CommonUtils.m13017a(flushable, "Failed to flush to session app file.");
                CommonUtils.m13016a(clsFileOutputStream, "Failed to close session app file.");
            } catch (Exception e2) {
                e = e2;
                Closeable closeable = clsFileOutputStream;
                try {
                    ExceptionUtils.m8005a(e, outputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    clsFileOutputStream = outputStream;
                    CommonUtils.m13017a(flushable, "Failed to flush to session app file.");
                    CommonUtils.m13016a(clsFileOutputStream, "Failed to close session app file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m13017a(flushable, "Failed to flush to session app file.");
                CommonUtils.m13016a(clsFileOutputStream, "Failed to close session app file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            outputStream = null;
            ExceptionUtils.m8005a(e, outputStream);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            clsFileOutputStream = null;
            CommonUtils.m13017a(flushable, "Failed to flush to session app file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close session app file.");
            throw e;
        }
    }

    private void m7968d(String str) {
        Closeable clsFileOutputStream;
        Throwable e;
        Flushable flushable = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.f5381j, str + "SessionOS");
            try {
                flushable = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                this.f5390s.m8087a((CodedOutputStream) flushable, CommonUtils.m13032g(this.f5388q.m7860C()));
                CommonUtils.m13017a(flushable, "Failed to flush to session OS file.");
                CommonUtils.m13016a(clsFileOutputStream, "Failed to close session OS file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    CommonUtils.m13017a(flushable, "Failed to flush to session OS file.");
                    CommonUtils.m13016a(clsFileOutputStream, "Failed to close session OS file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            clsFileOutputStream = null;
            CommonUtils.m13017a(flushable, "Failed to flush to session OS file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close session OS file.");
            throw e;
        }
    }

    private void m7969e(String str) {
        Throwable e;
        OutputStream outputStream;
        OutputStream outputStream2 = null;
        Flushable flushable = null;
        try {
            OutputStream clsFileOutputStream = new ClsFileOutputStream(this.f5381j, str + "SessionDevice");
            try {
                flushable = CodedOutputStream.m7778a(clsFileOutputStream);
                Context C = this.f5388q.m7860C();
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                this.f5390s.m8083a((CodedOutputStream) flushable, this.f5386o.m13066f(), CommonUtils.m12997a(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.m13020b(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()), CommonUtils.m13031f(C), this.f5386o.m13067g(), CommonUtils.m13033h(C), Build.MANUFACTURER, Build.PRODUCT);
                CommonUtils.m13017a(flushable, "Failed to flush session device info.");
                CommonUtils.m13016a((Closeable) clsFileOutputStream, "Failed to close session device file.");
            } catch (Exception e2) {
                e = e2;
                outputStream2 = clsFileOutputStream;
                try {
                    ExceptionUtils.m8005a(e, outputStream2);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    outputStream = outputStream2;
                    CommonUtils.m13017a(flushable, "Failed to flush session device info.");
                    CommonUtils.m13016a((Closeable) outputStream, "Failed to close session device file.");
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                CommonUtils.m13017a(flushable, "Failed to flush session device info.");
                CommonUtils.m13016a((Closeable) outputStream, "Failed to close session device file.");
                throw e;
            }
        } catch (Exception e3) {
            e = e3;
            ExceptionUtils.m8005a(e, outputStream2);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            outputStream = null;
            CommonUtils.m13017a(flushable, "Failed to flush session device info.");
            CommonUtils.m13016a((Closeable) outputStream, "Failed to close session device file.");
            throw e;
        }
    }

    private void m7970f(String str) {
        Throwable e;
        Flushable flushable = null;
        Closeable clsFileOutputStream;
        try {
            clsFileOutputStream = new ClsFileOutputStream(this.f5381j, str + "SessionUser");
            try {
                flushable = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                String n = this.f5388q.m7908n();
                String p = this.f5388q.m7910p();
                String o = this.f5388q.m7909o();
                if (n == null && p == null && o == null) {
                    CommonUtils.m13017a(flushable, "Failed to flush session user file.");
                    CommonUtils.m13016a(clsFileOutputStream, "Failed to close session user file.");
                    return;
                }
                this.f5390s.m8085a((CodedOutputStream) flushable, n, p, o);
                CommonUtils.m13017a(flushable, "Failed to flush session user file.");
                CommonUtils.m13016a(clsFileOutputStream, "Failed to close session user file.");
            } catch (Exception e2) {
                e = e2;
                try {
                    ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    CommonUtils.m13017a(flushable, "Failed to flush session user file.");
                    CommonUtils.m13016a(clsFileOutputStream, "Failed to close session user file.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            clsFileOutputStream = null;
            CommonUtils.m13017a(flushable, "Failed to flush session user file.");
            CommonUtils.m13016a(clsFileOutputStream, "Failed to close session user file.");
            throw e;
        }
    }

    private void m7945a(CodedOutputStream codedOutputStream, Date date, Thread thread, Throwable th, String str, boolean z) {
        Thread[] threadArr;
        Map map;
        Context C = this.f5388q.m7860C();
        long time = date.getTime() / 1000;
        float c = CommonUtils.m13025c(C);
        int a = CommonUtils.m12999a(C, this.f5387p);
        boolean d = CommonUtils.m13029d(C);
        int i = C.getResources().getConfiguration().orientation;
        long b = CommonUtils.m13020b() - CommonUtils.m13021b(C);
        long b2 = CommonUtils.m13022b(Environment.getDataDirectory().getPath());
        RunningAppProcessInfo a2 = CommonUtils.m13001a(C.getPackageName(), C);
        List linkedList = new LinkedList();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (z) {
            Map allStackTraces = Thread.getAllStackTraces();
            threadArr = new Thread[allStackTraces.size()];
            int i2 = 0;
            for (Entry entry : allStackTraces.entrySet()) {
                threadArr[i2] = (Thread) entry.getKey();
                linkedList.add(entry.getValue());
                i2++;
            }
        } else {
            threadArr = new Thread[0];
        }
        if (CommonUtils.m13019a(C, "com.crashlytics.CollectCustomKeys", true)) {
            Map g = this.f5388q.m7901g();
            if (g == null || g.size() <= 1) {
                map = g;
            } else {
                Map treeMap = new TreeMap(g);
            }
        } else {
            map = new TreeMap();
        }
        CodedOutputStream codedOutputStream2 = codedOutputStream;
        Thread thread2 = thread;
        Throwable th2 = th;
        String str2 = str;
        this.f5390s.m8082a(codedOutputStream2, time, thread2, th2, str2, threadArr, c, a, d, i, b, b2, a2, linkedList, stackTrace, this.f5389r, map);
    }

    private void m7966c(Date date, Thread thread, Throwable th) {
        Throwable e;
        Closeable closeable;
        Flushable flushable = null;
        String j = m7973j();
        if (j != null) {
            Crashlytics.m7888c(j);
            try {
                Fabric.m12905g().m12867a("Fabric", "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
                Closeable clsFileOutputStream = new ClsFileOutputStream(this.f5381j, j + "SessionEvent" + CommonUtils.m13003a(this.f5378g.getAndIncrement()));
                try {
                    flushable = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream);
                    m7945a(flushable, date, thread, th, XMPPError.ERROR, false);
                    CommonUtils.m13017a(flushable, "Failed to flush to non-fatal file.");
                    CommonUtils.m13016a(clsFileOutputStream, "Failed to close non-fatal file output stream.");
                } catch (Exception e2) {
                    e = e2;
                    closeable = clsFileOutputStream;
                    try {
                        Fabric.m12905g().m12874d("Fabric", "An error occurred in the non-fatal exception logger", e);
                        ExceptionUtils.m8005a(e, (OutputStream) closeable);
                        CommonUtils.m13017a(flushable, "Failed to flush to non-fatal file.");
                        CommonUtils.m13016a(closeable, "Failed to close non-fatal file output stream.");
                        m7953a(j, 64);
                        return;
                    } catch (Throwable th2) {
                        e = th2;
                        CommonUtils.m13017a(flushable, "Failed to flush to non-fatal file.");
                        CommonUtils.m13016a(closeable, "Failed to close non-fatal file output stream.");
                        throw e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                    closeable = clsFileOutputStream;
                    CommonUtils.m13017a(flushable, "Failed to flush to non-fatal file.");
                    CommonUtils.m13016a(closeable, "Failed to close non-fatal file output stream.");
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                closeable = null;
                Fabric.m12905g().m12874d("Fabric", "An error occurred in the non-fatal exception logger", e);
                ExceptionUtils.m8005a(e, (OutputStream) closeable);
                CommonUtils.m13017a(flushable, "Failed to flush to non-fatal file.");
                CommonUtils.m13016a(closeable, "Failed to close non-fatal file output stream.");
                m7953a(j, 64);
                return;
            } catch (Throwable th4) {
                e = th4;
                closeable = null;
                CommonUtils.m13017a(flushable, "Failed to flush to non-fatal file.");
                CommonUtils.m13016a(closeable, "Failed to close non-fatal file output stream.");
                throw e;
            }
            try {
                m7953a(j, 64);
                return;
            } catch (Throwable e4) {
                Fabric.m12905g().m12874d("Fabric", "An error occurred when trimming non-fatal files.", e4);
                return;
            }
        }
        Fabric.m12905g().m12874d("Fabric", "Tried to write a non-fatal exception while no session was open.", null);
    }

    private void m7951a(File file, String str, int i) {
        boolean z;
        Flushable a;
        Throwable e;
        Closeable closeable;
        ClsFileOutputStream clsFileOutputStream = null;
        Fabric.m12905g().m12867a("Fabric", "Collecting session parts for ID " + str);
        File[] a2 = m7958a(new FileNameContainsFilter(str + "SessionCrash"));
        boolean z2 = a2 != null && a2.length > 0;
        Fabric.m12905g().m12867a("Fabric", String.format(Locale.US, "Session %s has fatal exception: %s", new Object[]{str, Boolean.valueOf(z2)}));
        File[] a3 = m7958a(new FileNameContainsFilter(str + "SessionEvent"));
        if (a3 == null || a3.length <= 0) {
            z = false;
        } else {
            z = true;
        }
        Fabric.m12905g().m12867a("Fabric", String.format(Locale.US, "Session %s has non-fatal exceptions: %s", new Object[]{str, Boolean.valueOf(z)}));
        if (z2 || z) {
            Closeable clsFileOutputStream2;
            try {
                clsFileOutputStream2 = new ClsFileOutputStream(this.f5381j, str);
                try {
                    a = CodedOutputStream.m7778a((OutputStream) clsFileOutputStream2);
                } catch (Exception e2) {
                    e = e2;
                    a = null;
                    closeable = clsFileOutputStream2;
                    try {
                        Fabric.m12905g().m12874d("Fabric", "Failed to write session file for session ID: " + str, e);
                        ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
                        CommonUtils.m13017a(a, "Error flushing session file stream");
                        m7942a(clsFileOutputStream);
                        Fabric.m12905g().m12867a("Fabric", "Removing session part files for ID " + str);
                        m7952a(str);
                    } catch (Throwable th) {
                        e = th;
                        Object obj = clsFileOutputStream;
                        CommonUtils.m13017a(a, "Error flushing session file stream");
                        CommonUtils.m13016a(clsFileOutputStream2, "Failed to close CLS file");
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    a = null;
                    CommonUtils.m13017a(a, "Error flushing session file stream");
                    CommonUtils.m13016a(clsFileOutputStream2, "Failed to close CLS file");
                    throw e;
                }
                try {
                    Fabric.m12905g().m12867a("Fabric", "Collecting SessionStart data for session ID " + str);
                    m7943a((CodedOutputStream) a, file);
                    a.m7806a(4, new Date().getTime() / 1000);
                    a.m7809a(5, z2);
                    m7944a((CodedOutputStream) a, str);
                    if (z) {
                        File[] a4;
                        if (a3.length > i) {
                            Fabric.m12905g().m12867a("Fabric", String.format(Locale.US, "Trimming down to %d logged exceptions.", new Object[]{Integer.valueOf(i)}));
                            m7953a(str, i);
                            a4 = m7958a(new FileNameContainsFilter(str + "SessionEvent"));
                        } else {
                            a4 = a3;
                        }
                        m7946a((CodedOutputStream) a, a4, str);
                    }
                    if (z2) {
                        m7943a((CodedOutputStream) a, a2[0]);
                    }
                    a.m7818b(11, 1);
                    a.m7820c(12, 3);
                    CommonUtils.m13017a(a, "Error flushing session file stream");
                    CommonUtils.m13016a(clsFileOutputStream2, "Failed to close CLS file");
                } catch (Exception e3) {
                    e = e3;
                    closeable = clsFileOutputStream2;
                    Fabric.m12905g().m12874d("Fabric", "Failed to write session file for session ID: " + str, e);
                    ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
                    CommonUtils.m13017a(a, "Error flushing session file stream");
                    m7942a(clsFileOutputStream);
                    Fabric.m12905g().m12867a("Fabric", "Removing session part files for ID " + str);
                    m7952a(str);
                } catch (Throwable th3) {
                    e = th3;
                    CommonUtils.m13017a(a, "Error flushing session file stream");
                    CommonUtils.m13016a(clsFileOutputStream2, "Failed to close CLS file");
                    throw e;
                }
            } catch (Exception e4) {
                e = e4;
                a = null;
                Fabric.m12905g().m12874d("Fabric", "Failed to write session file for session ID: " + str, e);
                ExceptionUtils.m8005a(e, (OutputStream) clsFileOutputStream);
                CommonUtils.m13017a(a, "Error flushing session file stream");
                m7942a(clsFileOutputStream);
                Fabric.m12905g().m12867a("Fabric", "Removing session part files for ID " + str);
                m7952a(str);
            } catch (Throwable th4) {
                e = th4;
                a = null;
                clsFileOutputStream2 = null;
                CommonUtils.m13017a(a, "Error flushing session file stream");
                CommonUtils.m13016a(clsFileOutputStream2, "Failed to close CLS file");
                throw e;
            }
        }
        Fabric.m12905g().m12867a("Fabric", "No events present for session ID " + str);
        Fabric.m12905g().m12867a("Fabric", "Removing session part files for ID " + str);
        m7952a(str);
    }

    private void m7946a(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, CommonUtils.f8221a);
        for (File name : fileArr) {
            try {
                Fabric.m12905g().m12867a("Fabric", String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, name.getName()}));
                m7943a(codedOutputStream, name);
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Error writting non-fatal to session.", e);
            }
        }
    }

    private void m7944a(CodedOutputStream codedOutputStream, String str) {
        for (String str2 : new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"}) {
            File[] a = m7958a(new FileNameContainsFilter(str + str2));
            if (a.length == 0) {
                Fabric.m12905g().m12874d("Fabric", "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                Fabric.m12905g().m12867a("Fabric", "Collecting " + str2 + " data for session ID " + str);
                m7943a(codedOutputStream, a[0]);
            }
        }
    }

    private void m7943a(CodedOutputStream codedOutputStream, File file) {
        Throwable th;
        if (file.exists()) {
            byte[] bArr = new byte[((int) file.length())];
            Closeable fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                int i = 0;
                while (i < bArr.length) {
                    try {
                        int read = fileInputStream.read(bArr, i, bArr.length - i);
                        if (read < 0) {
                            break;
                        }
                        i += read;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                CommonUtils.m13016a(fileInputStream, "Failed to close file input stream.");
                codedOutputStream.m7815a(bArr);
                return;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                CommonUtils.m13016a(fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        }
        Fabric.m12905g().m12874d("Fabric", "Tried to include a file that doesn't exist: " + file.getName(), null);
    }

    private void m7978o() {
        for (File anonymousClass14 : m7976m()) {
            this.f5385n.m7922a(new AnonymousClass14(this, anonymousClass14));
        }
    }
}
