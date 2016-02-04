package com.nostra13.universalimageloader.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.FuzzyKeyMemoryCache;
import com.nostra13.universalimageloader.core.assist.FlushedInputStream;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import java.io.InputStream;
import java.util.concurrent.Executor;
import se.emilsjolander.stickylistheaders.C1128R;

public final class ImageLoaderConfiguration {
    final Resources f6939a;
    final int f6940b;
    final int f6941c;
    final int f6942d;
    final int f6943e;
    final BitmapProcessor f6944f;
    final Executor f6945g;
    final Executor f6946h;
    final boolean f6947i;
    final boolean f6948j;
    final int f6949k;
    final int f6950l;
    final QueueProcessingType f6951m;
    final MemoryCache f6952n;
    final DiskCache f6953o;
    final ImageDownloader f6954p;
    final ImageDecoder f6955q;
    final DisplayImageOptions f6956r;
    final ImageDownloader f6957s;
    final ImageDownloader f6958t;

    /* renamed from: com.nostra13.universalimageloader.core.ImageLoaderConfiguration.1 */
    /* synthetic */ class C09171 {
        static final /* synthetic */ int[] f6911a;

        static {
            f6911a = new int[Scheme.values().length];
            try {
                f6911a[Scheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6911a[Scheme.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public class Builder {
        public static final QueueProcessingType f6912a;
        private Context f6913b;
        private int f6914c;
        private int f6915d;
        private int f6916e;
        private int f6917f;
        private BitmapProcessor f6918g;
        private Executor f6919h;
        private Executor f6920i;
        private boolean f6921j;
        private boolean f6922k;
        private int f6923l;
        private int f6924m;
        private boolean f6925n;
        private QueueProcessingType f6926o;
        private int f6927p;
        private long f6928q;
        private int f6929r;
        private MemoryCache f6930s;
        private DiskCache f6931t;
        private FileNameGenerator f6932u;
        private ImageDownloader f6933v;
        private ImageDecoder f6934w;
        private DisplayImageOptions f6935x;
        private boolean f6936y;

        static {
            f6912a = QueueProcessingType.FIFO;
        }

        public Builder(Context context) {
            this.f6914c = 0;
            this.f6915d = 0;
            this.f6916e = 0;
            this.f6917f = 0;
            this.f6918g = null;
            this.f6919h = null;
            this.f6920i = null;
            this.f6921j = false;
            this.f6922k = false;
            this.f6923l = 3;
            this.f6924m = 3;
            this.f6925n = false;
            this.f6926o = f6912a;
            this.f6927p = 0;
            this.f6928q = 0;
            this.f6929r = 0;
            this.f6930s = null;
            this.f6931t = null;
            this.f6932u = null;
            this.f6933v = null;
            this.f6935x = null;
            this.f6936y = false;
            this.f6913b = context.getApplicationContext();
        }

        public Builder m11112a(int i) {
            if (!(this.f6919h == null && this.f6920i == null)) {
                C0926L.m11276c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.f6923l = i;
            return this;
        }

        public Builder m11119b(int i) {
            if (!(this.f6919h == null && this.f6920i == null)) {
                C0926L.m11276c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            if (i < 1) {
                this.f6924m = 1;
            } else if (i > 10) {
                this.f6924m = 10;
            } else {
                this.f6924m = i;
            }
            return this;
        }

        public Builder m11116a(QueueProcessingType queueProcessingType) {
            if (!(this.f6919h == null && this.f6920i == null)) {
                C0926L.m11276c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.f6926o = queueProcessingType;
            return this;
        }

        public Builder m11114a(MemoryCache memoryCache) {
            if (this.f6927p != 0) {
                C0926L.m11276c("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            this.f6930s = memoryCache;
            return this;
        }

        public Builder m11113a(DiskCache diskCache) {
            if (this.f6928q > 0 || this.f6929r > 0) {
                C0926L.m11276c("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other", new Object[0]);
            }
            if (this.f6932u != null) {
                C0926L.m11276c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }
            this.f6931t = diskCache;
            return this;
        }

        public Builder m11117a(ImageDecoder imageDecoder) {
            this.f6934w = imageDecoder;
            return this;
        }

        public Builder m11115a(DisplayImageOptions displayImageOptions) {
            this.f6935x = displayImageOptions;
            return this;
        }

        public ImageLoaderConfiguration m11118a() {
            m11094b();
            return new ImageLoaderConfiguration();
        }

        private void m11094b() {
            if (this.f6919h == null) {
                this.f6919h = DefaultConfigurationFactory.m10992a(this.f6923l, this.f6924m, this.f6926o);
            } else {
                this.f6921j = true;
            }
            if (this.f6920i == null) {
                this.f6920i = DefaultConfigurationFactory.m10992a(this.f6923l, this.f6924m, this.f6926o);
            } else {
                this.f6922k = true;
            }
            if (this.f6931t == null) {
                if (this.f6932u == null) {
                    this.f6932u = DefaultConfigurationFactory.m10994b();
                }
                this.f6931t = DefaultConfigurationFactory.m10987a(this.f6913b, this.f6932u, this.f6928q, this.f6929r);
            }
            if (this.f6930s == null) {
                this.f6930s = DefaultConfigurationFactory.m10988a(this.f6913b, this.f6927p);
            }
            if (this.f6925n) {
                this.f6930s = new FuzzyKeyMemoryCache(this.f6930s, MemoryCacheUtils.m11280a());
            }
            if (this.f6933v == null) {
                this.f6933v = DefaultConfigurationFactory.m10990a(this.f6913b);
            }
            if (this.f6934w == null) {
                this.f6934w = DefaultConfigurationFactory.m10989a(this.f6936y);
            }
            if (this.f6935x == null) {
                this.f6935x = DisplayImageOptions.m11046t();
            }
        }
    }

    class NetworkDeniedImageDownloader implements ImageDownloader {
        private final ImageDownloader f6937a;

        public NetworkDeniedImageDownloader(ImageDownloader imageDownloader) {
            this.f6937a = imageDownloader;
        }

        public InputStream m11121a(String str, Object obj) {
            switch (C09171.f6911a[Scheme.m11222a(str).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    throw new IllegalStateException();
                default:
                    return this.f6937a.m11120a(str, obj);
            }
        }
    }

    class SlowNetworkImageDownloader implements ImageDownloader {
        private final ImageDownloader f6938a;

        public SlowNetworkImageDownloader(ImageDownloader imageDownloader) {
            this.f6938a = imageDownloader;
        }

        public InputStream m11122a(String str, Object obj) {
            InputStream a = this.f6938a.m11120a(str, obj);
            switch (C09171.f6911a[Scheme.m11222a(str).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return new FlushedInputStream(a);
                default:
                    return a;
            }
        }
    }

    private ImageLoaderConfiguration(Builder builder) {
        this.f6939a = builder.f6913b.getResources();
        this.f6940b = builder.f6914c;
        this.f6941c = builder.f6915d;
        this.f6942d = builder.f6916e;
        this.f6943e = builder.f6917f;
        this.f6944f = builder.f6918g;
        this.f6945g = builder.f6919h;
        this.f6946h = builder.f6920i;
        this.f6949k = builder.f6923l;
        this.f6950l = builder.f6924m;
        this.f6951m = builder.f6926o;
        this.f6953o = builder.f6931t;
        this.f6952n = builder.f6930s;
        this.f6956r = builder.f6935x;
        this.f6954p = builder.f6933v;
        this.f6955q = builder.f6934w;
        this.f6947i = builder.f6921j;
        this.f6948j = builder.f6922k;
        this.f6957s = new NetworkDeniedImageDownloader(this.f6954p);
        this.f6958t = new SlowNetworkImageDownloader(this.f6954p);
        C0926L.m11274a(builder.f6936y);
    }

    ImageSize m11123a() {
        DisplayMetrics displayMetrics = this.f6939a.getDisplayMetrics();
        int i = this.f6940b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.f6941c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new ImageSize(i, i2);
    }
}
