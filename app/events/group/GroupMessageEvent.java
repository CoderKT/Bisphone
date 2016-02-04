package app.events.group;

import app.common.entity.HistoryEntity;
import java.util.ArrayList;

public class GroupMessageEvent {
    private ArrayList<HistoryEntity> f2450a;
    private String f2451b;

    public GroupMessageEvent(String str, ArrayList<HistoryEntity> arrayList) {
        this.f2451b = str;
        this.f2450a = arrayList;
    }

    public String m4904a() {
        return this.f2451b;
    }

    public ArrayList<HistoryEntity> m4905b() {
        return this.f2450a;
    }
}
