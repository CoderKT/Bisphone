package app.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import app.Main;
import app.common.collection.ObserverHashMap.HashMapListener;
import app.common.entity.ChatHistoryEntity;
import app.events.ConnectionListenerEvent;
import app.events.ConnectionListenerEvent.ConnectionType;
import app.events.HomeTabsBadgeUpdateEvent;
import app.messaging.selector.SelectRecipientActivity;
import app.notification.NotificationCenter;
import app.profile.ContactKeyPadActivity;
import app.profile.MoreInfoActivity;
import app.util.Background;
import app.xmpp.NormalMessageManager;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;

public class HistoryFragment extends Fragment implements OnClickListener {
    boolean f3022a;
    boolean f3023b;
    boolean f3024c;
    TextView f3025d;
    TextView f3026e;
    View f3027f;
    private ChatFragment f3028g;
    private CallFragment f3029h;

    /* renamed from: app.home.HistoryFragment.1 */
    class C02291 implements HashMapListener {
        final /* synthetic */ HistoryFragment f3021a;

        C02291(HistoryFragment historyFragment) {
            this.f3021a = historyFragment;
        }

        public void m5444a() {
            this.f3021a.m5451c();
        }

        public void m5445b() {
            this.f3021a.m5451c();
        }

        public void m5446c() {
            this.f3021a.m5451c();
        }

        public void m5447d() {
            this.f3021a.m5451c();
        }
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        super.onCreate(bundle);
        if (!getActivity().getIntent().getBooleanExtra("extra_fragment", false)) {
            z = true;
        }
        this.f3024c = z;
        Main.f1926a.m5683d(this.f3024c + "");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903137, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.m7744a(this, view);
        this.f3028g = new ChatFragment();
        this.f3029h = new CallFragment();
        this.f3025d.setOnClickListener(this);
        this.f3026e.setOnClickListener(this);
        m5450b();
        m5452a();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.m12779a().m12791a((Object) this);
    }

    public void onDetach() {
        super.onDetach();
        EventBus.m12779a().m12794c(this);
    }

    public void onResume() {
        super.onResume();
        m5451c();
    }

    public void onClick(View view) {
        if (!this.f3022a && !this.f3023b) {
            switch (view.getId()) {
                case 2131755449:
                    if (!this.f3024c) {
                        this.f3024c = true;
                        m5452a();
                        break;
                    }
                    return;
                case 2131755450:
                    if (this.f3024c) {
                        this.f3024c = false;
                        m5452a();
                        break;
                    }
                    return;
            }
            getActivity().invalidateOptionsMenu();
        }
    }

    private void m5450b() {
        NormalMessageManager.m7447a().m7449a(new C02291(this));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Main.f1926a.m5683d("onCreate option");
        if (this.f3024c) {
            menuInflater.inflate(2131820555, menu);
            this.f3028g.m5334a(menu);
            return;
        }
        menuInflater.inflate(2131820554, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        this.f3028g.m5336c();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755640:
                startActivity(new Intent(getActivity(), ContactKeyPadActivity.class));
                break;
            case 2131755641:
                startActivity(new Intent(getActivity(), MoreInfoActivity.class));
                break;
            case 2131755642:
                startActivity(new Intent(getActivity(), SelectRecipientActivity.class));
                break;
        }
        return true;
    }

    private void m5448a(View view) {
        Background.m6968a(getResources().getDrawable(2130837583), this.f3026e);
        Background.m6968a(getResources().getDrawable(2130837610), this.f3025d);
        if (view == this.f3026e) {
            Background.m6968a(getResources().getDrawable(2130837584), this.f3026e);
            this.f3026e.setTextColor(getResources().getColor(2131689566));
            this.f3025d.setTextColor(getResources().getColor(2131689564));
            return;
        }
        Background.m6968a(getResources().getDrawable(2130837611), this.f3025d);
        this.f3025d.setTextColor(getResources().getColor(2131689566));
        this.f3026e.setTextColor(getResources().getColor(2131689564));
    }

    private void m5451c() {
        Iterator it = new ArrayList(NormalMessageManager.m7447a().m7464c().values()).iterator();
        int i = 0;
        while (it.hasNext()) {
            ChatHistoryEntity chatHistoryEntity = (ChatHistoryEntity) it.next();
            if (chatHistoryEntity == null) {
                it.remove();
            } else {
                i = chatHistoryEntity.m4172f() + i;
            }
        }
        Main.f1926a.m5683d("unread is " + i);
        EventBus.m12779a().m12795d(new HomeTabsBadgeUpdateEvent(0, i));
    }

    protected void m5452a() {
        if (isAdded()) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            if (this.f3024c) {
                m5448a(this.f3025d);
                beginTransaction.replace(2131755384, this.f3028g, "chat");
            } else {
                m5448a(this.f3026e);
                beginTransaction.replace(2131755384, this.f3029h, "call");
                NotificationCenter.m6606a().m6621f();
            }
            beginTransaction.commit();
        }
    }

    public void m5453a(boolean z) {
        this.f3022a = z;
    }

    public void m5454b(boolean z) {
        this.f3023b = z;
    }

    public void onEvent(ConnectionListenerEvent connectionListenerEvent) {
        if (connectionListenerEvent.m4846a() == ConnectionType.authenticated) {
            if (this.f3028g != null) {
                this.f3028g.m5333a();
            }
            if (this.f3029h != null) {
                this.f3029h.m5243a();
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
    }
}
