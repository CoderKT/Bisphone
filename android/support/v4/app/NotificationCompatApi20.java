package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatBase.Action;
import android.widget.RemoteViews;
import java.util.ArrayList;

class NotificationCompatApi20 {

    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private android.app.Notification.Builder f316a;
        private Bundle f317b;

        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, boolean z3, int i4, CharSequence charSequence4, boolean z4, ArrayList<String> arrayList, Bundle bundle, String str, boolean z5, String str2) {
            boolean z6;
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
            this.f316a = lights.setFullScreenIntent(pendingIntent2, z6).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z3).setPriority(i4).setProgress(i2, i3, z).setLocalOnly(z4).setGroup(str).setGroupSummary(z5).setSortKey(str2);
            this.f317b = new Bundle();
            if (bundle != null) {
                this.f317b.putAll(bundle);
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                this.f317b.putStringArray("android.people", (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }

        public void m561a(Action action) {
            NotificationCompatApi20.m563a(this.f316a, action);
        }

        public android.app.Notification.Builder m560a() {
            return this.f316a;
        }

        public Notification m562b() {
            this.f316a.setExtras(this.f317b);
            return this.f316a.build();
        }
    }

    public static void m563a(android.app.Notification.Builder builder, Action action) {
        android.app.Notification.Action.Builder builder2 = new android.app.Notification.Action.Builder(action.m513a(), action.m514b(), action.m515c());
        if (action.m517f() != null) {
            for (RemoteInput addRemoteInput : RemoteInputCompatApi20.m593a(action.m517f())) {
                builder2.addRemoteInput(addRemoteInput);
            }
        }
        if (action.m516d() != null) {
            builder2.addExtras(action.m516d());
        }
        builder.addAction(builder2.build());
    }
}
