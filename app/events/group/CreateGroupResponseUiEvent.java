package app.events.group;

import app.xmpp.packet.common.ErrorXE.ErrorType;

public class CreateGroupResponseUiEvent {
    private String f2426a;
    private ErrorType f2427b;
    private boolean f2428c;

    public CreateGroupResponseUiEvent(String str, boolean z) {
        this.f2426a = str;
        this.f2428c = z;
    }

    public CreateGroupResponseUiEvent(ErrorType errorType) {
        this.f2427b = errorType;
        this.f2428c = false;
    }

    public String m4866a() {
        return this.f2426a;
    }

    public ErrorType m4867b() {
        return this.f2427b;
    }

    public boolean m4868c() {
        return this.f2428c;
    }
}
