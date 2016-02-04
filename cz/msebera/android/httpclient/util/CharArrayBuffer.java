package cz.msebera.android.httpclient.util;

import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.Serializable;

public final class CharArrayBuffer implements Serializable {
    private char[] f7922a;
    private int f7923b;

    public CharArrayBuffer(int i) {
        Args.m12726b(i, "Buffer capacity");
        this.f7922a = new char[i];
    }

    private void m12743d(int i) {
        Object obj = new char[Math.max(this.f7922a.length << 1, i)];
        System.arraycopy(this.f7922a, 0, obj, 0, this.f7923b);
        this.f7922a = obj;
    }

    public void m12753a(char[] cArr, int i, int i2) {
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || i + i2 < 0 || i + i2 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int i3 = this.f7923b + i2;
                if (i3 > this.f7922a.length) {
                    m12743d(i3);
                }
                System.arraycopy(cArr, i, this.f7922a, this.f7923b, i2);
                this.f7923b = i3;
            }
        }
    }

    public void m12751a(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i = this.f7923b + length;
        if (i > this.f7922a.length) {
            m12743d(i);
        }
        str.getChars(0, length, this.f7922a, this.f7923b);
        this.f7923b = i;
    }

    public void m12750a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        if (charArrayBuffer != null) {
            m12753a(charArrayBuffer.f7922a, i, i2);
        }
    }

    public void m12748a(char c) {
        int i = this.f7923b + 1;
        if (i > this.f7922a.length) {
            m12743d(i);
        }
        this.f7922a[this.f7923b] = c;
        this.f7923b = i;
    }

    public void m12752a(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || i + i2 < 0 || i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int i3 = this.f7923b;
                int i4 = i3 + i2;
                if (i4 > this.f7922a.length) {
                    m12743d(i4);
                }
                while (i3 < i4) {
                    this.f7922a[i3] = (char) (bArr[i] & 255);
                    i++;
                    i3++;
                }
                this.f7923b = i4;
            }
        }
    }

    public void m12749a(ByteArrayBuffer byteArrayBuffer, int i, int i2) {
        if (byteArrayBuffer != null) {
            m12752a(byteArrayBuffer.m12740e(), i, i2);
        }
    }

    public void m12747a() {
        this.f7923b = 0;
    }

    public char m12744a(int i) {
        return this.f7922a[i];
    }

    public char[] m12756b() {
        return this.f7922a;
    }

    public int m12757c() {
        return this.f7923b;
    }

    public void m12755b(int i) {
        if (i > 0 && i > this.f7922a.length - this.f7923b) {
            m12743d(this.f7923b + i);
        }
    }

    public boolean m12759d() {
        return this.f7923b == 0;
    }

    public int m12745a(int i, int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 > this.f7923b) {
            i3 = this.f7923b;
        }
        if (i2 > i3) {
            return -1;
        }
        for (int i4 = i2; i4 < i3; i4++) {
            if (this.f7922a[i4] == i) {
                return i4;
            }
        }
        return -1;
    }

    public int m12758c(int i) {
        return m12745a(i, 0, this.f7923b);
    }

    public String m12746a(int i, int i2) {
        return new String(this.f7922a, i, i2 - i);
    }

    public String m12754b(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i);
        } else if (i2 > this.f7923b) {
            throw new IndexOutOfBoundsException("endIndex: " + i2 + " > length: " + this.f7923b);
        } else if (i > i2) {
            throw new IndexOutOfBoundsException("beginIndex: " + i + " > endIndex: " + i2);
        } else {
            while (i < i2 && HTTP.m12703a(this.f7922a[i])) {
                i++;
            }
            while (i2 > i && HTTP.m12703a(this.f7922a[i2 - 1])) {
                i2--;
            }
            return new String(this.f7922a, i, i2 - i);
        }
    }

    public String toString() {
        return new String(this.f7922a, 0, this.f7923b);
    }
}
