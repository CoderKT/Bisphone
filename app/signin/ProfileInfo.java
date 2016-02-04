package app.signin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import app.Main;
import app.SplashActivity.EntryPoint;
import app.common.CustomImageLoader;
import app.common.LocalizeActivity;
import app.common.entity.ContactEntity;
import app.database.datasource.ContactDataSource;
import app.events.vcard.VCardSuccessEvent;
import app.galley.SelectResourceFromSDCardActivity;
import app.home.HomeActivity;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.MediaPickerUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.PixelConverter;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.io.File;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class ProfileInfo extends LocalizeActivity {
    Button f4466k;
    EditText f4467o;
    ImageView f4468p;
    String f4469q;
    private File f4470r;
    private File f4471s;
    private InputFilter f4472t;

    /* renamed from: app.signin.ProfileInfo.1 */
    class C04701 implements InputFilter {
        final /* synthetic */ ProfileInfo f4462a;

        C04701(ProfileInfo profileInfo) {
            this.f4462a = profileInfo;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (charSequence == null || !"()".contains("" + charSequence)) {
                return null;
            }
            return "";
        }
    }

    /* renamed from: app.signin.ProfileInfo.2 */
    class C04732 implements OnMenuItemClickListener {
        final /* synthetic */ ProfileInfo f4465a;

        /* renamed from: app.signin.ProfileInfo.2.1 */
        class C04711 implements OnClickListener {
            final /* synthetic */ C04732 f4463a;

            C04711(C04732 c04732) {
                this.f4463a = c04732;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.signin.ProfileInfo.2.2 */
        class C04722 implements OnClickListener {
            final /* synthetic */ C04732 f4464a;

            C04722(C04732 c04732) {
                this.f4464a = c04732;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (ContactDataSource.m4553a().m4580f()) {
                    ContactEntity g = ContactDataSource.m4553a().m4581g();
                    g.m4205j(null);
                    ContactDataSource.m4553a().m4566a(g);
                } else {
                    SharedPreferencesUtil.m7055a(this.f4464a.f4465a.getString(2131296919), null);
                }
                CustomImageLoader.m4009a().m4020a(this.f4464a.f4465a.f4468p, null, null, 2130837596);
            }
        }

        C04732(ProfileInfo profileInfo) {
            this.f4465a = profileInfo;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getTitle() == this.f4465a.getResources().getString(2131296452)) {
                if (PermissionUtil.m7044a(PermissionType.storage)) {
                    this.f4465a.m6852l();
                } else {
                    PermissionUtil.m7042a(this.f4465a, PermissionType.storage, 2);
                }
            } else if (menuItem.getTitle() == this.f4465a.getResources().getString(2131296379)) {
                this.f4465a.f4470r = MediaPickerUtil.m7032a(this.f4465a, 3701);
            } else if (menuItem.getTitle() == this.f4465a.getResources().getString(2131296668)) {
                Builder builder = new Builder(this.f4465a, 2131558537);
                builder.m1989c(2130837730);
                builder.m1974a(2131296331);
                builder.m1986b(this.f4465a.getString(2131296742));
                builder.m1987b(this.f4465a.getString(2131296784), new C04711(this));
                builder.m1981a(this.f4465a.getString(2131296786), new C04722(this));
                builder.m1992c();
            }
            return true;
        }
    }

    public ProfileInfo() {
        this.f4472t = new C04701(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903146);
        ButterKnife.m7741a((Activity) this);
        setTitle(getString(2131296806));
        m6854n();
        this.f4467o.setFilters(new InputFilter[]{this.f4472t});
        String a = SharedPreferencesUtil.m7052a(getString(2131296919));
        if (a != null) {
            CustomImageLoader.m4009a().m4020a(this.f4468p, a, null, 2130837596);
        }
    }

    void m6855j() {
        PopupMenu popupMenu = new PopupMenu(this, this.f4468p);
        Menu menu = popupMenu.getMenu();
        menu.add(getResources().getString(2131296452));
        menu.add(getResources().getString(2131296379));
        if (this.f4469q != null) {
            menu.add(getResources().getString(2131296668));
        }
        popupMenu.getMenuInflater().inflate(2131820582, menu);
        popupMenu.setOnMenuItemClickListener(new C04732(this));
        popupMenu.show();
    }

    private void m6852l() {
        try {
            this.f4471s = FileUtil.m7016a("crop_image", null, Storage.m6952g());
            if (this.f4471s == null || Uri.fromFile(this.f4471s).toString() == null) {
                Main.m3905a(getString(2131296438));
                return;
            }
            Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
            intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_SINGLE_PICK");
            intent.putExtra("output", Uri.fromFile(this.f4471s).toString());
            startActivityForResult(intent, 3700);
        } catch (StorageException e) {
            Storage.m6945c(this);
        } catch (Throwable e2) {
            Main.f1926a.m5682c(e2);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0 && iArr[0] == 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6852l();
                default:
            }
        }
    }

    protected void onResume() {
        super.onResume();
        EventBus.m12779a().m12791a((Object) this);
    }

    protected void onPause() {
        super.onPause();
        EventBus.m12779a().m12794c(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6854n();
    }

    public void onEventMainThread(VCardSuccessEvent vCardSuccessEvent) {
        if (SharedPreferencesUtil.m7060b(getString(2131296928), Boolean.valueOf(false))) {
            this.f4468p.setAlpha(1.0f);
        }
    }

    void m6856k() {
        this.f4466k.setEnabled(false);
        if (m6853m()) {
            ContactDataSource.m4553a().m4578e();
            SharedPreferencesUtil.m7055a(getString(2131296916), EntryPoint.profile.toString());
            SharedPreferencesUtil.m7055a(getResources().getString(2131296933), this.f4467o.getText().toString().trim());
            SharedPreferencesUtil.m7054a(getString(2131296929), Boolean.valueOf(false));
            ContactDataSource.m4553a().m4573c(this.f4467o.getText().toString().trim());
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(335577088);
            intent.putExtra("FirstRun", true);
            startActivity(intent);
            finish();
            return;
        }
        this.f4466k.setEnabled(true);
        Main.m3905a(getResources().getString(2131296591));
    }

    private boolean m6853m() {
        String replace = this.f4467o.getText().toString().replace('\u200c', ' ').replace('\u200d', ' ').replace('\u200c', ' ').replace('\u200d', ' ');
        while (replace.contains("  ")) {
            replace = replace.replace("  ", " ");
        }
        return replace.trim().length() > 2;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 3700:
                case 3702:
                    if (this.f4471s == null || !this.f4471s.exists()) {
                        Main.m3905a(getString(2131296837));
                        return;
                    }
                    MediaPickerUtil.m7034a(this.f4471s.getPath(), this.f4468p);
                    SharedPreferencesUtil.m7054a(getString(2131296928), Boolean.valueOf(false));
                case 3701:
                    if (this.f4470r != null) {
                        this.f4471s = MediaPickerUtil.m7033a(this, Uri.fromFile(this.f4470r), 3702);
                    }
                default:
            }
        }
    }

    private void m6854n() {
        LayoutParams layoutParams = this.f4467o.getLayoutParams();
        layoutParams.width = getResources().getConfiguration().orientation == 2 ? (int) PixelConverter.m7048a(220.0f, this) : -1;
        this.f4467o.setLayoutParams(layoutParams);
    }
}
