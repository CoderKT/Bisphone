package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smackx.search.UserSearch;

public class GroupIQ extends IQ {
    private GroupElement f5124a;

    public GroupIQ(String str) {
        super(UserSearch.ELEMENT, str);
    }

    public void m7587a(GroupElement groupElement) {
        if (groupElement == null) {
            throw new IllegalArgumentException("group element can't be null");
        }
        this.f5124a = groupElement;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket().append(this.f5124a.m7583a());
        return iQChildElementXmlStringBuilder;
    }
}
