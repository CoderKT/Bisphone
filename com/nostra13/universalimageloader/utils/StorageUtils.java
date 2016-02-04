package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public final class StorageUtils {
    public static File m11281a(Context context) {
        return m11283a(context, true);
    }

    public static File m11283a(Context context, boolean z) {
        Object externalStorageState;
        File file = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        } catch (IncompatibleClassChangeError e2) {
            externalStorageState = "";
        }
        if (z && "mounted".equals(r1) && m11286d(context)) {
            file = m11285c(context);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        C0926L.m11276c("Can't define system cache directory! '%s' will be used.", "/data/data/" + context.getPackageName() + "/cache/");
        return new File("/data/data/" + context.getPackageName() + "/cache/");
    }

    public static File m11284b(Context context) {
        return m11282a(context, "uil-images");
    }

    public static File m11282a(Context context, String str) {
        File a = m11281a(context);
        File file = new File(a, str);
        return (file.exists() || file.mkdir()) ? file : a;
    }

    private static File m11285c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), DataPacketExtension.ELEMENT), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (file.mkdirs()) {
            try {
                new File(file, ".nomedia").createNewFile();
                return file;
            } catch (IOException e) {
                C0926L.m11275b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
                return file;
            }
        }
        C0926L.m11276c("Unable to create external cache directory", new Object[0]);
        return null;
    }

    private static boolean m11286d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
