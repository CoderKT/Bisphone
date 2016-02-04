package org.jivesoftware.smack.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;

public abstract class EmbeddedExtensionProvider<PE extends ExtensionElement> extends ExtensionElementProvider<PE> {
    protected abstract PE createReturnExtension(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list);

    public final PE parse(XmlPullParser xmlPullParser, int i) {
        String namespace = xmlPullParser.getNamespace();
        String name = xmlPullParser.getName();
        int attributeCount = xmlPullParser.getAttributeCount();
        Map hashMap = new HashMap(attributeCount);
        for (int i2 = 0; i2 < attributeCount; i2++) {
            hashMap.put(xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2));
        }
        Collection arrayList = new ArrayList();
        while (true) {
            attributeCount = xmlPullParser.next();
            if (attributeCount == 2) {
                PacketParserUtils.addExtensionElement(arrayList, xmlPullParser);
            }
            if (attributeCount == 3 && xmlPullParser.getDepth() == i) {
                return createReturnExtension(name, namespace, hashMap, arrayList);
            }
        }
    }
}
