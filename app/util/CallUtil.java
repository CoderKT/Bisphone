package app.util;

import android.content.Context;
import app.Main;
import app.common.entity.CallLogEntity;
import app.common.entity.CallLogEntity.Builder;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.HistoryEntity.CallStatus;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.datasource.CallsDataSource;
import app.database.datasource.ContactDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.NewMessageEvent;
import app.notification.NotificationCenter;
import app.voip.Sip;
import app.voip.VoipManager;
import app.voip.VoipManager.State;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageHandler;
import app.xmpp.NormalMessageManager;
import de.greenrobot.event.EventBus;
import se.emilsjolander.stickylistheaders.C1128R;

public class CallUtil {

    /* renamed from: app.util.CallUtil.1 */
    /* synthetic */ class C05051 {
        static final /* synthetic */ int[] f4605a;

        static {
            f4605a = new int[CallStatus.values().length];
            try {
                f4605a[CallStatus.OUTGOING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4605a[CallStatus.INCOMING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4605a[CallStatus.MISSED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static void m7005a(String str, Context context, boolean z) {
        if (VoipManager.m7265b().m7306h() != State.IDLE) {
            Main.m3905a(context.getString(2131296864));
        } else if (Sip.m7215c()) {
            Main.m3905a(context.getString(2131296863));
        } else {
            VoipManager.m7265b().m7296a(context, new JabberId(str, "bisphone.com", null), z);
        }
    }

    public static void m7004a(Context context, CallStatus callStatus, JabberId jabberId, int i, long j, boolean z) {
        if (jabberId != null) {
            if (j == 0) {
                j = System.currentTimeMillis() * 1000;
            }
            Builder a = new Builder().m4105a(z).m4099a(0.0f).m4100a(i).m4101a(j).m4104a(jabberId.m7387a());
            HistoryNormalMessageEntity.Builder builder = new HistoryNormalMessageEntity.Builder();
            builder.m4356a(Type.CALL).m4365b(jabberId.m7390d()).m4352a(callStatus).m4372d(j + "").m4359a(StringUtil.m7065b()).m4368c(i);
            builder.m4455t(jabberId.m7387a());
            switch (C05051.f4605a[callStatus.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    a.m4102a(CallLogEntity.Type.outgoing);
                    builder.m4369c(context.getResources().getString(2131296371)).m4353a(DeliveryStatus.SENT);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    a.m4102a(CallLogEntity.Type.incoming);
                    builder.m4369c(context.getResources().getString(2131296363)).m4353a(DeliveryStatus.RECEIVED);
                    break;
                default:
                    NotificationCenter.m6606a().m6609a(jabberId.m7387a());
                    a.m4102a(CallLogEntity.Type.missed);
                    builder.m4369c(context.getResources().getString(2131296365)).m4353a(DeliveryStatus.RECEIVED);
                    break;
            }
            CallLogEntity a2 = a.m4106a();
            Main.f1926a.m5681c("inserted call record of type " + callStatus + " and id " + CallsDataSource.m4527a().m4529a(a2) + " in callLog");
            HistoryNormalMessageEntity a3 = builder.m4453a();
            if (!z) {
                long a4 = HistoryNormalMessageDataSource.m4717a(context, a3);
                a3.m4408a(a4);
                Main.f1926a.m5681c("inserted call record of type " + callStatus + " and id " + a4 + " in messageTable");
            }
            NormalMessageManager.m7447a().m7450a(new CallLogHistoryEntity(a2, ContactDataSource.m4553a().m4582g(jabberId.m7387a())));
            NormalMessageHandler.m7415a().m7437a(a3, callStatus != CallStatus.MISSED);
            EventBus.m12779a().m12795d(new NewMessageEvent(a3, jabberId.m7387a()));
        }
    }
}
