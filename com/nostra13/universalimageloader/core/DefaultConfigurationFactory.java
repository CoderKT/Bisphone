package com.nostra13.universalimageloader.core;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultConfigurationFactory {

    class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger f6854a;
        private final ThreadGroup f6855b;
        private final AtomicInteger f6856c;
        private final String f6857d;
        private final int f6858e;

        static {
            f6854a = new AtomicInteger(1);
        }

        DefaultThreadFactory(int i, String str) {
            this.f6856c = new AtomicInteger(1);
            this.f6858e = i;
            this.f6855b = Thread.currentThread().getThreadGroup();
            this.f6857d = str + f6854a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f6855b, runnable, this.f6857d + this.f6856c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.f6858e);
            return thread;
        }
    }

    public static Executor m10992a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (queueProcessingType == QueueProcessingType.LIFO ? 1 : null) != null ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue(), m10993a(i2, "uil-pool-"));
    }

    public static Executor m10991a() {
        return Executors.newCachedThreadPool(m10993a(5, "uil-pool-d-"));
    }

    public static FileNameGenerator m10994b() {
        return new HashCodeFileNameGenerator();
    }

    public static DiskCache m10987a(Context context, FileNameGenerator fileNameGenerator, long j, int i) {
        File b = m10995b(context);
        if (j > 0 || i > 0) {
            try {
                return new LruDiskCache(StorageUtils.m11284b(context), b, fileNameGenerator, j, i);
            } catch (Throwable e) {
                C0926L.m11273a(e);
            }
        }
        return new UnlimitedDiskCache(StorageUtils.m11281a(context), b, fileNameGenerator);
    }

    private static File m10995b(Context context) {
        File a = StorageUtils.m11283a(context, false);
        File file = new File(a, "uil-images");
        if (file.exists() || file.mkdir()) {
            return file;
        }
        return a;
    }

    public static MemoryCache m10988a(Context context, int i) {
        if (i == 0) {
            int a;
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int memoryClass = activityManager.getMemoryClass();
            if (m10998d() && m10997c(context)) {
                a = m10986a(activityManager);
            } else {
                a = memoryClass;
            }
            i = (a * 1048576) / 8;
        }
        return new LruMemoryCache(i);
    }

    private static boolean m10998d() {
        return VERSION.SDK_INT >= 11;
    }

    @TargetApi(11)
    private static boolean m10997c(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    @TargetApi(11)
    private static int m10986a(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    public static ImageDownloader m10990a(Context context) {
        return new BaseImageDownloader(context);
    }

    public static ImageDecoder m10989a(boolean z) {
        return new BaseImageDecoder(z);
    }

    public static BitmapDisplayer m10996c() {
        return new SimpleBitmapDisplayer();
    }

    private static ThreadFactory m10993a(int i, String str) {
        return new DefaultThreadFactory(i, str);
    }
}
