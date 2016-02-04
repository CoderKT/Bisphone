package app.messaging;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import app.Main;
import app.common.entity.ChannelEntity;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.Builder;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryEntityDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.NewMessageEvent;
import app.events.channel.ChannelEvent;
import app.events.channel.ChannelEvent.Action;
import app.events.channel.ChannelEvent.ChannelEventListener;
import app.events.xmpp.MessageSendEvent;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.messaging.channel.ChannelCommunication;
import app.messaging.channel.ChannelDetailActivity;
import app.messaging.channel.ChannelDetailAdapterModel;
import app.notification.NotificationCenter;
import app.util.StringUtil;
import app.xmpp.packet.channel.ChannelElement;
import de.greenrobot.event.EventBus;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ChannelMessagingActivity extends RecycleMessagingActivity {
    private String aw;
    private ChannelEntity ax;
    private boolean ay;

    /* renamed from: app.messaging.ChannelMessagingActivity.1 */
    class C02511 extends ArrayList<String> {
        final /* synthetic */ ChannelMessagingActivity f3208a;

        C02511(ChannelMessagingActivity channelMessagingActivity) {
            this.f3208a = channelMessagingActivity;
            add(this.f3208a.aw);
        }
    }

    /* renamed from: app.messaging.ChannelMessagingActivity.2 */
    class C02532 implements ChannelEventListener {
        final /* synthetic */ ChannelMessagingActivity f3211a;

        /* renamed from: app.messaging.ChannelMessagingActivity.2.1 */
        class C02521 extends ArrayList<ChannelElement> {
            final /* synthetic */ ChannelElement f3209a;
            final /* synthetic */ C02532 f3210b;

            C02521(C02532 c02532, ChannelElement channelElement) {
                this.f3210b = c02532;
                this.f3209a = channelElement;
                add(this.f3209a);
            }
        }

        C02532(ChannelMessagingActivity channelMessagingActivity) {
            this.f3211a = channelMessagingActivity;
        }

        public void m5848a(ChannelElement channelElement, Long l) {
            ChannelDataSource.m4543a(new C02521(this, channelElement));
            this.f3211a.runOnUiThread(new RunnableUpdateActionbarTitle(this.f3211a, channelElement));
        }

        public void m5847a() {
            Main.f1926a.m5683d("failed get info of channel");
        }
    }

    class RunnableUpdateActionbarTitle implements Runnable {
        final WeakReference<ChannelMessagingActivity> f3212a;
        final ChannelElement f3213b;

        public RunnableUpdateActionbarTitle(ChannelMessagingActivity channelMessagingActivity, ChannelElement channelElement) {
            this.f3212a = new WeakReference(channelMessagingActivity);
            this.f3213b = channelElement;
        }

        public void run() {
            ChannelMessagingActivity channelMessagingActivity = (ChannelMessagingActivity) this.f3212a.get();
            if (channelMessagingActivity != null && !channelMessagingActivity.isFinishing()) {
                ActionBar actionBar = channelMessagingActivity.getActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(this.f3213b.m7551b());
                    actionBar.setSubtitle(channelMessagingActivity.m5852a((double) this.f3213b.m7560g()));
                }
                if (this.f3213b.m7556d()) {
                    channelMessagingActivity.t.setVisibility(8);
                }
            }
        }
    }

    public ChannelMessagingActivity() {
        this.ay = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.m12779a().m12791a((Object) this);
        this.ax = ChannelDataSource.m4537a(this.aw);
        m5800a(LoadDirection.downToUp, false, getIntent());
        m5862j();
    }

    protected void onResume() {
        super.onResume();
        this.P.m5977f(3);
        NotificationCenter.m6606a().m6628i(this.aw);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 30017:
                    this.ay = intent.getBooleanExtra("extra_channel_read_only", false);
                    break;
            }
            m5862j();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820562, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755649:
                Intent intent = new Intent(this, ChannelDetailActivity.class);
                ChannelEntity a = ChannelDataSource.m4537a(this.aw);
                ChannelCommunication.getInstance().m6096a(this.aw, Boolean.valueOf(a.m4157g()));
                intent.putExtra(DataPacketExtension.ELEMENT, new ChannelDetailAdapterModel(ChannelDataSource.m4539a(a), a.m4157g()));
                intent.putExtra("messaging", true);
                startActivityForResult(intent, 30017);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onStop() {
        super.onStop();
        ChannelDataSource.m4542a(this.aw, m5850S());
        HistoryChannelDataSource.m4672a(this.aw, this.w.getText().toString().trim());
    }

    private long m5849R() {
        if (this.ax == null) {
            return 0;
        }
        return this.ax.m4161k();
    }

    private long m5850S() {
        if (this.O != null && !this.O.m5894e() && m5781P().size() > 0) {
            return Long.parseLong(((HistoryEntity) m5781P().get(m5781P().size() - 1)).m4419f());
        }
        HistoryEntity d = HistoryChannelDataSource.m4667a().m4686d(this.aw);
        return d == null ? 0 : Long.parseLong(d.m4419f());
    }

    protected void m5859a(Intent intent) {
        this.aw = intent.getStringExtra("extra_channel_jid");
        this.ay = intent.getBooleanExtra("extra_channel_read_only", false);
    }

    protected ArrayList<String> m5863l() {
        return new C02511(this);
    }

    protected ArrayList<HistoryEntity> m5857a(long j, String str) {
        ArrayList<HistoryEntity> arrayList = null;
        HistoryEntity a = HistoryChannelDataSource.m4667a().m4679a(str);
        if (a != null) {
            Cursor a2 = HistoryChannelDataSource.m4667a().m4678a(a.m4414c(), Long.parseLong(this.V.m4419f()), 40);
            if (a2 != null && a2.moveToFirst()) {
                arrayList = new ArrayList();
                do {
                    arrayList.add(HistoryNormalMessageDataSource.m4719a(a2));
                } while (a2.moveToNext());
            }
        }
        return arrayList;
    }

    protected void m5864m() {
        m5811b(HistoryChannelDataSource.m4676c(this.aw));
    }

    protected ArrayList<HistoryEntity> m5856a(long j, long j2) {
        return HistoryChannelDataSource.m4667a().m4681a(this.aw, j, j2);
    }

    protected void m5862j() {
        ChannelEntity a = ChannelDataSource.m4537a(this.aw);
        if (a == null) {
            finish();
            return;
        }
        if (this.ay) {
            m5804a("", false);
        } else {
            m5827u();
        }
        m5851T();
        this.ab = a.m4152b();
    }

    protected void m5860a(String[] strArr) {
        HistoryChannelDataSource.m4671a((Context) this, strArr);
    }

    protected boolean m5861a(HistoryEntity historyEntity) {
        return historyEntity.m4414c().equals(this.aw);
    }

    public void onEventMainThread(MessageSendEvent messageSendEvent) {
        if (messageSendEvent.m4986b() != 3 || !this.aw.equals(messageSendEvent.m4985a().m4414c()) || !NotificationCenter.m6606a().m6632m().equals(this.aw)) {
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
        m5781P().add(messageSendEvent.m4985a());
        this.N.add(messageSendEvent.m4985a().m4412b());
        m5796a(-1, true);
    }

    public void onEventMainThread(NewMessageEvent newMessageEvent) {
        if (newMessageEvent.f2412b.m4414c().equals(this.aw)) {
            super.onEventMainThread(newMessageEvent);
        }
    }

    private void m5851T() {
        ChannelEvent channelEvent = new ChannelEvent(this.aw, Action.info, false);
        channelEvent.m4852a(new C02532(this));
        EventBus.m12779a().m12795d(channelEvent);
    }

    private String m5852a(double d) {
        if (d < 1000.0d) {
            return String.format(getResources().getString(2131296385), new Object[]{new DecimalFormat("##").format(d)});
        }
        double d2 = d / 1000.0d;
        return String.format(getResources().getString(2131296386), new Object[]{new DecimalFormat("##.#").format(d2)});
    }

    protected ArrayList<HistoryEntity> m5858a(LoadDirection loadDirection, long j, long j2) {
        Object arrayList = new ArrayList();
        if (this.O == null) {
            this.O = new LoadEarlier();
        }
        HistoryChannelDataSource.m4667a().m4684a(this.O, this.aw, 40, j, j2, m5849R(), loadDirection);
        Cursor b = this.O.m5888b();
        if (b.moveToFirst()) {
            do {
                arrayList.add(0, HistoryEntityDataSource.m4687a(b));
                this.N.add(((HistoryEntity) arrayList.get(0)).m4412b());
            } while (b.moveToNext());
            if (this.O.m5884a() > -1) {
                int a = this.O.m5884a();
                if (a >= arrayList.size()) {
                    a = arrayList.size() - 1;
                }
                arrayList.add(this.O.m5884a() > arrayList.size() ? arrayList.size() : this.O.m5884a(), new Builder().m4367b(Long.parseLong(((HistoryEntity) arrayList.get(a)).m4419f())));
                if (arrayList.size() >= 40) {
                    this.ap = false;
                } else {
                    this.ap = true;
                }
            } else if (arrayList.size() < 40) {
                this.ap = true;
            }
        }
        b.close();
        if (this.O.m5892c()) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    protected HistoryNormalMessageEntity m5855a(HistoryNormalMessageEntity.Builder builder) {
        builder.m4359a(StringUtil.m7065b());
        builder.m4365b(this.aw);
        builder.m4455t(null);
        return builder.m4453a();
    }
}
