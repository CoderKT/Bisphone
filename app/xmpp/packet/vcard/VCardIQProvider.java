package app.xmpp.packet.vcard;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.xmlpull.v1.XmlPullParser;

public class VCardIQProvider extends IQProvider<VCard> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7728a(xmlPullParser, i);
    }

    public VCard m7728a(XmlPullParser xmlPullParser, int i) {
        VCard vCard = new VCard();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "JABBERID".equals(xmlPullParser.getName())) {
                vCard.m7724a(xmlPullParser.nextText());
            } else if (next == 2 && "PHOTO".equals(xmlPullParser.getName())) {
                vCard.m7726b(xmlPullParser.nextText());
            } else if (next == 2 && "NICKNAME".equals(xmlPullParser.getName())) {
                vCard.m7727c(xmlPullParser.nextText());
            } else if (next == 3 && VCard.ELEMENT.equals(xmlPullParser.getName())) {
                return vCard;
            }
        }
    }
}
