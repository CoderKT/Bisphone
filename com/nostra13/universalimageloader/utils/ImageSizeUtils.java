package com.nostra13.universalimageloader.utils;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import se.emilsjolander.stickylistheaders.C1128R;

public final class ImageSizeUtils {
    private static ImageSize f7091a;

    /* renamed from: com.nostra13.universalimageloader.utils.ImageSizeUtils.1 */
    /* synthetic */ class C09251 {
        static final /* synthetic */ int[] f7090a;

        static {
            f7090a = new int[ViewScaleType.values().length];
            try {
                f7090a[ViewScaleType.FIT_INSIDE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7090a[ViewScaleType.CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        f7091a = new ImageSize(max, max);
    }

    public static ImageSize m11265a(ImageAware imageAware, ImageSize imageSize) {
        int a = imageAware.m11226a();
        if (a <= 0) {
            a = imageSize.m11167a();
        }
        int b = imageAware.m11229b();
        if (b <= 0) {
            b = imageSize.m11170b();
        }
        return new ImageSize(a, b);
    }

    public static int m11264a(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int max;
        int i = 1;
        int a = imageSize.m11167a();
        int b = imageSize.m11170b();
        int a2 = imageSize2.m11167a();
        int b2 = imageSize2.m11170b();
        int i2;
        int i3;
        switch (C09251.f7090a[viewScaleType.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (!z) {
                    max = Math.max(a / a2, b / b2);
                    break;
                }
                i2 = a / 2;
                i3 = b / 2;
                max = 1;
                while (true) {
                    if (i2 / max <= a2 && i3 / max <= b2) {
                        break;
                    }
                    max *= 2;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (!z) {
                    max = Math.min(a / a2, b / b2);
                    break;
                }
                i2 = a / 2;
                i3 = b / 2;
                max = 1;
                while (i2 / max > a2 && i3 / max > b2) {
                    max *= 2;
                }
                break;
            default:
                max = 1;
                break;
        }
        if (max >= 1) {
            i = max;
        }
        return m11262a(a, b, i, z);
    }

    private static int m11262a(int i, int i2, int i3, boolean z) {
        int a = f7091a.m11167a();
        int b = f7091a.m11170b();
        while (true) {
            if (i / i3 <= a && i2 / i3 <= b) {
                return i3;
            }
            if (z) {
                i3 *= 2;
            } else {
                i3++;
            }
        }
    }

    public static int m11263a(ImageSize imageSize) {
        int a = imageSize.m11167a();
        int b = imageSize.m11170b();
        return Math.max((int) Math.ceil((double) (((float) a) / ((float) f7091a.m11167a()))), (int) Math.ceil((double) (((float) b) / ((float) f7091a.m11170b()))));
    }

    public static float m11266b(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int a = imageSize.m11167a();
        int b = imageSize.m11170b();
        int a2 = imageSize2.m11167a();
        int b2 = imageSize2.m11170b();
        float f = ((float) a) / ((float) a2);
        float f2 = ((float) b) / ((float) b2);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = (int) (((float) a) / f2);
            a2 = b2;
        } else {
            i = a2;
            a2 = (int) (((float) b) / f);
        }
        if ((z || i >= a || r1 >= b) && (!z || i == a || r1 == b)) {
            return 1.0f;
        }
        return ((float) i) / ((float) a);
    }
}
