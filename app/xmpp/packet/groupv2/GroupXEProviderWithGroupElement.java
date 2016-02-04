package app.xmpp.packet.groupv2;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupXEProviderWithGroupElement extends ExtensionElementProvider<AbstractXEWithGroupElement> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7643a(xmlPullParser, i);
    }

    public AbstractXEWithGroupElement m7643a(XmlPullParser xmlPullParser, int i) {
        String name = xmlPullParser.getName();
        GroupElement groupElement = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next != 2 || !Item.GROUP.equals(xmlPullParser.getName())) {
                if (next == 3 && name.equals(xmlPullParser.getName())) {
                    break;
                }
            }
            groupElement = GroupElement.m7605a(xmlPullParser);
        }
        if (groupElement == null) {
            throw new SmackException("No GroupElement Specified!");
        }
        Object obj = -1;
        switch (name.hashCode()) {
            case -1567875199:
                if (name.equals("get-info-ack")) {
                    next = 2;
                    break;
                }
                break;
            case -1352294148:
                if (name.equals("create")) {
                    obj = null;
                    break;
                }
                break;
            case 1195341721:
                if (name.equals("invitation")) {
                    obj = 1;
                    break;
                }
                break;
            case 1369061177:
                if (name.equals("set-info")) {
                    next = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return new CreateXE(groupElement);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return new InvitationXE(groupElement);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return new GetInfoAckXE(groupElement);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return new SetInfoXE(groupElement);
            default:
                throw new SmackException("Can't parse element " + name);
        }
    }
}
