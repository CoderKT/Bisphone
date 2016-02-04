package app.messaging.group;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog.Builder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import app.Main;
import app.account.AccountManager;
import app.common.BaseActivity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ConversationGroupEntity;
import app.database.datasource.ContactDataSource;
import app.database.datasource.GroupUsersDatasource;
import app.events.group.GetGroupMemberChanged;
import app.events.group.InvitationResponseUiEvent;
import app.events.group.KickResponseUiEvent;
import app.events.group.LeaveMemberEvent;
import app.events.vcard.VCardInfoLoadEvent;
import app.messaging.selector.SelectRecipientActivity;
import app.profile.ProfileModel;
import app.profile.ProfileViewer;
import app.xmpp.GroupManager;
import app.xmpp.JabberId;
import app.xmpp.VCardHandler;
import app.xmpp.VCardInsertUpdateEntity;
import app.xmpp.packet.common.ErrorXE.ErrorType;
import app.xmpp.packet.groupv2.GroupElement.Type;
import app.xmpp.packet.groupv2.MembersXE.MemberState;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupMemberActivity extends BaseActivity {
    private Set<String> f3650A;
    private HashMap<String, VCardInsertUpdateEntity> f3651B;
    private ContactEntity f3652C;
    private String f3653D;
    private boolean f3654E;
    private boolean f3655F;
    private boolean f3656G;
    private boolean f3657H;
    ExpandableListView f3658o;
    Button f3659p;
    Handler f3660q;
    private GroupMemberExpandableAdapter f3661r;
    private ConversationGroupEntity f3662s;
    private HashMap<String, ContactEntity> f3663t;
    private ArrayList<ArrayList<GroupSettingModel>> f3664u;
    private ArrayList<GroupSettingModel> f3665v;
    private ArrayList<GroupSettingModel> f3666w;
    private ArrayList<GroupSettingModel> f3667x;
    private Set<String> f3668y;
    private Set<String> f3669z;

    /* renamed from: app.messaging.group.GroupMemberActivity.1 */
    class C03301 implements OnGroupClickListener {
        final /* synthetic */ GroupMemberActivity f3638a;

        C03301(GroupMemberActivity groupMemberActivity) {
            this.f3638a = groupMemberActivity;
        }

        public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
            return true;
        }
    }

    /* renamed from: app.messaging.group.GroupMemberActivity.2 */
    class C03312 implements Runnable {
        final /* synthetic */ GroupMemberActivity f3639a;

        C03312(GroupMemberActivity groupMemberActivity) {
            this.f3639a = groupMemberActivity;
        }

        public void run() {
            this.f3639a.m6270n();
        }
    }

    /* renamed from: app.messaging.group.GroupMemberActivity.3 */
    class C03323 implements OnClickListener {
        final /* synthetic */ int f3640a;
        final /* synthetic */ int f3641b;
        final /* synthetic */ GroupMemberActivity f3642c;

        C03323(GroupMemberActivity groupMemberActivity, int i, int i2) {
            this.f3642c = groupMemberActivity;
            this.f3640a = i;
            this.f3641b = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            ((GroupSettingModel) ((ArrayList) this.f3642c.f3664u.get(this.f3640a)).get(this.f3641b)).m6334a(true);
            GroupManager.m7323a().m7351a(this.f3642c.f3653D, ((GroupSettingModel) ((ArrayList) this.f3642c.f3664u.get(this.f3640a)).get(this.f3641b)).m6335b(), null);
            this.f3642c.m6270n();
        }
    }

    /* renamed from: app.messaging.group.GroupMemberActivity.4 */
    class C03344 implements OnClickListener {
        final /* synthetic */ int f3644a;
        final /* synthetic */ GroupMemberActivity f3645b;

        /* renamed from: app.messaging.group.GroupMemberActivity.4.1 */
        class C03331 extends ArrayList<String> {
            final /* synthetic */ C03344 f3643a;

            C03331(C03344 c03344) {
                this.f3643a = c03344;
                add(((GroupSettingModel) ((ArrayList) this.f3643a.f3645b.f3661r.m6288e().get(this.f3643a.f3645b.f3661r.m6286c())).get(this.f3643a.f3644a)).m6335b());
            }
        }

        C03344(GroupMemberActivity groupMemberActivity, int i) {
            this.f3645b = groupMemberActivity;
            this.f3644a = i;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.f3645b.f3661r.m6286c() >= 0 && this.f3645b.f3661r.m6286c() < this.f3645b.f3664u.size() && this.f3644a < ((ArrayList) this.f3645b.f3661r.m6288e().get(this.f3645b.f3661r.m6286c())).size()) {
                GroupManager.m7323a().m7362b(this.f3645b.f3653D, new C03331(this), null);
            }
        }
    }

    /* renamed from: app.messaging.group.GroupMemberActivity.5 */
    class C03355 implements Runnable {
        final /* synthetic */ GetGroupMemberChanged f3646a;
        final /* synthetic */ GroupMemberActivity f3647b;

        C03355(GroupMemberActivity groupMemberActivity, GetGroupMemberChanged getGroupMemberChanged) {
            this.f3647b = groupMemberActivity;
            this.f3646a = getGroupMemberChanged;
        }

        public void run() {
            Iterator it;
            String str;
            switch (C03366.f3649b[this.f3646a.m4885b().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    it = this.f3646a.m4884a().iterator();
                    while (it.hasNext()) {
                        str = (String) it.next();
                        if (str != null && this.f3647b.f3650A.add(str)) {
                            this.f3647b.f3667x.add(this.f3647b.m6256b(str));
                        }
                    }
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    it = this.f3646a.m4884a().iterator();
                    while (it.hasNext()) {
                        str = (String) it.next();
                        if (str != null) {
                            if (this.f3647b.f3669z.add(str)) {
                                this.f3647b.f3666w.add(this.f3647b.m6256b(str));
                            }
                            this.f3647b.m6262d(str);
                        }
                    }
                    break;
            }
            this.f3647b.m6270n();
        }
    }

    /* renamed from: app.messaging.group.GroupMemberActivity.6 */
    /* synthetic */ class C03366 {
        static final /* synthetic */ int[] f3648a;
        static final /* synthetic */ int[] f3649b;

        static {
            f3649b = new int[MemberState.values().length];
            try {
                f3649b[MemberState.invited.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3649b[MemberState.joined.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            f3648a = new int[ErrorType.values().length];
            try {
                f3648a[ErrorType.User_Not_Exist.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3648a[ErrorType.User_Not_Allowed.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3648a[ErrorType.Group_Not_Exist.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3648a[ErrorType.User_Not_Join.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3648a[ErrorType.Time_out.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3648a[ErrorType.Reach_Max_Member.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public GroupMemberActivity() {
        this.f3654E = true;
        this.f3657H = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903078);
        ButterKnife.m7741a((Activity) this);
        EventBus.m12779a().m12791a((Object) this);
        this.f3660q = new Handler();
        this.f3664u = new ArrayList();
        this.f3665v = new ArrayList();
        this.f3666w = new ArrayList();
        this.f3667x = new ArrayList();
        this.f3668y = new HashSet();
        this.f3669z = new HashSet();
        this.f3650A = new HashSet();
        this.f3651B = new HashMap();
        m6268l();
        if (this.f3662s == null) {
            finish();
            return;
        }
        m6269m();
        m6272p();
        m6267k();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.m12779a().m12794c(this);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z;
        boolean z2 = false;
        MenuItem item = menu.getItem(0);
        MenuItem item2 = menu.getItem(1);
        if (this.f3661r.m6284a() || !this.f3656G || this.f3661r.m6287d() <= 0) {
            z = false;
        } else {
            z = true;
        }
        item.setVisible(z);
        if (this.f3661r.m6284a() && !this.f3666w.isEmpty()) {
            z2 = true;
        }
        item2.setVisible(z2);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820576, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.f3661r.m6284a()) {
                    this.f3661r.m6283a(false);
                    m6270n();
                    invalidateOptionsMenu();
                    return false;
                }
                break;
            case 2131755661:
                this.f3661r.m6283a(true);
                m6270n();
                break;
            case 2131755662:
                if (this.f3661r.m6284a()) {
                    this.f3661r.m6283a(false);
                    m6270n();
                    break;
                }
                break;
        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(menuItem);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 190:
                    int v;
                    List<String> stringArrayListExtra = intent.getStringArrayListExtra("recipients_list");
                    this.f3663t.putAll(ContactDataSource.m4554a(ContactDataSource.m4553a().m4574d((List) stringArrayListExtra)));
                    for (String str : stringArrayListExtra) {
                        if (str != null && this.f3650A.add(str)) {
                            this.f3667x.add(m6256b(str));
                        }
                    }
                    this.f3657H = true;
                    this.f3662s.m4284e(this.f3662s.m4311w() + stringArrayListExtra.size());
                    ConversationGroupEntity conversationGroupEntity = this.f3662s;
                    if (this.f3662s.m4310v() + stringArrayListExtra.size() <= this.f3662s.m4312x()) {
                        v = this.f3662s.m4310v() + stringArrayListExtra.size();
                    } else {
                        v = this.f3662s.m4312x();
                    }
                    conversationGroupEntity.m4281d(v);
                    m6270n();
                    GroupManager.m7323a().m7362b(this.f3653D, stringArrayListExtra, null);
                default:
            }
        }
    }

    public void onBackPressed() {
        if (this.f3661r.m6284a()) {
            this.f3661r.m6283a(false);
            m6270n();
            invalidateOptionsMenu();
            return;
        }
        super.onBackPressed();
    }

    private void m6267k() {
        this.f3658o.setOnGroupClickListener(new C03301(this));
    }

    private void m6268l() {
        boolean z = false;
        this.f3653D = getIntent().getStringExtra("extra_group_jid");
        if (this.f3653D == null) {
            finish();
            return;
        }
        this.f3662s = GroupManager.m7323a().m7358b(this.f3653D);
        this.f3655F = getIntent().getBooleanExtra("user_can_invite", false);
        this.f3656G = getIntent().getBooleanExtra("user_can_kick", false);
        this.f3659p.setVisibility(this.f3655F ? 0 : 8);
        if (this.f3662s.m4311w() > 0) {
            z = true;
        }
        this.f3657H = z;
    }

    private void m6269m() {
        this.f3664u.add(this.f3665v);
        this.f3664u.add(this.f3666w);
        this.f3664u.add(this.f3667x);
        this.f3661r = new GroupMemberExpandableAdapter(this, this.f3664u, this.f3662s.m4312x());
        m6271o();
        this.f3658o.setAdapter(this.f3661r);
        this.f3658o.expandGroup(0);
        this.f3658o.expandGroup(1);
        this.f3658o.expandGroup(2);
    }

    private void m6270n() {
        invalidateOptionsMenu();
        m6271o();
        this.f3661r.notifyDataSetChanged();
    }

    private void m6271o() {
        int v = this.f3662s.m4310v() - this.f3662s.m4311w();
        GroupMemberExpandableAdapter groupMemberExpandableAdapter = this.f3661r;
        if (v < 0) {
            v = 0;
        }
        groupMemberExpandableAdapter.m6280a(v);
        this.f3661r.m6282a(this.f3664u);
    }

    private void m6272p() {
        this.f3665v.clear();
        this.f3666w.clear();
        this.f3667x.clear();
        this.f3668y.clear();
        this.f3669z.clear();
        this.f3650A.clear();
        this.f3652C = ContactDataSource.m4553a().m4581g();
        Cursor a = GroupUsersDatasource.m4639a().m4641a(this.f3653D);
        if (a == null) {
            finish();
            return;
        }
        this.f3663t = ContactDataSource.m4554a(a);
        if (a.moveToFirst()) {
            do {
                JabberId a2;
                String string = a.getString(a.getColumnIndex("contact_username"));
                if (string.contains("@")) {
                    a2 = JabberId.m7386a(string);
                    string = a2.m7387a();
                } else {
                    a2 = new JabberId(string, "bisphone.com", null);
                }
                if (string != null) {
                    GroupSettingModel b = m6256b(string);
                    switch (a.getInt(a.getColumnIndex("user_status"))) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            if (this.f3650A.add(b.m6335b())) {
                                this.f3667x.add(b);
                                break;
                            }
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            if (this.f3662s.m4302n() != Type.unmoderated && this.f3665v.size() == 0 && this.f3668y.add(b.m6335b())) {
                                this.f3665v.add(b);
                                break;
                            }
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            if (this.f3669z.add(b.m6335b())) {
                                this.f3666w.add(b);
                                break;
                            }
                            break;
                    }
                    m6255a(a2);
                }
            } while (a.moveToNext());
        }
        if (this.f3665v.size() == 0 && this.f3662s.m4302n() != Type.unmoderated && this.f3668y.add(this.f3662s.m4304p())) {
            this.f3665v.add(m6256b(this.f3662s.m4304p()));
        }
        if (this.f3654E) {
            Main.f1926a.m5681c("request for vcard");
            VCardHandler.m7499a().m7502a(this.f3651B);
        }
        m6273q();
        m6270n();
    }

    private void m6255a(JabberId jabberId) {
        if (VCardHandler.m7499a().m7506c(jabberId.m7391e())) {
            Main.f1926a.m5683d("duplicate request for " + jabberId.m7391e());
        } else if (this.f3654E && !VCardHandler.m7499a().m7506c(jabberId.m7391e())) {
            VCardHandler.m7499a().m7500a(jabberId.m7391e());
            if (this.f3663t.containsKey(jabberId.m7387a())) {
                ContactEntity contactEntity = (ContactEntity) this.f3663t.get(jabberId.m7387a());
                if (contactEntity.m4210m() == TYPE.LOCAL || contactEntity.m4210m() == TYPE.REMOTE) {
                    this.f3651B.put(jabberId.m7391e(), new VCardInsertUpdateEntity(jabberId.m7391e(), false));
                    return;
                } else if (contactEntity.m4210m() == null) {
                    this.f3651B.put(jabberId.m7391e(), new VCardInsertUpdateEntity(jabberId.m7391e(), false));
                    return;
                } else {
                    return;
                }
            }
            this.f3651B.put(jabberId.m7391e(), new VCardInsertUpdateEntity(jabberId.m7391e(), true));
        }
    }

    private void m6273q() {
        GroupSettingModel b = m6256b(AccountManager.m3934a().m3937c());
        if (this.f3662s.m4302n().equals(Type.unmoderated) && this.f3669z.add(b.m6335b())) {
            this.f3666w.add(0, b);
        } else if (!this.f3662s.m4304p().equals(AccountManager.m3934a().m3937c()) && this.f3669z.add(b.m6335b())) {
            this.f3666w.add(0, b);
        }
    }

    private GroupSettingModel m6256b(String str) {
        if (str.equals(AccountManager.m3934a().m3937c())) {
            return new GroupSettingModel(getString(2131296560), AccountManager.m3934a().m3937c(), AccountManager.m3934a().m3937c(), this.f3652C.m4212o(), null, true, TYPE.SELF, getString(2131296560));
        }
        if (this.f3663t.containsKey(str)) {
            return m6252a((ContactEntity) this.f3663t.get(str));
        }
        return new GroupSettingModel(str, str, null, null, null, false, TYPE.REMOTE, getResources().getString(2131296307));
    }

    public void onEvent(VCardInfoLoadEvent vCardInfoLoadEvent) {
        this.f3654E = false;
        this.f3661r.m6281a(vCardInfoLoadEvent.m4955a());
        if (!isFinishing()) {
            runOnUiThread(new C03312(this));
        }
    }

    public void onEventMainThread(KickResponseUiEvent kickResponseUiEvent) {
        if (kickResponseUiEvent.m4933d()) {
            switch (C03366.f3648a[kickResponseUiEvent.m4932c().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                default:
            }
        } else if (kickResponseUiEvent.m4930a().equals(this.f3653D)) {
            m6260c(kickResponseUiEvent.m4931b());
        }
    }

    public void onEventMainThread(LeaveMemberEvent leaveMemberEvent) {
        if (leaveMemberEvent.m4938a().equals(this.f3653D) && leaveMemberEvent.m4939b() != null) {
            m6260c(leaveMemberEvent.m4939b());
        }
    }

    public void onEventMainThread(InvitationResponseUiEvent invitationResponseUiEvent) {
        if (!invitationResponseUiEvent.m4914a().equals(this.f3653D)) {
            return;
        }
        if (invitationResponseUiEvent.m4917d()) {
            switch (C03366.f3648a[invitationResponseUiEvent.m4916c().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    return;
                default:
                    return;
            }
        }
        m6262d(invitationResponseUiEvent.m4915b());
        if (invitationResponseUiEvent.m4915b() != null) {
            this.f3666w.add(m6256b(invitationResponseUiEvent.m4915b()));
            this.f3654E = true;
            m6255a(new JabberId(invitationResponseUiEvent.m4915b(), "bisphone.com", null));
            VCardHandler.m7499a().m7502a(this.f3651B);
            if ((this.f3662s.m4311w() > 0 && this.f3657H) || (this.f3662s.m4310v() > 0 && !this.f3657H)) {
                this.f3662s.m4281d(this.f3662s.m4310v() > 0 ? this.f3662s.m4310v() - 1 : 0);
            }
            if (this.f3657H) {
                int w;
                ConversationGroupEntity conversationGroupEntity = this.f3662s;
                if (this.f3662s.m4311w() > 0) {
                    w = this.f3662s.m4311w() - 1;
                } else {
                    w = 0;
                }
                conversationGroupEntity.m4284e(w);
            }
            this.f3657H = false;
            m6270n();
        }
    }

    private void m6260c(String str) {
        Iterator it = this.f3666w.iterator();
        while (it.hasNext()) {
            if (str.equals(((GroupSettingModel) it.next()).m6335b())) {
                it.remove();
            }
        }
        m6270n();
    }

    private void m6262d(String str) {
        Iterator it = this.f3667x.iterator();
        while (it.hasNext()) {
            if (str.equals(((GroupSettingModel) it.next()).m6335b())) {
                it.remove();
            }
        }
    }

    private GroupSettingModel m6252a(ContactEntity contactEntity) {
        return new GroupSettingModel(contactEntity.m4196e(), contactEntity.m4200g(), contactEntity.m4198f(), contactEntity.m4212o(), contactEntity.m4211n(), false, contactEntity.m4210m(), contactEntity.m4209l());
    }

    private ArrayList<String> m6274r() {
        ArrayList<String> arrayList = new ArrayList();
        Iterator it = this.f3664u.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) it.next()).iterator();
            while (it2.hasNext()) {
                arrayList.add(((GroupSettingModel) it2.next()).m6335b());
            }
        }
        return arrayList;
    }

    public void m6275a(int i, int i2) {
        GroupSettingModel groupSettingModel = (GroupSettingModel) ((ArrayList) this.f3661r.m6288e().get(i)).get(i2);
        if (!groupSettingModel.m6339e()) {
            String str;
            String a = groupSettingModel.m6332a();
            String d = groupSettingModel.m6338d();
            if (this.f3663t.get(groupSettingModel.m6335b()) == null) {
                str = "0";
            } else {
                str = ((ContactEntity) this.f3663t.get(groupSettingModel.m6335b())).m4192c();
            }
            Parcelable profileModel = new ProfileModel(a, d, str, groupSettingModel.m6337c(), groupSettingModel.m6335b(), groupSettingModel.m6340f(), 0, groupSettingModel.m6341g());
            Intent intent = new Intent(this, ProfileViewer.class);
            intent.putExtra(DataPacketExtension.ELEMENT, profileModel);
            startActivity(intent);
        }
    }

    public void m6276b(int i, int i2) {
        String c = ((GroupSettingModel) ((ArrayList) this.f3661r.m6288e().get(i)).get(i2)).m6340f() == TYPE.LOCAL ? ((GroupSettingModel) ((ArrayList) this.f3661r.m6288e().get(i)).get(i2)).m6337c() : (((GroupSettingModel) ((ArrayList) this.f3661r.m6288e().get(i)).get(i2)).m6340f() != TYPE.REMOTE || ((GroupSettingModel) ((ArrayList) this.f3661r.m6288e().get(i)).get(i2)).m6341g() == null) ? getResources().getString(2131296307) : ((GroupSettingModel) ((ArrayList) this.f3661r.m6288e().get(i)).get(i2)).m6341g();
        Builder a = new Builder(this, 2131558538).m1980a(getString(2131296482)).m1986b(String.format(getString(2131296422), new Object[]{c})).m1981a(getString(2131296786), new C03323(this, i, i2));
        a.m1989c(2130837731);
        a.m1985b(2131296784, null);
        a.m1992c();
    }

    public void m6277c(int i, int i2) {
        Builder a = new Builder(this, 2131558537).m1980a(getString(2131296491)).m1989c(2130837730).m1986b(getString(2131296492)).m1981a(getString(2131296786), new C03344(this, i2));
        a.m1985b(2131296784, null);
        a.m1992c();
    }

    void m6278j() {
        if (this.f3662s.m4312x() <= this.f3662s.m4310v() + this.f3662s.m4309u()) {
            m3922a(getResources().getString(2131296456));
            return;
        }
        Intent intent = new Intent(this, SelectRecipientActivity.class);
        intent.putStringArrayListExtra("skipped_contacts", m6274r());
        intent.putExtra("is_selecting_for_group", true);
        intent.putExtra("group_member_accepted", (this.f3662s.m4312x() - this.f3662s.m4310v()) + this.f3662s.m4309u());
        startActivityForResult(intent, 190);
    }

    public void onEvent(GetGroupMemberChanged getGroupMemberChanged) {
        if (!isFinishing()) {
            this.f3663t.putAll(ContactDataSource.m4554a(ContactDataSource.m4553a().m4574d(getGroupMemberChanged.m4884a())));
            this.f3660q.post(new C03355(this, getGroupMemberChanged));
        }
    }
}
