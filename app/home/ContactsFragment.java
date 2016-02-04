package app.home;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import app.Main;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.database.datasource.ContactDataSource;
import app.database.provider.ContactProvider;
import app.profile.MoreInfoActivity;
import app.profile.ProfileModel;
import app.profile.ProfileViewer;
import app.util.Background;
import app.xmpp.JabberId;
import app.xmpp.VCardHandler;
import app.xmpp.VCardInsertUpdateEntity;
import butterknife.ButterKnife;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import java.util.HashMap;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.search.UserSearch;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ContactsFragment extends Fragment implements LoaderCallbacks<Cursor>, OnClickListener {
    ViewGroup f2933a;
    StickyListHeadersListView f2934b;
    TextView f2935c;
    TextView f2936d;
    View f2937e;
    ProgressBar f2938f;
    TextView f2939g;
    TextView f2940h;
    TextView f2941i;
    String[] f2942j;
    private SearchView f2943k;
    private MenuItem f2944l;
    private ContactsAdapter f2945m;
    private String f2946n;
    private String f2947o;
    private boolean f2948p;
    private View f2949q;

    /* renamed from: app.home.ContactsFragment.1 */
    class C02091 implements RowOnClickListener {
        final /* synthetic */ ContactsFragment f2929a;

        C02091(ContactsFragment contactsFragment) {
            this.f2929a = contactsFragment;
        }

        public void m5386a(View view) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            ContactEntity b = ContactDataSource.m4553a().m4570b(viewHolder.f2912e);
            Parcelable profileModel = new ProfileModel(b.m4212o(), b.m4211n(), b.m4192c(), viewHolder.f2913f, viewHolder.f2912e, viewHolder.f2914g, viewHolder.f2915h, viewHolder.f2916i);
            Intent intent = new Intent(this.f2929a.getActivity(), ProfileViewer.class);
            intent.putExtra(DataPacketExtension.ELEMENT, profileModel);
            this.f2929a.startActivity(intent);
        }
    }

    /* renamed from: app.home.ContactsFragment.2 */
    class C02102 implements OnActionExpandListener {
        final /* synthetic */ Menu f2930a;
        final /* synthetic */ ContactsFragment f2931b;

        C02102(ContactsFragment contactsFragment, Menu menu) {
            this.f2931b = contactsFragment;
            this.f2930a = menu;
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            if (this.f2931b.getActivity().getActionBar() != null) {
                this.f2931b.getActivity().getActionBar().setNavigationMode(0);
            }
            this.f2930a.findItem(2131755632).setVisible(false);
            this.f2930a.findItem(2131755629).setVisible(false);
            ((HomeActivity) this.f2931b.getActivity()).m5539j().setPagingEnabled(false);
            return true;
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            if (this.f2931b.getActivity().getActionBar() != null) {
                this.f2931b.getActivity().getActionBar().setNavigationMode(2);
            }
            this.f2930a.findItem(2131755632).setVisible(true);
            this.f2930a.findItem(2131755629).setVisible(true);
            this.f2931b.f2946n = null;
            this.f2931b.f2945m.m5378a(null);
            this.f2931b.getLoaderManager().restartLoader(111, null, this.f2931b);
            ((HomeActivity) this.f2931b.getActivity()).m5539j().setPagingEnabled(true);
            return true;
        }
    }

    /* renamed from: app.home.ContactsFragment.3 */
    class C02113 implements OnQueryTextListener {
        final /* synthetic */ ContactsFragment f2932a;

        C02113(ContactsFragment contactsFragment) {
            this.f2932a = contactsFragment;
        }

        public boolean onQueryTextSubmit(String str) {
            return true;
        }

        public boolean onQueryTextChange(String str) {
            Main.f1926a.m5683d("onQueryChanged " + str);
            if (this.f2932a.f2945m.m5384b()) {
                this.f2932a.f2945m.m5383b(false);
                TextView textView = (TextView) this.f2932a.f2943k.findViewById(this.f2932a.f2943k.getContext().getResources().getIdentifier("android:id/search_src_text", null, null));
                if (textView != null) {
                    Main.f1926a.m5683d("onQueryChanged setText " + this.f2932a.f2945m.m5376a());
                    textView.setText(this.f2932a.f2945m.m5376a());
                    return true;
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            if (!(this.f2932a.f2946n == null && str == null) && (this.f2932a.f2946n == null || !this.f2932a.f2946n.equals(str))) {
                this.f2932a.f2946n = str;
                this.f2932a.f2945m.m5378a(str);
                this.f2932a.getLoaderManager().restartLoader(111, null, this.f2932a);
            }
            return true;
        }
    }

    public ContactsFragment() {
        this.f2948p = false;
        this.f2942j = new String[]{"_id", "contact_id", "contact_data_table_id", "name", "username", "version", Status.ELEMENT, "is_registered", "is_favorite", "vcard_nickname", "photo_uri", "phone_number", "vcard_avatar", "last_seen", "background_json", "recently_joined", "type", "sum(is_registered) AS is_registered"};
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m5396a(loader, (Cursor) obj);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2948p = false;
        if (bundle != null) {
            this.f2946n = bundle.getString(UserSearch.ELEMENT);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903132, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        this.f2937e.setVisibility(8);
        this.f2935c.setOnClickListener(this);
        this.f2936d.setOnClickListener(this);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        ButterKnife.m7743a((Object) this);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        this.f2945m = new ContactsAdapter(getActivity(), null);
        this.f2945m.m5380a(new C02091(this));
        this.f2949q = getActivity().getLayoutInflater().inflate(2130903126, this.f2933a, false);
        ((ViewGroup) this.f2934b.getParent()).addView(this.f2949q);
        m5390a(8);
        m5394b(0);
        this.f2939g = new TextView(getActivity());
        this.f2939g.setLayoutParams(new LayoutParams(-1, -2));
        this.f2939g.setPadding(0, 5, 0, 5);
        this.f2939g.setGravity(17);
        this.f2939g.setTextColor(getResources().getColor(2131689523));
        this.f2934b.m13510b(this.f2939g);
        View inflate = getActivity().getLayoutInflater().inflate(2130903116, null);
        inflate.setOnClickListener(null);
        this.f2934b.m13508a(inflate);
        this.f2940h = (TextView) inflate.findViewById(2131755303);
        this.f2941i = (TextView) inflate.findViewById(2131755304);
        this.f2941i.setOnClickListener(this);
        this.f2940h.setOnClickListener(this);
        this.f2934b.setAdapter(this.f2945m);
        this.f2934b.setOnScrollListener(new PauseOnScrollListener(ImageLoader.m11076a(), false, true));
        getActivity().getLoaderManager().initLoader(111, null, this).forceLoad();
        m5393b();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820547, menu);
        this.f2944l = menu.findItem(2131755633);
        this.f2944l.setOnActionExpandListener(new C02102(this, menu));
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.f2944l != null) {
            this.f2943k = (SearchView) this.f2944l.getActionView();
            this.f2943k.setQueryHint(getString(2131296675));
            this.f2943k.setOnQueryTextListener(new C02113(this));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755629:
                startActivity(new Intent(Main.f1927b, MoreInfoActivity.class));
                break;
            case 2131755632:
                Intent intent = new Intent("android.intent.action.INSERT");
                intent.setType("vnd.android.cursor.dir/contact");
                if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
                    Main.m3905a(getString(2131296811));
                    break;
                }
                startActivity(intent);
                break;
        }
        return true;
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        m5390a(8);
        m5394b(0);
        if (i != 111) {
            return null;
        }
        if (this.f2948p) {
            if (this.f2946n == null) {
                return new CursorLoader(getActivity(), ContactProvider.f2364a, this.f2942j, "type='" + TYPE.LOCAL + "' AND " + Status.ELEMENT + "!='REMOVED' ) GROUP BY ( " + "contact_id" + "),(" + "name", null, "name COLLATE NOCASE,is_registered DESC");
            }
            return new CursorLoader(getActivity(), ContactProvider.f2364a, this.f2942j, "type='" + TYPE.LOCAL + "' AND " + "(" + "name" + " LIKE ? OR " + "name" + " LIKE ?) AND " + Status.ELEMENT + "!='REMOVED' ) GROUP BY ( " + "username" + "),(" + "name", new String[]{this.f2946n + "%", "% " + this.f2946n + "%"}, "name COLLATE NOCASE,is_registered DESC");
        } else if (this.f2946n == null) {
            return new CursorLoader(getActivity(), ContactProvider.f2365b, null, "is_registered='1' AND type='" + TYPE.LOCAL + "' AND " + Status.ELEMENT + "!='REMOVED' ) GROUP BY (" + "username" + "),(" + "name", null, "name COLLATE NOCASE");
        } else {
            return new CursorLoader(getActivity(), ContactProvider.f2365b, null, "is_registered='1' AND type='" + TYPE.LOCAL + "' AND " + "(" + "name" + " LIKE ? OR " + "name" + " LIKE ?) AND " + Status.ELEMENT + "!='REMOVED' ) GROUP BY ( " + "name" + "),(" + "username", new String[]{this.f2946n + "%", "% " + this.f2946n + "%"}, "name COLLATE NOCASE");
        }
    }

    public void m5396a(Loader<Cursor> loader, Cursor cursor) {
        int i = 0;
        m5394b(8);
        if ((this.f2946n == null || !this.f2946n.equals(this.f2947o)) && cursor != null) {
            if (cursor.getCount() == 0) {
                this.f2937e.setVisibility(0);
                this.f2934b.setVisibility(8);
                if (this.f2946n == null) {
                    m5390a(0);
                }
            } else {
                this.f2937e.setVisibility(8);
                m5390a(8);
                this.f2934b.setVisibility(0);
            }
            if (cursor.isClosed()) {
                getLoaderManager().restartLoader(111, null, this);
            } else if (loader.getId() == 111) {
                HashMap hashMap = new HashMap();
                if (!this.f2948p && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    int i2 = 0;
                    do {
                        if (!(cursor.getString(cursor.getColumnIndex("recently_joined")) == null || cursor.getString(cursor.getColumnIndex("recently_joined")).equals("0"))) {
                            i2++;
                        }
                        if (cursor.getString(cursor.getColumnIndex("vCard_checked_time")) == null || cursor.getString(cursor.getColumnIndex("vCard_checked_time")).equals("0")) {
                            JabberId jabberId = new JabberId(cursor.getString(cursor.getColumnIndex("username")), "bisphone.com", null);
                            hashMap.put(jabberId.m7391e(), new VCardInsertUpdateEntity(jabberId.m7391e(), false));
                        }
                    } while (cursor.moveToNext());
                    i = i2;
                }
                this.f2945m.m5385c(i / 2);
                this.f2945m.swapCursor(cursor);
                this.f2945m.m5381a(this.f2948p);
                String str = " " + getString(2131296338);
                if (this.f2948p) {
                    str = " " + getString(2131296400);
                }
                this.f2939g.setText((cursor.getCount() - (i / 2)) + str);
                if (hashMap.size() > 0) {
                    VCardHandler.m7499a().m7502a(hashMap);
                }
            }
        }
    }

    private void m5389a() {
        m5390a(8);
        m5394b(0);
        if (this.f2945m != null) {
            this.f2945m.swapCursor(null);
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        m5390a(8);
        m5394b(0);
        if (loader.getId() == 111 && this.f2945m != null) {
            this.f2945m.swapCursor(null);
        }
    }

    private void m5393b() {
        if (this.f2948p) {
            m5391a(this.f2941i);
        } else {
            m5391a(this.f2940h);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755303:
                if (this.f2948p) {
                    this.f2948p = false;
                    m5389a();
                    m5391a(this.f2940h);
                    getLoaderManager().restartLoader(111, null, this);
                }
            case 2131755304:
                if (!this.f2948p) {
                    this.f2948p = true;
                    m5389a();
                    m5391a(this.f2941i);
                    getLoaderManager().restartLoader(111, null, this);
                }
            default:
        }
    }

    private void m5390a(int i) {
        if (this.f2949q != null) {
            this.f2949q.setVisibility(i);
        }
    }

    private void m5394b(int i) {
        if (this.f2938f != null) {
            this.f2938f.setVisibility(i);
        }
    }

    private void m5391a(View view) {
        Background.m6968a(getResources().getDrawable(2130837583), this.f2941i);
        Background.m6968a(getResources().getDrawable(2130837610), this.f2940h);
        Background.m6968a(getResources().getDrawable(2130837583), this.f2936d);
        Background.m6968a(getResources().getDrawable(2130837610), this.f2935c);
        if (view == this.f2941i) {
            Background.m6968a(getResources().getDrawable(2130837584), this.f2941i);
            this.f2941i.setTextColor(getResources().getColor(2131689566));
            this.f2940h.setTextColor(getResources().getColor(2131689564));
            Background.m6968a(getResources().getDrawable(2130837584), this.f2936d);
            this.f2936d.setTextColor(getResources().getColor(2131689566));
            this.f2935c.setTextColor(getResources().getColor(2131689564));
            return;
        }
        Background.m6968a(getResources().getDrawable(2130837611), this.f2940h);
        this.f2940h.setTextColor(getResources().getColor(2131689566));
        this.f2941i.setTextColor(getResources().getColor(2131689564));
        Background.m6968a(getResources().getDrawable(2130837611), this.f2935c);
        this.f2935c.setTextColor(getResources().getColor(2131689566));
        this.f2936d.setTextColor(getResources().getColor(2131689564));
    }
}
