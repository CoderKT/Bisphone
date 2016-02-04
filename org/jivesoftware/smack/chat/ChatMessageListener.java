package org.jivesoftware.smack.chat;

import org.jivesoftware.smack.packet.Message;

public interface ChatMessageListener {
    void processMessage(Chat chat, Message message);
}
