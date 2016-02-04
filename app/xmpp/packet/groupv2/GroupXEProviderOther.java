package app.xmpp.packet.groupv2;

import app.xmpp.packet.common.ErrorXE;
import app.xmpp.packet.common.ErrorXE.ErrorType;
import app.xmpp.packet.groupv2.EventXE.Action;
import app.xmpp.packet.groupv2.ListGroupsXE.GetGroupType;
import app.xmpp.packet.groupv2.MembersXE.MemberState;
import app.xmpp.packet.groupv2.OneToOneEventXE.OneToOneEventAction;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupXEProviderOther extends ExtensionElementProvider<AbstractXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7641a(xmlPullParser, i);
    }

    public AbstractXE m7641a(XmlPullParser xmlPullParser, int i) {
        String name = xmlPullParser.getName();
        Object obj = -1;
        switch (name.hashCode()) {
            case -1998430739:
                if (name.equals("group-statistics-ack")) {
                    obj = 2;
                    break;
                }
                break;
            case -1651302877:
                if (name.equals("selective-history")) {
                    obj = 9;
                    break;
                }
                break;
            case -1642549705:
                if (name.equals("oto-event")) {
                    obj = 10;
                    break;
                }
                break;
            case -839998744:
                if (name.equals("set-member-setting")) {
                    obj = 7;
                    break;
                }
                break;
            case -798745424:
                if (name.equals("get-member-setting-ack")) {
                    obj = 3;
                    break;
                }
                break;
            case -582293461:
                if (name.equals("get-groups")) {
                    obj = 12;
                    break;
                }
                break;
            case 100709:
                if (name.equals("err")) {
                    obj = 11;
                    break;
                }
                break;
            case 3237038:
                if (name.equals("info")) {
                    obj = 1;
                    break;
                }
                break;
            case 96891546:
                if (name.equals("event")) {
                    obj = 4;
                    break;
                }
                break;
            case 350840784:
                if (name.equals("history-ack")) {
                    obj = 8;
                    break;
                }
                break;
            case 926934164:
                if (name.equals(History.ELEMENT)) {
                    obj = 6;
                    break;
                }
                break;
            case 948881689:
                if (name.equals("members")) {
                    obj = null;
                    break;
                }
                break;
            case 1368043576:
                if (name.equals("create-ack")) {
                    obj = 5;
                    break;
                }
                break;
        }
        String attributeValue;
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return m7636f(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return m7634d(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return m7635e(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return m7638h(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                Action a = Action.m7597a(xmlPullParser.getAttributeValue("", XHTMLText.f8584A));
                String attributeValue2 = xmlPullParser.getAttributeValue("", "m");
                String attributeValue3 = xmlPullParser.getAttributeValue("", "b");
                m7631a(xmlPullParser, name);
                return new EventXE(a, attributeValue2, attributeValue3);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                attributeValue = xmlPullParser.getAttributeValue("", "id");
                m7631a(xmlPullParser, name);
                return new CreateAckXE(attributeValue);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return m7633c(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return m7637g(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return m7639i(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return m7640j(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return m7632b(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                attributeValue = xmlPullParser.getAttributeValue("", AckRequest.ELEMENT);
                m7631a(xmlPullParser, name);
                return new ErrorXE(ErrorType.m7575a(attributeValue));
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                return m7630a(xmlPullParser);
            default:
                throw new SmackException("Can't parse element " + name);
        }
    }

    private ListGroupsXE m7630a(XmlPullParser xmlPullParser) {
        GetGroupType getGroupType = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                getGroupType = GetGroupType.m7655a(xmlPullParser.getAttributeValue("", "s"));
            } else if (next == 3 && "get-groups".equals(xmlPullParser.getName())) {
                return new ListGroupsXE(getGroupType);
            }
        }
    }

    private OneToOneEventXE m7632b(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue("", XHTMLText.f8584A);
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
            return null;
        }
        OneToOneEventXE oneToOneEventXE = new OneToOneEventXE(groupElement);
        oneToOneEventXE.m7672a(OneToOneEventAction.m7671a(attributeValue));
        return oneToOneEventXE;
    }

    private AbstractXE m7633c(XmlPullParser xmlPullParser) {
        long j = 0;
        int i = 0;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                j = Long.parseLong(xmlPullParser.getAttributeValue("", "timestamp"));
                i = Integer.parseInt(xmlPullParser.getAttributeValue("", "window"));
            } else if (next == 3 && History.ELEMENT.equals(xmlPullParser.getName())) {
                return new HistoryXE(j, i);
            }
        }
    }

    private AbstractXE m7634d(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue("", "i");
        String name = xmlPullParser.getName();
        GroupElement groupElement = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && Item.GROUP.equals(xmlPullParser.getName())) {
                groupElement = GroupElement.m7605a(xmlPullParser);
            } else if (next == 3 && name.equals(xmlPullParser.getName())) {
                return new InfoXE(groupElement, attributeValue);
            }
        }
    }

    private AbstractXE m7635e(XmlPullParser xmlPullParser) {
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
                            obj2 = null;
                            break;
                        }
                        break;
                    case 3385:
                        if (name.equals("jc")) {
                            obj2 = 4;
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
                            obj2 = 5;
                            break;
                        }
                        break;
                    case 3487:
                        if (name.equals("ml")) {
                            next = 3;
                            break;
                        }
                        break;
                }
                switch (obj2) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        groupStatisticAckXE.m7626d(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        groupStatisticAckXE.m7622b(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        groupStatisticAckXE.m7624c(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        groupStatisticAckXE.m7628f(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        groupStatisticAckXE.m7620a(Integer.parseInt(xmlPullParser.nextText()));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        groupStatisticAckXE.m7627e(Integer.parseInt(xmlPullParser.nextText()));
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

    private void m7631a(XmlPullParser xmlPullParser, String str) {
        while (true) {
            if (xmlPullParser.next() == 3 && str.equals(xmlPullParser.getName())) {
                return;
            }
        }
    }

    private MembersXE m7636f(XmlPullParser xmlPullParser) {
        MemberState memberState = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                memberState = MemberState.m7662a(xmlPullParser.getAttributeValue("", "s"));
            } else if (next == 3 && "members".equals(xmlPullParser.getName())) {
                return new MembersXE(memberState);
            }
        }
    }

    private SettingXE m7637g(XmlPullParser xmlPullParser) {
        SettingXE settingXE = new SettingXE();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "notif".equals(xmlPullParser.getName())) {
                settingXE.m7677a(NotificationState.m7675a(xmlPullParser.getAttributeValue("", "v")));
            } else if (next == 2 && "temp-mute".equals(xmlPullParser.getName())) {
                settingXE.m7678a(Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue("", "v"))));
            } else if (next == 3 && "set-member-setting".equals(xmlPullParser.getName())) {
                return settingXE;
            }
        }
    }

    private GetGroupSettingAckXE m7638h(XmlPullParser xmlPullParser) {
        GetGroupSettingAckXE getGroupSettingAckXE = new GetGroupSettingAckXE();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "notif".equals(xmlPullParser.getName())) {
                getGroupSettingAckXE.m7602a(NotificationState.m7675a(xmlPullParser.getAttributeValue("", "v")));
            } else if (next == 2 && "temp-mute".equals(xmlPullParser.getName())) {
                getGroupSettingAckXE.m7603a(Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue("", "v"))));
            } else if (next == 3 && "set-member-setting".equals(xmlPullParser.getName())) {
                return getGroupSettingAckXE;
            }
        }
    }

    private HistoryAckXE m7639i(XmlPullParser xmlPullParser) {
        int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue("", "window"));
        String attributeValue = xmlPullParser.getAttributeValue("", "last");
        HistoryAckXE historyAckXE = new HistoryAckXE(parseInt);
        while (true) {
            parseInt = xmlPullParser.next();
            if (parseInt == 2 && "m".equals(xmlPullParser.getName())) {
                historyAckXE.m7648a(new HistoryAckXE.Item(xmlPullParser.getAttributeValue("", "id")));
            } else if (parseInt == 3 && "history-ack".equals(xmlPullParser.getName())) {
                historyAckXE.m7649a(attributeValue);
                return historyAckXE;
            }
        }
    }

    private SelectiveHistoryXE m7640j(XmlPullParser xmlPullParser) {
        SelectiveHistoryXE selectiveHistoryXE = new SelectiveHistoryXE();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "m".equals(xmlPullParser.getName())) {
                selectiveHistoryXE.m7674a(new HistoryAckXE.Item(xmlPullParser.getAttributeValue("", "id")));
            } else if (next == 3 && "selective-history".equals(xmlPullParser.getName())) {
                return selectiveHistoryXE;
            }
        }
    }
}
