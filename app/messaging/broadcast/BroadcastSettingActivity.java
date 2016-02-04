package app.messaging.broadcast;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.Main;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntityLite;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.BroadcastUsersDataSource;
import app.database.datasource.ContactDataSource;
import app.galley.SelectResourceFromSDCardActivity;
import app.messaging.selector.SelectRecipientActivity;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.MediaPickerUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.Utils;
import app.view.CustomRecycleView;
import app.xmpp.NormalMessageManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class BroadcastSettingActivity extends BaseActivity {
    private GetContactEntityInfo f3344A;
    private File f3345B;
    private File f3346C;
    private String f3347D;
    private String f3348E;
    private String f3349F;
    private boolean f3350G;
    private boolean f3351H;
    private boolean f3352I;
    private boolean f3353J;
    private ImageView f3354o;
    private ImageView f3355p;
    private EditText f3356q;
    private TextView f3357r;
    private TextView f3358s;
    private Button f3359t;
    private CustomRecycleView f3360u;
    private ProgressBar f3361v;
    private LinearLayoutManager f3362w;
    private ArrayList<String> f3363x;
    private ArrayList<ContactEntityLite> f3364y;
    private BroadcastSettingRecyclerAdapter f3365z;

    /* renamed from: app.messaging.broadcast.BroadcastSettingActivity.1 */
    class C02691 implements OnClickListener {
        final /* synthetic */ BroadcastSettingActivity f3337a;

        C02691(BroadcastSettingActivity broadcastSettingActivity) {
            this.f3337a = broadcastSettingActivity;
        }

        public void onClick(View view) {
            if (this.f3337a.f3350G) {
                this.f3337a.m6030o();
                return;
            }
            this.f3337a.f3351H = true;
            this.f3337a.f3355p.setVisibility(0);
            CustomImageLoader.m4009a().m4020a(this.f3337a.f3355p, this.f3337a.f3349F, null, 2130837597);
        }
    }

    /* renamed from: app.messaging.broadcast.BroadcastSettingActivity.2 */
    class C02702 implements OnClickListener {
        final /* synthetic */ BroadcastSettingActivity f3338a;

        C02702(BroadcastSettingActivity broadcastSettingActivity) {
            this.f3338a = broadcastSettingActivity;
        }

        public void onClick(View view) {
            this.f3338a.f3351H = false;
            this.f3338a.f3355p.setVisibility(8);
        }
    }

    /* renamed from: app.messaging.broadcast.BroadcastSettingActivity.3 */
    class C02713 implements OnClickListener {
        final /* synthetic */ BroadcastSettingActivity f3339a;

        C02713(BroadcastSettingActivity broadcastSettingActivity) {
            this.f3339a = broadcastSettingActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.f3339a, SelectRecipientActivity.class);
            intent.putStringArrayListExtra("recipients_list", this.f3339a.f3363x);
            intent.putExtra("extra_is_broadcast_edit_mode", true);
            this.f3339a.startActivityForResult(intent, 5603);
        }
    }

    /* renamed from: app.messaging.broadcast.BroadcastSettingActivity.4 */
    class C02744 implements DialogInterface.OnClickListener {
        final /* synthetic */ BroadcastSettingActivity f3342a;

        /* renamed from: app.messaging.broadcast.BroadcastSettingActivity.4.1 */
        class C02721 implements DialogInterface.OnClickListener {
            final /* synthetic */ C02744 f3340a;

            C02721(C02744 c02744) {
                this.f3340a = c02744;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.messaging.broadcast.BroadcastSettingActivity.4.2 */
        class C02732 implements DialogInterface.OnClickListener {
            final /* synthetic */ C02744 f3341a;

            C02732(C02744 c02744) {
                this.f3341a = c02744;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f3341a.f3342a.f3349F = null;
                BroadcastListDataSource.m4504a().m4515b(this.f3341a.f3342a.f3347D, this.f3341a.f3342a.f3349F);
                CustomImageLoader.m4009a().m4020a(this.f3341a.f3342a.f3354o, this.f3341a.f3342a.f3349F, null, 2130837597);
                this.f3341a.f3342a.f3345B = null;
                this.f3341a.f3342a.f3352I = false;
                this.f3341a.f3342a.f3353J = true;
            }
        }

        C02744(BroadcastSettingActivity broadcastSettingActivity) {
            this.f3342a = broadcastSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (PermissionUtil.m7044a(PermissionType.storage)) {
                        this.f3342a.m6025k();
                    } else {
                        PermissionUtil.m7042a(this.f3342a, PermissionType.storage, 2);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f3342a.f3346C = MediaPickerUtil.m7032a(this.f3342a, 5601);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    Builder builder = new Builder(this.f3342a, 2131558538);
                    builder.m1980a(this.f3342a.getString(2131296331));
                    builder.m1989c(2130837731);
                    builder.m1986b(this.f3342a.getString(2131296742));
                    builder.m1985b(2131296784, new C02721(this));
                    builder.m1981a(this.f3342a.getString(2131296786), new C02732(this));
                    builder.m1989c(2130837731);
                    builder.m1992c();
                default:
            }
        }
    }

    class GetContactEntityInfo extends AsyncTask<Void, Void, ArrayList<ContactEntityLite>> {
        final /* synthetic */ BroadcastSettingActivity f3343a;

        GetContactEntityInfo(BroadcastSettingActivity broadcastSettingActivity) {
            this.f3343a = broadcastSettingActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6004a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m6005a((ArrayList) obj);
        }

        protected ArrayList<ContactEntityLite> m6004a(Void... voidArr) {
            return ContactDataSource.m4553a().m4571c(this.f3343a.f3363x);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f3343a.f3360u.setEmptyViewVisibility(0);
        }

        protected void m6005a(ArrayList<ContactEntityLite> arrayList) {
            super.onPostExecute(arrayList);
            this.f3343a.f3360u.setEmptyViewVisibility(8);
            this.f3343a.f3364y = arrayList;
            this.f3343a.f3357r.setText(String.format("(%d)", new Object[]{Integer.valueOf(this.f3343a.f3363x.size())}));
            this.f3343a.f3365z.m6044a(this.f3343a.f3364y);
            this.f3343a.f3365z.m3345c();
        }
    }

    public BroadcastSettingActivity() {
        this.f3346C = null;
        this.f3350G = false;
        this.f3351H = false;
        this.f3352I = false;
        this.f3353J = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903068);
        m6035j();
        m6031p();
        m6033r();
        m6027l();
        this.f3344A = new GetContactEntityInfo(this);
        this.f3344A.execute(new Void[0]);
    }

    protected void m6034f() {
        this.f3354o = (ImageView) findViewById(2131755108);
        this.f3355p = (ImageView) findViewById(2131755115);
        this.f3356q = (EditText) findViewById(2131755110);
        this.f3357r = (TextView) findViewById(2131755111);
        this.f3360u = (CustomRecycleView) findViewById(2131755112);
        this.f3358s = (TextView) findViewById(2131755109);
        this.f3361v = (ProgressBar) findViewById(2131755113);
        this.f3359t = (Button) findViewById(2131755114);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        MenuItem item = menu.getItem(0);
        MenuItem item2 = menu.getItem(1);
        if (!this.f3350G) {
            z = true;
        }
        item.setVisible(z);
        item2.setVisible(this.f3350G);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820572, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.f3350G) {
                    m6013b(false);
                    return false;
                }
                break;
            case 2131755653:
                if (!this.f3356q.getText().toString().trim().equalsIgnoreCase("")) {
                    if (!this.f3356q.getText().toString().equals(this.f3348E)) {
                        this.f3348E = this.f3356q.getText().toString();
                        NormalMessageManager.m7447a().m7472g(this.f3347D).m4165a(this.f3348E);
                        BroadcastListDataSource.m4504a().m4509a(this.f3347D, this.f3348E);
                    }
                    if (this.f3353J && !m6029n().equals(this.f3349F)) {
                        if (!m6029n().isEmpty()) {
                            this.f3349F = m6029n();
                        }
                        NormalMessageManager.m7447a().m7472g(this.f3347D).m4166b(this.f3349F);
                        BroadcastListDataSource.m4504a().m4515b(this.f3347D, this.f3349F);
                    }
                    m6028m();
                    m6013b(false);
                    break;
                }
                this.f3356q.setError(getString(2131296351));
                return true;
            case 2131755659:
                this.f3356q.setText(this.f3348E);
                this.f3356q.setSelection(this.f3356q.getText().length());
                CustomImageLoader.m4009a().m4020a(this.f3354o, this.f3349F, null, 2130837597);
                m6013b(true);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void m6025k() {
        try {
            this.f3345B = FileUtil.m7016a("cropped_image", null, Storage.m6952g());
            if (this.f3345B == null || Uri.fromFile(this.f3345B).toString() == null) {
                Main.m3905a(getString(2131296438));
                return;
            }
            Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
            intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_SINGLE_PICK");
            intent.putExtra("output", Uri.fromFile(this.f3345B).toString());
            startActivityForResult(intent, 5600);
        } catch (StorageException e) {
            Storage.m6945c(this);
        } catch (Throwable e2) {
            Main.f1926a.m5682c(e2);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Throwable e;
        if (i2 == -1) {
            switch (i) {
                case 5600:
                case 5602:
                    if (this.f3345B == null || !this.f3345B.exists()) {
                        Main.m3905a(getString(2131296837));
                        return;
                    }
                    try {
                        File file = new File(Storage.m6941b() + File.separator + m6029n());
                        file.createNewFile();
                        FileUtil.m7022a(this.f3345B, file, true);
                        CustomImageLoader.m4009a().m4017a(this.f3354o, this.f3345B, null);
                        this.f3352I = true;
                        this.f3353J = true;
                    } catch (StorageException e2) {
                        e = e2;
                        Main.f1926a.m5682c(e);
                    } catch (IOException e3) {
                        e = e3;
                        Main.f1926a.m5682c(e);
                    }
                case 5601:
                    if (this.f3346C == null) {
                        Main.m3905a(getString(2131296837));
                    } else {
                        this.f3345B = MediaPickerUtil.m7033a(this, Uri.fromFile(this.f3346C), 5602);
                    }
                case 5603:
                    this.f3363x = intent.getStringArrayListExtra("extra_is_broadcast_edit_mode");
                    NormalMessageManager.m7447a().m7460b(this.f3363x);
                    String a = Utils.m7080a(this.f3363x);
                    BroadcastListDataSource.m4504a().m4518c(this.f3347D, a);
                    BroadcastUsersDataSource.m4519a().m4526b(this.f3347D, this.f3363x);
                    NormalMessageManager.m7447a().m7472g(this.f3347D).m4169c(a);
                    this.f3344A = new GetContactEntityInfo(this);
                    this.f3344A.execute(new Void[0]);
                default:
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0 && iArr[0] == 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6025k();
                default:
            }
        }
    }

    public void onBackPressed() {
        if (this.f3350G) {
            m6013b(false);
        } else if (this.f3351H) {
            this.f3355p.setVisibility(8);
            this.f3351H = false;
        } else {
            super.onBackPressed();
        }
    }

    private void m6027l() {
        this.f3354o.setOnClickListener(new C02691(this));
        this.f3355p.setOnClickListener(new C02702(this));
        this.f3359t.setOnClickListener(new C02713(this));
    }

    private void m6028m() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private String m6029n() {
        try {
            return FileUtil.m7024b(this.f3345B);
        } catch (IOException e) {
            return "";
        } catch (NullPointerException e2) {
            return "";
        }
    }

    private void m6030o() {
        Builder builder = new Builder(this, 2131558535);
        CharSequence[] charSequenceArr = !this.f3352I ? new CharSequence[]{getString(2131296452), getString(2131296379)} : new CharSequence[]{getString(2131296452), getString(2131296379), getString(2131296667)};
        builder.m1980a(getString(2131296393));
        builder.m1983a(charSequenceArr, new C02744(this));
        builder.m1992c();
    }

    public void m6035j() {
        this.f3347D = getIntent().getStringExtra("broadcast_id_key");
        this.f3363x = getIntent().getStringArrayListExtra("broadcast_username_key");
    }

    private void m6031p() {
        this.f3362w = new LinearLayoutManager(this);
        this.f3360u.setLayoutManager(this.f3362w);
        this.f3360u.setEmptyView(this.f3361v);
        m6032q();
    }

    private void m6032q() {
        if (this.f3365z == null) {
            this.f3365z = new BroadcastSettingRecyclerAdapter(this);
            this.f3360u.setAdapter(this.f3365z);
        }
    }

    private void m6033r() {
        setTitle(getString(2131296353));
        m6013b(this.f3350G);
        this.f3348E = NormalMessageManager.m7447a().m7472g(this.f3347D).m4173g();
        this.f3349F = NormalMessageManager.m7447a().m7472g(this.f3347D).m4174h();
        this.f3356q.setText(this.f3348E);
        CustomImageLoader.m4009a().m4020a(this.f3354o, this.f3349F, null, 2130837597);
        boolean z = (this.f3349F == null || this.f3349F.isEmpty()) ? false : true;
        this.f3352I = z;
    }

    private void m6013b(boolean z) {
        this.f3350G = z;
        this.f3356q.setFocusable(z);
        this.f3356q.setFocusableInTouchMode(z);
        this.f3356q.setClickable(z);
        this.f3356q.requestFocus();
        this.f3358s.setVisibility(z ? 0 : 8);
        invalidateOptionsMenu();
    }
}
