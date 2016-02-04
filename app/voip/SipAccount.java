package app.voip;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnRegStartedParam;
import org.pjsip.pjsua2.OnRegStateParam;

public class SipAccount extends Account {
    private IncomingCallListener f4816a;
    private RegStateListener f4817b;
    private RegStartedListener f4818c;

    public interface IncomingCallListener {
        void m7199a(OnIncomingCallParam onIncomingCallParam);
    }

    public interface RegStateListener {
        void m7201a(OnRegStateParam onRegStateParam);
    }

    public interface RegStartedListener {
        void m7233a(OnRegStartedParam onRegStartedParam);
    }

    public void onIncomingCall(OnIncomingCallParam onIncomingCallParam) {
        if (this.f4816a != null) {
            this.f4816a.m7199a(onIncomingCallParam);
        }
    }

    public void onRegState(OnRegStateParam onRegStateParam) {
        if (this.f4817b != null) {
            this.f4817b.m7201a(onRegStateParam);
        }
    }

    public void onRegStarted(OnRegStartedParam onRegStartedParam) {
        if (this.f4818c != null) {
            this.f4818c.m7233a(onRegStartedParam);
        }
    }

    public void m7234a(IncomingCallListener incomingCallListener) {
        this.f4816a = incomingCallListener;
    }

    public void m7235a(RegStateListener regStateListener) {
        this.f4817b = regStateListener;
    }
}
