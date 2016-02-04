package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.xmlpull.v1.XmlPullParser;

public class GroupStatusProvider extends ExtensionElementProvider<GroupStatus> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7591a(xmlPullParser, i);
    }

    public GroupStatus m7591a(XmlPullParser xmlPullParser, int i) {
        Object obj = null;
        String str = "";
        String str2 = "";
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2 && "m".equals(xmlPullParser.getName())) {
                str = xmlPullParser.getAttributeValue("", "j");
                str2 = xmlPullParser.getAttributeValue("", XHTMLText.f8584A);
            } else if (next == 3 && "m".equals(xmlPullParser.getName())) {
                return new GroupStatus(str, str2);
            } else {
                if (next == 3 && Status.ELEMENT.equals(xmlPullParser.getName())) {
                    obj = 1;
                }
            }
        }
        return null;
    }
}
