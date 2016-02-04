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
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.Main;
import app.common.collection.ObserverArrayList.ArrayListListener;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ContactEntityLite.Builder;
import app.database.datasource.CallsDataSource;
import app.events.vcard.VCardInfoLoadEvent;
import app.home.CallRecycleAdapter.CallAdapterListener;
import app.profile.CallLogActivity;
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

public class CallFragment extends Fragment {
    CustomRecycleView f2776a;
    ProgressBar f2777b;
    View f2778c;
    View f2779d;
    private OnFragmentInteractionListener f2780e;
    private List<CallLogHistoryEntity> f2781f;
    private CallRecycleAdapter f2782g;
    private Handler f2783h;
    private View f2784i;
    private LinearLayoutManager f2785j;
    private ActionMode f2786k;
    private boolean f2787l;
    private int f2788m;
    private Callback f2789n;

    /* renamed from: app.home.CallFragment.1 */
    class C01851 implements ArrayListListener {
        final /* synthetic */ CallFragment f2765a;

        C01851(CallFragment callFragment) {
            this.f2765a = callFragment;
        }

        public void m5206a() {
            this.f2765a.m5239f();
            if (this.f2765a.f2787l) {
                this.f2765a.m5243a();
            }
        }

        public void m5207a(int i) {
            this.f2765a.m5239f();
            if (this.f2765a.f2787l) {
                this.f2765a.m5243a();
            }
        }

        public void m5209b() {
            this.f2765a.m5239f();
        }

        public void m5210b(int i) {
            this.f2765a.m5239f();
        }

        public void m5211c() {
            this.f2765a.m5239f();
        }

        public void m5212c(int i) {
            this.f2765a.m5239f();
        }

        public void m5208a(int i, int i2) {
            this.f2765a.m5239f();
        }

        public void m5213d() {
            this.f2765a.m5239f();
        }

        public void m5214d(int i) {
            this.f2765a.m5239f();
        }

        public void m5215e() {
            this.f2765a.m5239f();
        }
    }

    /* renamed from: app.home.CallFragment.2 */
    class C01862 implements CallAdapterListener {
        final /* synthetic */ CallFragment f2766a;

        C01862(CallFragment callFragment) {
            this.f2766a = callFragment;
        }

        public void m5220a(int i) {
            if (this.f2766a.f2786k == null) {
                Intent intent = new Intent(this.f2766a.getActivity(), CallLogActivity.class);
                intent.putExtra("username", ((CallLogHistoryEntity) this.f2766a.f2781f.get(i)).m4120e().m4108b());
                intent.putExtra("first_timestamp", "" + ((CallLogHistoryEntity) this.f2766a.f2781f.get(i)).m4118c());
                intent.putExtra("last_timestamp", "" + ((CallLogHistoryEntity) this.f2766a.f2781f.get(i)).m4120e().m4112f());
                intent.putExtra("allow_update", i == 0);
                this.f2766a.startActivity(intent);
                return;
            }
            this.f2766a.f2782g.m5271f(i);
        }

        public void m5222b(int i) {
        }

        public void m5221a(boolean z) {
            if (z) {
                this.f2766a.f2786k = this.f2766a.getActivity().startActionMode(this.f2766a.f2789n);
                this.f2766a.m5244b();
            } else if (this.f2766a.f2786k != null) {
                this.f2766a.f2786k.finish();
            }
        }

        public void m5223c(int i) {
            if (this.f2766a.f2786k != null) {
                this.f2766a.f2786k.setTitle(i + (" " + this.f2766a.getString(2131296403)));
            }
        }
    }

    /* renamed from: app.home.CallFragment.3 */
    class C01873 implements Runnable {
        final /* synthetic */ CallFragment f2767a;

        C01873(CallFragment callFragment) {
            this.f2767a = callFragment;
        }

        public void run() {
            this.f2767a.m5240g();
            this.f2767a.f2782g.m5265a(this.f2767a.f2781f);
            if (this.f2767a.f2781f == null || this.f2767a.f2781f.isEmpty()) {
                this.f2767a.f2776a.setEmptyView(this.f2767a.f2779d);
                this.f2767a.f2776a.setEmptyViewVisibility(0);
                return;
            }
            this.f2767a.f2776a.setEmptyViewVisibility(8);
            this.f2767a.f2782g.m3345c();
        }
    }

    /* renamed from: app.home.CallFragment.4 */
    class C01884 implements Callback {
        final /* synthetic */ CallFragment f2768a;

        C01884(CallFragment callFragment) {
            this.f2768a = callFragment;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(2131820570, menu);
            ((HomeActivity) this.f2768a.getActivity()).m5539j().setPagingEnabled(false);
            this.f2768a.f2780e.a_(true);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case 2131755655:
                    if (this.f2768a.f2782g.m5268d() == 0) {
                        this.f2768a.f2782g.m5266a(false);
                        return false;
                    }
                    new CallRemover(this.f2768a).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    return true;
                default:
                    return false;
            }
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            if (this.f2768a.isAdded()) {
                this.f2768a.f2786k = null;
                this.f2768a.f2782g.m5269e();
                ((HomeActivity) this.f2768a.getActivity()).m5539j().setPagingEnabled(true);
                this.f2768a.f2780e.a_(false);
            }
        }
    }

    class CallRemover extends AsyncTask<Void, Void, String> {
        final /* synthetic */ CallFragment f2769a;
        private List<Integer> f2770b;
        private ProgressDialog f2771c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5224a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5225a((String) obj);
        }

        public CallRemover(CallFragment callFragment) {
            this.f2769a = callFragment;
            this.f2770b = callFragment.f2782g.m5270f();
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.f2769a.getActivity() != null && this.f2769a.isAdded()) {
                this.f2771c = new ProgressDialog(this.f2769a.getActivity());
                this.f2771c.setTitle(this.f2769a.getString(2131296644));
                this.f2771c.setMessage(this.f2769a.getString(2131296525));
                this.f2771c.setCancelable(false);
                this.f2771c.show();
            }
        }

        protected String m5224a(Void... voidArr) {
            Collections.sort(this.f2770b);
            List arrayList = new ArrayList();
            for (int size = this.f2770b.size() - 1; size >= 0; size--) {
                CallLogHistoryEntity callLogHistoryEntity = (CallLogHistoryEntity) this.f2769a.f2781f.get(((Integer) this.f2770b.get(size)).intValue());
                arrayList.add(new DeleteCallModel(this.f2769a, callLogHistoryEntity.m4120e().m4108b(), "" + callLogHistoryEntity.m4118c(), "" + callLogHistoryEntity.m4120e().m4112f()));
                NormalMessageManager.m7447a().m7458b(callLogHistoryEntity);
            }
            CallsDataSource.m4527a().m4533a(arrayList);
            String str = " ";
            if (this.f2770b.size() == 1) {
                return str + this.f2769a.getString(2131296825);
            }
            return str + this.f2769a.getString(2131296823);
        }

        protected void m5225a(String str) {
            super.onPostExecute(str);
            if (this.f2771c != null) {
                this.f2771c.dismiss();
            }
            Main.m3905a(this.f2770b.size() + str);
            this.f2769a.f2782g.m5269e();
        }
    }

    public class DeleteCallModel {
        final /* synthetic */ CallFragment f2772a;
        private String f2773b;
        private String f2774c;
        private String f2775d;

        public DeleteCallModel(CallFragment callFragment, String str, String str2, String str3) {
            this.f2772a = callFragment;
            this.f2773b = str;
            this.f2774c = str2;
            this.f2775d = str3;
        }

        public String m5226a() {
            return this.f2773b;
        }

        public String m5227b() {
            return this.f2774c;
        }

        public String m5228c() {
            return this.f2775d;
        }
    }

    public interface OnFragmentInteractionListener {
        void a_(boolean z);
    }

    public CallFragment() {
        this.f2786k = null;
        this.f2787l = false;
        this.f2788m = 0;
        this.f2789n = new C01884(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2784i = layoutInflater.inflate(2130903131, viewGroup, false);
        ButterKnife.m7744a(this, this.f2784i);
        this.f2783h = new Handler();
        this.f2778c.setVisibility(8);
        this.f2779d.setVisibility(8);
        return this.f2784i;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        m5235d();
        m5233c();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        NormalMessageManager.m7447a().m7462b(true);
        this.f2776a.setAdapter(this.f2782g);
        m5239f();
        this.f2776a.m3599a(this.f2788m);
    }

    public void onPause() {
        super.onPause();
        this.f2788m = this.f2785j.m3239i();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.m12779a().m12791a((Object) this);
        try {
            this.f2780e = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        EventBus.m12779a().m12794c(this);
        this.f2780e = null;
    }

    private void m5233c() {
        NormalMessageManager.m7447a().m7448a(new C01851(this));
        this.f2782g.m5263a(new C01862(this));
    }

    private void m5235d() {
        this.f2785j = new LinearLayoutManager(getActivity());
        this.f2776a.setLayoutManager(this.f2785j);
        this.f2776a.setEmptyView(this.f2777b);
        this.f2776a.setEmptyViewVisibility(0);
        this.f2776a.m3603a(new DividerItemDecoration(getActivity(), 1, 2131689523));
        m5237e();
    }

    private void m5237e() {
        if (this.f2782g == null) {
            this.f2782g = new CallRecycleAdapter(getActivity());
            this.f2776a.setAdapter(this.f2782g);
        }
    }

    private void m5239f() {
        if (isAdded()) {
            this.f2783h.post(new C01873(this));
        }
    }

    private void m5240g() {
        if (getActivity() != null) {
            this.f2781f = NormalMessageManager.m7447a().m7471f();
        }
    }

    public void onEvent(VCardInfoLoadEvent vCardInfoLoadEvent) {
        HashMap a = vCardInfoLoadEvent.m4955a();
        Iterator it = NormalMessageManager.m7447a().m7471f().iterator();
        while (it.hasNext()) {
            CallLogHistoryEntity callLogHistoryEntity = (CallLogHistoryEntity) it.next();
            String b = callLogHistoryEntity.m4120e().m4108b();
            if (a.containsKey(b)) {
                if (callLogHistoryEntity.m4117b() == null) {
                    Builder builder = new Builder();
                    builder.m4223a(TYPE.REMOTE).m4230e(((VcardInfoEntity) a.get(b)).m7530a()).m4231f(((VcardInfoEntity) a.get(b)).m7531b());
                    callLogHistoryEntity.m4116a(builder.m4226a());
                } else {
                    callLogHistoryEntity.m4117b().m4233a(((VcardInfoEntity) a.get(b)).m7530a());
                    callLogHistoryEntity.m4117b().m4235b(((VcardInfoEntity) a.get(b)).m7531b());
                }
            }
        }
        m5239f();
    }

    public void m5243a() {
        HashMap hashMap = new HashMap();
        Iterator it = NormalMessageManager.m7447a().m7471f().iterator();
        while (it.hasNext()) {
            CallLogHistoryEntity callLogHistoryEntity = (CallLogHistoryEntity) it.next();
            if (callLogHistoryEntity.m4117b() == null || callLogHistoryEntity.m4117b().m4232a() == TYPE.REMOTE || callLogHistoryEntity.m4117b().m4240g() == null) {
                String e = new JabberId(callLogHistoryEntity.m4120e().m4108b(), "bisphone.com", null).m7391e();
                hashMap.put(e, new VCardInsertUpdateEntity(e, true));
            }
        }
        if (hashMap.size() > 0) {
            VCardHandler.m7499a().m7502a(hashMap);
        }
        this.f2787l = true;
    }

    public void m5244b() {
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

    public void onSaveInstanceState(Bundle bundle) {
    }
}
