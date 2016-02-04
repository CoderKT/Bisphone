package org.jivesoftware.smackx.vcardtemp.provider;

import app.C0110R;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class VCardProvider extends IQProvider<VCard> {
    private static final String[] ADR;
    private static final String[] TEL;

    static {
        ADR = new String[]{"POSTAL", "PARCEL", "DOM", "INTL", "PREF", "POBOX", "EXTADR", "STREET", "LOCALITY", "REGION", "PCODE", "CTRY", "FF"};
        TEL = new String[]{"VOICE", "FAX", "PAGER", "MSG", "CELL", "VIDEO", "BBS", "MODEM", "ISDN", "PCS", "PREF"};
    }

    public VCard parse(XmlPullParser xmlPullParser, int i) {
        VCard vCard = new VCard();
        String str = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str = xmlPullParser.getName();
                    Object obj = -1;
                    switch (str.hashCode()) {
                        case -370243905:
                            if (str.equals("JABBERID")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case C0110R.styleable.Theme_panelBackground /*78*/:
                            if (str.equals("N")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 64655:
                            if (str.equals("ADR")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 78532:
                            if (str.equals("ORG")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 82939:
                            if (str.equals("TEL")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 66081660:
                            if (str.equals("EMAIL")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 76105234:
                            if (str.equals("PHOTO")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 853317742:
                            if (str.equals("NICKNAME")) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            parseName(xmlPullParser, vCard);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            parseOrg(xmlPullParser, vCard);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            parseTel(xmlPullParser, vCard);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            parseAddress(xmlPullParser, vCard);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            parseEmail(xmlPullParser, vCard);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            vCard.setNickName(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                            vCard.setJabberId(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                            parsePhoto(xmlPullParser, vCard);
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return vCard;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    if (i + 1 != xmlPullParser.getDepth()) {
                        break;
                    }
                    vCard.setField(str, xmlPullParser.getText());
                    break;
                default:
                    break;
            }
        }
    }

    private static void parseAddress(XmlPullParser xmlPullParser, VCard vCard) {
        int depth = xmlPullParser.getDepth();
        Object obj = 1;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    if (!"HOME".equals(name)) {
                        for (String equals : ADR) {
                            if (equals.equals(name)) {
                                if (obj != null) {
                                    vCard.setAddressFieldWork(name, xmlPullParser.nextText());
                                } else {
                                    vCard.setAddressFieldHome(name, xmlPullParser.nextText());
                                }
                            }
                        }
                        break;
                    }
                    obj = null;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static void parseTel(XmlPullParser xmlPullParser, VCard vCard) {
        int depth = xmlPullParser.getDepth();
        Object obj = 1;
        String str = null;
        while (true) {
            Object obj2;
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    if (!"HOME".equals(name)) {
                        if (str != null && "NUMBER".equals(name)) {
                            if (obj == null) {
                                vCard.setPhoneHome(str, xmlPullParser.nextText());
                                obj2 = obj;
                                break;
                            }
                            vCard.setPhoneWork(str, xmlPullParser.nextText());
                            obj2 = obj;
                            break;
                        }
                        for (String equals : TEL) {
                            if (equals.equals(name)) {
                                str = name;
                            }
                        }
                        obj2 = obj;
                        break;
                    }
                    obj2 = null;
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() == depth) {
                        return;
                    }
                    break;
            }
            obj2 = obj;
            obj = obj2;
        }
    }

    private static void parseOrg(XmlPullParser xmlPullParser, VCard vCard) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -486104241:
                            if (name.equals("ORGNAME")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -485883320:
                            if (name.equals("ORGUNIT")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            vCard.setOrganization(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            vCard.setOrganizationUnit(xmlPullParser.nextText());
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static void parseEmail(XmlPullParser xmlPullParser, VCard vCard) {
        int depth = xmlPullParser.getDepth();
        Object obj = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj2 = -1;
                    switch (name.hashCode()) {
                        case -1782700506:
                            if (name.equals("USERID")) {
                                int i = 1;
                                break;
                            }
                            break;
                        case 2670353:
                            if (name.equals("WORK")) {
                                obj2 = null;
                                break;
                            }
                            break;
                    }
                    switch (obj2) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            obj = 1;
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            if (obj == null) {
                                vCard.setEmailHome(xmlPullParser.nextText());
                                break;
                            } else {
                                vCard.setEmailWork(xmlPullParser.nextText());
                                break;
                            }
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static void parseName(XmlPullParser xmlPullParser, VCard vCard) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -2021012075:
                            if (name.equals("MIDDLE")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -1926781294:
                            if (name.equals("PREFIX")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -1838093487:
                            if (name.equals("SUFFIX")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 67829597:
                            if (name.equals("GIVEN")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 2066435940:
                            if (name.equals("FAMILY")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            vCard.setLastName(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            vCard.setFirstName(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            vCard.setMiddleName(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            vCard.setPrefix(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            vCard.setSuffix(xmlPullParser.nextText());
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static void parsePhoto(XmlPullParser xmlPullParser, VCard vCard) {
        String str = null;
        int depth = xmlPullParser.getDepth();
        String str2 = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 2590522:
                            if (name.equals("TYPE")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1959349434:
                            if (name.equals("BINVAL")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            str2 = xmlPullParser.nextText();
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            str = xmlPullParser.nextText();
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    } else if (str2 != null && str != null) {
                        vCard.setAvatar(str2, str);
                        return;
                    } else {
                        return;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
