package app.util;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.jivesoftware.smack.util.StringUtils;

public class DeviceUtils {
    public static String m7012a(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    public static DisplayMetrics m7011a(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int m7010a(Activity activity, int i, int i2, int i3) {
        int i4 = m7011a(activity).widthPixels;
        if (i == 0) {
            return 0;
        }
        return (i4 / i) - (i2 + i3);
    }

    public static boolean m7013b(Context context) {
        try {
            return Arrays.equals(MessageDigest.getInstance(StringUtils.MD5).digest(context.getPackageName().getBytes()), new byte[]{(byte) -82, (byte) -34, (byte) -99, (byte) -35, (byte) 112, (byte) -105, (byte) 89, (byte) 7, (byte) -23, (byte) -60, (byte) 39, (byte) -108, (byte) -39, (byte) 112, (byte) -92, (byte) 6});
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }
}
