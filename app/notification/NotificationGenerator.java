package app.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.util.TypedValue;
import app.Main;
import app.common.AddressBookHelper;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.HistoryEntity.GroupStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.datasource.ContactDataSource;
import app.database.datasource.ConversationGroupDataSource;
import app.storage.StorageException;
import app.util.Utils;
import app.voip.HelperCallActivity;
import app.xmpp.GroupManager;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

class NotificationGenerator {
    private static final long[] f4142h;
    Runnable f4143a;
    boolean f4144b;
    int f4145c;
    List<HistoryNormalMessageEntity> f4146d;
    Runnable f4147e;
    MessageNotificationHandler f4148f;
    GroupNotificationHandler1 f4149g;
    private NotificationManager f4150i;
    private Context f4151j;
    private HashMap<ContactEntity, Integer> f4152k;
    private boolean f4153l;
    private boolean f4154m;
    private boolean f4155n;
    private HashMap<String, NotificationState> f4156o;
    private boolean f4157p;
    private Handler f4158q;

    /* renamed from: app.notification.NotificationGenerator.1 */
    class C04311 implements Runnable {
        final /* synthetic */ NotificationGenerator f4136a;

        C04311(NotificationGenerator notificationGenerator) {
            this.f4136a = notificationGenerator;
        }

        public void run() {
            this.f4136a.f4153l = false;
            if (this.f4136a.f4154m) {
                this.f4136a.m6661a(this.f4136a.f4144b, this.f4136a.f4145c, this.f4136a.f4146d, true);
            }
        }
    }

    /* renamed from: app.notification.NotificationGenerator.2 */
    class C04322 implements Runnable {
        final /* synthetic */ NotificationGenerator f4137a;

        C04322(NotificationGenerator notificationGenerator) {
            this.f4137a = notificationGenerator;
        }

        public void run() {
            this.f4137a.f4153l = false;
            if (this.f4137a.f4155n) {
                this.f4137a.m6660a(true);
            }
        }
    }

    /* renamed from: app.notification.NotificationGenerator.3 */
    class C04333 implements Runnable {
        final /* synthetic */ NotificationGenerator f4138a;

        C04333(NotificationGenerator notificationGenerator) {
            this.f4138a = notificationGenerator;
        }

        public void run() {
            this.f4138a.f4157p = true;
        }
    }

    /* renamed from: app.notification.NotificationGenerator.4 */
    /* synthetic */ class C04344 {
        static final /* synthetic */ int[] f4139a;

        static {
            f4139a = new int[NotificationState.values().length];
            try {
                f4139a[NotificationState.sound.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4139a[NotificationState.soundPending.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4139a[NotificationState.getPending.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4139a[NotificationState.vibrate.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4139a[NotificationState.vibratePending.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4139a[NotificationState.disable.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4139a[NotificationState.disablePending.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public class GroupNotificationHandler1 extends AsyncTask<Void, Void, Builder> {
        final /* synthetic */ NotificationGenerator f4140a;

        public GroupNotificationHandler1(NotificationGenerator notificationGenerator) {
            this.f4140a = notificationGenerator;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6633a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m6634a((Builder) obj);
        }

        protected Builder m6633a(Void... voidArr) {
            Builder b = new Builder(this.f4140a.f4151j).m528a(2130837859).m540b(true);
            NotificationState notificationState = NotificationState.sound;
            List b2 = NotificationCenter.m6606a().m6613b();
            if (b2 == null || b2.size() == 0) {
                return null;
            }
            String str = (String) b2.get(0);
            Bitmap a;
            int i;
            if (b2.size() == 1) {
                ConversationGroupEntity b3 = GroupManager.m7323a().m7358b((String) b2.get(0));
                if (b3 == null) {
                    Main.f1926a.m5683d("Notification Generator, notifyNewGroupMessages, Notify a message from a group not in group table?!");
                    return null;
                }
                List c = NotificationCenter.m6606a().m6615c((String) b2.get(0));
                if (c == null || c.size() == 0) {
                    Main.f1926a.m5683d("Notification Generator, notifyNewGroupMessages, Notify a message from a group not in group table?!");
                    return null;
                } else if (c.size() == 1) {
                    CharSequence f = b3.m4286f();
                    a = this.f4140a.m6642b(b3.m4289g());
                    if (a != null) {
                        b.m531a(a);
                    }
                    ContactEntity b4 = ContactDataSource.m4553a().m4570b(((HistoryGroupEntity) c.get(0)).m4449M());
                    CharSequence charSequence;
                    if (b4 == null) {
                        b.m539b(((HistoryGroupEntity) c.get(0)).m4436w() == GroupStatus.JOIN ? ((HistoryGroupEntity) c.get(0)).m4416d() : Main.f1927b.getResources().getString(2131296307) + ": " + ((HistoryGroupEntity) c.get(0)).m4416d());
                        if (((HistoryGroupEntity) c.get(0)).m4436w() == GroupStatus.JOIN) {
                            charSequence = f + ": " + ((HistoryGroupEntity) c.get(0)).m4416d();
                        } else {
                            charSequence = Main.f1927b.getResources().getString(2131296307) + " " + this.f4140a.f4151j.getResources().getString(2131296451) + " " + f + ": " + ((HistoryGroupEntity) c.get(0)).m4416d().replaceAll("\\n+", "\n").replaceAll("\\s+", " ");
                        }
                        b.m542c(charSequence);
                    } else {
                        b.m539b(((HistoryGroupEntity) c.get(0)).m4436w() == GroupStatus.JOIN ? ((HistoryGroupEntity) c.get(0)).m4416d() : b4.m4183a(false) + ": " + ((HistoryGroupEntity) c.get(0)).m4416d());
                        if (((HistoryGroupEntity) c.get(0)).m4436w() == GroupStatus.JOIN) {
                            charSequence = f + ": " + ((HistoryGroupEntity) c.get(0)).m4416d();
                        } else {
                            charSequence = b4.m4183a(false) + " " + this.f4140a.f4151j.getResources().getString(2131296451) + " " + f + ": " + ((HistoryGroupEntity) c.get(0)).m4416d().replaceAll("\\n+", "\n").replaceAll("\\s+", " ");
                        }
                        b.m542c(charSequence);
                    }
                    b.m534a(f);
                    b.m529a(-1, 300, 1500);
                } else if (c.size() <= 10) {
                    Style inboxStyle = new InboxStyle();
                    inboxStyle.m544a(b3.m4286f());
                    b.m534a(b3.m4286f());
                    a = this.f4140a.m6642b(b3.m4289g());
                    if (a != null) {
                        b.m531a(a);
                    }
                    for (i = 0; i < c.size(); i++) {
                        ContactEntity b5 = ContactDataSource.m4553a().m4570b(((HistoryGroupEntity) c.get(i)).m4449M());
                        if (b5 == null) {
                            inboxStyle.m545b(((HistoryGroupEntity) c.get(i)).m4436w() == GroupStatus.JOIN ? ((HistoryGroupEntity) c.get(i)).m4416d() : Main.f1927b.getResources().getString(2131296307) + ": " + ((HistoryGroupEntity) c.get(i)).m4416d());
                        } else {
                            inboxStyle.m545b(((HistoryGroupEntity) c.get(i)).m4436w() == GroupStatus.JOIN ? ((HistoryGroupEntity) c.get(i)).m4416d() : b5.m4183a(false) + ": " + ((HistoryGroupEntity) c.get(i)).m4416d());
                        }
                    }
                    b.m533a(inboxStyle);
                    b.m539b(String.format(this.f4140a.f4151j.getString(2131296613), new Object[]{Integer.valueOf(c.size())}));
                    b.m542c(b3.m4286f() + ": " + String.format(this.f4140a.f4151j.getString(2131296613), new Object[]{Integer.valueOf(c.size())}));
                    b.m529a(-1, 300, 1500);
                } else {
                    b.m534a(b3.m4286f());
                    a = this.f4140a.m6642b(b3.m4289g());
                    if (a != null) {
                        b.m531a(a);
                    }
                    b.m539b(String.format(this.f4140a.f4151j.getString(2131296613), new Object[]{Integer.valueOf(c.size())}));
                    b.m542c(b3.m4286f() + ": " + String.format(this.f4140a.f4151j.getString(2131296613), new Object[]{Integer.valueOf(c.size())}));
                    b.m529a(-1, 300, 1500);
                }
            } else {
                int i2 = 0;
                for (i = 0; i < b2.size(); i++) {
                    i2 += NotificationCenter.m6606a().m6615c((String) b2.get(i)).size();
                }
                a = this.f4140a.m6642b("");
                b.m534a(String.format(this.f4140a.f4151j.getString(2131296613), new Object[]{Integer.valueOf(i2)}));
                b.m539b(String.format(this.f4140a.f4151j.getString(2131296614), new Object[]{Integer.valueOf(i2), Integer.valueOf(b2.size())}));
                b.m542c(String.format(this.f4140a.f4151j.getString(2131296613), new Object[]{Integer.valueOf(i2)}));
                b.m529a(-1, 300, 1500);
                if (a != null) {
                    b.m531a(a);
                }
            }
            Intent intent = new Intent("com.bistalk.bisphone.action.notification");
            if (b2.size() == 1) {
                intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_GROUP_CHAT);
                intent.putExtra("jabber_id", str);
            } else {
                intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_GROUPS);
            }
            intent.setClass(this.f4140a.f4151j, NotificationReceiver.class);
            b.m530a(PendingIntent.getBroadcast(this.f4140a.f4151j, 203, intent, 134217728));
            Intent intent2 = new Intent("com.bistalk.bisphone.action.notification");
            intent2.putExtra(Action.ATTRIBUTE_NAME, ACTION.CLEAR_GROUP_CHATS);
            intent2.setClass(this.f4140a.f4151j, NotificationReceiver.class);
            b.m538b(PendingIntent.getBroadcast(this.f4140a.f4151j, 206, intent2, 134217728));
            switch (C04344.f4139a[notificationState.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (this.f4140a.f4157p) {
                        Uri parse = Uri.parse("android.resource://" + this.f4140a.f4151j.getPackageName() + "/raw/notification");
                        this.f4140a.m6648d();
                        b.m536a(NotificationGenerator.f4142h).m532a(parse);
                    }
                    return b;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    b.m536a(NotificationGenerator.f4142h);
                    return b;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    return null;
                default:
                    return b;
            }
        }

        protected void m6634a(Builder builder) {
            super.onPostExecute(builder);
            if (builder != null) {
                this.f4140a.f4150i.notify(12, builder.m527a());
            }
            this.f4140a.f4155n = false;
            this.f4140a.f4158q.postDelayed(this.f4140a.f4147e, 3000);
        }
    }

    public class MessageNotificationHandler extends AsyncTask<Void, Void, Builder> {
        final /* synthetic */ NotificationGenerator f4141a;

        public MessageNotificationHandler(NotificationGenerator notificationGenerator) {
            this.f4141a = notificationGenerator;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6635a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m6636a((Builder) obj);
        }

        protected Builder m6635a(Void... voidArr) {
            try {
                if (this.f4141a.f4145c < 1 || this.f4141a.f4146d == null || this.f4141a.f4146d.size() == 0) {
                    Main.f1926a.m5683d("NotificationGenerator, notifyNewMessages, invalid arguments, return");
                    return null;
                }
                Builder b = new Builder(this.f4141a.f4151j).m528a(2130837859).m540b(true).m537b(1);
                ContactEntity b2;
                String a;
                if (this.f4141a.f4145c == 1) {
                    CharSequence charSequence;
                    b2 = ContactDataSource.m4553a().m4570b(((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4456M());
                    if (b2 == null) {
                        charSequence = "+" + ((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4456M();
                    } else {
                        a = b2.m4183a(true);
                        b.m531a(this.f4141a.m6637a(b2));
                        Object obj = a;
                    }
                    b.m539b(((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4416d());
                    b.m534a(charSequence);
                    b.m542c(charSequence + ": " + ((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4416d().replaceAll("\\n+", "\n").replaceAll("\\s+", " "));
                    b.m529a(-1, 300, 1500);
                } else if (this.f4141a.f4145c <= 10) {
                    Style inboxStyle = new InboxStyle();
                    if (this.f4141a.f4144b) {
                        inboxStyle.m544a(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                        b.m534a(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                        b.m542c(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                        b.m529a(-1, 300, 1500);
                        for (HistoryNormalMessageEntity historyNormalMessageEntity : this.f4141a.f4146d) {
                            ContactEntity b3 = ContactDataSource.m4553a().m4570b(historyNormalMessageEntity.m4456M());
                            if (b3 == null) {
                                inboxStyle.m545b("+" + historyNormalMessageEntity.m4456M() + ": " + historyNormalMessageEntity.m4416d());
                            } else {
                                inboxStyle.m545b(b3.m4183a(true) + ": " + historyNormalMessageEntity.m4416d());
                            }
                        }
                    } else {
                        String str;
                        a = ((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4456M();
                        b2 = ContactDataSource.m4553a().m4570b(a);
                        if (b2 == null) {
                            inboxStyle.m544a("+" + a);
                            b.m534a("+" + a);
                            str = "+" + a;
                        } else {
                            inboxStyle.m544a(b2.m4183a(true));
                            b.m534a(b2.m4183a(true));
                            a = b2.m4183a(true);
                            b.m531a(this.f4141a.m6637a(b2));
                            str = a;
                        }
                        for (HistoryNormalMessageEntity historyNormalMessageEntity2 : this.f4141a.f4146d) {
                            inboxStyle.m545b(historyNormalMessageEntity2.m4416d());
                        }
                        b.m539b(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                        b.m542c(str + ": " + String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                        b.m529a(-1, 300, 1500);
                    }
                    b.m533a(inboxStyle);
                } else if (this.f4141a.f4144b) {
                    b.m534a(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                    b.m542c(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                    b.m529a(-1, 300, 1500);
                } else {
                    a = ((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4456M();
                    b2 = ContactDataSource.m4553a().m4570b(a);
                    if (b2 == null) {
                        b.m534a("+" + a);
                        a = "+" + a;
                    } else {
                        b.m534a(b2.m4183a(true));
                        a = b2.m4183a(true);
                        b.m531a(this.f4141a.m6637a(b2));
                    }
                    b.m539b(String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                    b.m542c(a + ": " + String.format(this.f4141a.f4151j.getString(2131296612), new Object[]{Integer.valueOf(this.f4141a.f4145c)}));
                    b.m529a(-1, 300, 1500);
                }
                Intent intent = new Intent("com.bistalk.bisphone.action.notification");
                if (this.f4141a.f4144b) {
                    intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_CHATS);
                } else {
                    intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_CHAT);
                    intent.putExtra("jabber_id", ((HistoryNormalMessageEntity) this.f4141a.f4146d.get(0)).m4414c());
                }
                intent.setClass(this.f4141a.f4151j, NotificationReceiver.class);
                b.m530a(PendingIntent.getBroadcast(this.f4141a.f4151j, 202, intent, 134217728));
                Uri parse = Uri.parse("android.resource://" + this.f4141a.f4151j.getPackageName() + "/raw/notification");
                if (this.f4141a.f4157p) {
                    this.f4141a.m6648d();
                    b.m536a(NotificationGenerator.f4142h).m532a(parse);
                }
                return b;
            } catch (Exception e) {
                return null;
            }
        }

        protected void m6636a(Builder builder) {
            super.onPostExecute(builder);
            if (builder != null) {
                this.f4141a.f4150i.notify(11, builder.m527a());
            }
            this.f4141a.f4154m = false;
            this.f4141a.f4158q.postDelayed(this.f4141a.f4143a, 3000);
        }
    }

    static {
        f4142h = new long[]{50, 150, 150, 300};
    }

    public NotificationGenerator(Context context) {
        this.f4152k = new HashMap();
        this.f4157p = true;
        this.f4150i = (NotificationManager) context.getSystemService("notification");
        this.f4158q = new Handler(Looper.getMainLooper());
        this.f4156o = new HashMap();
        m6651e();
        this.f4151j = context;
        this.f4143a = new C04311(this);
        this.f4147e = new C04322(this);
    }

    void m6658a(List<String> list) {
        if (list == null || list.size() == 0) {
            Main.f1926a.m5683d("NotificationGenerator, notifyMissedCalls, empty usernames, return");
            return;
        }
        List arrayList = new ArrayList();
        boolean z = false;
        for (Entry entry : this.f4152k.entrySet()) {
            boolean z2;
            if (((String) list.get(list.size() - 1)).equals(((ContactEntity) entry.getKey()).m4200g())) {
                this.f4152k.put(entry.getKey(), Integer.valueOf(((Integer) entry.getValue()).intValue() + 1));
                z2 = true;
            } else {
                z2 = z;
            }
            arrayList.add(entry.getKey());
            z = z2;
        }
        if (!z) {
            Object b = ContactDataSource.m4553a().m4570b((String) list.get(list.size() - 1));
            if (b == null) {
                ContactEntity contactEntity = new ContactEntity();
                contactEntity.m4184a(TYPE.REMOTE);
                contactEntity.m4197f((String) list.get(list.size() - 1));
                contactEntity.m4195e((String) list.get(list.size() - 1));
                b = contactEntity;
            }
            this.f4152k.put(b, Integer.valueOf(1));
            arrayList.add(b);
        }
        if (arrayList != null && arrayList.size() != 0) {
            Builder b2 = new Builder(this.f4151j).m528a(2130837859).m540b(true);
            Main.f1926a.m5683d("hey " + arrayList.size());
            if (this.f4152k.size() == 1) {
                b2.m531a(m6637a((ContactEntity) arrayList.get(0)));
                b2.m534a(String.format(this.f4151j.getString(2131296606), new Object[]{((Integer) this.f4152k.get(arrayList.get(0))).toString(), ((ContactEntity) arrayList.get(0)).m4183a(true)})).m539b(this.f4151j.getResources().getString(2131296609)).m542c(String.format(this.f4151j.getString(2131296607), new Object[]{Integer.valueOf(((Integer) this.f4152k.get(arrayList.get(0))).intValue())}));
                b2.m529a(-1, 300, 1500);
            } else {
                int i;
                String str = "";
                String a = ((ContactEntity) arrayList.get(0)).m4183a(true);
                int intValue = ((Integer) this.f4152k.get(arrayList.get(0))).intValue() + 0;
                if (this.f4152k.size() > 2) {
                    i = intValue;
                    str = a;
                    int i2 = 1;
                    while (i2 < arrayList.size() - 1) {
                        String str2;
                        if (arrayList.get(i2) != null) {
                            str2 = str + ", " + ((ContactEntity) arrayList.get(i2)).m4183a(true);
                            i += ((Integer) this.f4152k.get(arrayList.get(i2))).intValue();
                        } else {
                            str2 = str;
                        }
                        i2++;
                        str = str2;
                    }
                } else {
                    i = intValue;
                    str = a;
                }
                a = str + " " + this.f4151j.getResources().getString(2131296782) + " " + ((ContactEntity) arrayList.get(arrayList.size() - 1)).m4183a(true);
                intValue = ((Integer) this.f4152k.get(arrayList.get(arrayList.size() - 1))).intValue() + i;
                CharSequence format = String.format(this.f4151j.getString(2131296606), new Object[]{Integer.valueOf(intValue), a});
                b2.m534a(String.format(this.f4151j.getResources().getString(2131296608), new Object[]{Integer.valueOf(intValue)})).m539b(format).m542c(format);
                b2.m529a(-1, 300, 1500);
            }
            Intent intent = new Intent("com.bistalk.bisphone.action.notification");
            if (this.f4152k.size() == 1) {
                intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_CALL_LOG);
                intent.putExtra("call", true);
                intent.putExtra("jabber_id", Utils.m7078a((String) list.get(0)));
            } else {
                intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_CALL_LOG);
                intent.putExtra("call", true);
            }
            intent.setClass(this.f4151j, NotificationReceiver.class);
            b2.m530a(PendingIntent.getBroadcast(this.f4151j, 207, intent, 134217728));
            Intent intent2 = new Intent("com.bistalk.bisphone.action.notification");
            intent2.putExtra(Action.ATTRIBUTE_NAME, ACTION.CLEAR_CONTACTS);
            intent2.setClass(this.f4151j, NotificationReceiver.class);
            b2.m538b(PendingIntent.getBroadcast(this.f4151j, 208, intent2, 134217728));
            Uri parse = Uri.parse("android.resource://" + this.f4151j.getPackageName() + "/raw/notification");
            if (this.f4157p) {
                m6648d();
                b2.m536a(f4142h).m532a(parse);
            }
            this.f4150i.notify(14, b2.m527a());
        }
    }

    void m6663b(List<String> list) {
        if (list == null || list.size() == 0) {
            Main.f1926a.m5683d("NotificationGenerator, notifyNewUsers, empty usernames, return");
            return;
        }
        List a = ContactDataSource.m4553a().m4565a((List) list);
        if (a != null) {
            int size;
            for (size = a.size() - 1; size >= 0; size--) {
                if (((ContactEntity) a.get(size)).m4210m() != TYPE.LOCAL) {
                    a.remove(size);
                }
            }
            if (a.size() != 0) {
                Builder b = new Builder(this.f4151j).m528a(2130837859).m540b(true);
                if (a.size() == 1) {
                    b.m531a(m6637a((ContactEntity) a.get(0)));
                    b.m534a(String.format(this.f4151j.getString(2131296620), new Object[]{((ContactEntity) a.get(0)).m4183a(true)})).m539b(this.f4151j.getResources().getString(2131296622)).m542c(String.format(this.f4151j.getString(2131296620), new Object[]{((ContactEntity) a.get(0)).m4183a(true)}));
                    b.m529a(-1, 300, 1500);
                } else {
                    String str = "";
                    str = ((ContactEntity) a.get(0)).m4183a(true);
                    if (a.size() > 2) {
                        size = 1;
                        while (size < a.size() - 1) {
                            String str2;
                            if (a.get(size) != null) {
                                str2 = str + ", " + ((ContactEntity) a.get(size)).m4183a(true);
                            } else {
                                str2 = str;
                            }
                            size++;
                            str = str2;
                        }
                    }
                    str = str + " " + this.f4151j.getResources().getString(2131296782) + " " + ((ContactEntity) a.get(a.size() - 1)).m4183a(true);
                    CharSequence format = String.format(this.f4151j.getString(2131296623), new Object[]{str});
                    b.m534a(String.format(this.f4151j.getResources().getString(2131296621), new Object[]{Integer.valueOf(a.size())})).m539b(format).m542c(format);
                    b.m529a(-1, 300, 1500);
                }
                Intent intent = new Intent("com.bistalk.bisphone.action.notification");
                if (list.size() == 1) {
                    intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_CHAT);
                    intent.putExtra("jabber_id", Utils.m7078a((String) list.get(0)));
                } else {
                    intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_CONTACTS);
                }
                intent.setClass(this.f4151j, NotificationReceiver.class);
                b.m530a(PendingIntent.getBroadcast(this.f4151j, 201, intent, 134217728));
                Intent intent2 = new Intent("com.bistalk.bisphone.action.notification");
                intent2.putExtra(Action.ATTRIBUTE_NAME, ACTION.CLEAR_CONTACTS);
                intent2.setClass(this.f4151j, NotificationReceiver.class);
                b.m538b(PendingIntent.getBroadcast(this.f4151j, 204, intent2, 134217728));
                Uri parse = Uri.parse("android.resource://" + this.f4151j.getPackageName() + "/raw/notification");
                if (this.f4157p) {
                    m6648d();
                    b.m536a(f4142h).m532a(parse);
                }
                this.f4150i.notify(10, b.m527a());
            }
        }
    }

    void m6659a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            Main.f1926a.m5683d("NotificationGenerator, notifyGroupInvitation, empty groupNames, return");
            return;
        }
        Builder b = new Builder(this.f4151j).m528a(2130837859).m540b(true);
        Bitmap b2;
        ContactEntity b3;
        if (list.size() == 1) {
            ConversationGroupEntity b4 = GroupManager.m7323a().m7358b((String) list.get(0));
            if (b4 == null) {
                Main.f1926a.m5683d("NotificationGenerator, notifyGroupInvitation, unknown group, return");
                return;
            }
            String str;
            b2 = m6642b(b4.m4289g());
            if (b2 != null) {
                b.m531a(b2);
            }
            String str2 = "+" + ((String) list2.get(0));
            b3 = ContactDataSource.m4553a().m4570b((String) list2.get(0));
            if (b3 == null) {
                str = str2;
            } else {
                str = b3.m4183a(true);
            }
            b.m534a(String.format(this.f4151j.getString(2131296604), new Object[]{b4.m4286f()})).m539b(String.format(this.f4151j.getString(2131296605), new Object[]{str, b4.m4286f()})).m542c(String.format(this.f4151j.getString(2131296604), new Object[]{b4.m4286f()}));
            b.m529a(-1, 300, 1500);
        } else {
            Style inboxStyle = new InboxStyle();
            if (GroupManager.m7323a().m7358b((String) list.get(0)) == null) {
                Main.f1926a.m5683d("NotificationGenerator, notifyGroupInvitation, unknown group, return");
                return;
            }
            b2 = m6642b("");
            if (b2 != null) {
                b.m531a(b2);
            }
            if (list.size() > 1) {
                for (int i = 0; i < list.size(); i++) {
                    ConversationGroupEntity b5 = GroupManager.m7323a().m7358b((String) list.get(i));
                    if (ContactDataSource.m4553a().m4570b((String) list2.get(i)) == null) {
                        inboxStyle.m545b(String.format(this.f4151j.getString(2131296605), new Object[]{"+" + ((String) list2.get(i)), b5.m4286f()}));
                    } else {
                        inboxStyle.m545b(String.format(this.f4151j.getString(2131296605), new Object[]{b3.m4183a(true), b5.m4286f()}));
                    }
                }
            }
            inboxStyle.m544a(String.format(this.f4151j.getString(2131296610), new Object[]{Integer.valueOf(list.size())}));
            b.m534a(String.format(this.f4151j.getString(2131296610), new Object[]{Integer.valueOf(list.size())}));
            b.m539b(String.format(this.f4151j.getString(2131296611), new Object[]{Integer.valueOf(list.size())}));
            b.m542c(String.format(this.f4151j.getString(2131296610), new Object[]{Integer.valueOf(list.size())}));
            b.m529a(-1, 300, 1500);
            b.m533a(inboxStyle);
        }
        Intent intent = new Intent("com.bistalk.bisphone.action.notification");
        intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.OPEN_GROUPS);
        intent.setClass(this.f4151j, NotificationReceiver.class);
        b.m530a(PendingIntent.getBroadcast(this.f4151j, 207, intent, 134217728));
        intent = new Intent("com.bistalk.bisphone.action.notification");
        intent.putExtra(Action.ATTRIBUTE_NAME, ACTION.CLEAR_GROUP_INVITATIONS);
        intent.setClass(this.f4151j, NotificationReceiver.class);
        b.m538b(PendingIntent.getBroadcast(this.f4151j, 206, intent, 134217728));
        Uri parse = Uri.parse("android.resource://" + this.f4151j.getPackageName() + "/raw/notification");
        if (this.f4157p) {
            m6648d();
            b.m536a(f4142h).m532a(parse);
        }
        this.f4150i.notify(13, b.m527a());
    }

    void m6661a(boolean z, int i, List<HistoryNormalMessageEntity> list, boolean z2) {
        this.f4145c = i;
        this.f4146d = list;
        this.f4144b = z;
        if (!z2) {
            z2 = false;
        }
        this.f4157p = z2;
        if (this.f4153l) {
            this.f4154m = true;
            return;
        }
        this.f4153l = true;
        this.f4148f = new MessageNotificationHandler(this);
        this.f4148f.execute(new Void[0]);
    }

    void m6660a(boolean z) {
        this.f4157p = z;
        if (this.f4153l) {
            this.f4155n = true;
            return;
        }
        this.f4153l = true;
        this.f4149g = new GroupNotificationHandler1(this);
        this.f4149g.execute(new Void[0]);
    }

    void m6656a(String str) {
        Intent intent = new Intent(this.f4151j, HelperCallActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        this.f4150i.notify(21, new Builder(this.f4151j).m528a(2130837713).m534a("In call with " + str).m535a(true).m540b(true).m530a(PendingIntent.getActivity(this.f4151j, 401, intent, 134217728)).m542c("In call with " + str).m529a(-1, 300, 1500).m527a());
    }

    NotificationManager m6655a() {
        return this.f4150i;
    }

    private Bitmap m6637a(ContactEntity contactEntity) {
        Bitmap bitmap = null;
        if (contactEntity.m4212o() != null) {
            int applyDimension = (int) TypedValue.applyDimension(1, 64.0f, Main.f1927b.getResources().getDisplayMetrics());
            try {
                bitmap = CustomImageLoader.m4009a().m4013a(new ImageSize(applyDimension, applyDimension), Uri.fromFile(new File(Utils.m7079a(contactEntity.m4212o(), Type.PHOTO))).toString());
            } catch (StorageException e) {
            }
            if (bitmap != null) {
                return bitmap;
            }
        }
        if (contactEntity.m4211n() == null) {
            return bitmap;
        }
        bitmap = AddressBookHelper.m3940a(this.f4151j.getContentResolver(), Uri.parse(contactEntity.m4211n()), false);
        if (bitmap == null) {
            return BitmapFactory.decodeResource(Main.f1927b.getResources(), 2130837592);
        }
        return bitmap;
    }

    private Bitmap m6642b(String str) {
        if (str == null || str.isEmpty()) {
            return BitmapFactory.decodeResource(Main.f1927b.getResources(), 2130837859);
        }
        try {
            Bitmap a = CustomImageLoader.m4009a().m4014a(new File(Utils.m7079a(str, Type.PHOTO)));
            if (a == null) {
                return null;
            }
            return Bitmap.createScaledBitmap(a, this.f4151j.getResources().getDimensionPixelSize(17104901), this.f4151j.getResources().getDimensionPixelSize(17104902), false);
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
            return BitmapFactory.decodeResource(Main.f1927b.getResources(), 2130837859);
        }
    }

    private void m6648d() {
        this.f4157p = false;
        this.f4158q.postDelayed(new C04333(this), 3000);
    }

    public void m6657a(String str, NotificationState notificationState) {
        if (this.f4156o == null) {
            m6651e();
        }
        this.f4156o.put(str, notificationState);
    }

    private void m6651e() {
        this.f4156o = ConversationGroupDataSource.m4587a().m4617e();
    }

    public void m6664b(boolean z) {
        this.f4153l = z;
    }

    public void m6662b() {
        this.f4152k.clear();
    }
}
