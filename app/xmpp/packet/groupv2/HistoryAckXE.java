package app.xmpp.packet.groupv2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HistoryAckXE extends AbstractXE {
    private int f5160a;
    private String f5161b;
    private final List<Item> f5162c;

    public class Item {
        private String f5159a;

        public Item(String str) {
            this.f5159a = str;
        }

        public String m7646a() {
            return this.f5159a;
        }

        public CharSequence m7647b() {
            CharSequence stringBuilder = new StringBuilder();
            stringBuilder.append("<").append("m").append(" ").append("id").append("='").append(this.f5159a).append("'/>");
            return stringBuilder;
        }
    }

    public HistoryAckXE(int i) {
        this.f5162c = new ArrayList();
        this.f5160a = i;
    }

    public boolean m7650a() {
        return this.f5161b != null && this.f5161b.equals("true");
    }

    public void m7649a(String str) {
        this.f5161b = str;
    }

    public void m7648a(Item item) {
        synchronized (this.f5162c) {
            this.f5162c.add(item);
        }
    }

    public Collection<Item> m7651b() {
        Collection unmodifiableList;
        synchronized (this.f5162c) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.f5162c));
        }
        return unmodifiableList;
    }

    public String getElementName() {
        return "history-ack";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' ").append("window").append("='").append(this.f5160a).append("'");
        if (this.f5161b != null) {
            stringBuilder.append("last").append("='").append(this.f5161b).append("'");
        }
        stringBuilder.append(">");
        synchronized (this.f5162c) {
            if (this.f5162c.size() != 0) {
                for (Item b : this.f5162c) {
                    stringBuilder.append(b.m7647b());
                }
            }
        }
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder;
    }
}
