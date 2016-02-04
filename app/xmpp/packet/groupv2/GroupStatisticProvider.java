package app.xmpp.packet.groupv2;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupStatisticProvider extends ExtensionElementProvider<AbstractXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7629a(xmlPullParser, i);
    }

    public AbstractXE m7629a(XmlPullParser xmlPullParser, int i) {
        if (!"group-statistics-ack".equals(xmlPullParser.getName())) {
            return null;
        }
        AbstractXE groupStatisticAckXE = new GroupStatisticAckXE();
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                Object obj2 = -1;
                switch (name.hashCode()) {
                    case 3354:
                        if (name.equals("ic")) {
                            next = 3;
                            break;
                        }
                        break;
                    case 3385:
                        if (name.equals("jc")) {
                            obj2 = null;
                            break;
                        }
                        break;
                    case 3416:
                        if (name.equals("kc")) {
                            next = 1;
                            break;
                        }
                        break;
                    case 3447:
                        if (name.equals("lc")) {
                            next = 2;
                            break;
                        }
                        break;
                    case 3484:
                        if (name.equals("mi")) {
                            obj2 = 4;
                            break;
                        }
                        break;
                    case 3487:
                        if (name.equals("ml")) {
                            obj2 = 5;
                            break;
                        }
                        break;
                }
                switch (obj2) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        groupStatisticAckXE.m7620a(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        groupStatisticAckXE.m7622b(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        groupStatisticAckXE.m7624c(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        groupStatisticAckXE.m7626d(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        groupStatisticAckXE.m7627e(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        groupStatisticAckXE.m7628f(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    default:
                        break;
                }
            } else if (next == 3 && "group-statistics-ack".equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return groupStatisticAckXE;
    }
}
