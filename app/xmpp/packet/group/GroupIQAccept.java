package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class GroupIQAccept extends GroupIQ {
    public GroupIQAccept() {
        super("http://bisphone.com/protocol/group#accept_invitation");
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        return super.getIQChildElementBuilder(iQChildElementXmlStringBuilder);
    }
}
