package app.events.group;

import org.jivesoftware.smack.packet.XMPPError.Condition;

public class LeaveGroupEvent {
    private String f2473a;
    private LeaveGroupListener f2474b;

    public interface LeaveGroupListener {
        void m4934a();

        void m4935a(Condition condition);
    }

    public LeaveGroupEvent(String str, LeaveGroupListener leaveGroupListener) {
        this.f2473a = str;
        this.f2474b = leaveGroupListener;
    }

    public String m4936a() {
        return this.f2473a;
    }

    public LeaveGroupListener m4937b() {
        return this.f2474b;
    }
}
