package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class ExpirationXEProvider extends ExtensionElementProvider<ExpirationXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7577a(xmlPullParser, i);
    }

    public ExpirationXE m7577a(XmlPullParser xmlPullParser, int i) {
        long parseLong = Long.parseLong(xmlPullParser.getAttributeValue("", "v"));
        while (true) {
            if (xmlPullParser.next() == 3 && "expiration".equals(xmlPullParser.getName())) {
                return new ExpirationXE(parseLong);
            }
        }
    }
}
