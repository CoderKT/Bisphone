package org.jivesoftware.smack.chat;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FlexibleStanzaTypeFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.ThreadFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.util.XmppStringUtils;

public class ChatManager extends Manager {
    private static final Map<XMPPConnection, ChatManager> INSTANCES;
    private static boolean defaultIsNormalInclude;
    private static MatchMode defaultMatchMode;
    private Map<String, Chat> baseJidChats;
    private Set<ChatManagerListener> chatManagerListeners;
    private Map<MessageListener, StanzaFilter> interceptors;
    private Map<String, Chat> jidChats;
    private MatchMode matchMode;
    private boolean normalIncluded;
    private final StanzaFilter packetFilter;
    private Map<String, Chat> threadChats;

    /* renamed from: org.jivesoftware.smack.chat.ChatManager.1 */
    class C09961 extends FlexibleStanzaTypeFilter<Message> {
        C09961() {
        }

        protected boolean acceptSpecific(Message message) {
            return ChatManager.this.normalIncluded && message.getType() == Type.normal;
        }
    }

    /* renamed from: org.jivesoftware.smack.chat.ChatManager.2 */
    class C09972 implements StanzaListener {
        C09972() {
        }

        public void processPacket(Stanza stanza) {
            Chat access$100;
            Message message = (Message) stanza;
            if (message.getThread() == null) {
                access$100 = ChatManager.this.getUserChat(message.getFrom());
            } else {
                access$100 = ChatManager.this.getThreadChat(message.getThread());
            }
            if (access$100 == null) {
                access$100 = ChatManager.this.createChat(message);
            }
            if (access$100 != null) {
                ChatManager.this.deliverMessage(access$100, message);
            }
        }
    }

    public enum MatchMode {
        NONE,
        SUPPLIED_JID,
        BARE_JID
    }

    static {
        INSTANCES = new WeakHashMap();
        defaultIsNormalInclude = true;
        defaultMatchMode = MatchMode.BARE_JID;
    }

    public static synchronized ChatManager getInstanceFor(XMPPConnection xMPPConnection) {
        ChatManager chatManager;
        synchronized (ChatManager.class) {
            chatManager = (ChatManager) INSTANCES.get(xMPPConnection);
            if (chatManager == null) {
                chatManager = new ChatManager(xMPPConnection);
            }
        }
        return chatManager;
    }

    private ChatManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.packetFilter = new OrFilter(MessageTypeFilter.CHAT, new C09961());
        this.normalIncluded = defaultIsNormalInclude;
        this.matchMode = defaultMatchMode;
        this.threadChats = new ConcurrentHashMap();
        this.jidChats = new ConcurrentHashMap();
        this.baseJidChats = new ConcurrentHashMap();
        this.chatManagerListeners = new CopyOnWriteArraySet();
        this.interceptors = new WeakHashMap();
        xMPPConnection.addSyncStanzaListener(new C09972(), this.packetFilter);
        INSTANCES.put(xMPPConnection, this);
    }

    public boolean isNormalIncluded() {
        return this.normalIncluded;
    }

    public void setNormalIncluded(boolean z) {
        this.normalIncluded = z;
    }

    public MatchMode getMatchMode() {
        return this.matchMode;
    }

    public void setMatchMode(MatchMode matchMode) {
        this.matchMode = matchMode;
    }

    public Chat createChat(String str) {
        return createChat(str, null);
    }

    public Chat createChat(String str, ChatMessageListener chatMessageListener) {
        return createChat(str, null, chatMessageListener);
    }

    public Chat createChat(String str, String str2, ChatMessageListener chatMessageListener) {
        if (str2 == null) {
            str2 = nextID();
        }
        if (((Chat) this.threadChats.get(str2)) != null) {
            throw new IllegalArgumentException("ThreadID is already used");
        }
        Chat createChat = createChat(str, str2, true);
        createChat.addMessageListener(chatMessageListener);
        return createChat;
    }

    private Chat createChat(String str, String str2, boolean z) {
        Chat chat = new Chat(this, str, str2);
        this.threadChats.put(str2, chat);
        this.jidChats.put(str, chat);
        this.baseJidChats.put(XmppStringUtils.m13447d(str), chat);
        for (ChatManagerListener chatCreated : this.chatManagerListeners) {
            chatCreated.chatCreated(chat, z);
        }
        return chat;
    }

    void closeChat(Chat chat) {
        this.threadChats.remove(chat.getThreadID());
        String participant = chat.getParticipant();
        this.jidChats.remove(participant);
        this.baseJidChats.remove(XmppStringUtils.m13447d(participant));
    }

    private Chat createChat(Message message) {
        String from = message.getFrom();
        if (from == null) {
            return null;
        }
        String thread = message.getThread();
        if (thread == null) {
            thread = nextID();
        }
        return createChat(from, thread, false);
    }

    private Chat getUserChat(String str) {
        if (this.matchMode == MatchMode.NONE || str == null) {
            return null;
        }
        Chat chat = (Chat) this.jidChats.get(str);
        if (chat == null && this.matchMode == MatchMode.BARE_JID) {
            return (Chat) this.baseJidChats.get(XmppStringUtils.m13447d(str));
        }
        return chat;
    }

    public Chat getThreadChat(String str) {
        return (Chat) this.threadChats.get(str);
    }

    public void addChatListener(ChatManagerListener chatManagerListener) {
        this.chatManagerListeners.add(chatManagerListener);
    }

    public void removeChatListener(ChatManagerListener chatManagerListener) {
        this.chatManagerListeners.remove(chatManagerListener);
    }

    public Set<ChatManagerListener> getChatListeners() {
        return Collections.unmodifiableSet(this.chatManagerListeners);
    }

    private void deliverMessage(Chat chat, Message message) {
        chat.deliver(message);
    }

    void sendMessage(Chat chat, Message message) {
        for (Entry entry : this.interceptors.entrySet()) {
            StanzaFilter stanzaFilter = (StanzaFilter) entry.getValue();
            if (stanzaFilter != null && stanzaFilter.accept(message)) {
                ((MessageListener) entry.getKey()).processMessage(message);
            }
        }
        if (message.getFrom() == null) {
            message.setFrom(connection().getUser());
        }
        connection().sendStanza(message);
    }

    PacketCollector createPacketCollector(Chat chat) {
        return connection().createPacketCollector(new AndFilter(new ThreadFilter(chat.getThreadID()), FromMatchesFilter.create(chat.getParticipant())));
    }

    public void addOutgoingMessageInterceptor(MessageListener messageListener) {
        addOutgoingMessageInterceptor(messageListener, null);
    }

    public void addOutgoingMessageInterceptor(MessageListener messageListener, StanzaFilter stanzaFilter) {
        if (messageListener != null) {
            this.interceptors.put(messageListener, stanzaFilter);
        }
    }

    private static String nextID() {
        return UUID.randomUUID().toString();
    }

    public static void setDefaultMatchMode(MatchMode matchMode) {
        defaultMatchMode = matchMode;
    }

    public static void setDefaultIsNormalIncluded(boolean z) {
        defaultIsNormalInclude = z;
    }
}
