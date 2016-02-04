package org.jivesoftware.smackx.xhtmlim.provider;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.xhtmlim.packet.XHTMLExtension;
import org.xmlpull.v1.XmlPullParser;

public class XHTMLExtensionProvider extends ExtensionElementProvider<XHTMLExtension> {
    public XHTMLExtension parse(XmlPullParser xmlPullParser, int i) {
        XHTMLExtension xHTMLExtension = new XHTMLExtension();
        while (true) {
            int eventType = xmlPullParser.getEventType();
            String name = xmlPullParser.getName();
            if (eventType == 2) {
                if (name.equals(Message.BODY)) {
                    xHTMLExtension.addBody(PacketParserUtils.parseElement(xmlPullParser));
                }
            } else if (eventType == 3 && xmlPullParser.getDepth() == i) {
                return xHTMLExtension;
            }
            xmlPullParser.next();
        }
    }
}
