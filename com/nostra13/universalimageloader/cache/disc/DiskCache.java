package com.nostra13.universalimageloader.cache.disc;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import java.io.File;
import java.io.InputStream;

public interface DiskCache {
    File m10900a(String str);

    boolean m10901a(String str, Bitmap bitmap);

    boolean m10902a(String str, InputStream inputStream, CopyListener copyListener);
}
