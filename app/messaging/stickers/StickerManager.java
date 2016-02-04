package app.messaging.stickers;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.widget.ImageView;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.StickerEntity;
import app.common.entity.StickerPackEntity;
import app.database.datasource.StickerDataSource;
import app.database.provider.StickerPackProvider;
import app.database.provider.StickerProvider;
import app.events.sticker.StickerDownloadedEvent;
import app.events.sticker.StickerPackDownloadedEvent;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.Unzipper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class StickerManager {
    private static StickerManager f3978a;
    private SparseArray<StickerEntity> f3979b;
    private SparseArray<StickerPackEntity> f3980c;
    private int f3981d;

    /* renamed from: app.messaging.stickers.StickerManager.1 */
    class C03771 extends ContentObserver {
        final /* synthetic */ StickerManager f3959a;

        C03771(StickerManager stickerManager, Handler handler) {
            this.f3959a = stickerManager;
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            this.f3959a.f3979b = StickerDataSource.m4753a();
            Main.f1926a.m5685e("STICKER MANAGER : Stickers Updated");
        }
    }

    /* renamed from: app.messaging.stickers.StickerManager.2 */
    class C03782 extends ContentObserver {
        final /* synthetic */ StickerManager f3960a;

        C03782(StickerManager stickerManager, Handler handler) {
            this.f3960a = stickerManager;
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            this.f3960a.f3980c = StickerDataSource.m4759b();
            Main.f1926a.m5685e("STICKER MANAGER : Sticker Packs Updated");
        }
    }

    /* renamed from: app.messaging.stickers.StickerManager.3 */
    class C03793 extends JsonHttpResponseHandler {
        final /* synthetic */ int f3961a;
        final /* synthetic */ StickerManager f3962b;

        C03793(StickerManager stickerManager, int i) {
            this.f3962b = stickerManager;
            this.f3961a = i;
        }

        public void m6480a(int i, Header[] headerArr, JSONObject jSONObject) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("pack");
                if ("0".equals(jSONObject2.getString("price"))) {
                    jSONObject2.getInt("count");
                    String string = jSONObject2.getString("name");
                    String string2 = jSONObject2.getJSONObject("android").getString("layout");
                    Iterator keys = jSONObject2.getJSONObject("stickers").keys();
                    while (keys.hasNext()) {
                        StickerDataSource.m4751a(new StickerEntity(Integer.parseInt((String) keys.next()), this.f3961a));
                    }
                    StickerDataSource.m4752a(new StickerPackEntity(this.f3961a, string, string2, false, true, 0, true));
                }
            } catch (Throwable e) {
                Main.f1926a.m5680b(e);
            }
        }

        public void m6479a(int i, Header[] headerArr, String str, Throwable th) {
            super.m5513a(i, headerArr, str, th);
        }
    }

    /* renamed from: app.messaging.stickers.StickerManager.4 */
    class C03804 extends FileTaskListener {
        final /* synthetic */ int f3963a;
        final /* synthetic */ Context f3964b;
        final /* synthetic */ boolean f3965c;
        final /* synthetic */ StickerManager f3966h;

        C03804(StickerManager stickerManager, File file, int i, Context context, boolean z) {
            this.f3966h = stickerManager;
            this.f3963a = i;
            this.f3964b = context;
            this.f3965c = z;
            super(file);
        }

        public void m6481a(int i, Header[] headerArr, File file) {
            if (this.f3966h.m6489a(file, this.f3963a)) {
                this.f3966h.m6495a(this.f3964b, this.f3963a);
            }
            EventBus.m12779a().m12795d(new StickerPackDownloadedEvent(this.f3963a, true));
            if (this.f3965c) {
                Main.m3905a(Main.f1927b.getString(2131296772));
            }
            Main.f1926a.m5683d("Sticker Pack Download Successful");
        }

        public void m6482a(int i, Header[] headerArr, File file, Throwable th) {
            EventBus.m12779a().m12795d(new StickerPackDownloadedEvent(this.f3963a, false));
            if (this.f3965c) {
                Main.m3905a(Main.f1927b.getString(2131296771));
            }
            Main.f1926a.m5685e("Sticker Pack Download Failed");
        }
    }

    /* renamed from: app.messaging.stickers.StickerManager.5 */
    class C03815 extends FileTaskListener {
        final /* synthetic */ Context f3967a;
        final /* synthetic */ File f3968b;
        final /* synthetic */ int f3969c;
        final /* synthetic */ int f3970h;
        final /* synthetic */ ImageView f3971i;
        final /* synthetic */ StickerManager f3972j;

        C03815(StickerManager stickerManager, File file, Context context, File file2, int i, int i2, ImageView imageView) {
            this.f3972j = stickerManager;
            this.f3967a = context;
            this.f3968b = file2;
            this.f3969c = i;
            this.f3970h = i2;
            this.f3971i = imageView;
            super(file);
        }

        public void m6483a(int i, Header[] headerArr, File file) {
            Object obj = (Storage.m6939a(this.f3967a, this.f3968b.getParentFile()) && file.renameTo(this.f3968b)) ? 1 : null;
            if (obj != null) {
                StickerDataSource.m4751a(new StickerEntity(this.f3969c, this.f3970h));
            }
            EventBus.m12779a().m12795d(new StickerDownloadedEvent());
            if (this.f3971i != null) {
                CustomImageLoader.m4009a().m4022b(this.f3971i, this.f3968b);
            }
        }

        public void m6484a(int i, Header[] headerArr, File file, Throwable th) {
            Main.f1926a.m5685e("Sticker Download Failed");
        }
    }

    /* renamed from: app.messaging.stickers.StickerManager.7 */
    class C03827 extends JsonHttpResponseHandler {
        final /* synthetic */ SparseArray f3973a;
        final /* synthetic */ SparseArray f3974b;
        final /* synthetic */ StickerManager f3975c;
        private List<Integer> f3976d;
        private int f3977e;

        C03827(StickerManager stickerManager, SparseArray sparseArray, SparseArray sparseArray2) {
            this.f3975c = stickerManager;
            this.f3973a = sparseArray;
            this.f3974b = sparseArray2;
            this.f3977e = this.f3973a.size();
        }

        public void m6486a(int i, Header[] headerArr, JSONObject jSONObject) {
            Main.f1926a.m5683d("syncPacksWithServer onSuccess");
            Iterator keys = jSONObject.keys();
            this.f3976d = new ArrayList();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                if (!"100".equals(str)) {
                    this.f3976d.add(Integer.valueOf(Integer.parseInt(str)));
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                        String string = jSONObject2.getString("name");
                        boolean z = jSONObject2.getBoolean("new");
                        if (this.f3974b.get(Integer.parseInt(str)) == null) {
                            StickerDataSource.m4752a(new StickerPackEntity(Integer.parseInt(str), string, null, z, true, 0, false));
                            this.f3977e++;
                        } else if (((StickerPackEntity) this.f3974b.get(Integer.parseInt(str))).m4490d()) {
                            StickerDataSource.m4756b(Integer.parseInt(str));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (this.f3977e > this.f3976d.size()) {
                StickerDataSource.m4755a(this.f3976d);
            }
            this.f3976d = null;
        }

        public void m6485a(int i, Header[] headerArr, String str, Throwable th) {
            Main.f1926a.m5683d("syncPacksWithServer onFail");
            this.f3976d = null;
        }
    }

    static {
        f3978a = null;
    }

    public int m6492a() {
        return this.f3981d;
    }

    public void m6493a(int i) {
        this.f3981d = i;
    }

    private StickerManager(Context context) {
        this.f3981d = 100;
        Handler handler = new Handler(Looper.getMainLooper());
        ContentObserver c03771 = new C03771(this, handler);
        ContentObserver c03782 = new C03782(this, handler);
        context.getContentResolver().registerContentObserver(StickerProvider.f2394a, false, c03771);
        context.getContentResolver().registerContentObserver(StickerPackProvider.f2391a, false, c03782);
        this.f3979b = StickerDataSource.m4753a();
        this.f3980c = StickerDataSource.m4759b();
        Main.f1926a.m5683d("on sticker button clicked sticker added to install pack");
    }

    public static StickerManager m6491b() {
        if (f3978a == null) {
            f3978a = new StickerManager(Main.f1927b);
        }
        return f3978a;
    }

    public SparseArray<StickerEntity> m6498c() {
        return this.f3979b;
    }

    public SparseArray<StickerPackEntity> m6499d() {
        return this.f3980c;
    }

    public void m6495a(Context context, int i) {
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        Map hashMap = new HashMap(2);
        hashMap.put("id", i + "");
        hashMap.put("density", LocalPacksManager.m6428b(context) + "");
        Context context2 = context;
        new AsyncHttpClient().m10584b(context2, "https://chatng.bisphone.com:443/api/v1/sticker/file", headerArr, new RequestParams(hashMap), new C03793(this, i));
    }

    public void m6497a(Context context, int i, boolean z) {
        if (HttpService.m5565a(i + "") == null) {
            try {
                File file = new File(Storage.m6952g() + File.separator + i);
                Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/zip")};
                RequestParams requestParams = new RequestParams();
                requestParams.m10743a("id", i + "");
                requestParams.m10743a("density", LocalPacksManager.m6428b(context) + "");
                HttpService.m5567a(new TaskBuilder().m5590a(i + "").m5585a(context).m5592b("https://chatng.bisphone.com:443/api/v1/sticker/file").m5591a(headerArr).m5589a(requestParams).m5586a(RequestType.get).m5587a(TaskPriority.low).m5588a(new C03804(this, file, i, context, z)).m5584a());
            } catch (StorageException e) {
                Storage.m6945c(context);
            }
        } else if (z) {
            Main.m3905a(Main.f1927b.getString(2131296433));
        }
    }

    public void m6496a(Context context, int i, ImageView imageView) {
        if (HttpService.m5565a(i + "") == null) {
            int i2 = i / 100;
            try {
                File file = new File(Storage.m6952g() + File.separator + i);
                File file2 = new File(StickerEntity.m4472a(i, i2));
                Header[] headerArr = new Header[]{new BasicHeader("Accept", "image/png")};
                RequestParams requestParams = new RequestParams();
                requestParams.m10743a("id", i + "");
                requestParams.m10743a("density", LocalPacksManager.m6428b(context) + "");
                HttpService.m5567a(new TaskBuilder().m5590a(i + "").m5585a(context).m5592b("https://chatng.bisphone.com:443/api/v1/sticker/file").m5591a(headerArr).m5589a(requestParams).m5586a(RequestType.get).m5587a(TaskPriority.high).m5588a(new C03815(this, file, context, file2, i, i2, imageView)).m5584a());
            } catch (StorageException e) {
            }
        }
    }

    private boolean m6489a(File file, int i) {
        boolean z = false;
        try {
            File file2 = new File(Storage.m6955i() + File.separator + i);
            Main.f1926a.m5683d("Saving sticker pack to " + file2.getAbsolutePath());
            z = Unzipper.m7076a(file2, file.getAbsolutePath());
        } catch (StorageException e) {
            Storage.m6945c(Main.f1927b);
        } catch (Throwable e2) {
            Main.f1926a.m5682c(e2);
        }
        file.delete();
        return z;
    }

    public void m6494a(Context context) {
        SparseArray clone = m6499d().clone();
        SparseArray c = StickerDataSource.m4762c();
        clone.remove(100);
        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/json")};
        RequestParams requestParams = new RequestParams();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        ResponseHandlerInterface c03827 = new C03827(this, clone, c);
        asyncHttpClient.m10584b(context, "https://chatng.bisphone.com:443/api/v1/sticker/list", headerArr, requestParams, c03827);
    }
}
