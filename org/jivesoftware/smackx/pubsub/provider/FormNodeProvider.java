package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.FormNode;
import org.jivesoftware.smackx.pubsub.FormNodeType;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class FormNodeProvider extends EmbeddedExtensionProvider<FormNode> {
    protected FormNode createReturnExtension(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new FormNode(FormNodeType.valueOfFromElementName(str, str2), (String) map.get("node"), new Form((DataForm) list.iterator().next()));
    }
}
