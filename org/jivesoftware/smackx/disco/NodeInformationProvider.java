package org.jivesoftware.smackx.disco;

import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;

public interface NodeInformationProvider {
    List<String> getNodeFeatures();

    List<Identity> getNodeIdentities();

    List<Item> getNodeItems();

    List<ExtensionElement> getNodePacketExtensions();
}
