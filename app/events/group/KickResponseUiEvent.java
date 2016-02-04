package app.events.group;

import app.xmpp.packet.common.ErrorXE.ErrorType;

public class KickResponseUiEvent {
    private String f2469a;
    private String f2470b;
    private ErrorType f2471c;
    private boolean f2472d;

    public KickResponseUiEvent(String str, String str2) {
        this.f2469a = str;
        this.f2470b = str2;
        this.f2472d = false;
    }

    public KickResponseUiEvent(ErrorType errorType) {
        this.f2471c = errorType;
        this.f2472d = true;
    }

    public String m4930a() {
        return this.f2469a;
    }

    public String m4931b() {
        return this.f2470b;
    }

    public ErrorType m4932c() {
        return this.f2471c;
    }

    public boolean m4933d() {
        return this.f2472d;
    }
}
