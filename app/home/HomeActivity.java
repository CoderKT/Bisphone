package app.home;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.AlertDialog.Builder;
import android.util.SparseArray;
import android.view.View;
import app.Main;
import app.account.AccountManager;
import app.common.BaseActivity;
import app.common.entity.StickerEntity;
import app.contact.ContactComparatorService;
import app.database.datasource.ContactDataSource;
import app.events.ConnectionListenerEvent;
import app.events.ConnectionListenerEvent.ConnectionType;
import app.events.ContactLocalSyncFinishedEvent;
import app.events.HomeTabsBadgeUpdateEvent;
import app.events.channel.EnterAllChannels;
import app.home.CallFragment.OnFragmentInteractionListener;
import app.messaging.stickers.LocalPacksManager;
import app.messaging.stickers.StickerManager;
import app.notification.NotificationCenter;
import app.profile.InviteUtil;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.SharedPreferencesUtil;
import app.view.CustomViewPager;
import app.xmpp.GroupManager;
import app.xmpp.NetworkConnectivityChangeReceiver;
import app.xmpp.VCardHandler;
import app.xmpp.XMPPService;
import com.crashlytics.android.Crashlytics;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nispok.snackbar.Snackbar;
import com.sahandrc.calendar.PersianCalendar;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;
import se.emilsjolander.stickylistheaders.C1128R;

public class HomeActivity extends BaseActivity implements OnFragmentInteractionListener, ChatFragment.OnFragmentInteractionListener {
    private Tab f3049A;
    private Handler f3050B;
    private ProgressDialog f3051C;
    private Boolean f3052D;
    private int f3053E;
    private boolean f3054F;
    Intent f3055o;
    Snackbar f3056p;
    boolean f3057q;
    private CustomViewPager f3058r;
    private ContactsFragment f3059s;
    private HistoryFragment f3060t;
    private GroupsFragment f3061u;
    private ChannelFragment f3062v;
    private ActionBar f3063w;
    private Tab f3064x;
    private Tab f3065y;
    private Tab f3066z;

    /* renamed from: app.home.HomeActivity.1 */
    class C02301 implements Runnable {
        final /* synthetic */ HomeActivity f3030a;

        C02301(HomeActivity homeActivity) {
            this.f3030a = homeActivity;
        }

        public void run() {
            if (Storage.m6936a() && Storage.m6942b(this.f3030a)) {
                LocalPacksManager.m6426a(this.f3030a);
                this.f3030a.m5540k();
                ContactDataSource.m4553a().m4584i();
                StickerManager.m6491b().m6494a(this.f3030a);
            }
        }
    }

    /* renamed from: app.home.HomeActivity.2 */
    class C02312 implements Runnable {
        final /* synthetic */ HashMap f3031a;
        final /* synthetic */ SparseArray f3032b;
        final /* synthetic */ HomeActivity f3033c;

        C02312(HomeActivity homeActivity, HashMap hashMap, SparseArray sparseArray) {
            this.f3033c = homeActivity;
            this.f3031a = hashMap;
            this.f3032b = sparseArray;
        }

        public void run() {
            Iterator it = this.f3031a.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (this.f3032b.get(Integer.parseInt(entry.getKey().toString())) != null) {
                    StickerManager.m6491b().m6497a(this.f3033c, Integer.parseInt(entry.getKey().toString()), false);
                }
                it.remove();
            }
        }
    }

    /* renamed from: app.home.HomeActivity.3 */
    class C02323 extends JsonHttpResponseHandler {
        final /* synthetic */ HomeActivity f3043a;

        C02323(HomeActivity homeActivity) {
            this.f3043a = homeActivity;
        }

        public void m5521a(int i, Header[] headerArr, JSONObject jSONObject) {
            try {
                if (jSONObject.getBoolean("is_expired")) {
                    SharedPreferencesUtil.m7055a("expire_date", "0");
                    this.f3043a.m5537o();
                } else {
                    SharedPreferencesUtil.m7055a("expire_date", new PersianCalendar().m11348f());
                }
                int i2 = jSONObject.getInt("max_group_per_user");
                if (i2 > 0) {
                    SharedPreferencesUtil.m7053a("mgpu", i2);
                }
            } catch (Throwable e) {
                Main.f1926a.m5678a(e);
            }
        }

        public void m5520a(int i, Header[] headerArr, String str, Throwable th) {
            super.m5513a(i, headerArr, str, th);
        }
    }

    /* renamed from: app.home.HomeActivity.4 */
    class C02334 implements OnClickListener {
        final /* synthetic */ HomeActivity f3044a;

        C02334(HomeActivity homeActivity) {
            this.f3044a = homeActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=com.bistalk.bisphone"));
            this.f3044a.startActivity(Intent.createChooser(intent, this.f3044a.getString(2131296448)));
            this.f3044a.finish();
        }
    }

    /* renamed from: app.home.HomeActivity.5 */
    class C02345 implements OnClickListener {
        final /* synthetic */ HomeActivity f3045a;

        C02345(HomeActivity homeActivity) {
            this.f3045a = homeActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f3045a.finish();
        }
    }

    /* renamed from: app.home.HomeActivity.6 */
    class C02356 extends SimpleOnPageChangeListener {
        final /* synthetic */ HomeActivity f3046a;

        C02356(HomeActivity homeActivity) {
            this.f3046a = homeActivity;
        }

        public void m5522a(int i) {
            if (this.f3046a.f3063w.getNavigationMode() != 2) {
                this.f3046a.f3063w.setNavigationMode(2);
            }
            this.f3046a.m5529c(i);
            this.f3046a.f3063w.setSelectedNavigationItem(i);
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    NotificationCenter.m6606a().m6625h();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    NotificationCenter.m6606a().m6619e();
                default:
            }
        }
    }

    class ActionBarTabsListeners implements TabListener {
        final /* synthetic */ HomeActivity f3047a;

        ActionBarTabsListeners(HomeActivity homeActivity) {
            this.f3047a = homeActivity;
        }

        public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
            this.f3047a.f3058r.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
        }
    }

    class CustomViewPagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ HomeActivity f3048a;

        CustomViewPagerAdapter(HomeActivity homeActivity, FragmentManager fragmentManager) {
            this.f3048a = homeActivity;
            super(fragmentManager);
        }

        public Fragment m5523a(int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    return this.f3048a.f3060t;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return this.f3048a.f3061u;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return this.f3048a.f3059s;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    return this.f3048a.f3062v;
                default:
                    return this.f3048a.f3059s;
            }
        }

        public int getCount() {
            return 4;
        }
    }

    public HomeActivity() {
        this.f3050B = new Handler();
        this.f3052D = null;
        this.f3057q = true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903080);
        if (this.f3052D == null) {
            this.f3052D = Boolean.valueOf(getIntent().getBooleanExtra("FirstRun", false));
        }
        EventBus.m12779a().m12791a((Object) this);
        GroupManager.m7323a().m7341a((Context) this);
        m5541l();
        m5527b(getIntent().getIntExtra("extra_tab", 0));
        AccountManager a = AccountManager.m3934a();
        Crashlytics.m7881a(a.m3937c());
        Crashlytics.m7886b(a.m3937c());
        StickerManager.m6491b();
        new Handler().postDelayed(new C02301(this), 2500);
        if (!SharedPreferencesUtil.m7060b(getResources().getString(2131296929), Boolean.valueOf(false))) {
            VCardHandler.m7499a().m7504b();
        }
        this.f3055o = new Intent(this, ContactComparatorService.class);
        if (PermissionUtil.m7044a(PermissionType.contact)) {
            startService(this.f3055o);
        } else {
            PermissionUtil.m7042a((Activity) this, PermissionType.contact, 1);
        }
        if (!NetworkConnectivityChangeReceiver.m7394a(this)) {
            onEventMainThread(new ConnectionListenerEvent(ConnectionType.waitingForNetwork));
        }
    }

    protected void onResume() {
        super.onResume();
        m5536n();
        startService(new Intent(this, XMPPService.class));
        EventBus.m12779a().m12795d(new EnterAllChannels());
        if (this.f3052D.booleanValue()) {
            this.f3052D = Boolean.valueOf(false);
            m5541l();
        }
        this.f3057q = true;
        if (this.f3056p != null) {
            this.f3056p.m10877b((Activity) this);
        }
    }

    protected void onPostResume() {
        Main.f1926a.m5685e("onPostResume");
        super.onPostResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        EventBus.m12779a().m12794c(this);
        super.onDestroy();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i >= 40) {
            Storage.m6957k();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m5529c(this.f3053E);
    }

    CustomViewPager m5539j() {
        return this.f3058r;
    }

    public void showInviteDialog(View view) {
        InviteUtil.showInviteDialog(this);
    }

    public void m5540k() {
        SparseArray c = StickerManager.m6491b().m6498c();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < c.size(); i++) {
            try {
                int keyAt = c.keyAt(i);
                if (!new File(((StickerEntity) c.get(keyAt)).m4477c()).exists()) {
                    hashMap.put(Integer.valueOf(((StickerEntity) c.get(keyAt)).m4475b()), "");
                }
            } catch (StorageException e) {
                e.printStackTrace();
            }
        }
        runOnUiThread(new C02312(this, hashMap, StickerManager.m6491b().m6499d()));
    }

    private void m5536n() {
        int i;
        SharedPreferencesUtil.m7052a("expire_date");
        try {
            i = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            i = 0;
        }
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        RequestParams requestParams = new RequestParams();
        requestParams.m10743a("app", i + "");
        requestParams.m10743a("os", "android");
        requestParams.m10743a("id", AccountManager.m3934a().m3937c());
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        asyncHttpClient.m10584b(this, "https://chatng.bisphone.com:443/api/v2/global_config", headerArr, requestParams, new C02323(this));
    }

    private void m5537o() {
        Builder builder = new Builder(this, 2131558536);
        builder.m1980a(getResources().getString(2131296335));
        builder.m1986b(getResources().getString(2131296336));
        builder.m1975a(2131296624, new C02334(this));
        builder.m1987b(getString(2131296380), new C02345(this));
        builder.m1989c(2130837729);
        builder.m1992c();
    }

    private void m5527b(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                break;
            default:
                i = 0;
                break;
        }
        this.f3063w = getActionBar();
        if (this.f3063w != null) {
            this.f3063w.setDisplayHomeAsUpEnabled(false);
            this.f3063w.setNavigationMode(2);
            this.f3063w.setDisplayShowHomeEnabled(false);
            this.f3063w.setDisplayShowTitleEnabled(false);
            this.f3058r = (CustomViewPager) findViewById(2131755174);
            this.f3059s = new ContactsFragment();
            this.f3060t = new HistoryFragment();
            this.f3061u = new GroupsFragment();
            this.f3062v = new ChannelFragment();
            this.f3058r.setAdapter(new CustomViewPagerAdapter(this, getFragmentManager()));
            this.f3058r.setOffscreenPageLimit(4);
            this.f3064x = this.f3063w.newTab();
            this.f3065y = this.f3063w.newTab();
            this.f3066z = this.f3063w.newTab();
            this.f3049A = this.f3063w.newTab();
            TabListener actionBarTabsListeners = new ActionBarTabsListeners(this);
            this.f3064x.setTabListener(actionBarTabsListeners);
            this.f3065y.setTabListener(actionBarTabsListeners);
            this.f3066z.setTabListener(actionBarTabsListeners);
            this.f3049A.setTabListener(actionBarTabsListeners);
            m5529c(i);
            this.f3063w.addTab(this.f3065y);
            this.f3063w.addTab(this.f3066z);
            this.f3063w.addTab(this.f3064x);
            this.f3063w.addTab(this.f3049A);
            this.f3058r.setOnPageChangeListener(new C02356(this));
            this.f3063w.setSelectedNavigationItem(i);
        }
    }

    public void startComposing(View view) {
    }

    public void a_(boolean z) {
        if (this.f3060t != null) {
            this.f3060t.m5453a(z);
        }
    }

    public void m5538b(boolean z) {
        if (this.f3060t != null) {
            this.f3060t.m5454b(z);
        }
    }

    private void m5529c(int i) {
        this.f3053E = i;
        if (getResources().getConfiguration().orientation == 2) {
            m5533e(i);
        } else {
            m5531d(i);
        }
    }

    private void m5531d(int i) {
        int i2 = 2130837810;
        Tab tab;
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f3064x.setIcon(2130837819);
                this.f3065y.setIcon(2130837816);
                this.f3066z.setIcon(2130837823);
                tab = this.f3049A;
                if (!this.f3054F) {
                    i2 = 2130837809;
                }
                tab.setIcon(i2);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f3064x.setIcon(2130837819);
                this.f3065y.setIcon(2130837815);
                this.f3066z.setIcon(2130837824);
                tab = this.f3049A;
                if (!this.f3054F) {
                    i2 = 2130837809;
                }
                tab.setIcon(i2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f3064x.setIcon(2130837820);
                this.f3065y.setIcon(2130837815);
                this.f3066z.setIcon(2130837823);
                tab = this.f3049A;
                if (!this.f3054F) {
                    i2 = 2130837809;
                }
                tab.setIcon(i2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f3064x.setIcon(2130837819);
                this.f3065y.setIcon(2130837815);
                this.f3066z.setIcon(2130837823);
                this.f3049A.setIcon(this.f3054F ? 2130837812 : 2130837811);
            default:
                throw new IllegalArgumentException("Selected tab number must be between 0 ~ 3");
        }
    }

    private void m5533e(int i) {
        int i2 = 2130837806;
        Tab tab;
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f3064x.setIcon(2130837817);
                this.f3065y.setIcon(2130837814);
                this.f3066z.setIcon(2130837821);
                tab = this.f3049A;
                if (!this.f3054F) {
                    i2 = 2130837805;
                }
                tab.setIcon(i2);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f3064x.setIcon(2130837817);
                this.f3065y.setIcon(2130837813);
                this.f3066z.setIcon(2130837822);
                tab = this.f3049A;
                if (!this.f3054F) {
                    i2 = 2130837805;
                }
                tab.setIcon(i2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f3064x.setIcon(2130837818);
                this.f3065y.setIcon(2130837813);
                this.f3066z.setIcon(2130837821);
                tab = this.f3049A;
                if (!this.f3054F) {
                    i2 = 2130837805;
                }
                tab.setIcon(i2);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f3064x.setIcon(2130837817);
                this.f3065y.setIcon(2130837813);
                this.f3066z.setIcon(2130837821);
                this.f3049A.setIcon(this.f3054F ? 2130837807 : 2130837808);
            default:
                throw new IllegalArgumentException("Selected tab number must be between 0 ~ 3");
        }
    }

    public void m5541l() {
        this.f3051C = new ProgressDialog(this);
        this.f3051C.setMessage(getResources().getString(2131296644));
        this.f3051C.setCanceledOnTouchOutside(false);
        this.f3051C.setCancelable(false);
        this.f3051C.setIndeterminate(true);
    }

    public void m5542m() {
        if (this.f3051C != null && !isFinishing()) {
            this.f3051C.dismiss();
        }
    }

    public void onEvent(ContactLocalSyncFinishedEvent contactLocalSyncFinishedEvent) {
        EventBus.m12779a().m12797f(contactLocalSyncFinishedEvent);
        m5542m();
    }

    public void onEventMainThread(ConnectionListenerEvent connectionListenerEvent) {
    }

    public void onEventMainThread(HomeTabsBadgeUpdateEvent homeTabsBadgeUpdateEvent) {
        CharSequence charSequence = null;
        Tab tab;
        switch (homeTabsBadgeUpdateEvent.m4847a()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                tab = this.f3065y;
                if (homeTabsBadgeUpdateEvent.m4848b() > 0) {
                    if (homeTabsBadgeUpdateEvent.m4848b() > 99) {
                        charSequence = "99";
                    } else {
                        charSequence = "" + homeTabsBadgeUpdateEvent.m4848b();
                    }
                }
                tab.setText(charSequence);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                tab = this.f3066z;
                if (homeTabsBadgeUpdateEvent.m4848b() > 0) {
                    charSequence = homeTabsBadgeUpdateEvent.m4848b() > 99 ? "99" : "" + homeTabsBadgeUpdateEvent.m4848b();
                }
                tab.setText(charSequence);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f3054F = homeTabsBadgeUpdateEvent.m4848b() > 0;
                m5529c(this.f3053E);
            default:
                throw new IllegalArgumentException("Unknown YOLO SWAG");
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (iArr[0] == 0) {
                        startService(this.f3055o);
                    }
                default:
            }
        }
    }
}
