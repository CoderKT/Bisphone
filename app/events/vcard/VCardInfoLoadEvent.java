package app.events.vcard;

import app.xmpp.packet.VcardInfoEntity;
import java.util.HashMap;

public class VCardInfoLoadEvent {
    private HashMap<String, VcardInfoEntity> f2499a;

    public VCardInfoLoadEvent(HashMap<String, VcardInfoEntity> hashMap) {
        this.f2499a = hashMap;
    }

    public HashMap<String, VcardInfoEntity> m4955a() {
        return this.f2499a;
    }
}
