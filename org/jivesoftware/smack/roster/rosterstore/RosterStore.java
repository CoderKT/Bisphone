package org.jivesoftware.smack.roster.rosterstore;

import java.util.Collection;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;

public interface RosterStore {
    boolean addEntry(Item item, String str);

    Collection<Item> getEntries();

    Item getEntry(String str);

    String getRosterVersion();

    boolean removeEntry(String str, String str2);

    boolean resetEntries(Collection<Item> collection, String str);
}
