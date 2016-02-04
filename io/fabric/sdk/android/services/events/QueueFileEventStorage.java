package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.QueueFile;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueueFileEventStorage implements EventsStorage {
    private final Context f8324a;
    private final File f8325b;
    private final String f8326c;
    private final File f8327d;
    private QueueFile f8328e;
    private File f8329f;

    public QueueFileEventStorage(Context context, File file, String str, String str2) {
        this.f8324a = context;
        this.f8325b = file;
        this.f8326c = str2;
        this.f8327d = new File(this.f8325b, str);
        this.f8328e = new QueueFile(this.f8327d);
        m13148e();
    }

    private void m13148e() {
        this.f8329f = new File(this.f8325b, this.f8326c);
        if (!this.f8329f.exists()) {
            this.f8329f.mkdirs();
        }
    }

    public void m13154a(byte[] bArr) {
        this.f8328e.m13100a(bArr);
    }

    public int m13149a() {
        return this.f8328e.m13098a();
    }

    public void m13152a(String str) {
        this.f8328e.close();
        m13147a(this.f8327d, new File(this.f8329f, str));
        this.f8328e = new QueueFile(this.f8327d);
    }

    private void m13147a(File file, File file2) {
        Closeable fileInputStream;
        Throwable th;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                closeable = m13150a(file2);
                CommonUtils.m13018a((InputStream) fileInputStream, (OutputStream) closeable, new byte[1024]);
                CommonUtils.m13016a(fileInputStream, "Failed to close file input stream");
                CommonUtils.m13016a(closeable, "Failed to close output stream");
                file.delete();
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.m13016a(fileInputStream, "Failed to close file input stream");
                CommonUtils.m13016a(closeable, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            CommonUtils.m13016a(fileInputStream, "Failed to close file input stream");
            CommonUtils.m13016a(closeable, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    public OutputStream m13150a(File file) {
        return new FileOutputStream(file);
    }

    public List<File> m13151a(int i) {
        List<File> arrayList = new ArrayList();
        for (Object add : this.f8329f.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    public void m13153a(List<File> list) {
        for (File file : list) {
            CommonUtils.m13014a(this.f8324a, String.format("deleting sent analytics file %s", new Object[]{file.getName()}));
            file.delete();
        }
    }

    public List<File> m13157c() {
        return Arrays.asList(this.f8329f.listFiles());
    }

    public void m13158d() {
        try {
            this.f8328e.close();
        } catch (IOException e) {
        }
        this.f8327d.delete();
    }

    public boolean m13156b() {
        return this.f8328e.m13103b();
    }

    public boolean m13155a(int i, int i2) {
        return this.f8328e.m13102a(i, i2);
    }
}
