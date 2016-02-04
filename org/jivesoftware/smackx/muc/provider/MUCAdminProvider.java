package org.jivesoftware.smackx.muc.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.xmlpull.v1.XmlPullParser;

public class MUCAdminProvider extends IQProvider<MUCAdmin> {
    public MUCAdmin parse(XmlPullParser xmlPullParser, int i) {
        MUCAdmin mUCAdmin = new MUCAdmin();
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(Item.ELEMENT)) {
                    mUCAdmin.addItem(MUCParserUtils.parseItem(xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals(UserSearch.ELEMENT)) {
                obj = 1;
            }
        }
        return mUCAdmin;
    }
}
