package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatBase.Action;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class NotificationCompatJellybean {
    private static final Object f323a;
    private static Field f324b;
    private static boolean f325c;
    private static final Object f326d;

    public class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor {
        private android.app.Notification.Builder f320a;
        private final Bundle f321b;
        private List<Bundle> f322c;

        public Builder(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, int i4, CharSequence charSequence4, boolean z3, Bundle bundle, String str, boolean z4, String str2) {
            boolean z5;
            this.f322c = new ArrayList();
            android.app.Notification.Builder lights = new android.app.Notification.Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            lights = lights.setOngoing(z5);
            if ((notification.flags & 8) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            lights = lights.setOnlyAlertOnce(z5);
            if ((notification.flags & 16) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            lights = lights.setAutoCancel(z5).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z5 = true;
            } else {
                z5 = false;
            }
            this.f320a = lights.setFullScreenIntent(pendingIntent2, z5).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z2).setPriority(i4).setProgress(i2, i3, z);
            this.f321b = new Bundle();
            if (bundle != null) {
                this.f321b.putAll(bundle);
            }
            if (z3) {
                this.f321b.putBoolean("android.support.localOnly", true);
            }
            if (str != null) {
                this.f321b.putString("android.support.groupKey", str);
                if (z4) {
                    this.f321b.putBoolean("android.support.isGroupSummary", true);
                } else {
                    this.f321b.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (str2 != null) {
                this.f321b.putString("android.support.sortKey", str2);
            }
        }

        public void m572a(Action action) {
            this.f322c.add(NotificationCompatJellybean.m574a(this.f320a, action));
        }

        public android.app.Notification.Builder m571a() {
            return this.f320a;
        }

        public Notification m573b() {
            Notification build = this.f320a.build();
            Bundle a = NotificationCompatJellybean.m575a(build);
            Bundle bundle = new Bundle(this.f321b);
            for (String str : this.f321b.keySet()) {
                if (a.containsKey(str)) {
                    bundle.remove(str);
                }
            }
            a.putAll(bundle);
            SparseArray a2 = NotificationCompatJellybean.m576a(this.f322c);
            if (a2 != null) {
                NotificationCompatJellybean.m575a(build).putSparseParcelableArray("android.support.actionExtras", a2);
            }
            return build;
        }
    }

    static {
        f323a = new Object();
        f326d = new Object();
    }

    public static void m578a(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean z, CharSequence charSequence2, CharSequence charSequence3) {
        BigTextStyle bigText = new BigTextStyle(notificationBuilderWithBuilderAccessor.m511a()).setBigContentTitle(charSequence).bigText(charSequence3);
        if (z) {
            bigText.setSummaryText(charSequence2);
        }
    }

    public static void m577a(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean z, CharSequence charSequence2, Bitmap bitmap, Bitmap bitmap2, boolean z2) {
        BigPictureStyle bigPicture = new BigPictureStyle(notificationBuilderWithBuilderAccessor.m511a()).setBigContentTitle(charSequence).bigPicture(bitmap);
        if (z2) {
            bigPicture.bigLargeIcon(bitmap2);
        }
        if (z) {
            bigPicture.setSummaryText(charSequence2);
        }
    }

    public static void m579a(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, CharSequence charSequence, boolean z, CharSequence charSequence2, ArrayList<CharSequence> arrayList) {
        InboxStyle bigContentTitle = new InboxStyle(notificationBuilderWithBuilderAccessor.m511a()).setBigContentTitle(charSequence);
        if (z) {
            bigContentTitle.setSummaryText(charSequence2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine((CharSequence) it.next());
        }
    }

    public static SparseArray<Bundle> m576a(List<Bundle> list) {
        SparseArray<Bundle> sparseArray = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Bundle bundle = (Bundle) list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle m575a(Notification notification) {
        synchronized (f323a) {
            if (f325c) {
                return null;
            }
            try {
                if (f324b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (Bundle.class.isAssignableFrom(declaredField.getType())) {
                        declaredField.setAccessible(true);
                        f324b = declaredField;
                    } else {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        f325c = true;
                        return null;
                    }
                }
                Bundle bundle = (Bundle) f324b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    f324b.set(notification, bundle);
                }
                return bundle;
            } catch (Throwable e) {
                Log.e("NotificationCompat", "Unable to access notification extras", e);
                f325c = true;
                return null;
            } catch (Throwable e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                f325c = true;
                return null;
            }
        }
    }

    public static Bundle m574a(android.app.Notification.Builder builder, Action action) {
        builder.addAction(action.m513a(), action.m514b(), action.m515c());
        Bundle bundle = new Bundle(action.m516d());
        if (action.m517f() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", RemoteInputCompatJellybean.m595a(action.m517f()));
        }
        return bundle;
    }
}
