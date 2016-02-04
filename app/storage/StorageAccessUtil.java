package app.storage;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import app.Main;

public class StorageAccessUtil {
    @SuppressLint({"NewApi"})
    public static String m6962a(Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : 0) == 0 || !DocumentsContract.isDocumentUri(Main.f1927b, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (m6966e(uri)) {
                    return uri.getLastPathSegment();
                }
                return m6961a(Main.f1927b, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            } else {
                return null;
            }
        } else if (m6963b(uri)) {
            r1 = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(r1[0])) {
                return Environment.getExternalStorageDirectory() + "/" + r1[1];
            }
            return null;
        } else if (m6964c(uri)) {
            return m6961a(Main.f1927b, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (!m6965d(uri)) {
            return null;
        } else {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if ("image".equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String str = "_id=?";
            return m6961a(Main.f1927b, uri2, "_id=?", new String[]{r1[1]});
        }
    }

    public static String m6961a(Context context, Uri uri, String str, String[] strArr) {
        Throwable th;
        Cursor cursor = null;
        String str2 = "_data";
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query == null) {
                            return str2;
                        }
                        query.close();
                        return str2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean m6963b(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean m6964c(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean m6965d(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean m6966e(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
