package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.offline.packet.OfflineMessageRequest;
import org.xmlpull.v1.XmlPullParser;

public class OfflineXEProvider extends ExtensionElementProvider<OfflineXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7578a(xmlPullParser, i);
    }

    public OfflineXE m7578a(XmlPullParser xmlPullParser, int i) {
        while (true) {
            if (xmlPullParser.next() == 3 && OfflineMessageRequest.ELEMENT.equals(xmlPullParser.getName())) {
                return new OfflineXE();
            }
        }
    }
}
