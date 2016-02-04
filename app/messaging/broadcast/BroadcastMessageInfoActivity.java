package app.messaging.broadcast;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ProgressBar;
import app.common.BaseActivity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.xmpp.MessageSendStatusEvent;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;

public class BroadcastMessageInfoActivity extends BaseActivity {
    ExpandableListView f3318o;
    private List<BroadcastEntityStatusModel> f3319p;
    private List<BroadcastEntityStatusModel> f3320q;
    private DeliveryStatus f3321r;
    private String f3322s;
    private BroadcastMessageInfoExpandableAdapter f3323t;
    private ProgressBar f3324u;
    private boolean f3325v;

    /* renamed from: app.messaging.broadcast.BroadcastMessageInfoActivity.1 */
    class C02681 implements OnGroupClickListener {
        final /* synthetic */ BroadcastMessageInfoActivity f3316a;

        C02681(BroadcastMessageInfoActivity broadcastMessageInfoActivity) {
            this.f3316a = broadcastMessageInfoActivity;
        }

        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            return true;
        }
    }

    class GetEntityStatus extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ BroadcastMessageInfoActivity f3317a;

        GetEntityStatus(BroadcastMessageInfoActivity broadcastMessageInfoActivity) {
            this.f3317a = broadcastMessageInfoActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5989a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5990a((Void) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f3317a.f3324u.setVisibility(0);
            this.f3317a.f3325v = false;
            this.f3317a.findViewById(2131755105).setVisibility(8);
        }

        protected Void m5989a(Void... voidArr) {
            List e = HistoryNormalMessageDataSource.m4720a().m4748e(this.f3317a.f3322s);
            if (e == null || (e.size() == 0 && this.f3317a.f3321r != DeliveryStatus.SENDING)) {
                this.f3317a.f3325v = true;
            } else {
                for (int i = 0; i < e.size(); i++) {
                    if (((BroadcastEntityStatusModel) e.get(i)).m5988b() == DeliveryStatus.SENT) {
                        this.f3317a.f3319p.add(e.get(i));
                    } else if (((BroadcastEntityStatusModel) e.get(i)).m5988b() == DeliveryStatus.DELIVERED) {
                        this.f3317a.f3320q.add(e.get(i));
                    }
                }
            }
            return null;
        }

        protected void m5990a(Void voidR) {
            super.onPostExecute(voidR);
            this.f3317a.f3324u.setVisibility(8);
            this.f3317a.m6000k();
            if (this.f3317a.f3325v) {
                this.f3317a.findViewById(2131755105).setVisibility(0);
                this.f3317a.f3318o.setVisibility(8);
            }
        }
    }

    public BroadcastMessageInfoActivity() {
        this.f3321r = DeliveryStatus.SENDING;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903067);
        EventBus.m12779a().m12791a((Object) this);
        m6001l();
        m5999j();
        new GetEntityStatus(this).execute(new Void[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.m12779a().m12794c(this);
    }

    private void m5999j() {
        this.f3320q = new ArrayList();
        this.f3319p = new ArrayList();
        setTitle(2131296348);
    }

    private void m6000k() {
        this.f3323t = new BroadcastMessageInfoExpandableAdapter(this, this.f3319p, this.f3320q);
        this.f3318o.setAdapter(this.f3323t);
        this.f3318o.expandGroup(0);
        this.f3318o.expandGroup(1);
    }

    protected void m6002f() {
        this.f3318o = (ExpandableListView) findViewById(2131755103);
        this.f3324u = (ProgressBar) findViewById(2131755104);
        this.f3318o.setOnGroupClickListener(new C02681(this));
    }

    public void onEventMainThread(MessageSendStatusEvent messageSendStatusEvent) {
        if (messageSendStatusEvent.m4987a().contains(this.f3322s) && messageSendStatusEvent.m4988b().equals(DeliveryStatus.DELIVERED)) {
            String substring = messageSendStatusEvent.m4987a().substring(messageSendStatusEvent.m4987a().indexOf("_") + 1);
            for (int i = 0; i < this.f3319p.size(); i++) {
                if (substring.equals(((BroadcastEntityStatusModel) this.f3319p.get(i)).m5987a().m4234b())) {
                    this.f3320q.add(this.f3319p.get(i));
                    this.f3319p.remove(i);
                    this.f3323t.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    private void m6001l() {
        this.f3322s = getIntent().getStringExtra("message_id");
        int intExtra = getIntent().getIntExtra("message_delivery_status", -1);
        if (intExtra >= 0 && intExtra < DeliveryStatus.values().length) {
            this.f3321r = DeliveryStatus.values()[intExtra];
        }
    }
}
