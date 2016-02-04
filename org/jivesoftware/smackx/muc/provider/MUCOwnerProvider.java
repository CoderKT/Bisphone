package org.jivesoftware.smackx.muc.provider;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.xmlpull.v1.XmlPullParser;

public class MUCOwnerProvider extends IQProvider<MUCOwner> {
    public MUCOwner parse(XmlPullParser xmlPullParser, int i) {
        Stanza mUCOwner = new MUCOwner();
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(Item.ELEMENT)) {
                    mUCOwner.addItem(MUCParserUtils.parseItem(xmlPullParser));
                } else if (xmlPullParser.getName().equals(Destroy.ELEMENT)) {
                    mUCOwner.setDestroy(MUCParserUtils.parseDestroy(xmlPullParser));
                } else {
                    PacketParserUtils.addExtensionElement(mUCOwner, xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals(UserSearch.ELEMENT)) {
                obj = 1;
            }
        }
        return mUCOwner;
    }
}
