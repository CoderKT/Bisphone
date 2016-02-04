package app.xmpp.packet.group;

import app.xmpp.packet.group.GroupElement.Item;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;

public class GroupIQProvider extends IQProvider<GroupIQ> {
    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) {
        return m7588a(xmlPullParser, i);
    }

    public GroupIQ m7588a(XmlPullParser xmlPullParser, int i) {
        GroupIQ groupIQ = new GroupIQ("http://bisphone.com/protocol/group");
        GroupElement groupElement = new GroupElement("", null);
        Object obj = null;
        String str = "";
        String str2 = "";
        String str3 = "";
        if ("http://bisphone.com/protocol/group#accept_invitation".equals(xmlPullParser.getNamespace())) {
            groupIQ = new GroupIQAccept();
        } else if ("http://bisphone.com/protocol/group#create_group".equals(xmlPullParser.getNamespace())) {
            groupIQ = new GroupIQCreate();
        } else if ("http://bisphone.com/protocol/group#invite_member".equals(xmlPullParser.getNamespace())) {
            groupIQ = new GroupIQInvite();
        } else if ("http://bisphone.com/protocol/group#leave_group".equals(xmlPullParser.getNamespace())) {
            groupIQ = new GroupIQLeave();
        } else if ("http://bisphone.com/protocol/group#get_members".equals(xmlPullParser.getNamespace())) {
            groupIQ = new GroupIQGetMembers();
        }
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2 && "g".equals(xmlPullParser.getName())) {
                str = xmlPullParser.getAttributeValue("", "n");
                str2 = xmlPullParser.getAttributeValue("", "o");
            } else if (next == 2 && "i".equals(xmlPullParser.getName())) {
                str3 = xmlPullParser.getAttributeValue("", "j");
            } else if (next == 3 && "i".equals(xmlPullParser.getName())) {
                groupElement.m7584a(new Item(str3));
            } else if (next == 3 && "g".equals(xmlPullParser.getName())) {
                groupElement.m7585a(str);
                groupElement.m7586b(str2);
                groupIQ.m7587a(groupElement);
            } else if (next == 3 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return groupIQ;
    }
}
