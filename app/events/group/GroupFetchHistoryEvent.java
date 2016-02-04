package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.HistoryXE;

public class GroupFetchHistoryEvent {
    HistoryXE f2447a;
    String f2448b;
    GroupListener f2449c;

    public GroupListener m4898a() {
        return this.f2449c;
    }

    public void m4899a(GroupListener groupListener) {
        this.f2449c = groupListener;
    }

    public HistoryXE m4902b() {
        return this.f2447a;
    }

    public String m4903c() {
        return this.f2448b;
    }

    public void m4900a(HistoryXE historyXE) {
        this.f2447a = historyXE;
    }

    public void m4901a(String str) {
        this.f2448b = str;
    }
}
