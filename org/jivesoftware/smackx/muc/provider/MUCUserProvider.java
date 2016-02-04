package org.jivesoftware.smackx.muc.provider;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.packet.MUCUser.Decline;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class MUCUserProvider extends ExtensionElementProvider<MUCUser> {
    public MUCUser parse(XmlPullParser xmlPullParser, int i) {
        MUCUser mUCUser = new MUCUser();
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -1183699191:
                            if (name.equals(Invite.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case -892481550:
                            if (name.equals(Status.ELEMENT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3242771:
                            if (name.equals(Item.ELEMENT)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1216985755:
                            if (name.equals("password")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1542349558:
                            if (name.equals(Decline.ELEMENT)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 1557372922:
                            if (name.equals(Destroy.ELEMENT)) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            mUCUser.setInvite(parseInvite(xmlPullParser));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            mUCUser.setItem(MUCParserUtils.parseItem(xmlPullParser));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            mUCUser.setPassword(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            mUCUser.addStatusCode(Status.create(xmlPullParser.getAttributeValue("", XHTMLText.CODE)));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            mUCUser.setDecline(parseDecline(xmlPullParser));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            mUCUser.setDestroy(MUCParserUtils.parseDestroy(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return mUCUser;
                default:
                    break;
            }
        }
    }

    private static Invite parseInvite(XmlPullParser xmlPullParser) {
        Object obj = null;
        Invite invite = new Invite();
        invite.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM));
        invite.setTo(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    invite.setReason(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(Invite.ELEMENT)) {
                obj = 1;
            }
        }
        return invite;
    }

    private static Decline parseDecline(XmlPullParser xmlPullParser) {
        Object obj = null;
        Decline decline = new Decline();
        decline.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM));
        decline.setTo(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    decline.setReason(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(Decline.ELEMENT)) {
                obj = 1;
            }
        }
        return decline;
    }
}
