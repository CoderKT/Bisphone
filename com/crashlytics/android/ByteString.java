package com.crashlytics.android;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.jivesoftware.smack.util.StringUtils;

final class ByteString {
    public static final ByteString f5288a;
    private final byte[] f5289b;
    private volatile int f5290c;

    private ByteString(byte[] bArr) {
        this.f5290c = 0;
        this.f5289b = bArr;
    }

    public int m7769a() {
        return this.f5289b.length;
    }

    static {
        f5288a = new ByteString(new byte[0]);
    }

    public static ByteString m7768a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return new ByteString(obj);
    }

    public static ByteString m7767a(String str) {
        try {
            return new ByteString(str.getBytes(StringUtils.UTF8));
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public void m7770a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.f5289b, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        int length = this.f5289b.length;
        if (length != byteString.f5289b.length) {
            return false;
        }
        byte[] bArr = this.f5289b;
        byte[] bArr2 = byteString.f5289b;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.f5290c;
        if (i == 0) {
            byte[] bArr = this.f5289b;
            int length = this.f5289b.length;
            int i2 = 0;
            i = length;
            while (i2 < length) {
                int i3 = bArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
            if (i == 0) {
                i = 1;
            }
            this.f5290c = i;
        }
        return i;
    }

    public InputStream m7771b() {
        return new ByteArrayInputStream(this.f5289b);
    }
}
