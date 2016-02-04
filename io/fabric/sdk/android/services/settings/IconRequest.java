package io.fabric.sdk.android.services.settings;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;

public class IconRequest {
    public final String f8413a;
    public final int f8414b;
    public final int f8415c;
    public final int f8416d;

    public IconRequest(String str, int i, int i2, int i3) {
        this.f8413a = str;
        this.f8414b = i;
        this.f8415c = i2;
        this.f8416d = i3;
    }

    public static IconRequest m13291a(Context context, String str) {
        if (str != null) {
            try {
                int l = CommonUtils.m13037l(context);
                Fabric.m12905g().m12867a("Fabric", "App icon resource ID is " + l);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), l, options);
                return new IconRequest(str, l, options.outWidth, options.outHeight);
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Failed to load icon", e);
            }
        }
        return null;
    }
}
