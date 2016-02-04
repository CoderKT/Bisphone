package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class GroupIQCreate extends GroupIQ {
    public GroupIQCreate() {
        super("http://bisphone.com/protocol/group#create_group");
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        return super.getIQChildElementBuilder(iQChildElementXmlStringBuilder);
    }
}
