package app.xmpp.packet.channel;

import app.xmpp.packet.channel.CastPX.Type;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class CastPXProvider extends ExtensionElementProvider<CastPX> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7541a(xmlPullParser, i);
    }

    public CastPX m7541a(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && "cast".equals(xmlPullParser.getName())) {
                return new CastPX(Type.m7539a(attributeValue));
            }
            eventType = xmlPullParser.next();
        }
    }
}
