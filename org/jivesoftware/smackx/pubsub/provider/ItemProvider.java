package org.jivesoftware.smackx.pubsub.provider;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.xmlpull.v1.XmlPullParser;

public class ItemProvider extends ExtensionElementProvider<Item> {
    public Item parse(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "node");
        if (xmlPullParser.next() == 3) {
            return new Item(attributeValue, attributeValue2);
        }
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        ExtensionElementProvider extensionProvider = ProviderManager.getExtensionProvider(name, namespace);
        if (extensionProvider == null) {
            return new PayloadItem(attributeValue, attributeValue2, new SimplePayload(name, namespace, PacketParserUtils.parseElement(xmlPullParser, true)));
        }
        return new PayloadItem(attributeValue, attributeValue2, (ExtensionElement) extensionProvider.parse(xmlPullParser));
    }
}
