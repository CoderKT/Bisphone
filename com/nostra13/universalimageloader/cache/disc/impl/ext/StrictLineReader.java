package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class StrictLineReader implements Closeable {
    private final InputStream f6842a;
    private final Charset f6843b;
    private byte[] f6844c;
    private int f6845d;
    private int f6846e;

    /* renamed from: com.nostra13.universalimageloader.cache.disc.impl.ext.StrictLineReader.1 */
    class C09141 extends ByteArrayOutputStream {
        final /* synthetic */ StrictLineReader f6841a;

        C09141(StrictLineReader strictLineReader, int i) {
            this.f6841a = strictLineReader;
            super(i);
        }

        public String toString() {
            int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
            try {
                return new String(this.buf, 0, i, this.f6841a.f6843b.name());
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
    }

    public StrictLineReader(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public StrictLineReader(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(Util.f6847a)) {
            this.f6842a = inputStream;
            this.f6843b = charset;
            this.f6844c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public void close() {
        synchronized (this.f6842a) {
            if (this.f6844c != null) {
                this.f6844c = null;
                this.f6842a.close();
            }
        }
    }

    public String m10964a() {
        String str;
        synchronized (this.f6842a) {
            if (this.f6844c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f6845d >= this.f6846e) {
                m10963b();
            }
            int i2 = this.f6845d;
            while (i2 != this.f6846e) {
                if (this.f6844c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f6845d || this.f6844c[i2 - 1] != 13) ? i2 : i2 - 1;
                    str = new String(this.f6844c, this.f6845d, i3 - this.f6845d, this.f6843b.name());
                    this.f6845d = i2 + 1;
                } else {
                    i2++;
                }
            }
            ByteArrayOutputStream c09141 = new C09141(this, (this.f6846e - this.f6845d) + 80);
            loop1:
            while (true) {
                c09141.write(this.f6844c, this.f6845d, this.f6846e - this.f6845d);
                this.f6846e = -1;
                m10963b();
                i = this.f6845d;
                while (i != this.f6846e) {
                    if (this.f6844c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f6845d) {
                c09141.write(this.f6844c, this.f6845d, i - this.f6845d);
            }
            this.f6845d = i + 1;
            str = c09141.toString();
        }
        return str;
    }

    private void m10963b() {
        int read = this.f6842a.read(this.f6844c, 0, this.f6844c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f6845d = 0;
        this.f6846e = read;
    }
}
