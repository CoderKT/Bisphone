package app.xmpp.packet.common;

import app.xmpp.packet.common.CastXE.Type;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class CastXEProvider extends ExtensionElementProvider<CastXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7571a(xmlPullParser, i);
    }

    public CastXE m7571a(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", "v");
        while (true) {
            if (xmlPullParser.next() == 3 && "cast".equals(xmlPullParser.getName())) {
                return new CastXE(Type.m7570a(attributeValue));
            }
        }
    }
}
