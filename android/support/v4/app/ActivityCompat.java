package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat21.SharedElementCallback21;
import android.support.v4.content.ContextCompat;
import android.view.View;
import java.util.List;
import java.util.Map;

public class ActivityCompat extends ContextCompat {

    /* renamed from: android.support.v4.app.ActivityCompat.1 */
    final class C00011 implements Runnable {
        final /* synthetic */ String[] f14a;
        final /* synthetic */ Activity f15b;
        final /* synthetic */ int f16c;

        C00011(String[] strArr, Activity activity, int i) {
            this.f14a = strArr;
            this.f15b = activity;
            this.f16c = i;
        }

        public void run() {
            int[] iArr = new int[this.f14a.length];
            PackageManager packageManager = this.f15b.getPackageManager();
            String packageName = this.f15b.getPackageName();
            int length = this.f14a.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.f14a[i], packageName);
            }
            ((OnRequestPermissionsResultCallback) this.f15b).onRequestPermissionsResult(this.f16c, this.f14a, iArr);
        }
    }

    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    class SharedElementCallback21Impl extends SharedElementCallback21 {
        private SharedElementCallback f17a;

        public SharedElementCallback21Impl(SharedElementCallback sharedElementCallback) {
            this.f17a = sharedElementCallback;
        }

        public void m89a(List<String> list, List<View> list2, List<View> list3) {
            this.f17a.m600a((List) list, (List) list2, (List) list3);
        }

        public void m91b(List<String> list, List<View> list2, List<View> list3) {
            this.f17a.m602b(list, list2, list3);
        }

        public void m88a(List<View> list) {
            this.f17a.m599a((List) list);
        }

        public void m90a(List<String> list, Map<String, View> map) {
            this.f17a.m601a((List) list, (Map) map);
        }

        public Parcelable m86a(View view, Matrix matrix, RectF rectF) {
            return this.f17a.m597a(view, matrix, rectF);
        }

        public View m87a(Context context, Parcelable parcelable) {
            return this.f17a.m598a(context, parcelable);
        }
    }

    public static void m101a(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            ActivityCompat21.m93a(activity);
        } else {
            activity.finish();
        }
    }

    public static void m102a(Activity activity, SharedElementCallback sharedElementCallback) {
        if (VERSION.SDK_INT >= 21) {
            ActivityCompat21.m94a(activity, m100a(sharedElementCallback));
        }
    }

    public static void m104b(Activity activity, SharedElementCallback sharedElementCallback) {
        if (VERSION.SDK_INT >= 21) {
            ActivityCompat21.m96b(activity, m100a(sharedElementCallback));
        }
    }

    public static void m103a(Activity activity, String[] strArr, int i) {
        if (VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.m105a(activity, strArr, i);
        } else if (activity instanceof OnRequestPermissionsResultCallback) {
            new Handler(Looper.getMainLooper()).post(new C00011(strArr, activity, i));
        }
    }

    private static SharedElementCallback21 m100a(SharedElementCallback sharedElementCallback) {
        if (sharedElementCallback != null) {
            return new SharedElementCallback21Impl(sharedElementCallback);
        }
        return null;
    }
}
