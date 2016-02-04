package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;

public class MessageWithBodiesFilter extends FlexibleStanzaTypeFilter<Message> {
    public static final StanzaFilter INSTANCE;

    static {
        INSTANCE = new MessageWithBodiesFilter();
    }

    private MessageWithBodiesFilter() {
        super(Message.class);
    }

    protected boolean acceptSpecific(Message message) {
        return !message.getBodies().isEmpty();
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
