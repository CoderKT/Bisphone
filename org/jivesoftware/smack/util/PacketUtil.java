package org.jivesoftware.smack.util;

import java.util.Collection;
import org.jivesoftware.smack.packet.ExtensionElement;

public class PacketUtil {
    @Deprecated
    public static <PE extends ExtensionElement> PE packetExtensionfromCollection(Collection<ExtensionElement> collection, String str, String str2) {
        return extensionElementFrom(collection, str, str2);
    }

    public static <PE extends ExtensionElement> PE extensionElementFrom(Collection<ExtensionElement> collection, String str, String str2) {
        for (ExtensionElement extensionElement : collection) {
            if ((str == null || extensionElement.getElementName().equals(str)) && extensionElement.getNamespace().equals(str2)) {
                return extensionElement;
            }
        }
        return null;
    }
}
