package app.xmpp.packet.groupv2;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupXEProviderSimple extends ExtensionElementProvider<AbstractXESimple> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7642a(xmlPullParser, i);
    }

    public AbstractXESimple m7642a(XmlPullParser xmlPullParser, int i) {
        String name = xmlPullParser.getName();
        while (true) {
            if (xmlPullParser.next() == 3 && name.equals(xmlPullParser.getName())) {
                break;
            }
        }
        Object obj = -1;
        switch (name.hashCode()) {
            case -1983578058:
                if (name.equals("destroy-ack")) {
                    obj = 10;
                    break;
                }
                break;
            case -1760032396:
                if (name.equals("get-member-setting")) {
                    obj = 11;
                    break;
                }
                break;
            case -1402919578:
                if (name.equals("join-ack")) {
                    obj = 1;
                    break;
                }
                break;
            case -934710369:
                if (name.equals("reject")) {
                    obj = 2;
                    break;
                }
                break;
            case -651265701:
                if (name.equals("reject-ack")) {
                    int i2 = 3;
                    break;
                }
                break;
            case -18882571:
                if (name.equals("set-info-ack")) {
                    obj = 4;
                    break;
                }
                break;
            case 3267882:
                if (name.equals("join")) {
                    obj = null;
                    break;
                }
                break;
            case 102846135:
                if (name.equals("leave")) {
                    obj = 6;
                    break;
                }
                break;
            case 514382884:
                if (name.equals("set-member-setting-ack")) {
                    obj = 5;
                    break;
                }
                break;
            case 985297201:
                if (name.equals("group-statistics")) {
                    obj = 12;
                    break;
                }
                break;
            case 1557372922:
                if (name.equals(Destroy.ELEMENT)) {
                    obj = 9;
                    break;
                }
                break;
            case 1660094579:
                if (name.equals("leave-ack")) {
                    obj = 7;
                    break;
                }
                break;
            case 1930173637:
                if (name.equals("get-info")) {
                    obj = 8;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return new JoinXE();
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return new JoinAckXE();
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return new RejectXE();
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return new RejectAckXE();
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return new SetInfoAckXE();
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return new SettingAckXE();
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return new LeaveXE();
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return new LeaveAckXE();
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return new GetInfoXE();
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return new DestroyGroupsXE();
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return new DestroyAckXE();
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return new GetGroupSettingXE();
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                return new GroupStatisticXE();
            default:
                throw new SmackException("Can't parse element " + name);
        }
    }
}
