package app.xmpp.packet;

import app.xmpp.packet.CallSignalPX.Type;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class CallSignalPXProvider extends ExtensionElementProvider<CallSignalPX> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7527a(xmlPullParser, i);
    }

    public CallSignalPX m7527a(XmlPullParser xmlPullParser, int i) {
        Type type = Type.unavailable;
        String str = "";
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && "order".equals(xmlPullParser.getName())) {
                type = Type.m7524a(xmlPullParser.nextText());
            } else if (next == 2 && "callid".equals(xmlPullParser.getName())) {
                str = xmlPullParser.nextText();
            } else if (next == 3 && "call".equals(xmlPullParser.getName())) {
                return new CallSignalPX(type, str);
            }
        }
    }
}
