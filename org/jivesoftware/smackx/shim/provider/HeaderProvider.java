package org.jivesoftware.smackx.shim.provider;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.shim.packet.Header;
import org.xmlpull.v1.XmlPullParser;

public class HeaderProvider extends ExtensionElementProvider<Header> {
    public Header parse(XmlPullParser xmlPullParser, int i) {
        String str = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        xmlPullParser.next();
        if (xmlPullParser.getEventType() == 4) {
            str = xmlPullParser.getText();
        }
        while (xmlPullParser.getEventType() != 3) {
            xmlPullParser.next();
        }
        return new Header(attributeValue, str);
    }
}
