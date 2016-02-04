package app.xmpp.packet.channel;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class ChannelIQEnter extends AbstractChannelIQ {
    public ChannelIQEnter() {
        super("http://bisphone.com/protocol/channel/common/action#enter");
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
