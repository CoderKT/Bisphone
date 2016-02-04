package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatBase.Action;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

class NotificationCompatKitKat {

    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private android.app.Notification.Builder f327a;
        private Bundle f328b;
        private List<Bundle> f329c;

        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, CharSequence charSequence4, boolean z4, ArrayList<String> arrayList, Bundle bundle, String str, boolean z5, String str2) {
            boolean z6;
            this.f329c = new ArrayList();
            android.app.Notification.Builder lights = new android.app.Notification.Builder(context).setWhen(notification.when).setShowWhen(z2).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z6 = true;
            } else {
                z6 = false;
            }
            lights = lights.setOngoing(z6);
            if ((notification.flags & 8) != 0) {
                z6 = true;
            } else {
                z6 = false;
            }
            lights = lights.setOnlyAlertOnce(z6);
            if ((notification.flags & 16) != 0) {
                z6 = true;
            } else {
                z6 = false;
            }
            lights = lights.setAutoCancel(z6).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z6 = true;
            } else {
                z6 = false;
            }
            this.f327a = lights.setFullScreenIntent(pendingIntent2, z6).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z3).setPriority(i4).setProgress(i2, i3, z);
            this.f328b = new Bundle();
            if (bundle != null) {
                this.f328b.putAll(bundle);
            }
            if (!(arrayList == null || arrayList.isEmpty())) {
                this.f328b.putStringArray("android.people", (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
            if (z4) {
                this.f328b.putBoolean("android.support.localOnly", true);
            }
            if (str != null) {
                this.f328b.putString("android.support.groupKey", str);
                if (z5) {
                    this.f328b.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.f328b.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (str2 != null) {
                this.f328b.putString("android.support.sortKey", str2);
            }
        }

        public void m581a(Action action) {
            this.f329c.add(NotificationCompatJellybean.m574a(this.f327a, action));
        }

        public android.app.Notification.Builder m580a() {
            return this.f327a;
        }

        public Notification m582b() {
            SparseArray a = NotificationCompatJellybean.m576a(this.f329c);
            if (a != null) {
                this.f328b.putSparseParcelableArray("android.support.actionExtras", a);
            }
            this.f327a.setExtras(this.f328b);
            return this.f327a.build();
        }
    }
}
