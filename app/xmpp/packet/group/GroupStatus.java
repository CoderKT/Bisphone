package app.xmpp.packet.group;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;

public class GroupStatus implements ExtensionElement {
    private String f5125a;
    private String f5126b;

    public GroupStatus(String str, String str2) {
        m7589a(str);
        m7590b(str2);
    }

    public void m7589a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("jid can't be null");
        }
        this.f5125a = str;
    }

    public void m7590b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("action can't be null");
        } else if ("j".equals(str) || "l".equals(str)) {
            this.f5126b = str;
        } else {
            throw new IllegalArgumentException("Invalid State!");
        }
    }

    public String getElementName() {
        return Status.ELEMENT;
    }

    public String getNamespace() {
        return "http://bisphone.com/protocol/group#set_member_status";
    }

    public CharSequence toXML() {
        CharSequence xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(Status.ELEMENT).xmlnsAttribute("http://bisphone.com/protocol/group#set_member_status").rightAngelBracket().openElement("m").attribute("j", this.f5125a).attribute(XHTMLText.f8584A, this.f5126b).closeEmptyElement().closeElement(Status.ELEMENT);
        return xmlStringBuilder;
    }
}
