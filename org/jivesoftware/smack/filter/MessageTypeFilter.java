package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;

public class MessageTypeFilter extends FlexibleStanzaTypeFilter<Message> {
    public static final StanzaFilter CHAT;
    public static final StanzaFilter ERROR;
    public static final StanzaFilter GROUPCHAT;
    public static final StanzaFilter HEADLINE;
    public static final StanzaFilter NORMAL;
    public static final StanzaFilter NORMAL_OR_CHAT;
    public static final StanzaFilter NORMAL_OR_CHAT_OR_HEADLINE;
    private final Type type;

    static {
        NORMAL = new MessageTypeFilter(Type.normal);
        CHAT = new MessageTypeFilter(Type.chat);
        GROUPCHAT = new MessageTypeFilter(Type.groupchat);
        HEADLINE = new MessageTypeFilter(Type.headline);
        ERROR = new MessageTypeFilter(Type.error);
        NORMAL_OR_CHAT = new OrFilter(NORMAL, CHAT);
        NORMAL_OR_CHAT_OR_HEADLINE = new OrFilter(NORMAL_OR_CHAT, HEADLINE);
    }

    private MessageTypeFilter(Type type) {
        super(Message.class);
        this.type = type;
    }

    protected boolean acceptSpecific(Message message) {
        return message.getType() == this.type;
    }

    public String toString() {
        return getClass().getSimpleName() + ": type=" + this.type;
    }
}
