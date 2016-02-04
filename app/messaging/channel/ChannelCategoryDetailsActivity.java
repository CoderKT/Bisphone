package app.messaging.channel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.Main;
import app.common.BaseActivity;
import app.database.datasource.ChannelDataSource;
import app.view.CustomViewPager;
import butterknife.ButterKnife;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelCategoryDetailsActivity extends BaseActivity {
    Handler f3409A;
    Runnable f3410B;
    boolean f3411C;
    boolean f3412D;
    private final int f3413E;
    private int f3414F;
    List<ChannelModel> f3415o;
    List<ChannelModel> f3416p;
    List<String> f3417q;
    ChannelMetaModel f3418r;
    ListView f3419s;
    ProgressBar f3420t;
    boolean f3421u;
    List<ChannelDetailAdapterModel> f3422v;
    ChannelCategoryDetailsListAdapter f3423w;
    CustomViewPager f3424x;
    int f3425y;
    ProgressBar f3426z;

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.1 */
    class C02841 implements OnScrollListener {
        final /* synthetic */ ChannelCategoryDetailsActivity f3394a;

        C02841(ChannelCategoryDetailsActivity channelCategoryDetailsActivity) {
            this.f3394a = channelCategoryDetailsActivity;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!this.f3394a.f3412D && this.f3394a.f3418r.getTotal() > this.f3394a.f3416p.size() && i + i2 >= this.f3394a.f3416p.size() - 3) {
                this.f3394a.f3412D = true;
                this.f3394a.f3414F = this.f3394a.f3416p.size() - 1;
                this.f3394a.f3419s.addFooterView(this.f3394a.f3426z);
                this.f3394a.m6078k();
            }
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.2 */
    class C02852 extends JsonHttpResponseHandler {
        final /* synthetic */ ChannelCategoryDetailsActivity f3395a;

        C02852(ChannelCategoryDetailsActivity channelCategoryDetailsActivity) {
            this.f3395a = channelCategoryDetailsActivity;
        }

        public void m6066a(int i, Header[] headerArr, JSONObject jSONObject) {
            Throwable e;
            try {
                if (jSONObject.has("featured") && jSONObject.getJSONArray("featured").length() > 0) {
                    this.f3395a.f3415o = new LinkedList(Arrays.asList((ChannelModel[]) ChannelNetworkKeyMap.m6120a(ChannelModel[].class, jSONObject.getJSONArray("featured").toString())));
                    this.f3395a.f3418r = (ChannelMetaModel) ChannelNetworkKeyMap.m6120a(ChannelMetaModel.class, jSONObject.getJSONObject("meta").toString());
                }
                this.f3395a.f3416p.addAll(new LinkedList(Arrays.asList((ChannelModel[]) ChannelNetworkKeyMap.m6120a(ChannelModel[].class, jSONObject.getJSONArray("normal").toString()))));
                this.f3395a.m6079l();
            } catch (JSONException e2) {
                e = e2;
                Main.f1926a.m5682c(e);
            } catch (IOException e3) {
                e = e3;
                Main.f1926a.m5682c(e);
            }
        }

        public void m6065a(int i, Header[] headerArr, String str, Throwable th) {
            super.m5513a(i, headerArr, str, th);
            if (!this.f3395a.f3412D) {
                this.f3395a.m6081n();
            }
            this.f3395a.f3412D = false;
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.3 */
    class C02893 implements Runnable {
        final /* synthetic */ ChannelCategoryDetailsActivity f3400a;

        /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.3.1 */
        class C02871 implements OnPageChangeListener {
            final /* synthetic */ C02893 f3397a;

            /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.3.1.1 */
            class C02861 implements Runnable {
                final /* synthetic */ C02871 f3396a;

                C02861(C02871 c02871) {
                    this.f3396a = c02871;
                }

                public void run() {
                    this.f3396a.f3397a.f3400a.f3411C = false;
                }
            }

            C02871(C02893 c02893) {
                this.f3397a = c02893;
            }

            public void m6068a(int i, float f, int i2) {
            }

            public void m6067a(int i) {
                int size = this.f3397a.f3400a.f3415o.size();
                if (i == 0) {
                    this.f3397a.f3400a.f3424x.m1301a(size - 2, false);
                } else if (i > size - 2) {
                    this.f3397a.f3400a.f3424x.m1301a(1, false);
                }
            }

            public void m6069b(int i) {
                if (i == 1) {
                    this.f3397a.f3400a.f3411C = true;
                    new Handler().postDelayed(new C02861(this), (long) this.f3397a.f3400a.f3425y);
                }
            }
        }

        /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.3.2 */
        class C02882 implements Runnable {
            final /* synthetic */ Handler f3398a;
            final /* synthetic */ C02893 f3399b;

            C02882(C02893 c02893, Handler handler) {
                this.f3399b = c02893;
                this.f3398a = handler;
            }

            public void run() {
                if (!this.f3399b.f3400a.f3411C && this.f3399b.f3400a.f3421u && this.f3399b.f3400a.f3424x != null) {
                    try {
                        int currentItem = this.f3399b.f3400a.f3424x.getCurrentItem();
                        if (currentItem >= this.f3399b.f3400a.f3415o.size() - 2) {
                            this.f3399b.f3400a.f3424x.m1301a(1, false);
                        } else {
                            this.f3399b.f3400a.f3424x.m1301a(currentItem + 1, true);
                        }
                        if (!this.f3399b.f3400a.isFinishing()) {
                            this.f3398a.postDelayed(this, (long) this.f3399b.f3400a.f3425y);
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        C02893(ChannelCategoryDetailsActivity channelCategoryDetailsActivity) {
            this.f3400a = channelCategoryDetailsActivity;
        }

        public void run() {
            this.f3400a.f3417q = ChannelDataSource.m4549c();
            for (int d = this.f3400a.f3414F + 1; d < this.f3400a.f3416p.size(); d++) {
                if (((ChannelModel) this.f3400a.f3416p.get(d)).getPrice() == 0.0f) {
                    boolean z = false;
                    for (int i = 0; i < this.f3400a.f3417q.size(); i++) {
                        if (((ChannelModel) this.f3400a.f3416p.get(d)).getId().equals(this.f3400a.f3417q.get(i))) {
                            z = true;
                        }
                    }
                    this.f3400a.f3422v.add(new ChannelDetailAdapterModel((ChannelModel) this.f3400a.f3416p.get(d), z));
                }
            }
            if (this.f3400a.f3412D) {
                this.f3400a.f3419s.removeFooterView(this.f3400a.f3426z);
                this.f3400a.f3423w.notifyDataSetChanged();
            } else if (!this.f3400a.isFinishing()) {
                if (this.f3400a.f3415o != null && this.f3400a.f3415o.size() > 0) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    this.f3400a.f3415o.add(0, this.f3400a.f3415o.get(this.f3400a.f3415o.size() - 1));
                    this.f3400a.f3415o.add(this.f3400a.f3415o.get(1));
                    this.f3400a.f3424x.setAdapter(new ChannelCategoryViewPagerAdapter(this.f3400a.getFragmentManager(), this.f3400a.f3415o));
                    this.f3400a.f3424x.m1301a(1, false);
                    this.f3400a.f3424x.setOnPageChangeListener(new C02871(this));
                    this.f3400a.f3411C = false;
                    this.f3400a.f3410B = new C02882(this, handler);
                    handler.postDelayed(this.f3400a.f3410B, (long) this.f3400a.f3425y);
                    if (this.f3400a.f3415o.size() == 3) {
                        this.f3400a.f3424x.setPagingEnabled(false);
                    }
                }
                this.f3400a.f3423w = new ChannelCategoryDetailsListAdapter(this.f3400a, this.f3400a.f3422v);
                this.f3400a.f3419s.setAdapter(this.f3400a.f3423w);
            } else {
                return;
            }
            this.f3400a.f3412D = false;
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.4 */
    class C02914 implements Runnable {
        final /* synthetic */ ChannelCategoryDetailsActivity f3402a;

        /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.4.1 */
        class C02901 implements OnClickListener {
            final /* synthetic */ C02914 f3401a;

            C02901(C02914 c02914) {
                this.f3401a = c02914;
            }

            public void onClick(View view) {
                this.f3401a.f3402a.setContentView(2130903071);
                this.f3401a.f3402a.m6078k();
            }
        }

        C02914(ChannelCategoryDetailsActivity channelCategoryDetailsActivity) {
            this.f3402a = channelCategoryDetailsActivity;
        }

        public void run() {
            if (!this.f3402a.isFinishing()) {
                this.f3402a.setContentView(2130903183);
                this.f3402a.findViewById(2131755494).setOnClickListener(new C02901(this));
            }
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.5 */
    class C02925 implements Runnable {
        final /* synthetic */ int f3403a;
        final /* synthetic */ ChannelCommunicationFollowEntity f3404b;
        final /* synthetic */ ChannelCategoryDetailsActivity f3405c;

        C02925(ChannelCategoryDetailsActivity channelCategoryDetailsActivity, int i, ChannelCommunicationFollowEntity channelCommunicationFollowEntity) {
            this.f3405c = channelCategoryDetailsActivity;
            this.f3403a = i;
            this.f3404b = channelCommunicationFollowEntity;
        }

        public void run() {
            ((ChannelDetailAdapterModel) this.f3405c.f3422v.get(this.f3403a)).f3502b = Boolean.valueOf(this.f3404b.m6098a());
            this.f3405c.f3423w.notifyDataSetChanged();
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsActivity.6 */
    class C02936 implements Runnable {
        final /* synthetic */ int f3406a;
        final /* synthetic */ ChannelCommunicationUnfollowEntity f3407b;
        final /* synthetic */ ChannelCategoryDetailsActivity f3408c;

        C02936(ChannelCategoryDetailsActivity channelCategoryDetailsActivity, int i, ChannelCommunicationUnfollowEntity channelCommunicationUnfollowEntity) {
            this.f3408c = channelCategoryDetailsActivity;
            this.f3406a = i;
            this.f3407b = channelCommunicationUnfollowEntity;
        }

        public void run() {
            ((ChannelDetailAdapterModel) this.f3408c.f3422v.get(this.f3406a)).f3502b = Boolean.valueOf(!this.f3407b.m6099a());
            this.f3408c.f3423w.notifyDataSetChanged();
        }
    }

    public ChannelCategoryDetailsActivity() {
        this.f3425y = 3000;
        this.f3413E = 25;
        this.f3414F = -1;
        this.f3412D = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903071);
        ButterKnife.m7741a((Activity) this);
        this.f3419s.setEmptyView(this.f3420t);
        setTitle(getResources().getString(2131296391));
        EventBus.m12779a().m12791a((Object) this);
        this.f3422v = new ArrayList();
        this.f3416p = new ArrayList();
        this.f3418r = new ChannelMetaModel();
        View inflate = getLayoutInflater().inflate(2130903115, null);
        ((TextView) inflate.findViewById(2131755302)).setText(getIntent().getStringExtra("title"));
        m6077j();
        this.f3419s.setOnScrollListener(new PauseOnScrollListener(ImageLoader.m11076a(), false, true));
        this.f3419s.addHeaderView(inflate);
        this.f3419s.setOnScrollListener(new C02841(this));
        this.f3424x = (CustomViewPager) inflate.findViewById(2131755301);
        this.f3409A = new Handler();
        m6078k();
    }

    protected void onResume() {
        super.onResume();
        this.f3421u = true;
        if (this.f3416p != null && this.f3416p.size() > 0) {
            m6080m();
        }
    }

    protected void onPause() {
        super.onPause();
        this.f3421u = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.m12779a().m12794c(this);
    }

    private void m6077j() {
        this.f3426z = new ProgressBar(this);
        this.f3426z.setIndeterminate(true);
    }

    private void m6078k() {
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        RequestParams requestParams = new RequestParams();
        List a = ChannelCommunication.getInstance().m6093a((Context) this);
        if (a != null && a.size() > 0) {
            requestParams.m10743a("lang", m6071a(a));
        }
        requestParams.m10743a("category", getIntent().getStringExtra("category_id"));
        requestParams.m10743a("type", this.f3416p.size() == 0 ? PrivacyItem.SUBSCRIPTION_BOTH : "normal");
        requestParams.m10743a("limit", "25");
        requestParams.m10743a("skip", this.f3416p.size() > 0 ? (this.f3416p.size() + 1) + "" : "1");
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        asyncHttpClient.m10584b(this, "https://channel.bisphone.com/api/v1/channel", headerArr, requestParams, new C02852(this));
    }

    private String m6071a(List<String> list) {
        if (list.size() == 0) {
            return "fa";
        }
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str = str + ((String) list.get(i));
            if (i != list.size() - 1) {
                str = str + "-";
            }
        }
        return str;
    }

    private synchronized void m6079l() {
        this.f3409A.post(new C02893(this));
        m6080m();
    }

    private void m6080m() {
        for (int i = 0; i < this.f3416p.size(); i++) {
            int a = ChannelCommunication.getInstance().m6092a(((ChannelModel) this.f3416p.get(i)).getId());
            if (a == -1) {
                ((ChannelModel) this.f3416p.get(i)).setFollowing(null);
            } else if (a == 0) {
                ((ChannelModel) this.f3416p.get(i)).setFollowing(Boolean.valueOf(false));
            } else if (a == 1) {
                ((ChannelModel) this.f3416p.get(i)).setFollowing(Boolean.valueOf(true));
            }
        }
        if (this.f3423w != null) {
            this.f3423w.notifyDataSetChanged();
        }
    }

    private void m6081n() {
        this.f3409A.post(new C02914(this));
    }

    public void onEvent(ChannelCommunicationFollowEntity channelCommunicationFollowEntity) {
        if (!isFinishing()) {
            int i;
            if (channelCommunicationFollowEntity.getHolder() != null) {
                i = channelCommunicationFollowEntity.getHolder().f3436g;
            } else {
                i = m6073b(channelCommunicationFollowEntity.getJabberId());
            }
            if (i != -1 && i < this.f3422v.size() && ((ChannelDetailAdapterModel) this.f3422v.get(i)).f3501a.getId().equals(channelCommunicationFollowEntity.getJabberId())) {
                this.f3409A.post(new C02925(this, i, channelCommunicationFollowEntity));
            }
        }
    }

    public void onEvent(ChannelCommunicationUnfollowEntity channelCommunicationUnfollowEntity) {
        if (!isFinishing()) {
            int i;
            if (channelCommunicationUnfollowEntity.getHolder() != null) {
                i = channelCommunicationUnfollowEntity.getHolder().f3436g;
            } else {
                i = m6073b(channelCommunicationUnfollowEntity.getJabberId());
            }
            if ((i >= this.f3422v.size() || ((ChannelDetailAdapterModel) this.f3422v.get(i)).f3501a.getId().equals(channelCommunicationUnfollowEntity.getJabberId())) && i < this.f3422v.size()) {
                this.f3409A.post(new C02936(this, i, channelCommunicationUnfollowEntity));
            }
        }
    }

    private int m6073b(String str) {
        for (int i = 0; i < this.f3422v.size(); i++) {
            if (((ChannelDetailAdapterModel) this.f3422v.get(i)).f3501a.getId().equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
