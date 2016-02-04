package app.xmpp.packet.groupv2;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupXEProviderWithGroupList extends ExtensionElementProvider<AbstractXEWithGroupList> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7644a(xmlPullParser, i);
    }

    public AbstractXEWithGroupList m7644a(XmlPullParser xmlPullParser, int i) {
        String name = xmlPullParser.getName();
        Object obj = -1;
        switch (name.hashCode()) {
            case -267728921:
                if (name.equals("get-groups-ack")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                AbstractXEWithGroupList listGroupsAckXE = new ListGroupsAckXE();
                while (true) {
                    int next = xmlPullParser.next();
                    if (next == 2 && Item.GROUP.equals(xmlPullParser.getName())) {
                        listGroupsAckXE.m7594a(MigrationGroupElement.m7664b(xmlPullParser));
                    } else if (next == 3 && name.equals(xmlPullParser.getName())) {
                        return listGroupsAckXE;
                    }
                }
                break;
            default:
                throw new SmackException("Can't parse element " + name);
        }
    }
}
