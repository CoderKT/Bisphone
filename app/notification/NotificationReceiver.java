package app.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import app.Main;
import app.home.HomeActivity;
import app.messaging.GroupMessagingActivity;
import app.messaging.NormalMessagingActivity;
import app.voip.VoipManager;
import app.voip.VoipManager.State;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class NotificationReceiver extends BroadcastReceiver {

    /* renamed from: app.notification.NotificationReceiver.1 */
    /* synthetic */ class C04351 {
        static final /* synthetic */ int[] f4159a;

        static {
            f4159a = new int[ACTION.values().length];
            try {
                f4159a[ACTION.CLEAR_CONTACTS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4159a[ACTION.CLEAR_GROUP_INVITATIONS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4159a[ACTION.OPEN_CALL_LOG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4159a[ACTION.OPEN_CHAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4159a[ACTION.OPEN_GROUP_CHAT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4159a[ACTION.OPEN_CHATS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4159a[ACTION.OPEN_GROUPS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4159a[ACTION.OPEN_CONTACTS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    enum ACTION {
        OPEN_CHAT,
        OPEN_GROUP_CHAT,
        OPEN_CHATS,
        OPEN_GROUPS,
        OPEN_CONTACTS,
        OPEN_CALL_LOG,
        CLEAR_CHATS,
        CLEAR_GROUP_CHATS,
        CLEAR_CONTACTS,
        CLEAR_GROUP_INVITATIONS
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            boolean booleanExtra;
            ACTION action = (ACTION) intent.getExtras().get(Action.ATTRIBUTE_NAME);
            if (intent.getExtras().containsKey("call")) {
                booleanExtra = intent.getBooleanExtra("call", false);
            } else {
                booleanExtra = false;
            }
            if (action != null) {
                switch (C04351.f4159a[action.ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        NotificationCenter.m6606a().m6616c();
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        NotificationCenter.m6606a().m6617d();
                    default:
                        if (VoipManager.m7265b().m7306h() == State.IDLE) {
                            String stringExtra = intent.getStringExtra("jabber_id");
                            Intent intent2 = new Intent(context, HomeActivity.class);
                            TaskStackBuilder a = TaskStackBuilder.m603a(context);
                            Intent intent3;
                            switch (C04351.f4159a[action.ordinal()]) {
                                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                                    intent2.putExtra("extra_tab", 0);
                                    intent2.putExtra("extra_fragment", booleanExtra);
                                    a.m604a(intent2);
                                    break;
                                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                                    intent3 = new Intent(context, NormalMessagingActivity.class);
                                    intent2.putExtra("extra_tab", 0);
                                    intent3.putExtra("extra_jabber_id", stringExtra);
                                    a.m604a(intent2).m604a(intent3);
                                    break;
                                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                                    intent3 = new Intent(context, GroupMessagingActivity.class);
                                    intent2.putExtra("extra_tab", 1);
                                    intent3.putExtra("extra_group_jid", stringExtra);
                                    a.m604a(intent2).m604a(intent3);
                                    break;
                                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                                    intent2.putExtra("extra_tab", 0);
                                    a.m604a(intent2);
                                    break;
                                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                                    intent2.putExtra("extra_tab", 1);
                                    a.m604a(intent2);
                                    break;
                                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                                    intent2.putExtra("extra_tab", 2);
                                    a.m604a(intent2);
                                    break;
                                default:
                                    Main.f1926a.m5679b("NotificationReceiver: default action?");
                                    return;
                            }
                            try {
                                a.m605a();
                            } catch (Throwable e) {
                                Main.f1926a.m5679b("This should not happen");
                                Main.f1926a.m5680b(e);
                            }
                        }
                }
            }
        }
    }
}
