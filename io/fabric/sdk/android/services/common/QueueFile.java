package io.fabric.sdk.android.services.common;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueFile implements Closeable {
    private static final Logger f8272b;
    int f8273a;
    private final RandomAccessFile f8274c;
    private int f8275d;
    private Element f8276e;
    private Element f8277f;
    private final byte[] f8278g;

    public interface ElementReader {
        void m8008a(InputStream inputStream, int i);
    }

    /* renamed from: io.fabric.sdk.android.services.common.QueueFile.1 */
    class C09601 implements ElementReader {
        boolean f8263a;
        final /* synthetic */ StringBuilder f8264b;
        final /* synthetic */ QueueFile f8265c;

        C09601(QueueFile queueFile, StringBuilder stringBuilder) {
            this.f8265c = queueFile;
            this.f8264b = stringBuilder;
            this.f8263a = true;
        }

        public void m13078a(InputStream inputStream, int i) {
            if (this.f8263a) {
                this.f8263a = false;
            } else {
                this.f8264b.append(", ");
            }
            this.f8264b.append(i);
        }
    }

    class Element {
        static final Element f8266a;
        final int f8267b;
        final int f8268c;

        static {
            f8266a = new Element(0, 0);
        }

        Element(int i, int i2) {
            this.f8267b = i;
            this.f8268c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[" + "position = " + this.f8267b + ", length = " + this.f8268c + "]";
        }
    }

    final class ElementInputStream extends InputStream {
        final /* synthetic */ QueueFile f8269a;
        private int f8270b;
        private int f8271c;

        private ElementInputStream(QueueFile queueFile, Element element) {
            this.f8269a = queueFile;
            this.f8270b = queueFile.m13089b(element.f8267b + 4);
            this.f8271c = element.f8268c;
        }

        public int read(byte[] bArr, int i, int i2) {
            QueueFile.m13091b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.f8271c <= 0) {
                return -1;
            } else {
                if (i2 > this.f8271c) {
                    i2 = this.f8271c;
                }
                this.f8269a.m13092b(this.f8270b, bArr, i, i2);
                this.f8270b = this.f8269a.m13089b(this.f8270b + i2);
                this.f8271c -= i2;
                return i2;
            }
        }

        public int read() {
            if (this.f8271c == 0) {
                return -1;
            }
            this.f8269a.f8274c.seek((long) this.f8270b);
            int read = this.f8269a.f8274c.read();
            this.f8270b = this.f8269a.m13089b(this.f8270b + 1);
            this.f8271c--;
            return read;
        }
    }

    static {
        f8272b = Logger.getLogger(QueueFile.class.getName());
    }

    public QueueFile(File file) {
        this.f8278g = new byte[16];
        if (!file.exists()) {
            m13087a(file);
        }
        this.f8274c = m13090b(file);
        m13094c();
    }

    private static void m13093b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void m13088a(byte[] bArr, int... iArr) {
        int i = 0;
        int length = iArr.length;
        int i2 = 0;
        while (i < length) {
            m13093b(bArr, i2, iArr[i]);
            i2 += 4;
            i++;
        }
    }

    private static int m13080a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16)) + ((bArr[i + 2] & 255) << 8)) + (bArr[i + 3] & 255);
    }

    private void m13094c() {
        this.f8274c.seek(0);
        this.f8274c.readFully(this.f8278g);
        this.f8273a = m13080a(this.f8278g, 0);
        if (((long) this.f8273a) > this.f8274c.length()) {
            throw new IOException("File is truncated. Expected length: " + this.f8273a + ", Actual length: " + this.f8274c.length());
        }
        this.f8275d = m13080a(this.f8278g, 4);
        int a = m13080a(this.f8278g, 8);
        int a2 = m13080a(this.f8278g, 12);
        this.f8276e = m13081a(a);
        this.f8277f = m13081a(a2);
    }

    private void m13084a(int i, int i2, int i3, int i4) {
        m13088a(this.f8278g, i, i2, i3, i4);
        this.f8274c.seek(0);
        this.f8274c.write(this.f8278g);
    }

    private Element m13081a(int i) {
        if (i == 0) {
            return Element.f8266a;
        }
        this.f8274c.seek((long) i);
        return new Element(i, this.f8274c.readInt());
    }

    private static void m13087a(File file) {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b = m13090b(file2);
        try {
            b.setLength(4096);
            b.seek(0);
            byte[] bArr = new byte[16];
            m13088a(bArr, 4096, 0, 0, 0);
            b.write(bArr);
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } finally {
            b.close();
        }
    }

    private static RandomAccessFile m13090b(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    private int m13089b(int i) {
        return i < this.f8273a ? i : (i + 16) - this.f8273a;
    }

    private void m13085a(int i, byte[] bArr, int i2, int i3) {
        int b = m13089b(i);
        if (b + i3 <= this.f8273a) {
            this.f8274c.seek((long) b);
            this.f8274c.write(bArr, i2, i3);
            return;
        }
        int i4 = this.f8273a - b;
        this.f8274c.seek((long) b);
        this.f8274c.write(bArr, i2, i4);
        this.f8274c.seek(16);
        this.f8274c.write(bArr, i2 + i4, i3 - i4);
    }

    private void m13092b(int i, byte[] bArr, int i2, int i3) {
        int b = m13089b(i);
        if (b + i3 <= this.f8273a) {
            this.f8274c.seek((long) b);
            this.f8274c.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.f8273a - b;
        this.f8274c.seek((long) b);
        this.f8274c.readFully(bArr, i2, i4);
        this.f8274c.seek(16);
        this.f8274c.readFully(bArr, i2 + i4, i3 - i4);
    }

    public void m13100a(byte[] bArr) {
        m13101a(bArr, 0, bArr.length);
    }

    public synchronized void m13101a(byte[] bArr, int i, int i2) {
        m13091b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        int i3;
        m13095c(i2);
        boolean b = m13103b();
        if (b) {
            i3 = 16;
        } else {
            i3 = m13089b((this.f8277f.f8267b + 4) + this.f8277f.f8268c);
        }
        Element element = new Element(i3, i2);
        m13093b(this.f8278g, 0, i2);
        m13085a(element.f8267b, this.f8278g, 0, 4);
        m13085a(element.f8267b + 4, bArr, i, i2);
        m13084a(this.f8273a, this.f8275d + 1, b ? element.f8267b : this.f8276e.f8267b, element.f8267b);
        this.f8277f = element;
        this.f8275d++;
        if (b) {
            this.f8276e = this.f8277f;
        }
    }

    public int m13098a() {
        if (this.f8275d == 0) {
            return 16;
        }
        if (this.f8277f.f8267b >= this.f8276e.f8267b) {
            return (((this.f8277f.f8267b - this.f8276e.f8267b) + 4) + this.f8277f.f8268c) + 16;
        }
        return (((this.f8277f.f8267b + 4) + this.f8277f.f8268c) + this.f8273a) - this.f8276e.f8267b;
    }

    private int m13096d() {
        return this.f8273a - m13098a();
    }

    public synchronized boolean m13103b() {
        return this.f8275d == 0;
    }

    private void m13095c(int i) {
        int i2 = i + 4;
        int d = m13096d();
        if (d < i2) {
            int i3 = this.f8273a;
            do {
                d += i3;
                i3 <<= 1;
            } while (d < i2);
            m13097d(i3);
            i2 = m13089b((this.f8277f.f8267b + 4) + this.f8277f.f8268c);
            if (i2 < this.f8276e.f8267b) {
                FileChannel channel = this.f8274c.getChannel();
                channel.position((long) this.f8273a);
                int i4 = i2 - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f8277f.f8267b < this.f8276e.f8267b) {
                d = (this.f8273a + this.f8277f.f8267b) - 16;
                m13084a(i3, this.f8275d, this.f8276e.f8267b, d);
                this.f8277f = new Element(d, this.f8277f.f8268c);
            } else {
                m13084a(i3, this.f8275d, this.f8276e.f8267b, this.f8277f.f8267b);
            }
            this.f8273a = i3;
        }
    }

    private void m13097d(int i) {
        this.f8274c.setLength((long) i);
        this.f8274c.getChannel().force(true);
    }

    public synchronized void m13099a(ElementReader elementReader) {
        int i = this.f8276e.f8267b;
        for (int i2 = 0; i2 < this.f8275d; i2++) {
            Element a = m13081a(i);
            elementReader.m8008a(new ElementInputStream(a, null), a.f8268c);
            i = m13089b(a.f8268c + (a.f8267b + 4));
        }
    }

    private static <T> T m13091b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public synchronized void close() {
        this.f8274c.close();
    }

    public boolean m13102a(int i, int i2) {
        return (m13098a() + 4) + i <= i2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append('[');
        stringBuilder.append("fileLength=").append(this.f8273a);
        stringBuilder.append(", size=").append(this.f8275d);
        stringBuilder.append(", first=").append(this.f8276e);
        stringBuilder.append(", last=").append(this.f8277f);
        stringBuilder.append(", element lengths=[");
        try {
            m13099a(new C09601(this, stringBuilder));
        } catch (Throwable e) {
            f8272b.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
