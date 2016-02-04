package com.crashlytics.android;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class SessionDataWriter {
    private static final ByteString f5442a;
    private RunningAppProcessInfo f5443b;
    private Thread[] f5444c;
    private List<StackTraceElement[]> f5445d;
    private StackTraceElement[] f5446e;
    private final Context f5447f;
    private final ByteString f5448g;
    private final ByteString f5449h;
    private final int f5450i;

    static {
        f5442a = ByteString.m7767a("0");
    }

    public SessionDataWriter(Context context, String str, String str2) {
        this.f5450i = 8;
        this.f5447f = context;
        this.f5448g = ByteString.m7767a(str2);
        this.f5449h = str == null ? null : ByteString.m7767a(str.replace("-", ""));
    }

    public void m8084a(CodedOutputStream codedOutputStream, String str, String str2, long j) {
        codedOutputStream.m7807a(1, ByteString.m7767a(str2));
        codedOutputStream.m7807a(2, ByteString.m7767a(str));
        codedOutputStream.m7806a(3, j);
    }

    public void m8086a(CodedOutputStream codedOutputStream, String str, String str2, String str3, String str4, int i) {
        ByteString a = ByteString.m7767a(str);
        ByteString a2 = ByteString.m7767a(str2);
        ByteString a3 = ByteString.m7767a(str3);
        ByteString a4 = ByteString.m7767a(str4);
        codedOutputStream.m7826i(7, 2);
        codedOutputStream.m7827k(m8061a(a, a2, a3, a4, i));
        codedOutputStream.m7807a(1, a);
        codedOutputStream.m7807a(2, a2);
        codedOutputStream.m7807a(3, a3);
        codedOutputStream.m7826i(5, 2);
        codedOutputStream.m7827k(m8057a());
        codedOutputStream.m7808a(1, new ApiKey().m12991a(this.f5447f));
        codedOutputStream.m7807a(6, a4);
        codedOutputStream.m7820c(10, i);
    }

    public void m8087a(CodedOutputStream codedOutputStream, boolean z) {
        ByteString a = ByteString.m7767a(VERSION.RELEASE);
        ByteString a2 = ByteString.m7767a(VERSION.CODENAME);
        codedOutputStream.m7826i(8, 2);
        codedOutputStream.m7827k(m8062a(a, a2, z));
        codedOutputStream.m7820c(1, 3);
        codedOutputStream.m7807a(2, a);
        codedOutputStream.m7807a(3, a2);
        codedOutputStream.m7809a(4, z);
    }

    public void m8083a(CodedOutputStream codedOutputStream, String str, int i, String str2, int i2, long j, long j2, boolean z, Map<DeviceIdentifierType, String> map, int i3, String str3, String str4) {
        ByteString a = ByteString.m7767a(str);
        ByteString a2 = m8071a(str2);
        ByteString a3 = m8071a(str4);
        ByteString a4 = m8071a(str3);
        codedOutputStream.m7826i(9, 2);
        codedOutputStream.m7827k(m8059a(i, a, a2, i2, j, j2, z, map, i3, a4, a3));
        codedOutputStream.m7807a(1, a);
        codedOutputStream.m7820c(3, i);
        codedOutputStream.m7807a(4, a2);
        codedOutputStream.m7818b(5, i2);
        codedOutputStream.m7806a(6, j);
        codedOutputStream.m7806a(7, j2);
        codedOutputStream.m7809a(10, z);
        for (Entry entry : map.entrySet()) {
            codedOutputStream.m7826i(11, 2);
            codedOutputStream.m7827k(m8063a((DeviceIdentifierType) entry.getKey(), (String) entry.getValue()));
            codedOutputStream.m7820c(1, ((DeviceIdentifierType) entry.getKey()).f8249h);
            codedOutputStream.m7807a(2, ByteString.m7767a((String) entry.getValue()));
        }
        codedOutputStream.m7818b(12, i3);
        if (a4 != null) {
            codedOutputStream.m7807a(13, a4);
        }
        if (a3 != null) {
            codedOutputStream.m7807a(14, a3);
        }
    }

    public void m8085a(CodedOutputStream codedOutputStream, String str, String str2, String str3) {
        if (str == null) {
            str = "";
        }
        ByteString a = ByteString.m7767a(str);
        ByteString a2 = m8071a(str2);
        ByteString a3 = m8071a(str3);
        int b = 0 + CodedOutputStream.m7784b(1, a);
        if (str2 != null) {
            b += CodedOutputStream.m7784b(2, a2);
        }
        if (str3 != null) {
            b += CodedOutputStream.m7784b(3, a3);
        }
        codedOutputStream.m7826i(6, 2);
        codedOutputStream.m7827k(b);
        codedOutputStream.m7807a(1, a);
        if (str2 != null) {
            codedOutputStream.m7807a(2, a2);
        }
        if (str3 != null) {
            codedOutputStream.m7807a(3, a3);
        }
    }

    public void m8082a(CodedOutputStream codedOutputStream, long j, Thread thread, Throwable th, String str, Thread[] threadArr, float f, int i, boolean z, int i2, long j2, long j3, RunningAppProcessInfo runningAppProcessInfo, List<StackTraceElement[]> list, StackTraceElement[] stackTraceElementArr, LogFileManager logFileManager, Map<String, String> map) {
        this.f5443b = runningAppProcessInfo;
        this.f5445d = list;
        this.f5446e = stackTraceElementArr;
        this.f5444c = threadArr;
        ByteString a = logFileManager.m8010a();
        if (a == null) {
            Fabric.m12905g().m12867a("Fabric", "No log data to include with this event.");
        }
        logFileManager.m8011b();
        codedOutputStream.m7826i(10, 2);
        codedOutputStream.m7827k(m8068a(thread, th, str, j, (Map) map, f, i, z, i2, j2, j3, a));
        codedOutputStream.m7806a(1, j);
        codedOutputStream.m7807a(2, ByteString.m7767a(str));
        m8076a(codedOutputStream, thread, th, i2, (Map) map);
        m8072a(codedOutputStream, f, i, z, i2, j2, j3);
        m8074a(codedOutputStream, a);
    }

    private void m8076a(CodedOutputStream codedOutputStream, Thread thread, Throwable th, int i, Map<String, String> map) {
        codedOutputStream.m7826i(3, 2);
        codedOutputStream.m7827k(m8067a(thread, th, i, (Map) map));
        m8075a(codedOutputStream, thread, th);
        if (!(map == null || map.isEmpty())) {
            m8079a(codedOutputStream, (Map) map);
        }
        if (this.f5443b != null) {
            codedOutputStream.m7809a(3, this.f5443b.importance != 100);
        }
        codedOutputStream.m7818b(4, i);
    }

    private void m8075a(CodedOutputStream codedOutputStream, Thread thread, Throwable th) {
        codedOutputStream.m7826i(1, 2);
        codedOutputStream.m7827k(m8066a(thread, th));
        m8077a(codedOutputStream, thread, this.f5446e, 4, true);
        int length = this.f5444c.length;
        for (int i = 0; i < length; i++) {
            m8077a(codedOutputStream, this.f5444c[i], (StackTraceElement[]) this.f5445d.get(i), 0, false);
        }
        m8078a(codedOutputStream, th, 1, 2);
        codedOutputStream.m7826i(3, 2);
        codedOutputStream.m7827k(m8081c());
        codedOutputStream.m7807a(1, f5442a);
        codedOutputStream.m7807a(2, f5442a);
        codedOutputStream.m7806a(3, 0);
        codedOutputStream.m7826i(4, 2);
        codedOutputStream.m7827k(m8080b());
        codedOutputStream.m7806a(1, 0);
        codedOutputStream.m7806a(2, 0);
        codedOutputStream.m7807a(3, this.f5448g);
        if (this.f5449h != null) {
            codedOutputStream.m7807a(4, this.f5449h);
        }
    }

    private void m8079a(CodedOutputStream codedOutputStream, Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            codedOutputStream.m7826i(2, 2);
            codedOutputStream.m7827k(m8065a((String) entry.getKey(), (String) entry.getValue()));
            codedOutputStream.m7807a(1, ByteString.m7767a((String) entry.getKey()));
            String str = (String) entry.getValue();
            if (str == null) {
                str = "";
            }
            codedOutputStream.m7807a(2, ByteString.m7767a(str));
        }
    }

    private void m8078a(CodedOutputStream codedOutputStream, Throwable th, int i, int i2) {
        int i3 = 0;
        codedOutputStream.m7826i(i2, 2);
        codedOutputStream.m7827k(m8070a(th, 1));
        codedOutputStream.m7807a(1, ByteString.m7767a(th.getClass().getName()));
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            codedOutputStream.m7807a(3, ByteString.m7767a(localizedMessage));
        }
        for (StackTraceElement a : th.getStackTrace()) {
            m8073a(codedOutputStream, 4, a, true);
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return;
        }
        if (i < this.f5450i) {
            m8078a(codedOutputStream, cause, i + 1, 6);
            return;
        }
        while (cause != null) {
            cause = cause.getCause();
            i3++;
        }
        codedOutputStream.m7818b(7, i3);
    }

    private void m8077a(CodedOutputStream codedOutputStream, Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        codedOutputStream.m7826i(1, 2);
        codedOutputStream.m7827k(m8069a(thread, stackTraceElementArr, i, z));
        codedOutputStream.m7807a(1, ByteString.m7767a(thread.getName()));
        codedOutputStream.m7818b(2, i);
        for (StackTraceElement a : stackTraceElementArr) {
            m8073a(codedOutputStream, 3, a, z);
        }
    }

    private void m8073a(CodedOutputStream codedOutputStream, int i, StackTraceElement stackTraceElement, boolean z) {
        int i2 = 4;
        codedOutputStream.m7826i(i, 2);
        codedOutputStream.m7827k(m8064a(stackTraceElement, z));
        if (stackTraceElement.isNativeMethod()) {
            codedOutputStream.m7806a(1, (long) Math.max(stackTraceElement.getLineNumber(), 0));
        } else {
            codedOutputStream.m7806a(1, 0);
        }
        codedOutputStream.m7807a(2, ByteString.m7767a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            codedOutputStream.m7807a(3, ByteString.m7767a(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            codedOutputStream.m7806a(4, (long) stackTraceElement.getLineNumber());
        }
        if (!z) {
            i2 = 0;
        }
        codedOutputStream.m7818b(5, i2);
    }

    private void m8072a(CodedOutputStream codedOutputStream, float f, int i, boolean z, int i2, long j, long j2) {
        codedOutputStream.m7826i(5, 2);
        codedOutputStream.m7827k(m8058a(f, i, z, i2, j, j2));
        codedOutputStream.m7804a(1, f);
        codedOutputStream.m7824d(2, i);
        codedOutputStream.m7809a(3, z);
        codedOutputStream.m7818b(4, i2);
        codedOutputStream.m7806a(5, j);
        codedOutputStream.m7806a(6, j2);
    }

    private void m8074a(CodedOutputStream codedOutputStream, ByteString byteString) {
        if (byteString != null) {
            codedOutputStream.m7826i(6, 2);
            codedOutputStream.m7827k(m8060a(byteString));
            codedOutputStream.m7807a(1, byteString);
        }
    }

    private int m8061a(ByteString byteString, ByteString byteString2, ByteString byteString3, ByteString byteString4, int i) {
        int b = ((0 + CodedOutputStream.m7784b(1, byteString)) + CodedOutputStream.m7784b(2, byteString2)) + CodedOutputStream.m7784b(3, byteString3);
        int a = m8057a();
        return ((b + (a + (CodedOutputStream.m7798j(5) + CodedOutputStream.m7799l(a)))) + CodedOutputStream.m7784b(6, byteString4)) + CodedOutputStream.m7795g(10, i);
    }

    private int m8057a() {
        return 0 + CodedOutputStream.m7784b(1, ByteString.m7767a(new ApiKey().m12991a(this.f5447f)));
    }

    private int m8062a(ByteString byteString, ByteString byteString2, boolean z) {
        return (((0 + CodedOutputStream.m7795g(1, 3)) + CodedOutputStream.m7784b(2, byteString)) + CodedOutputStream.m7784b(3, byteString2)) + CodedOutputStream.m7785b(4, z);
    }

    private int m8063a(DeviceIdentifierType deviceIdentifierType, String str) {
        return CodedOutputStream.m7795g(1, deviceIdentifierType.f8249h) + CodedOutputStream.m7784b(2, ByteString.m7767a(str));
    }

    private int m8059a(int i, ByteString byteString, ByteString byteString2, int i2, long j, long j2, boolean z, Map<DeviceIdentifierType, String> map, int i3, ByteString byteString3, ByteString byteString4) {
        int i4;
        int i5;
        int g = CodedOutputStream.m7795g(3, i) + (0 + CodedOutputStream.m7784b(1, byteString));
        if (byteString2 == null) {
            i4 = 0;
        } else {
            i4 = CodedOutputStream.m7784b(4, byteString2);
        }
        i4 = ((((i4 + g) + CodedOutputStream.m7793f(5, i2)) + CodedOutputStream.m7783b(6, j)) + CodedOutputStream.m7783b(7, j2)) + CodedOutputStream.m7785b(10, z);
        if (map != null) {
            i5 = i4;
            for (Entry entry : map.entrySet()) {
                i4 = m8063a((DeviceIdentifierType) entry.getKey(), (String) entry.getValue());
                i5 = (i4 + (CodedOutputStream.m7798j(11) + CodedOutputStream.m7799l(i4))) + i5;
            }
        } else {
            i5 = i4;
        }
        return (byteString4 == null ? 0 : CodedOutputStream.m7784b(14, byteString4)) + ((i5 + CodedOutputStream.m7793f(12, i3)) + (byteString3 == null ? 0 : CodedOutputStream.m7784b(13, byteString3)));
    }

    private int m8080b() {
        int b = ((0 + CodedOutputStream.m7783b(1, 0)) + CodedOutputStream.m7783b(2, 0)) + CodedOutputStream.m7784b(3, this.f5448g);
        if (this.f5449h != null) {
            return b + CodedOutputStream.m7784b(4, this.f5449h);
        }
        return b;
    }

    private int m8068a(Thread thread, Throwable th, String str, long j, Map<String, String> map, float f, int i, boolean z, int i2, long j2, long j3, ByteString byteString) {
        int b = (0 + CodedOutputStream.m7783b(1, j)) + CodedOutputStream.m7784b(2, ByteString.m7767a(str));
        int a = m8067a(thread, th, i2, (Map) map);
        b += a + (CodedOutputStream.m7798j(3) + CodedOutputStream.m7799l(a));
        a = m8058a(f, i, z, i2, j2, j3);
        b += a + (CodedOutputStream.m7798j(5) + CodedOutputStream.m7799l(a));
        if (byteString == null) {
            return b;
        }
        a = m8060a(byteString);
        return b + (a + (CodedOutputStream.m7798j(6) + CodedOutputStream.m7799l(a)));
    }

    private int m8067a(Thread thread, Throwable th, int i, Map<String, String> map) {
        int i2;
        int a = m8066a(thread, th);
        a = (a + (CodedOutputStream.m7798j(1) + CodedOutputStream.m7799l(a))) + 0;
        if (map != null) {
            i2 = a;
            for (Entry entry : map.entrySet()) {
                a = m8065a((String) entry.getKey(), (String) entry.getValue());
                i2 = (a + (CodedOutputStream.m7798j(2) + CodedOutputStream.m7799l(a))) + i2;
            }
        } else {
            i2 = a;
        }
        if (this.f5443b != null) {
            i2 += CodedOutputStream.m7785b(3, this.f5443b.importance != 100);
        }
        return CodedOutputStream.m7793f(4, i) + i2;
    }

    private int m8066a(Thread thread, Throwable th) {
        int i;
        int a = m8069a(thread, this.f5446e, 4, true);
        a = (a + (CodedOutputStream.m7798j(1) + CodedOutputStream.m7799l(a))) + 0;
        int length = this.f5444c.length;
        int i2 = a;
        for (i = 0; i < length; i++) {
            a = m8069a(this.f5444c[i], (StackTraceElement[]) this.f5445d.get(i), 0, false);
            i2 += a + (CodedOutputStream.m7798j(1) + CodedOutputStream.m7799l(a));
        }
        a = m8070a(th, 1);
        a = (a + (CodedOutputStream.m7798j(2) + CodedOutputStream.m7799l(a))) + i2;
        i = m8081c();
        a += i + (CodedOutputStream.m7798j(3) + CodedOutputStream.m7799l(i));
        i = m8080b();
        return a + (i + (CodedOutputStream.m7798j(3) + CodedOutputStream.m7799l(i)));
    }

    private int m8065a(String str, String str2) {
        int b = CodedOutputStream.m7784b(1, ByteString.m7767a(str));
        if (str2 == null) {
            str2 = "";
        }
        return b + CodedOutputStream.m7784b(2, ByteString.m7767a(str2));
    }

    private int m8058a(float f, int i, boolean z, int i2, long j, long j2) {
        return (((((0 + CodedOutputStream.m7782b(1, f)) + CodedOutputStream.m7797h(2, i)) + CodedOutputStream.m7785b(3, z)) + CodedOutputStream.m7793f(4, i2)) + CodedOutputStream.m7783b(5, j)) + CodedOutputStream.m7783b(6, j2);
    }

    private int m8060a(ByteString byteString) {
        return CodedOutputStream.m7784b(1, byteString);
    }

    private int m8070a(Throwable th, int i) {
        int i2 = 0;
        int b = CodedOutputStream.m7784b(1, ByteString.m7767a(th.getClass().getName())) + 0;
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            b += CodedOutputStream.m7784b(3, ByteString.m7767a(localizedMessage));
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i3 = 0;
        while (i3 < length) {
            int a = m8064a(stackTrace[i3], true);
            i3++;
            b = (a + (CodedOutputStream.m7798j(4) + CodedOutputStream.m7799l(a))) + b;
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return b;
        }
        if (i < this.f5450i) {
            i2 = m8070a(cause, i + 1);
            return b + (i2 + (CodedOutputStream.m7798j(6) + CodedOutputStream.m7799l(i2)));
        }
        while (cause != null) {
            cause = cause.getCause();
            i2++;
        }
        return b + CodedOutputStream.m7793f(7, i2);
    }

    private int m8081c() {
        return ((0 + CodedOutputStream.m7784b(1, f5442a)) + CodedOutputStream.m7784b(2, f5442a)) + CodedOutputStream.m7783b(3, 0);
    }

    private int m8064a(StackTraceElement stackTraceElement, boolean z) {
        int b;
        int i;
        if (stackTraceElement.isNativeMethod()) {
            b = CodedOutputStream.m7783b(1, (long) Math.max(stackTraceElement.getLineNumber(), 0)) + 0;
        } else {
            b = CodedOutputStream.m7783b(1, 0) + 0;
        }
        b += CodedOutputStream.m7784b(2, ByteString.m7767a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            b += CodedOutputStream.m7784b(3, ByteString.m7767a(stackTraceElement.getFileName()));
        }
        if (stackTraceElement.isNativeMethod() || stackTraceElement.getLineNumber() <= 0) {
            i = b;
        } else {
            i = b + CodedOutputStream.m7783b(4, (long) stackTraceElement.getLineNumber());
        }
        return CodedOutputStream.m7793f(5, z ? 2 : 0) + i;
    }

    private int m8069a(Thread thread, StackTraceElement[] stackTraceElementArr, int i, boolean z) {
        int f = CodedOutputStream.m7793f(2, i) + CodedOutputStream.m7784b(1, ByteString.m7767a(thread.getName()));
        for (StackTraceElement a : stackTraceElementArr) {
            int a2 = m8064a(a, z);
            f += a2 + (CodedOutputStream.m7798j(3) + CodedOutputStream.m7799l(a2));
        }
        return f;
    }

    private ByteString m8071a(String str) {
        return str == null ? null : ByteString.m7767a(str);
    }
}
