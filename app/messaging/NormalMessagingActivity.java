package app.messaging;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import app.Main;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.database.datasource.ContactDataSource;
import app.database.datasource.ConversationNormalDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.NewMessageEvent;
import app.events.vcard.VCardRefreshEvent;
import app.events.view_holder.ViewHolderDeleteEvent;
import app.events.xmpp.ChatStateEvent;
import app.events.xmpp.LastActivityEvent;
import app.events.xmpp.LastActivityEvent.LastActivityListener;
import app.events.xmpp.MessageSendEvent;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.messaging.vh.DisplayDataHandler;
import app.notification.NotificationCenter;
import app.util.CallUtil;
import app.util.DialogsUtil;
import app.util.StringUtil;
import app.util.TimeUtils;
import app.xmpp.ChatStateManager;
import app.xmpp.JabberId;
import app.xmpp.NetworkConnectivityChangeReceiver;
import app.xmpp.NormalMessageManager;
import app.xmpp.VCardHandler;
import app.xmpp.VCardInsertUpdateEntity;
import de.greenrobot.event.EventBus;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jivesoftware.smackx.chatstates.ChatState;
import se.emilsjolander.stickylistheaders.C1128R;

public class NormalMessagingActivity extends RecycleMessagingActivity {
    private String aA;
    private boolean aB;
    private BackStateFromComposing aw;
    private Handler ax;
    private long ay;
    private String az;

    /* renamed from: app.messaging.NormalMessagingActivity.1 */
    class C02551 implements LastActivityListener {
        final /* synthetic */ String f3226a;
        final /* synthetic */ NormalMessagingActivity f3227b;

        C02551(NormalMessagingActivity normalMessagingActivity, String str) {
            this.f3227b = normalMessagingActivity;
            this.f3226a = str;
        }

        public void m5901a(long j) {
            if (!this.f3227b.isFinishing()) {
                this.f3227b.ay = j;
                if (j == 0) {
                    ChatStateManager.m7307a().m7308a(this.f3226a, ChatState.active);
                }
                this.f3227b.Q.post(new RunnableImplementation(this.f3227b, 1));
            }
        }

        public void m5900a() {
        }
    }

    /* renamed from: app.messaging.NormalMessagingActivity.2 */
    class C02562 extends ArrayList<String> {
        final /* synthetic */ NormalMessagingActivity f3228a;

        C02562(NormalMessagingActivity normalMessagingActivity) {
            this.f3228a = normalMessagingActivity;
            add(this.f3228a.az);
        }
    }

    /* renamed from: app.messaging.NormalMessagingActivity.3 */
    /* synthetic */ class C02573 {
        static final /* synthetic */ int[] f3229a;

        static {
            f3229a = new int[ChatState.values().length];
            try {
                f3229a[ChatState.active.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3229a[ChatState.inactive.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3229a[ChatState.composing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3229a[ChatState.gone.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    class BackStateFromComposing extends AsyncTask<Void, Void, Void> {
        private final WeakReference<NormalMessagingActivity> f3230a;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5902a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5903a((Void) obj);
        }

        public BackStateFromComposing(NormalMessagingActivity normalMessagingActivity) {
            this.f3230a = new WeakReference(normalMessagingActivity);
        }

        protected Void m5902a(Void... voidArr) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        protected void m5903a(Void voidR) {
            super.onPostExecute(voidR);
            NormalMessagingActivity normalMessagingActivity = (NormalMessagingActivity) this.f3230a.get();
            if (normalMessagingActivity != null) {
                normalMessagingActivity.m5918a(ChatState.active);
            }
        }
    }

    class RunnableImplementation implements Runnable {
        private final WeakReference<NormalMessagingActivity> f3231a;
        private int f3232b;

        public RunnableImplementation(NormalMessagingActivity normalMessagingActivity, int i) {
            this.f3231a = new WeakReference(normalMessagingActivity);
            this.f3232b = i;
        }

        public void run() {
            switch (this.f3232b) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    m5904a();
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m5905b();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m5906c();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m5907d();
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    m5908e();
                default:
            }
        }

        private void m5904a() {
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = DisplayDataHandler.f4039a;
            if (hashMap2 != null) {
                for (Entry entry : hashMap2.entrySet()) {
                    if (entry.getValue() != null && ((ContactEntity) entry.getValue()).m4210m() != null) {
                        hashMap.put(new JabberId(((ContactEntity) entry.getValue()).m4200g(), "bisphone.com", null).m7391e(), new VCardInsertUpdateEntity(new JabberId(((ContactEntity) entry.getValue()).m4200g(), "bisphone.com", null).m7391e(), false));
                    } else if (entry.getKey() != null) {
                        Object obj = entry.getKey().toString();
                        if (obj != null) {
                            if (!obj.contains("@")) {
                                obj = new JabberId(obj, "bisphone.com", null).m7391e();
                            }
                            hashMap.put(obj, new VCardInsertUpdateEntity(entry.getKey().toString(), true));
                        }
                    }
                }
            }
            if (hashMap.size() > 0) {
                VCardHandler.m7499a().m7502a(hashMap);
            }
        }

        private void m5905b() {
            NormalMessagingActivity normalMessagingActivity = (NormalMessagingActivity) this.f3231a.get();
            if (normalMessagingActivity != null && normalMessagingActivity.getActionBar() != null) {
                normalMessagingActivity.getActionBar().setSubtitle(TimeUtils.m7073b(normalMessagingActivity.ay));
            }
        }

        private void m5906c() {
            NormalMessagingActivity normalMessagingActivity = (NormalMessagingActivity) this.f3231a.get();
            if (normalMessagingActivity != null && normalMessagingActivity.w.getText().toString().length() == 0 && !normalMessagingActivity.isFinishing()) {
                ChatStateManager.m7307a().m7308a(normalMessagingActivity.az, ChatState.active);
            }
        }

        private void m5907d() {
            NormalMessagingActivity normalMessagingActivity = (NormalMessagingActivity) this.f3231a.get();
            if (normalMessagingActivity != null) {
                normalMessagingActivity.m5921c(false);
                if (normalMessagingActivity.aA != null) {
                    ContactEntity b = ContactDataSource.m4553a().m4570b(normalMessagingActivity.aA);
                    if (b != null && b.m4210m() == TYPE.REMOTE) {
                        if (b.m4209l() != null) {
                            normalMessagingActivity.ab = b.m4209l();
                        } else {
                            normalMessagingActivity.ab = "+" + b.m4200g();
                        }
                    }
                }
                normalMessagingActivity.runOnUiThread(new RunnableImplementation(normalMessagingActivity, 4));
            }
        }

        private void m5908e() {
            NormalMessagingActivity normalMessagingActivity = (NormalMessagingActivity) this.f3231a.get();
            if (normalMessagingActivity != null) {
                if (normalMessagingActivity.getActionBar() != null) {
                    normalMessagingActivity.getActionBar().setTitle(normalMessagingActivity.ab);
                }
                normalMessagingActivity.m5820n();
            }
        }
    }

    public NormalMessagingActivity() {
        this.ax = null;
        this.ay = -1;
        this.aB = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.m12779a().m12791a((Object) this);
        m5800a(this.at ? LoadDirection.upToDown : LoadDirection.downToUp, false, getIntent());
        m5931j();
    }

    protected void onResume() {
        super.onResume();
        this.P.m5977f(1);
        NotificationCenter.m6606a().m6628i(this.az);
        NotificationCenter.m6606a().m6624g(this.aA);
    }

    protected void onPause() {
        super.onPause();
        if (ChatStateManager.m7307a().m7309b().get(this.az) != null) {
            ChatStateManager.m7307a().m7308a(this.az, ChatState.inactive);
            ChatStateManager.m7307a().m7309b().remove(this.az);
        }
    }

    protected void onStop() {
        super.onStop();
        long U = m5912U();
        if (this.az != null) {
            ConversationNormalDataSource.m4623a().m4632a(this.aA, U);
            HistoryNormalMessageDataSource.m4726a(this.aA, this.w.getText().toString().trim());
        }
        if (NormalMessageManager.m7447a().m7470f(this.aA) != null) {
            NormalMessageManager.m7447a().m7470f(this.aA).m4163a(0);
            NormalMessageManager.m7447a().m7470f(this.aA).m4170d().m4325a(U);
        }
        if (this.az != null && m5781P() != null && m5781P().size() == 0 && this.an && this.O != null && !this.O.m5894e() && !this.O.m5893d()) {
            ConversationNormalDataSource.m4623a().m4637b(this.aA);
            NormalMessageManager.m7447a().m7452a(this.aA);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        m5927a(intent);
        m5931j();
        m5922f(this.az);
        m5781P().clear();
        m5826t();
        m5800a(LoadDirection.downToUp, false, intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (this.aB) {
            getMenuInflater().inflate(2131820563, menu);
        } else {
            getMenuInflater().inflate(2131820560, menu);
        }
        m5922f(this.az);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        m5770D();
        switch (menuItem.getItemId()) {
            case 2131755647:
                if (!NetworkConnectivityChangeReceiver.m7394a(this)) {
                    DialogsUtil.m7014a(this);
                    break;
                }
                JabberId a = JabberId.m7386a(this.az);
                if (a == null) {
                    Main.f1926a.m5677a("jabber id is null!");
                    break;
                }
                CallUtil.m7005a(a.m7387a(), this, false);
                break;
            case 2131755650:
                Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
                intent.setType("vnd.android.cursor.item/contact");
                intent.putExtra("phone", "+" + this.aA);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void m5932k() {
        super.m5817k();
        m5910S();
    }

    protected HistoryNormalMessageEntity m5923a(Builder builder) {
        builder.m4359a(StringUtil.m7065b());
        builder.m4365b(this.az);
        builder.m4455t(this.aA);
        return builder.m4453a();
    }

    protected void m5928a(CharSequence charSequence, int i, int i2, int i3) {
        super.m5801a(charSequence, i, i2, i3);
        m5917a(charSequence);
    }

    protected void m5931j() {
        if (this.az != null) {
            m5921c(true);
            m5909R();
        }
    }

    private void m5909R() {
        if (this.az != null) {
            try {
                this.aA = this.az.substring(0, this.az.indexOf("@"));
            } catch (ArrayIndexOutOfBoundsException e) {
                this.aA = this.az;
                this.az += "@" + "bisphone.com";
            }
            ContactEntity b = ContactDataSource.m4553a().m4570b(this.aA);
            if (b == null) {
                this.aB = true;
                this.ab = "+" + this.aA;
            } else if (b.m4210m() == TYPE.REMOTE) {
                this.aB = true;
                if (b.m4209l() != null) {
                    this.ab = b.m4209l();
                } else {
                    this.ab = "+" + b.m4200g();
                }
            } else if (b.m4210m() == TYPE.LOCAL) {
                if (b.m4214q().longValue() > 0) {
                    ContactDataSource.m4553a().m4576d(b.m4200g());
                }
                this.ab = b.m4196e();
            }
            if (getActionBar() != null) {
                getActionBar().setTitle(this.ab);
            }
        }
    }

    private void m5921c(boolean z) {
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.az);
        arrayList.add(this.aA);
        DisplayDataHandler.f4039a = ContactDataSource.m4554a(ContactDataSource.m4553a().m4574d(arrayList));
        for (int i = 0; i < arrayList2.size(); i++) {
            JabberId a = JabberId.m7386a((String) arrayList2.get(i));
            if (a != null && DisplayDataHandler.f4039a.get(a.m7387a()) == null) {
                DisplayDataHandler.f4039a.put(arrayList.get(i), null);
            }
        }
        if (z) {
            this.Q.postDelayed(new RunnableImplementation(this, 0), 2000);
        }
    }

    private void m5922f(String str) {
        JabberId a = JabberId.m7386a(str);
        if (a != null) {
            LastActivityEvent lastActivityEvent = new LastActivityEvent(a);
            lastActivityEvent.m4983a(new C02551(this, str));
            EventBus.m12779a().m12795d(lastActivityEvent);
        }
    }

    private void m5918a(ChatState chatState) {
        if (this.aw != null) {
            this.aw.cancel(true);
        }
        this.ay = 0;
        if (getActionBar() != null) {
            switch (C02573.f3229a[chatState.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    getActionBar().setSubtitle(TimeUtils.m7073b(this.ay));
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    getActionBar().setSubtitle(getResources().getString(2131296868));
                    this.aw = new BackStateFromComposing(this);
                    this.aw.execute(new Void[0]);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.ay = 1;
                    getActionBar().setSubtitle(TimeUtils.m7073b(this.ay));
                default:
            }
        }
    }

    private void m5917a(CharSequence charSequence) {
        if (this.ay == 0) {
            if (this.ax == null) {
                this.ax = new Handler();
            }
            Map b = ChatStateManager.m7307a().m7309b();
            if ((charSequence == null || charSequence.length() == 0) && b.get(this.az) != null) {
                this.ax.postDelayed(new RunnableImplementation(this, 2), 2000);
            } else if (b.get(this.az) == ChatState.active || b.get(this.az) == ChatState.composing) {
                ChatStateManager.m7307a().m7308a(this.az, ChatState.composing);
            }
        }
    }

    public void onEventMainThread(MessageSendEvent messageSendEvent) {
        if (!this.az.equals(messageSendEvent.m4985a().m4414c()) || messageSendEvent.m4986b() != 1) {
            return;
        }
        if (m5781P().size() > 0 && ((HistoryEntity) m5781P().get(m5781P().size() - 1)).m4412b().equals(messageSendEvent.m4985a().m4412b())) {
            return;
        }
        if (this.O == null || this.O.m5894e()) {
            m5832z();
            return;
        }
        m5823q();
        HistoryNormalMessageEntity a = new Builder().m4452a(messageSendEvent.m4985a(), this.aA).m4453a();
        m5781P().add(a);
        this.N.add(a.m4412b());
        m5796a(-1, true);
    }

    public void onEventMainThread(NewMessageEvent newMessageEvent) {
        if (newMessageEvent.f2412b.m4414c().equals(this.az)) {
            super.onEventMainThread(newMessageEvent);
            this.ay = 0;
            m5918a(ChatState.active);
        }
    }

    public void onEventMainThread(ChatStateEvent chatStateEvent) {
        if (this.az != null && chatStateEvent.m4978a().m7390d().equals(this.az)) {
            if (chatStateEvent.m4979b() == ChatState.active) {
                ChatStateManager.m7307a().m7308a(this.az, this.w.getText().toString().length() > 0 ? ChatState.composing : ChatState.active);
            } else if (chatStateEvent.m4979b() == ChatState.inactive) {
                ChatStateManager.m7307a().m7309b().remove(this.az);
            }
            m5918a(chatStateEvent.m4979b());
            Main.f1926a.m5685e("Received chat state " + chatStateEvent.m4979b() + " from " + chatStateEvent.m4978a());
        }
    }

    public void onEvent(VCardRefreshEvent vCardRefreshEvent) {
        this.Q.postDelayed(new RunnableImplementation(this, 3), 2000);
    }

    public void onEvent(ViewHolderDeleteEvent viewHolderDeleteEvent) {
        super.onEvent(viewHolderDeleteEvent);
        m5910S();
    }

    private void m5910S() {
        if (!this.K.isEmpty()) {
            ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(this.aA);
            if (f != null) {
                HistoryEntity historyEntity = (HistoryEntity) m5781P().get(this.K.size() - 1);
                f.m4170d().m4330b(historyEntity.m4416d());
                f.m4170d().m4327a(historyEntity.m4412b());
                f.m4170d().m4334d(historyEntity.m4419f());
                f.m4170d().m4326a(historyEntity.m4418e());
                NormalMessageManager.m7447a().m7451a(f);
            }
        }
    }

    protected void m5927a(Intent intent) {
        this.az = intent.getStringExtra("extra_jabber_id");
        this.aA = this.az.substring(0, this.az.indexOf("@"));
        long longExtra = intent.getLongExtra("smt", 0);
        this.au = intent.getLongExtra("smi", 0);
        if (longExtra > 0) {
            this.ad = longExtra;
            this.at = true;
        }
    }

    protected ArrayList<String> m5933l() {
        return new C02562(this);
    }

    protected ArrayList<HistoryEntity> m5925a(long j, String str) {
        ArrayList<HistoryEntity> arrayList = null;
        HistoryNormalMessageEntity a = HistoryNormalMessageDataSource.m4720a().m4733a(str);
        if (a != null) {
            Cursor a2 = HistoryNormalMessageDataSource.m4720a().m4732a(a.m4456M(), Long.parseLong(a.m4419f()), 40);
            if (a2 != null && a2.moveToFirst()) {
                arrayList = new ArrayList();
                do {
                    arrayList.add(HistoryNormalMessageDataSource.m4719a(a2));
                } while (a2.moveToNext());
            }
        }
        return arrayList;
    }

    protected void m5934m() {
        m5811b(HistoryNormalMessageDataSource.m4729b(this.az));
    }

    protected ArrayList<HistoryEntity> m5924a(long j, long j2) {
        return HistoryNormalMessageDataSource.m4720a().m4734a(this.aA, j, j2);
    }

    protected void m5929a(String[] strArr) {
        HistoryNormalMessageDataSource.m4725a((Context) this, strArr);
        ConversationNormalDataSource.m4623a().m4630a(this.az);
    }

    protected boolean m5930a(HistoryEntity historyEntity) {
        return ((HistoryNormalMessageEntity) historyEntity).m4456M().equals(this.aA);
    }

    protected ArrayList<HistoryEntity> m5926a(LoadDirection loadDirection, long j, long j2) {
        Object arrayList = new ArrayList();
        if (this.O == null) {
            this.O = new LoadEarlier();
        }
        if (this.at) {
            this.O.m5885a(-1);
            this.O.m5889b(0);
            this.O.m5886a(HistoryNormalMessageDataSource.m4720a().m4732a(this.aA, j, 40));
            this.O.m5891c(true);
            this.O.m5890b(true);
            this.O.m5887a(true);
        } else {
            HistoryNormalMessageDataSource.m4720a().m4739a(this.O, this.az, 40, j, j2, m5911T(), loadDirection);
        }
        Cursor b = this.O.m5888b();
        if (b.moveToFirst()) {
            do {
                arrayList.add(0, HistoryNormalMessageDataSource.m4719a(b));
                this.N.add(((HistoryEntity) arrayList.get(0)).m4412b());
            } while (b.moveToNext());
            if (this.O.m5884a() > -1) {
                int a = this.O.m5884a();
                if (a >= arrayList.size()) {
                    a = arrayList.size() - 1;
                }
                if (a == arrayList.size()) {
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
            ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(this.aA);
            if (f != null) {
                f.m4163a(0);
            }
            b.close();
        }
        if (this.O.m5892c()) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    private long m5911T() {
        ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(this.aA);
        if (f == null) {
            return 0;
        }
        return f.m4170d().m4338h();
    }

    private long m5912U() {
        if (this.O != null && !this.O.m5894e() && m5781P().size() > 0) {
            return Long.parseLong(((HistoryEntity) m5781P().get(m5781P().size() - 1)).m4419f());
        }
        ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(this.aA);
        if (f != null) {
            return Long.parseLong(f.m4170d().m4336f());
        }
        HistoryEntity d = HistoryNormalMessageDataSource.m4720a().m4747d(this.aA);
        if (d == null) {
            return 0;
        }
        return Long.parseLong(d.m4419f());
    }
}
