package app.voip;

import app.Main;
import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.OnCallMediaStateParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.pjsip_inv_state;

public class SipCall extends Call {
    private CallStateListener f4819a;
    private CallMediaStateListener f4820b;

    public interface CallStateListener {
        void m7193a(OnCallStateParam onCallStateParam);
    }

    public interface CallMediaStateListener {
        void m7195a(OnCallMediaStateParam onCallMediaStateParam);
    }

    public SipCall(Account account, int i) {
        super(account, i);
    }

    public void onCallState(OnCallStateParam onCallStateParam) {
        if (this.f4819a != null) {
            this.f4819a.m7193a(onCallStateParam);
            try {
                if (getInfo().getState() == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                    delete();
                }
            } catch (Throwable e) {
                Main.f1926a.m5682c(e);
            }
        }
    }

    public void onCallMediaState(OnCallMediaStateParam onCallMediaStateParam) {
        if (this.f4820b != null) {
            this.f4820b.m7195a(onCallMediaStateParam);
        }
    }

    public void m7237a(CallStateListener callStateListener) {
        this.f4819a = callStateListener;
    }

    public void m7236a(CallMediaStateListener callMediaStateListener) {
        this.f4820b = callMediaStateListener;
    }
}
