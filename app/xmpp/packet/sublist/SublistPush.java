package app.xmpp.packet.sublist;

import app.xmpp.packet.sublist.Sublist.Item;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class SublistPush implements ExtensionElement {
    private Item f5231a;

    public /* synthetic */ CharSequence toXML() {
        return m7721b();
    }

    public SublistPush(Item item) {
        this.f5231a = item;
    }

    public Item m7720a() {
        return this.f5231a;
    }

    public String getElementName() {
        return "push";
    }

    public String getNamespace() {
        return "http://bisphone.com/protocol/sublist";
    }

    public XmlStringBuilder m7721b() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement("push").xmlnsAttribute("http://bisphone.com/protocol/sublist").rightAngelBracket().openElement("list").append(this.f5231a.m7715c()).closeElement("list").closeElement("push");
        return xmlStringBuilder;
    }
}
