package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build.VERSION;

public class NavUtils {
    private static final NavUtilsImpl f270a;

    interface NavUtilsImpl {
        String m503a(Context context, ActivityInfo activityInfo);
    }

    class NavUtilsImplBase implements NavUtilsImpl {
        NavUtilsImplBase() {
        }

        public String m504a(Context context, ActivityInfo activityInfo) {
            if (activityInfo.metaData == null) {
                return null;
            }
            String string = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
            if (string == null) {
                return null;
            }
            if (string.charAt(0) == '.') {
                return context.getPackageName() + string;
            }
            return string;
        }
    }

    class NavUtilsImplJB extends NavUtilsImplBase {
        NavUtilsImplJB() {
        }

        public String m505a(Context context, ActivityInfo activityInfo) {
            String a = NavUtilsJB.m508a(activityInfo);
            if (a == null) {
                return super.m504a(context, activityInfo);
            }
            return a;
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f270a = new NavUtilsImplJB();
        } else {
            f270a = new NavUtilsImplBase();
        }
    }

    public static String m506a(Activity activity) {
        try {
            return m507a(activity, activity.getComponentName());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String m507a(Context context, ComponentName componentName) {
        return f270a.m503a(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }
}
