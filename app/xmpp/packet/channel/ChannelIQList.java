package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class ChannelIQList extends AbstractMultiChannelIQ {
    public ChannelIQList() {
        super("http://bisphone.com/protocol/channel/common/discover#list");
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        for (ChannelElement h : this.a) {
            iQChildElementXmlStringBuilder.append(h.m7561h());
        }
        return iQChildElementXmlStringBuilder;
    }
}
