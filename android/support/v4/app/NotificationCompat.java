package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatBase.Action.Factory;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationCompat {
    private static final NotificationCompatImpl f315a;

    public class Action extends android.support.v4.app.NotificationCompatBase.Action {
        public static final Factory f271d;
        public int f272a;
        public CharSequence f273b;
        public PendingIntent f274c;
        private final Bundle f275e;
        private final RemoteInput[] f276f;

        /* renamed from: android.support.v4.app.NotificationCompat.Action.1 */
        final class C00121 implements Factory {
            C00121() {
            }
        }

        public /* synthetic */ RemoteInput[] m523f() {
            return m522e();
        }

        public int m518a() {
            return this.f272a;
        }

        public CharSequence m519b() {
            return this.f273b;
        }

        public PendingIntent m520c() {
            return this.f274c;
        }

        public Bundle m521d() {
            return this.f275e;
        }

        public RemoteInput[] m522e() {
            return this.f276f;
        }

        static {
            f271d = new C00121();
        }
    }

    public abstract class Style {
        Builder f277d;
        CharSequence f278e;
        CharSequence f279f;
        boolean f280g;

        public Style() {
            this.f280g = false;
        }

        public void m524a(Builder builder) {
            if (this.f277d != builder) {
                this.f277d = builder;
                if (this.f277d != null) {
                    this.f277d.m533a(this);
                }
            }
        }
    }

    public class BigPictureStyle extends Style {
        Bitmap f281a;
        Bitmap f282b;
        boolean f283c;
    }

    public class BigTextStyle extends Style {
        CharSequence f284a;
    }

    public class Builder {
        Notification f285A;
        public Notification f286B;
        public ArrayList<String> f287C;
        public Context f288a;
        public CharSequence f289b;
        public CharSequence f290c;
        PendingIntent f291d;
        PendingIntent f292e;
        RemoteViews f293f;
        public Bitmap f294g;
        public CharSequence f295h;
        public int f296i;
        int f297j;
        boolean f298k;
        public boolean f299l;
        public Style f300m;
        public CharSequence f301n;
        int f302o;
        int f303p;
        boolean f304q;
        String f305r;
        boolean f306s;
        String f307t;
        public ArrayList<Action> f308u;
        boolean f309v;
        String f310w;
        Bundle f311x;
        int f312y;
        int f313z;

        public Builder(Context context) {
            this.f298k = true;
            this.f308u = new ArrayList();
            this.f309v = false;
            this.f312y = 0;
            this.f313z = 0;
            this.f286B = new Notification();
            this.f288a = context;
            this.f286B.when = System.currentTimeMillis();
            this.f286B.audioStreamType = -1;
            this.f297j = 0;
            this.f287C = new ArrayList();
        }

        public Builder m528a(int i) {
            this.f286B.icon = i;
            return this;
        }

        public Builder m534a(CharSequence charSequence) {
            this.f289b = m526d(charSequence);
            return this;
        }

        public Builder m539b(CharSequence charSequence) {
            this.f290c = m526d(charSequence);
            return this;
        }

        public Builder m530a(PendingIntent pendingIntent) {
            this.f291d = pendingIntent;
            return this;
        }

        public Builder m538b(PendingIntent pendingIntent) {
            this.f286B.deleteIntent = pendingIntent;
            return this;
        }

        public Builder m542c(CharSequence charSequence) {
            this.f286B.tickerText = m526d(charSequence);
            return this;
        }

        public Builder m531a(Bitmap bitmap) {
            this.f294g = bitmap;
            return this;
        }

        public Builder m532a(Uri uri) {
            this.f286B.sound = uri;
            this.f286B.audioStreamType = -1;
            return this;
        }

        public Builder m536a(long[] jArr) {
            this.f286B.vibrate = jArr;
            return this;
        }

        public Builder m529a(int i, int i2, int i3) {
            int i4;
            int i5 = 1;
            this.f286B.ledARGB = i;
            this.f286B.ledOnMS = i2;
            this.f286B.ledOffMS = i3;
            if (this.f286B.ledOnMS == 0 || this.f286B.ledOffMS == 0) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            Notification notification = this.f286B;
            int i6 = this.f286B.flags & -2;
            if (i4 == 0) {
                i5 = 0;
            }
            notification.flags = i6 | i5;
            return this;
        }

        public Builder m535a(boolean z) {
            m525a(2, z);
            return this;
        }

        public Builder m540b(boolean z) {
            m525a(16, z);
            return this;
        }

        private void m525a(int i, boolean z) {
            if (z) {
                Notification notification = this.f286B;
                notification.flags |= i;
                return;
            }
            notification = this.f286B;
            notification.flags &= i ^ -1;
        }

        public Builder m537b(int i) {
            this.f297j = i;
            return this;
        }

        public Builder m533a(Style style) {
            if (this.f300m != style) {
                this.f300m = style;
                if (this.f300m != null) {
                    this.f300m.m524a(this);
                }
            }
            return this;
        }

        public Notification m527a() {
            return NotificationCompat.f315a.m546a(this, m541b());
        }

        protected BuilderExtender m541b() {
            return new BuilderExtender();
        }

        protected static CharSequence m526d(CharSequence charSequence) {
            if (charSequence != null && charSequence.length() > 5120) {
                return charSequence.subSequence(0, 5120);
            }
            return charSequence;
        }
    }

    public class BuilderExtender {
        protected BuilderExtender() {
        }

        public Notification m543a(Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return notificationBuilderWithBuilderAccessor.m512b();
        }
    }

    public class InboxStyle extends Style {
        ArrayList<CharSequence> f314a;

        public InboxStyle() {
            this.f314a = new ArrayList();
        }

        public InboxStyle m544a(CharSequence charSequence) {
            this.e = Builder.m526d(charSequence);
            return this;
        }

        public InboxStyle m545b(CharSequence charSequence) {
            this.f314a.add(Builder.m526d(charSequence));
            return this;
        }
    }

    interface NotificationCompatImpl {
        Notification m546a(Builder builder, BuilderExtender builderExtender);
    }

    class NotificationCompatImplBase implements NotificationCompatImpl {
        NotificationCompatImplBase() {
        }

        public Notification m547a(Builder builder, BuilderExtender builderExtender) {
            Notification notification = builder.f286B;
            notification.setLatestEventInfo(builder.f288a, builder.f289b, builder.f290c, builder.f291d);
            if (builder.f297j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    class NotificationCompatImplJellybean extends NotificationCompatImplBase {
        NotificationCompatImplJellybean() {
        }

        public Notification m548a(Builder builder, BuilderExtender builderExtender) {
            NotificationBuilderWithBuilderAccessor builder2 = new android.support.v4.app.NotificationCompatJellybean.Builder(builder.f288a, builder.f286B, builder.f289b, builder.f290c, builder.f295h, builder.f293f, builder.f296i, builder.f291d, builder.f292e, builder.f294g, builder.f302o, builder.f303p, builder.f304q, builder.f299l, builder.f297j, builder.f301n, builder.f309v, builder.f311x, builder.f305r, builder.f306s, builder.f307t);
            NotificationCompat.m558b((NotificationBuilderWithActions) builder2, builder.f308u);
            NotificationCompat.m559b(builder2, builder.f300m);
            return builderExtender.m543a(builder, builder2);
        }
    }

    class NotificationCompatImplKitKat extends NotificationCompatImplJellybean {
        NotificationCompatImplKitKat() {
        }

        public Notification m549a(Builder builder, BuilderExtender builderExtender) {
            NotificationBuilderWithBuilderAccessor builder2 = new android.support.v4.app.NotificationCompatKitKat.Builder(builder.f288a, builder.f286B, builder.f289b, builder.f290c, builder.f295h, builder.f293f, builder.f296i, builder.f291d, builder.f292e, builder.f294g, builder.f302o, builder.f303p, builder.f304q, builder.f298k, builder.f299l, builder.f297j, builder.f301n, builder.f309v, builder.f287C, builder.f311x, builder.f305r, builder.f306s, builder.f307t);
            NotificationCompat.m558b((NotificationBuilderWithActions) builder2, builder.f308u);
            NotificationCompat.m559b(builder2, builder.f300m);
            return builderExtender.m543a(builder, builder2);
        }
    }

    class NotificationCompatImplApi20 extends NotificationCompatImplKitKat {
        NotificationCompatImplApi20() {
        }

        public Notification m550a(Builder builder, BuilderExtender builderExtender) {
            NotificationBuilderWithBuilderAccessor builder2 = new android.support.v4.app.NotificationCompatApi20.Builder(builder.f288a, builder.f286B, builder.f289b, builder.f290c, builder.f295h, builder.f293f, builder.f296i, builder.f291d, builder.f292e, builder.f294g, builder.f302o, builder.f303p, builder.f304q, builder.f298k, builder.f299l, builder.f297j, builder.f301n, builder.f309v, builder.f287C, builder.f311x, builder.f305r, builder.f306s, builder.f307t);
            NotificationCompat.m558b((NotificationBuilderWithActions) builder2, builder.f308u);
            NotificationCompat.m559b(builder2, builder.f300m);
            return builderExtender.m543a(builder, builder2);
        }
    }

    class NotificationCompatImplApi21 extends NotificationCompatImplApi20 {
        NotificationCompatImplApi21() {
        }

        public Notification m551a(Builder builder, BuilderExtender builderExtender) {
            NotificationBuilderWithBuilderAccessor builder2 = new android.support.v4.app.NotificationCompatApi21.Builder(builder.f288a, builder.f286B, builder.f289b, builder.f290c, builder.f295h, builder.f293f, builder.f296i, builder.f291d, builder.f292e, builder.f294g, builder.f302o, builder.f303p, builder.f304q, builder.f298k, builder.f299l, builder.f297j, builder.f301n, builder.f309v, builder.f310w, builder.f287C, builder.f311x, builder.f312y, builder.f313z, builder.f285A, builder.f305r, builder.f306s, builder.f307t);
            NotificationCompat.m558b((NotificationBuilderWithActions) builder2, builder.f308u);
            NotificationCompat.m559b(builder2, builder.f300m);
            return builderExtender.m543a(builder, builder2);
        }
    }

    class NotificationCompatImplGingerbread extends NotificationCompatImplBase {
        NotificationCompatImplGingerbread() {
        }

        public Notification m552a(Builder builder, BuilderExtender builderExtender) {
            Notification notification = builder.f286B;
            notification.setLatestEventInfo(builder.f288a, builder.f289b, builder.f290c, builder.f291d);
            notification = NotificationCompatGingerbread.m567a(notification, builder.f288a, builder.f289b, builder.f290c, builder.f291d, builder.f292e);
            if (builder.f297j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    class NotificationCompatImplHoneycomb extends NotificationCompatImplBase {
        NotificationCompatImplHoneycomb() {
        }

        public Notification m553a(Builder builder, BuilderExtender builderExtender) {
            return NotificationCompatHoneycomb.m568a(builder.f288a, builder.f286B, builder.f289b, builder.f290c, builder.f295h, builder.f293f, builder.f296i, builder.f291d, builder.f292e, builder.f294g);
        }
    }

    class NotificationCompatImplIceCreamSandwich extends NotificationCompatImplBase {
        NotificationCompatImplIceCreamSandwich() {
        }

        public Notification m554a(Builder builder, BuilderExtender builderExtender) {
            return builderExtender.m543a(builder, new android.support.v4.app.NotificationCompatIceCreamSandwich.Builder(builder.f288a, builder.f286B, builder.f289b, builder.f290c, builder.f295h, builder.f293f, builder.f296i, builder.f291d, builder.f292e, builder.f294g, builder.f302o, builder.f303p, builder.f304q));
        }
    }

    private static void m558b(NotificationBuilderWithActions notificationBuilderWithActions, ArrayList<Action> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            notificationBuilderWithActions.m510a((Action) it.next());
        }
    }

    private static void m559b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, Style style) {
        if (style == null) {
            return;
        }
        if (style instanceof BigTextStyle) {
            BigTextStyle bigTextStyle = (BigTextStyle) style;
            NotificationCompatJellybean.m578a(notificationBuilderWithBuilderAccessor, bigTextStyle.e, bigTextStyle.g, bigTextStyle.f, bigTextStyle.f284a);
        } else if (style instanceof InboxStyle) {
            InboxStyle inboxStyle = (InboxStyle) style;
            NotificationCompatJellybean.m579a(notificationBuilderWithBuilderAccessor, inboxStyle.e, inboxStyle.g, inboxStyle.f, inboxStyle.f314a);
        } else if (style instanceof BigPictureStyle) {
            BigPictureStyle bigPictureStyle = (BigPictureStyle) style;
            NotificationCompatJellybean.m577a(notificationBuilderWithBuilderAccessor, bigPictureStyle.e, bigPictureStyle.g, bigPictureStyle.f, bigPictureStyle.f281a, bigPictureStyle.f282b, bigPictureStyle.f283c);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            f315a = new NotificationCompatImplApi21();
        } else if (VERSION.SDK_INT >= 20) {
            f315a = new NotificationCompatImplApi20();
        } else if (VERSION.SDK_INT >= 19) {
            f315a = new NotificationCompatImplKitKat();
        } else if (VERSION.SDK_INT >= 16) {
            f315a = new NotificationCompatImplJellybean();
        } else if (VERSION.SDK_INT >= 14) {
            f315a = new NotificationCompatImplIceCreamSandwich();
        } else if (VERSION.SDK_INT >= 11) {
            f315a = new NotificationCompatImplHoneycomb();
        } else if (VERSION.SDK_INT >= 9) {
            f315a = new NotificationCompatImplGingerbread();
        } else {
            f315a = new NotificationCompatImplBase();
        }
    }
}
