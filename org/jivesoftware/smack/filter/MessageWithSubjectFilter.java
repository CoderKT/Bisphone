package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Message;

public class MessageWithSubjectFilter extends FlexibleStanzaTypeFilter<Message> {
    public static final StanzaFilter INSTANCE;

    static {
        INSTANCE = new MessageWithSubjectFilter();
    }

    private MessageWithSubjectFilter() {
        super(Message.class);
    }

    protected boolean acceptSpecific(Message message) {
        return message.getSubject() != null;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
