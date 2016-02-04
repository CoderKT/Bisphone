package app.events.xmpp;

import app.xmpp.JabberId;

public class LastActivityEvent {
    private JabberId f2517a;
    private LastActivityListener f2518b;

    public interface LastActivityListener {
        void m4980a();

        void m4981a(long j);
    }

    public LastActivityEvent(JabberId jabberId) {
        this.f2517a = jabberId;
    }

    public JabberId m4982a() {
        return this.f2517a;
    }

    public LastActivityListener m4984b() {
        return this.f2518b;
    }

    public void m4983a(LastActivityListener lastActivityListener) {
        this.f2518b = lastActivityListener;
    }
}
