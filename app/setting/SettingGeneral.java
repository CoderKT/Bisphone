package app.setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import app.Main;
import app.account.AccountManager;
import app.common.BaseActivity;
import app.common.Constants;
import app.common.entity.ContactEntity.TYPE;
import app.contact.ContactComparatorService;
import app.database.provider.ContactProvider;
import app.signin.LogoutReceiver;
import app.util.DialogsUtil;
import butterknife.ButterKnife;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SettingGeneral extends BaseActivity {

    /* renamed from: app.setting.SettingGeneral.1 */
    class C04601 implements OnClickListener {
        final /* synthetic */ SettingGeneral f4374a;

        /* renamed from: app.setting.SettingGeneral.1.1 */
        class C04591 extends JsonHttpResponseHandler {
            final /* synthetic */ C04601 f4373a;

            C04591(C04601 c04601) {
                this.f4373a = c04601;
            }

            public void m6796a(int i, Header[] headerArr, JSONObject jSONObject) {
                Main.m3905a(this.f4373a.f4374a.getString(2131296860));
                this.f4373a.f4374a.sendBroadcast(new Intent(this.f4373a.f4374a, LogoutReceiver.class).setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT"));
            }

            public void m6795a(int i, Header[] headerArr, String str, Throwable th) {
                Main.f1926a.m5682c(th);
                if (!this.f4373a.f4374a.isFinishing()) {
                    DialogsUtil.m7014a(this.f4373a.f4374a);
                }
            }
        }

        C04601(SettingGeneral settingGeneral) {
            this.f4374a = settingGeneral;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            HttpEntity stringEntity;
            Map hashMap = new HashMap();
            hashMap.put(this.f4374a.getString(2131296539), AccountManager.m3934a().m3937c());
            hashMap.put(this.f4374a.getString(2131296536), AccountManager.m3934a().m3938d());
            hashMap.put(this.f4374a.getString(2131296535), "bisphone.com");
            JSONObject jSONObject = new JSONObject(hashMap);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            try {
                stringEntity = new StringEntity(jSONObject.toString());
            } catch (Throwable e) {
                Main.f1926a.m5680b(e);
                stringEntity = null;
            }
            asyncHttpClient.m10572a(this.f4374a, Constants.m3961f(), stringEntity, "application/json", new C04591(this));
        }
    }

    /* renamed from: app.setting.SettingGeneral.2 */
    class C04612 implements OnClickListener {
        final /* synthetic */ SettingGeneral f4375a;

        C04612(SettingGeneral settingGeneral) {
            this.f4375a = settingGeneral;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Main.m3905a(this.f4375a.getString(2131296861));
            this.f4375a.getContentResolver().delete(ContactProvider.f2364a, "type=?", new String[]{TYPE.LOCAL.toString()});
            Main.f1926a.m5683d("Settings: Starting ContactComparatorService");
            this.f4375a.startService(new Intent(this.f4375a, ContactComparatorService.class));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903093);
        setTitle(getResources().getString(2131296712));
        ButterKnife.m7741a((Activity) this);
    }

    void m6797j() {
        startActivity(new Intent(this, SettingContentLanguage.class));
    }

    void m6798k() {
        Builder builder = new Builder(this, 2131558538);
        builder.m1975a(2131296553, new C04601(this));
        builder.m1989c(2130837731);
        builder.m1974a(2131296331);
        builder.m1985b(2131296380, null);
        builder.m1984b(2131296554);
        builder.m1992c();
    }

    void m6799l() {
        Builder builder = new Builder(this, 2131558537);
        builder.m1975a(2131296731, new C04612(this));
        builder.m1985b(2131296380, null);
        builder.m1984b(2131296730);
        builder.m1974a(2131296331);
        builder.m1989c(2130837730);
        builder.m1992c();
    }
}
