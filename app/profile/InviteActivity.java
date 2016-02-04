package app.profile;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import app.common.BaseActivity;

public class InviteActivity extends BaseActivity implements OnClickListener {
    private RelativeLayout f4223o;
    private RelativeLayout f4224p;
    private RelativeLayout f4225q;
    private RelativeLayout f4226r;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903081);
        m6695j();
        m6696k();
    }

    private void m6695j() {
        this.f4223o = (RelativeLayout) findViewById(2131755194);
        this.f4224p = (RelativeLayout) findViewById(2131755196);
        this.f4225q = (RelativeLayout) findViewById(2131755198);
        this.f4226r = (RelativeLayout) findViewById(2131755200);
        setTitle(getString(2131296340));
    }

    private void m6696k() {
        this.f4223o.setOnClickListener(this);
        this.f4224p.setOnClickListener(this);
        this.f4225q.setOnClickListener(this);
        this.f4226r.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755194:
                InviteUtil.m6697a(this);
            case 2131755196:
                InviteUtil.m6698b(this);
            case 2131755198:
                InviteUtil.m6699c(this);
            case 2131755200:
                InviteUtil.m6700d(this);
            default:
        }
    }
}
