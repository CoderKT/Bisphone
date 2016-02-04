package app.common;

import app.Main;
import app.xmpp.packet.common.TimeXE;
import app.xmpp.packet.groupv2.GroupElement;
import app.xmpp.packet.groupv2.HistoryAckXE;
import app.xmpp.packet.groupv2.HistoryAckXE.Item;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.time.packet.Time;

public class GroupV2FetchHistoryModel {
    private final Set<Message> f2003a;
    private List<Message> f2004b;
    private final ArrayList<String> f2005c;
    private Boolean f2006d;
    private Message f2007e;
    private GroupElement f2008f;
    private long f2009g;

    public GroupV2FetchHistoryModel() {
        this.f2003a = new HashSet();
        this.f2005c = new ArrayList();
    }

    private String m4028d(Message message) {
        ExtensionElement extension = message.getExtension(Time.ELEMENT, "bpn:common:v1");
        if (extension == null) {
            return null;
        }
        String str = ((TimeXE) extension).m7579a() + "";
        Main.f1926a.m5681c("TimePX with timestamp: " + str);
        return str;
    }

    private void m4027a(List<String> list) {
        synchronized (this.f2005c) {
            this.f2005c.clear();
            this.f2005c.addAll(list);
        }
    }

    private void m4029j() {
        synchronized (this.f2005c) {
            this.f2005c.clear();
        }
    }

    private void m4030k() {
        synchronized (this.f2003a) {
            this.f2003a.clear();
        }
    }

    public void m4033a(HistoryAckXE historyAckXE) {
        List arrayList = new ArrayList();
        for (Item a : historyAckXE.m7651b()) {
            arrayList.add(a.m7646a());
        }
        m4027a(arrayList);
        this.f2006d = Boolean.valueOf(historyAckXE.m7650a());
    }

    public boolean m4034a(Message message) {
        boolean z = true;
        synchronized (this.f2003a) {
            this.f2003a.add(message);
            synchronized (this.f2005c) {
                if (this.f2005c.size() == 0) {
                    if (this.f2003a.size() != 20) {
                        z = false;
                    }
                } else {
                    if (this.f2005c.size() != this.f2003a.size()) {
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    public ArrayList<Message> m4031a() {
        ArrayList<Message> arrayList;
        synchronized (this.f2003a) {
            if (this.f2003a.size() == 0) {
                arrayList = new ArrayList();
            } else {
                arrayList = new ArrayList(this.f2003a);
            }
        }
        return arrayList;
    }

    public void m4036b(Message message) {
        if (this.f2004b == null) {
            this.f2004b = new ArrayList();
        }
        this.f2004b.add(message);
    }

    public void m4035b() {
        if (this.f2004b == null) {
            this.f2004b = new ArrayList();
        } else {
            this.f2004b.clear();
        }
    }

    public List<Message> m4037c() {
        if (this.f2004b == null) {
            return new ArrayList();
        }
        return this.f2004b;
    }

    public List<String> m4039d() {
        synchronized (this.f2003a) {
            Set<Message> copyOnWriteArraySet = new CopyOnWriteArraySet(this.f2003a);
        }
        synchronized (this.f2005c) {
            List<String> copyOnWriteArrayList = new CopyOnWriteArrayList(this.f2005c);
        }
        if (copyOnWriteArraySet.size() == 0 || copyOnWriteArrayList.size() / 5 > copyOnWriteArraySet.size()) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String str : copyOnWriteArrayList) {
            Object obj;
            for (Message message : copyOnWriteArraySet) {
                if (m4028d(message) != null && m4028d(message).equals(str)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public boolean m4040e() {
        return this.f2006d == null || !this.f2006d.booleanValue();
    }

    public void m4038c(Message message) {
        this.f2007e = message;
    }

    public Message m4041f() {
        return this.f2007e;
    }

    public boolean m4042g() {
        boolean z;
        synchronized (this.f2003a) {
            synchronized (this.f2005c) {
                z = (this.f2006d.booleanValue() && this.f2005c.size() == 0) || this.f2005c.size() == this.f2003a.size();
            }
        }
        return z;
    }

    public void m4043h() {
        m4029j();
        m4030k();
        m4035b();
        this.f2007e = null;
        this.f2006d = Boolean.valueOf(false);
    }

    public void m4032a(GroupElement groupElement, long j) {
        if (this.f2008f == null) {
            this.f2008f = groupElement;
        } else if (this.f2009g < j) {
            this.f2008f = groupElement;
        }
    }

    public GroupElement m4044i() {
        return this.f2008f;
    }
}
