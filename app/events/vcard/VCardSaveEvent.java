package app.events.vcard;

import app.xmpp.packet.vcard.VCard;

public class VCardSaveEvent {
    private VCard f2503a;
    private VCardSaveListener f2504b;

    public interface VCardSaveListener {
        void m4962a();

        void m4963b();
    }

    public VCardSaveEvent(VCard vCard, VCardSaveListener vCardSaveListener) {
        this.f2504b = null;
        this.f2503a = vCard;
        this.f2504b = vCardSaveListener;
    }

    public VCard m4964a() {
        return this.f2503a;
    }

    public VCardSaveListener m4965b() {
        return this.f2504b;
    }
}
