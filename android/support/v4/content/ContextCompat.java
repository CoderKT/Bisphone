package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;

public class ContextCompat {
    public static boolean m99a(Context context, Intent[] intentArr, Bundle bundle) {
        int i = VERSION.SDK_INT;
        if (i >= 16) {
            ContextCompatJellybean.m609a(context, intentArr, bundle);
            return true;
        } else if (i < 11) {
            return false;
        } else {
            ContextCompatHoneycomb.m608a(context, intentArr);
            return true;
        }
    }

    public static final Drawable m98a(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.m607a(context, i);
        }
        return context.getResources().getDrawable(i);
    }

    public static int m97a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
}
