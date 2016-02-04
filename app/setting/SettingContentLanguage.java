package app.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import app.Main;
import app.common.BaseActivity;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SettingContentLanguage extends BaseActivity {
    ListView f4353o;
    String[] f4354p;
    SettingContentLanguageAdapter f4355q;
    SearchView f4356r;

    /* renamed from: app.setting.SettingContentLanguage.1 */
    class C04561 implements OnActionExpandListener {
        final /* synthetic */ SettingContentLanguage f4351a;

        C04561(SettingContentLanguage settingContentLanguage) {
            this.f4351a = settingContentLanguage;
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return true;
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            this.f4351a.finish();
            return false;
        }
    }

    /* renamed from: app.setting.SettingContentLanguage.2 */
    class C04572 implements OnQueryTextListener {
        final /* synthetic */ SettingContentLanguage f4352a;

        C04572(SettingContentLanguage settingContentLanguage) {
            this.f4352a = settingContentLanguage;
        }

        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        public boolean onQueryTextChange(String str) {
            this.f4352a.f4355q.getFilter().filter(str);
            return false;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903092);
        ButterKnife.m7741a((Activity) this);
        this.f4354p = getResources().getStringArray(2131361798);
        if (SharedPreferencesUtil.m7052a(getResources().getString(2131296924)) == null || SharedPreferencesUtil.m7052a(getResources().getString(2131296924)).length() == 0) {
            m6788j();
        }
        m6786k();
    }

    private void m6786k() {
        this.f4355q = new SettingContentLanguageAdapter(this, this.f4354p, m6787l());
        this.f4353o.setAdapter(this.f4355q);
    }

    private HashMap<String, Integer> m6787l() {
        HashMap<String, Integer> hashMap = new HashMap();
        String[] split = SharedPreferencesUtil.m7052a(getResources().getString(2131296924)).split(",");
        for (String split2 : split) {
            int parseInt = Integer.parseInt(split2.split("-")[0]);
            hashMap.put(this.f4354p[parseInt], Integer.valueOf(parseInt));
        }
        return hashMap;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820567, menu);
        MenuItem findItem = menu.findItem(2131755633);
        this.f4356r = (SearchView) findItem.getActionView();
        findItem.setOnActionExpandListener(new C04561(this));
        findItem.expandActionView();
        if (this.f4356r == null) {
            return false;
        }
        this.f4356r.setIconified(false);
        this.f4356r.setQueryHint(getString(2131296541));
        this.f4356r.setOnQueryTextListener(new C04572(this));
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755653:
                if (!(this.f4355q == null || this.f4355q.m6791a() == null)) {
                    if (this.f4355q.m6791a().size() != 0) {
                        String[] stringArray = getResources().getStringArray(2131361797);
                        String str = "";
                        Iterator it = this.f4355q.m6791a().entrySet().iterator();
                        while (it.hasNext()) {
                            String str2;
                            Entry entry = (Entry) it.next();
                            int i = 0;
                            while (i < stringArray.length) {
                                if (entry.getValue().equals(stringArray[i])) {
                                    str2 = str + entry.getValue() + "-" + i + ",";
                                    it.remove();
                                    str = str2;
                                } else {
                                    i++;
                                }
                            }
                            str2 = str;
                            it.remove();
                            str = str2;
                        }
                        SharedPreferencesUtil.m7055a(getResources().getString(2131296924), str.substring(0, str.length() - 1));
                        finish();
                        break;
                    }
                    Main.m3905a(getResources().getString(2131296710));
                    break;
                }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void m6788j() {
        int i = 0;
        String[] stringArray = getResources().getStringArray(2131361793);
        String[] stringArray2 = getResources().getStringArray(2131361797);
        String a = SharedPreferencesUtil.m7052a(getResources().getString(2131296925));
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            if (stringArray[i2].equals(a)) {
                while (i < this.f4354p.length) {
                    if (stringArray2[i2].equals(this.f4354p[i])) {
                        SharedPreferencesUtil.m7055a(getResources().getString(2131296924), i + "-" + i2);
                        return;
                    }
                    i++;
                }
                return;
            }
        }
    }
}
