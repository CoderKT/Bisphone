package app.galley.external;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import app.util.DeviceUtils;
import java.io.File;

public class SystemCropper {
    public static void m5114a(Activity activity, String str, File file, int i) {
        m5113a(activity, str, Uri.fromFile(file), i, false);
    }

    public static void m5115a(Activity activity, String str, String str2, int i, boolean z) {
        m5113a(activity, str, Uri.parse(str2), i, z);
    }

    public static void m5113a(Activity activity, String str, Uri uri, int i, boolean z) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        File file = new File(str);
        if (file.exists()) {
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            if (z) {
                int[] b = m5116b(DeviceUtils.m7011a(activity).widthPixels, DeviceUtils.m7011a(activity).heightPixels);
                intent.putExtra("aspectX", b[0]);
                intent.putExtra("aspectY", b[1]);
                intent.putExtra("outputX", b[2]);
                intent.putExtra("outputY", b[3]);
                intent.putExtra("scale", false);
            } else {
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
            }
            intent.putExtra("output", uri);
            intent.putExtra("return-data", false);
            activity.startActivityForResult(Intent.createChooser(intent, activity.getResources().getString(2131296409)), i);
        }
    }

    private static int m5112a(int i, int i2) {
        return i2 == 0 ? i : m5112a(i2, i % i2);
    }

    private static int[] m5116b(int i, int i2) {
        r0 = new int[4];
        int a = m5112a(i, i2);
        r0[0] = i / a;
        r0[1] = i2 / a;
        r0[2] = i - (r0[0] * 30);
        r0[3] = i2 - (r0[1] * 30);
        return r0;
    }
}
