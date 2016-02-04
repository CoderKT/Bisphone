package org.jivesoftware.smackx.chatstates.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.xmlpull.v1.XmlPullParser;

public class ChatStateExtension implements ExtensionElement {
    public static final String NAMESPACE = "http://jabber.org/protocol/chatstates";
    private final ChatState state;

    public class Provider extends ExtensionElementProvider<ChatStateExtension> {
        public ChatStateExtension parse(XmlPullParser xmlPullParser, int i) {
            ChatState valueOf;
            try {
                valueOf = ChatState.valueOf(xmlPullParser.getName());
            } catch (Exception e) {
                valueOf = ChatState.active;
            }
            return new ChatStateExtension(valueOf);
        }
    }

    public ChatStateExtension(ChatState chatState) {
        this.state = chatState;
    }

    public String getElementName() {
        return this.state.name();
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public ChatState getChatState() {
        return this.state;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.closeEmptyElement();
        return xmlStringBuilder;
    }
}
