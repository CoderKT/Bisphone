package app.xmpp.packet.sublist;

import app.xmpp.packet.sublist.Sublist.Item;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;

public class SublistProvider extends IQProvider<Sublist> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7719a(xmlPullParser, i);
    }

    public Sublist m7719a(XmlPullParser xmlPullParser, int i) {
        Sublist sublist = new Sublist();
        Object obj = null;
        String str = "";
        String str2 = "";
        String str3 = "";
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2 && "i".equals(xmlPullParser.getName())) {
                str = xmlPullParser.getAttributeValue("", "j");
                str2 = xmlPullParser.getAttributeValue("", "s");
                str3 = xmlPullParser.getAttributeValue("", "n");
            } else if (next == 3 && "i".equals(xmlPullParser.getName())) {
                sublist.m7718a(new Item(str, str2, str3));
            } else if (next == 3 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return sublist;
    }
}
