package app.home;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import app.Main;
import app.common.collection.ObserverHashMap.HashMapListener;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ContactEntityLite.Builder;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.BroadcastUsersDataSource;
import app.database.datasource.ConversationNormalDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.vcard.VCardInfoLoadEvent;
import app.home.ChatRecycleAdapter.ChatAdapterListener;
import app.messaging.BroadcastMessagingActivity;
import app.messaging.NormalMessagingActivity;
import app.messaging.selector.SelectRecipientActivity;
import app.view.CustomRecycleView;
import app.view.DividerItemDecoration;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageManager;
import app.xmpp.VCardHandler;
import app.xmpp.VCardInsertUpdateEntity;
import app.xmpp.packet.VcardInfoEntity;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChatFragment extends Fragment {
    CustomRecycleView f2864a;
    ProgressBar f2865b;
    View f2866c;
    View f2867d;
    LoadMessage f2868e;
    private OnFragmentInteractionListener f2869f;
    private List<ChatHistoryEntity> f2870g;
    private ChatRecycleAdapter f2871h;
    private Handler f2872i;
    private boolean f2873j;
    private View f2874k;
    private LinearLayoutManager f2875l;
    private ActionMode f2876m;
    private MenuItem f2877n;
    private SearchView f2878o;
    private boolean f2879p;
    private int f2880q;
    private Callback f2881r;

    /* renamed from: app.home.ChatFragment.1 */
    class C02001 implements HashMapListener {
        final /* synthetic */ ChatFragment f2852a;

        C02001(ChatFragment chatFragment) {
            this.f2852a = chatFragment;
        }

        public void m5292a() {
            this.f2852a.m5321f();
            this.f2852a.m5327i();
            if (this.f2852a.f2879p) {
                this.f2852a.m5333a();
            }
        }

        public void m5293b() {
            this.f2852a.m5321f();
            this.f2852a.m5327i();
        }

        public void m5294c() {
            this.f2852a.m5321f();
            this.f2852a.m5327i();
        }

        public void m5295d() {
            this.f2852a.m5321f();
            this.f2852a.m5327i();
        }
    }

    /* renamed from: app.home.ChatFragment.2 */
    class C02012 implements ChatAdapterListener {
        final /* synthetic */ ChatFragment f2853a;

        C02012(ChatFragment chatFragment) {
            this.f2853a = chatFragment;
        }

        public void m5300a(int i) {
            if (this.f2853a.f2876m != null) {
                this.f2853a.f2871h.m5364f(i);
            } else if (i < this.f2853a.f2870g.size()) {
                Intent intent;
                if (((ChatHistoryEntity) this.f2853a.f2870g.get(i)).m4167b()) {
                    intent = new Intent(this.f2853a.getActivity(), BroadcastMessagingActivity.class);
                    intent.putExtra("extra_broadcast_id", ((ChatHistoryEntity) this.f2853a.f2870g.get(i)).m4168c());
                } else {
                    intent = new Intent(this.f2853a.getActivity(), NormalMessagingActivity.class);
                    intent.putExtra("extra_jabber_id", new JabberId(((ChatHistoryEntity) this.f2853a.f2870g.get(i)).m4170d().m4333d(), "bisphone.com", null).m7391e());
                }
                if (!(this.f2853a.f2871h.m5367h() == null || this.f2853a.f2871h.m5367h().isEmpty())) {
                    intent.putExtra("smt", Long.parseLong(((ChatHistoryEntity) this.f2853a.f2870g.get(i)).m4170d().m4336f()));
                    intent.putExtra("smi", ((ChatHistoryEntity) this.f2853a.f2870g.get(i)).m4170d().m4323a());
                    this.f2853a.f2873j = true;
                    ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(((ChatHistoryEntity) this.f2853a.f2870g.get(i)).m4170d().m4333d());
                    f.m4170d().m4329b(0);
                    f.m4170d().m4324a(0);
                }
                this.f2853a.startActivity(intent);
            }
        }

        public void m5302b(int i) {
        }

        public void m5301a(boolean z) {
            if (z) {
                this.f2853a.f2876m = this.f2853a.getActivity().startActionMode(this.f2853a.f2881r);
                this.f2853a.m5335b();
            } else if (this.f2853a.f2876m != null) {
                this.f2853a.f2876m.finish();
            }
        }

        public void m5303c(int i) {
            if (this.f2853a.f2876m != null) {
                this.f2853a.f2876m.setTitle(i + (" " + this.f2853a.getString(2131296403)));
            }
        }
    }

    /* renamed from: app.home.ChatFragment.3 */
    class C02023 implements Runnable {
        final /* synthetic */ ChatFragment f2854a;

        C02023(ChatFragment chatFragment) {
            this.f2854a = chatFragment;
        }

        public void run() {
            this.f2854a.m5331k();
            this.f2854a.f2868e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{(String) null});
        }
    }

    /* renamed from: app.home.ChatFragment.4 */
    class C02034 implements Runnable {
        final /* synthetic */ ChatFragment f2855a;

        C02034(ChatFragment chatFragment) {
            this.f2855a = chatFragment;
        }

        public void run() {
            if (this.f2855a.f2870g == null || this.f2855a.f2870g.isEmpty()) {
                this.f2855a.f2864a.setEmptyView(this.f2855a.f2866c);
                this.f2855a.f2864a.setEmptyViewVisibility(0);
                return;
            }
            this.f2855a.f2864a.setEmptyViewVisibility(8);
            this.f2855a.f2871h.m3345c();
        }
    }

    /* renamed from: app.home.ChatFragment.5 */
    class C02045 implements Callback {
        final /* synthetic */ ChatFragment f2856a;

        C02045(ChatFragment chatFragment) {
            this.f2856a = chatFragment;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(2131820570, menu);
            ((HomeActivity) this.f2856a.getActivity()).m5539j().setPagingEnabled(false);
            this.f2856a.f2869f.a_(true);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case 2131755655:
                    if (this.f2856a.f2871h.m5361d() == 0) {
                        this.f2856a.f2871h.m5359a(false);
                        return false;
                    }
                    new ChatRemover(this.f2856a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    return true;
                default:
                    return false;
            }
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            if (this.f2856a.isAdded()) {
                this.f2856a.f2876m = null;
                this.f2856a.f2871h.m5362e();
                ((HomeActivity) this.f2856a.getActivity()).m5539j().setPagingEnabled(true);
                this.f2856a.f2869f.a_(false);
            }
        }
    }

    /* renamed from: app.home.ChatFragment.6 */
    class C02056 implements OnActionExpandListener {
        final /* synthetic */ Menu f2857a;
        final /* synthetic */ ChatFragment f2858b;

        C02056(ChatFragment chatFragment, Menu menu) {
            this.f2858b = chatFragment;
            this.f2857a = menu;
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            if (this.f2858b.getActivity().getActionBar() != null) {
                this.f2858b.getActivity().getActionBar().setNavigationMode(0);
            }
            this.f2858b.f2869f.m5308b(true);
            this.f2857a.findItem(2131755641).setVisible(false);
            this.f2857a.findItem(2131755642).setVisible(false);
            ((HomeActivity) this.f2858b.getActivity()).m5539j().setPagingEnabled(false);
            return true;
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            if (this.f2858b.getActivity().getActionBar() != null) {
                this.f2858b.getActivity().getActionBar().setNavigationMode(2);
            }
            if (!(this.f2857a.findItem(2131755642) == null || this.f2857a.findItem(2131755641) == null)) {
                this.f2857a.findItem(2131755642).setVisible(true);
                this.f2858b.f2871h.m5363f();
                this.f2857a.findItem(2131755641).setVisible(true);
                this.f2858b.f2869f.m5308b(false);
                ((HomeActivity) this.f2858b.getActivity()).m5539j().setPagingEnabled(true);
            }
            return true;
        }
    }

    /* renamed from: app.home.ChatFragment.7 */
    class C02067 implements OnQueryTextListener {
        final /* synthetic */ ChatFragment f2859a;

        C02067(ChatFragment chatFragment) {
            this.f2859a = chatFragment;
        }

        public boolean onQueryTextSubmit(String str) {
            return true;
        }

        public boolean onQueryTextChange(String str) {
            if (this.f2859a.f2873j) {
                this.f2859a.f2873j = false;
                TextView textView = (TextView) this.f2859a.f2878o.findViewById(this.f2859a.f2878o.getContext().getResources().getIdentifier("android:id/search_src_text", null, null));
                if (textView != null) {
                    textView.setText(this.f2859a.f2871h.m5367h());
                    return true;
                }
            }
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            this.f2859a.m5331k();
            this.f2859a.f2868e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
            return true;
        }
    }

    class ChatRemover extends AsyncTask<Void, Void, String> {
        final /* synthetic */ ChatFragment f2860a;
        private List<Integer> f2861b;
        private ProgressDialog f2862c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5304a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5305a((String) obj);
        }

        public ChatRemover(ChatFragment chatFragment) {
            this.f2860a = chatFragment;
            this.f2861b = chatFragment.f2871h.m5365g();
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f2862c = new ProgressDialog(this.f2860a.getActivity());
            this.f2862c.setTitle(this.f2860a.getString(2131296644));
            this.f2862c.setMessage(this.f2860a.getString(2131296526));
            this.f2862c.setCancelable(false);
            this.f2862c.show();
        }

        protected String m5304a(Void... voidArr) {
            Collections.sort(this.f2861b);
            List arrayList = new ArrayList();
            for (Integer num : this.f2861b) {
                if (num.intValue() >= this.f2860a.f2870g.size()) {
                    break;
                }
                arrayList.add(((ChatHistoryEntity) this.f2860a.f2870g.get(num.intValue())).m4176j());
            }
            HistoryNormalMessageDataSource.m4720a().m4742a((ArrayList) arrayList);
            ConversationNormalDataSource.m4623a().m4633a((ArrayList) arrayList);
            BroadcastListDataSource.m4504a().m4511a(arrayList);
            BroadcastUsersDataSource.m4519a().m4523a(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                NormalMessageManager.m7447a().m7452a((String) it.next());
            }
            String str = " ";
            if (this.f2861b.size() == 1) {
                return str + this.f2860a.getString(2131296826);
            }
            return str + this.f2860a.getString(2131296824);
        }

        protected void m5305a(String str) {
            super.onPostExecute(str);
            this.f2862c.dismiss();
            Main.m3905a(this.f2861b.size() + str);
            this.f2860a.f2871h.m5362e();
        }
    }

    class LoadMessage extends AsyncTask<String, Void, List<ChatHistoryEntity>> {
        final /* synthetic */ ChatFragment f2863a;

        LoadMessage(ChatFragment chatFragment) {
            this.f2863a = chatFragment;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5306a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5307a((List) obj);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.f2863a.f2864a.setEmptyView(this.f2863a.f2865b);
            this.f2863a.f2864a.setEmptyViewVisibility(0);
        }

        protected List<ChatHistoryEntity> m5306a(String... strArr) {
            if ((this.f2863a.f2871h.m5367h() == null || this.f2863a.f2871h.m5367h().isEmpty()) && strArr[0] == null) {
                return this.f2863a.m5328j();
            }
            if (this.f2863a.f2871h.m5367h() != null && this.f2863a.f2871h.m5367h().equals(strArr[0])) {
                return this.f2863a.f2870g;
            }
            this.f2863a.f2871h.m5357a(strArr[0]);
            return this.f2863a.m5312a(strArr[0]);
        }

        protected void m5307a(List<ChatHistoryEntity> list) {
            super.onPostExecute(list);
            this.f2863a.f2870g = list;
            this.f2863a.f2871h.m5358a(this.f2863a.f2870g);
            this.f2863a.m5327i();
        }
    }

    public interface OnFragmentInteractionListener {
        void a_(boolean z);

        void m5308b(boolean z);
    }

    public ChatFragment() {
        this.f2876m = null;
        this.f2879p = false;
        this.f2880q = 0;
        this.f2881r = new C02045(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2874k = layoutInflater.inflate(2130903131, viewGroup, false);
        ButterKnife.m7744a(this, this.f2874k);
        this.f2872i = new Handler();
        this.f2867d.setVisibility(8);
        this.f2866c.setVisibility(8);
        return this.f2874k;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m5323g();
        m5319e();
        m5321f();
    }

    public void onResume() {
        super.onResume();
        NormalMessageManager.m7447a().m7456a(true);
        this.f2864a.setAdapter(this.f2871h);
        this.f2864a.m3599a(this.f2880q);
    }

    public void onPause() {
        super.onPause();
        this.f2880q = this.f2875l.m3239i();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.m12779a().m12791a((Object) this);
        try {
            this.f2869f = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        EventBus.m12779a().m12794c(this);
        this.f2869f = null;
    }

    private void m5319e() {
        NormalMessageManager.m7447a().m7449a(new C02001(this));
        this.f2871h.m5355a(new C02012(this));
    }

    private void m5321f() {
        if (this.f2871h.m5367h() == null || this.f2871h.m5367h().length() <= 0) {
            this.f2872i.post(new C02023(this));
        }
    }

    private void m5323g() {
        this.f2875l = new LinearLayoutManager(getActivity());
        this.f2864a.setLayoutManager(this.f2875l);
        this.f2864a.setEmptyView(this.f2865b);
        this.f2864a.setEmptyViewVisibility(0);
        this.f2864a.m3603a(new DividerItemDecoration(getActivity(), 1, 2131689523));
        m5324h();
    }

    private void m5324h() {
        if (this.f2871h == null) {
            this.f2871h = new ChatRecycleAdapter(getActivity());
            this.f2864a.setAdapter(this.f2871h);
        }
    }

    private void m5327i() {
        if (isAdded()) {
            this.f2872i.post(new C02034(this));
        }
    }

    private List<ChatHistoryEntity> m5328j() {
        if (getActivity() == null) {
            return new ArrayList();
        }
        return NormalMessageManager.m7447a().m7466d();
    }

    private List<ChatHistoryEntity> m5312a(String str) {
        if (str == null) {
            return m5328j();
        }
        return HistoryNormalMessageDataSource.m4720a().m4749f(str);
    }

    public void m5333a() {
        HashMap hashMap = new HashMap();
        for (ChatHistoryEntity chatHistoryEntity : NormalMessageManager.m7447a().m7464c().values()) {
            if (chatHistoryEntity.m4171e() == null || chatHistoryEntity.m4171e().m4232a() == TYPE.REMOTE || chatHistoryEntity.m4171e().m4240g() == null) {
                String e = new JabberId(chatHistoryEntity.m4170d().m4333d(), "bisphone.com", null).m7391e();
                hashMap.put(e, new VCardInsertUpdateEntity(e, true));
            }
        }
        if (hashMap.size() > 0) {
            VCardHandler.m7499a().m7502a(hashMap);
        }
        this.f2879p = true;
    }

    public void onEvent(VCardInfoLoadEvent vCardInfoLoadEvent) {
        HashMap a = vCardInfoLoadEvent.m4955a();
        for (ChatHistoryEntity chatHistoryEntity : NormalMessageManager.m7447a().m7464c().values()) {
            String d = chatHistoryEntity.m4170d().m4333d();
            if (a.containsKey(d)) {
                if (chatHistoryEntity.m4171e() == null) {
                    Builder builder = new Builder();
                    builder.m4223a(TYPE.REMOTE).m4230e(((VcardInfoEntity) a.get(d)).m7530a()).m4231f(((VcardInfoEntity) a.get(d)).m7531b());
                    chatHistoryEntity.m4164a(builder.m4226a());
                } else {
                    chatHistoryEntity.m4171e().m4233a(((VcardInfoEntity) a.get(d)).m7530a());
                    chatHistoryEntity.m4171e().m4235b(((VcardInfoEntity) a.get(d)).m7531b());
                }
            }
        }
        m5327i();
    }

    public void m5335b() {
        int identifier = Resources.getSystem().getIdentifier("action_mode_close_button", "id", "android");
        if (identifier != 0) {
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

    public void m5334a(Menu menu) {
        if (isAdded()) {
            this.f2877n = menu.findItem(2131755643);
            this.f2877n.setOnActionExpandListener(new C02056(this, menu));
        }
    }

    public void m5336c() {
        if (isAdded() && this.f2877n != null) {
            this.f2878o = (SearchView) this.f2877n.getActionView();
            this.f2878o.setQueryHint(getResources().getString(2131296677));
            this.f2878o.setQuery(this.f2871h.m5367h(), false);
            this.f2878o.setOnQueryTextListener(new C02067(this));
        }
    }

    private void m5331k() {
        if (this.f2868e == null) {
            this.f2868e = new LoadMessage(this);
            return;
        }
        this.f2868e.cancel(true);
        this.f2868e = new LoadMessage(this);
    }

    void m5337d() {
        startActivity(new Intent(getActivity(), SelectRecipientActivity.class));
    }

    public void onSaveInstanceState(Bundle bundle) {
    }
}
