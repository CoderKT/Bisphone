package app.messaging.channel;

import android.content.Context;
import app.Main;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.events.channel.ChannelEvent;
import app.events.channel.ChannelEvent.Action;
import app.events.channel.ChannelEvent.ChannelEventListener;
import app.messaging.channel.ChannelModel.State;
import app.util.SharedPreferencesUtil;
import app.xmpp.packet.channel.ChannelElement;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelCommunication {
    static ChannelCommunication f3465b;
    HashMap<String, Boolean> f3466a;
    ArrayList<String> f3467c;

    /* renamed from: app.messaging.channel.ChannelCommunication.1 */
    class C02971 implements ChannelEventListener {
        final /* synthetic */ String f3457a;
        final /* synthetic */ ChannelModel f3458b;
        final /* synthetic */ ViewHolder f3459c;
        final /* synthetic */ ChannelCommunication f3460d;

        C02971(ChannelCommunication channelCommunication, String str, ChannelModel channelModel, ViewHolder viewHolder) {
            this.f3460d = channelCommunication;
            this.f3457a = str;
            this.f3458b = channelModel;
            this.f3459c = viewHolder;
        }

        public void m6089a(ChannelElement channelElement, Long l) {
            this.f3460d.m6096a(this.f3457a, Boolean.valueOf(true));
            Main.f1926a.m5681c("follow channel");
            if (l != null) {
                if (l.toString().length() < 16) {
                    l = Long.valueOf(l.longValue() * 1000);
                }
                this.f3458b.setTimestamp(l.longValue());
            }
            this.f3458b.setFollowing(Boolean.valueOf(true));
            this.f3458b.setState(State.joined);
            ChannelDataSource.m4535a(this.f3458b);
            EventBus.m12779a().m12795d(new ChannelCommunicationFollowEntity(this.f3459c, this.f3457a, true));
            if (this.f3457a.equals("chan-bisphone-01@channel.bisphone.com")) {
                SharedPreferencesUtil.m7054a(Main.f1927b.getString(2131296922), Boolean.valueOf(true));
            }
        }

        public void m6088a() {
            EventBus.m12779a().m12795d(new ChannelCommunicationFollowEntity(this.f3459c, this.f3457a, false));
        }
    }

    /* renamed from: app.messaging.channel.ChannelCommunication.2 */
    class C02982 implements ChannelEventListener {
        final /* synthetic */ String f3461a;
        final /* synthetic */ boolean f3462b;
        final /* synthetic */ ViewHolder f3463c;
        final /* synthetic */ ChannelCommunication f3464d;

        C02982(ChannelCommunication channelCommunication, String str, boolean z, ViewHolder viewHolder) {
            this.f3464d = channelCommunication;
            this.f3461a = str;
            this.f3462b = z;
            this.f3463c = viewHolder;
        }

        public void m6091a(ChannelElement channelElement, Long l) {
            this.f3464d.m6096a(this.f3461a, Boolean.valueOf(false));
            Main.f1926a.m5681c("unFollow success");
            if (this.f3462b) {
                ChannelDataSource.m4544b(channelElement.m7545a());
                HistoryChannelDataSource.m4674b(channelElement.m7545a());
            } else {
                ChannelDataSource.m4548c(channelElement.m7545a());
            }
            EventBus.m12779a().m12795d(new ChannelCommunicationUnfollowEntity(this.f3463c, this.f3461a, true));
        }

        public void m6090a() {
            Main.f1926a.m5681c("unFollow failed");
        }
    }

    public static ChannelCommunication getInstance() {
        if (f3465b == null) {
            f3465b = new ChannelCommunication();
        }
        return f3465b;
    }

    public ChannelCommunication() {
        this.f3466a = new HashMap();
    }

    public void m6094a(ChannelModel channelModel, String str, ViewHolder viewHolder) {
        m6096a(str, null);
        ChannelEvent channelEvent = new ChannelEvent(str, Action.follow, false);
        channelEvent.m4852a(new C02971(this, str, channelModel, viewHolder));
        EventBus.m12779a().m12795d(channelEvent);
    }

    public List<String> m6093a(Context context) {
        if (this.f3467c != null) {
            return this.f3467c;
        }
        String a = SharedPreferencesUtil.m7052a(context.getString(2131296924));
        this.f3467c = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(2131361795);
        if (a == null || a.length() == 0) {
            return m6097b(context);
        }
        String[] split = a.split(",");
        for (String split2 : split) {
            this.f3467c.add(stringArray[Integer.parseInt(split2.split("-")[1])]);
        }
        return this.f3467c;
    }

    public List<String> m6097b(Context context) {
        this.f3467c = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(2131361795);
        String[] stringArray2 = context.getResources().getStringArray(2131361793);
        String a = SharedPreferencesUtil.m7052a(context.getResources().getString(2131296925));
        for (int i = 0; i < stringArray2.length; i++) {
            if (stringArray2[i].equals(a)) {
                this.f3467c.add(stringArray[i]);
                break;
            }
        }
        return this.f3467c;
    }

    public void m6096a(String str, Boolean bool) {
        this.f3466a.put(str, bool);
    }

    public int m6092a(String str) {
        if (!this.f3466a.containsKey(str)) {
            return -2;
        }
        Boolean bool = (Boolean) this.f3466a.get(str);
        if (bool == null) {
            return -1;
        }
        return bool.booleanValue() ? 1 : 0;
    }

    public void m6095a(String str, ViewHolder viewHolder, boolean z) {
        m6096a(str, null);
        ChannelEvent channelEvent = new ChannelEvent(str, Action.unfollow, z);
        channelEvent.m4852a(new C02982(this, str, z, viewHolder));
        EventBus.m12779a().m12795d(channelEvent);
    }
}
