package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.Closeable;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask implements CopyListener, Runnable {
    final String f6987a;
    final ImageAware f6988b;
    final DisplayImageOptions f6989c;
    final ImageLoadingListener f6990d;
    final ImageLoadingProgressListener f6991e;
    private final ImageLoaderEngine f6992f;
    private final ImageLoadingInfo f6993g;
    private final Handler f6994h;
    private final ImageLoaderConfiguration f6995i;
    private final ImageDownloader f6996j;
    private final ImageDownloader f6997k;
    private final ImageDownloader f6998l;
    private final ImageDecoder f6999m;
    private final String f7000n;
    private final ImageSize f7001o;
    private final boolean f7002p;
    private LoadedFrom f7003q;

    /* renamed from: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.1 */
    class C09191 implements Runnable {
        final /* synthetic */ int f6979a;
        final /* synthetic */ int f6980b;
        final /* synthetic */ LoadAndDisplayImageTask f6981c;

        C09191(LoadAndDisplayImageTask loadAndDisplayImageTask, int i, int i2) {
            this.f6981c = loadAndDisplayImageTask;
            this.f6979a = i;
            this.f6980b = i2;
        }

        public void run() {
            this.f6981c.f6991e.m11260a(this.f6981c.f6987a, this.f6981c.f6988b.m11231d(), this.f6979a, this.f6980b);
        }
    }

    /* renamed from: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.2 */
    class C09202 implements Runnable {
        final /* synthetic */ FailType f6982a;
        final /* synthetic */ Throwable f6983b;
        final /* synthetic */ LoadAndDisplayImageTask f6984c;

        C09202(LoadAndDisplayImageTask loadAndDisplayImageTask, FailType failType, Throwable th) {
            this.f6984c = loadAndDisplayImageTask;
            this.f6982a = failType;
            this.f6983b = th;
        }

        public void run() {
            if (this.f6984c.f6989c.m11052c()) {
                this.f6984c.f6988b.m11228a(this.f6984c.f6989c.m11051c(this.f6984c.f6995i.f6939a));
            }
            this.f6984c.f6990d.m5091a(this.f6984c.f6987a, this.f6984c.f6988b.m11231d(), new FailReason(this.f6982a, this.f6983b));
        }
    }

    /* renamed from: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.3 */
    class C09213 implements Runnable {
        final /* synthetic */ LoadAndDisplayImageTask f6985a;

        C09213(LoadAndDisplayImageTask loadAndDisplayImageTask) {
            this.f6985a = loadAndDisplayImageTask;
        }

        public void run() {
            this.f6985a.f6990d.m5092b(this.f6985a.f6987a, this.f6985a.f6988b.m11231d());
        }
    }

    class TaskCancelledException extends Exception {
        final /* synthetic */ LoadAndDisplayImageTask f6986a;

        TaskCancelledException(LoadAndDisplayImageTask loadAndDisplayImageTask) {
            this.f6986a = loadAndDisplayImageTask;
        }
    }

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.f7003q = LoadedFrom.NETWORK;
        this.f6992f = imageLoaderEngine;
        this.f6993g = imageLoadingInfo;
        this.f6994h = handler;
        this.f6995i = imageLoaderEngine.f6961a;
        this.f6996j = this.f6995i.f6954p;
        this.f6997k = this.f6995i.f6957s;
        this.f6998l = this.f6995i.f6958t;
        this.f6999m = this.f6995i.f6955q;
        this.f6987a = imageLoadingInfo.f6971a;
        this.f7000n = imageLoadingInfo.f6972b;
        this.f6988b = imageLoadingInfo.f6973c;
        this.f7001o = imageLoadingInfo.f6974d;
        this.f6989c = imageLoadingInfo.f6975e;
        this.f6990d = imageLoadingInfo.f6976f;
        this.f6991e = imageLoadingInfo.f6977g;
        this.f7002p = this.f6989c.m11068s();
    }

    public void run() {
        if (!m11147b() && !m11149c()) {
            ReentrantLock reentrantLock = this.f6993g.f6978h;
            C0926L.m11272a("Start display image task [%s]", this.f7000n);
            if (reentrantLock.isLocked()) {
                C0926L.m11272a("Image already is loading. Waiting... [%s]", this.f7000n);
            }
            reentrantLock.lock();
            try {
                m11156i();
                Bitmap a = this.f6995i.f6952n.m10969a(this.f7000n);
                if (a == null || a.isRecycled()) {
                    a = m11151d();
                    if (a != null) {
                        m11156i();
                        m11162o();
                        if (this.f6989c.m11053d()) {
                            C0926L.m11272a("PreProcess image before caching in memory [%s]", this.f7000n);
                            a = this.f6989c.m11064o().m11261a(a);
                            if (a == null) {
                                C0926L.m11277d("Pre-processor returned null [%s]", this.f7000n);
                            }
                        }
                        if (a != null && this.f6989c.m11057h()) {
                            C0926L.m11272a("Cache image in memory [%s]", this.f7000n);
                            this.f6995i.f6952n.m10971a(this.f7000n, a);
                        }
                    } else {
                        return;
                    }
                }
                this.f7003q = LoadedFrom.MEMORY_CACHE;
                C0926L.m11272a("...Get cached bitmap from memory after waiting. [%s]", this.f7000n);
                if (a != null && this.f6989c.m11054e()) {
                    C0926L.m11272a("PostProcess image before displaying [%s]", this.f7000n);
                    a = this.f6989c.m11065p().m11261a(a);
                    if (a == null) {
                        C0926L.m11277d("Post-processor returned null [%s]", this.f7000n);
                    }
                }
                m11156i();
                m11162o();
                reentrantLock.unlock();
                m11146a(new DisplayBitmapTask(a, this.f6993g, this.f6992f, this.f7003q), this.f7002p, this.f6994h, this.f6992f);
            } catch (TaskCancelledException e) {
                m11154g();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private boolean m11147b() {
        AtomicBoolean c = this.f6992f.m11138c();
        if (c.get()) {
            synchronized (this.f6992f.m11139d()) {
                if (c.get()) {
                    C0926L.m11272a("ImageLoader is paused. Waiting...  [%s]", this.f7000n);
                    try {
                        this.f6992f.m11139d().wait();
                        C0926L.m11272a(".. Resume loading [%s]", this.f7000n);
                    } catch (InterruptedException e) {
                        C0926L.m11277d("Task was interrupted [%s]", this.f7000n);
                        return true;
                    }
                }
            }
        }
        return m11157j();
    }

    private boolean m11149c() {
        if (!this.f6989c.m11055f()) {
            return false;
        }
        C0926L.m11272a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.f6989c.m11061l()), this.f7000n);
        try {
            Thread.sleep((long) this.f6989c.m11061l());
            return m11157j();
        } catch (InterruptedException e) {
            C0926L.m11277d("Task was interrupted [%s]", this.f7000n);
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap m11151d() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.f6995i;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.f6953o;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = r7.f6987a;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.m10900a(r2);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x00d9;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x0013:
        r2 = r0.length();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d9;
    L_0x001d:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r5 = r7.f7000n;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        com.nostra13.universalimageloader.utils.C0926L.m11272a(r2, r3);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.f7003q = r2;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.m11156i();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r2.m11224b(r0);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r7.m11143a(r0);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
    L_0x003f:
        if (r0 == 0) goto L_0x004d;
    L_0x0041:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x004d;
    L_0x0047:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x004d:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = 0;
        r5 = r7.f7000n;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        com.nostra13.universalimageloader.utils.C0926L.m11272a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r7.f7003q = r2;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r7.f6987a;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r7.f6989c;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.m11058i();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x0068:
        r3 = r7.m11152e();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x006e:
        r3 = r7.f6995i;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.f6953o;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = r7.f6987a;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.m10900a(r4);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x007a:
        r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r2.m11224b(r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x0084:
        r7.m11156i();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r0 = r7.m11143a(r2);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r0 == 0) goto L_0x0099;
    L_0x008d:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x0099;
    L_0x0093:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x0099:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = 0;
        r7.m11145a(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x009f:
        return r0;
    L_0x00a0:
        r0 = move-exception;
        r0 = r1;
    L_0x00a2:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.NETWORK_DENIED;
        r7.m11145a(r2, r1);
        goto L_0x009f;
    L_0x00a8:
        r0 = move-exception;
        throw r0;
    L_0x00aa:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00ae:
        com.nostra13.universalimageloader.utils.C0926L.m11273a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR;
        r7.m11145a(r2, r1);
        goto L_0x009f;
    L_0x00b7:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00bb:
        com.nostra13.universalimageloader.utils.C0926L.m11273a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY;
        r7.m11145a(r2, r1);
        goto L_0x009f;
    L_0x00c4:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00c8:
        com.nostra13.universalimageloader.utils.C0926L.m11273a(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.UNKNOWN;
        r7.m11145a(r2, r1);
        goto L_0x009f;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00c8;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x00bb;
    L_0x00d5:
        r1 = move-exception;
        goto L_0x00ae;
    L_0x00d7:
        r2 = move-exception;
        goto L_0x00a2;
    L_0x00d9:
        r0 = r1;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.d():android.graphics.Bitmap");
    }

    private Bitmap m11143a(String str) {
        String str2 = str;
        return this.f6999m.m3975a(new ImageDecodingInfo(this.f7000n, str2, this.f6987a, this.f7001o, this.f6988b.m11230c(), m11155h(), this.f6989c));
    }

    private boolean m11152e() {
        C0926L.m11272a("Cache image on disk [%s]", this.f7000n);
        try {
            boolean f = m11153f();
            if (!f) {
                return f;
            }
            int i = this.f6995i.f6942d;
            int i2 = this.f6995i.f6943e;
            if (i <= 0 && i2 <= 0) {
                return f;
            }
            C0926L.m11272a("Resize image in disk cache [%s]", this.f7000n);
            m11148b(i, i2);
            return f;
        } catch (Throwable e) {
            C0926L.m11273a(e);
            return false;
        }
    }

    private boolean m11153f() {
        boolean z = false;
        Closeable a = m11155h().m11120a(this.f6987a, this.f6989c.m11063n());
        if (a == null) {
            C0926L.m11277d("No stream for image [%s]", this.f7000n);
        } else {
            try {
                z = this.f6995i.f6953o.m10902a(this.f6987a, a, this);
            } finally {
                IoUtils.m11267a(a);
            }
        }
        return z;
    }

    private boolean m11148b(int i, int i2) {
        File a = this.f6995i.f6953o.m10900a(this.f6987a);
        if (a != null && a.exists()) {
            Bitmap a2 = this.f6999m.m3975a(new ImageDecodingInfo(this.f7000n, Scheme.FILE.m11224b(a.getAbsolutePath()), this.f6987a, new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, m11155h(), new Builder().m11020a(this.f6989c).m11021a(ImageScaleType.IN_SAMPLE_INT).m11023a()));
            if (!(a2 == null || this.f6995i.f6944f == null)) {
                C0926L.m11272a("Process image before cache on disk [%s]", this.f7000n);
                a2 = this.f6995i.f6944f.m11261a(a2);
                if (a2 == null) {
                    C0926L.m11277d("Bitmap processor for disk cache returned null [%s]", this.f7000n);
                }
            }
            Bitmap bitmap = a2;
            if (bitmap != null) {
                boolean a3 = this.f6995i.f6953o.m10901a(this.f6987a, bitmap);
                bitmap.recycle();
                return a3;
            }
        }
        return false;
    }

    public boolean m11165a(int i, int i2) {
        return this.f7002p || m11150c(i, i2);
    }

    private boolean m11150c(int i, int i2) {
        if (m11163p() || m11157j()) {
            return false;
        }
        if (this.f6991e != null) {
            m11146a(new C09191(this, i, i2), false, this.f6994h, this.f6992f);
        }
        return true;
    }

    private void m11145a(FailType failType, Throwable th) {
        if (!this.f7002p && !m11163p() && !m11157j()) {
            m11146a(new C09202(this, failType, th), false, this.f6994h, this.f6992f);
        }
    }

    private void m11154g() {
        if (!this.f7002p && !m11163p()) {
            m11146a(new C09213(this), false, this.f6994h, this.f6992f);
        }
    }

    private ImageDownloader m11155h() {
        if (this.f6992f.m11140e()) {
            return this.f6997k;
        }
        if (this.f6992f.m11141f()) {
            return this.f6998l;
        }
        return this.f6996j;
    }

    private void m11156i() {
        m11158k();
        m11160m();
    }

    private boolean m11157j() {
        return m11159l() || m11161n();
    }

    private void m11158k() {
        if (m11159l()) {
            throw new TaskCancelledException(this);
        }
    }

    private boolean m11159l() {
        if (!this.f6988b.m11232e()) {
            return false;
        }
        C0926L.m11272a("ImageAware was collected by GC. Task is cancelled. [%s]", this.f7000n);
        return true;
    }

    private void m11160m() {
        if (m11161n()) {
            throw new TaskCancelledException(this);
        }
    }

    private boolean m11161n() {
        if (!(!this.f7000n.equals(this.f6992f.m11129a(this.f6988b)))) {
            return false;
        }
        C0926L.m11272a("ImageAware is reused for another image. Task is cancelled. [%s]", this.f7000n);
        return true;
    }

    private void m11162o() {
        if (m11163p()) {
            throw new TaskCancelledException(this);
        }
    }

    private boolean m11163p() {
        if (!Thread.interrupted()) {
            return false;
        }
        C0926L.m11272a("Task was interrupted [%s]", this.f7000n);
        return true;
    }

    String m11164a() {
        return this.f6987a;
    }

    static void m11146a(Runnable runnable, boolean z, Handler handler, ImageLoaderEngine imageLoaderEngine) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            imageLoaderEngine.m11135a(runnable);
        } else {
            handler.post(runnable);
        }
    }
}
