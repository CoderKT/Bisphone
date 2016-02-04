package app.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.database.datasource.ContactDataSource;
import app.events.vcard.VCardSuccessEvent;
import app.galley.SelectResourceFromSDCardActivity;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.MediaPickerUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.SharedPreferencesUtil;
import app.xmpp.VCardHandler;
import de.greenrobot.event.EventBus;
import java.io.File;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class ProfileActivity extends BaseActivity implements OnClickListener {
    private TextView f4238o;
    private EditText f4239p;
    private ImageView f4240q;
    private ImageView f4241r;
    private boolean f4242s;
    private File f4243t;
    private File f4244u;
    private ContactEntity f4245v;

    /* renamed from: app.profile.ProfileActivity.1 */
    class C04411 implements DialogInterface.OnClickListener {
        final /* synthetic */ ProfileActivity f4237a;

        /* renamed from: app.profile.ProfileActivity.1.1 */
        class C04391 implements DialogInterface.OnClickListener {
            final /* synthetic */ C04411 f4235a;

            C04391(C04411 c04411) {
                this.f4235a = c04411;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.profile.ProfileActivity.1.2 */
        class C04402 implements DialogInterface.OnClickListener {
            final /* synthetic */ C04411 f4236a;

            C04402(C04411 c04411) {
                this.f4236a = c04411;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                CustomImageLoader.m4009a().m4020a(this.f4236a.f4237a.f4241r, null, null, 2130837596);
                SharedPreferencesUtil.m7054a(this.f4236a.f4237a.getString(2131296928), Boolean.valueOf(false));
                ContactEntity g = ContactDataSource.m4553a().m4581g();
                g.m4205j(null);
                ContactDataSource.m4553a().m4566a(g);
                VCardHandler.m7499a().m7501a(null, g.m4209l());
            }
        }

        C04411(ProfileActivity profileActivity) {
            this.f4237a = profileActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (PermissionUtil.m7044a(PermissionType.storage)) {
                        this.f4237a.m6707j();
                    } else {
                        PermissionUtil.m7042a(this.f4237a, PermissionType.storage, 2);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f4237a.f4243t = MediaPickerUtil.m7032a(this.f4237a, 3601);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    Builder builder = new Builder(this.f4237a, 2131558538);
                    builder.m1989c(2130837731);
                    builder.m1974a(2131296331);
                    builder.m1986b(this.f4237a.getString(2131296742));
                    builder.m1985b(2131296784, new C04391(this));
                    builder.m1981a(this.f4237a.getString(2131296786), new C04402(this));
                    builder.m1992c();
                default:
            }
        }
    }

    public ProfileActivity() {
        this.f4242s = false;
        this.f4243t = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m6710m();
        this.f4245v = ContactDataSource.m4553a().m4581g();
        m6711n();
        if (bundle != null && bundle.getString("filePath") != null) {
            this.f4244u = new File(bundle.getString("filePath"));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6710m();
        m6711n();
    }

    public void onStart() {
        super.onStart();
        EventBus.m12779a().m12791a((Object) this);
    }

    protected void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
        EventBus.m12779a().m12794c(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 3600:
                case 3602:
                    if (this.f4244u == null || !this.f4244u.exists()) {
                        Main.m3905a(getString(2131296837));
                        return;
                    }
                    MediaPickerUtil.m7034a(this.f4244u.getPath(), this.f4241r);
                    SharedPreferencesUtil.m7054a(getString(2131296928), Boolean.valueOf(false));
                    this.f4241r.setAlpha(0.4f);
                    SharedPreferencesUtil.m7054a(getString(2131296929), Boolean.valueOf(false));
                    VCardHandler.m7499a().m7504b();
                case 3601:
                    if (this.f4243t == null) {
                        Main.m3905a(getString(2131296837));
                    } else {
                        this.f4244u = MediaPickerUtil.m7033a(this, Uri.fromFile(this.f4243t), 3602);
                    }
                default:
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0 && iArr[0] == 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6707j();
                default:
            }
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.f4244u != null) {
            bundle.putString("filePath", this.f4244u.getAbsolutePath());
        }
    }

    public void onClick(View view) {
        boolean z = true;
        switch (view.getId()) {
            case 2131755116:
                Builder builder = new Builder(this, 2131558535);
                CharSequence[] charSequenceArr = this.f4245v.m4212o() == null ? new CharSequence[]{getString(2131296452), getString(2131296379)} : new CharSequence[]{getString(2131296452), getString(2131296379), getString(2131296667)};
                builder.m1980a(getString(2131296393));
                builder.m1983a(charSequenceArr, new C04411(this));
                builder.m1992c();
            case 2131755223:
                if (this.f4242s) {
                    String replace = this.f4239p.getText().toString().replace('\u200c', ' ').replace('\u200d', ' ').replace('\u200c', ' ').replace('\u200d', ' ');
                    while (replace.contains("  ")) {
                        replace = replace.replace("  ", " ");
                    }
                    if (replace.trim().length() > 2) {
                        this.f4240q.setImageResource(2130837788);
                        this.f4239p.setFocusable(false);
                        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.f4239p.getWindowToken(), 0);
                        VCardHandler.m7499a().m7501a(ContactDataSource.m4553a().m4581g().m4212o(), replace.trim());
                        this.f4242s = !this.f4242s;
                        return;
                    }
                    Main.m3905a(getResources().getString(2131296591));
                    return;
                }
                this.f4240q.setImageResource(2130837789);
                this.f4239p.setFocusableInTouchMode(true);
                this.f4239p.requestFocus();
                this.f4239p.setSelection(this.f4239p.getText().length());
                ((InputMethodManager) getSystemService("input_method")).toggleSoftInputFromWindow(this.f4239p.getWindowToken(), 2, 0);
                if (this.f4242s) {
                    z = false;
                }
                this.f4242s = z;
            default:
        }
    }

    private void m6707j() {
        try {
            this.f4244u = FileUtil.m7016a("cropped_image", null, Storage.m6952g());
            if (this.f4244u == null || Uri.fromFile(this.f4244u).toString() == null) {
                Main.m3905a(getString(2131296438));
                return;
            }
            Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
            intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_SINGLE_PICK");
            intent.putExtra("output", Uri.fromFile(this.f4244u).toString());
            startActivityForResult(intent, 3600);
        } catch (StorageException e) {
            Storage.m6945c(this);
        } catch (Throwable e2) {
            Main.f1926a.m5682c(e2);
        }
    }

    public void onBackPressed() {
        m6712o();
        super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                m6712o();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onEventMainThread(VCardSuccessEvent vCardSuccessEvent) {
        if (SharedPreferencesUtil.m7060b(getString(2131296928), Boolean.valueOf(false))) {
            this.f4241r.setAlpha(1.0f);
        }
        this.f4245v = ContactDataSource.m4553a().m4581g();
    }

    private void m6708k() {
        this.f4241r.setOnClickListener(this);
        this.f4240q.setOnClickListener(this);
    }

    private void m6709l() {
        this.f4238o = (TextView) findViewById(2131755224);
        this.f4241r = (ImageView) findViewById(2131755116);
        this.f4239p = (EditText) findViewById(2131755222);
        this.f4240q = (ImageView) findViewById(2131755223);
    }

    private void m6710m() {
        if (getResources().getConfiguration().orientation == 1) {
            setContentView(2130903087);
        } else {
            setContentView(2130903088);
        }
        m6709l();
        m6708k();
    }

    private void m6711n() {
        this.f4239p.setFocusable(false);
        this.f4238o.setText("+" + this.f4245v.m4200g());
        if (this.f4245v.m4212o() != null) {
            if (SharedPreferencesUtil.m7060b(getString(2131296928), Boolean.valueOf(false))) {
                this.f4241r.setAlpha(1.0f);
            } else {
                this.f4241r.setAlpha(0.4f);
            }
            CustomImageLoader.m4009a().m4020a(this.f4241r, this.f4245v.m4212o(), null, 2130837596);
        }
        if (this.f4245v.m4209l() != null && this.f4245v.m4209l().length() > 0) {
            this.f4239p.setText(this.f4245v.m4209l());
        }
    }

    private void m6712o() {
        Intent intent = getIntent();
        intent.putExtra("profile_updated_photo_token", ContactDataSource.m4553a().m4581g().m4212o());
        setResult(-1, intent);
    }
}
