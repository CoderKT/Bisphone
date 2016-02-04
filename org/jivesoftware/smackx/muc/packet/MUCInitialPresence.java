package org.jivesoftware.smackx.muc.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppDateTime;

public class MUCInitialPresence implements ExtensionElement {
    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "http://jabber.org/protocol/muc";
    private History history;
    private String password;

    public class History implements NamedElement {
        public static final String ELEMENT = "history";
        private int maxChars;
        private int maxStanzas;
        private int seconds;
        private Date since;

        public History() {
            this.maxChars = -1;
            this.maxStanzas = -1;
            this.seconds = -1;
        }

        public int getMaxChars() {
            return this.maxChars;
        }

        public int getMaxStanzas() {
            return this.maxStanzas;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public Date getSince() {
            return this.since;
        }

        public void setMaxChars(int i) {
            this.maxChars = i;
        }

        public void setMaxStanzas(int i) {
            this.maxStanzas = i;
        }

        public void setSeconds(int i) {
            this.seconds = i;
        }

        public void setSince(Date date) {
            this.since = date;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.optIntAttribute("maxchars", getMaxChars());
            xmlStringBuilder.optIntAttribute("maxstanzas", getMaxStanzas());
            xmlStringBuilder.optIntAttribute("seconds", getSeconds());
            if (getSince() != null) {
                xmlStringBuilder.attribute("since", XmppDateTime.m13428a(getSince()));
            }
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement("password", getPassword());
        xmlStringBuilder.optElement(getHistory());
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    public History getHistory() {
        return this.history;
    }

    public String getPassword() {
        return this.password;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    @Deprecated
    public static MUCInitialPresence getFrom(Stanza stanza) {
        return from(stanza);
    }

    public static MUCInitialPresence from(Stanza stanza) {
        return (MUCInitialPresence) stanza.getExtension(ELEMENT, NAMESPACE);
    }
}
