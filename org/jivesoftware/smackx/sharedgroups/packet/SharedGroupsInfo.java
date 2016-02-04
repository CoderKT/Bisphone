package org.jivesoftware.smackx.sharedgroups.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.xmlpull.v1.XmlPullParser;

public class SharedGroupsInfo extends IQ {
    public static final String ELEMENT = "sharedgroup";
    public static final String NAMESPACE = "http://www.jivesoftware.org/protocol/sharedgroup";
    private List<String> groups;

    public class Provider extends IQProvider<SharedGroupsInfo> {
        public SharedGroupsInfo parse(XmlPullParser xmlPullParser, int i) {
            SharedGroupsInfo sharedGroupsInfo = new SharedGroupsInfo();
            Object obj = null;
            while (obj == null) {
                int next = xmlPullParser.next();
                if (next == 2 && xmlPullParser.getName().equals(Item.GROUP)) {
                    sharedGroupsInfo.getGroups().add(xmlPullParser.nextText());
                } else if (next == 3 && xmlPullParser.getName().equals(SharedGroupsInfo.ELEMENT)) {
                    obj = 1;
                }
            }
            return sharedGroupsInfo;
        }
    }

    public SharedGroupsInfo() {
        super(ELEMENT, NAMESPACE);
        this.groups = new ArrayList();
    }

    public List<String> getGroups() {
        return this.groups;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        for (String element : this.groups) {
            iQChildElementXmlStringBuilder.element(Item.GROUP, element);
        }
        return iQChildElementXmlStringBuilder;
    }
}
