package app.xmpp.packet.groupv2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractXEWithMemberList extends AbstractXE {
    private final List<MemberElement> f5129a;

    public AbstractXEWithMemberList() {
        this.f5129a = new ArrayList();
    }

    public void m7596a(MemberElement memberElement) {
        synchronized (this.f5129a) {
            this.f5129a.add(memberElement);
        }
    }

    public Collection<MemberElement> m7595a() {
        Collection unmodifiableList;
        synchronized (this.f5129a) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.f5129a));
        }
        return unmodifiableList;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>");
        synchronized (this.f5129a) {
            if (this.f5129a.size() != 0) {
                for (MemberElement c : this.f5129a) {
                    stringBuilder.append(c.m7661c());
                }
            }
        }
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder;
    }
}
