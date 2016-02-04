package com.loopj.android.http;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64OutputStream extends FilterOutputStream {
    private static final byte[] f6554a;
    private final Coder f6555b;
    private final int f6556c;
    private byte[] f6557d;
    private int f6558e;

    static {
        f6554a = new byte[0];
    }

    public Base64OutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, true);
    }

    public Base64OutputStream(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this.f6557d = null;
        this.f6558e = 0;
        this.f6556c = i;
        if (z) {
            this.f6555b = new Encoder(i, null);
        } else {
            this.f6555b = new Decoder(i, null);
        }
    }

    public void write(int i) {
        if (this.f6557d == null) {
            this.f6557d = new byte[1024];
        }
        if (this.f6558e >= this.f6557d.length) {
            m10606a(this.f6557d, 0, this.f6558e, false);
            this.f6558e = 0;
        }
        byte[] bArr = this.f6557d;
        int i2 = this.f6558e;
        this.f6558e = i2 + 1;
        bArr[i2] = (byte) i;
    }

    private void m10605a() {
        if (this.f6558e > 0) {
            m10606a(this.f6557d, 0, this.f6558e, false);
            this.f6558e = 0;
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            m10605a();
            m10606a(bArr, i, i2, false);
        }
    }

    public void close() {
        IOException iOException = null;
        try {
            m10605a();
            m10606a(f6554a, 0, 0, true);
        } catch (IOException e) {
            IOException e2;
            iOException = e2;
        }
        try {
            if ((this.f6556c & 16) == 0) {
                this.out.close();
            } else {
                this.out.flush();
            }
            e2 = iOException;
        } catch (IOException e3) {
            e2 = e3;
            if (iOException == null) {
                e2 = iOException;
            }
        }
        if (e2 != null) {
            throw e2;
        }
    }

    private void m10606a(byte[] bArr, int i, int i2, boolean z) {
        this.f6555b.f6538a = m10607a(this.f6555b.f6538a, this.f6555b.m10599a(i2));
        if (this.f6555b.m10600a(bArr, i, i2, z)) {
            this.out.write(this.f6555b.f6538a, 0, this.f6555b.f6539b);
            return;
        }
        throw new Base64DataException("bad base-64");
    }

    private byte[] m10607a(byte[] bArr, int i) {
        if (bArr == null || bArr.length < i) {
            return new byte[i];
        }
        return bArr;
    }
}
