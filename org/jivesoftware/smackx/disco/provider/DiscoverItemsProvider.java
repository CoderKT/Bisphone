package org.jivesoftware.smackx.disco.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.xmlpull.v1.XmlPullParser;

public class DiscoverItemsProvider extends IQProvider<DiscoverItems> {
    public DiscoverItems parse(XmlPullParser xmlPullParser, int i) {
        DiscoverItems discoverItems = new DiscoverItems();
        Object obj = null;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        discoverItems.setNode(xmlPullParser.getAttributeValue("", "node"));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2 && Item.ELEMENT.equals(xmlPullParser.getName())) {
                str = xmlPullParser.getAttributeValue("", "jid");
                str2 = xmlPullParser.getAttributeValue("", "name");
                str4 = xmlPullParser.getAttributeValue("", "node");
                str3 = xmlPullParser.getAttributeValue("", Action.ATTRIBUTE_NAME);
            } else if (next == 3 && Item.ELEMENT.equals(xmlPullParser.getName())) {
                DiscoverItems.Item item = new DiscoverItems.Item(str);
                item.setName(str2);
                item.setNode(str4);
                item.setAction(str3);
                discoverItems.addItem(item);
            } else if (next == 3 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return discoverItems;
    }
}
