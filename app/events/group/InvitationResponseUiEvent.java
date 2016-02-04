package app.events.group;

import app.xmpp.packet.common.ErrorXE.ErrorType;

public class InvitationResponseUiEvent {
    private String f2459a;
    private String f2460b;
    private ErrorType f2461c;
    private boolean f2462d;

    public InvitationResponseUiEvent(String str, String str2) {
        this.f2459a = str;
        this.f2460b = str2;
        this.f2462d = false;
    }

    public InvitationResponseUiEvent(ErrorType errorType) {
        this.f2461c = errorType;
        this.f2462d = true;
    }

    public String m4914a() {
        return this.f2459a;
    }

    public String m4915b() {
        return this.f2460b;
    }

    public ErrorType m4916c() {
        return this.f2461c;
    }

    public boolean m4917d() {
        return this.f2462d;
    }
}
