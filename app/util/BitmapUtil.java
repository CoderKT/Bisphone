package app.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.util.Base64;
import app.Main;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import se.emilsjolander.stickylistheaders.C1128R;

public class BitmapUtil {
    public static Bitmap m6971a(String str, int i) {
        int i2;
        float f;
        ExifInterface exifInterface = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFile(str, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5;
        int i6;
        if (i3 >= i4) {
            i5 = i4;
            i6 = i3;
            i2 = 1;
            while (i6 / 2 > i) {
                i6 /= 2;
                i5 /= 2;
                i2 *= 2;
            }
            f = ((float) i) / ((float) i6);
        } else {
            i5 = i4;
            i6 = i3;
            i2 = 1;
            while (i5 / 2 > i) {
                i6 /= 2;
                i5 /= 2;
                i2 *= 2;
            }
            f = ((float) i) / ((float) i5);
        }
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = i2;
        options.inPreferredConfig = Config.ARGB_8888;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        try {
            exifInterface = new ExifInterface(str);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
        Bitmap createBitmap;
        if (i3 > i || i4 > i) {
            Matrix matrix = new Matrix();
            matrix.postScale(f, f);
            createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
            decodeFile.recycle();
            if (exifInterface == null) {
                return createBitmap;
            }
            decodeFile = m6970a(exifInterface, createBitmap);
            if (createBitmap == decodeFile) {
                return decodeFile;
            }
            createBitmap.recycle();
            return decodeFile;
        } else if (exifInterface == null) {
            return decodeFile;
        } else {
            createBitmap = m6970a(exifInterface, decodeFile);
            if (decodeFile != createBitmap) {
                decodeFile.recycle();
            }
            return createBitmap;
        }
    }

    public static Bitmap m6969a(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= i && height <= i) {
            return bitmap;
        }
        if (width >= height) {
            int i2 = (height * i) / width;
            width = i;
            i = i2;
        } else {
            width = (width * i) / height;
        }
        return Bitmap.createScaledBitmap(bitmap, width, i, false);
    }

    public static boolean m6975a(Bitmap bitmap, OutputStream outputStream) {
        return bitmap.compress(CompressFormat.JPEG, 90, outputStream);
    }

    private static String m6979b(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static String m6972a(Bitmap bitmap) {
        return m6979b(m6969a(bitmap, 120), 25);
    }

    public static String m6973a(File file) {
        return m6974a(file.toString());
    }

    public static String m6974a(String str) {
        Dimension c = m6980c(str);
        ExifInterface exifInterface = new ExifInterface(str);
        int max = Math.max(c.f4606a / 120, c.f4607b / 120);
        Options options = new Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = max;
        options.inPurgeable = true;
        Bitmap a = m6970a(exifInterface, BitmapFactory.decodeFile(str, options));
        Bitmap a2 = m6969a(a, 120);
        if (a2 != a) {
            a.recycle();
        }
        String b = m6979b(a2, 25);
        if (!(a2 == null || a2.isRecycled())) {
            a2.recycle();
        }
        return b;
    }

    public static String m6978b(Bitmap bitmap) {
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, min, min);
        int width = (extractThumbnail.getWidth() * 70) / 100;
        return m6979b(ThumbnailUtils.extractThumbnail(Bitmap.createBitmap(extractThumbnail, (extractThumbnail.getWidth() * 15) / 100, (extractThumbnail.getHeight() * 10) / 100, width, width), 200, 200), 25);
    }

    public static Bitmap m6976b(String str) {
        Bitmap bitmap = null;
        if (str == null) {
            return bitmap;
        }
        try {
            byte[] decode = Base64.decode(str.getBytes(), 2);
            Options options = new Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 1;
            options.inPurgeable = true;
            return BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
        } catch (Throwable e) {
            Main.f1926a.m5680b(e);
            return bitmap;
        }
    }

    public static Dimension m6977b(File file) {
        return m6980c(file.getAbsolutePath());
    }

    public static Dimension m6980c(String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        try {
            switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                    return new Dimension(i2, i);
            }
        } catch (Throwable e) {
            Main.f1926a.m5680b(e);
        }
        return new Dimension(i, i2);
    }

    public static Bitmap m6970a(ExifInterface exifInterface, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        switch (exifInterface.getAttributeInt("Orientation", 1)) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return bitmap;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                matrix.postRotate(180.0f);
                return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                matrix.postRotate(90.0f);
                return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                matrix.postRotate(270.0f);
                return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            default:
                Main.f1926a.m5683d("MediaPickerUtil: EXIF Orientation Undefined");
                return bitmap;
        }
    }

    public static String m6981d(String str) {
        File file;
        if (str == null) {
            try {
                return "";
            } catch (Throwable e) {
                Main.f1926a.m5678a(e);
                file = null;
            }
        } else {
            if (str.startsWith("file://")) {
                file = FileUtil.m7029e(str);
            } else {
                String d = FileUtil.m7028d(str);
                if (d == null) {
                    return "";
                }
                file = FileUtil.m7029e(d);
            }
            try {
                return m6973a(file);
            } catch (Throwable e2) {
                Main.f1926a.m5678a(e2);
                return "";
            }
        }
    }
}
