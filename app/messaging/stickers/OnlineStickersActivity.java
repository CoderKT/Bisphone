package app.messaging.stickers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import app.Main;
import app.common.BaseActivity;
import app.database.datasource.StickerDataSource;
import app.events.sticker.StickerPackDownloadedEvent;
import app.messaging.stickers.NoInternetConnection.NoInternetConnectionComminucator;
import app.messaging.stickers.OnlineStickerPreviewFragment.StickerCommunicator;
import app.messaging.stickers.OnlineStickersFragment.CommunicateStickyListPreview;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.json.JSONObject;

public class OnlineStickersActivity extends BaseActivity implements NoInternetConnectionComminucator, StickerCommunicator, CommunicateStickyListPreview {
    ArrayList<OnlinePackEntity> f3927o;
    OnlineStickersFragment f3928p;
    boolean f3929q;

    /* renamed from: app.messaging.stickers.OnlineStickersActivity.1 */
    class C03711 extends JsonHttpResponseHandler {
        final /* synthetic */ OnlineStickersActivity f3926a;

        C03711(OnlineStickersActivity onlineStickersActivity) {
            this.f3926a = onlineStickersActivity;
        }

        public void m6455a(int i, Header[] headerArr, JSONObject jSONObject) {
            this.f3926a.f3929q = false;
            this.f3926a.f3927o = new ArrayList(jSONObject.length());
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                SortedMap treeMap = new TreeMap();
                while (keys.hasNext()) {
                    boolean z;
                    String str = (String) keys.next();
                    if (StickerDataSource.m4763c(Integer.parseInt(str))) {
                        z = true;
                    } else {
                        z = false;
                    }
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                        String string = jSONObject2.getString("name");
                        String string2 = jSONObject2.getString("description");
                        boolean z2 = jSONObject2.getBoolean("new");
                        treeMap.put(str, new OnlinePackEntity(Integer.parseInt(str), string, string2, z, z2));
                        this.f3926a.f3927o.add(new OnlinePackEntity(Integer.parseInt(str), string, string2, z, z2));
                    } catch (Throwable e) {
                        Main.f1926a.m5680b(e);
                    }
                }
                if (!this.f3926a.isFinishing()) {
                    this.f3926a.f3928p.m6470a(new LinkedList(treeMap.values()));
                    if (this.f3926a.getIntent().getIntExtra("selected_sticker_id", 0) != 0) {
                        this.f3926a.f3928p.m6468a(this.f3926a.getIntent().getIntExtra("selected_sticker_id", 0));
                    }
                    StickerDataSource.m4765e();
                }
            }
        }

        public void m6454a(int i, Header[] headerArr, String str, Throwable th) {
            super.m5513a(i, headerArr, str, th);
            if (!this.f3926a.isFinishing()) {
                Main.f1926a.m5683d("Getting Sticker List Failed");
                try {
                    this.f3926a.getFragmentManager().popBackStack();
                    if (this.f3926a.f3927o == null || this.f3926a.f3927o.size() == 0) {
                        this.f3926a.f3929q = true;
                        Fragment noInternetConnection = new NoInternetConnection();
                        noInternetConnection.m6431a(this.f3926a);
                        this.f3926a.m6460a(noInternetConnection, false);
                    }
                } catch (Throwable e) {
                    Main.f1926a.m5684d(e);
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903086);
        if (bundle == null) {
            m6457j();
            return;
        }
        getFragmentManager().popBackStack();
        m6457j();
    }

    private void m6457j() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.addToBackStack("firstFrag");
        this.f3928p = new OnlineStickersFragment();
        this.f3928p.m6469a((CommunicateStickyListPreview) this);
        beginTransaction.add(2131755221, this.f3928p);
        beginTransaction.commit();
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        RequestParams requestParams = new RequestParams();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        asyncHttpClient.m10584b(this, "https://chatng.bisphone.com:443/api/v1/sticker/list", headerArr, requestParams, new C03711(this));
    }

    public void m6461a(OnlinePackEntity onlinePackEntity) {
        Fragment onlineStickerPreviewFragment = new OnlineStickerPreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DataPacketExtension.ELEMENT, onlinePackEntity);
        onlineStickerPreviewFragment.setArguments(bundle);
        onlineStickerPreviewFragment.m6453a((StickerCommunicator) this);
        m6460a(onlineStickerPreviewFragment, true);
    }

    public void m6460a(Fragment fragment, boolean z) {
        try {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            if (z) {
                beginTransaction.setCustomAnimations(2130968588, 2130968589, 2130968591, 2130968592);
            }
            beginTransaction.replace(2131755221, fragment);
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();
        } catch (Exception e) {
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return false;
    }

    public void onResume() {
        EventBus.m12779a().m12791a((Object) this);
        super.onResume();
    }

    public void onPause() {
        EventBus.m12779a().m12794c(this);
        super.onPause();
    }

    public void onEvent(StickerPackDownloadedEvent stickerPackDownloadedEvent) {
        for (int i = 0; i < this.f3927o.size(); i++) {
            if (((OnlinePackEntity) this.f3927o.get(i)).f3901a == stickerPackDownloadedEvent.f2494a) {
                ((OnlinePackEntity) this.f3927o.get(i)).f3904d = Boolean.valueOf(stickerPackDownloadedEvent.m4954a());
            }
        }
    }

    public void onBackPressed() {
        if (this.f3929q) {
            finish();
            return;
        }
        try {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                if ("firstFrag".equals(getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName())) {
                    finish();
                    return;
                } else {
                    getFragmentManager().popBackStack();
                    return;
                }
            }
            finish();
        } catch (Exception e) {
            finish();
        }
    }

    public void m6458a() {
        getFragmentManager().popBackStack();
        m6457j();
    }

    public void m6459a(int i) {
        if (this.f3927o != null) {
            Iterator it = this.f3927o.iterator();
            while (it.hasNext()) {
                OnlinePackEntity onlinePackEntity = (OnlinePackEntity) it.next();
                if (onlinePackEntity != null && i == onlinePackEntity.f3901a) {
                    onlinePackEntity.f3904d = Boolean.valueOf(false);
                }
            }
        }
    }
}
