package app.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.database.datasource.ContactDataSource;
import app.setting.SettingMain;

public class MoreInfoActivity extends BaseActivity implements OnClickListener {
    private ImageView f4228o;
    private ImageView f4229p;
    private RelativeLayout f4230q;
    private RelativeLayout f4231r;
    private RelativeLayout f4232s;
    private RelativeLayout f4233t;
    private ContactEntity f4234u;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903084);
        m6701j();
        m6702k();
        m6703l();
    }

    private void m6701j() {
        this.f4228o = (ImageView) findViewById(2131755116);
        this.f4229p = (ImageView) findViewById(2131755209);
        this.f4230q = (RelativeLayout) findViewById(2131755210);
        this.f4231r = (RelativeLayout) findViewById(2131755212);
        this.f4232s = (RelativeLayout) findViewById(2131755214);
        this.f4233t = (RelativeLayout) findViewById(2131755216);
    }

    private void m6702k() {
        this.f4234u = ContactDataSource.m4553a().m4581g();
        CustomImageLoader.m4009a().m4020a(this.f4228o, this.f4234u.m4212o(), null, 2130837592);
        CustomImageLoader.m4009a().m4020a(this.f4229p, this.f4234u.m4212o(), null, 2130837596);
    }

    private void m6703l() {
        this.f4230q.setOnClickListener(this);
        this.f4231r.setOnClickListener(this);
        this.f4232s.setOnClickListener(this);
        this.f4231r.setOnClickListener(this);
        this.f4233t.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755210:
                startActivityForResult(new Intent(this, ProfileActivity.class), 3603);
            case 2131755212:
                startActivity(new Intent(this, SettingMain.class));
            case 2131755214:
                startActivity(new Intent(this, InviteActivity.class));
            case 2131755216:
                startActivity(new Intent(this, AboutActivity.class));
            default:
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            String stringExtra = intent.getStringExtra("profile_updated_photo_token");
            switch (i) {
                case 3603:
                    CustomImageLoader.m4009a().m4020a(this.f4228o, stringExtra, null, 2130837592);
                    CustomImageLoader.m4009a().m4020a(this.f4229p, stringExtra, null, 2130837596);
                default:
            }
        }
    }
}
