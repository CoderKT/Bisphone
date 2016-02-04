package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import java.util.concurrent.locks.ReentrantLock;

final class ImageLoadingInfo {
    final String f6971a;
    final String f6972b;
    final ImageAware f6973c;
    final ImageSize f6974d;
    final DisplayImageOptions f6975e;
    final ImageLoadingListener f6976f;
    final ImageLoadingProgressListener f6977g;
    final ReentrantLock f6978h;

    public ImageLoadingInfo(String str, ImageAware imageAware, ImageSize imageSize, String str2, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener, ReentrantLock reentrantLock) {
        this.f6971a = str;
        this.f6973c = imageAware;
        this.f6974d = imageSize;
        this.f6975e = displayImageOptions;
        this.f6976f = imageLoadingListener;
        this.f6977g = imageLoadingProgressListener;
        this.f6978h = reentrantLock;
        this.f6972b = str2;
    }
}
