package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.NodeExtension;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

public class SimpleNodeProvider extends EmbeddedExtensionProvider<NodeExtension> {
    protected NodeExtension createReturnExtension(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new NodeExtension(PubSubElementType.valueOfFromElemName(str, str2), (String) map.get("node"));
    }
}
