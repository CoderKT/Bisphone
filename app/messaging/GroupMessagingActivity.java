package app.messaging;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryGroupEntity.Builder;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.datasource.ContactDataSource;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.NewMessageEvent;
import app.events.group.GroupMessageEvent;
import app.events.group.UserStatusChangedEvent;
import app.events.vcard.VCardRefreshEvent;
import app.events.xmpp.MessageSendEvent;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.messaging.group.GroupSettingActivity;
import app.messaging.vh.DisplayDataHandler;
import app.notification.NotificationCenter;
import app.util.StringUtil;
import app.xmpp.GroupManager;
import app.xmpp.JabberId;
import app.xmpp.VCardHandler;
import app.xmpp.VCardInsertUpdateEntity;
import de.greenrobot.event.EventBus;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class GroupMessagingActivity extends RecycleMessagingActivity {
    private int aA;
    private boolean aB;
    private final Object aC;
    private ConversationGroupEntity aw;
    private Set<String> ax;
    private ArrayList<HistoryEntity> ay;
    private String az;

    /* renamed from: app.messaging.GroupMessagingActivity.1 */
    class C02541 extends ArrayList<String> {
        final /* synthetic */ GroupMessagingActivity f3214a;

        C02541(GroupMessagingActivity groupMessagingActivity) {
            this.f3214a = groupMessagingActivity;
            add(this.f3214a.az);
        }
    }

    class RunnableMessageSendEvent implements Runnable {
        private final WeakReference<GroupMessagingActivity> f3215a;
        private final MessageSendEvent f3216b;

        public RunnableMessageSendEvent(GroupMessagingActivity groupMessagingActivity, MessageSendEvent messageSendEvent) {
            this.f3215a = new WeakReference(groupMessagingActivity);
            this.f3216b = messageSendEvent;
        }

        public void run() {
            GroupMessagingActivity groupMessagingActivity = (GroupMessagingActivity) this.f3215a.get();
            if (groupMessagingActivity != null) {
                if (groupMessagingActivity.O == null || groupMessagingActivity.O.m5894e()) {
                    groupMessagingActivity.m5832z();
                    return;
                }
                groupMessagingActivity.m5823q();
                HistoryGroupEntity a = new Builder().m4444a(this.f3216b.m4985a(), "").m4445a();
                groupMessagingActivity.m5781P().add(a);
                groupMessagingActivity.N.add(a.m4412b());
                groupMessagingActivity.m5796a(-1, true);
            }
        }
    }

    class RunnableUpdateVCard implements Runnable {
        private final List<String> f3217a;

        public RunnableUpdateVCard(List<String> list) {
            this.f3217a = list;
        }

        public void run() {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < this.f3217a.size(); i++) {
                ContactEntity contactEntity = (ContactEntity) DisplayDataHandler.f4039a.get(this.f3217a.get(i));
                if (contactEntity != null) {
                    JabberId jabberId = new JabberId(contactEntity.m4200g(), "bisphone.com", null);
                    if (!VCardHandler.m7499a().m7506c(jabberId.m7391e())) {
                        if (contactEntity.m4210m() == null) {
                            hashMap.put(jabberId.m7391e(), new VCardInsertUpdateEntity(jabberId.m7391e(), true));
                        } else if (contactEntity.m4210m() != TYPE.SELF) {
                            hashMap.put(jabberId.m7391e(), new VCardInsertUpdateEntity(jabberId.m7391e(), false));
                        }
                    }
                }
            }
            if (hashMap.size() > 0) {
                VCardHandler.m7499a().m7502a(hashMap);
            }
        }
    }

    class RunnableVCardRefreshEvent implements Runnable {
        private final WeakReference<GroupMessagingActivity> f3218a;
        private final VCardRefreshEvent f3219b;

        public RunnableVCardRefreshEvent(GroupMessagingActivity groupMessagingActivity, VCardRefreshEvent vCardRefreshEvent) {
            this.f3218a = new WeakReference(groupMessagingActivity);
            this.f3219b = vCardRefreshEvent;
        }

        public void run() {
            GroupMessagingActivity groupMessagingActivity = (GroupMessagingActivity) this.f3218a.get();
            if (groupMessagingActivity != null) {
                groupMessagingActivity.m5872c(false);
                groupMessagingActivity.m5867T().removeAll(this.f3219b.m4961a().keySet());
                groupMessagingActivity.m5820n();
            }
        }
    }

    public GroupMessagingActivity() {
        this.aA = 0;
        this.aB = false;
        this.aC = new Object();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.m12779a().m12791a((Object) this);
        this.ax = new HashSet();
        DisplayDataHandler.f4039a = new HashMap();
        m5800a(LoadDirection.downToUp, false, getIntent());
        m5881j();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        m5878a(intent);
        m5881j();
        m5781P().clear();
        m5826t();
        m5800a(LoadDirection.downToUp, false, intent);
    }

    protected void onResume() {
        super.onResume();
        this.P.m5977f(2);
        NotificationCenter.m6606a().m6628i(this.az);
        NotificationCenter.m6606a().m6626h(this.az);
        GroupManager.m7323a().m7364c(this.aw);
    }

    protected void onPause() {
        super.onPause();
        if (this.aw != null && this.an) {
            long parseLong;
            HistoryEntity R = m5865R();
            if (R != null) {
                parseLong = Long.parseLong(R.m4419f());
            } else {
                parseLong = Long.parseLong(this.aw.m4297k());
            }
            if (parseLong == 1000) {
                parseLong = System.currentTimeMillis() * 1000;
            }
            this.aw.m4268a(0);
            this.aw.m4288f(R != null ? R.m4416d() : "");
            this.aw.m4276b(R != null ? R.m4412b() : "-1");
            this.aw.m4269a(parseLong);
            if (this.P != null && this.P.m5962a() > 0) {
                ConversationGroupDataSource.m4587a().m4601a(this.az, this.aw.m4307s());
                HistoryGroupDataSource.m4696a(this.az, this.w.getText().toString().trim());
            } else if (this.an) {
                ConversationGroupDataSource.m4587a().m4599a(this.az);
                HistoryGroupDataSource.m4701c(this.az);
            }
        }
    }

    private HistoryEntity m5865R() {
        if (this.O == null || this.K.size() == 0) {
            return null;
        }
        if (this.O.m5894e()) {
            return HistoryGroupDataSource.m4691a().m4714d(this.az);
        }
        int size = m5781P().size() - 1;
        while (size > 0 && ((HistoryEntity) m5781P().get(size)).m4420g() != DeliveryStatus.RECEIVED && ((HistoryEntity) m5781P().get(size)).m4420g() != DeliveryStatus.DELIVERED && ((HistoryEntity) m5781P().get(size)).m4420g() != DeliveryStatus.SENT) {
            size--;
        }
        return (HistoryEntity) m5781P().get(size);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        NotificationCenter.m6606a().m6628i(this.az);
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 30020:
                    String stringExtra = intent.getStringExtra("extra_group_status");
                    if (stringExtra.equals("DESTROYING") || stringExtra.equals("LEFT_DELETE")) {
                        finish();
                        return;
                    }
                    this.aw = GroupManager.m7323a().m7358b(this.az);
                    this.aw.m4279c(stringExtra);
                    m5866S();
                    m5881j();
                default:
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!(this.aw == null || this.aw.m4283e().equals("LEAVING") || this.aw.m4283e().equals("LEFT") || this.aw.m4283e().equals("DESTROYED") || this.aw.m4283e().equals("DESTROYING") || this.aw.m4283e().equals("KICKED"))) {
            getMenuInflater().inflate(2131820549, menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        m5770D();
        switch (menuItem.getItemId()) {
            case 2131755634:
                Intent intent = new Intent(this, GroupSettingActivity.class);
                intent.putExtra("extra_group_jid", this.az);
                startActivityForResult(intent, 30020);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void m5878a(Intent intent) {
        this.az = intent.getStringExtra("extra_group_jid");
        this.aw = GroupManager.m7323a().m7358b(this.az);
        m5866S();
    }

    private void m5866S() {
        boolean z = this.aw == null || this.aw.m4283e().equals("DESTROYED") || this.aw.m4283e().equals("DESTROYING") || this.aw.m4283e().equals("LEFT") || this.aw.m4283e().equals("LEAVING") || this.aw.m4283e().equals("KICKED");
        this.aB = z;
    }

    protected ArrayList<String> m5882l() {
        return new C02541(this);
    }

    private Set<String> m5867T() {
        Set<String> set;
        synchronized (this.aC) {
            set = this.ax;
        }
        return set;
    }

    protected ArrayList<HistoryEntity> m5875a(long j, String str) {
        ArrayList<HistoryEntity> arrayList = null;
        HistoryEntity a = HistoryGroupDataSource.m4691a().m4704a(str);
        if (a != null) {
            Cursor a2 = HistoryGroupDataSource.m4691a().m4703a(a.m4414c(), Long.parseLong(a.m4419f()), 40);
            if (a2 != null && a2.moveToFirst()) {
                arrayList = new ArrayList();
                do {
                    arrayList.add(HistoryNormalMessageDataSource.m4719a(a2));
                } while (a2.moveToNext());
            }
        }
        return arrayList;
    }

    protected void m5883m() {
        m5811b(HistoryGroupDataSource.m4698b(this.az));
    }

    protected ArrayList<HistoryEntity> m5874a(long j, long j2) {
        return HistoryGroupDataSource.m4691a().m4706a(this.az, j, j2);
    }

    protected void m5877a(int i, int i2, boolean z) {
        super.m5795a(i, i2, z);
        if (!z) {
            return;
        }
        if (this.J.m3240j() <= 0 || this.J.m3240j() + 2 >= i + i2) {
            this.p.m3611b(m5781P().size());
        } else {
            this.u.setVisibility(0);
        }
    }

    public void onEventMainThread(NewMessageEvent newMessageEvent) {
    }

    public void onEventMainThread(GroupMessageEvent groupMessageEvent) {
        boolean z = false;
        if (groupMessageEvent.m4905b() != null && groupMessageEvent.m4905b().size() != 0 && groupMessageEvent.m4904a().equals(this.az) && this.an) {
            if (this.O == null || this.O.m5894e()) {
                this.u.setVisibility(0);
                return;
            }
            this.ay = groupMessageEvent.m4905b();
            Collections.sort(this.ay);
            Iterator it = this.ay.iterator();
            while (it.hasNext()) {
                if (this.N.contains(((HistoryEntity) it.next()).m4412b())) {
                    it.remove();
                }
            }
            if (this.ay.size() == 1) {
                m5830x();
            }
            if (m5781P().size() > 0) {
                String f = ((HistoryEntity) this.ay.get(0)).m4419f();
                for (int size = m5781P().size() - 1; size >= 0; size--) {
                    if (f.compareTo(((HistoryEntity) m5781P().get(size)).m4419f()) > 0) {
                        this.aA = size + 1;
                        break;
                    }
                }
            } else {
                this.aA = 0;
            }
            m5781P().addAll(this.aA, this.ay);
            m5877a(this.aA, this.ay.size(), true);
            it = groupMessageEvent.m4905b().iterator();
            while (it.hasNext()) {
                boolean z2;
                String M = ((HistoryGroupEntity) ((HistoryEntity) it.next())).m4449M();
                if (DisplayDataHandler.f4039a.get(M) == null) {
                    m5867T().add(new JabberId(M, "bisphone.com", null).m7391e());
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
            if (z) {
                m5872c(true);
            }
        }
    }

    public void onEvent(VCardRefreshEvent vCardRefreshEvent) {
        this.Q.postDelayed(new RunnableVCardRefreshEvent(this, vCardRefreshEvent), 2000);
    }

    public void onEvent(MessageSendEvent messageSendEvent) {
        if (messageSendEvent.m4985a().m4414c().equals(this.az) && NotificationCenter.m6606a().m6632m().equals(this.az)) {
            runOnUiThread(new RunnableMessageSendEvent(this, messageSendEvent));
        }
    }

    public void onEventMainThread(UserStatusChangedEvent userStatusChangedEvent) {
        if (this.az.equals(userStatusChangedEvent.m4945b())) {
            this.aw.m4279c(userStatusChangedEvent.m4944a());
            m5866S();
            m5881j();
        }
    }

    protected void m5881j() {
        if (this.aw == null) {
            finish();
            return;
        }
        if (this.aB) {
            m5780N();
            m5804a(m5868U(), true);
        } else {
            m5827u();
        }
        this.ab = this.aw.m4286f();
        this.ac = null;
        if (getActionBar() != null) {
            getActionBar().setTitle(this.ab);
            getActionBar().setSubtitle(null);
        }
    }

    private String m5868U() {
        if (this.aw.m4283e().equals("DESTROYED") || this.aw.m4283e().equals("DESTROYING")) {
            return getString(2131296472);
        }
        if (this.aw.m4283e().equals("LEAVING") || this.aw.m4283e().equals("LEFT")) {
            return getString(2131296502);
        }
        if (this.aw.m4283e().equals("KICKED")) {
            return getString(2131296501);
        }
        return "";
    }

    private void m5872c(boolean z) {
        if (DisplayDataHandler.f4039a == null) {
            DisplayDataHandler.f4039a = new HashMap();
        }
        if (m5867T().size() > 0) {
            HashMap a = ContactDataSource.m4554a(ContactDataSource.m4553a().m4569b(new ArrayList(m5867T())));
            DisplayDataHandler.f4039a.putAll(a);
            if (z) {
                ContactEntity contactEntity;
                List arrayList = new ArrayList();
                for (String str : m5867T()) {
                    JabberId a2 = JabberId.m7386a(str);
                    if (a2 != null) {
                        Object a3;
                        if (str.contains("@")) {
                            a3 = a2.m7387a();
                        } else {
                            String str2 = str;
                        }
                        if (a.get(a3) == null) {
                            arrayList.add(a2.m7387a());
                            if (DisplayDataHandler.f4039a.get(str) == null) {
                                contactEntity = new ContactEntity();
                                contactEntity.m4197f(a2.m7387a());
                                DisplayDataHandler.f4039a.put(a2.m7387a(), contactEntity);
                            }
                        }
                    }
                }
                for (Entry entry : a.entrySet()) {
                    JabberId jabberId = new JabberId(entry.getKey().toString(), "bisphone.com", null);
                    m5867T().add(jabberId.m7391e());
                    arrayList.add(entry.getKey().toString());
                    if (DisplayDataHandler.f4039a.get(jabberId.m7387a()) == null) {
                        contactEntity = new ContactEntity();
                        contactEntity.m4197f(jabberId.m7391e());
                        DisplayDataHandler.f4039a.put(jabberId.m7387a(), contactEntity);
                    }
                }
                this.Q.post(new RunnableUpdateVCard(arrayList));
            }
        }
    }

    protected void m5879a(String[] strArr) {
        HistoryGroupDataSource.m4695a((Context) this, strArr);
        ConversationGroupDataSource.m4587a().m4614c(this.az);
    }

    protected boolean m5880a(HistoryEntity historyEntity) {
        return historyEntity.m4414c().equals(this.az);
    }

    protected ArrayList<HistoryEntity> m5876a(LoadDirection loadDirection, long j, long j2) {
        Object arrayList = new ArrayList();
        if (this.O == null) {
            this.O = new LoadEarlier();
        }
        if (this.aw == null) {
            finish();
        }
        HistoryGroupDataSource.m4691a().m4710a(this.O, this.az, 40, j, j2, this.aw.m4307s(), loadDirection);
        Cursor b = this.O.m5888b();
        if (b.moveToFirst()) {
            do {
                arrayList.add(0, HistoryGroupDataSource.m4690a(b));
                this.N.add(((HistoryEntity) arrayList.get(0)).m4412b());
                m5867T().add(new JabberId(b.getString(b.getColumnIndex("contact_username")), "bisphone.com", null).m7391e());
            } while (b.moveToNext());
            if (this.O.m5884a() > -1) {
                int a = this.O.m5884a();
                if (a >= arrayList.size()) {
                    a = arrayList.size() - 1;
                }
                arrayList.add(this.O.m5884a() > arrayList.size() ? arrayList.size() : this.O.m5884a(), new HistoryEntity.Builder().m4367b(Long.parseLong(((HistoryEntity) arrayList.get(a)).m4419f())));
                if (arrayList.size() >= 40) {
                    this.ap = false;
                } else {
                    this.ap = true;
                }
            } else if (arrayList.size() < 40 && loadDirection == LoadDirection.upToDown) {
                this.ap = true;
            }
            this.aw.m4268a(0);
            this.aw.m4275b(0);
            m5872c(true);
        }
        b.close();
        if (this.O.m5892c()) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    protected HistoryNormalMessageEntity m5873a(HistoryNormalMessageEntity.Builder builder) {
        builder.m4359a(StringUtil.m7065b());
        builder.m4365b(this.az);
        builder.m4455t(null);
        return builder.m4453a();
    }
}
