package com.crashlytics.android;

import android.os.Process;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.IdManager;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

class CLSUUID {
    private static final AtomicLong f5291a;
    private static String f5292b;

    static {
        f5291a = new AtomicLong(0);
    }

    public CLSUUID(IdManager idManager) {
        r0 = new byte[10];
        m7772a(r0);
        m7774b(r0);
        m7776c(r0);
        String a = CommonUtils.m13007a(idManager.m13062b());
        String a2 = CommonUtils.m13009a(r0);
        f5292b = String.format(Locale.US, "%s-%s-%s-%s", new Object[]{a2.substring(0, 12), a2.substring(12, 16), a2.subSequence(16, 20), a.substring(0, 12)}).toUpperCase(Locale.US);
    }

    private void m7772a(byte[] bArr) {
        long time = new Date().getTime();
        long j = time / 1000;
        time %= 1000;
        byte[] a = m7773a(j);
        bArr[0] = a[0];
        bArr[1] = a[1];
        bArr[2] = a[2];
        bArr[3] = a[3];
        byte[] b = m7775b(time);
        bArr[4] = b[0];
        bArr[5] = b[1];
    }

    private void m7774b(byte[] bArr) {
        byte[] b = m7775b(f5291a.incrementAndGet());
        bArr[6] = b[0];
        bArr[7] = b[1];
    }

    private void m7776c(byte[] bArr) {
        byte[] b = m7775b((long) Integer.valueOf(Process.myPid()).shortValue());
        bArr[8] = b[0];
        bArr[9] = b[1];
    }

    private static byte[] m7773a(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt((int) j);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.position(0);
        return allocate.array();
    }

    private static byte[] m7775b(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort((short) ((int) j));
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.position(0);
        return allocate.array();
    }

    public String toString() {
        return f5292b;
    }
}
