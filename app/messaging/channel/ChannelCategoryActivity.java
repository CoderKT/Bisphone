package app.messaging.channel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import app.Main;
import app.common.BaseActivity;
import app.util.SharedPreferencesUtil;
import app.view.CustomViewPager;
import butterknife.ButterKnife;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;

public class ChannelCategoryActivity extends BaseActivity {
    List<ChannelCategoryModel> f3384o;
    List<ChannelModel> f3385p;
    ListView f3386q;
    ProgressBar f3387r;
    CustomViewPager f3388s;
    int f3389t;
    Handler f3390u;
    Runnable f3391v;
    boolean f3392w;
    private boolean f3393x;

    /* renamed from: app.messaging.channel.ChannelCategoryActivity.1 */
    class C02751 extends JsonHttpResponseHandler {
        final /* synthetic */ ChannelCategoryActivity f3374a;

        C02751(ChannelCategoryActivity channelCategoryActivity) {
            this.f3374a = channelCategoryActivity;
        }

        public void m6047a(int i, Header[] headerArr, JSONArray jSONArray) {
            try {
                this.f3374a.f3384o = new LinkedList(Arrays.asList((ChannelCategoryModel[]) ChannelNetworkKeyMap.m6120a(ChannelCategoryModel[].class, jSONArray.toString())));
                this.f3374a.m6063k();
            } catch (Throwable e) {
                Main.f1926a.m5682c(e);
            }
        }

        public void m6046a(int i, Header[] headerArr, String str, Throwable th) {
            super.m5513a(i, headerArr, str, th);
            this.f3374a.m6064l();
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryActivity.2 */
    class C02762 extends JsonHttpResponseHandler {
        final /* synthetic */ ChannelCategoryActivity f3375a;

        C02762(ChannelCategoryActivity channelCategoryActivity) {
            this.f3375a = channelCategoryActivity;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m6049a(int r5, cz.msebera.android.httpclient.Header[] r6, org.json.JSONObject r7) {
            /*
            r4 = this;
            r1 = r4.f3375a;	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r2 = new java.util.LinkedList;	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0 = app.messaging.channel.ChannelModel[].class;
            r3 = "featured";
            r3 = r7.getJSONArray(r3);	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r3 = r3.toString();	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0 = app.messaging.channel.ChannelNetworkKeyMap.m6120a(r0, r3);	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0 = (app.messaging.channel.ChannelModel[]) r0;	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0 = (app.messaging.channel.ChannelModel[]) r0;	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0 = java.util.Arrays.asList(r0);	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r2.<init>(r0);	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r1.f3385p = r2;	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0 = r4.f3375a;	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
            r0.m6063k();	 Catch:{ IOException -> 0x002e, JSONException -> 0x0027 }
        L_0x0026:
            return;
        L_0x0027:
            r0 = move-exception;
        L_0x0028:
            r1 = app.Main.f1926a;
            r1.m5682c(r0);
            goto L_0x0026;
        L_0x002e:
            r0 = move-exception;
            goto L_0x0028;
            */
            throw new UnsupportedOperationException("Method not decompiled: app.messaging.channel.ChannelCategoryActivity.2.a(int, cz.msebera.android.httpclient.Header[], org.json.JSONObject):void");
        }

        public void m6048a(int i, Header[] headerArr, String str, Throwable th) {
            super.m5513a(i, headerArr, str, th);
            this.f3375a.m6064l();
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryActivity.3 */
    class C02813 implements Runnable {
        final /* synthetic */ ChannelCategoryActivity f3381a;

        /* renamed from: app.messaging.channel.ChannelCategoryActivity.3.1 */
        class C02781 implements OnPageChangeListener {
            final /* synthetic */ C02813 f3377a;

            /* renamed from: app.messaging.channel.ChannelCategoryActivity.3.1.1 */
            class C02771 implements Runnable {
                final /* synthetic */ C02781 f3376a;

                C02771(C02781 c02781) {
                    this.f3376a = c02781;
                }

                public void run() {
                    this.f3376a.f3377a.f3381a.f3392w = false;
                }
            }

            C02781(C02813 c02813) {
                this.f3377a = c02813;
            }

            public void m6051a(int i, float f, int i2) {
            }

            public void m6050a(int i) {
                int size = this.f3377a.f3381a.f3385p.size();
                if (i == 0) {
                    this.f3377a.f3381a.f3388s.m1301a(size - 2, false);
                } else if (i > size - 2) {
                    this.f3377a.f3381a.f3388s.m1301a(1, false);
                }
            }

            public void m6052b(int i) {
                if (i == 1) {
                    this.f3377a.f3381a.f3392w = true;
                    new Handler().postDelayed(new C02771(this), (long) this.f3377a.f3381a.f3389t);
                }
            }
        }

        /* renamed from: app.messaging.channel.ChannelCategoryActivity.3.2 */
        class C02792 implements Runnable {
            final /* synthetic */ Handler f3378a;
            final /* synthetic */ C02813 f3379b;

            C02792(C02813 c02813, Handler handler) {
                this.f3379b = c02813;
                this.f3378a = handler;
            }

            public void run() {
                if (!this.f3379b.f3381a.isFinishing()) {
                    if (!this.f3379b.f3381a.f3392w) {
                        int currentItem = this.f3379b.f3381a.f3388s.getCurrentItem();
                        if (currentItem >= this.f3379b.f3381a.f3385p.size() - 2) {
                            this.f3379b.f3381a.f3388s.m1301a(1, false);
                        } else {
                            this.f3379b.f3381a.f3388s.m1301a(currentItem + 1, true);
                        }
                    }
                    if (!this.f3379b.f3381a.isFinishing()) {
                        this.f3378a.postDelayed(this, (long) this.f3379b.f3381a.f3389t);
                    }
                }
            }
        }

        /* renamed from: app.messaging.channel.ChannelCategoryActivity.3.3 */
        class C02803 implements OnItemClickListener {
            final /* synthetic */ C02813 f3380a;

            C02803(C02813 c02813) {
                this.f3380a = c02813;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(this.f3380a.f3381a, ChannelCategoryDetailsActivity.class);
                intent.putExtra("category_id", ((ChannelCategoryModel) this.f3380a.f3381a.f3384o.get(i - 1)).getId());
                intent.putExtra("title", ((ChannelCategoryModel) this.f3380a.f3381a.f3384o.get(i - 1)).getTitle());
                this.f3380a.f3381a.startActivity(intent);
            }
        }

        C02813(ChannelCategoryActivity channelCategoryActivity) {
            this.f3381a = channelCategoryActivity;
        }

        public void run() {
            if (!this.f3381a.isFinishing()) {
                if (this.f3381a.f3385p != null && this.f3381a.f3385p.size() > 0) {
                    if (!SharedPreferencesUtil.m7060b(this.f3381a.getString(2131296922), Boolean.valueOf(false))) {
                        for (int i = 0; i < this.f3381a.f3385p.size(); i++) {
                            if (((ChannelModel) this.f3381a.f3385p.get(i)).getId().equals("chan-bisphone-01@channel.bisphone.com")) {
                                ChannelCommunication.getInstance().m6094a((ChannelModel) this.f3381a.f3385p.get(i), ((ChannelModel) this.f3381a.f3385p.get(i)).getId(), null);
                            }
                        }
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    this.f3381a.f3385p.add(0, this.f3381a.f3385p.get(this.f3381a.f3385p.size() - 1));
                    this.f3381a.f3385p.add(this.f3381a.f3385p.get(1));
                    this.f3381a.f3388s.setAdapter(new ChannelCategoryViewPagerAdapter(this.f3381a.getFragmentManager(), this.f3381a.f3385p));
                    this.f3381a.f3388s.m1301a(1, false);
                    this.f3381a.f3388s.setOnPageChangeListener(new C02781(this));
                    this.f3381a.f3392w = false;
                    this.f3381a.f3391v = new C02792(this, handler);
                    handler.postDelayed(this.f3381a.f3391v, (long) this.f3381a.f3389t);
                    if (this.f3381a.f3385p.size() == 3) {
                        this.f3381a.f3388s.setPagingEnabled(false);
                    }
                }
                this.f3381a.f3386q.setOnScrollListener(new PauseOnScrollListener(ImageLoader.m11076a(), false, true));
                this.f3381a.f3386q.setAdapter(new ChannelCategoryListAdapter(this.f3381a, this.f3381a.f3384o));
                this.f3381a.f3386q.setOnItemClickListener(new C02803(this));
            }
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryActivity.4 */
    class C02834 implements Runnable {
        final /* synthetic */ ChannelCategoryActivity f3383a;

        /* renamed from: app.messaging.channel.ChannelCategoryActivity.4.1 */
        class C02821 implements OnClickListener {
            final /* synthetic */ C02834 f3382a;

            C02821(C02834 c02834) {
                this.f3382a = c02834;
            }

            public void onClick(View view) {
                this.f3382a.f3383a.setContentView(2130903071);
                ButterKnife.m7741a(this.f3382a.f3383a);
                this.f3382a.f3383a.m6062j();
                this.f3382a.f3383a.m6057a(ChannelCommunication.getInstance().m6093a(this.f3382a.f3383a), null);
                this.f3382a.f3383a.m6056a(ChannelCommunication.getInstance().m6093a(this.f3382a.f3383a));
                this.f3382a.f3383a.f3393x = false;
            }
        }

        C02834(ChannelCategoryActivity channelCategoryActivity) {
            this.f3383a = channelCategoryActivity;
        }

        public void run() {
            if (!this.f3383a.isFinishing()) {
                this.f3383a.setContentView(2130903183);
                this.f3383a.findViewById(2131755494).setOnClickListener(new C02821(this));
            }
        }
    }

    public ChannelCategoryActivity() {
        this.f3389t = 3000;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903071);
        ButterKnife.m7741a((Activity) this);
        setTitle(getResources().getString(2131296795));
        m6062j();
        m6057a(ChannelCommunication.getInstance().m6093a((Context) this), null);
        m6056a(ChannelCommunication.getInstance().m6093a((Context) this));
    }

    private void m6062j() {
        View inflate = getLayoutInflater().inflate(2130903115, null);
        this.f3386q.addHeaderView(inflate);
        this.f3386q.setEmptyView(this.f3387r);
        this.f3388s = (CustomViewPager) inflate.findViewById(2131755301);
        this.f3393x = false;
        this.f3390u = new Handler();
    }

    private void m6057a(List<String> list, String str) {
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        RequestParams requestParams = new RequestParams();
        if (list != null && list.size() > 0) {
            requestParams.m10743a("lang", m6059b((List) list));
        }
        if (str != null && str.length() > 0) {
            requestParams.m10743a("sortBy", str);
        }
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        asyncHttpClient.m10584b(this, "https://channel.bisphone.com/api/v1/category", headerArr, requestParams, new C02751(this));
    }

    private void m6056a(List<String> list) {
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        RequestParams requestParams = new RequestParams();
        if (list != null && list.size() > 0) {
            requestParams.m10743a("lang", m6059b((List) list));
        }
        requestParams.m10743a("type", "featured");
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        asyncHttpClient.m10584b(this, "https://channel.bisphone.com/api/v1/channel", headerArr, requestParams, new C02762(this));
    }

    private String m6059b(List<String> list) {
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

    private synchronized void m6063k() {
        if (this.f3393x) {
            this.f3390u.post(new C02813(this));
        }
        this.f3393x = true;
    }

    private void m6064l() {
        this.f3390u.post(new C02834(this));
    }

    protected void onDestroy() {
        super.onDestroy();
        ChannelCommunication.getInstance().f3467c = null;
    }
}
