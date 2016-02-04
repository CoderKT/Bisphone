package app.home;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import app.Main;
import app.common.collection.ObserverHashMap.HashMapListener;
import app.common.entity.ConversationGroupEntity;
import app.database.datasource.ConversationGroupDataSource;
import app.events.HomeTabsBadgeUpdateEvent;
import app.events.group.GroupMigrateFinishEvent;
import app.messaging.group.CreateGroupActivity;
import app.notification.NotificationCenter;
import app.profile.MoreInfoActivity;
import app.util.SharedPreferencesUtil;
import app.view.CustomRecycleView;
import app.view.DividerItemDecoration;
import app.xmpp.GroupManager;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GroupsFragment extends Fragment {
    CustomRecycleView f3011a;
    ImageView f3012b;
    ProgressBar f3013c;
    private View f3014d;
    private LinearLayoutManager f3015e;
    private GroupRecycleAdapter f3016f;
    private List<ConversationGroupEntity> f3017g;
    private MenuItem f3018h;
    private Handler f3019i;
    private final Object f3020j;

    /* renamed from: app.home.GroupsFragment.1 */
    class C02251 implements Runnable {
        final /* synthetic */ GroupsFragment f3006a;

        C02251(GroupsFragment groupsFragment) {
            this.f3006a = groupsFragment;
        }

        public void run() {
            if (SharedPreferencesUtil.m7056a("ggln", true)) {
                this.f3006a.f3011a.setEmptyView(this.f3006a.f3013c);
                this.f3006a.f3011a.setEmptyViewVisibility(0);
                return;
            }
            this.f3006a.m5442e();
            this.f3006a.f3011a.setEmptyView(this.f3006a.f3012b);
            this.f3006a.f3016f.m5425a(this.f3006a.f3017g);
            if (this.f3006a.f3017g == null || this.f3006a.f3017g.isEmpty()) {
                this.f3006a.f3011a.setEmptyViewVisibility(0);
                return;
            }
            this.f3006a.f3011a.setEmptyViewVisibility(8);
            this.f3006a.f3016f.m3345c();
        }
    }

    /* renamed from: app.home.GroupsFragment.2 */
    class C02262 implements HashMapListener {
        final /* synthetic */ GroupsFragment f3007a;

        C02262(GroupsFragment groupsFragment) {
            this.f3007a = groupsFragment;
        }

        public void m5428a() {
            Main.f1926a.m5681c("an entry add or changed");
            this.f3007a.m5439c();
        }

        public void m5429b() {
            Main.f1926a.m5681c("an entry removed");
            this.f3007a.m5439c();
        }

        public void m5430c() {
            Main.f1926a.m5681c("hashmap cleared");
            this.f3007a.m5439c();
        }

        public void m5431d() {
            Main.f1926a.m5681c("an item hash been changed");
            this.f3007a.m5439c();
        }
    }

    /* renamed from: app.home.GroupsFragment.3 */
    class C02273 implements OnClickListener {
        final /* synthetic */ GroupsFragment f3008a;

        C02273(GroupsFragment groupsFragment) {
            this.f3008a = groupsFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.home.GroupsFragment.4 */
    class C02284 implements OnClickListener {
        final /* synthetic */ GroupsFragment f3009a;

        C02284(GroupsFragment groupsFragment) {
            this.f3009a = groupsFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            new MarkAsReadThread(this.f3009a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            NotificationCenter.m6606a().m6631l();
        }
    }

    class MarkAsReadThread extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ GroupsFragment f3010a;

        MarkAsReadThread(GroupsFragment groupsFragment) {
            this.f3010a = groupsFragment;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5432a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5433a((Void) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void m5432a(Void... voidArr) {
            ConversationGroupDataSource.m4587a().m4616d();
            GroupManager.m7323a().m7373g();
            return null;
        }

        protected void m5433a(Void voidR) {
            super.onPostExecute(voidR);
            EventBus.m12779a().m12795d(new HomeTabsBadgeUpdateEvent(1, 0));
        }
    }

    public GroupsFragment() {
        this.f3020j = new Object();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3014d = layoutInflater.inflate(2130903140, viewGroup, false);
        ButterKnife.m7744a(this, this.f3014d);
        EventBus.m12779a().m12791a((Object) this);
        this.f3019i = new Handler();
        return this.f3014d;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        m5434a();
        m5440d();
    }

    public void onResume() {
        super.onResume();
        GroupManager.m7323a().m7356a(true);
        m5439c();
    }

    public void onDestroyView() {
        super.onDestroyView();
        EventBus.m12779a().m12794c(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820553, menu);
        this.f3018h = menu.findItem(2131755628);
        if (this.f3017g == null || this.f3017g.isEmpty()) {
            this.f3018h.setVisible(false);
        } else {
            this.f3018h.setVisible(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755628:
                m5443f();
                break;
            case 2131755629:
                startActivity(new Intent(Main.f1927b, MoreInfoActivity.class));
                break;
            case 2131755639:
                if (ConversationGroupDataSource.m4587a().m4592a(getActivity()).size() >= SharedPreferencesUtil.m7057b("mgpu", 50) && this.f3016f != null) {
                    this.f3016f.m5426a(true);
                    break;
                }
                getActivity().startActivity(new Intent(getActivity(), CreateGroupActivity.class));
                break;
                break;
        }
        return true;
    }

    private void m5434a() {
        this.f3015e = new LinearLayoutManager(getActivity());
        this.f3011a.setLayoutManager(this.f3015e);
        this.f3011a.setEmptyView(this.f3012b);
        this.f3011a.m3603a(new DividerItemDecoration(getActivity(), 1, 2131689523));
        m5437b();
    }

    private void m5437b() {
        if (this.f3016f == null) {
            this.f3016f = new GroupRecycleAdapter(getActivity());
            this.f3011a.setAdapter(this.f3016f);
        }
    }

    private void m5439c() {
        if (isAdded()) {
            this.f3019i.post(new C02251(this));
        }
    }

    private void m5440d() {
        GroupManager.m7323a().m7342a(new C02262(this));
    }

    private void m5442e() {
        if (getActivity() != null) {
            synchronized (this.f3020j) {
                this.f3017g = GroupManager.m7323a().m7375i();
                getActivity().invalidateOptionsMenu();
                Iterator it = this.f3017g.iterator();
                int i = 0;
                while (it.hasNext()) {
                    ConversationGroupEntity conversationGroupEntity = (ConversationGroupEntity) it.next();
                    if (conversationGroupEntity == null) {
                        it.remove();
                    } else {
                        String e = conversationGroupEntity.m4283e();
                        if (e.equals("CREATE_FAILED") || e.equals("CREATE_DRAFT") || e.equals("REJECTING") || e.equals("LEFT_DELETE")) {
                            it.remove();
                        } else if (e.equals("INVITED")) {
                            i++;
                        } else {
                            i = conversationGroupEntity.m4277c() + i;
                        }
                    }
                }
                Main.f1926a.m5681c("unread count is: " + i);
                EventBus.m12779a().m12795d(new HomeTabsBadgeUpdateEvent(1, i));
                Collections.sort(this.f3017g, Collections.reverseOrder());
            }
        }
    }

    public void onEventMainThread(GroupMigrateFinishEvent groupMigrateFinishEvent) {
        m5439c();
    }

    private void m5443f() {
        new Builder(getActivity(), 2131558537).m1980a(getString(2131296558)).m1986b(getString(2131296559)).m1975a(17039379, new C02284(this)).m1985b(17039369, new C02273(this)).m1989c(2130837730).m1992c();
    }
}
