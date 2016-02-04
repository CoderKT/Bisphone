package app.xmpp.packet.sublist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;

public class Sublist extends IQ {
    private final List<Item> f5230a;

    public class Item {
        private String f5227a;
        private String f5228b;
        private String f5229c;

        public Item(String str, String str2, String str3) {
            m7712a(str);
            m7714b(str2);
            m7716c(str3);
        }

        public String m7711a() {
            return this.f5227a;
        }

        public String m7713b() {
            return this.f5228b;
        }

        public void m7712a(String str) {
            if (str == null) {
                throw new IllegalArgumentException("Jid can't be null!");
            }
            this.f5227a = str;
        }

        public void m7714b(String str) {
            if (str == null) {
                throw new IllegalArgumentException("State can't be null!");
            } else if (XHTMLText.f8584A.equals(str) || "m".equals(str) || AckRequest.ELEMENT.equals(str)) {
                this.f5228b = str;
            } else {
                throw new IllegalArgumentException("Invalid State!");
            }
        }

        public void m7716c(String str) {
            this.f5229c = str;
        }

        public CharSequence m7715c() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<").append("i").append(" ").append("j").append("='").append(this.f5227a).append("'").append(" ").append("s").append("='").append(this.f5228b).append("'");
            if (this.f5229c != null) {
                stringBuilder.append(" ").append("n").append("='").append(this.f5229c).append("'");
            }
            stringBuilder.append("/>");
            return stringBuilder.toString();
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
            if (this.f5227a == null) {
                if (item.f5227a != null) {
                    return false;
                }
            } else if (!this.f5227a.equals(item.f5227a)) {
                return false;
            }
            if (this.f5228b == null) {
                if (item.f5228b != null) {
                    return false;
                }
            } else if (!this.f5228b.equals(item.f5228b)) {
                return false;
            }
            if (this.f5229c == null) {
                if (item.f5229c != null) {
                    return false;
                }
                return true;
            } else if (this.f5229c.equals(item.f5229c)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Sublist() {
        super(UserSearch.ELEMENT, "http://bisphone.com/protocol/sublist");
        this.f5230a = new ArrayList();
    }

    public void m7718a(Item item) {
        synchronized (this.f5230a) {
            this.f5230a.add(item);
        }
    }

    public Collection<Item> m7717a() {
        Collection unmodifiableList;
        synchronized (this.f5230a) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.f5230a));
        }
        return unmodifiableList;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        iQChildElementXmlStringBuilder.openElement("list");
        synchronized (this.f5230a) {
            for (Item c : this.f5230a) {
                iQChildElementXmlStringBuilder.append(c.m7715c());
            }
        }
        iQChildElementXmlStringBuilder.closeElement("list");
        return iQChildElementXmlStringBuilder;
    }
}
