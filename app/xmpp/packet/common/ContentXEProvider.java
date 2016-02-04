package app.xmpp.packet.common;

import app.xmpp.packet.common.ContentXE.Type;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class ContentXEProvider extends ExtensionElementProvider<ContentXE> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7574a(xmlPullParser, i);
    }

    public ContentXE m7574a(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue("", "t");
        while (true) {
            if (xmlPullParser.next() == 3 && "content".equals(xmlPullParser.getName())) {
                return new ContentXE(Type.m7572a(attributeValue));
            }
        }
    }
}
