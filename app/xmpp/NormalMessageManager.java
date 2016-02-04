package app.xmpp;

import app.Main;
import app.common.collection.ObserverArrayList;
import app.common.collection.ObserverArrayList.ArrayListListener;
import app.common.collection.ObserverHashMap;
import app.common.collection.ObserverHashMap.HashMapListener;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.ChatHistoryEntity;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.CallsDataSource;
import app.database.datasource.ConversationNormalDataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NormalMessageManager {
    private static NormalMessageManager f4971a;
    private ObserverHashMap<String, ChatHistoryEntity> f4972b;
    private ObserverArrayList<CallLogHistoryEntity> f4973c;
    private String f4974d;
    private List<String> f4975e;
    private final Object f4976f;
    private final Object f4977g;

    public static synchronized NormalMessageManager m7447a() {
        NormalMessageManager normalMessageManager;
        synchronized (NormalMessageManager.class) {
            if (f4971a == null) {
                f4971a = new NormalMessageManager();
            }
            normalMessageManager = f4971a;
        }
        return normalMessageManager;
    }

    public NormalMessageManager() {
        this.f4976f = new Object();
        this.f4977g = new Object();
        this.f4972b = new ObserverHashMap();
        this.f4973c = new ObserverArrayList();
        m7455a(ConversationNormalDataSource.m4623a().m4629a(Main.f1927b), true, false);
        m7461b(BroadcastListDataSource.m4504a().m4517c(), true, false);
        m7454a(CallsDataSource.m4527a().m4532a(Main.f1927b), true);
    }

    public void m7451a(ChatHistoryEntity chatHistoryEntity) {
        this.f4972b.put(chatHistoryEntity.m4170d().m4333d(), chatHistoryEntity);
    }

    public void m7459b(ChatHistoryEntity chatHistoryEntity) {
        this.f4972b.put(chatHistoryEntity.m4168c(), chatHistoryEntity);
    }

    public void m7457b() {
        if (this.f4972b == null) {
            this.f4972b = new ObserverHashMap();
        } else {
            this.f4972b.clear();
        }
    }

    public void m7453a(List<String> list) {
        for (String a : list) {
            m7452a(a);
        }
    }

    public void m7452a(String str) {
        this.f4972b.remove(str);
    }

    public void m7449a(HashMapListener hashMapListener) {
        this.f4972b.m4090a(hashMapListener);
    }

    public void m7456a(boolean z) {
        this.f4972b.m4091a(z);
    }

    public void m7455a(List<ChatHistoryEntity> list, boolean z, boolean z2) {
        if (z2) {
            m7457b();
        }
        for (ChatHistoryEntity chatHistoryEntity : list) {
            if (!this.f4972b.containsKey(chatHistoryEntity.m4170d().m4333d()) || z) {
                m7451a(chatHistoryEntity);
            }
        }
    }

    public void m7461b(List<ChatHistoryEntity> list, boolean z, boolean z2) {
        if (z2) {
            m7457b();
        }
        for (ChatHistoryEntity chatHistoryEntity : list) {
            if (!this.f4972b.containsKey(chatHistoryEntity.m4168c()) || z) {
                m7459b(chatHistoryEntity);
            }
        }
    }

    public boolean m7463b(String str) {
        return this.f4972b.get(str) != null;
    }

    public boolean m7465c(String str) {
        return this.f4972b.get(str) != null && ((ChatHistoryEntity) this.f4972b.get(str)).m4167b();
    }

    public boolean m7467d(String str) {
        for (ChatHistoryEntity i : this.f4972b.values()) {
            if (i.m4175i().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public ChatHistoryEntity m7468e(String str) {
        for (ChatHistoryEntity chatHistoryEntity : this.f4972b.values()) {
            if (chatHistoryEntity.m4175i().equals(str)) {
                return chatHistoryEntity;
            }
        }
        return null;
    }

    public ObserverHashMap<String, ChatHistoryEntity> m7464c() {
        ObserverHashMap<String, ChatHistoryEntity> observerHashMap;
        synchronized (this.f4976f) {
            observerHashMap = this.f4972b;
        }
        return observerHashMap;
    }

    public List<ChatHistoryEntity> m7466d() {
        List<ChatHistoryEntity> arrayList;
        synchronized (this.f4976f) {
            arrayList = new ArrayList(this.f4972b.values());
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public ChatHistoryEntity m7470f(String str) {
        return (ChatHistoryEntity) this.f4972b.get(str);
    }

    public ChatHistoryEntity m7472g(String str) {
        return (ChatHistoryEntity) this.f4972b.get(str);
    }

    public void m7450a(CallLogHistoryEntity callLogHistoryEntity) {
        if (!this.f4973c.isEmpty() && ((CallLogHistoryEntity) this.f4973c.m4077a()).m4120e().m4108b().equals(callLogHistoryEntity.m4120e().m4108b()) && ((CallLogHistoryEntity) this.f4973c.m4077a()).m4120e().m4109c().equals(callLogHistoryEntity.m4120e().m4109c())) {
            ((CallLogHistoryEntity) this.f4973c.m4077a()).m4115a(callLogHistoryEntity.m4120e());
            ((CallLogHistoryEntity) this.f4973c.m4077a()).m4121f();
            return;
        }
        this.f4973c.add(callLogHistoryEntity);
    }

    public void m7469e() {
        if (this.f4973c == null) {
            this.f4973c = new ObserverArrayList();
        } else {
            this.f4973c.clear();
        }
    }

    public void m7458b(CallLogHistoryEntity callLogHistoryEntity) {
        synchronized (this.f4977g) {
            Iterator it = this.f4973c.iterator();
            while (it.hasNext()) {
                if (((CallLogHistoryEntity) it.next()).equals(callLogHistoryEntity)) {
                    it.remove();
                    this.f4973c.m4080b();
                }
            }
        }
    }

    public void m7448a(ArrayListListener arrayListListener) {
        this.f4973c.m4078a(arrayListListener);
    }

    public void m7462b(boolean z) {
        this.f4973c.m4079a(z);
    }

    public void m7454a(List<CallLogHistoryEntity> list, boolean z) {
        m7469e();
        for (CallLogHistoryEntity callLogHistoryEntity : list) {
            if (!this.f4973c.contains(callLogHistoryEntity) || z) {
                m7450a(callLogHistoryEntity);
            }
        }
    }

    public ArrayList<CallLogHistoryEntity> m7471f() {
        Object arrayList;
        synchronized (this.f4977g) {
            arrayList = new ArrayList(this.f4973c);
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public String m7473g() {
        return this.f4974d;
    }

    public void m7475h(String str) {
        this.f4974d = str;
    }

    public ArrayList<String> m7474h() {
        if (this.f4975e == null) {
            return new ArrayList();
        }
        return new ArrayList(this.f4975e);
    }

    public ArrayList<String> m7476i() {
        ArrayList<String> arrayList = new ArrayList();
        for (ChatHistoryEntity chatHistoryEntity : this.f4972b.values()) {
            if (!chatHistoryEntity.m4167b()) {
                arrayList.add(chatHistoryEntity.m4170d().m4333d());
            }
        }
        return arrayList;
    }

    public ArrayList<String> m7477j() {
        ArrayList<String> arrayList = new ArrayList();
        for (ChatHistoryEntity chatHistoryEntity : this.f4972b.values()) {
            if (chatHistoryEntity.m4167b()) {
                arrayList.add(chatHistoryEntity.m4168c());
            }
        }
        return arrayList;
    }

    public void m7460b(List<String> list) {
        this.f4975e = list;
    }
}
