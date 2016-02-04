package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemType;

public final class RosterEntry extends Manager {
    private String name;
    private final Roster roster;
    private ItemStatus status;
    private ItemType type;
    private final String user;

    RosterEntry(String str, String str2, ItemType itemType, ItemStatus itemStatus, Roster roster, XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.user = str;
        this.name = str2;
        this.type = itemType;
        this.status = itemStatus;
        this.roster = roster;
    }

    public String getUser() {
        return this.user;
    }

    public String getName() {
        return this.name;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setName(java.lang.String r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        if (r3 == 0) goto L_0x000d;
    L_0x0003:
        r0 = r2.name;	 Catch:{ all -> 0x002c }
        r0 = r3.equals(r0);	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r2);
        return;
    L_0x000d:
        r0 = new org.jivesoftware.smack.roster.packet.RosterPacket;	 Catch:{ all -> 0x002c }
        r0.<init>();	 Catch:{ all -> 0x002c }
        r1 = org.jivesoftware.smack.packet.IQ.Type.set;	 Catch:{ all -> 0x002c }
        r0.setType(r1);	 Catch:{ all -> 0x002c }
        r1 = toRosterItem(r2, r3);	 Catch:{ all -> 0x002c }
        r0.addRosterItem(r1);	 Catch:{ all -> 0x002c }
        r1 = r2.connection();	 Catch:{ all -> 0x002c }
        r0 = r1.createPacketCollectorAndSend(r0);	 Catch:{ all -> 0x002c }
        r0.nextResultOrThrow();	 Catch:{ all -> 0x002c }
        r2.name = r3;	 Catch:{ all -> 0x002c }
        goto L_0x000b;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.roster.RosterEntry.setName(java.lang.String):void");
    }

    void updateState(String str, ItemType itemType, ItemStatus itemStatus) {
        this.name = str;
        this.type = itemType;
        this.status = itemStatus;
    }

    public List<RosterGroup> getGroups() {
        List<RosterGroup> arrayList = new ArrayList();
        for (RosterGroup rosterGroup : this.roster.getGroups()) {
            if (rosterGroup.contains(this)) {
                arrayList.add(rosterGroup);
            }
        }
        return arrayList;
    }

    public ItemType getType() {
        return this.type;
    }

    public ItemStatus getStatus() {
        return this.status;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.name != null) {
            stringBuilder.append(this.name).append(": ");
        }
        stringBuilder.append(this.user);
        Collection groups = getGroups();
        if (!groups.isEmpty()) {
            stringBuilder.append(" [");
            Iterator it = groups.iterator();
            stringBuilder.append(((RosterGroup) it.next()).getName());
            while (it.hasNext()) {
                stringBuilder.append(", ");
                stringBuilder.append(((RosterGroup) it.next()).getName());
            }
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }

    public int hashCode() {
        return this.user == null ? 0 : this.user.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RosterEntry)) {
            return false;
        }
        return this.user.equals(((RosterEntry) obj).getUser());
    }

    public boolean equalsDeep(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RosterEntry rosterEntry = (RosterEntry) obj;
        if (this.name == null) {
            if (rosterEntry.name != null) {
                return false;
            }
        } else if (!this.name.equals(rosterEntry.name)) {
            return false;
        }
        if (this.status == null) {
            if (rosterEntry.status != null) {
                return false;
            }
        } else if (!this.status.equals(rosterEntry.status)) {
            return false;
        }
        if (this.type == null) {
            if (rosterEntry.type != null) {
                return false;
            }
        } else if (!this.type.equals(rosterEntry.type)) {
            return false;
        }
        if (this.user == null) {
            if (rosterEntry.user != null) {
                return false;
            }
            return true;
        } else if (this.user.equals(rosterEntry.user)) {
            return true;
        } else {
            return false;
        }
    }

    static Item toRosterItem(RosterEntry rosterEntry) {
        return toRosterItem(rosterEntry, rosterEntry.getName());
    }

    private static Item toRosterItem(RosterEntry rosterEntry, String str) {
        Item item = new Item(rosterEntry.getUser(), str);
        item.setItemType(rosterEntry.getType());
        item.setItemStatus(rosterEntry.getStatus());
        for (RosterGroup name : rosterEntry.getGroups()) {
            item.addGroupName(name.getName());
        }
        return item;
    }
}
