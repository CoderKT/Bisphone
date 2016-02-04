package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.jivesoftware.smack.util.StringUtils;

public class CommonUtils {
    public static final Comparator<File> f8221a;
    private static Boolean f8222b;
    private static final char[] f8223c;
    private static long f8224d;
    private static Boolean f8225e;

    /* renamed from: io.fabric.sdk.android.services.common.CommonUtils.1 */
    final class C09551 implements Comparator<File> {
        C09551() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m12995a((File) obj, (File) obj2);
        }

        public int m12995a(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    }

    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> f8219k;

        static {
            f8219k = new HashMap(4);
            f8219k.put("armeabi-v7a", ARMV7);
            f8219k.put("armeabi", ARMV6);
            f8219k.put("x86", X86_32);
        }

        static Architecture m12996a() {
            Object obj = Build.CPU_ABI;
            if (TextUtils.isEmpty(obj)) {
                Fabric.m12905g().m12867a("Fabric", "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = (Architecture) f8219k.get(obj.toLowerCase(Locale.US));
            if (architecture == null) {
                return UNKNOWN;
            }
            return architecture;
        }
    }

    static {
        f8222b = null;
        f8223c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        f8224d = -1;
        f8225e = null;
        f8221a = new C09551();
    }

    public static SharedPreferences m13002a(Context context) {
        return context.getSharedPreferences("com.crashlytics.prefs", 0);
    }

    public static String m13004a(File file, String str) {
        Throwable e;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            Closeable bufferedReader;
            try {
                String[] split;
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        CharSequence readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                str2 = split[1];
                m13016a(bufferedReader, "Failed to close system file reader.");
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                try {
                    Fabric.m12905g().m12874d("Fabric", "Error parsing " + file, e);
                    m13016a(bufferedReader, "Failed to close system file reader.");
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    m13016a(bufferedReader, "Failed to close system file reader.");
                    throw th;
                }
            } catch (Throwable e4) {
                bufferedReader = null;
                th = e4;
                m13016a(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
        }
        return str2;
    }

    public static int m12997a() {
        return Architecture.m12996a().ordinal();
    }

    public static synchronized long m13020b() {
        long j;
        synchronized (CommonUtils.class) {
            if (f8224d == -1) {
                j = 0;
                Object a = m13004a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a)) {
                    String toUpperCase = a.toUpperCase(Locale.US);
                    try {
                        if (toUpperCase.endsWith("KB")) {
                            j = m13000a(toUpperCase, "KB", 1024);
                        } else if (toUpperCase.endsWith("MB")) {
                            j = m13000a(toUpperCase, "MB", 1048576);
                        } else if (toUpperCase.endsWith("GB")) {
                            j = m13000a(toUpperCase, "GB", 1073741824);
                        } else {
                            Fabric.m12905g().m12867a("Fabric", "Unexpected meminfo format while computing RAM: " + toUpperCase);
                        }
                    } catch (Throwable e) {
                        Fabric.m12905g().m12874d("Fabric", "Unexpected meminfo format while computing RAM: " + toUpperCase, e);
                    }
                }
                f8224d = j;
            }
            j = f8224d;
        }
        return j;
    }

    static long m13000a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static RunningAppProcessInfo m13001a(String str, Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String m13005a(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String m13007a(String str) {
        return m13008a(str, StringUtils.SHA1);
    }

    public static String m13024b(InputStream inputStream) {
        return m13006a(inputStream, StringUtils.SHA1);
    }

    private static String m13006a(InputStream inputStream, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(StringUtils.SHA1);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return m13009a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String m13010a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return m13009a(instance.digest());
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    private static String m13008a(String str, String str2) {
        return m13010a(str.getBytes(), str2);
    }

    public static String m13011a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
        }
        String append2 = stringBuilder.toString();
        return append2.length() > 0 ? m13007a(append2) : null;
    }

    public static long m13021b(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long m13022b(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (((long) statFs.getAvailableBlocks()) * blockSize);
    }

    public static float m13025c(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
    }

    public static boolean m13029d(Context context) {
        if (m13031f(context)) {
            return false;
        }
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null;
    }

    public static void m13014a(Context context, String str) {
        if (m13030e(context)) {
            Fabric.m12905g().m12867a("Fabric", str);
        }
    }

    public static void m13015a(Context context, String str, Throwable th) {
        if (m13030e(context)) {
            Fabric.m12905g().m12873d("Fabric", str);
        }
    }

    public static void m13013a(Context context, int i, String str, String str2) {
        if (m13030e(context)) {
            Fabric.m12905g().m12866a(i, "Fabric", str2);
        }
    }

    public static boolean m13030e(Context context) {
        if (f8222b == null) {
            f8222b = Boolean.valueOf(m13019a(context, "com.crashlytics.Trace", false));
        }
        return f8222b.booleanValue();
    }

    public static boolean m13019a(Context context, String str, boolean z) {
        if (context == null) {
            return z;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return z;
        }
        int a = m12998a(context, str, "bool");
        if (a > 0) {
            return resources.getBoolean(a);
        }
        int a2 = m12998a(context, str, "string");
        if (a2 > 0) {
            return Boolean.parseBoolean(context.getString(a2));
        }
        return z;
    }

    public static int m12998a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, m13035j(context));
    }

    public static boolean m13031f(Context context) {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    public static boolean m13032g(Context context) {
        boolean f = m13031f(context);
        String str = Build.TAGS;
        if ((!f && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (f || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean m13026c() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int m13033h(Context context) {
        int i = 0;
        if (m13031f(context)) {
            i = 1;
        }
        if (m13032g(context)) {
            i |= 2;
        }
        if (m13026c()) {
            return i | 4;
        }
        return i;
    }

    public static int m12999a(Context context, boolean z) {
        float c = m13025c(context);
        if (!z) {
            return 1;
        }
        if (z && ((double) c) >= 99.0d) {
            return 3;
        }
        if (!z || ((double) c) >= 99.0d) {
            return 0;
        }
        return 2;
    }

    @SuppressLint({"GetInstance"})
    public static Cipher m13012a(int i, String str) {
        if (str.length() < 32) {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        }
        Key secretKeySpec = new SecretKeySpec(str.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(i, secretKeySpec);
            return instance;
        } catch (Throwable e) {
            Fabric.m12905g().m12874d("Fabric", "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", e);
            throw new RuntimeException(e);
        }
    }

    public static String m13009a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = f8223c[i2 >>> 4];
            cArr[(i * 2) + 1] = f8223c[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean m13034i(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String m13023b(Context context, String str) {
        int a = m12998a(context, str, "string");
        if (a > 0) {
            return context.getString(a);
        }
        return "";
    }

    public static void m13016a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", str, e);
            }
        }
    }

    public static void m13017a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", str, e);
            }
        }
    }

    public static boolean m13028c(String str) {
        return str == null || str.length() == 0;
    }

    public static String m13003a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
    }

    public static String m13035j(Context context) {
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            return context.getResources().getResourcePackageName(i);
        }
        return context.getPackageName();
    }

    public static void m13018a(InputStream inputStream, OutputStream outputStream, byte[] bArr) {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String m13036k(Context context) {
        Throwable e;
        Throwable th;
        String str = null;
        Closeable openRawResource;
        try {
            openRawResource = context.getResources().openRawResource(m13037l(context));
            try {
                String b = m13024b((InputStream) openRawResource);
                if (!m13028c(b)) {
                    str = b;
                }
                m13016a(openRawResource, "Failed to close icon input stream.");
            } catch (Exception e2) {
                e = e2;
                try {
                    Fabric.m12905g().m12874d("Fabric", "Could not calculate hash for app icon.", e);
                    m13016a(openRawResource, "Failed to close icon input stream.");
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    m13016a(openRawResource, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            openRawResource = null;
            Fabric.m12905g().m12874d("Fabric", "Could not calculate hash for app icon.", e);
            m13016a(openRawResource, "Failed to close icon input stream.");
            return str;
        } catch (Throwable e4) {
            openRawResource = null;
            th = e4;
            m13016a(openRawResource, "Failed to close icon input stream.");
            throw th;
        }
        return str;
    }

    public static int m13037l(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String m13038m(Context context) {
        int a = m12998a(context, "io.fabric.android.build_id", "string");
        if (a == 0) {
            a = m12998a(context, "com.crashlytics.android.build_id", "string");
        }
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        Fabric.m12905g().m12867a("Fabric", "Build ID is: " + string);
        return string;
    }

    public static boolean m13027c(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean m13039n(Context context) {
        if (!m13027c(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
