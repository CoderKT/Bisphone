package com.nostra13.universalimageloader.cache.disc.impl.ext;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor;
import com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Snapshot;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LruDiskCache implements DiskCache {
    public static final CompressFormat f6834a;
    protected DiskLruCache f6835b;
    protected final FileNameGenerator f6836c;
    protected int f6837d;
    protected CompressFormat f6838e;
    protected int f6839f;
    private File f6840g;

    static {
        f6834a = CompressFormat.PNG;
    }

    public LruDiskCache(File file, File file2, FileNameGenerator fileNameGenerator, long j, int i) {
        this.f6837d = 32768;
        this.f6838e = f6834a;
        this.f6839f = 100;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (fileNameGenerator == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            long j2;
            int i2;
            if (j == 0) {
                j2 = Long.MAX_VALUE;
            } else {
                j2 = j;
            }
            if (i == 0) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = i;
            }
            this.f6840g = file2;
            this.f6836c = fileNameGenerator;
            m10957a(file, file2, j2, i2);
        }
    }

    private void m10957a(File file, File file2, long j, int i) {
        try {
            this.f6835b = DiskLruCache.m10931a(file, 1, 1, j, i);
        } catch (Throwable e) {
            C0926L.m11273a(e);
            if (file2 != null) {
                m10957a(file2, null, j, i);
            }
            if (this.f6835b == null) {
                throw e;
            }
        }
    }

    public File m10959a(String str) {
        Snapshot a;
        Throwable e;
        Throwable th;
        File file = null;
        try {
            a = this.f6835b.m10953a(m10958b(str));
            if (a != null) {
                try {
                    file = a.m10928a(0);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        C0926L.m11273a(e);
                        if (a != null) {
                            a.close();
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (IOException e3) {
            e = e3;
            a = file;
            C0926L.m11273a(e);
            if (a != null) {
                a.close();
            }
            return file;
        } catch (Throwable e4) {
            a = file;
            th = e4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return file;
    }

    public boolean m10961a(String str, InputStream inputStream, CopyListener copyListener) {
        boolean z = false;
        Editor b = this.f6835b.m10955b(m10958b(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.m10911a(0), this.f6837d);
            try {
                z = IoUtils.m11270a(inputStream, bufferedOutputStream, copyListener, this.f6837d);
                IoUtils.m11267a(bufferedOutputStream);
                if (z) {
                    b.m10912a();
                } else {
                    b.m10913b();
                }
            } catch (Throwable th) {
                IoUtils.m11267a(bufferedOutputStream);
                b.m10913b();
            }
        }
        return z;
    }

    public boolean m10960a(String str, Bitmap bitmap) {
        boolean z = false;
        Editor b = this.f6835b.m10955b(m10958b(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.m10911a(0), this.f6837d);
            try {
                z = bitmap.compress(this.f6838e, this.f6839f, bufferedOutputStream);
                if (z) {
                    b.m10912a();
                } else {
                    b.m10913b();
                }
            } finally {
                IoUtils.m11267a(bufferedOutputStream);
            }
        }
        return z;
    }

    private String m10958b(String str) {
        return this.f6836c.m10967a(str);
    }
}
