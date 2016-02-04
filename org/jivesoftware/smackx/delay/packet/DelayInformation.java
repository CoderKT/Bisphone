package org.jivesoftware.smackx.delay.packet;

import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jxmpp.util.XmppDateTime;

public class DelayInformation implements ExtensionElement {
    public static final String ELEMENT = "delay";
    public static final String NAMESPACE = "urn:xmpp:delay";
    private final String from;
    private final String reason;
    private final Date stamp;

    public DelayInformation(Date date, String str, String str2) {
        this.stamp = date;
        this.from = str;
        this.reason = str2;
    }

    public DelayInformation(Date date) {
        this(date, null, null);
    }

    public String getFrom() {
        return this.from;
    }

    public Date getStamp() {
        return this.stamp;
    }

    public String getReason() {
        return this.reason;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.attribute("stamp", XmppDateTime.m13428a(this.stamp));
        xmlStringBuilder.optAttribute(PrivacyItem.SUBSCRIPTION_FROM, this.from);
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optAppend(this.reason);
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    @Deprecated
    public static DelayInformation getFrom(Stanza stanza) {
        return from(stanza);
    }

    public static DelayInformation from(Stanza stanza) {
        return (DelayInformation) stanza.getExtension(ELEMENT, NAMESPACE);
    }
}
