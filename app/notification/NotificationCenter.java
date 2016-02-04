package app.notification;

import app.Main;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.datasource.HistoryGroupDataSource;
import app.events.NewMessageEvent;
import app.xmpp.GroupManager;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class NotificationCenter {
    private static NotificationCenter f4123a;
    private NotificationGenerator f4124b;
    private List<String> f4125c;
    private List<String> f4126d;
    private boolean f4127e;
    private String f4128f;
    private int f4129g;
    private List<HistoryNormalMessageEntity> f4130h;
    private List<String> f4131i;
    private List<String> f4132j;
    private HashMap<String, List<HistoryGroupEntity>> f4133k;
    private volatile String f4134l;
    private final Object f4135m;

    static {
        f4123a = null;
    }

    private NotificationCenter() {
        this.f4127e = false;
        this.f4128f = null;
        this.f4129g = 0;
        this.f4134l = null;
        this.f4135m = new Object();
        m6607n();
        this.f4126d = new ArrayList();
        this.f4125c = new ArrayList();
        this.f4130h = new ArrayList();
        this.f4131i = new ArrayList();
        this.f4132j = new ArrayList();
        this.f4124b = new NotificationGenerator(Main.f1927b);
    }

    private void m6607n() {
        List i = GroupManager.m7323a().m7375i();
        if (i == null || i.size() == 0) {
            this.f4133k = new HashMap();
            return;
        }
        this.f4133k = new HashMap();
        for (int i2 = 0; i2 < i.size(); i2++) {
            if (((ConversationGroupEntity) i.get(i2)).m4277c() > 0) {
                int c = ((ConversationGroupEntity) i.get(i2)).m4277c();
                String b = ((ConversationGroupEntity) i.get(i2)).m4274b();
                if (b != null) {
                    List a = HistoryGroupDataSource.m4691a().m4707a(b, c);
                    if (a.size() != 0) {
                        this.f4133k.put(b, a);
                    }
                }
            }
        }
    }

    public static synchronized NotificationCenter m6606a() {
        NotificationCenter notificationCenter;
        synchronized (NotificationCenter.class) {
            if (f4123a == null) {
                f4123a = new NotificationCenter();
            }
            notificationCenter = f4123a;
        }
        return notificationCenter;
    }

    public void m6609a(String str) {
        this.f4126d.add(str);
        this.f4124b.m6658a(this.f4126d);
    }

    public void m6614b(String str) {
        if (!this.f4125c.contains(str)) {
            this.f4125c.add(str);
        }
        this.f4124b.m6663b(this.f4125c);
    }

    public void m6611a(String str, String str2) {
        if (!this.f4131i.contains(str)) {
            this.f4131i.add(str);
            this.f4132j.add(str2);
        }
        this.f4124b.m6659a(this.f4131i, this.f4132j);
    }

    public void m6608a(HistoryNormalMessageEntity historyNormalMessageEntity) {
        if (historyNormalMessageEntity != null) {
            EventBus.m12779a().m12795d(new NewMessageEvent(historyNormalMessageEntity, historyNormalMessageEntity.m4456M()));
            if (this.f4134l == null || !this.f4134l.equals(historyNormalMessageEntity.m4414c())) {
                if (this.f4128f == null) {
                    this.f4128f = historyNormalMessageEntity.m4456M();
                } else if (!(this.f4127e || this.f4128f.equals(historyNormalMessageEntity.m4456M()))) {
                    this.f4127e = true;
                }
                this.f4129g++;
                this.f4130h.add(historyNormalMessageEntity);
                this.f4124b.m6661a(this.f4127e, this.f4129g, this.f4130h, true);
            }
        }
    }

    public void m6612a(ArrayList<HistoryGroupEntity> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            if (this.f4134l == null || !this.f4134l.equals(((HistoryGroupEntity) arrayList.get(0)).m4414c())) {
                if (this.f4133k.containsKey(((HistoryGroupEntity) arrayList.get(0)).m4414c())) {
                    ((List) this.f4133k.get(((HistoryGroupEntity) arrayList.get(0)).m4414c())).addAll(arrayList);
                } else {
                    this.f4133k.put(((HistoryGroupEntity) arrayList.get(0)).m4414c(), arrayList);
                }
                this.f4124b.m6660a(true);
            }
        }
    }

    public List<String> m6613b() {
        synchronized (this.f4135m) {
            if (this.f4133k == null || this.f4133k.size() == 0) {
                List arrayList = new ArrayList();
                return arrayList;
            }
            List<String> arrayList2 = new ArrayList();
            for (Entry key : this.f4133k.entrySet()) {
                arrayList2.add(key.getKey());
            }
            return arrayList2;
        }
    }

    public List<HistoryGroupEntity> m6615c(String str) {
        List<HistoryGroupEntity> list;
        synchronized (this.f4135m) {
            if (this.f4133k == null || this.f4133k.size() == 0) {
                list = null;
            } else {
                list = (List) this.f4133k.get(str);
            }
        }
        return list;
    }

    public void m6618d(String str) {
        this.f4124b.m6656a(str);
    }

    void m6616c() {
        this.f4125c.clear();
    }

    void m6617d() {
        this.f4131i.clear();
        this.f4132j.clear();
    }

    void m6620e(String str) {
        Iterator it = this.f4130h.iterator();
        while (it.hasNext()) {
            if (((HistoryNormalMessageEntity) it.next()).m4456M().equals(str)) {
                it.remove();
            }
        }
        this.f4129g = this.f4130h.size();
        this.f4127e = this.f4130h.size() > 1;
        this.f4128f = this.f4130h.isEmpty() ? null : this.f4128f;
    }

    void m6622f(String str) {
        if (str != null && this.f4133k != null && this.f4133k.size() != 0) {
            Iterator it = this.f4133k.entrySet().iterator();
            while (it.hasNext()) {
                if (str.equals(((Entry) it.next()).getKey())) {
                    it.remove();
                }
            }
        }
    }

    public synchronized void m6619e() {
        this.f4124b.m6655a().cancel(10);
        m6616c();
    }

    public synchronized void m6621f() {
        this.f4124b.m6655a().cancel(14);
        m6623g();
    }

    void m6623g() {
        this.f4126d.clear();
        this.f4124b.m6662b();
    }

    public synchronized void m6624g(String str) {
        if (this.f4130h != null) {
            m6620e(str);
            if (this.f4130h.isEmpty()) {
                this.f4124b.m6655a().cancel(11);
                this.f4129g = 0;
            } else {
                this.f4124b.m6664b(false);
                this.f4124b.m6661a(this.f4127e, this.f4129g, this.f4130h, false);
            }
        }
    }

    public void m6626h(String str) {
        synchronized (this.f4135m) {
            if (this.f4133k == null) {
                return;
            }
            m6622f(str);
            if (this.f4133k.size() == 0) {
                this.f4124b.m6655a().cancel(12);
            } else {
                this.f4124b.m6655a().cancel(12);
                this.f4124b.m6664b(false);
            }
        }
    }

    public synchronized void m6625h() {
        this.f4124b.m6655a().cancel(13);
        m6617d();
    }

    public synchronized void m6627i() {
        this.f4124b.m6655a().cancel(21);
    }

    public void m6629j() {
        this.f4124b.m6655a().cancelAll();
        m6616c();
        m6617d();
    }

    public synchronized void m6630k() {
        if (this.f4130h != null) {
            this.f4130h.clear();
            this.f4124b.m6655a().cancel(11);
            this.f4129g = 0;
            this.f4124b.m6664b(false);
        }
    }

    public synchronized void m6631l() {
        if (!(this.f4133k == null || this.f4133k.size() == 0)) {
            this.f4133k.clear();
            this.f4124b.m6655a().cancel(12);
            this.f4124b.m6664b(false);
        }
    }

    public synchronized void m6628i(String str) {
        this.f4134l = str;
    }

    public String m6632m() {
        return this.f4134l != null ? this.f4134l : "";
    }

    public void m6610a(String str, NotificationState notificationState) {
        this.f4124b.m6657a(str, notificationState);
    }
}
