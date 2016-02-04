package com.nostra13.universalimageloader.core.decode;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

public class ImageDecodingInfo {
    private final String f7057a;
    private final String f7058b;
    private final String f7059c;
    private final ImageSize f7060d;
    private final ImageScaleType f7061e;
    private final ViewScaleType f7062f;
    private final ImageDownloader f7063g;
    private final Object f7064h;
    private final boolean f7065i;
    private final Options f7066j;

    public ImageDecodingInfo(String str, String str2, String str3, ImageSize imageSize, ViewScaleType viewScaleType, ImageDownloader imageDownloader, DisplayImageOptions displayImageOptions) {
        this.f7057a = str;
        this.f7058b = str2;
        this.f7059c = str3;
        this.f7060d = imageSize;
        this.f7061e = displayImageOptions.m11059j();
        this.f7062f = viewScaleType;
        this.f7063g = imageDownloader;
        this.f7064h = displayImageOptions.m11063n();
        this.f7065i = displayImageOptions.m11062m();
        this.f7066j = new Options();
        m11195a(displayImageOptions.m11060k(), this.f7066j);
    }

    private void m11195a(Options options, Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            m11196b(options, options2);
        }
        if (VERSION.SDK_INT >= 11) {
            m11197c(options, options2);
        }
    }

    @TargetApi(10)
    private void m11196b(Options options, Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    private void m11197c(Options options, Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    public String m11198a() {
        return this.f7057a;
    }

    public String m11199b() {
        return this.f7058b;
    }

    public ImageSize m11200c() {
        return this.f7060d;
    }

    public ImageScaleType m11201d() {
        return this.f7061e;
    }

    public ViewScaleType m11202e() {
        return this.f7062f;
    }

    public ImageDownloader m11203f() {
        return this.f7063g;
    }

    public Object m11204g() {
        return this.f7064h;
    }

    public boolean m11205h() {
        return this.f7065i;
    }

    public Options m11206i() {
        return this.f7066j;
    }
}
