package app.messaging;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import app.account.AccountManager;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.BroadcastUsersDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.NewMessageEvent;
import app.events.view_holder.ViewHolderDeleteEvent;
import app.events.xmpp.MessageSendEvent;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.messaging.broadcast.BroadcastSettingActivity;
import app.util.StringUtil;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageHandler;
import app.xmpp.NormalMessageManager;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collections;

public class BroadcastMessagingActivity extends RecycleMessagingActivity {
    private ArrayList<String> aw;
    private String ax;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.m12779a().m12791a((Object) this);
        this.aw = new ArrayList();
        NormalMessageManager.m7447a().m7475h(this.ax);
        m5800a(LoadDirection.downToUp, false, getIntent());
        m5843j();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        m5840a(intent);
        m5843j();
        m5781P().clear();
        m5826t();
        m5800a(LoadDirection.downToUp, false, intent);
    }

    protected void onResume() {
        super.onResume();
        this.aw = NormalMessageManager.m7447a().m7474h();
        this.P.m5977f(4);
        if (getActionBar() != null) {
            getActionBar().setTitle(NormalMessageManager.m7447a().m7472g(this.ax).m4173g());
            getActionBar().setSubtitle(String.format(getResources().getString(2131296344), new Object[]{Integer.valueOf(this.aw.size())}));
        }
    }

    protected void onStop() {
        super.onStop();
        HistoryNormalMessageDataSource.m4726a(this.ax, this.w.getText().toString().trim());
    }

    protected void onDestroy() {
        super.onDestroy();
        NormalMessageManager.m7447a().m7475h(null);
        NormalMessageManager.m7447a().m7460b(null);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(2131820549, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755634:
                Intent intent = new Intent(this, BroadcastSettingActivity.class);
                intent.putStringArrayListExtra("broadcast_username_key", this.aw);
                intent.putExtra("broadcast_id_key", this.ax);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void m5843j() {
        this.aw = BroadcastUsersDataSource.m4519a().m4521a(this.ax);
        NormalMessageManager.m7447a().m7460b(this.aw);
        this.ab = NormalMessageManager.m7447a().m7472g(this.ax).m4173g();
        if (getActionBar() != null) {
            getActionBar().setTitle(this.ab);
            getActionBar().setSubtitle(String.format(getResources().getString(2131296344), new Object[]{Integer.valueOf(this.aw.size())}));
        }
    }

    protected void m5839a(int i, int i2) {
        super.m5794a(i, i2);
    }

    protected void m5844k() {
        super.m5817k();
        m5833R();
    }

    public void onEventMainThread(MessageSendEvent messageSendEvent) {
        if (messageSendEvent.m4986b() == 4 && (messageSendEvent.m4985a() instanceof HistoryNormalMessageEntity)) {
            JabberId a = JabberId.m7386a(messageSendEvent.m4985a().m4414c());
            if (a != null && a.m7387a().equals(this.ax)) {
                m5823q();
                HistoryNormalMessageEntity a2 = new Builder().m4452a(messageSendEvent.m4985a(), this.ax).m4453a();
                m5781P().add(a2);
                this.N.add(a2.m4412b());
                m5796a(-1, true);
            }
        }
    }

    public void onEventMainThread(NewMessageEvent newMessageEvent) {
    }

    public void onEvent(ViewHolderDeleteEvent viewHolderDeleteEvent) {
        super.onEvent(viewHolderDeleteEvent);
        m5833R();
    }

    private void m5833R() {
        if (this.K.isEmpty()) {
            NormalMessageManager.m7447a().m7472g(this.ax).m4170d().m4330b(getString(2131296599));
        } else {
            NormalMessageHandler.m7415a().m7436a((HistoryNormalMessageEntity) this.K.get(this.K.size() - 1), this.ax);
        }
    }

    protected void m5840a(Intent intent) {
        this.ax = intent.getStringExtra("extra_broadcast_id");
        NormalMessageManager.m7447a().m7475h(this.ax);
    }

    protected ArrayList<String> m5845l() {
        return this.aw;
    }

    protected ArrayList<HistoryEntity> m5837a(long j, String str) {
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

    protected void m5846m() {
        m5811b(HistoryNormalMessageDataSource.m4730c(this.ax));
    }

    protected ArrayList<HistoryEntity> m5836a(long j, long j2) {
        return HistoryNormalMessageDataSource.m4720a().m4734a(this.ax, j, j2);
    }

    protected HistoryNormalMessageEntity m5835a(Builder builder) {
        builder.m4359a(StringUtil.m7065b());
        builder.m4455t("");
        builder.m4365b(new JabberId(AccountManager.m3934a().m3937c(), "broadcast.bisphone.com", null).m7391e());
        return builder.m4453a();
    }

    protected boolean m5842a(HistoryEntity historyEntity) {
        return ((HistoryNormalMessageEntity) historyEntity).m4456M().equals(this.ax);
    }

    protected ArrayList<HistoryEntity> m5838a(LoadDirection loadDirection, long j, long j2) {
        if (this.O == null) {
            this.O = new LoadEarlier();
        }
        Object arrayList = new ArrayList();
        HistoryNormalMessageDataSource.m4720a().m4739a(this.O, this.ax, 40, j, j2, m5834S(), loadDirection);
        Cursor b = this.O.m5888b();
        if (b.moveToFirst()) {
            do {
                arrayList.add(0, HistoryNormalMessageDataSource.m4719a(b));
                this.N.add(((HistoryEntity) arrayList.get(0)).m4412b());
            } while (b.moveToNext());
            b.close();
        }
        if (arrayList.size() < 40 && loadDirection == LoadDirection.upToDown) {
            this.ap = true;
        }
        if (this.O.m5892c()) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    private long m5834S() {
        ChatHistoryEntity g = NormalMessageManager.m7447a().m7472g(this.ax);
        if (g == null) {
            return 0;
        }
        return (long) g.m4170d().m4339i();
    }

    protected void m5841a(String[] strArr) {
        HistoryNormalMessageDataSource.m4720a().m4743a(strArr);
        BroadcastListDataSource.m4504a().m4514b(this.ax);
    }
}
