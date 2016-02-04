package app.xmpp.packet.reply;

import app.xmpp.packet.common.CastXE.Type;
import app.xmpp.packet.groupv2.AbstractXE;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.caps.packet.CapsExtension;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class ReplyXEProvider extends ExtensionElementProvider<AbstractXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7710a(xmlPullParser, i);
    }

    public AbstractXE m7710a(XmlPullParser xmlPullParser, int i) {
        String name = xmlPullParser.getName();
        Object obj = -1;
        switch (name.hashCode()) {
            case -1293259981:
                if (name.equals("reply-audio")) {
                    obj = null;
                    break;
                }
                break;
            case -1279783537:
                if (name.equals("reply-photo")) {
                    obj = 2;
                    break;
                }
                break;
            case -1274223656:
                if (name.equals("reply-video")) {
                    obj = 5;
                    break;
                }
                break;
            case -595356112:
                if (name.equals("reply-text")) {
                    obj = 4;
                    break;
                }
                break;
            case -434853895:
                if (name.equals("reply-map")) {
                    obj = 1;
                    break;
                }
                break;
            case 1488680282:
                if (name.equals("reply-sticker")) {
                    obj = 3;
                    break;
                }
                break;
        }
        String attributeValue;
        Type type;
        String attributeValue2;
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                attributeValue = xmlPullParser.getAttributeValue("", "id");
                type = Type.unicast;
                if (xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT) != null) {
                    type = Type.m7570a(xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT));
                }
                return new ReplyAudioXE(attributeValue, type, xmlPullParser.getAttributeValue("", "u"));
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return new ReplyMapXE(xmlPullParser.getAttributeValue("", "id"), xmlPullParser.nextText(), xmlPullParser.getAttributeValue("", "u"));
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                attributeValue = xmlPullParser.getAttributeValue("", "id");
                attributeValue2 = xmlPullParser.getAttributeValue("", "u");
                type = Type.unicast;
                if (xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT) != null) {
                    type = Type.m7570a(xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT));
                }
                return new ReplyPhotoXE(attributeValue, xmlPullParser.nextText(), type, attributeValue2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return new ReplyStickerXE(xmlPullParser.getAttributeValue("", "id"), xmlPullParser.nextText(), xmlPullParser.getAttributeValue("", "u"));
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                attributeValue = xmlPullParser.getAttributeValue("", "id");
                attributeValue2 = xmlPullParser.getAttributeValue("", "u");
                type = Type.unicast;
                if (xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT) != null) {
                    type = Type.m7570a(xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT));
                }
                return new ReplyTextXE(attributeValue, xmlPullParser.nextText(), type, attributeValue2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                attributeValue = xmlPullParser.getAttributeValue("", "id");
                attributeValue2 = xmlPullParser.getAttributeValue("", "u");
                type = Type.unicast;
                if (xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT) != null) {
                    type = Type.m7570a(xmlPullParser.getAttributeValue("", CapsExtension.ELEMENT));
                }
                return new ReplyVideoXE(attributeValue, xmlPullParser.nextText(), type, attributeValue2);
            default:
                throw new SmackException("Can't parse element " + name);
        }
    }
}
