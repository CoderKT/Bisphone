package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.search.UserSearch;

public abstract class AbstractChannelIQ extends IQ {
    protected ChannelElement f5058a;
    protected TimeElement f5059b;
    protected HistoryElement f5060c;

    protected AbstractChannelIQ(String str) {
        super(UserSearch.ELEMENT, str);
    }

    public ChannelElement m7533a() {
        return this.f5058a;
    }

    public TimeElement m7537b() {
        return this.f5059b;
    }

    public void m7534a(ChannelElement channelElement) {
        if (channelElement == null) {
            throw new IllegalArgumentException("channel element can't be null");
        }
        this.f5058a = channelElement;
    }

    public void m7536a(TimeElement timeElement) {
        this.f5059b = timeElement;
    }

    public void m7535a(HistoryElement historyElement) {
        this.f5060c = historyElement;
    }
}
