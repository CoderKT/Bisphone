package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BaseImageDecoder implements ImageDecoder {
    protected final boolean f1992a;

    public class ExifInfo {
        public final int f7053a;
        public final boolean f7054b;

        protected ExifInfo() {
            this.f7053a = 0;
            this.f7054b = false;
        }

        protected ExifInfo(int i, boolean z) {
            this.f7053a = i;
            this.f7054b = z;
        }
    }

    public class ImageFileInfo {
        public final ImageSize f7055a;
        public final ExifInfo f7056b;

        protected ImageFileInfo(ImageSize imageSize, ExifInfo exifInfo) {
            this.f7055a = imageSize;
            this.f7056b = exifInfo;
        }
    }

    public BaseImageDecoder(boolean z) {
        this.f1992a = z;
    }

    public Bitmap m3978a(ImageDecodingInfo imageDecodingInfo) {
        Closeable b = m3982b(imageDecodingInfo);
        if (b == null) {
            C0926L.m11277d("No stream for image [%s]", imageDecodingInfo.m11198a());
            return null;
        }
        try {
            ImageFileInfo a = m3981a((InputStream) b, imageDecodingInfo);
            b = m3983b(b, imageDecodingInfo);
            Bitmap decodeStream = BitmapFactory.decodeStream(b, null, m3979a(a.f7055a, imageDecodingInfo));
            if (decodeStream != null) {
                return m3977a(decodeStream, imageDecodingInfo, a.f7056b.f7053a, a.f7056b.f7054b);
            }
            C0926L.m11277d("Image can't be decoded [%s]", imageDecodingInfo.m11198a());
            return decodeStream;
        } finally {
            IoUtils.m11267a(b);
        }
    }

    protected InputStream m3982b(ImageDecodingInfo imageDecodingInfo) {
        return imageDecodingInfo.m11203f().m11120a(imageDecodingInfo.m11199b(), imageDecodingInfo.m11204g());
    }

    protected ImageFileInfo m3981a(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) {
        ExifInfo a;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String b = imageDecodingInfo.m11199b();
        if (imageDecodingInfo.m11205h() && m3976a(b, options.outMimeType)) {
            a = m3980a(b);
        } else {
            a = new ExifInfo();
        }
        return new ImageFileInfo(new ImageSize(options.outWidth, options.outHeight, a.f7053a), a);
    }

    private boolean m3976a(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && Scheme.m11222a(str) == Scheme.FILE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.nostra13.universalimageloader.core.decode.BaseImageDecoder.ExifInfo m3980a(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = new android.media.ExifInterface;	 Catch:{ IOException -> 0x002a }
        r3 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IOException -> 0x002a }
        r3 = r3.m11225c(r6);	 Catch:{ IOException -> 0x002a }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a }
        r3 = "Orientation";
        r4 = 1;
        r2 = r2.getAttributeInt(r3, r4);	 Catch:{ IOException -> 0x002a }
        switch(r2) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0018;
            case 3: goto L_0x0022;
            case 4: goto L_0x0023;
            case 5: goto L_0x0027;
            case 6: goto L_0x001e;
            case 7: goto L_0x001f;
            case 8: goto L_0x0026;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r2 = new com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo;
        r2.<init>(r1, r0);
        return r2;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        r1 = 90;
        goto L_0x0018;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        r1 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x0018;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x0018;
    L_0x002a:
        r2 = move-exception;
        r2 = "Can't read EXIF tags from file [%s]";
        r0 = new java.lang.Object[r0];
        r0[r1] = r6;
        com.nostra13.universalimageloader.utils.C0926L.m11276c(r2, r0);
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.decode.BaseImageDecoder.a(java.lang.String):com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo");
    }

    protected Options m3979a(ImageSize imageSize, ImageDecodingInfo imageDecodingInfo) {
        int i;
        ImageScaleType d = imageDecodingInfo.m11201d();
        if (d == ImageScaleType.NONE) {
            i = 1;
        } else if (d == ImageScaleType.NONE_SAFE) {
            i = ImageSizeUtils.m11263a(imageSize);
        } else {
            boolean z;
            ImageSize c = imageDecodingInfo.m11200c();
            if (d == ImageScaleType.IN_SAMPLE_POWER_OF_2) {
                z = true;
            } else {
                z = false;
            }
            i = ImageSizeUtils.m11264a(imageSize, c, imageDecodingInfo.m11202e(), z);
        }
        if (i > 1 && this.f1992a) {
            C0926L.m11272a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", imageSize, imageSize.m11169a(i), Integer.valueOf(i), imageDecodingInfo.m11198a());
        }
        Options i2 = imageDecodingInfo.m11206i();
        i2.inSampleSize = i;
        return i2;
    }

    protected InputStream m3983b(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException e) {
            }
        }
        IoUtils.m11267a((Closeable) inputStream);
        return m3982b(imageDecodingInfo);
    }

    protected Bitmap m3977a(Bitmap bitmap, ImageDecodingInfo imageDecodingInfo, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType d = imageDecodingInfo.m11201d();
        if (d == ImageScaleType.EXACTLY || d == ImageScaleType.EXACTLY_STRETCHED) {
            float b = ImageSizeUtils.m11266b(new ImageSize(bitmap.getWidth(), bitmap.getHeight(), i), imageDecodingInfo.m11200c(), imageDecodingInfo.m11202e(), d == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(b, 1.0f) != 0) {
                matrix.setScale(b, b);
                if (this.f1992a) {
                    C0926L.m11272a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", r2, r2.m11168a(b), Float.valueOf(b), imageDecodingInfo.m11198a());
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.f1992a) {
                C0926L.m11272a("Flip image horizontally [%s]", imageDecodingInfo.m11198a());
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.f1992a) {
                C0926L.m11272a("Rotate image on %1$d\u00b0 [%2$s]", Integer.valueOf(i), imageDecodingInfo.m11198a());
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
