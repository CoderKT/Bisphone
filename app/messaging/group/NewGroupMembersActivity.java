package app.messaging.group;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.database.provider.ContactProvider;
import app.messaging.selector.SelectRecipientActivity;
import app.xmpp.GroupManager;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class NewGroupMembersActivity extends BaseActivity implements LoaderCallbacks<Cursor> {
    ListView f3763o;
    ImageButton f3764p;
    AutoCompleteTextView f3765q;
    private ArrayList<String> f3766r;
    private NewGroupMembersAdapter f3767s;
    private String f3768t;

    /* renamed from: app.messaging.group.NewGroupMembersActivity.1 */
    class C03461 implements OnItemClickListener {
        final /* synthetic */ NewGroupMembersActivity f3744a;

        C03461(NewGroupMembersActivity newGroupMembersActivity) {
            this.f3744a = newGroupMembersActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f3744a.f3766r.add(((AutoCompleteHolder) view.getTag()).f3749c);
            this.f3744a.f3765q.setText("");
            this.f3744a.m6346k();
        }
    }

    class AutoCompleteAdapter extends CursorAdapter {
        final /* synthetic */ NewGroupMembersActivity f3745a;
        private LayoutInflater f3746b;

        public AutoCompleteAdapter(NewGroupMembersActivity newGroupMembersActivity, Cursor cursor) {
            this.f3745a = newGroupMembersActivity;
            super(newGroupMembersActivity, cursor, 2);
            this.f3746b = LayoutInflater.from(newGroupMembersActivity);
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f3746b.inflate(2130903223, viewGroup, false);
            inflate.setTag(new AutoCompleteHolder(this.f3745a, inflate));
            return inflate;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            AutoCompleteHolder autoCompleteHolder = (AutoCompleteHolder) view.getTag();
            autoCompleteHolder.f3747a.setText(cursor.getString(3));
            autoCompleteHolder.f3750d = cursor.getString(1);
            autoCompleteHolder.f3749c = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            CustomImageLoader.m4009a().m4020a(autoCompleteHolder.f3748b, cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar")), cursor.getString(cursor.getColumnIndexOrThrow("photo_uri")), 2130837592);
        }

        public CharSequence convertToString(Cursor cursor) {
            return cursor.getString(cursor.getColumnIndexOrThrow("username"));
        }

        public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
            String[] strArr;
            String str;
            String str2 = "is_registered='1' AND status!='REMOVED'" + " AND ( name LIKE ? OR name LIKE ? )";
            if (this.f3745a.f3766r == null || this.f3745a.f3766r.size() == 0) {
                strArr = new String[2];
            } else {
                strArr = new String[(this.f3745a.f3766r.size() + 2)];
                str2 = str2 + " AND ( ";
                int i = 0;
                while (i < this.f3745a.f3766r.size()) {
                    str = str2 + "username!=?";
                    strArr[i + 2] = (String) this.f3745a.f3766r.get(i);
                    if (i != this.f3745a.f3766r.size() - 1) {
                        str = str + " AND ";
                    }
                    i++;
                    str2 = str;
                }
                str2 = str2 + ")";
            }
            str = str2 + ") GROUP BY (contact_id),(username";
            strArr[0] = charSequence + "%";
            strArr[1] = "% " + charSequence + "%";
            return this.f3745a.getContentResolver().query(ContactProvider.f2364a, null, str, strArr, "name COLLATE NOCASE");
        }
    }

    class AutoCompleteHolder {
        TextView f3747a;
        ImageView f3748b;
        String f3749c;
        String f3750d;
        final /* synthetic */ NewGroupMembersActivity f3751e;

        AutoCompleteHolder(NewGroupMembersActivity newGroupMembersActivity, View view) {
            this.f3751e = newGroupMembersActivity;
            ButterKnife.m7744a(this, view);
        }
    }

    class NewGroupMembersAdapter extends CursorAdapter {
        final /* synthetic */ NewGroupMembersActivity f3752a;
        private LayoutInflater f3753b;

        public NewGroupMembersAdapter(NewGroupMembersActivity newGroupMembersActivity, Cursor cursor) {
            this.f3752a = newGroupMembersActivity;
            super(newGroupMembersActivity, cursor, 2);
            this.f3753b = LayoutInflater.from(newGroupMembersActivity);
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f3753b.inflate(2130903229, viewGroup, false);
            inflate.setTag(new ViewHolder(this.f3752a, inflate));
            return inflate;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.f3756a.setText(cursor.getString(cursor.getColumnIndex("name")));
            viewHolder.f3761f = cursor.getString(cursor.getColumnIndex("contact_id"));
            viewHolder.f3760e = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            viewHolder.f3759d.setText("+" + viewHolder.f3760e);
            CustomImageLoader.m4009a().m4020a(viewHolder.f3757b, cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar")), cursor.getString(cursor.getColumnIndexOrThrow("photo_uri")), 2130837592);
        }
    }

    class ViewHolder {
        TextView f3756a;
        ImageView f3757b;
        ImageView f3758c;
        TextView f3759d;
        String f3760e;
        String f3761f;
        final /* synthetic */ NewGroupMembersActivity f3762g;

        /* renamed from: app.messaging.group.NewGroupMembersActivity.ViewHolder.1 */
        class C03471 implements OnClickListener {
            final /* synthetic */ NewGroupMembersActivity f3754a;
            final /* synthetic */ ViewHolder f3755b;

            C03471(ViewHolder viewHolder, NewGroupMembersActivity newGroupMembersActivity) {
                this.f3755b = viewHolder;
                this.f3754a = newGroupMembersActivity;
            }

            public void onClick(View view) {
                if (this.f3755b.f3762g.f3766r != null) {
                    this.f3755b.f3762g.f3766r.remove(this.f3755b.f3760e);
                    this.f3755b.f3762g.getLoaderManager().restartLoader(473442378, null, this.f3755b.f3762g);
                }
            }
        }

        ViewHolder(NewGroupMembersActivity newGroupMembersActivity, View view) {
            this.f3762g = newGroupMembersActivity;
            ButterKnife.m7744a(this, view);
            this.f3758c.setOnClickListener(new C03471(this, newGroupMembersActivity));
        }
    }

    public NewGroupMembersActivity() {
        this.f3766r = null;
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        m6348a(loader, (Cursor) obj);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903085);
        ButterKnife.m7741a((Activity) this);
        setTitle(getString(2131296484));
        this.f3766r = new ArrayList(0);
        this.f3767s = new NewGroupMembersAdapter(this, null);
        this.f3763o.setAdapter(this.f3767s);
        m6347l();
        ListAdapter autoCompleteAdapter = new AutoCompleteAdapter(this, null);
        this.f3765q.setThreshold(1);
        this.f3765q.setAdapter(autoCompleteAdapter);
        this.f3765q.setOnItemClickListener(new C03461(this));
        m6346k();
    }

    protected void onRestart() {
        super.onRestart();
        m6346k();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820566, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case 2131755652:
                GroupManager.m7323a().m7362b(this.f3768t, this.f3766r, null);
                finish();
                break;
        }
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 189:
                    this.f3766r = intent.getStringArrayListExtra("recipients_list");
                default:
            }
        }
    }

    void m6349j() {
        Intent intent = new Intent(this, SelectRecipientActivity.class);
        intent.putStringArrayListExtra("recipients_list", this.f3766r);
        intent.putExtra("is_selecting_for_group", true);
        intent.putExtra("group_member_accepted", 150);
        startActivityForResult(intent, 189);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i != 473442378) {
            return null;
        }
        if (this.f3766r == null || this.f3766r.size() == 0) {
            return new CursorLoader(this, ContactProvider.f2364a, null, "username='Z'", null, null);
        }
        String[] strArr = new String[this.f3766r.size()];
        String str = "is_registered='1' AND status!='REMOVED'" + " AND (";
        int i2 = 0;
        while (i2 < this.f3766r.size()) {
            String str2 = str + "username=?";
            strArr[i2] = (String) this.f3766r.get(i2);
            if (i2 != this.f3766r.size() - 1) {
                str2 = str2 + " OR ";
            }
            i2++;
            str = str2;
        }
        return new CursorLoader(this, ContactProvider.f2364a, null, str + " )) GROUP BY (username", strArr, "name COLLATE NOCASE, 'type' ASC");
    }

    public void m6348a(Loader<Cursor> loader, Cursor cursor) {
        this.f3767s.changeCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.f3767s.changeCursor(null);
    }

    private void m6346k() {
        if (getLoaderManager().getLoader(473442378) == null) {
            getLoaderManager().initLoader(473442378, null, this);
        } else {
            getLoaderManager().restartLoader(473442378, null, this);
        }
    }

    private void m6347l() {
        this.f3768t = getIntent().getStringExtra("extra_group_jid");
        if (this.f3768t == null) {
            finish();
        }
    }
}
