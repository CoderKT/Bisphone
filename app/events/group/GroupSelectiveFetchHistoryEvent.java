package app.events.group;

import app.messaging.group.GroupListener;
import java.util.List;

public class GroupSelectiveFetchHistoryEvent {
    List<String> f2452a;
    String f2453b;
    int f2454c;
    GroupListener f2455d;

    public GroupListener m4906a() {
        return this.f2455d;
    }

    public int m4911b() {
        return this.f2454c;
    }

    public String m4912c() {
        return this.f2453b;
    }

    public List<String> m4913d() {
        return this.f2452a;
    }

    public void m4910a(List<String> list) {
        this.f2452a = list;
    }

    public void m4908a(GroupListener groupListener) {
        this.f2455d = groupListener;
    }

    public void m4909a(String str) {
        this.f2453b = str;
    }

    public void m4907a(int i) {
        this.f2454c = i;
    }
}
