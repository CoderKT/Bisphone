package com.crashlytics.android;

import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.util.StringUtils;

final class CodedOutputStream implements Flushable {
    private final byte[] f5298a;
    private final int f5299b;
    private int f5300c;
    private final OutputStream f5301d;

    class OutOfSpaceException extends IOException {
        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private CodedOutputStream(OutputStream outputStream, byte[] bArr) {
        this.f5301d = outputStream;
        this.f5298a = bArr;
        this.f5300c = 0;
        this.f5299b = bArr.length;
    }

    public static CodedOutputStream m7778a(OutputStream outputStream) {
        return m7779a(outputStream, 4096);
    }

    public static CodedOutputStream m7779a(OutputStream outputStream, int i) {
        return new CodedOutputStream(outputStream, new byte[i]);
    }

    public void m7804a(int i, float f) {
        m7826i(i, 5);
        m7802a(f);
    }

    public void m7806a(int i, long j) {
        m7826i(i, 0);
        m7810a(j);
    }

    public void m7805a(int i, int i2) {
        m7826i(i, 0);
        m7803a(i2);
    }

    public void m7809a(int i, boolean z) {
        m7826i(i, 0);
        m7814a(z);
    }

    public void m7808a(int i, String str) {
        m7826i(i, 2);
        m7813a(str);
    }

    public void m7807a(int i, ByteString byteString) {
        m7826i(i, 2);
        m7811a(byteString);
    }

    public void m7818b(int i, int i2) {
        m7826i(i, 0);
        m7817b(i2);
    }

    public void m7820c(int i, int i2) {
        m7826i(i, 0);
        m7819c(i2);
    }

    public void m7824d(int i, int i2) {
        m7826i(i, 0);
        m7823d(i2);
    }

    public void m7802a(float f) {
        m7828m(Float.floatToRawIntBits(f));
    }

    public void m7810a(long j) {
        m7821c(j);
    }

    public void m7803a(int i) {
        if (i >= 0) {
            m7827k(i);
        } else {
            m7821c((long) i);
        }
    }

    public void m7814a(boolean z) {
        m7825i(z ? 1 : 0);
    }

    public void m7813a(String str) {
        byte[] bytes = str.getBytes(StringUtils.UTF8);
        m7827k(bytes.length);
        m7815a(bytes);
    }

    public void m7811a(ByteString byteString) {
        m7827k(byteString.m7769a());
        m7822c(byteString);
    }

    public void m7817b(int i) {
        m7827k(i);
    }

    public void m7819c(int i) {
        m7803a(i);
    }

    public void m7823d(int i) {
        m7827k(m7800n(i));
    }

    public static int m7782b(int i, float f) {
        return m7798j(i) + m7781b(f);
    }

    public static int m7783b(int i, long j) {
        return m7798j(i) + m7786b(j);
    }

    public static int m7791e(int i, int i2) {
        return m7798j(i) + m7790e(i2);
    }

    public static int m7785b(int i, boolean z) {
        return m7798j(i) + m7788b(z);
    }

    public static int m7784b(int i, ByteString byteString) {
        return m7798j(i) + m7787b(byteString);
    }

    public static int m7793f(int i, int i2) {
        return m7798j(i) + m7792f(i2);
    }

    public static int m7795g(int i, int i2) {
        return m7798j(i) + m7794g(i2);
    }

    public static int m7797h(int i, int i2) {
        return m7798j(i) + m7796h(i2);
    }

    public static int m7781b(float f) {
        return 4;
    }

    public static int m7786b(long j) {
        return m7789d(j);
    }

    public static int m7790e(int i) {
        if (i >= 0) {
            return m7799l(i);
        }
        return 10;
    }

    public static int m7788b(boolean z) {
        return 1;
    }

    public static int m7787b(ByteString byteString) {
        return m7799l(byteString.m7769a()) + byteString.m7769a();
    }

    public static int m7792f(int i) {
        return m7799l(i);
    }

    public static int m7794g(int i) {
        return m7790e(i);
    }

    public static int m7796h(int i) {
        return m7799l(m7800n(i));
    }

    private void m7780a() {
        if (this.f5301d == null) {
            throw new OutOfSpaceException();
        }
        this.f5301d.write(this.f5298a, 0, this.f5300c);
        this.f5300c = 0;
    }

    public void flush() {
        if (this.f5301d != null) {
            m7780a();
        }
    }

    public void m7801a(byte b) {
        if (this.f5300c == this.f5299b) {
            m7780a();
        }
        byte[] bArr = this.f5298a;
        int i = this.f5300c;
        this.f5300c = i + 1;
        bArr[i] = b;
    }

    public void m7825i(int i) {
        m7801a((byte) i);
    }

    public void m7822c(ByteString byteString) {
        m7812a(byteString, 0, byteString.m7769a());
    }

    public void m7815a(byte[] bArr) {
        m7816a(bArr, 0, bArr.length);
    }

    public void m7816a(byte[] bArr, int i, int i2) {
        if (this.f5299b - this.f5300c >= i2) {
            System.arraycopy(bArr, i, this.f5298a, this.f5300c, i2);
            this.f5300c += i2;
            return;
        }
        int i3 = this.f5299b - this.f5300c;
        System.arraycopy(bArr, i, this.f5298a, this.f5300c, i3);
        int i4 = i + i3;
        i3 = i2 - i3;
        this.f5300c = this.f5299b;
        m7780a();
        if (i3 <= this.f5299b) {
            System.arraycopy(bArr, i4, this.f5298a, 0, i3);
            this.f5300c = i3;
            return;
        }
        this.f5301d.write(bArr, i4, i3);
    }

    public void m7812a(ByteString byteString, int i, int i2) {
        if (this.f5299b - this.f5300c >= i2) {
            byteString.m7770a(this.f5298a, i, this.f5300c, i2);
            this.f5300c += i2;
            return;
        }
        int i3 = this.f5299b - this.f5300c;
        byteString.m7770a(this.f5298a, i, this.f5300c, i3);
        int i4 = i + i3;
        i3 = i2 - i3;
        this.f5300c = this.f5299b;
        m7780a();
        if (i3 <= this.f5299b) {
            byteString.m7770a(this.f5298a, i4, 0, i3);
            this.f5300c = i3;
            return;
        }
        InputStream b = byteString.m7771b();
        if (((long) i4) != b.skip((long) i4)) {
            throw new IllegalStateException("Skip failed.");
        }
        while (i3 > 0) {
            i4 = Math.min(i3, this.f5299b);
            int read = b.read(this.f5298a, 0, i4);
            if (read != i4) {
                throw new IllegalStateException("Read failed.");
            }
            this.f5301d.write(this.f5298a, 0, read);
            i3 -= read;
        }
    }

    public void m7826i(int i, int i2) {
        m7827k(WireFormat.m8094a(i, i2));
    }

    public static int m7798j(int i) {
        return m7799l(WireFormat.m8094a(i, 0));
    }

    public void m7827k(int i) {
        while ((i & -128) != 0) {
            m7825i((i & 127) | 128);
            i >>>= 7;
        }
        m7825i(i);
    }

    public static int m7799l(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    public void m7821c(long j) {
        while ((-128 & j) != 0) {
            m7825i((((int) j) & 127) | 128);
            j >>>= 7;
        }
        m7825i((int) j);
    }

    public static int m7789d(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    public void m7828m(int i) {
        m7825i(i & 255);
        m7825i((i >> 8) & 255);
        m7825i((i >> 16) & 255);
        m7825i((i >> 24) & 255);
    }

    public static int m7800n(int i) {
        return (i << 1) ^ (i >> 31);
    }
}
