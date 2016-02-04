package app.profile;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import app.Main;
import app.account.AccountManager;
import app.common.Constants;
import app.common.LocalizeActivity;
import app.common.entity.ContactEntity.TYPE;
import app.contact.ContactComparatorService;
import app.database.datasource.ChannelDataSource;
import app.database.provider.ContactProvider;
import app.database.provider.ConversationGroupProvider;
import app.database.provider.ConversationNormalProvider;
import app.database.provider.HistoryChannelProvider;
import app.database.provider.HistoryGroupProvider;
import app.database.provider.HistoryOneToOneProvider;
import app.signin.LogoutReceiver;
import app.util.DialogsUtil;
import app.util.FileUtil;
import butterknife.ButterKnife;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SettingsActivity extends LocalizeActivity {
    RelativeLayout f4302k;
    RelativeLayout f4303o;

    /* renamed from: app.profile.SettingsActivity.1 */
    class C04481 implements OnClickListener {
        final /* synthetic */ SettingsActivity f4298a;

        C04481(SettingsActivity settingsActivity) {
            this.f4298a = settingsActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            ContentResolver contentResolver = this.f4298a.getContentResolver();
            contentResolver.delete(HistoryOneToOneProvider.f2383a, null, null);
            contentResolver.delete(HistoryGroupProvider.f2380a, null, null);
            contentResolver.delete(HistoryChannelProvider.f2377a, null, null);
            contentResolver.delete(ConversationGroupProvider.f2368a, null, null);
            contentResolver.delete(ConversationNormalProvider.f2373a, null, null);
            ChannelDataSource.m4540a(this.f4298a);
            FileUtil.m7021a(this.f4298a.getExternalCacheDir());
            Main.m3905a(this.f4298a.getString(2131296859));
            ((NotificationManager) this.f4298a.getSystemService("notification")).cancelAll();
        }
    }

    /* renamed from: app.profile.SettingsActivity.2 */
    class C04502 implements OnClickListener {
        final /* synthetic */ SettingsActivity f4300a;

        /* renamed from: app.profile.SettingsActivity.2.1 */
        class C04491 extends JsonHttpResponseHandler {
            final /* synthetic */ C04502 f4299a;

            C04491(C04502 c04502) {
                this.f4299a = c04502;
            }

            public void m6751a(int i, Header[] headerArr, JSONObject jSONObject) {
                Main.m3905a(this.f4299a.f4300a.getString(2131296860));
                this.f4299a.f4300a.sendBroadcast(new Intent(this.f4299a.f4300a, LogoutReceiver.class).setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT"));
            }

            public void m6750a(int i, Header[] headerArr, String str, Throwable th) {
                Main.f1926a.m5682c(th);
                if (!this.f4299a.f4300a.isFinishing()) {
                    DialogsUtil.m7014a(this.f4299a.f4300a);
                }
            }
        }

        C04502(SettingsActivity settingsActivity) {
            this.f4300a = settingsActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            HttpEntity stringEntity;
            Map hashMap = new HashMap();
            hashMap.put(this.f4300a.getString(2131296539), AccountManager.m3934a().m3937c());
            hashMap.put(this.f4300a.getString(2131296536), AccountManager.m3934a().m3938d());
            hashMap.put(this.f4300a.getString(2131296535), "bisphone.com");
            JSONObject jSONObject = new JSONObject(hashMap);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            try {
                stringEntity = new StringEntity(jSONObject.toString());
            } catch (Throwable e) {
                Main.f1926a.m5680b(e);
                stringEntity = null;
            }
            asyncHttpClient.m10572a(this.f4300a, Constants.m3961f(), stringEntity, "application/json", new C04491(this));
        }
    }

    /* renamed from: app.profile.SettingsActivity.3 */
    class C04513 implements OnClickListener {
        final /* synthetic */ SettingsActivity f4301a;

        C04513(SettingsActivity settingsActivity) {
            this.f4301a = settingsActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Main.m3905a(this.f4301a.getString(2131296861));
            this.f4301a.getContentResolver().delete(ContactProvider.f2364a, "type=?", new String[]{TYPE.LOCAL.toString()});
            Main.f1926a.m5683d("Settings: Starting ContactComparatorService");
            this.f4301a.startService(new Intent(this.f4301a, ContactComparatorService.class));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903099);
        ButterKnife.m7741a((Activity) this);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    void m6752j() {
        Builder builder = new Builder(this, 2131558535);
        builder.m1975a(2131296724, new C04481(this));
        builder.m1985b(2131296380, null);
        builder.m1984b(2131296723);
        builder.m1992c();
    }

    void m6753k() {
        Builder builder = new Builder(this, 2131558538);
        builder.m1974a(2131296331);
        builder.m1989c(2130837731);
        builder.m1975a(2131296553, new C04502(this));
        builder.m1985b(2131296380, null);
        builder.m1984b(2131296554);
        builder.m1992c();
    }

    void m6754l() {
        Builder builder = new Builder(this, 2131558537);
        builder.m1974a(2131296331);
        builder.m1989c(2130837730);
        builder.m1975a(2131296731, new C04513(this));
        builder.m1985b(2131296380, null);
        builder.m1984b(2131296730);
        builder.m1992c();
    }
}
