package app.messaging.selector;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import app.Main;
import app.common.BaseActivity;
import app.view.CustomViewPager;
import se.emilsjolander.stickylistheaders.C1128R;

public class SelectRecipientActivity extends BaseActivity {
    private boolean f3881A;
    SelectContactFragment f3882o;
    Bundle f3883p;
    ActionBar f3884q;
    CustomViewPager f3885r;
    CustomViewPagerAdapter f3886s;
    Tab f3887t;
    Tab f3888u;
    Tab f3889v;
    int f3890w;
    private SelectContactFragment f3891x;
    private SelectGroupFragment f3892y;
    private SelectConversationFragment f3893z;

    /* renamed from: app.messaging.selector.SelectRecipientActivity.1 */
    class C03611 extends SimpleOnPageChangeListener {
        final /* synthetic */ SelectRecipientActivity f3873a;

        C03611(SelectRecipientActivity selectRecipientActivity) {
            this.f3873a = selectRecipientActivity;
        }

        public void m6413a(int i) {
            if (this.f3873a.f3884q.getNavigationMode() != 2) {
                this.f3873a.f3884q.setNavigationMode(2);
            }
            this.f3873a.f3890w = i;
            this.f3873a.m6419b(this.f3873a.f3890w);
            this.f3873a.f3884q.setSelectedNavigationItem(i);
        }
    }

    class ActionBarTabsListeners implements TabListener {
        final /* synthetic */ SelectRecipientActivity f3874a;

        ActionBarTabsListeners(SelectRecipientActivity selectRecipientActivity) {
            this.f3874a = selectRecipientActivity;
        }

        public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
            this.f3874a.f3885r.setCurrentItem(tab.getPosition());
        }

        public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
        }
    }

    class CustomViewPagerAdapter extends FragmentPagerAdapter {
        final /* synthetic */ SelectRecipientActivity f3875a;

        CustomViewPagerAdapter(SelectRecipientActivity selectRecipientActivity, FragmentManager fragmentManager) {
            this.f3875a = selectRecipientActivity;
            super(fragmentManager);
        }

        public Fragment m6414a(int i) {
            if (i == 2) {
                return this.f3875a.f3891x;
            }
            if (i == 1) {
                return this.f3875a.f3892y;
            }
            if (i == 0) {
                return this.f3875a.f3893z;
            }
            return this.f3875a.f3891x;
        }

        public int getCount() {
            return 3;
        }
    }

    public enum ForwardHistoryType {
        chat,
        group,
        broadcast,
        contact
    }

    public SelectRecipientActivity() {
        this.f3890w = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3881A = false;
        if (getActionBar() != null) {
            getActionBar().setTitle(getString(2131296679));
        }
        setContentView(2130903090);
        this.f3885r = (CustomViewPager) findViewById(2131755174);
        this.f3883p = getIntent().getExtras();
        if (this.f3883p != null && this.f3883p.containsKey("extra_is_forward_mode")) {
            this.f3883p.putInt("multiple_forward_message_type", getIntent().getIntExtra("multiple_forward_message_type", -1));
            this.f3881A = true;
        }
        m6425k();
        if (this.f3881A) {
            m6424j();
            return;
        }
        this.f3885r.setVisibility(8);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        this.f3882o = new SelectContactFragment();
        this.f3882o.setArguments(this.f3883p);
        beginTransaction.add(2131755244, this.f3882o);
        beginTransaction.commit();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6419b(this.f3890w);
    }

    protected void onPause() {
        super.onPause();
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void m6424j() {
        this.f3884q = getActionBar();
        if (this.f3884q != null) {
            this.f3884q.setDisplayHomeAsUpEnabled(false);
            this.f3884q.setNavigationMode(2);
            this.f3884q.setDisplayShowHomeEnabled(false);
            this.f3884q.setDisplayShowTitleEnabled(false);
            this.f3891x = new SelectContactFragment();
            this.f3891x.setArguments(this.f3883p);
            this.f3892y = new SelectGroupFragment();
            this.f3892y.setArguments(this.f3883p);
            this.f3893z = new SelectConversationFragment();
            this.f3893z.setArguments(this.f3883p);
            this.f3886s = new CustomViewPagerAdapter(this, getFragmentManager());
            this.f3885r.setAdapter(this.f3886s);
            this.f3887t = this.f3884q.newTab();
            this.f3888u = this.f3884q.newTab();
            this.f3889v = this.f3884q.newTab();
            TabListener actionBarTabsListeners = new ActionBarTabsListeners(this);
            this.f3887t.setTabListener(actionBarTabsListeners);
            this.f3888u.setTabListener(actionBarTabsListeners);
            this.f3889v.setTabListener(actionBarTabsListeners);
            m6419b(this.f3890w);
            this.f3884q.addTab(this.f3889v);
            this.f3884q.addTab(this.f3888u);
            this.f3884q.addTab(this.f3887t);
            this.f3885r.setOnPageChangeListener(new C03611(this));
            this.f3884q.setSelectedNavigationItem(0);
        }
    }

    private void m6419b(int i) {
        if (this.f3887t != null || this.f3888u != null || this.f3889v != null) {
            if (getResources().getConfiguration().orientation == 2) {
                m6423d(i);
            } else {
                m6422c(i);
            }
        }
    }

    private void m6422c(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f3889v.setIcon(2130837816);
                this.f3888u.setIcon(2130837823);
                this.f3887t.setIcon(2130837819);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f3889v.setIcon(2130837815);
                this.f3888u.setIcon(2130837824);
                this.f3887t.setIcon(2130837819);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f3889v.setIcon(2130837815);
                this.f3888u.setIcon(2130837823);
                this.f3887t.setIcon(2130837820);
            default:
                throw new IllegalArgumentException("Selected tab number must be between 0 ~ 2");
        }
    }

    private void m6423d(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f3889v.setIcon(2130837814);
                this.f3888u.setIcon(2130837821);
                this.f3887t.setIcon(2130837817);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f3889v.setIcon(2130837813);
                this.f3888u.setIcon(2130837822);
                this.f3887t.setIcon(2130837817);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f3889v.setIcon(2130837813);
                this.f3888u.setIcon(2130837821);
                this.f3887t.setIcon(2130837818);
            default:
                throw new IllegalArgumentException("Selected tab number must be between 0 ~ 2");
        }
    }

    private void m6425k() {
        try {
            Intent intent = getIntent();
            String action = intent.getAction();
            String type = intent.getType();
            if (!"android.intent.action.SEND".equals(action) || type == null) {
                if (!"android.intent.action.SEND_MULTIPLE".equals(action) || type == null || !type.startsWith("image/")) {
                }
            } else if ("text/plain".equals(type)) {
                String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
                Main.f1926a.m5681c("forward text is " + stringExtra);
                if (stringExtra != null) {
                    this.f3883p.putString("share_text_extra", stringExtra);
                    this.f3881A = true;
                }
            } else if (type.startsWith("image/")) {
                m6420b(intent);
                this.f3881A = true;
            } else if (type.startsWith("video/")) {
                m6416a(intent);
                this.f3881A = true;
            }
        } catch (Exception e) {
            Main.f1926a.m5679b("problem on getting data from intent for explicit intent");
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4 || keyEvent.getAction() != 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        finish();
        return true;
    }

    private void m6416a(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri != null) {
            this.f3883p.putString("share_video_extra", uri.toString());
        }
    }

    private void m6420b(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri != null) {
            this.f3883p.putString("share_image_extra", uri.toString());
        }
    }
}
