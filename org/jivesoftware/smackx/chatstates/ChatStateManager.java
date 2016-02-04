package org.jivesoftware.smackx.chatstates;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class ChatStateManager extends Manager {
    private static final Map<XMPPConnection, ChatStateManager> INSTANCES;
    public static final String NAMESPACE = "http://jabber.org/protocol/chatstates";
    private static final StanzaFilter filter;
    private final ChatManager chatManager;
    private final Map<Chat, ChatState> chatStates;
    private final IncomingMessageInterceptor incomingInterceptor;
    private final OutgoingMessageInterceptor outgoingInterceptor;

    class IncomingMessageInterceptor implements ChatManagerListener, ChatMessageListener {
        private IncomingMessageInterceptor() {
        }

        public void chatCreated(Chat chat, boolean z) {
            chat.addMessageListener(this);
        }

        public void processMessage(Chat chat, Message message) {
            ExtensionElement extension = message.getExtension(ChatStateManager.NAMESPACE);
            if (extension != null) {
                try {
                    ChatStateManager.this.fireNewChatState(chat, ChatState.valueOf(extension.getElementName()));
                } catch (Exception e) {
                }
            }
        }
    }

    class OutgoingMessageInterceptor implements MessageListener {
        private OutgoingMessageInterceptor() {
        }

        public void processMessage(Message message) {
            Chat threadChat = ChatStateManager.this.chatManager.getThreadChat(message.getThread());
            if (threadChat != null && ChatStateManager.this.updateChatState(threadChat, ChatState.active)) {
                message.addExtension(new ChatStateExtension(ChatState.active));
            }
        }
    }

    static {
        INSTANCES = new WeakHashMap();
        filter = new NotFilter(new StanzaExtensionFilter(NAMESPACE));
    }

    public static synchronized ChatStateManager getInstance(XMPPConnection xMPPConnection) {
        ChatStateManager chatStateManager;
        synchronized (ChatStateManager.class) {
            chatStateManager = (ChatStateManager) INSTANCES.get(xMPPConnection);
            if (chatStateManager == null) {
                chatStateManager = new ChatStateManager(xMPPConnection);
            }
        }
        return chatStateManager;
    }

    private ChatStateManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.outgoingInterceptor = new OutgoingMessageInterceptor();
        this.incomingInterceptor = new IncomingMessageInterceptor();
        this.chatStates = new WeakHashMap();
        this.chatManager = ChatManager.getInstanceFor(xMPPConnection);
        this.chatManager.addOutgoingMessageInterceptor(this.outgoingInterceptor, filter);
        this.chatManager.addChatListener(this.incomingInterceptor);
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(NAMESPACE);
        INSTANCES.put(xMPPConnection, this);
    }

    public void setCurrentState(ChatState chatState, Chat chat) {
        if (chat == null || chatState == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        } else if (updateChatState(chat, chatState)) {
            Message message = new Message();
            message.addExtension(new ChatStateExtension(chatState));
            chat.sendMessage(message);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return connection().equals(((ChatStateManager) obj).connection());
    }

    public int hashCode() {
        return connection().hashCode();
    }

    private synchronized boolean updateChatState(Chat chat, ChatState chatState) {
        boolean z;
        if (((ChatState) this.chatStates.get(chat)) != chatState) {
            this.chatStates.put(chat, chatState);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void fireNewChatState(Chat chat, ChatState chatState) {
        for (ChatMessageListener chatMessageListener : chat.getListeners()) {
            if (chatMessageListener instanceof ChatStateListener) {
                ((ChatStateListener) chatMessageListener).stateChanged(chat, chatState);
            }
        }
    }
}
