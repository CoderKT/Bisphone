package com.loopj.android.http;

import android.text.TextUtils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jivesoftware.smack.util.StringUtils;

class SimpleMultipartEntity implements HttpEntity {
    private static final byte[] f6642a;
    private static final byte[] f6643b;
    private static final char[] f6644c;
    private final String f6645d;
    private final byte[] f6646e;
    private final byte[] f6647f;
    private final List<FilePart> f6648g;
    private final ByteArrayOutputStream f6649h;
    private final ResponseHandlerInterface f6650i;
    private boolean f6651j;
    private long f6652k;
    private long f6653l;

    class FilePart {
        public final File f6639a;
        public final byte[] f6640b;
        final /* synthetic */ SimpleMultipartEntity f6641c;

        public FilePart(SimpleMultipartEntity simpleMultipartEntity, String str, File file, String str2, String str3) {
            this.f6641c = simpleMultipartEntity;
            if (TextUtils.isEmpty(str3)) {
                str3 = file.getName();
            }
            this.f6640b = m10748a(str, str3, str2);
            this.f6639a = file;
        }

        private byte[] m10748a(String str, String str2, String str3) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(this.f6641c.f6646e);
                byteArrayOutputStream.write(this.f6641c.m10757a(str, str2));
                byteArrayOutputStream.write(this.f6641c.m10758b(str3));
                byteArrayOutputStream.write(SimpleMultipartEntity.f6643b);
                byteArrayOutputStream.write(SimpleMultipartEntity.f6642a);
            } catch (Throwable e) {
                AsyncHttpClient.f6518a.m10682b("SimpleMultipartEntity", "createHeader ByteArrayOutputStream exception", e);
            }
            return byteArrayOutputStream.toByteArray();
        }

        public long m10749a() {
            return (this.f6639a.length() + ((long) SimpleMultipartEntity.f6642a.length)) + ((long) this.f6640b.length);
        }

        public void m10750a(OutputStream outputStream) {
            outputStream.write(this.f6640b);
            this.f6641c.m10752a((long) this.f6640b.length);
            InputStream fileInputStream = new FileInputStream(this.f6639a);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                    this.f6641c.m10752a((long) read);
                } else {
                    outputStream.write(SimpleMultipartEntity.f6642a);
                    this.f6641c.m10752a((long) SimpleMultipartEntity.f6642a.length);
                    outputStream.flush();
                    AsyncHttpClient.m10567a(fileInputStream);
                    return;
                }
            }
        }
    }

    static {
        f6642a = "\r\n".getBytes();
        f6643b = "Content-Transfer-Encoding: binary\r\n".getBytes();
        f6644c = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public SimpleMultipartEntity(ResponseHandlerInterface responseHandlerInterface) {
        this.f6648g = new ArrayList();
        this.f6649h = new ByteArrayOutputStream();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append(f6644c[random.nextInt(f6644c.length)]);
        }
        this.f6645d = stringBuilder.toString();
        this.f6646e = ("--" + this.f6645d + "\r\n").getBytes();
        this.f6647f = ("--" + this.f6645d + "--" + "\r\n").getBytes();
        this.f6650i = responseHandlerInterface;
    }

    public void m10766a(String str, String str2, String str3) {
        try {
            this.f6649h.write(this.f6646e);
            this.f6649h.write(m10759c(str));
            this.f6649h.write(m10758b(str3));
            this.f6649h.write(f6642a);
            this.f6649h.write(str2.getBytes());
            this.f6649h.write(f6642a);
        } catch (Throwable e) {
            AsyncHttpClient.f6518a.m10682b("SimpleMultipartEntity", "addPart ByteArrayOutputStream exception", e);
        }
    }

    public void m10769b(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = StringUtils.UTF8;
        }
        m10766a(str, str2, "text/plain; charset=" + str3);
    }

    public void m10764a(String str, File file, String str2, String str3) {
        this.f6648g.add(new FilePart(this, str, file, m10751a(str2), str3));
    }

    public void m10765a(String str, String str2, InputStream inputStream, String str3) {
        this.f6649h.write(this.f6646e);
        this.f6649h.write(m10757a(str, str2));
        this.f6649h.write(m10758b(str3));
        this.f6649h.write(f6643b);
        this.f6649h.write(f6642a);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                this.f6649h.write(bArr, 0, read);
            } else {
                this.f6649h.write(f6642a);
                this.f6649h.flush();
                return;
            }
        }
    }

    private String m10751a(String str) {
        return str == null ? "application/octet-stream" : str;
    }

    private byte[] m10758b(String str) {
        return ("Content-Type: " + m10751a(str) + "\r\n").getBytes();
    }

    private byte[] m10759c(String str) {
        return ("Content-Disposition: form-data; name=\"" + str + "\"" + "\r\n").getBytes();
    }

    private byte[] m10757a(String str, String str2) {
        return ("Content-Disposition: form-data; name=\"" + str + "\"" + "; filename=\"" + str2 + "\"" + "\r\n").getBytes();
    }

    private void m10752a(long j) {
        this.f6652k += j;
        this.f6650i.m5463b(this.f6652k, this.f6653l);
    }

    public long m10768b() {
        long size = (long) this.f6649h.size();
        long j = size;
        for (FilePart a : this.f6648g) {
            size = a.m10749a();
            if (size < 0) {
                return -1;
            }
            j = size + j;
        }
        return ((long) this.f6647f.length) + j;
    }

    public Header m10775h() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.f6645d);
    }

    public boolean m10772e() {
        return false;
    }

    public void m10767a(boolean z) {
        this.f6651j = z;
    }

    public boolean m10771d() {
        return this.f6651j;
    }

    public boolean m10773f() {
        return false;
    }

    public void m10763a(OutputStream outputStream) {
        this.f6652k = 0;
        this.f6653l = (long) ((int) m10768b());
        this.f6649h.writeTo(outputStream);
        m10752a((long) this.f6649h.size());
        for (FilePart a : this.f6648g) {
            a.m10750a(outputStream);
        }
        outputStream.write(this.f6647f);
        m10752a((long) this.f6647f.length);
    }

    public Header m10774g() {
        return null;
    }

    public void m10770c() {
        if (m10773f()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream m10762a() {
        throw new UnsupportedOperationException("getContent() is not supported. Use writeTo() instead.");
    }
}
