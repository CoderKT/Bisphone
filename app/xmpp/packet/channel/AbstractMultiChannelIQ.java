package app.xmpp.packet.channel;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.search.UserSearch;

public abstract class AbstractMultiChannelIQ extends IQ {
    protected final List<ChannelElement> f5061a;

    protected AbstractMultiChannelIQ(String str) {
        super(UserSearch.ELEMENT, str);
        this.f5061a = new ArrayList();
    }

    public void m7538a(ChannelElement channelElement) {
        synchronized (this.f5061a) {
            this.f5061a.add(channelElement);
        }
    }
}
