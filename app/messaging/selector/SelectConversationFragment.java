package app.messaging.selector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import app.Main;
import app.common.collection.ObserverHashMap.HashMapListener;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ContactEntityLite.Builder;
import app.database.datasource.BroadcastUsersDataSource;
import app.events.vcard.VCardInfoLoadEvent;
import app.messaging.BroadcastMessagingActivity;
import app.messaging.NormalMessagingActivity;
import app.messaging.selector.SelectConversationAdapter.ChatAdapterListener;
import app.messaging.selector.SelectRecipientActivity.ForwardHistoryType;
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
import java.util.List;

public class SelectConversationFragment extends Fragment {
    CustomRecycleView f3843a;
    ProgressBar f3844b;
    View f3845c;
    View f3846d;
    private List<ChatHistoryEntity> f3847e;
    private SelectConversationAdapter f3848f;
    private Handler f3849g;
    private View f3850h;
    private LinearLayoutManager f3851i;
    private String f3852j;
    private boolean f3853k;
    private final Object f3854l;
    private int f3855m;

    /* renamed from: app.messaging.selector.SelectConversationFragment.1 */
    class C03571 implements HashMapListener {
        final /* synthetic */ SelectConversationFragment f3840a;

        C03571(SelectConversationFragment selectConversationFragment) {
            this.f3840a = selectConversationFragment;
        }

        public void m6395a() {
            this.f3840a.m6407e();
            if (this.f3840a.f3853k) {
                this.f3840a.m6411a();
            }
        }

        public void m6396b() {
            this.f3840a.m6407e();
        }

        public void m6397c() {
            this.f3840a.m6407e();
        }

        public void m6398d() {
            this.f3840a.m6407e();
        }
    }

    /* renamed from: app.messaging.selector.SelectConversationFragment.2 */
    class C03582 implements ChatAdapterListener {
        final /* synthetic */ SelectConversationFragment f3841a;

        C03582(SelectConversationFragment selectConversationFragment) {
            this.f3841a = selectConversationFragment;
        }

        public void m6399a(int i) {
            Intent intent;
            Bundle bundle = new Bundle();
            Intent intent2;
            if (((ChatHistoryEntity) this.f3841a.f3847e.get(i)).m4167b()) {
                intent = new Intent(this.f3841a.getActivity(), BroadcastMessagingActivity.class);
                ArrayList a = BroadcastUsersDataSource.m4519a().m4521a(((ChatHistoryEntity) this.f3841a.f3847e.get(i)).m4168c());
                bundle.putString("extra_broadcast_id", ((ChatHistoryEntity) this.f3841a.f3847e.get(i)).m4168c());
                bundle.putStringArrayList("forward_recipients_id", a);
                if (this.f3841a.getArguments() == null) {
                    return;
                }
                if (this.f3841a.getArguments().containsKey("share_text_extra")) {
                    bundle.putString("share_text_extra", this.f3841a.f3852j);
                } else if (this.f3841a.getArguments().containsKey("extra_is_forward_mode")) {
                    bundle.putInt("multiple_forward_message_type", ForwardHistoryType.broadcast.ordinal());
                    intent2 = this.f3841a.getActivity().getIntent();
                    intent2.putExtra("multiple_forward_message", bundle);
                    this.f3841a.getActivity().setResult(-1, intent2);
                    this.f3841a.getActivity().finish();
                    return;
                } else if (this.f3841a.getArguments().containsKey("share_image_extra")) {
                    bundle.putString("share_image_extra", this.f3841a.getArguments().getString("share_image_extra"));
                } else if (this.f3841a.getArguments().containsKey("share_video_extra")) {
                    bundle.putString("share_video_extra", this.f3841a.getArguments().getString("share_video_extra"));
                }
            } else {
                intent = new Intent(this.f3841a.getActivity(), NormalMessagingActivity.class);
                bundle.putString("forward_recipients_id", new JabberId(((ChatHistoryEntity) this.f3841a.f3847e.get(i)).m4170d().m4333d(), "bisphone.com", null).m7391e());
                bundle.putString("extra_jabber_id", new JabberId(((ChatHistoryEntity) this.f3841a.f3847e.get(i)).m4170d().m4333d(), "bisphone.com", null).m7391e());
                if (this.f3841a.getArguments() != null) {
                    if (this.f3841a.getArguments().containsKey("share_text_extra")) {
                        bundle.putString("share_text_extra", this.f3841a.f3852j);
                    } else if (this.f3841a.getArguments().containsKey("extra_is_forward_mode")) {
                        bundle.putInt("multiple_forward_message_type", ForwardHistoryType.chat.ordinal());
                        intent2 = this.f3841a.getActivity().getIntent();
                        intent2.putExtra("multiple_forward_message", bundle);
                        this.f3841a.getActivity().setResult(-1, intent2);
                        this.f3841a.getActivity().finish();
                        return;
                    } else if (this.f3841a.getArguments().containsKey("share_image_extra")) {
                        bundle.putString("share_image_extra", this.f3841a.getArguments().getString("share_image_extra"));
                    } else if (this.f3841a.getArguments().containsKey("share_video_extra")) {
                        bundle.putString("share_video_extra", this.f3841a.getArguments().getString("share_video_extra"));
                    }
                } else {
                    return;
                }
            }
            intent.putExtras(bundle);
            intent.putExtra("extra_jabber_id", new JabberId(((ChatHistoryEntity) this.f3841a.f3847e.get(i)).m4170d().m4333d(), "bisphone.com", null).m7391e());
            intent.setFlags(67108864);
            this.f3841a.startActivity(intent);
            this.f3841a.getActivity().finish();
        }
    }

    /* renamed from: app.messaging.selector.SelectConversationFragment.3 */
    class C03593 implements Runnable {
        final /* synthetic */ SelectConversationFragment f3842a;

        C03593(SelectConversationFragment selectConversationFragment) {
            this.f3842a = selectConversationFragment;
        }

        public void run() {
            this.f3842a.m6410f();
            this.f3842a.f3848f.m6393a(this.f3842a.f3847e);
            if (this.f3842a.f3847e == null || this.f3842a.f3847e.isEmpty()) {
                this.f3842a.f3843a.setEmptyView(this.f3842a.f3845c);
                this.f3842a.f3843a.setEmptyViewVisibility(0);
                return;
            }
            this.f3842a.f3843a.setEmptyViewVisibility(8);
            this.f3842a.f3848f.m3345c();
        }
    }

    public SelectConversationFragment() {
        this.f3853k = false;
        this.f3854l = new Object();
        this.f3855m = 0;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3850h = layoutInflater.inflate(2130903131, viewGroup, false);
        ButterKnife.m7744a(this, this.f3850h);
        this.f3849g = new Handler();
        this.f3845c.setVisibility(8);
        this.f3846d.setVisibility(8);
        return this.f3850h;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        try {
            if (getArguments() != null) {
                this.f3852j = getArguments().getString("share_text_extra");
            }
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
        }
        m6404c();
        m6401b();
    }

    public void onResume() {
        super.onResume();
        NormalMessageManager.m7447a().m7456a(true);
        this.f3843a.setAdapter(this.f3848f);
        m6407e();
        this.f3843a.m3599a(this.f3855m);
    }

    public void onPause() {
        super.onPause();
        this.f3855m = this.f3851i.m3239i();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.m12779a().m12791a((Object) this);
    }

    public void onDetach() {
        super.onDetach();
        EventBus.m12779a().m12794c(this);
    }

    private void m6401b() {
        NormalMessageManager.m7447a().m7449a(new C03571(this));
        this.f3848f.m6391a(new C03582(this));
    }

    private void m6404c() {
        this.f3851i = new LinearLayoutManager(getActivity());
        this.f3843a.setLayoutManager(this.f3851i);
        this.f3843a.setEmptyView(this.f3844b);
        this.f3843a.setEmptyViewVisibility(0);
        this.f3843a.m3603a(new DividerItemDecoration(getActivity(), 1, 2131689523));
        m6406d();
    }

    private void m6406d() {
        if (this.f3848f == null) {
            this.f3848f = new SelectConversationAdapter(getActivity());
            this.f3843a.setAdapter(this.f3848f);
        }
    }

    private void m6407e() {
        if (isAdded()) {
            this.f3849g.post(new C03593(this));
        }
    }

    private void m6410f() {
        if (getActivity() != null) {
            synchronized (this.f3854l) {
                this.f3847e = new ArrayList(NormalMessageManager.m7447a().m7464c().values());
                Collections.sort(this.f3847e);
            }
        }
    }

    public void m6411a() {
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
        this.f3853k = true;
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
        m6407e();
    }
}
