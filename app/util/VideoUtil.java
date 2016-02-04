package app.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import java.io.File;

public class VideoUtil {
    public static Dimension m7085a(Context context, String str) {
        Bitmap bitmap = null;
        String[] strArr = new String[]{"_id", "_data", "_display_name", "_size"};
        String[] strArr2 = new String[]{str};
        Cursor query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, "_data=?", strArr2, null);
        if (query.moveToFirst()) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 1;
            bitmap = Thumbnails.getThumbnail(context.getContentResolver(), (long) query.getInt(query.getColumnIndexOrThrow("_id")), 1, options);
        }
        if (bitmap != null) {
            return new Dimension(bitmap.getWidth(), bitmap.getHeight());
        }
        Dimension dimension = new Dimension(0, 0);
        Bitmap a = m7084a(new File(str));
        dimension = m7087b(str);
        if (a == null) {
            return dimension;
        }
        float height = ((float) a.getHeight()) / ((float) a.getWidth());
        float f = ((float) dimension.f4607b) / ((float) dimension.f4606a);
        if ((height <= 1.0f || f > 1.0f) && (height >= 1.0f || f < 1.0f)) {
            return dimension;
        }
        int i = dimension.f4607b;
        dimension.f4607b = dimension.f4606a;
        dimension.f4606a = i;
        return dimension;
    }

    private static Dimension m7087b(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        Integer valueOf = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19));
        Integer valueOf2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18));
        String extractMetadata = mediaMetadataRetriever.extractMetadata(24);
        if (extractMetadata == null || (!extractMetadata.equals("90") && !extractMetadata.equals("270"))) {
            return new Dimension(Integer.valueOf(valueOf2.intValue()).intValue(), Integer.valueOf(valueOf.intValue()).intValue());
        }
        return new Dimension(Integer.valueOf(valueOf.intValue()).intValue(), Integer.valueOf(valueOf2.intValue()).intValue());
    }

    public static int m7083a(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        return (int) (Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) / 1000);
    }

    public static String m7086a(int i) {
        String str;
        String str2;
        long j = (long) (i / 3600);
        long j2 = (((long) i) - (j * 3600)) / 60;
        long j3 = ((long) i) - ((3600 * j) + (60 * j2));
        String str3 = "";
        if (j <= 0) {
            str = str3;
        } else if (j < 10) {
            str = "0" + j + ":";
        } else {
            str = j + ":";
        }
        if (j2 <= 0) {
            str2 = "00:";
        } else if (j2 < 10) {
            str2 = "0" + j2 + ":";
        } else {
            str2 = j2 + ":";
        }
        if (j3 <= 0) {
            str3 = "00";
        } else if (j3 < 10) {
            str3 = "0" + j3 + "";
        } else {
            str3 = j3 + "";
        }
        return str + str2 + str3;
    }

    public static Bitmap m7084a(File file) {
        return ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), 1);
    }
}
