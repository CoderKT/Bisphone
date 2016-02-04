package app.xmpp.packet;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlPullParser;

public class TimePXProvider extends ExtensionElementProvider<TimePX> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7529a(xmlPullParser, i);
    }

    public TimePX m7529a(XmlPullParser xmlPullParser, int i) {
        String nextText = xmlPullParser.nextText();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && Time.ELEMENT.equals(xmlPullParser.getName())) {
                return new TimePX(Long.valueOf(nextText).longValue());
            }
            eventType = xmlPullParser.next();
        }
    }
}
