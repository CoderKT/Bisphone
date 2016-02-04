package app.setting;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import app.Main;
import app.common.BaseActivity;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.notification.NotificationCenter;
import butterknife.ButterKnife;
import java.util.Calendar;
import se.emilsjolander.stickylistheaders.C1128R;

public class CleanMessageActivity extends BaseActivity implements OnClickListener {
    private Dialog f4323o;
    private ProgressDialog f4324p;
    private CleanCacheMode f4325q;

    /* renamed from: app.setting.CleanMessageActivity.1 */
    /* synthetic */ class C04521 {
        static final /* synthetic */ int[] f4320a;

        static {
            f4320a = new int[CleanCacheMode.values().length];
            try {
                f4320a[CleanCacheMode.ONE_TO_ONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4320a[CleanCacheMode.GROUP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4320a[CleanCacheMode.CHANNEL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4320a[CleanCacheMode.BROADCAST.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4320a[CleanCacheMode.ALL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    class DeleteCacheTask extends AsyncTask<Long, Void, Integer> {
        boolean f4321a;
        final /* synthetic */ CleanMessageActivity f4322b;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6760a((Long[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m6761a((Integer) obj);
        }

        public DeleteCacheTask(CleanMessageActivity cleanMessageActivity, boolean z) {
            this.f4322b = cleanMessageActivity;
            this.f4321a = z;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f4322b.f4324p = ProgressDialog.show(this.f4322b, this.f4322b.getString(2131296413), this.f4322b.getString(2131296644), true);
        }

        protected Integer m6760a(Long... lArr) {
            switch (C04521.f4320a[this.f4322b.f4325q.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    HistoryNormalMessageDataSource.m4731d(lArr[0].longValue());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    HistoryGroupDataSource.m4699b(lArr[0].longValue());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    HistoryChannelDataSource.m4675b(lArr[0].longValue());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    HistoryGroupDataSource.m4699b(lArr[0].longValue());
                    HistoryChannelDataSource.m4675b(lArr[0].longValue());
                    HistoryNormalMessageDataSource.m4731d(lArr[0].longValue());
                    break;
            }
            return Integer.valueOf(this.f4322b.m6762a(lArr[0].longValue(), this.f4321a));
        }

        protected void m6761a(Integer num) {
            super.onPostExecute(num);
            this.f4322b.f4324p.dismiss();
            if (num.intValue() == 0) {
                Main.m3905a(this.f4322b.getString(2131296615));
            } else if (num.intValue() > 1) {
                Main.m3905a(num + " " + this.f4322b.getString(2131296616));
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903073);
        setTitle(getString(2131296721));
        ButterKnife.m7741a((Activity) this);
    }

    void m6768j() {
        this.f4325q = CleanCacheMode.ONE_TO_ONE;
        m6767o();
    }

    void m6769k() {
        this.f4325q = CleanCacheMode.GROUP;
        m6767o();
    }

    void m6770l() {
        this.f4325q = CleanCacheMode.CHANNEL;
        m6767o();
    }

    void m6771m() {
        this.f4325q = CleanCacheMode.BROADCAST;
        m6767o();
    }

    void m6772n() {
        this.f4325q = CleanCacheMode.ALL;
        m6767o();
    }

    private void m6767o() {
        this.f4323o = new Dialog(this);
        this.f4323o.requestWindowFeature(1);
        this.f4323o.setContentView(2130903120);
        this.f4323o.getWindow().setBackgroundDrawableResource(17170445);
        this.f4323o.findViewById(2131755315).setOnClickListener(this);
        this.f4323o.findViewById(2131755312).setOnClickListener(this);
        this.f4323o.findViewById(2131755313).setOnClickListener(this);
        this.f4323o.findViewById(2131755314).setOnClickListener(this);
        this.f4323o.show();
    }

    public void onClick(View view) {
        boolean z;
        Calendar instance = Calendar.getInstance();
        Main.f1926a.m5683d(instance.getTimeInMillis() + "");
        switch (view.getId()) {
            case 2131755312:
                instance.add(6, -14);
                break;
            case 2131755313:
                instance.add(2, -1);
                z = false;
                break;
            case 2131755314:
                instance.add(2, -6);
                z = false;
                break;
            case 2131755315:
                z = true;
                break;
        }
        z = false;
        this.f4323o.dismiss();
        new DeleteCacheTask(this, z).execute(new Long[]{Long.valueOf(instance.getTimeInMillis() * 1000)});
    }

    private int m6762a(long j, boolean z) {
        switch (C04521.f4320a[this.f4325q.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                NotificationCenter.m6606a().m6630k();
                return HistoryNormalMessageDataSource.m4720a().m4744b(j);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                NotificationCenter.m6606a().m6631l();
                return HistoryGroupDataSource.m4691a().m4713c(j);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return ChannelDataSource.m4538a().m4551a(j);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return HistoryNormalMessageDataSource.m4720a().m4746c(j);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                NotificationCenter.m6606a().m6630k();
                NotificationCenter.m6606a().m6631l();
                return ((HistoryNormalMessageDataSource.m4720a().m4744b(j) + HistoryGroupDataSource.m4691a().m4713c(j)) + ChannelDataSource.m4538a().m4551a(j)) + HistoryNormalMessageDataSource.m4720a().m4746c(j);
            default:
                return 0;
        }
    }
}
