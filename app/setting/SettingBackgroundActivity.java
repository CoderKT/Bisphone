package app.setting;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import app.common.BaseActivity;
import app.util.DeviceUtils;
import app.util.SharedPreferencesUtil;

public class SettingBackgroundActivity extends BaseActivity {
    private boolean f4338A;
    Runnable f4339o;
    private RelativeLayout f4340p;
    private ViewFlipper f4341q;
    private GridView f4342r;
    private TextView f4343s;
    private MessageBackgroundGridAdapter f4344t;
    private Animation f4345u;
    private Animation f4346v;
    private Animation f4347w;
    private Animation f4348x;
    private Handler f4349y;
    private int f4350z;

    /* renamed from: app.setting.SettingBackgroundActivity.1 */
    class C04541 implements OnItemClickListener {
        final /* synthetic */ SettingBackgroundActivity f4336a;

        C04541(SettingBackgroundActivity settingBackgroundActivity) {
            this.f4336a = settingBackgroundActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f4336a.f4350z = i;
            RelativeLayout b = this.f4336a.f4340p;
            this.f4336a.f4344t;
            b.setBackgroundResource(MessageBackgroundGridAdapter.m6773a(i));
            this.f4336a.m6780b(1);
        }
    }

    /* renamed from: app.setting.SettingBackgroundActivity.2 */
    class C04552 implements Runnable {
        final /* synthetic */ SettingBackgroundActivity f4337a;

        C04552(SettingBackgroundActivity settingBackgroundActivity) {
            this.f4337a = settingBackgroundActivity;
        }

        public void run() {
            this.f4337a.f4338A = false;
        }
    }

    public SettingBackgroundActivity() {
        this.f4350z = 0;
        this.f4338A = false;
        this.f4339o = new C04552(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903091);
        setTitle(getString(2131296714));
        m6785f();
        m6782j();
        m6783k();
        m6784l();
        this.f4349y = new Handler();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6782j();
    }

    protected void m6785f() {
        this.f4341q = (ViewFlipper) findViewById(2131755245);
        this.f4342r = (GridView) findViewById(2131755246);
        this.f4340p = (RelativeLayout) findViewById(2131755247);
        this.f4343s = (TextView) findViewById(2131755248);
    }

    private void m6782j() {
        int a = DeviceUtils.m7010a(this, 3, 10, 10);
        if (this.f4344t == null) {
            this.f4344t = new MessageBackgroundGridAdapter(getApplicationContext(), a, (int) (((double) a) * 1.5d));
            this.f4342r.setAdapter(this.f4344t);
            return;
        }
        this.f4344t.m6775a(a, (int) (((double) a) * 1.5d));
    }

    private void m6783k() {
        this.f4342r.setOnItemClickListener(new C04541(this));
    }

    private void m6784l() {
        this.f4345u = AnimationUtils.loadAnimation(getApplicationContext(), 2130968599);
        this.f4346v = AnimationUtils.loadAnimation(getApplicationContext(), 2130968601);
        this.f4347w = AnimationUtils.loadAnimation(getApplicationContext(), 2130968600);
        this.f4348x = AnimationUtils.loadAnimation(getApplicationContext(), 2130968602);
        this.f4341q.setOutAnimation(this.f4345u);
        this.f4341q.setInAnimation(this.f4348x);
    }

    private void m6780b(int i) {
        if (i >= 0 && !this.f4338A) {
            this.f4338A = true;
            if (i > this.f4341q.getDisplayedChild()) {
                this.f4341q.setOutAnimation(this.f4345u);
                this.f4341q.setInAnimation(this.f4348x);
                setTitle(getString(2131296719));
                this.f4341q.setDisplayedChild(i);
            } else if (i < this.f4341q.getDisplayedChild()) {
                this.f4341q.setOutAnimation(this.f4346v);
                this.f4341q.setInAnimation(this.f4347w);
                setTitle(getString(2131296714));
                this.f4341q.setDisplayedChild(i);
            }
            invalidateOptionsMenu();
            this.f4349y.postDelayed(this.f4339o, 500);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        MenuItem item = menu.getItem(0);
        if (this.f4341q.getDisplayedChild() == 1) {
            z = true;
        }
        item.setVisible(z);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820545, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.f4341q.getDisplayedChild() == 1) {
                    m6780b(0);
                    return true;
                }
                onBackPressed();
                return true;
            default:
                SharedPreferencesUtil.m7053a(getString(2131296920), this.f4350z);
                m6780b(0);
                this.f4344t.m6774a();
                this.f4344t.notifyDataSetChanged();
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onBackPressed() {
        if (this.f4341q.getDisplayedChild() > 0) {
            m6780b(this.f4341q.getDisplayedChild() - 1);
        } else {
            super.onBackPressed();
        }
    }
}
