package app.messaging.group;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.GroupV2RolesPrivilege;
import app.common.entity.ConversationGroupEntity;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.GroupUsersDatasource;
import app.galley.SelectResourceFromSDCardActivity;
import app.notification.NotificationCenter;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.MediaPickerUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.xmpp.GroupManager;
import app.xmpp.packet.groupv2.GroupElement;
import app.xmpp.packet.groupv2.GroupElement.Type;
import app.xmpp.packet.groupv2.MembersXE.MemberState;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import butterknife.ButterKnife;
import java.io.File;
import java.io.IOException;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupSettingActivity extends BaseActivity implements OnClickListener {
    private GroupV2RolesPrivilege f3712A;
    private File f3713B;
    private File f3714C;
    private StringBuilder f3715D;
    private String f3716E;
    private boolean f3717F;
    private boolean f3718G;
    private boolean f3719H;
    private boolean f3720I;
    ImageView f3721o;
    EditText f3722p;
    EditText f3723q;
    TextView f3724r;
    TextView f3725s;
    TextView f3726t;
    TextView f3727u;
    TextView f3728v;
    Button f3729w;
    ImageView f3730x;
    private Dialog f3731y;
    private ConversationGroupEntity f3732z;

    /* renamed from: app.messaging.group.GroupSettingActivity.1 */
    class C03371 implements TextWatcher {
        final /* synthetic */ GroupSettingActivity f3702a;

        C03371(GroupSettingActivity groupSettingActivity) {
            this.f3702a = groupSettingActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f3702a.m6308b(this.f3702a.f3723q.getText().toString().length());
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity.2 */
    class C03382 implements OnTouchListener {
        final /* synthetic */ GroupSettingActivity f3703a;

        C03382(GroupSettingActivity groupSettingActivity) {
            this.f3703a = groupSettingActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return false;
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity.3 */
    class C03413 implements DialogInterface.OnClickListener {
        final /* synthetic */ GroupSettingActivity f3706a;

        /* renamed from: app.messaging.group.GroupSettingActivity.3.1 */
        class C03391 implements DialogInterface.OnClickListener {
            final /* synthetic */ C03413 f3704a;

            C03391(C03413 c03413) {
                this.f3704a = c03413;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.messaging.group.GroupSettingActivity.3.2 */
        class C03402 implements DialogInterface.OnClickListener {
            final /* synthetic */ C03413 f3705a;

            C03402(C03413 c03413) {
                this.f3705a = c03413;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                CustomImageLoader.m4009a().m4020a(this.f3705a.f3706a.f3721o, null, null, 2130837596);
                this.f3705a.f3706a.f3714C = null;
                this.f3705a.f3706a.f3717F = false;
                this.f3705a.f3706a.f3720I = true;
            }
        }

        C03413(GroupSettingActivity groupSettingActivity) {
            this.f3706a = groupSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    try {
                        this.f3706a.f3714C = FileUtil.m7016a("cropped_image", null, Storage.m6952g());
                        if (this.f3706a.f3714C == null || Uri.fromFile(this.f3706a.f3714C).toString() == null) {
                            Main.m3905a(this.f3706a.getString(2131296438));
                        } else if (PermissionUtil.m7044a(PermissionType.storage)) {
                            this.f3706a.m6295A();
                        } else {
                            PermissionUtil.m7042a(this.f3706a, PermissionType.storage, 2);
                        }
                    } catch (StorageException e) {
                        Storage.m6945c(this.f3706a);
                    } catch (Throwable e2) {
                        Main.f1926a.m5682c(e2);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f3706a.f3713B = MediaPickerUtil.m7032a(this.f3706a, 4601);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    Builder builder = new Builder(this.f3706a, 2131558538);
                    builder.m1974a(2131296331);
                    builder.m1989c(2130837731);
                    builder.m1986b(this.f3706a.getString(2131296742));
                    builder.m1985b(2131296784, new C03391(this));
                    builder.m1981a(this.f3706a.getString(2131296786), new C03402(this));
                    builder.m1992c();
                default:
            }
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity.4 */
    class C03424 implements DialogInterface.OnClickListener {
        final /* synthetic */ GroupSettingActivity f3707a;

        C03424(GroupSettingActivity groupSettingActivity) {
            this.f3707a = groupSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            GroupManager.m7323a().m7353a(this.f3707a.f3716E, true, null);
            this.f3707a.m6310b("LEFT_DELETE");
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity.5 */
    class C03435 implements DialogInterface.OnClickListener {
        final /* synthetic */ GroupSettingActivity f3708a;

        C03435(GroupSettingActivity groupSettingActivity) {
            this.f3708a = groupSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            GroupManager.m7323a().m7353a(this.f3708a.f3716E, false, null);
            this.f3708a.m6310b("LEFT_DELETE");
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity.6 */
    class C03446 implements DialogInterface.OnClickListener {
        final /* synthetic */ GroupSettingActivity f3709a;

        C03446(GroupSettingActivity groupSettingActivity) {
            this.f3709a = groupSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            GroupManager.m7323a().m7365c(this.f3709a.f3716E, null);
            this.f3709a.m6310b("DESTROYING");
        }
    }

    /* renamed from: app.messaging.group.GroupSettingActivity.7 */
    /* synthetic */ class C03457 {
        static final /* synthetic */ int[] f3710a;
        static final /* synthetic */ int[] f3711b;

        static {
            f3711b = new int[NotificationState.values().length];
            try {
                f3711b[NotificationState.sound.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3711b[NotificationState.soundPending.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3711b[NotificationState.vibrate.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3711b[NotificationState.vibratePending.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3711b[NotificationState.disable.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3711b[NotificationState.disablePending.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            f3710a = new int[Type.values().length];
            try {
                f3710a[Type.moderated.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3710a[Type.semimoderated.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3710a[Type.unmoderated.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public GroupSettingActivity() {
        this.f3713B = null;
        this.f3717F = false;
        this.f3718G = false;
        this.f3720I = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903079);
        ButterKnife.m7741a((Activity) this);
        this.f3715D = new StringBuilder("");
        this.f3723q.setFilters(new InputFilter[]{new LengthFilter(200)});
        m6319t();
        m6316q();
        m6317r();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        MenuItem item = menu.getItem(0);
        MenuItem item2 = menu.getItem(1);
        if (this.f3712A.m4046a()) {
            if (!this.f3718G) {
                z = true;
            }
            item.setVisible(z);
            item2.setVisible(this.f3718G);
        } else {
            item.setVisible(false);
            item2.setVisible(false);
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820578, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.f3718G) {
                    m6311b(false);
                    return false;
                } else if (this.f3719H) {
                    this.f3730x.setVisibility(8);
                    this.f3719H = false;
                    return false;
                }
                break;
            case 2131755653:
                this.f3732z = GroupManager.m7323a().m7358b(this.f3716E);
                if (!this.f3722p.getText().toString().trim().equalsIgnoreCase("")) {
                    if (!this.f3722p.getText().toString().equals(this.f3732z.m4286f())) {
                        m6321v();
                    }
                    if (!this.f3723q.getText().toString().equals(this.f3732z.m4301m())) {
                        m6322w();
                    }
                    if (this.f3720I && !m6320u().equals(this.f3732z.m4289g())) {
                        m6323x();
                    }
                    m6324y();
                    m6311b(false);
                    m6318s();
                    break;
                }
                this.f3722p.setError(getString(2131296487));
                return true;
            case 2131755659:
                this.f3732z = GroupManager.m7323a().m7358b(this.f3716E);
                this.f3723q.setText(this.f3732z.m4301m());
                this.f3722p.setText(this.f3732z.m4286f());
                m6314c(this.f3732z.m4289g());
                m6311b(true);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Throwable e;
        if (i2 == -1) {
            switch (i) {
                case 4600:
                case 4602:
                    if (this.f3714C == null || !this.f3714C.exists()) {
                        Main.m3905a(getString(2131296837));
                        return;
                    }
                    try {
                        File file = new File(Storage.m6941b() + File.separator + m6320u());
                        file.createNewFile();
                        FileUtil.m7022a(this.f3714C, file, true);
                        CustomImageLoader.m4009a().m4017a(this.f3721o, this.f3714C, null);
                        this.f3717F = true;
                        this.f3720I = true;
                        m6323x();
                    } catch (StorageException e2) {
                        e = e2;
                        Main.f1926a.m5682c(e);
                    } catch (IOException e3) {
                        e = e3;
                        Main.f1926a.m5682c(e);
                    }
                case 4601:
                    if (this.f3713B == null) {
                        Main.m3905a(getString(2131296837));
                    } else {
                        this.f3714C = MediaPickerUtil.m7033a(this, Uri.fromFile(this.f3713B), 4602);
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
                    m6295A();
                default:
            }
        }
    }

    public void onBackPressed() {
        if (this.f3718G) {
            m6311b(false);
        } else if (this.f3719H) {
            this.f3730x.setVisibility(8);
            this.f3719H = false;
        } else {
            super.onBackPressed();
        }
    }

    private void m6315p() {
        Intent intent = new Intent();
        intent.putExtra("extra_group_status", this.f3732z.m4283e());
        setResult(-1, intent);
    }

    private void m6310b(String str) {
        Intent intent = new Intent();
        intent.putExtra("extra_group_status", str);
        setResult(-1, intent);
        finish();
    }

    private void m6316q() {
        this.f3723q.addTextChangedListener(new C03371(this));
        this.f3723q.setOnTouchListener(new C03382(this));
    }

    private void m6317r() {
        int i;
        int i2 = 0;
        GroupManager.m7323a().m7350a(this.f3716E, MemberState.joined, null);
        GroupManager.m7323a().m7370e(this.f3716E, null);
        this.f3712A = GroupUsersDatasource.m4639a().m4642a(this.f3716E, this.f3732z.m4302n().ordinal());
        boolean z = (this.f3732z.m4289g() == null || this.f3732z.m4289g().isEmpty()) ? false : true;
        this.f3717F = z;
        m6314c(this.f3732z.m4289g());
        this.f3722p.setText(this.f3732z.m4286f());
        this.f3723q.setText(this.f3732z.m4301m());
        this.f3724r.setText(getString(2131296494) + ": " + m6301a(this.f3732z.m4302n()));
        this.f3725s.setText(m6307b(this.f3732z.m4302n()));
        this.f3727u.setText(m6302a(this.f3732z.m4295j()));
        m6308b(this.f3732z.m4301m() == null ? 0 : this.f3732z.m4301m().length());
        Button button = this.f3729w;
        if (this.f3712A.m4052d()) {
            i = 0;
        } else {
            i = 8;
        }
        button.setVisibility(i);
        TextView textView = this.f3726t;
        if (!this.f3712A.m4046a()) {
            i2 = 8;
        }
        textView.setVisibility(i2);
        this.f3715D = new StringBuilder("");
    }

    private void m6318s() {
        if (this.f3715D == null || this.f3715D.toString().isEmpty()) {
            this.f3715D = new StringBuilder("");
            return;
        }
        while (this.f3715D.length() > 0 && this.f3715D.charAt(this.f3715D.length() - 1) == ',') {
            this.f3715D.deleteCharAt(this.f3715D.length() - 1);
        }
        if (!this.f3715D.toString().isEmpty()) {
            this.f3732z.m4282d(this.f3722p.getText().toString().trim());
            this.f3732z.m4296j(this.f3723q.getText().toString().trim());
            this.f3732z.m4298k(this.f3715D.toString());
            this.f3732z.m4300l(this.f3715D.toString());
            this.f3732z.m4285e(m6320u());
            if (m6320u().isEmpty()) {
                GroupManager.m7323a().m7344a(this.f3732z, null);
            } else {
                GroupManager.m7323a().m7343a(this.f3732z);
            }
            GroupElement groupElement = new GroupElement(this.f3732z.m4274b());
            groupElement.m7608a(this.f3732z.m4286f());
            groupElement.m7614d(this.f3732z.m4289g());
            groupElement.m7607a(this.f3732z.m4302n());
            groupElement.m7610b(this.f3732z.m4301m());
            groupElement.m7616e(this.f3732z.m4305q());
            ConversationGroupDataSource.m4587a().m4603a(this.f3732z.m4274b(), groupElement, this.f3732z.m4303o());
            this.f3715D = new StringBuilder("");
            m6315p();
        }
    }

    private void m6311b(boolean z) {
        this.f3718G = z;
        this.f3722p.setFocusable(z);
        this.f3722p.setFocusableInTouchMode(z);
        this.f3722p.setClickable(z);
        this.f3722p.requestFocus();
        this.f3723q.setFocusable(z);
        this.f3723q.setFocusableInTouchMode(z);
        this.f3723q.setClickable(z);
        this.f3728v.setVisibility(z ? 0 : 8);
        invalidateOptionsMenu();
    }

    private void m6319t() {
        this.f3716E = getIntent().getStringExtra("extra_group_jid");
        if (this.f3716E == null) {
            finish();
            return;
        }
        this.f3732z = GroupManager.m7323a().m7358b(this.f3716E);
        if (this.f3732z == null) {
            finish();
        }
    }

    private void m6314c(String str) {
        CustomImageLoader.m4009a().m4020a(this.f3721o, str, null, 2130837595);
        CustomImageLoader.m4009a().m4020a(this.f3730x, str, null, 2130837595);
    }

    private String m6320u() {
        try {
            return FileUtil.m7024b(this.f3714C);
        } catch (IOException e) {
            return "";
        } catch (NullPointerException e2) {
            return "";
        }
    }

    private String m6301a(Type type) {
        switch (C03457.f3710a[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return getString(2131296495);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return getString(2131296497);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return getString(2131296499);
            default:
                return getString(2131296495);
        }
    }

    private String m6307b(Type type) {
        switch (C03457.f3710a[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return getString(2131296496);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return getString(2131296498);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return getString(2131296500);
            default:
                return getString(2131296496);
        }
    }

    private String m6302a(NotificationState notificationState) {
        String str = "";
        switch (C03457.f3711b[notificationState.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                str = getString(2131296618);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                str = getString(2131296619);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                str = getString(2131296617);
                break;
            default:
                str = getString(2131296618);
                break;
        }
        return getString(2131296490) + ": " + str;
    }

    private void m6321v() {
        if (this.f3715D.indexOf("ti") <= -1) {
            this.f3715D.append("ti,");
        }
    }

    private void m6322w() {
        if (this.f3715D.indexOf("de") <= -1) {
            this.f3715D.append("de,");
        }
    }

    private void m6323x() {
        if (this.f3715D.indexOf("av") <= -1) {
            this.f3715D.append("av,");
        }
    }

    private void m6308b(int i) {
        this.f3726t.setText("(" + i + "/" + 200 + ")");
    }

    private void m6324y() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void m6325z() {
        Builder builder = new Builder(this, 2131558535);
        CharSequence[] charSequenceArr = !this.f3717F ? new CharSequence[]{getString(2131296452), getString(2131296379)} : new CharSequence[]{getString(2131296452), getString(2131296379), getString(2131296667)};
        builder.m1980a(getString(2131296393));
        builder.m1983a(charSequenceArr, new C03413(this));
        builder.m1992c();
    }

    private void m6295A() {
        Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
        intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_SINGLE_PICK");
        intent.putExtra("output", Uri.fromFile(this.f3714C).toString());
        startActivityForResult(intent, 4600);
    }

    void m6326j() {
        if (this.f3712A.m4046a() && this.f3718G) {
            m6325z();
            return;
        }
        this.f3730x.setVisibility(0);
        this.f3719H = true;
    }

    void m6327k() {
        this.f3730x.setVisibility(8);
        this.f3719H = false;
    }

    void m6328l() {
        m6311b(false);
        Intent intent = new Intent(this, GroupMemberActivity.class);
        intent.putExtra("extra_group_jid", this.f3716E);
        intent.putExtra("user_can_kick", this.f3712A.m4050c());
        intent.putExtra("user_can_invite", this.f3712A.m4048b());
        startActivity(intent);
    }

    void m6329m() {
        m6296B();
    }

    void m6330n() {
        m6298D();
    }

    void m6331o() {
        m6297C();
    }

    private void m6296B() {
        this.f3731y = new Dialog(this);
        this.f3731y.requestWindowFeature(1);
        this.f3731y.setContentView(2130903121);
        this.f3731y.getWindow().setBackgroundDrawableResource(17170445);
        this.f3731y.findViewById(2131755316).setOnClickListener(this);
        this.f3731y.findViewById(2131755317).setOnClickListener(this);
        this.f3731y.findViewById(2131755318).setOnClickListener(this);
        this.f3731y.show();
    }

    private void m6297C() {
        new Builder(this, 2131558538).m1980a(getString(2131296474)).m1986b(String.format(getString(2131296423), new Object[]{this.f3732z.m4286f()})).m1981a(getString(2131296473), new C03435(this)).m1991c(getString(2131296474), new C03424(this)).m1989c(2130837731).m1992c();
    }

    private void m6298D() {
        Builder c = new Builder(this, 2131558538).m1980a(getString(2131296471)).m1986b(String.format(getString(2131296421), new Object[]{this.f3732z.m4286f()})).m1981a(getString(2131296786), new C03446(this)).m1989c(2130837731);
        c.m1985b(2131296784, null);
        c.m1992c();
    }

    public void onClick(View view) {
        NotificationState notificationState = NotificationState.sound;
        switch (view.getId()) {
            case 2131755316:
                notificationState = NotificationState.sound;
                break;
            case 2131755317:
                notificationState = NotificationState.vibrate;
                break;
            case 2131755318:
                notificationState = NotificationState.disable;
                break;
        }
        this.f3732z.m4272a(notificationState);
        this.f3727u.setText(m6302a(notificationState));
        NotificationCenter.m6606a().m6610a(this.f3732z.m4274b(), notificationState);
        ConversationGroupDataSource.m4587a().m4593a(Main.f1927b, notificationState, this.f3732z.m4274b());
        this.f3731y.dismiss();
    }
}
