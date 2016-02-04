package app.voip;

import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.OnTransportStateParam;
import org.pjsip.pjsua2.pjsip_transport_state;

public class SipEndpoint extends Endpoint {
    OnTransportStateChangeListener f4821a;

    public interface OnTransportStateChangeListener {
        void m7197a(pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state);
    }

    public void onTransportState(OnTransportStateParam onTransportStateParam) {
        if (this.f4821a != null) {
            this.f4821a.m7197a(onTransportStateParam.getState());
        }
    }

    public void m7238a(OnTransportStateChangeListener onTransportStateChangeListener) {
        this.f4821a = onTransportStateChangeListener;
    }
}
