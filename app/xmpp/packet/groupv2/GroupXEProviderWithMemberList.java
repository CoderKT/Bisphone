package app.xmpp.packet.groupv2;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupXEProviderWithMemberList extends ExtensionElementProvider<AbstractXEWithMemberList> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7645a(xmlPullParser, i);
    }

    public AbstractXEWithMemberList m7645a(XmlPullParser xmlPullParser, int i) {
        AbstractXEWithMemberList membersAckXE;
        String name = xmlPullParser.getName();
        Object obj = -1;
        int i2;
        switch (name.hashCode()) {
            case -1183699191:
                if (name.equals(Invite.ELEMENT)) {
                    i2 = 2;
                    break;
                }
                break;
            case -864709502:
                if (name.equals("kick-ack")) {
                    i2 = 3;
                    break;
                }
                break;
            case 3291718:
                if (name.equals("kick")) {
                    obj = 1;
                    break;
                }
                break;
            case 1196912581:
                if (name.equals("invite-ack")) {
                    obj = 4;
                    break;
                }
                break;
            case 1400406485:
                if (name.equals("members-ack")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                membersAckXE = new MembersAckXE();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                membersAckXE = new KickXE();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                membersAckXE = new InviteXE();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                membersAckXE = new KickAckXE();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                membersAckXE = new InviteAckXE();
                break;
            default:
                throw new SmackException("Can't parse element " + name);
        }
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "m".equals(xmlPullParser.getName())) {
                membersAckXE.m7596a(MemberElement.m7658a(xmlPullParser));
            } else if (next == 3 && name.equals(xmlPullParser.getName())) {
                return membersAckXE;
            }
        }
    }
}
