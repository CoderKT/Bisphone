package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.jxmpp.util.XmppStringUtils;

public class RosterGroup extends Manager {
    private final Set<RosterEntry> entries;
    private final String name;

    RosterGroup(String str, XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.name = str;
        this.entries = new LinkedHashSet();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        synchronized (this.entries) {
            for (RosterEntry rosterEntry : this.entries) {
                IQ rosterPacket = new RosterPacket();
                rosterPacket.setType(Type.set);
                Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
                toRosterItem.removeGroupName(this.name);
                toRosterItem.addGroupName(str);
                rosterPacket.addRosterItem(toRosterItem);
                connection().createPacketCollectorAndSend(rosterPacket).nextResultOrThrow();
            }
        }
    }

    public int getEntryCount() {
        int size;
        synchronized (this.entries) {
            size = this.entries.size();
        }
        return size;
    }

    public List<RosterEntry> getEntries() {
        List arrayList;
        synchronized (this.entries) {
            arrayList = new ArrayList(this.entries);
        }
        return arrayList;
    }

    public RosterEntry getEntry(String str) {
        if (str == null) {
            return null;
        }
        String toLowerCase = XmppStringUtils.m13447d(str).toLowerCase(Locale.US);
        synchronized (this.entries) {
            for (RosterEntry rosterEntry : this.entries) {
                if (rosterEntry.getUser().equals(toLowerCase)) {
                    return rosterEntry;
                }
            }
            return null;
        }
    }

    public boolean contains(RosterEntry rosterEntry) {
        boolean contains;
        synchronized (this.entries) {
            contains = this.entries.contains(rosterEntry);
        }
        return contains;
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public void addEntry(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            if (!this.entries.contains(rosterEntry)) {
                IQ rosterPacket = new RosterPacket();
                rosterPacket.setType(Type.set);
                Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
                toRosterItem.addGroupName(getName());
                rosterPacket.addRosterItem(toRosterItem);
                connection().createPacketCollectorAndSend(rosterPacket).nextResultOrThrow();
            }
        }
    }

    public void removeEntry(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            if (this.entries.contains(rosterEntry)) {
                IQ rosterPacket = new RosterPacket();
                rosterPacket.setType(Type.set);
                Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
                toRosterItem.removeGroupName(getName());
                rosterPacket.addRosterItem(toRosterItem);
                connection().createPacketCollectorAndSend(rosterPacket).nextResultOrThrow();
            }
        }
    }

    void addEntryLocal(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            this.entries.remove(rosterEntry);
            this.entries.add(rosterEntry);
        }
    }

    void removeEntryLocal(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            if (this.entries.contains(rosterEntry)) {
                this.entries.remove(rosterEntry);
            }
        }
    }
}
