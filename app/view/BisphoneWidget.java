package app.view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import app.home.HomeActivity;

public class BisphoneWidget extends AppWidgetProvider {
    private AppWidgetManager f4627a;
    private ComponentName f4628b;
    private RemoteViews f4629c;
    private Intent f4630d;
    private String f4631e;

    public BisphoneWidget() {
        this.f4631e = "";
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        m7099a(context);
        this.f4627a = appWidgetManager;
        this.f4627a.updateAppWidget(iArr[0], this.f4629c);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if ("android.appwidget.action.APPWIDGET_DELETED".equals(intent.getAction())) {
            if (intent.getExtras().getInt("appWidgetId", 0) != 0) {
                onDeleted(context, new int[]{intent.getExtras().getInt("appWidgetId", 0)});
                return;
            }
            return;
        }
        m7099a(context);
        this.f4631e = m7098a(intent);
        if (this.f4631e == null || this.f4631e.equals("")) {
            this.f4629c.setViewVisibility(2131755625, 8);
        } else {
            this.f4629c.setViewVisibility(2131755625, 0);
            this.f4629c.setTextViewText(2131755626, this.f4631e);
        }
        this.f4627a.updateAppWidget(this.f4628b, this.f4629c);
    }

    private void m7099a(Context context) {
        if (this.f4627a == null) {
            this.f4627a = AppWidgetManager.getInstance(context);
        }
        if (this.f4628b == null) {
            this.f4628b = new ComponentName(context, BisphoneWidget.class);
        }
        if (this.f4629c == null) {
            this.f4629c = new RemoteViews(context.getPackageName(), 2130903241);
        }
        if (this.f4630d == null) {
            m7100b(context);
        }
    }

    private String m7098a(Intent intent) {
        if (intent == null) {
            return "";
        }
        int intExtra = intent.getIntExtra("message_count", 0);
        if (intExtra > 0) {
            return intExtra > 99 ? "99+" : intExtra + "";
        } else {
            return "";
        }
    }

    private void m7100b(Context context) {
        this.f4630d = new Intent(context, HomeActivity.class);
        this.f4629c.setOnClickPendingIntent(2131755623, PendingIntent.getActivity(context, 0, this.f4630d, 0));
    }
}
