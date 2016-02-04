package app.xmpp.packet.groupv2;

import app.xmpp.packet.groupv2.HistoryAckXE.Item;
import java.util.ArrayList;
import java.util.List;

public class SelectiveHistoryXE extends AbstractXE {
    private final List<Item> f5190a;

    public SelectiveHistoryXE() {
        this.f5190a = new ArrayList();
    }

    public void m7674a(Item item) {
        synchronized (this.f5190a) {
            this.f5190a.add(item);
        }
    }

    public String getElementName() {
        return "selective-history";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>");
        synchronized (this.f5190a) {
            if (this.f5190a.size() != 0) {
                for (Item b : this.f5190a) {
                    stringBuilder.append(b.m7647b());
                }
            }
        }
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder;
    }
}
