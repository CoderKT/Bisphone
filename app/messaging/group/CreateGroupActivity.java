package app.messaging.group;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import app.Main;
import app.account.AccountManager;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.ConversationGroupEntity.GroupInfoState;
import app.common.entity.HistoryEntity;
import app.database.datasource.ConversationGroupDataSource;
import app.events.group.CreateGroupResponseUiEvent;
import app.galley.SelectResourceFromSDCardActivity;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.MediaPickerUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.StringUtil;
import app.util.Utils;
import app.xmpp.GroupManager;
import app.xmpp.JabberId;
import app.xmpp.packet.common.ErrorXE.ErrorType;
import app.xmpp.packet.groupv2.GroupElement;
import app.xmpp.packet.groupv2.GroupElement.Type;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import butterknife.ButterKnife;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class CreateGroupActivity extends BaseActivity {
    private String f3616A;
    private File f3617B;
    private File f3618C;
    private boolean f3619D;
    private Type f3620E;
    ViewFlipper f3621o;
    RoundedImageView f3622p;
    EditText f3623q;
    EditText f3624r;
    TextView f3625s;
    ImageView f3626t;
    ImageView f3627u;
    ImageView f3628v;
    TextView f3629w;
    private ConversationGroupEntity f3630x;
    private ProgressDialog f3631y;
    private String f3632z;

    /* renamed from: app.messaging.group.CreateGroupActivity.10 */
    /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] f3602a;
        static final /* synthetic */ int[] f3603b;

        static {
            f3603b = new int[Type.values().length];
            try {
                f3603b[Type.moderated.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3603b[Type.semimoderated.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3603b[Type.unmoderated.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f3602a = new int[ErrorType.values().length];
            try {
                f3602a[ErrorType.Reach_Max_Group.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3602a[ErrorType.Group_Already_Exist.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3602a[ErrorType.Time_out.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.1 */
    class C03181 implements GroupListener {
        final /* synthetic */ CreateGroupActivity f3604a;

        C03181(CreateGroupActivity createGroupActivity) {
            this.f3604a = createGroupActivity;
        }

        public void m6211a() {
            this.f3604a.m6240x();
        }

        public void m6212a(String str) {
            this.f3604a.onEventMainThread(new CreateGroupResponseUiEvent(ErrorType.Time_out));
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.2 */
    class C03192 implements ImageLoadingListener {
        final /* synthetic */ CreateGroupActivity f3605a;

        C03192(CreateGroupActivity createGroupActivity) {
            this.f3605a = createGroupActivity;
        }

        public void m6213a(String str, View view) {
        }

        public void m6215a(String str, View view, FailReason failReason) {
        }

        public void m6214a(String str, View view, Bitmap bitmap) {
            this.f3605a.f3619D = true;
        }

        public void m6216b(String str, View view) {
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.3 */
    class C03203 implements TextWatcher {
        final /* synthetic */ CreateGroupActivity f3606a;

        C03203(CreateGroupActivity createGroupActivity) {
            this.f3606a = createGroupActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f3606a.m6225b(this.f3606a.f3624r.getText().toString().length());
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.4 */
    class C03214 implements OnTouchListener {
        final /* synthetic */ CreateGroupActivity f3607a;

        C03214(CreateGroupActivity createGroupActivity) {
            this.f3607a = createGroupActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction() & 255) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return false;
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.5 */
    class C03245 implements OnClickListener {
        final /* synthetic */ CreateGroupActivity f3610a;

        /* renamed from: app.messaging.group.CreateGroupActivity.5.1 */
        class C03221 implements OnClickListener {
            final /* synthetic */ C03245 f3608a;

            C03221(C03245 c03245) {
                this.f3608a = c03245;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        /* renamed from: app.messaging.group.CreateGroupActivity.5.2 */
        class C03232 implements OnClickListener {
            final /* synthetic */ C03245 f3609a;

            C03232(C03245 c03245) {
                this.f3609a = c03245;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                CustomImageLoader.m4009a().m4020a(this.f3609a.f3610a.f3622p, null, null, 2130837596);
                this.f3609a.f3610a.f3618C = null;
                this.f3609a.f3610a.f3616A = "";
                this.f3609a.f3610a.f3619D = false;
            }
        }

        C03245(CreateGroupActivity createGroupActivity) {
            this.f3610a = createGroupActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    if (PermissionUtil.m7044a(PermissionType.storage)) {
                        this.f3610a.m6239w();
                    } else {
                        PermissionUtil.m7042a(this.f3610a, PermissionType.storage, 2);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f3610a.f3617B = MediaPickerUtil.m7032a(this.f3610a, 4601);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    Builder builder = new Builder(this.f3610a, 2131558538);
                    builder.m1974a(2131296331);
                    builder.m1986b(this.f3610a.getString(2131296742));
                    builder.m1985b(2131296784, new C03221(this));
                    builder.m1981a(this.f3610a.getString(2131296786), new C03232(this));
                    builder.m1989c(2130837731);
                    builder.m1992c();
                default:
            }
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.6 */
    class C03256 implements Runnable {
        final /* synthetic */ CreateGroupActivity f3611a;

        C03256(CreateGroupActivity createGroupActivity) {
            this.f3611a = createGroupActivity;
        }

        public void run() {
            this.f3611a.f3631y.show();
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.7 */
    class C03267 implements Runnable {
        final /* synthetic */ CreateGroupActivity f3612a;

        C03267(CreateGroupActivity createGroupActivity) {
            this.f3612a = createGroupActivity;
        }

        public void run() {
            this.f3612a.f3631y.dismiss();
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.8 */
    class C03288 implements Runnable {
        final /* synthetic */ CreateGroupActivity f3614a;

        /* renamed from: app.messaging.group.CreateGroupActivity.8.1 */
        class C03271 implements OnClickListener {
            final /* synthetic */ C03288 f3613a;

            C03271(C03288 c03288) {
                this.f3613a = c03288;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }

        C03288(CreateGroupActivity createGroupActivity) {
            this.f3614a = createGroupActivity;
        }

        public void run() {
            Builder c = new Builder(this.f3614a, 2131558536).m1980a(this.f3614a.getString(2131296426)).m1986b(this.f3614a.getString(2131296415)).m1975a(17039370, new C03271(this)).m1989c(2130837729);
            c.m1988b();
            c.m1992c();
        }
    }

    /* renamed from: app.messaging.group.CreateGroupActivity.9 */
    class C03299 implements OnClickListener {
        final /* synthetic */ CreateGroupActivity f3615a;

        C03299(CreateGroupActivity createGroupActivity) {
            this.f3615a = createGroupActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    public CreateGroupActivity() {
        this.f3616A = "";
        this.f3617B = null;
        this.f3619D = false;
        this.f3620E = Type.moderated;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903075);
        ButterKnife.m7741a((Activity) this);
        setTitle(2131296316);
        this.f3624r.setFilters(new InputFilter[]{new LengthFilter(200)});
        m6218B();
        m6236t();
        m6233q();
    }

    protected void onResume() {
        super.onResume();
        EventBus.m12779a().m12791a((Object) this);
    }

    protected void onPause() {
        super.onPause();
        EventBus.m12779a().m12794c(this);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z;
        boolean z2 = false;
        MenuItem item = menu.getItem(0);
        MenuItem item2 = menu.getItem(1);
        if (this.f3621o.getDisplayedChild() == 0) {
            z = true;
        } else {
            z = false;
        }
        item.setVisible(z);
        if (this.f3621o.getDisplayedChild() == 1) {
            z2 = true;
        }
        item2.setVisible(z2);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820575, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 2131755651) {
            if (this.f3623q.getText().toString().trim().equalsIgnoreCase("")) {
                this.f3623q.setError(getString(2131296487));
                return true;
            }
            m6237u();
            this.f3621o.setDisplayedChild(1);
            setTitle(2131296494);
            invalidateOptionsMenu();
        } else if (itemId == 2131755653) {
            m6231o();
            m6230n();
        } else if (itemId == 16908332 && this.f3621o.getDisplayedChild() > 0) {
            this.f3621o.setDisplayedChild(0);
            setTitle(2131296316);
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Throwable e;
        if (i2 == -1) {
            switch (i) {
                case 4600:
                case 4602:
                    if (this.f3618C == null || !this.f3618C.exists()) {
                        Main.m3905a(getString(2131296837));
                        return;
                    }
                    try {
                        this.f3616A = m6234r();
                        File file = new File(Storage.m6941b() + File.separator + this.f3616A);
                        file.createNewFile();
                        FileUtil.m7022a(this.f3618C, file, true);
                        CustomImageLoader.m4009a().m4017a(this.f3622p, this.f3618C, null);
                        this.f3619D = true;
                    } catch (StorageException e2) {
                        e = e2;
                        Main.f1926a.m5682c(e);
                    } catch (IOException e3) {
                        e = e3;
                        Main.f1926a.m5682c(e);
                    }
                case 4601:
                    if (this.f3617B == null) {
                        Main.m3905a(getString(2131296837));
                    } else {
                        this.f3618C = MediaPickerUtil.m7033a(this, Uri.fromFile(this.f3617B), 4602);
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
                    m6239w();
                default:
            }
        }
    }

    public void onBackPressed() {
        if (this.f3621o.getDisplayedChild() > 0) {
            this.f3621o.setDisplayedChild(0);
            setTitle(2131296316);
            invalidateOptionsMenu();
            return;
        }
        super.onBackPressed();
    }

    public void onEventMainThread(CreateGroupResponseUiEvent createGroupResponseUiEvent) {
        m6241y();
        if (createGroupResponseUiEvent.m4868c()) {
            m6227b(createGroupResponseUiEvent.m4866a());
            m6229c(createGroupResponseUiEvent.m4866a());
            return;
        }
        switch (AnonymousClass10.f3602a[createGroupResponseUiEvent.m4867b().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m6242z();
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                String uuid = UUID.randomUUID().toString();
                ConversationGroupEntity p = m6232p();
                p.m4273a(uuid);
                ConversationGroupDataSource.m4587a().m4618e(uuid);
                GroupManager.m7323a().m7360b(p);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                GroupManager.m7323a().m7360b(m6232p());
                m6217A();
            default:
        }
    }

    private void m6227b(String str) {
        Intent intent = new Intent(this, NewGroupMembersActivity.class);
        intent.putExtra("extra_group_jid", str);
        startActivity(intent);
        finish();
    }

    private void m6230n() {
        GroupElement groupElement = new GroupElement(this.f3632z);
        groupElement.m7608a(this.f3623q.getText().toString().trim());
        groupElement.m7610b(this.f3624r.getText().toString().trim());
        groupElement.m7607a(this.f3620E);
        groupElement.m7612c(new JabberId(AccountManager.m3934a().m3937c(), "bisphone.com", null).m7391e());
        if (!this.f3619D || this.f3616A.isEmpty()) {
            groupElement.m7614d(null);
        } else {
            groupElement.m7614d(null);
        }
        GroupManager.m7323a().m7346a(groupElement, new C03181(this));
    }

    private void m6231o() {
        if (ConversationGroupDataSource.m4587a().m4608b() == null) {
            ConversationGroupDataSource.m4587a().m4589a(Main.f1927b, m6232p());
        } else {
            ConversationGroupDataSource.m4587a().m4610b(Main.f1927b, m6232p());
        }
        GroupManager.m7323a().m7367d(m6232p());
    }

    private ConversationGroupEntity m6232p() {
        ConversationGroupEntity.Builder builder = new ConversationGroupEntity.Builder();
        builder.m4262j(this.f3623q.getText().toString().trim()).m4261i(this.f3624r.getText().toString().trim()).m4259g(this.f3632z).m4263k(this.f3616A).m4246a(NotificationState.sound).m4247a((System.currentTimeMillis() * 1000) + "").m4242a(0).m4256e("").m4250b((System.currentTimeMillis() * 1000) + "").m4258f("").m4254d("-1").m4245a(this.f3620E).m4244a(GroupInfoState.NONE).m4260h("CREATE_DRAFT").m4264l(AccountManager.m3934a().m3937c()).m4265m("").m4253d(0).m4255e(0).m4257f(150).m4251c(1);
        return builder.m4248a();
    }

    private void m6233q() {
        this.f3630x = ConversationGroupDataSource.m4587a().m4608b();
        if (this.f3630x == null) {
            this.f3632z = StringUtil.m7065b();
            return;
        }
        this.f3632z = this.f3630x.m4274b();
        this.f3630x = GroupManager.m7323a().m7363c();
        if (this.f3630x != null) {
            this.f3616A = this.f3630x.m4289g();
            this.f3620E = this.f3630x.m4302n();
            this.f3623q.setText(this.f3630x.m4286f());
            this.f3624r.setText(this.f3630x.m4301m());
            m6235s();
            if (!this.f3616A.isEmpty()) {
                try {
                    this.f3618C = new File(Utils.m7079a(this.f3616A, HistoryEntity.Type.PHOTO));
                    CustomImageLoader.m4009a().m4017a(this.f3622p, this.f3618C, new C03192(this));
                } catch (StorageException e) {
                }
            }
        }
    }

    private void m6229c(String str) {
        ConversationGroupEntity p = m6232p();
        p.m4298k("av");
        p.m4273a(str);
        p.m4270a(GroupInfoState.AVATAR);
        GroupManager.m7323a().m7343a(p);
    }

    private String m6234r() {
        try {
            return FileUtil.m7024b(this.f3618C);
        } catch (IOException e) {
            return "";
        } catch (NullPointerException e2) {
            return "";
        }
    }

    private void m6235s() {
        switch (AnonymousClass10.f3603b[this.f3620E.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f3626t.setImageResource(2130837676);
                this.f3627u.setImageResource(2130837677);
                this.f3628v.setImageResource(2130837677);
                this.f3629w.setText(getString(2131296496));
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f3626t.setImageResource(2130837677);
                this.f3627u.setImageResource(2130837676);
                this.f3628v.setImageResource(2130837677);
                this.f3629w.setText(getString(2131296498));
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f3626t.setImageResource(2130837677);
                this.f3627u.setImageResource(2130837677);
                this.f3628v.setImageResource(2130837676);
                this.f3629w.setText(getString(2131296500));
            default:
        }
    }

    private void m6236t() {
        this.f3624r.addTextChangedListener(new C03203(this));
        this.f3624r.setOnTouchListener(new C03214(this));
    }

    private void m6225b(int i) {
        this.f3625s.setText("(" + i + "/" + 200 + ")");
    }

    private void m6237u() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void m6238v() {
        Builder builder = new Builder(this, 2131558535);
        CharSequence[] charSequenceArr = !this.f3619D ? new CharSequence[]{getString(2131296452), getString(2131296379)} : new CharSequence[]{getString(2131296452), getString(2131296379), getString(2131296667)};
        builder.m1980a(getString(2131296393));
        builder.m1983a(charSequenceArr, new C03245(this));
        builder.m1992c();
    }

    private void m6239w() {
        try {
            this.f3618C = FileUtil.m7016a("cropped_image", null, Storage.m6952g());
            if (this.f3618C == null || Uri.fromFile(this.f3618C).toString() == null) {
                Main.m3905a(getString(2131296438));
                return;
            }
            Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
            intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_SINGLE_PICK");
            intent.putExtra("output", Uri.fromFile(this.f3618C).toString());
            startActivityForResult(intent, 4600);
        } catch (StorageException e) {
            Storage.m6945c(this);
        } catch (Throwable e2) {
            Main.f1926a.m5682c(e2);
        }
    }

    private void m6240x() {
        runOnUiThread(new C03256(this));
    }

    private void m6241y() {
        runOnUiThread(new C03267(this));
    }

    private void m6242z() {
        runOnUiThread(new C03288(this));
    }

    private void m6217A() {
        Builder c = new Builder(this, 2131558536).m1980a(getString(2131296426)).m1986b(String.format(getString(2131296416), new Object[]{this.f3623q.getText().toString()})).m1975a(17039370, new C03299(this)).m1989c(2130837729);
        c.m1988b();
        c.m1992c();
    }

    private void m6218B() {
        this.f3631y = new ProgressDialog(this);
        this.f3631y.setTitle(getString(2131296644));
        this.f3631y.setMessage(String.format(getString(2131296417), new Object[]{this.f3623q.getText().toString()}));
        this.f3631y.setCancelable(false);
    }

    void m6243j() {
        m6238v();
    }

    void m6244k() {
        this.f3620E = Type.moderated;
        m6235s();
    }

    void m6245l() {
        this.f3620E = Type.semimoderated;
        m6235s();
    }

    void m6246m() {
        this.f3620E = Type.unmoderated;
        m6235s();
    }
}
