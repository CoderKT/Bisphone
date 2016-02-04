package app.events.xmpp;

public abstract class AbstractSendPacketEvent {
    private PacketStatusListener f2514a;

    public interface PacketStatusListener {
        void m4974a(String str);

        void m4975b(String str);
    }

    public void m4977a(PacketStatusListener packetStatusListener) {
        this.f2514a = packetStatusListener;
    }

    public PacketStatusListener m4976a() {
        return this.f2514a;
    }
}
