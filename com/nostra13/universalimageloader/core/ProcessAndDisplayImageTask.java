package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.utils.C0926L;

final class ProcessAndDisplayImageTask implements Runnable {
    private final ImageLoaderEngine f7004a;
    private final Bitmap f7005b;
    private final ImageLoadingInfo f7006c;
    private final Handler f7007d;

    public ProcessAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, Bitmap bitmap, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.f7004a = imageLoaderEngine;
        this.f7005b = bitmap;
        this.f7006c = imageLoadingInfo;
        this.f7007d = handler;
    }

    public void run() {
        C0926L.m11272a("PostProcess image before displaying [%s]", this.f7006c.f6972b);
        LoadAndDisplayImageTask.m11146a(new DisplayBitmapTask(this.f7006c.f6975e.m11065p().m11261a(this.f7005b), this.f7006c, this.f7004a, LoadedFrom.MEMORY_CACHE), this.f7006c.f6975e.m11068s(), this.f7007d, this.f7004a);
    }
}
