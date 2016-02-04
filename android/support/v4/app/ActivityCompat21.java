package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.session.MediaController;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

class ActivityCompat21 {

    public abstract class SharedElementCallback21 {
        public abstract Parcelable m80a(View view, Matrix matrix, RectF rectF);

        public abstract View m81a(Context context, Parcelable parcelable);

        public abstract void m82a(List<View> list);

        public abstract void m83a(List<String> list, List<View> list2, List<View> list3);

        public abstract void m84a(List<String> list, Map<String, View> map);

        public abstract void m85b(List<String> list, List<View> list2, List<View> list3);
    }

    class SharedElementCallbackImpl extends SharedElementCallback {
        private SharedElementCallback21 f18a;

        public SharedElementCallbackImpl(SharedElementCallback21 sharedElementCallback21) {
            this.f18a = sharedElementCallback21;
        }

        public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
            this.f18a.m83a((List) list, (List) list2, (List) list3);
        }

        public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
            this.f18a.m85b(list, list2, list3);
        }

        public void onRejectSharedElements(List<View> list) {
            this.f18a.m82a(list);
        }

        public void onMapSharedElements(List<String> list, Map<String, View> map) {
            this.f18a.m84a((List) list, (Map) map);
        }

        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
            return this.f18a.m80a(view, matrix, rectF);
        }

        public View onCreateSnapshotView(Context context, Parcelable parcelable) {
            return this.f18a.m81a(context, parcelable);
        }
    }

    public static void m95a(Activity activity, Object obj) {
        activity.setMediaController((MediaController) obj);
    }

    public static void m93a(Activity activity) {
        activity.finishAfterTransition();
    }

    public static void m94a(Activity activity, SharedElementCallback21 sharedElementCallback21) {
        activity.setEnterSharedElementCallback(m92a(sharedElementCallback21));
    }

    public static void m96b(Activity activity, SharedElementCallback21 sharedElementCallback21) {
        activity.setExitSharedElementCallback(m92a(sharedElementCallback21));
    }

    private static SharedElementCallback m92a(SharedElementCallback21 sharedElementCallback21) {
        if (sharedElementCallback21 != null) {
            return new SharedElementCallbackImpl(sharedElementCallback21);
        }
        return null;
    }
}
