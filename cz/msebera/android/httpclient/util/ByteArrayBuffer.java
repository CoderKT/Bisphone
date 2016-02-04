package cz.msebera.android.httpclient.util;

import java.io.Serializable;

public final class ByteArrayBuffer implements Serializable {
    private byte[] f7920a;
    private int f7921b;

    public ByteArrayBuffer(int i) {
        Args.m12726b(i, "Buffer capacity");
        this.f7920a = new byte[i];
    }

    private void m12730c(int i) {
        Object obj = new byte[Math.max(this.f7920a.length << 1, i)];
        System.arraycopy(this.f7920a, 0, obj, 0, this.f7921b);
        this.f7920a = obj;
    }

    public void m12734a(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || i + i2 < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int i3 = this.f7921b + i2;
                if (i3 > this.f7920a.length) {
                    m12730c(i3);
                }
                System.arraycopy(bArr, i, this.f7920a, this.f7921b, i2);
                this.f7921b = i3;
            }
        }
    }

    public void m12732a(int i) {
        int i2 = this.f7921b + 1;
        if (i2 > this.f7920a.length) {
            m12730c(i2);
        }
        this.f7920a[this.f7921b] = (byte) i;
        this.f7921b = i2;
    }

    public void m12735a(char[] cArr, int i, int i2) {
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || i + i2 < 0 || i + i2 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int i3 = this.f7921b;
                int i4 = i3 + i2;
                if (i4 > this.f7920a.length) {
                    m12730c(i4);
                }
                while (i3 < i4) {
                    this.f7920a[i3] = (byte) cArr[i];
                    i++;
                    i3++;
                }
                this.f7921b = i4;
            }
        }
    }

    public void m12733a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            m12735a(charArrayBuffer.m12756b(), i, i2);
        }
    }

    public void m12731a() {
        this.f7921b = 0;
    }

    public byte[] m12737b() {
        Object obj = new byte[this.f7921b];
        if (this.f7921b > 0) {
            System.arraycopy(this.f7920a, 0, obj, 0, this.f7921b);
        }
        return obj;
    }

    public int m12736b(int i) {
        return this.f7920a[i];
    }

    public int m12738c() {
        return this.f7920a.length;
    }

    public int m12739d() {
        return this.f7921b;
    }

    public byte[] m12740e() {
        return this.f7920a;
    }

    public boolean m12741f() {
        return this.f7921b == 0;
    }

    public boolean m12742g() {
        return this.f7921b == this.f7920a.length;
    }
}
