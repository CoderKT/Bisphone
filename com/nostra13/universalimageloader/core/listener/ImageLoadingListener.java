package com.nostra13.universalimageloader.core.listener;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;

public interface ImageLoadingListener {
    void m5089a(String str, View view);

    void m5090a(String str, View view, Bitmap bitmap);

    void m5091a(String str, View view, FailReason failReason);

    void m5092b(String str, View view);
}
