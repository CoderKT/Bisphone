package org.jivesoftware.smack.chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.util.StringUtils;

public class Chat {
    private ChatManager chatManager;
    private final Set<ChatMessageListener> listeners;
    private String participant;
    private String threadID;

    Chat(ChatManager chatManager, String str, String str2) {
        this.listeners = new CopyOnWriteArraySet();
        if (StringUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Thread ID must not be null");
        }
        this.chatManager = chatManager;
        this.participant = str;
        this.threadID = str2;
    }

    public String getThreadID() {
        return this.threadID;
    }

    public String getParticipant() {
        return this.participant;
    }

    public void sendMessage(String str) {
        Message message = new Message();
        message.setBody(str);
        sendMessage(message);
    }

    public void sendMessage(Message message) {
        message.setTo(this.participant);
        message.setType(Type.chat);
        message.setThread(this.threadID);
        this.chatManager.sendMessage(this, message);
    }

    public void addMessageListener(ChatMessageListener chatMessageListener) {
        if (chatMessageListener != null) {
            this.listeners.add(chatMessageListener);
        }
    }

    public void removeMessageListener(ChatMessageListener chatMessageListener) {
        this.listeners.remove(chatMessageListener);
    }

    public void close() {
        this.chatManager.closeChat(this);
        this.listeners.clear();
    }

    public Set<ChatMessageListener> getListeners() {
        return Collections.unmodifiableSet(this.listeners);
    }

    public PacketCollector createCollector() {
        return this.chatManager.createPacketCollector(this);
    }

    void deliver(Message message) {
        message.setThread(this.threadID);
        for (ChatMessageListener processMessage : this.listeners) {
            processMessage.processMessage(this, message);
        }
    }

    public String toString() {
        return "Chat [(participant=" + this.participant + "), (thread=" + this.threadID + ")]";
    }

    public int hashCode() {
        return ((this.threadID.hashCode() + 31) * 31) + this.participant.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Chat) && this.threadID.equals(((Chat) obj).getThreadID()) && this.participant.equals(((Chat) obj).getParticipant());
    }
}
