package app.events.vcard;

import app.xmpp.VCardInsertUpdateEntity;
import java.util.HashMap;

public class VCardRefreshEvent {
    private HashMap<String, VCardInsertUpdateEntity> f2502a;

    public VCardRefreshEvent(HashMap<String, VCardInsertUpdateEntity> hashMap) {
        this.f2502a = hashMap;
    }

    public HashMap<String, VCardInsertUpdateEntity> m4961a() {
        return this.f2502a;
    }
}
