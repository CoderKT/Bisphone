package app.profile;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog.Builder;
import app.Main;
import se.emilsjolander.stickylistheaders.C1128R;

public class InviteUtil {

    /* renamed from: app.profile.InviteUtil.1 */
    final class C04381 implements OnClickListener {
        final /* synthetic */ Context f4227a;

        C04381(Context context) {
            this.f4227a = context;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    InviteUtil.m6697a(this.f4227a);
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    InviteUtil.m6698b(this.f4227a);
                default:
            }
        }
    }

    public static void showInviteDialog(Context context) {
        Builder builder = new Builder(context, 2131558535);
        builder.m1982a(true);
        builder.m1983a(new CharSequence[]{context.getString(2131296650), context.getString(2131296651)}, new C04381(context));
        builder.m1992c();
    }

    public static void m6697a(Context context) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        Uri parse = Uri.parse("mailto:?subject=" + Uri.encode(context.getString(2131296654)) + "&body=" + Uri.encode(context.getString(2131296653)));
        intent.setType("message/rfc822");
        intent.setData(parse);
        context.startActivity(Intent.createChooser(intent, "Send mail..."));
    }

    public static void m6698b(Context context) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", context.getString(2131296653));
        context.startActivity(Intent.createChooser(intent, "Send SMS..."));
    }

    public static void m6699c(Context context) {
        int i;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", context.getString(2131296334));
        intent.putExtra("android.intent.extra.TEXT", context.getString(2131296915));
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.name.contains("com.facebook")) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setFlags(337641472);
                intent.setComponent(componentName);
                context.startActivity(intent);
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            Main.m3905a(context.getString(2131296855));
        }
    }

    public static void m6700d(Context context) {
        int i;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", context.getString(2131296655));
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.name.contains("com.twitter")) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setFlags(337641472);
                intent.setComponent(componentName);
                context.startActivity(intent);
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            Main.m3905a(context.getString(2131296857));
        }
    }
}
