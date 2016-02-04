package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class GroupIQGetMembers extends GroupIQ {
    public GroupIQGetMembers() {
        super("http://bisphone.com/protocol/group#get_members");
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        return super.getIQChildElementBuilder(iQChildElementXmlStringBuilder);
    }
}
