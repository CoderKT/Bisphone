package app.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import app.Main;
import java.util.HashMap;

public class PermissionUtil {
    private static HashMap<PermissionType, String> f4618a;

    /* renamed from: app.util.PermissionUtil.1 */
    final class C05061 implements Runnable {
        final /* synthetic */ AlertDialog f4608a;

        C05061(AlertDialog alertDialog) {
            this.f4608a = alertDialog;
        }

        public void run() {
            this.f4608a.show();
        }
    }

    public enum PermissionType {
        location,
        contact,
        microphone,
        phone,
        sms,
        storage,
        gallery,
        video
    }

    @TargetApi(16)
    private static HashMap<PermissionType, String> m7041a() {
        if (f4618a == null) {
            f4618a = new HashMap();
            f4618a.put(PermissionType.location, "android.permission.ACCESS_COARSE_LOCATION");
            f4618a.put(PermissionType.contact, "android.permission.READ_CONTACTS");
            f4618a.put(PermissionType.microphone, "android.permission.RECORD_AUDIO");
            f4618a.put(PermissionType.phone, "android.permission.READ_PHONE_STATE");
            f4618a.put(PermissionType.sms, "android.permission.RECEIVE_SMS");
            f4618a.put(PermissionType.storage, "android.permission.WRITE_EXTERNAL_STORAGE");
            f4618a.put(PermissionType.gallery, "android.permission.READ_EXTERNAL_STORAGE");
            f4618a.put(PermissionType.video, "android.permission.READ_EXTERNAL_STORAGE");
        }
        return f4618a;
    }

    public static void m7042a(Activity activity, PermissionType permissionType, int i) {
        if (ContextCompat.m97a((Context) activity, (String) m7041a().get(permissionType)) != 0) {
            Main.f1926a.m5683d("permission is deny");
            ActivityCompat.m103a(activity, new String[]{(String) m7041a().get(permissionType)}, i);
        }
    }

    public static void m7043a(Activity activity, String str, String str2) {
        AlertDialog b = new Builder(activity, 2131558537).m1982a(false).m1981a(Main.f1927b.getString(2131296624), null).m1980a((CharSequence) str).m1989c(2130837730).m1986b((CharSequence) str2).m1988b();
        b.setCanceledOnTouchOutside(false);
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                b.show();
            } else {
                new Handler(Looper.getMainLooper()).post(new C05061(b));
            }
        } catch (Exception e) {
        }
    }

    public static boolean m7044a(PermissionType permissionType) {
        return ContextCompat.m97a(Main.f1927b, (String) m7041a().get(permissionType)) == 0;
    }
}
