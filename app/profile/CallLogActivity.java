package app.profile;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.CallLogEntity;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.database.datasource.CallsDataSource;
import app.events.xmpp.SendPacketEvent;
import app.messaging.NormalMessagingActivity;
import app.messaging.vh.DisplayDataHandler;
import app.util.CallUtil;
import app.util.DialogsUtil;
import app.util.StringUtil;
import app.util.XMPPUtils;
import app.view.UnScrollableListView;
import app.xmpp.JabberId;
import app.xmpp.NetworkConnectivityChangeReceiver;
import app.xmpp.VCardHandler;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import parallaxedScrollView.ParallaxScrollView;

public class CallLogActivity extends BaseActivity {
    private ArrayList<CallLogHistoryEntity> f4199A;
    private CallHistoryProfileAdapter f4200B;
    private ContactEntity f4201C;
    private String f4202D;
    private String f4203E;
    private String f4204F;
    private boolean f4205G;
    private boolean f4206H;
    ParallaxScrollView f4207o;
    UnScrollableListView f4208p;
    ImageView f4209q;
    TextView f4210r;
    TextView f4211s;
    ProgressBar f4212t;
    View f4213u;
    View f4214v;
    View f4215w;
    View f4216x;
    View f4217y;
    private CallHistoryLoader f4218z;

    /* renamed from: app.profile.CallLogActivity.1 */
    class C04361 implements OnClickListener {
        final /* synthetic */ CallLogActivity f4193a;

        C04361(CallLogActivity callLogActivity) {
            this.f4193a = callLogActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.profile.CallLogActivity.2 */
    class C04372 implements OnClickListener {
        final /* synthetic */ CallLogActivity f4194a;

        C04372(CallLogActivity callLogActivity) {
            this.f4194a = callLogActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Builder builder = new Builder();
            builder.m4356a(Type.TEXT).m4369c(this.f4194a.getResources().getString(2131296656)).m4353a(DeliveryStatus.SENDING).m4372d(System.currentTimeMillis() + "");
            builder.m4455t(this.f4194a.f4202D);
            builder.m4359a(StringUtil.m7065b());
            builder.m4365b(new JabberId(this.f4194a.f4202D, "bisphone.com", null).m7391e());
            builder.m4455t(this.f4194a.f4202D);
            EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7091a(builder.m4453a(), 1)));
        }
    }

    class CallHistoryLoader extends AsyncTask<Void, Void, ArrayList<CallLogHistoryEntity>> {
        private final WeakReference<CallLogActivity> f4195a;
        private String f4196b;
        private String f4197c;
        private String f4198d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6673a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m6674a((ArrayList) obj);
        }

        public CallHistoryLoader(CallLogActivity callLogActivity, String str, String str2, String str3) {
            this.f4195a = new WeakReference(callLogActivity);
            this.f4196b = str;
            this.f4197c = str2;
            this.f4198d = str3;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            CallLogActivity callLogActivity = (CallLogActivity) this.f4195a.get();
            if (callLogActivity != null) {
                callLogActivity.f4212t.setVisibility(0);
            }
        }

        protected ArrayList<CallLogHistoryEntity> m6673a(Void... voidArr) {
            CallLogActivity callLogActivity = (CallLogActivity) this.f4195a.get();
            if (callLogActivity == null) {
                return new ArrayList();
            }
            callLogActivity.f4201C = DisplayDataHandler.m6535a(this.f4196b);
            return CallsDataSource.m4527a().m4531a(this.f4196b, this.f4197c, this.f4198d);
        }

        protected void m6674a(ArrayList<CallLogHistoryEntity> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null && !arrayList.isEmpty()) {
                CallLogActivity callLogActivity = (CallLogActivity) this.f4195a.get();
                if (callLogActivity != null) {
                    callLogActivity.f4212t.setVisibility(8);
                    callLogActivity.f4199A = arrayList;
                    callLogActivity.f4206H = true;
                    callLogActivity.m6686r();
                    callLogActivity.m6685q();
                    callLogActivity.m6687s();
                }
            }
        }
    }

    public CallLogActivity() {
        this.f4206H = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(2130903070);
        ButterKnife.m7741a((Activity) this);
        m6683o();
        m6684p();
    }

    protected void onResume() {
        super.onResume();
        if (this.f4205G) {
            this.f4204F = String.valueOf(System.currentTimeMillis() * 1000);
        }
        this.f4218z = new CallHistoryLoader(this, this.f4202D, this.f4203E, this.f4204F);
        this.f4218z.execute(new Void[0]);
    }

    private void m6683o() {
        this.f4202D = getIntent().getStringExtra("username");
        this.f4203E = getIntent().getStringExtra("first_timestamp");
        this.f4204F = getIntent().getStringExtra("last_timestamp");
        this.f4205G = getIntent().getBooleanExtra("allow_update", false);
    }

    private void m6684p() {
        this.f4200B = new CallHistoryProfileAdapter(this);
        this.f4208p.setAdapter(this.f4200B);
    }

    private void m6685q() {
        this.f4200B.m6667a(this.f4199A);
        this.f4200B.notifyDataSetChanged();
        m6678a(this.f4208p);
        this.f4207o.fullScroll(33);
    }

    private void m6686r() {
        setTitle(getString(2131296355));
        this.f4208p.setClickable(false);
        this.f4208p.setFocusable(false);
        this.f4208p.setDividerHeight(0);
        this.f4211s.setText("+" + this.f4202D);
        if (this.f4201C != null) {
            if (this.f4201C.m4210m().equals(TYPE.LOCAL)) {
                this.f4210r.setText(this.f4201C.m4196e());
            } else if (this.f4201C.m4209l() != null) {
                this.f4210r.setText(this.f4201C.m4209l());
            } else {
                this.f4210r.setText("");
            }
            String n = this.f4201C.m4211n();
            CustomImageLoader.m4009a().m4020a(this.f4209q, this.f4201C.m4212o(), n, 2130837592);
        } else {
            this.f4210r.setText("");
            this.f4209q.setImageDrawable(getResources().getDrawable(2130837592));
        }
        VCardHandler.m7499a().m7507d(new JabberId(this.f4202D, "bisphone.com", null).m7391e());
    }

    private void m6687s() {
        this.f4217y.setVisibility(8);
        if (this.f4201C == null) {
            this.f4213u.setVisibility(0);
            this.f4214v.setVisibility(8);
            this.f4216x.setVisibility(8);
            this.f4215w.setVisibility(0);
            this.f4217y.setVisibility(8);
        } else if (this.f4201C.m4210m() == TYPE.LOCAL) {
            if (((CallLogHistoryEntity) this.f4199A.get(0)).m4117b().m4236c()) {
                this.f4213u.setVisibility(8);
                this.f4214v.setVisibility(0);
                this.f4216x.setVisibility(0);
                this.f4215w.setVisibility(0);
                this.f4217y.setVisibility(8);
                return;
            }
            this.f4213u.setVisibility(0);
            this.f4214v.setVisibility(8);
            this.f4216x.setVisibility(8);
            this.f4215w.setVisibility(0);
            this.f4217y.setVisibility(8);
        } else if (this.f4201C.m4210m() == TYPE.REMOTE && this.f4201C.m4209l() != null) {
            this.f4213u.setVisibility(8);
            this.f4214v.setVisibility(0);
            this.f4216x.setVisibility(0);
            this.f4215w.setVisibility(0);
            this.f4217y.setVisibility(8);
        } else if (m6688t()) {
            this.f4213u.setVisibility(8);
            this.f4214v.setVisibility(0);
            this.f4216x.setVisibility(0);
            this.f4215w.setVisibility(0);
            this.f4217y.setVisibility(8);
        } else {
            this.f4213u.setVisibility(0);
            this.f4214v.setVisibility(8);
            this.f4216x.setVisibility(8);
            this.f4215w.setVisibility(0);
            this.f4217y.setVisibility(8);
        }
    }

    private boolean m6688t() {
        Iterator it = this.f4199A.iterator();
        while (it.hasNext()) {
            if (CallLogEntity.Type.incoming.equals(((CallLogHistoryEntity) it.next()).m4120e().m4109c())) {
                return true;
            }
        }
        return false;
    }

    void m6689j() {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + this.f4202D));
        intent.putExtra("sms_body", getString(2131296653));
        startActivity(Intent.createChooser(intent, "Select Application"));
    }

    public void m6690k() {
        if (NetworkConnectivityChangeReceiver.m7394a(this)) {
            CallUtil.m7005a(this.f4202D, this, false);
        } else {
            DialogsUtil.m7014a(this);
        }
    }

    public void m6691l() {
        Intent intent = new Intent(this, NormalMessagingActivity.class);
        intent.putExtra("extra_jabber_id", new JabberId(this.f4202D, "bisphone.com", null).m7391e());
        startActivity(intent);
    }

    public void m6692m() {
        String g = ((CallLogHistoryEntity) this.f4199A.get(0)).m4117b().m4240g() == null ? "unknown" : ((CallLogHistoryEntity) this.f4199A.get(0)).m4117b().m4240g();
        new AlertDialog.Builder(this, 2131558538).m1980a(getResources().getString(2131296647)).m1986b(String.format(getResources().getString(2131296646), new Object[]{g, g})).m1975a(2131296645, new C04372(this)).m1985b(17039360, new C04361(this)).m1989c(2130837731).m1992c();
    }

    public void m6693n() {
        if (NetworkConnectivityChangeReceiver.m7394a(this)) {
            CallUtil.m7005a(this.f4202D, this, true);
        } else {
            DialogsUtil.m7014a(this);
        }
    }

    private void m6678a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(listView.getWidth(), 0);
            View view = null;
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                view = adapter.getView(i2, view, listView);
                if (i2 == 0) {
                    view.setLayoutParams(new LayoutParams(makeMeasureSpec, -2));
                }
                view.measure(makeMeasureSpec, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
            listView.requestLayout();
        }
    }
}
