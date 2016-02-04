package app.events.channel;

import app.xmpp.packet.channel.ChannelElement;

public class ChannelEvent {
    private String f2417a;
    private Action f2418b;
    private ChannelEventListener f2419c;
    private boolean f2420d;

    public enum Action {
        follow,
        unfollow,
        info
    }

    public interface ChannelEventListener {
        void m4849a();

        void m4850a(ChannelElement channelElement, Long l);
    }

    public ChannelEvent(String str, Action action, boolean z) {
        this.f2417a = str;
        this.f2418b = action;
        this.f2419c = null;
        this.f2420d = z;
    }

    public String m4851a() {
        return this.f2417a;
    }

    public Action m4853b() {
        return this.f2418b;
    }

    public ChannelEventListener m4854c() {
        return this.f2419c;
    }

    public boolean m4855d() {
        return this.f2420d;
    }

    public void m4852a(ChannelEventListener channelEventListener) {
        this.f2419c = channelEventListener;
    }
}
