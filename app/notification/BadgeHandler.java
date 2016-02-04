package app.notification;

import android.content.Context;
import android.content.Intent;
import app.Main;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.ConversationNormalDataSource;
import app.view.BisphoneWidget;

public class BadgeHandler {
    private static BadgeHandler f4120a;
    private Context f4121b;
    private int f4122c;

    public BadgeHandler(Context context) {
        this.f4122c = 0;
        this.f4121b = context;
    }

    public static synchronized BadgeHandler m6597a() {
        BadgeHandler badgeHandler;
        synchronized (BadgeHandler.class) {
            if (f4120a == null) {
                f4120a = new BadgeHandler(Main.f1927b);
            }
            badgeHandler = f4120a;
        }
        return badgeHandler;
    }

    public void m6605b() {
        this.f4122c++;
        m6604a(false);
    }

    public void m6603a(int i) {
        this.f4122c += i;
        m6604a(false);
    }

    public void m6604a(boolean z) {
        if (z) {
            this.f4122c = ConversationNormalDataSource.m4623a().m4634b();
            this.f4122c += ConversationGroupDataSource.m4587a().m4619f();
        }
        m6598c();
        m6599d();
    }

    private void m6598c() {
        Intent intent = new Intent(this.f4121b, BisphoneWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", new int[]{1});
        intent.putExtra("message_count", this.f4122c);
        this.f4121b.sendBroadcast(intent);
    }

    private void m6599d() {
        m6600e();
        m6601f();
    }

    private void m6600e() {
        String g = m6602g();
        if (g != null) {
            Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
            intent.putExtra("badge_count", this.f4122c);
            intent.putExtra("badge_count_package_name", this.f4121b.getPackageName());
            intent.putExtra("badge_count_class_name", g);
            this.f4121b.sendBroadcast(intent);
        }
    }

    private void m6601f() {
        String g = m6602g();
        if (g != null) {
            Intent intent = new Intent();
            intent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", g);
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", this.f4122c > 0);
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(this.f4122c));
            intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", this.f4121b.getPackageName());
            this.f4121b.sendBroadcast(intent);
        }
    }

    private String m6602g() {
        return "app.SplashActivity";
    }
}
