package com.nostra13.universalimageloader.cache.disc.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class BaseDiskCache implements DiskCache {
    public static final CompressFormat f6790a;
    protected final File f6791b;
    protected final File f6792c;
    protected final FileNameGenerator f6793d;
    protected int f6794e;
    protected CompressFormat f6795f;
    protected int f6796g;

    static {
        f6790a = CompressFormat.PNG;
    }

    public BaseDiskCache(File file, File file2, FileNameGenerator fileNameGenerator) {
        this.f6794e = 32768;
        this.f6795f = f6790a;
        this.f6796g = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (fileNameGenerator == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.f6791b = file;
            this.f6792c = file2;
            this.f6793d = fileNameGenerator;
        }
    }

    public File m10903a(String str) {
        return m10906b(str);
    }

    public boolean m10905a(String str, InputStream inputStream, CopyListener copyListener) {
        Closeable bufferedOutputStream;
        boolean a;
        Throwable th;
        File b = m10906b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f6794e);
            a = IoUtils.m11270a(inputStream, bufferedOutputStream, copyListener, this.f6794e);
            try {
                IoUtils.m11267a(bufferedOutputStream);
                if (a && !file.renameTo(b)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                a = false;
                if (!a) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = false;
            if (a && !file.renameTo(b)) {
                a = false;
            }
            if (a) {
                file.delete();
            }
            throw th;
        }
    }

    public boolean m10904a(String str, Bitmap bitmap) {
        File b = m10906b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f6794e);
        try {
            boolean compress = bitmap.compress(this.f6795f, this.f6796g, bufferedOutputStream);
            IoUtils.m11267a(bufferedOutputStream);
            if (compress && !file.renameTo(b)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            IoUtils.m11267a(bufferedOutputStream);
            file.delete();
        }
    }

    protected File m10906b(String str) {
        String a = this.f6793d.m10967a(str);
        File file = this.f6791b;
        if (!(this.f6791b.exists() || this.f6791b.mkdirs() || this.f6792c == null || (!this.f6792c.exists() && !this.f6792c.mkdirs()))) {
            file = this.f6792c;
        }
        return new File(file, a);
    }
}
