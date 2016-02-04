package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class ChannelIQFollow extends AbstractChannelIQ {
    public ChannelIQFollow() {
        super("http://bisphone.com/protocol/channel/consumer/action#follow");
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        if (this.a != null) {
            iQChildElementXmlStringBuilder.append(this.a.m7561h());
        }
        if (this.b != null) {
            iQChildElementXmlStringBuilder.append(this.b.m7569b());
        }
        if (this.c != null) {
            iQChildElementXmlStringBuilder.append(this.c.m7565a());
        }
        return iQChildElementXmlStringBuilder;
    }
}
