package app.messaging.selector;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ConversationNormalEntity.Builder;
import app.common.entity.HistoryEntity.Type;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.BroadcastUsersDataSource;
import app.database.provider.ContactProvider;
import app.messaging.BroadcastMessagingActivity;
import app.messaging.NormalMessagingActivity;
import app.messaging.selector.SelectContactAdapter.ViewHolder;
import app.messaging.selector.SelectRecipientActivity.ForwardHistoryType;
import app.util.StringUtil;
import app.util.Utils;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageManager;
import butterknife.ButterKnife;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public class SelectContactFragment extends Fragment implements LoaderCallbacks<Cursor> {
    ListView f3804a;
    EditText f3805b;
    ImageView f3806c;
    LinearLayout f3807d;
    HorizontalScrollView f3808e;
    List<View> f3809f;
    SelectContactAdapter f3810g;
    ArrayList<String> f3811h;
    public ActionMode f3812i;
    String f3813j;
    private String f3814k;
    private String f3815l;
    private String f3816m;
    private List<String> f3817n;
    private List<String> f3818o;
    private boolean f3819p;
    private boolean f3820q;
    private boolean f3821r;
    private boolean f3822s;
    private Callback f3823t;

    /* renamed from: app.messaging.selector.SelectContactFragment.1 */
    class C03481 implements OnItemClickListener {
        final /* synthetic */ SelectContactFragment f3791a;

        C03481(SelectContactFragment selectContactFragment) {
            this.f3791a = selectContactFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f3791a.f3819p) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (this.f3791a.f3810g.m6353a(viewHolder.f3778e)) {
                    this.f3791a.m6359a(viewHolder.f3778e);
                    viewHolder.f3777d.setChecked(false);
                } else if (this.f3791a.f3810g.m6350a().size() < 99) {
                    this.f3791a.f3808e.setVisibility(0);
                    this.f3791a.m6360a(viewHolder.f3778e, viewHolder.f3779f, viewHolder.f3781h, viewHolder.f3780g, i);
                    viewHolder.f3777d.setChecked(true);
                }
                if (this.f3791a.f3812i == null) {
                    this.f3791a.f3812i = this.f3791a.getActivity().startActionMode(this.f3791a.f3823t);
                    this.f3791a.m6379a();
                }
                if (this.f3791a.f3810g.m6350a().size() > 0) {
                    this.f3791a.f3812i.setTitle(this.f3791a.f3810g.m6350a().size() + " " + this.f3791a.f3813j);
                }
            }
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.2 */
    class C03492 implements OnClickListener {
        final /* synthetic */ SelectContactFragment f3792a;

        C03492(SelectContactFragment selectContactFragment) {
            this.f3792a = selectContactFragment;
        }

        public void onClick(View view) {
            this.f3792a.f3805b.setText("");
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.3 */
    class C03503 implements TextWatcher {
        final /* synthetic */ SelectContactFragment f3793a;

        C03503(SelectContactFragment selectContactFragment) {
            this.f3793a = selectContactFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.length() == 0) {
                this.f3793a.f3806c.setVisibility(8);
                this.f3793a.f3814k = null;
            } else {
                this.f3793a.f3806c.setVisibility(0);
                this.f3793a.f3814k = charSequence.toString();
            }
            this.f3793a.getLoaderManager().restartLoader(117, null, this.f3793a);
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.4 */
    class C03514 implements AnimationListener {
        final /* synthetic */ int f3794a;
        final /* synthetic */ String f3795b;
        final /* synthetic */ SelectContactFragment f3796c;

        C03514(SelectContactFragment selectContactFragment, int i, String str) {
            this.f3796c = selectContactFragment;
            this.f3794a = i;
            this.f3795b = str;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f3796c.f3807d.removeViewAt(this.f3794a);
            this.f3796c.f3809f.remove(this.f3794a);
            this.f3796c.f3810g.m6355b(this.f3795b);
            if (!(this.f3796c.f3812i == null || this.f3796c.f3810g == null || this.f3796c.f3810g.m6350a().size() <= 0)) {
                this.f3796c.f3812i.setTitle(this.f3796c.f3810g.m6350a().size() + " " + this.f3796c.f3813j);
            }
            this.f3796c.f3819p = true;
            if (this.f3796c.f3812i != null && this.f3796c.f3810g != null && this.f3796c.f3810g.m6354b().size() == 0) {
                this.f3796c.f3812i.finish();
                this.f3796c.f3808e.setVisibility(8);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.5 */
    class C03525 implements OnClickListener {
        final /* synthetic */ SelectContactFragment f3797a;

        C03525(SelectContactFragment selectContactFragment) {
            this.f3797a = selectContactFragment;
        }

        public void onClick(View view) {
            this.f3797a.f3804a.smoothScrollToPositionFromTop(((Integer) view.getTag()).intValue(), 0);
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.6 */
    class C03536 implements OnClickListener {
        final /* synthetic */ SelectContactFragment f3798a;

        C03536(SelectContactFragment selectContactFragment) {
            this.f3798a = selectContactFragment;
        }

        public void onClick(View view) {
            this.f3798a.m6359a(view.getTag().toString());
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.7 */
    class C03547 implements Runnable {
        final /* synthetic */ View f3799a;
        final /* synthetic */ SelectContactFragment f3800b;

        C03547(SelectContactFragment selectContactFragment, View view) {
            this.f3800b = selectContactFragment;
            this.f3799a = view;
        }

        public void run() {
            this.f3800b.f3808e.smoothScrollTo(this.f3799a.getRight(), 0);
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.8 */
    class C03558 implements Callback {
        final /* synthetic */ SelectContactFragment f3801a;

        C03558(SelectContactFragment selectContactFragment) {
            this.f3801a = selectContactFragment;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater menuInflater = actionMode.getMenuInflater();
            if (this.f3801a.f3822s) {
                menuInflater.inflate(2131820565, menu);
                if (this.f3801a.getActivity().getActionBar() != null) {
                    this.f3801a.getActivity().getActionBar().setTitle(this.f3801a.getString(2131296457));
                }
            } else {
                menuInflater.inflate(2131820569, menu);
            }
            ((SelectRecipientActivity) this.f3801a.getActivity()).f3885r.setPagingEnabled(false);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            ArrayList arrayList;
            Intent intent;
            if (menuItem.getItemId() == 2131755654) {
                this.f3801a.f3811h = new ArrayList(this.f3801a.f3810g.m6354b());
                if (this.f3801a.f3821r) {
                    arrayList = new ArrayList(this.f3801a.f3810g.m6354b());
                    if (arrayList.size() < 2) {
                        Main.m3905a(this.f3801a.getString(2131296678));
                        return false;
                    }
                    intent = new Intent();
                    intent.putStringArrayListExtra("extra_is_broadcast_edit_mode", arrayList);
                    this.f3801a.getActivity().setResult(-1, intent);
                    this.f3801a.getActivity().finish();
                } else if (this.f3801a.f3811h.size() == 1) {
                    this.f3801a.m6371e();
                } else if (this.f3801a.f3811h.size() > 1 && this.f3801a.m6367c() && this.f3801a.getArguments() != null && this.f3801a.getArguments().containsKey("extra_is_forward_mode")) {
                    if (this.f3801a.f3817n != null && this.f3801a.f3810g.m6354b().size() == this.f3801a.f3817n.size()) {
                        int i;
                        for (String contains : this.f3801a.f3810g.m6354b()) {
                            if (!this.f3801a.f3817n.contains(contains)) {
                                i = 0;
                                break;
                            }
                        }
                        i = 1;
                        if (i != 0) {
                            this.f3801a.getActivity().finish();
                            return true;
                        }
                    }
                    this.f3801a.m6373f();
                } else if (this.f3801a.f3811h.size() > 1) {
                    this.f3801a.m6369d();
                }
            } else if (menuItem.getItemId() == 2131755636) {
                if (this.f3801a.f3810g.m6354b().size() > this.f3801a.getActivity().getIntent().getIntExtra("group_member_accepted", 0)) {
                    Toast.makeText(this.f3801a.getActivity(), this.f3801a.getResources().getString(2131296455), 1).show();
                    return false;
                }
                arrayList = new ArrayList(this.f3801a.f3810g.m6354b());
                intent = new Intent();
                intent.putStringArrayListExtra("recipients_list", arrayList);
                this.f3801a.getActivity().setResult(-1, intent);
                this.f3801a.getActivity().finish();
            }
            actionMode.finish();
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.f3801a.m6366b();
        }
    }

    /* renamed from: app.messaging.selector.SelectContactFragment.9 */
    class C03569 extends ArrayList<String> {
        final /* synthetic */ String f3802a;
        final /* synthetic */ SelectContactFragment f3803b;

        C03569(SelectContactFragment selectContactFragment, String str) {
            this.f3803b = selectContactFragment;
            this.f3802a = str;
            add(new JabberId(this.f3802a, "bisphone.com", null).m7391e());
        }
    }

    public SelectContactFragment() {
        this.f3817n = null;
        this.f3818o = null;
        this.f3812i = null;
        this.f3813j = "";
        this.f3820q = false;
        this.f3821r = false;
        this.f3822s = false;
        this.f3823t = new C03558(this);
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m6380a(loader, (Cursor) obj);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3822s = getActivity().getIntent().getBooleanExtra("is_selecting_for_group", false);
        this.f3820q = getActivity().getIntent().getBooleanExtra("extra_is_forward_mode", false);
        this.f3821r = getActivity().getIntent().getBooleanExtra("extra_is_broadcast_edit_mode", false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903147, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f3813j = getActivity().getString(2131296399);
        this.f3804a.setChoiceMode(2);
        this.f3808e.setVisibility(8);
        this.f3809f = new ArrayList();
        this.f3819p = true;
        this.f3810g = new SelectContactAdapter(getActivity(), null, getActivity().getFragmentManager());
        try {
            if (getArguments() != null) {
                this.f3816m = getArguments().getString("share_text_extra");
            }
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
        }
        this.f3817n = getActivity().getIntent().getStringArrayListExtra("recipients_list");
        this.f3818o = getActivity().getIntent().getStringArrayListExtra("skipped_contacts");
        this.f3804a.setOnScrollListener(new PauseOnScrollListener(ImageLoader.m11076a(), false, true));
        this.f3804a.setOnItemClickListener(new C03481(this));
        this.f3806c.setVisibility(8);
        this.f3806c.setOnClickListener(new C03492(this));
        this.f3805b.addTextChangedListener(new C03503(this));
        this.f3804a.setAdapter(this.f3810g);
        getActivity().getLoaderManager().initLoader(117, null, this);
    }

    private void m6359a(String str) {
        this.f3819p = false;
        for (int i = 0; i < this.f3809f.size(); i++) {
            if (str.equals(((View) this.f3809f.get(i)).getTag().toString())) {
                Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(200);
                ((View) this.f3809f.get(i)).startAnimation(scaleAnimation);
                scaleAnimation.setAnimationListener(new C03514(this, i, str));
                return;
            }
        }
    }

    private void m6360a(String str, String str2, String str3, String str4, int i) {
        this.f3808e.setVisibility(0);
        View inflate = getActivity().getLayoutInflater().inflate(2130903232, null);
        this.f3810g.m6352a(str, str2, str3, str4);
        TextView textView = (TextView) inflate.findViewById(2131755613);
        ImageView imageView = (ImageView) inflate.findViewById(2131755612);
        View findViewById = inflate.findViewById(2131755614);
        CustomImageLoader.m4009a().m4020a(imageView, str4, str2, 2130837592);
        textView.setText(str3);
        inflate.setTag(str);
        findViewById.setTag(str);
        imageView.setTag(Integer.valueOf(i));
        this.f3807d.addView(inflate);
        this.f3809f.add(inflate);
        imageView.setOnClickListener(new C03525(this));
        findViewById.setOnClickListener(new C03536(this));
        this.f3808e.post(new C03547(this, inflate));
    }

    private void m6366b() {
        this.f3812i = null;
        this.f3804a.clearChoices();
        this.f3810g.m6356c();
        ((SelectRecipientActivity) getActivity()).f3885r.setPagingEnabled(true);
        this.f3807d.removeAllViews();
        this.f3808e.setVisibility(8);
        this.f3809f = new ArrayList();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.f3822s) {
            menuInflater.inflate(2131820565, menu);
            if (getActivity().getActionBar() != null) {
                getActivity().getActionBar().setTitle(getString(2131296457));
                return;
            }
            return;
        }
        menuInflater.inflate(2131820569, menu);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i != 117) {
            return null;
        }
        String str = this.f3814k;
        List arrayList = new ArrayList();
        String str2 = "is_registered='1' AND type= '" + TYPE.LOCAL + "' AND " + Status.ELEMENT + "!='REMOVED'";
        if (!(str == null || str.equals(""))) {
            str2 = str2 + " AND ( name LIKE ? OR name LIKE ? )";
            arrayList.add(str + "%");
            arrayList.add("% " + str + "%");
        }
        if (!(this.f3818o == null || this.f3818o.size() == 0)) {
            str = str2 + " AND ( ";
            for (int i2 = 0; i2 < this.f3818o.size(); i2++) {
                str = str + "username!=?";
                arrayList.add(this.f3818o.get(i2));
                if (i2 != this.f3818o.size() - 1) {
                    str = str + " AND ";
                }
            }
            str2 = str + ")";
        }
        return new CursorLoader(getActivity(), ContactProvider.f2364a, null, str2 + " ) GROUP BY ( name),(username", (String[]) arrayList.toArray(new String[arrayList.size()]), "name COLLATE NOCASE");
    }

    public void m6380a(Loader<Cursor> loader, Cursor cursor) {
        if (this.f3814k == null || !this.f3814k.equals(this.f3815l)) {
            this.f3815l = this.f3814k;
            if (cursor.isClosed()) {
                getLoaderManager().restartLoader(117, null, this);
            } else if (loader.getId() == 117) {
                this.f3810g.changeCursor(cursor);
            }
            if (this.f3817n != null && cursor != null && cursor.moveToFirst()) {
                int i = 0;
                while (i < cursor.getCount()) {
                    if (this.f3817n.contains(cursor.getString(5))) {
                        Object obj = 1;
                        for (int i2 = 0; i2 < this.f3809f.size(); i2++) {
                            if (((View) this.f3809f.get(i2)).getTag().toString().equals(cursor.getString(5))) {
                                obj = null;
                            }
                        }
                        if (obj != null) {
                            m6360a(cursor.getString(5), cursor.getString(cursor.getColumnIndexOrThrow("photo_uri")), cursor.getString(3), cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar")), i);
                        }
                    }
                    i++;
                    cursor.moveToNext();
                }
                if (this.f3812i == null) {
                    this.f3812i = getActivity().startActionMode(this.f3823t);
                }
                if (this.f3810g.m6350a().size() > 0) {
                    this.f3812i.setTitle(this.f3810g.m6350a().size() + " " + this.f3813j);
                }
            }
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void m6379a() {
        int identifier = Resources.getSystem().getIdentifier("action_mode_close_button", "id", "android");
        if (identifier != 0 && getActivity() != null) {
            View findViewById = getActivity().findViewById(identifier);
            if (findViewById != null) {
                LinearLayout linearLayout = (LinearLayout) findViewById;
                if (linearLayout != null) {
                    findViewById = linearLayout.getChildAt(1);
                    if (findViewById != null) {
                        ((TextView) findViewById).setText(" ");
                    }
                }
            }
        }
    }

    private boolean m6367c() {
        Collections.sort(this.f3811h);
        return !NormalMessageManager.m7447a().m7467d(Utils.m7080a(this.f3811h));
    }

    private void m6369d() {
        String c;
        Collections.sort(this.f3811h);
        String a = Utils.m7080a(this.f3811h);
        if (NormalMessageManager.m7447a().m7467d(a)) {
            c = NormalMessageManager.m7447a().m7468e(a).m4168c();
        } else {
            c = StringUtil.m7065b();
            m6361a(c, a, this.f3811h);
        }
        Intent intent = new Intent(getActivity(), BroadcastMessagingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("forward_recipients_id", this.f3811h);
        bundle.putString("extra_broadcast_id", c);
        if (getArguments() != null) {
            if (getArguments().containsKey("share_text_extra")) {
                bundle.putString("share_text_extra", this.f3816m);
            } else if (getArguments().containsKey("extra_is_forward_mode")) {
                bundle.putInt("multiple_forward_message_type", ForwardHistoryType.broadcast.ordinal());
                Intent intent2 = getActivity().getIntent();
                intent2.putExtra("multiple_forward_message", bundle);
                getActivity().setResult(-1, intent2);
                getActivity().finish();
                return;
            } else if (getArguments().containsKey("share_image_extra")) {
                bundle.putString("share_image_extra", getArguments().getString("share_image_extra"));
            } else if (getArguments().containsKey("share_video_extra")) {
                bundle.putString("share_video_extra", getArguments().getString("share_video_extra"));
            }
        }
        intent.putExtras(bundle);
        intent.setFlags(67108864);
        startActivity(intent);
        getActivity().finish();
    }

    private void m6371e() {
        Intent intent = new Intent(getActivity(), NormalMessagingActivity.class);
        String str = (String) this.f3811h.toArray()[0];
        Bundle bundle = new Bundle();
        bundle.putString("extra_jabber_id", new JabberId(str, "bisphone.com", null).m7391e());
        if (getArguments() != null) {
            if (getArguments().containsKey("share_text_extra")) {
                bundle.putString("share_text_extra", this.f3816m);
            } else if (getArguments().containsKey("extra_is_forward_mode")) {
                bundle.putInt("multiple_forward_message_type", ForwardHistoryType.contact.ordinal());
                bundle.putStringArrayList("forward_recipients_id", new C03569(this, str));
                Intent intent2 = getActivity().getIntent();
                intent2.putExtra("multiple_forward_message", bundle);
                getActivity().setResult(-1, intent2);
                getActivity().finish();
                return;
            } else if (getArguments().containsKey("share_image_extra")) {
                bundle.putString("share_image_extra", getArguments().getString("share_image_extra"));
            } else if (getArguments().containsKey("share_video_extra")) {
                bundle.putString("share_video_extra", getArguments().getString("share_video_extra"));
            }
        }
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();
    }

    private void m6361a(String str, String str2, ArrayList<String> arrayList) {
        Builder builder = new Builder();
        builder.m4322d("").m4320b("").m4321c(getString(2131296599)).m4316a((System.currentTimeMillis() * 1000) + "").m4315a(Type.TEXT);
        ChatHistoryEntity chatHistoryEntity = new ChatHistoryEntity(builder.m4317a(), str, str2);
        BroadcastListDataSource.m4504a().m4513b(chatHistoryEntity);
        BroadcastUsersDataSource.m4519a().m4522a(str, (List) arrayList);
        NormalMessageManager.m7447a().m7459b(chatHistoryEntity);
    }

    private void m6373f() {
        new AlertDialog.Builder(getActivity(), 2131558537).m1980a(getString(2131296408)).m1986b(getString(2131296407)).m1975a(2131296786, new DialogInterface.OnClickListener() {
            final /* synthetic */ SelectContactFragment f3790a;

            {
                this.f3790a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f3790a.m6369d();
            }
        }).m1985b(2131296784, new DialogInterface.OnClickListener() {
            final /* synthetic */ SelectContactFragment f3789a;

            {
                this.f3789a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("forward_recipients_id", this.f3789a.f3811h);
                bundle.putInt("multiple_forward_message_type", ForwardHistoryType.contact.ordinal());
                Intent intent = this.f3789a.getActivity().getIntent();
                intent.putExtra("multiple_forward_message", bundle);
                this.f3789a.getActivity().setResult(-1, intent);
                this.f3789a.getActivity().finish();
            }
        }).m1989c(2130837730).m1992c();
    }
}
