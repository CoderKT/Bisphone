package app.xmpp.packet.group;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class GroupElement {
    private String f5121a;
    private String f5122b;
    private final List<Item> f5123c;

    public class Item {
        private String f5120a;

        public Item(String str) {
            m7582a(str);
        }

        public void m7582a(String str) {
            if (str == null) {
                throw new IllegalArgumentException("Jid can't be null!");
            }
            this.f5120a = str;
        }

        public XmlStringBuilder m7581a() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.halfOpenElement("i").attribute("j", this.f5120a).closeEmptyElement();
            return xmlStringBuilder;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            if (this.f5120a == null) {
                if (item.f5120a != null) {
                    return false;
                }
                return true;
            } else if (this.f5120a.equals(item.f5120a)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void m7584a(Item item) {
        synchronized (this.f5123c) {
            this.f5123c.add(item);
        }
    }

    public GroupElement(String str, String str2) {
        this.f5123c = new ArrayList();
        m7585a(str);
        m7586b(str2);
    }

    public void m7585a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id can't be null!");
        }
        this.f5121a = str;
    }

    public void m7586b(String str) {
        this.f5122b = str;
    }

    public XmlStringBuilder m7583a() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement("g").attribute("n", this.f5121a).optAttribute("o", this.f5122b);
        synchronized (this.f5123c) {
            if (this.f5123c.size() == 0) {
                xmlStringBuilder.closeEmptyElement();
            } else {
                xmlStringBuilder.rightAngelBracket();
                for (Item a : this.f5123c) {
                    xmlStringBuilder.append(a.m7581a());
                }
                xmlStringBuilder.closeElement("g");
            }
        }
        return xmlStringBuilder;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GroupElement groupElement = (GroupElement) obj;
        if (this.f5121a == null) {
            if (groupElement.f5121a != null) {
                return false;
            }
        } else if (!this.f5121a.equals(groupElement.f5121a)) {
            return false;
        }
        if (this.f5122b == null) {
            if (groupElement.f5121a != null) {
                return false;
            }
            return true;
        } else if (this.f5122b.equals(groupElement.f5122b)) {
            return true;
        } else {
            return false;
        }
    }
}
