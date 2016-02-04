package app.xmpp;

import android.content.ContentValues;
import android.content.Context;
import app.Main;
import app.account.AccountManager;
import app.common.Constants;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.STATUS;
import app.common.entity.ContactEntity.TYPE;
import app.database.datasource.ContactDataSource;
import app.events.vcard.VCardInfoLoadEvent;
import app.events.vcard.VCardLoadEvent;
import app.events.vcard.VCardLoadEvent.VCardLoadListener;
import app.events.vcard.VCardRefreshEvent;
import app.events.vcard.VCardSaveEvent;
import app.events.vcard.VCardSaveEvent.VCardSaveListener;
import app.events.vcard.VCardSuccessEvent;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.TaskListener;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.DeviceUtils;
import app.util.SharedPreferencesUtil;
import app.xmpp.packet.VcardInfoEntity;
import app.xmpp.packet.vcard.VCard;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public class VCardHandler {
    public static VCardHandler f5004a;
    private HashMap<String, Integer> f5005b;
    private Context f5006c;

    /* renamed from: app.xmpp.VCardHandler.1 */
    class C05641 implements VCardSaveListener {
        final /* synthetic */ VCardHandler f4999a;

        C05641(VCardHandler vCardHandler) {
            this.f4999a = vCardHandler;
        }

        public void m7493a() {
            SharedPreferencesUtil.m7054a(this.f4999a.f5006c.getString(2131296929), Boolean.valueOf(true));
            SharedPreferencesUtil.m7054a(this.f4999a.f5006c.getString(2131296928), Boolean.valueOf(true));
            EventBus.m12779a().m12795d(new VCardSuccessEvent());
        }

        public void m7494b() {
            Main.f1926a.m5683d("vCard save failed");
        }
    }

    /* renamed from: app.xmpp.VCardHandler.2 */
    class C05652 implements VCardLoadListener {
        final /* synthetic */ HashMap f5000a;
        final /* synthetic */ VCardHandler f5001b;

        C05652(VCardHandler vCardHandler, HashMap hashMap) {
            this.f5001b = vCardHandler;
            this.f5000a = hashMap;
        }

        public void m7495a(List<VCard> list) {
            if (list != null && list.size() != 0) {
                ArrayList arrayList = new ArrayList();
                HashMap hashMap = new HashMap();
                for (int i = 0; i < list.size(); i++) {
                    JabberId a = JabberId.m7386a(((VCard) list.get(i)).getFrom());
                    if (a != null) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("username", a.m7387a());
                        VCardInsertUpdateEntity vCardInsertUpdateEntity = (VCardInsertUpdateEntity) this.f5000a.get(a.m7391e());
                        if (vCardInsertUpdateEntity != null) {
                            Main.f1926a.m5683d(a.m7387a() + " " + vCardInsertUpdateEntity.m7508a());
                            contentValues.put("vcard_nickname", ((VCard) list.get(i)).m7725b());
                            contentValues.put("vcard_avatar", ((VCard) list.get(i)).m7723a());
                            contentValues.put("vCard_checked_time", Long.valueOf(System.currentTimeMillis()));
                            hashMap.put(a.m7387a(), new VcardInfoEntity(((VCard) list.get(i)).m7725b(), ((VCard) list.get(i)).m7723a()));
                            if (ContactDataSource.m4553a().m4561a(a.m7387a(), contentValues, false) == 0) {
                                contentValues.put("is_registered", Boolean.valueOf(true));
                                contentValues.put(Status.ELEMENT, STATUS.SYNCED.toString());
                                contentValues.put("type", TYPE.REMOTE.toString());
                                contentValues.put("recently_joined", Integer.valueOf(0));
                                ContactDataSource.m4553a().m4561a(a.m7387a(), contentValues, true);
                            }
                        }
                    }
                }
                EventBus.m12779a().m12795d(new VCardRefreshEvent(this.f5000a));
                EventBus.m12779a().m12795d(new VCardInfoLoadEvent(hashMap));
            }
        }
    }

    /* renamed from: app.xmpp.VCardHandler.3 */
    class C05663 extends TaskListener {
        final /* synthetic */ String f5002a;
        final /* synthetic */ VCardHandler f5003b;

        C05663(VCardHandler vCardHandler, String str) {
            this.f5003b = vCardHandler;
            this.f5002a = str;
        }

        public void m7496a(int i, Header[] headerArr, byte[] bArr) {
            VCardHandler.m7499a().m7501a(this.f5002a, ContactDataSource.m4553a().m4581g().m4209l());
        }

        public void m7497a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Main.f1926a.m5683d("VCard Avatar Upload Failed");
        }
    }

    static {
        f5004a = null;
    }

    protected VCardHandler(Context context) {
        this.f5006c = context;
        this.f5005b = new HashMap();
    }

    public static VCardHandler m7499a() {
        if (f5004a == null) {
            f5004a = new VCardHandler(Main.f1927b);
        }
        return f5004a;
    }

    public void m7500a(String str) {
        if (this.f5005b == null) {
            this.f5005b = new HashMap();
        }
        this.f5005b.put(str, Integer.valueOf(1));
    }

    public void m7505b(String str) {
        if (this.f5005b != null) {
            this.f5005b.remove(str);
        }
    }

    public boolean m7506c(String str) {
        return (this.f5005b == null || this.f5005b.get(str) == null) ? false : true;
    }

    public void m7501a(String str, String str2) {
        VCard vCard = new VCard();
        vCard.m7724a(AccountManager.m3934a().m3937c());
        if (str != null) {
            vCard.m7726b(str);
        }
        if (str2 != null) {
            vCard.m7727c(str2);
        }
        ContactEntity g = ContactDataSource.m4553a().m4581g();
        g.m4199g(str2);
        ContactDataSource.m4553a().m4566a(g);
        EventBus.m12779a().m12795d(new VCardSaveEvent(vCard, new C05641(this)));
    }

    public void m7507d(String str) {
        if (!m7506c(str)) {
            m7500a(str);
            HashMap hashMap = new HashMap();
            hashMap.put(str, new VCardInsertUpdateEntity(str, false));
            m7502a(hashMap);
        }
    }

    public void m7503a(List<String> list) {
        HashMap hashMap = new HashMap();
        for (String str : list) {
            VCardInsertUpdateEntity vCardInsertUpdateEntity = new VCardInsertUpdateEntity(str, false);
            if (!m7506c(str)) {
                m7500a(str);
                hashMap.put(str, vCardInsertUpdateEntity);
            }
        }
        m7502a(hashMap);
    }

    public void m7502a(HashMap<String, VCardInsertUpdateEntity> hashMap) {
        if (hashMap != null && hashMap.size() != 0) {
            VCardLoadEvent vCardLoadEvent = new VCardLoadEvent(new C05652(this, hashMap));
            for (Entry entry : hashMap.entrySet()) {
                if (!(entry == null || entry.getKey() == null)) {
                    vCardLoadEvent.m4958a(entry.getKey().toString());
                }
            }
            EventBus.m12779a().m12795d(vCardLoadEvent);
        }
    }

    public void m7504b() {
        String l;
        if (ContactDataSource.m4553a().m4580f()) {
            l = ContactDataSource.m4553a().m4581g().m4209l();
        } else {
            l = null;
        }
        String o = ContactDataSource.m4553a().m4581g().m4212o();
        if (!SharedPreferencesUtil.m7060b(this.f5006c.getString(2131296928), Boolean.valueOf(false))) {
            if (o == null) {
                m7501a(null, l);
                return;
            }
            try {
                File file = new File(Storage.m6941b() + File.separator + o);
                try {
                    RequestParams requestParams = new RequestParams();
                    requestParams.m10743a("username", AccountManager.m3934a().m3937c());
                    requestParams.m10743a("uuid", DeviceUtils.m7012a(Main.f1927b));
                    requestParams.m10743a("host", "bisphone.com");
                    requestParams.m10741a("uploadedfile", file);
                    HttpService.m5567a(new TaskBuilder().m5590a(o).m5585a(this.f5006c).m5592b(Constants.m3959d()).m5589a(requestParams).m5586a(RequestType.post).m5587a(TaskPriority.high).m5588a(new C05663(this, o)).m5584a());
                } catch (FileNotFoundException e) {
                }
            } catch (StorageException e2) {
                Storage.m6945c(this.f5006c);
            }
        }
    }
}
