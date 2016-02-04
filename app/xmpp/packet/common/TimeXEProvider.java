package app.xmpp.packet.common;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlPullParser;

public class TimeXEProvider extends ExtensionElementProvider<TimeXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7580a(xmlPullParser, i);
    }

    public TimeXE m7580a(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", "v");
        while (true) {
            if (xmlPullParser.next() == 3 && Time.ELEMENT.equals(xmlPullParser.getName())) {
                return new TimeXE(attributeValue);
            }
        }
    }
}
