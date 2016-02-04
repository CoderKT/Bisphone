package app.events.xmpp;

import app.xmpp.JabberId;
import org.jivesoftware.smackx.chatstates.ChatState;

public class ChatStateEvent {
    private JabberId f2515a;
    private ChatState f2516b;

    public ChatStateEvent(JabberId jabberId, ChatState chatState) {
        this.f2515a = jabberId;
        this.f2516b = chatState;
    }

    public JabberId m4978a() {
        return this.f2515a;
    }

    public ChatState m4979b() {
        return this.f2516b;
    }
}
