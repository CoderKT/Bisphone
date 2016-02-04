package app.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import app.common.LocalizeActivity;
import app.util.KeyboardUtil;
import java.util.Arrays;
import org.jivesoftware.smack.packet.Message;

public class CountrySelectActivity extends LocalizeActivity {
    private Ui f4449k;
    private CountryNameAndCode[] f4450o;
    private CountryListAdapter f4451p;
    private SearchView f4452q;

    /* renamed from: app.signin.CountrySelectActivity.1 */
    class C04651 implements OnActionExpandListener {
        final /* synthetic */ CountrySelectActivity f4435a;

        C04651(CountrySelectActivity countrySelectActivity) {
            this.f4435a = countrySelectActivity;
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return true;
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            this.f4435a.finish();
            return false;
        }
    }

    /* renamed from: app.signin.CountrySelectActivity.2 */
    class C04662 implements OnQueryTextListener {
        final /* synthetic */ CountrySelectActivity f4436a;

        C04662(CountrySelectActivity countrySelectActivity) {
            this.f4436a = countrySelectActivity;
        }

        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        public boolean onQueryTextChange(String str) {
            this.f4436a.f4451p.getFilter().filter(str);
            return false;
        }
    }

    class CountryListAdapter extends ArrayAdapter<CountryNameAndCode> {
        final /* synthetic */ CountrySelectActivity f4440a;
        private Context f4441b;

        class ViewHolder {
            TextView f4437a;
            TextView f4438b;
            final /* synthetic */ CountryListAdapter f4439c;

            ViewHolder(CountryListAdapter countryListAdapter, View view) {
                this.f4439c = countryListAdapter;
                this.f4437a = (TextView) view.findViewById(2131755572);
                this.f4438b = (TextView) view.findViewById(2131755573);
            }
        }

        public CountryListAdapter(CountrySelectActivity countrySelectActivity, Context context, CountryNameAndCode[] countryNameAndCodeArr) {
            this.f4440a = countrySelectActivity;
            super(context, 2130903217, countryNameAndCodeArr);
            this.f4441b = context;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            LayoutInflater layoutInflater = (LayoutInflater) this.f4441b.getSystemService("layout_inflater");
            if (view == null) {
                view = layoutInflater.inflate(2130903217, viewGroup, false);
                ViewHolder viewHolder2 = new ViewHolder(this, view);
                view.setTag(viewHolder2);
                viewHolder = viewHolder2;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.f4437a.setText(((CountryNameAndCode) getItem(i)).f4442a);
            viewHolder.f4438b.setText(((CountryNameAndCode) getItem(i)).f4443b);
            return view;
        }
    }

    class CountryNameAndCode {
        String f4442a;
        String f4443b;
        final /* synthetic */ CountrySelectActivity f4444c;

        public CountryNameAndCode(CountrySelectActivity countrySelectActivity, String str, String str2) {
            this.f4444c = countrySelectActivity;
            this.f4442a = str;
            this.f4443b = str2;
        }

        public String toString() {
            return this.f4442a;
        }
    }

    class Ui {
        ListView f4447a;
        final /* synthetic */ CountrySelectActivity f4448b;

        /* renamed from: app.signin.CountrySelectActivity.Ui.1 */
        class C04671 implements OnScrollListener {
            final /* synthetic */ Ui f4445a;

            C04671(Ui ui) {
                this.f4445a = ui;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.f4445a.f4448b.f4452q != null) {
                    KeyboardUtil.m7031a(this.f4445a.f4448b, this.f4445a.f4448b.f4452q.getWindowToken());
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        }

        /* renamed from: app.signin.CountrySelectActivity.Ui.2 */
        class C04682 implements OnItemClickListener {
            final /* synthetic */ Ui f4446a;

            C04682(Ui ui) {
                this.f4446a = ui;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String charSequence = ((TextView) view.findViewById(2131755572)).getText().toString();
                ((TextView) view.findViewById(2131755573)).getText().toString();
                Intent intent = new Intent();
                intent.putExtra(Message.ELEMENT, charSequence);
                this.f4446a.f4448b.setResult(-1, intent);
                this.f4446a.f4448b.finish();
            }
        }

        Ui(CountrySelectActivity countrySelectActivity, View view) {
            this.f4448b = countrySelectActivity;
            m6843a(view);
            m6842a();
        }

        private void m6843a(View view) {
            this.f4447a = (ListView) view.findViewById(2131755161);
        }

        private void m6842a() {
            this.f4447a.setOnScrollListener(new C04671(this));
            this.f4447a.setOnItemClickListener(new C04682(this));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903074);
        String[] stringArray = getResources().getStringArray(2131361796);
        String[] stringArray2 = getResources().getStringArray(2131361793);
        this.f4450o = new CountryNameAndCode[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            this.f4450o[i] = new CountryNameAndCode(this, stringArray[i], '+' + stringArray2[i]);
        }
        this.f4449k = new Ui(this, findViewById(16908290));
        this.f4451p = new CountryListAdapter(this, this, this.f4450o);
        this.f4449k.f4447a.setAdapter(this.f4451p);
    }

    protected void onPause() {
        super.onPause();
        try {
            KeyboardUtil.m7031a(this, this.f4452q.getWindowToken());
        } catch (Exception e) {
        }
    }

    protected void onResume() {
        super.onResume();
        String stringExtra = getIntent().getStringExtra("country");
        if (stringExtra != null) {
            this.f4449k.f4447a.setSelection(Arrays.asList(getResources().getStringArray(2131361796)).indexOf(stringExtra));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820548, menu);
        MenuItem findItem = menu.findItem(2131755633);
        this.f4452q = (SearchView) findItem.getActionView();
        findItem.setOnActionExpandListener(new C04651(this));
        findItem.expandActionView();
        if (this.f4452q == null) {
            return false;
        }
        this.f4452q.setIconified(false);
        this.f4452q.setQueryHint(getString(2131296406));
        this.f4452q.setOnQueryTextListener(new C04662(this));
        return super.onCreateOptionsMenu(menu);
    }
}
