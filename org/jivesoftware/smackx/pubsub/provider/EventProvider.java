package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.EventElement;
import org.jivesoftware.smackx.pubsub.EventElementType;
import org.jivesoftware.smackx.pubsub.NodeExtension;

public class EventProvider extends EmbeddedExtensionProvider<EventElement> {
    protected EventElement createReturnExtension(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new EventElement(EventElementType.valueOf(((ExtensionElement) list.get(0)).getElementName()), (NodeExtension) list.get(0));
    }
}
