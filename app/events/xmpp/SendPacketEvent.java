package app.events.xmpp;

import org.jivesoftware.smack.packet.Stanza;

public class SendPacketEvent extends AbstractSendPacketEvent {
    private Stanza f2528a;

    public SendPacketEvent(Stanza stanza) {
        this.f2528a = stanza;
    }

    public Stanza m4994b() {
        return this.f2528a;
    }
}
