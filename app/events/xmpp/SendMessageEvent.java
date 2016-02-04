package app.events.xmpp;

import org.jivesoftware.smack.packet.Message;

public class SendMessageEvent extends AbstractSendPacketEvent {
    private Message f2527a;

    public SendMessageEvent(Message message) {
        this.f2527a = message;
    }

    public Message m4993b() {
        return this.f2527a;
    }
}
