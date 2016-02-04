package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Deprecated
public interface Packet extends TopLevelStreamElement {
    public static final String ITEM = "item";
    public static final String TEXT = "text";

    void addExtension(ExtensionElement extensionElement);

    void addExtensions(Collection<ExtensionElement> collection);

    XMPPError getError();

    ExtensionElement getExtension(String str);

    <PE extends ExtensionElement> PE getExtension(String str, String str2);

    List<ExtensionElement> getExtensions();

    Set<ExtensionElement> getExtensions(String str, String str2);

    String getFrom();

    String getLanguage();

    @Deprecated
    String getPacketID();

    String getStanzaId();

    String getTo();

    boolean hasExtension(String str);

    boolean hasExtension(String str, String str2);

    ExtensionElement removeExtension(String str, String str2);

    ExtensionElement removeExtension(ExtensionElement extensionElement);

    void setError(XMPPError xMPPError);

    void setFrom(String str);

    void setLanguage(String str);

    @Deprecated
    void setPacketID(String str);

    void setStanzaId(String str);

    void setTo(String str);

    String toString();
}
