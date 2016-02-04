package app.events.vcard;

import app.xmpp.packet.vcard.VCard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VCardLoadEvent {
    private final List<String> f2500a;
    private VCardLoadListener f2501b;

    public interface VCardLoadListener {
        void m4956a(List<VCard> list);
    }

    public VCardLoadEvent(VCardLoadListener vCardLoadListener) {
        this.f2500a = new ArrayList();
        this.f2501b = null;
        this.f2501b = vCardLoadListener;
    }

    public void m4958a(String str) {
        synchronized (this.f2500a) {
            this.f2500a.add(str);
        }
    }

    public int m4957a() {
        int size;
        synchronized (this.f2500a) {
            size = this.f2500a.size();
        }
        return size;
    }

    public Collection<String> m4959b() {
        Collection unmodifiableList;
        synchronized (this.f2500a) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.f2500a));
        }
        return unmodifiableList;
    }

    public VCardLoadListener m4960c() {
        return this.f2501b;
    }
}
