package org.jivesoftware.smackx.disco;

import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;

public abstract class AbstractNodeInformationProvider implements NodeInformationProvider {
    public List<Item> getNodeItems() {
        return null;
    }

    public List<String> getNodeFeatures() {
        return null;
    }

    public List<Identity> getNodeIdentities() {
        return null;
    }

    public List<ExtensionElement> getNodePacketExtensions() {
        return null;
    }
}
