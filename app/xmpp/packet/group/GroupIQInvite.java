package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class GroupIQInvite extends GroupIQ {
    public GroupIQInvite() {
        super("http://bisphone.com/protocol/group#invite_member");
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        return super.getIQChildElementBuilder(iQChildElementXmlStringBuilder);
    }
}
