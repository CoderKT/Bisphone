package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smack.util.MultiMap;
import org.jivesoftware.smack.util.PacketUtil;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jxmpp.util.XmppStringUtils;

public abstract class Stanza implements Packet, TopLevelStreamElement {
    protected static final String DEFAULT_LANGUAGE;
    public static final String ITEM = "item";
    public static final String TEXT = "text";
    private XMPPError error;
    private String from;
    private String id;
    protected String language;
    private final MultiMap<String, ExtensionElement> packetExtensions;
    private String to;

    static {
        DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase(Locale.US);
    }

    protected Stanza() {
        this(StanzaIdUtil.newStanzaId());
    }

    protected Stanza(String str) {
        this.packetExtensions = new MultiMap();
        this.id = null;
        this.to = null;
        this.from = null;
        this.error = null;
        setStanzaId(str);
    }

    protected Stanza(Stanza stanza) {
        this.packetExtensions = new MultiMap();
        this.id = null;
        this.to = null;
        this.from = null;
        this.error = null;
        this.id = stanza.getStanzaId();
        this.to = stanza.getTo();
        this.from = stanza.getFrom();
        this.error = stanza.error;
        for (ExtensionElement addExtension : stanza.getExtensions()) {
            addExtension(addExtension);
        }
    }

    public String getStanzaId() {
        return this.id;
    }

    @Deprecated
    public String getPacketID() {
        return getStanzaId();
    }

    public void setStanzaId(String str) {
        if (str != null) {
            StringUtils.requireNotNullOrEmpty(str, "id must either be null or not the empty String");
        }
        this.id = str;
    }

    @Deprecated
    public void setPacketID(String str) {
        setStanzaId(str);
    }

    public boolean hasStanzaIdSet() {
        return this.id != null;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public XMPPError getError() {
        return this.error;
    }

    public void setError(XMPPError xMPPError) {
        this.error = xMPPError;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public List<ExtensionElement> getExtensions() {
        List<ExtensionElement> values;
        synchronized (this.packetExtensions) {
            values = this.packetExtensions.values();
        }
        return values;
    }

    public Set<ExtensionElement> getExtensions(String str, String str2) {
        StringUtils.requireNotNullOrEmpty(str, "elementName must not be null or empty");
        StringUtils.requireNotNullOrEmpty(str2, "namespace must not be null or empty");
        return this.packetExtensions.getAll(XmppStringUtils.m13445b(str, str2));
    }

    public ExtensionElement getExtension(String str) {
        return PacketUtil.extensionElementFrom(getExtensions(), null, str);
    }

    public <PE extends ExtensionElement> PE getExtension(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        String b = XmppStringUtils.m13445b(str, str2);
        synchronized (this.packetExtensions) {
            ExtensionElement extensionElement = (ExtensionElement) this.packetExtensions.getFirst(b);
        }
        if (extensionElement == null) {
            return null;
        }
        return extensionElement;
    }

    public void addExtension(ExtensionElement extensionElement) {
        if (extensionElement != null) {
            String b = XmppStringUtils.m13445b(extensionElement.getElementName(), extensionElement.getNamespace());
            synchronized (this.packetExtensions) {
                this.packetExtensions.put(b, extensionElement);
            }
        }
    }

    public ExtensionElement overrideExtension(ExtensionElement extensionElement) {
        if (extensionElement == null) {
            return null;
        }
        ExtensionElement removeExtension;
        synchronized (this.packetExtensions) {
            removeExtension = removeExtension(extensionElement);
            addExtension(extensionElement);
        }
        return removeExtension;
    }

    public void addExtensions(Collection<ExtensionElement> collection) {
        if (collection != null) {
            for (ExtensionElement addExtension : collection) {
                addExtension(addExtension);
            }
        }
    }

    public boolean hasExtension(String str, String str2) {
        if (str == null) {
            return hasExtension(str2);
        }
        boolean containsKey;
        String b = XmppStringUtils.m13445b(str, str2);
        synchronized (this.packetExtensions) {
            containsKey = this.packetExtensions.containsKey(b);
        }
        return containsKey;
    }

    public boolean hasExtension(String str) {
        synchronized (this.packetExtensions) {
            for (ExtensionElement namespace : this.packetExtensions.values()) {
                if (namespace.getNamespace().equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public ExtensionElement removeExtension(String str, String str2) {
        ExtensionElement extensionElement;
        String b = XmppStringUtils.m13445b(str, str2);
        synchronized (this.packetExtensions) {
            extensionElement = (ExtensionElement) this.packetExtensions.remove(b);
        }
        return extensionElement;
    }

    public ExtensionElement removeExtension(ExtensionElement extensionElement) {
        return removeExtension(extensionElement.getElementName(), extensionElement.getNamespace());
    }

    public String toString() {
        return toXML().toString();
    }

    protected final XmlStringBuilder getExtensionsXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        for (ExtensionElement toXML : getExtensions()) {
            xmlStringBuilder.append(toXML.toXML());
        }
        return xmlStringBuilder;
    }

    public static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    protected void addCommonAttributes(XmlStringBuilder xmlStringBuilder) {
        xmlStringBuilder.optAttribute(PrivacyItem.SUBSCRIPTION_TO, getTo());
        xmlStringBuilder.optAttribute(PrivacyItem.SUBSCRIPTION_FROM, getFrom());
        xmlStringBuilder.optAttribute("id", getStanzaId());
        xmlStringBuilder.xmllangAttribute(getLanguage());
    }

    protected void appendErrorIfExists(XmlStringBuilder xmlStringBuilder) {
        XMPPError error = getError();
        if (error != null) {
            xmlStringBuilder.append(error.toXML());
        }
    }
}
