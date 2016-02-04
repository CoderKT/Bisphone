package app.xmpp.packet.groupv2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractXEWithGroupList extends AbstractXE {
    private final List<MigrationGroupElement> f5128a;

    public AbstractXEWithGroupList() {
        this.f5128a = new ArrayList();
    }

    public void m7594a(MigrationGroupElement migrationGroupElement) {
        synchronized (this.f5128a) {
            this.f5128a.add(migrationGroupElement);
        }
    }

    public Collection<MigrationGroupElement> m7593a() {
        Collection unmodifiableList;
        synchronized (this.f5128a) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.f5128a));
        }
        return unmodifiableList;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>");
        synchronized (this.f5128a) {
            if (this.f5128a.size() != 0) {
                for (GroupElement g : this.f5128a) {
                    stringBuilder.append(g.m7618g());
                }
            }
        }
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder;
    }
}
