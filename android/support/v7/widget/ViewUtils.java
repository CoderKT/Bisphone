package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

public class ViewUtils {
    private static Method f1919a;

    static {
        if (VERSION.SDK_INT >= 18) {
            try {
                f1919a = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[]{Rect.class, Rect.class});
                if (!f1919a.isAccessible()) {
                    f1919a.setAccessible(true);
                }
            } catch (NoSuchMethodException e) {
                Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    public static boolean m3901a(View view) {
        return ViewCompat.m1179h(view) == 1;
    }

    public static int m3899a(int i, int i2) {
        return i | i2;
    }

    public static void m3900a(View view, Rect rect, Rect rect2) {
        if (f1919a != null) {
            try {
                f1919a.invoke(view, new Object[]{rect, rect2});
            } catch (Throwable e) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e);
            }
        }
    }

    public static void m3902b(View view) {
        if (VERSION.SDK_INT >= 16) {
            try {
                Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                method.invoke(view, new Object[0]);
            } catch (NoSuchMethodException e) {
                Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            } catch (Throwable e2) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
            } catch (Throwable e22) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e22);
            }
        }
    }
}
