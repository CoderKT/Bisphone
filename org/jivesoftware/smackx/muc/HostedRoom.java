package org.jivesoftware.smackx.muc;

import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;

public class HostedRoom {
    private final String jid;
    private final String name;

    public HostedRoom(Item item) {
        this.jid = item.getEntityID();
        this.name = item.getName();
    }

    public String getJid() {
        return this.jid;
    }

    public String getName() {
        return this.name;
    }
}
