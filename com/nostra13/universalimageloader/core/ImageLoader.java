package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

public class ImageLoader {
    public static final String f6906a;
    private static volatile ImageLoader f6907e;
    private ImageLoaderConfiguration f6908b;
    private ImageLoaderEngine f6909c;
    private ImageLoadingListener f6910d;

    class SyncImageLoadingListener extends SimpleImageLoadingListener {
        private Bitmap f6905a;

        private SyncImageLoadingListener() {
        }

        public void m11074a(String str, View view, Bitmap bitmap) {
            this.f6905a = bitmap;
        }

        public Bitmap m11073a() {
            return this.f6905a;
        }
    }

    static {
        f6906a = ImageLoader.class.getSimpleName();
    }

    public static ImageLoader m11076a() {
        if (f6907e == null) {
            synchronized (ImageLoader.class) {
                if (f6907e == null) {
                    f6907e = new ImageLoader();
                }
            }
        }
        return f6907e;
    }

    protected ImageLoader() {
        this.f6910d = new SimpleImageLoadingListener();
    }

    public synchronized void m11082a(ImageLoaderConfiguration imageLoaderConfiguration) {
        if (imageLoaderConfiguration == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.f6908b == null) {
            C0926L.m11272a("Initialize ImageLoader with configuration", new Object[0]);
            this.f6909c = new ImageLoaderEngine(imageLoaderConfiguration);
            this.f6908b = imageLoaderConfiguration;
        } else {
            C0926L.m11276c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public void m11088a(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        m11087a(str, imageAware, displayImageOptions, null, imageLoadingListener, imageLoadingProgressListener);
    }

    public void m11087a(String str, ImageAware imageAware, DisplayImageOptions displayImageOptions, ImageSize imageSize, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        m11077e();
        if (imageAware == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        ImageLoadingListener imageLoadingListener2;
        DisplayImageOptions displayImageOptions2;
        if (imageLoadingListener == null) {
            imageLoadingListener2 = this.f6910d;
        } else {
            imageLoadingListener2 = imageLoadingListener;
        }
        if (displayImageOptions == null) {
            displayImageOptions2 = this.f6908b.f6956r;
        } else {
            displayImageOptions2 = displayImageOptions;
        }
        if (TextUtils.isEmpty(str)) {
            this.f6909c.m11137b(imageAware);
            imageLoadingListener2.m5089a(str, imageAware.m11231d());
            if (displayImageOptions2.m11050b()) {
                imageAware.m11228a(displayImageOptions2.m11049b(this.f6908b.f6939a));
            } else {
                imageAware.m11228a(null);
            }
            imageLoadingListener2.m5090a(str, imageAware.m11231d(), null);
            return;
        }
        ImageSize a;
        if (imageSize == null) {
            a = ImageSizeUtils.m11265a(imageAware, this.f6908b.m11123a());
        } else {
            a = imageSize;
        }
        String a2 = MemoryCacheUtils.m11279a(str, a);
        this.f6909c.m11134a(imageAware, a2);
        imageLoadingListener2.m5089a(str, imageAware.m11231d());
        Bitmap a3 = this.f6908b.f6952n.m10969a(a2);
        if (a3 == null || a3.isRecycled()) {
            if (displayImageOptions2.m11048a()) {
                imageAware.m11228a(displayImageOptions2.m11047a(this.f6908b.f6939a));
            } else if (displayImageOptions2.m11056g()) {
                imageAware.m11228a(null);
            }
            LoadAndDisplayImageTask loadAndDisplayImageTask = new LoadAndDisplayImageTask(this.f6909c, new ImageLoadingInfo(str, imageAware, a, a2, displayImageOptions2, imageLoadingListener2, imageLoadingProgressListener, this.f6909c.m11130a(str)), m11075a(displayImageOptions2));
            if (displayImageOptions2.m11068s()) {
                loadAndDisplayImageTask.run();
                return;
            } else {
                this.f6909c.m11132a(loadAndDisplayImageTask);
                return;
            }
        }
        C0926L.m11272a("Load image from memory cache [%s]", a2);
        if (displayImageOptions2.m11054e()) {
            ProcessAndDisplayImageTask processAndDisplayImageTask = new ProcessAndDisplayImageTask(this.f6909c, a3, new ImageLoadingInfo(str, imageAware, a, a2, displayImageOptions2, imageLoadingListener2, imageLoadingProgressListener, this.f6909c.m11130a(str)), m11075a(displayImageOptions2));
            if (displayImageOptions2.m11068s()) {
                processAndDisplayImageTask.run();
                return;
            } else {
                this.f6909c.m11133a(processAndDisplayImageTask);
                return;
            }
        }
        displayImageOptions2.m11066q().m11207a(a3, imageAware, LoadedFrom.MEMORY_CACHE);
        imageLoadingListener2.m5090a(str, imageAware.m11231d(), a3);
    }

    public void m11083a(String str, ImageView imageView) {
        m11088a(str, new ImageViewAware(imageView), null, null, null);
    }

    public void m11084a(String str, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        m11088a(str, new ImageViewAware(imageView), null, imageLoadingListener, null);
    }

    public void m11085a(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener) {
        m11086a(str, imageSize, displayImageOptions, imageLoadingListener, null);
    }

    public void m11086a(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        DisplayImageOptions displayImageOptions2;
        m11077e();
        if (imageSize == null) {
            imageSize = this.f6908b.m11123a();
        }
        if (displayImageOptions == null) {
            displayImageOptions2 = this.f6908b.f6956r;
        } else {
            displayImageOptions2 = displayImageOptions;
        }
        m11088a(str, new NonViewAware(str, imageSize, ViewScaleType.CROP), displayImageOptions2, imageLoadingListener, imageLoadingProgressListener);
    }

    public Bitmap m11078a(String str) {
        return m11080a(str, null, null);
    }

    public Bitmap m11079a(String str, ImageSize imageSize) {
        return m11080a(str, imageSize, null);
    }

    public Bitmap m11080a(String str, ImageSize imageSize, DisplayImageOptions displayImageOptions) {
        if (displayImageOptions == null) {
            displayImageOptions = this.f6908b.f6956r;
        }
        DisplayImageOptions a = new Builder().m11020a(displayImageOptions).m11026d(true).m11023a();
        Object syncImageLoadingListener = new SyncImageLoadingListener();
        m11085a(str, imageSize, a, syncImageLoadingListener);
        return syncImageLoadingListener.m11073a();
    }

    private void m11077e() {
        if (this.f6908b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public void m11089b() {
        m11077e();
        this.f6908b.f6952n.m10973b();
    }

    public void m11081a(ImageView imageView) {
        this.f6909c.m11137b(new ImageViewAware(imageView));
    }

    public void m11090c() {
        this.f6909c.m11131a();
    }

    public void m11091d() {
        this.f6909c.m11136b();
    }

    private static Handler m11075a(DisplayImageOptions displayImageOptions) {
        Handler r = displayImageOptions.m11067r();
        if (displayImageOptions.m11068s()) {
            return null;
        }
        if (r == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return r;
    }
}
