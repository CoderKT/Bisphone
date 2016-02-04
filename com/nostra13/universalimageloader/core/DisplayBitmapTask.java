package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.C0926L;

final class DisplayBitmapTask implements Runnable {
    private final Bitmap f6859a;
    private final String f6860b;
    private final ImageAware f6861c;
    private final String f6862d;
    private final BitmapDisplayer f6863e;
    private final ImageLoadingListener f6864f;
    private final ImageLoaderEngine f6865g;
    private final LoadedFrom f6866h;

    public DisplayBitmapTask(Bitmap bitmap, ImageLoadingInfo imageLoadingInfo, ImageLoaderEngine imageLoaderEngine, LoadedFrom loadedFrom) {
        this.f6859a = bitmap;
        this.f6860b = imageLoadingInfo.f6971a;
        this.f6861c = imageLoadingInfo.f6973c;
        this.f6862d = imageLoadingInfo.f6972b;
        this.f6863e = imageLoadingInfo.f6975e.m11066q();
        this.f6864f = imageLoadingInfo.f6976f;
        this.f6865g = imageLoaderEngine;
        this.f6866h = loadedFrom;
    }

    public void run() {
        if (this.f6861c.m11232e()) {
            C0926L.m11272a("ImageAware was collected by GC. Task is cancelled. [%s]", this.f6862d);
            this.f6864f.m5092b(this.f6860b, this.f6861c.m11231d());
        } else if (m10999a()) {
            C0926L.m11272a("ImageAware is reused for another image. Task is cancelled. [%s]", this.f6862d);
            this.f6864f.m5092b(this.f6860b, this.f6861c.m11231d());
        } else {
            C0926L.m11272a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.f6866h, this.f6862d);
            this.f6863e.m11207a(this.f6859a, this.f6861c, this.f6866h);
            this.f6865g.m11137b(this.f6861c);
            this.f6864f.m5090a(this.f6860b, this.f6861c.m11231d(), this.f6859a);
        }
    }

    private boolean m10999a() {
        return !this.f6862d.equals(this.f6865g.m11129a(this.f6861c));
    }
}
