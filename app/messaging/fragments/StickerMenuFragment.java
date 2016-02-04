package app.messaging.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.common.entity.StickerPackEntity;
import app.database.datasource.StickerDataSource;
import app.events.sticker.StickerPackDownloadedEvent;
import app.events.sticker.StickerPackSelectedEvent;
import app.messaging.stickers.OnlineStickersActivity;
import app.messaging.stickers.StickerManager;
import app.messaging.stickers.StickerPacksContainer;
import de.greenrobot.event.EventBus;

public class StickerMenuFragment extends Fragment {
    private static StickerMenuFragment f3585a;
    private HorizontalScrollView aj;
    private FragmentTransaction ak;
    private SparseArray<StickerPackEntity> al;
    private int am;
    private boolean an;
    private RecycleFragmentsListener f3586b;
    private View f3587c;
    private RelativeLayout f3588d;
    private FrameLayout f3589e;
    private ImageButton f3590f;
    private ImageButton f3591g;
    private ImageButton f3592h;
    private TextView f3593i;

    /* renamed from: app.messaging.fragments.StickerMenuFragment.1 */
    class C03141 implements Runnable {
        final /* synthetic */ StickerMenuFragment f3581a;

        C03141(StickerMenuFragment stickerMenuFragment) {
            this.f3581a = stickerMenuFragment;
        }

        public void run() {
            this.f3581a.aj.removeAllViewsInLayout();
            this.f3581a.al = StickerManager.m6491b().m6499d();
            this.f3581a.aj.addView(new StickerPacksContainer(this.f3581a.m227i()));
        }
    }

    /* renamed from: app.messaging.fragments.StickerMenuFragment.2 */
    class C03152 implements OnClickListener {
        final /* synthetic */ StickerMenuFragment f3582a;

        C03152(StickerMenuFragment stickerMenuFragment) {
            this.f3582a = stickerMenuFragment;
        }

        public void onClick(View view) {
            StickerManager.m6491b().m6493a(-1);
            this.f3582a.m6200b(0);
        }
    }

    /* renamed from: app.messaging.fragments.StickerMenuFragment.3 */
    class C03163 implements OnClickListener {
        final /* synthetic */ StickerMenuFragment f3583a;

        C03163(StickerMenuFragment stickerMenuFragment) {
            this.f3583a = stickerMenuFragment;
        }

        public void onClick(View view) {
            if (StickerManager.m6491b().m6492a() != -1) {
                this.f3583a.m192a(new Intent(this.f3583a.m227i(), OnlineStickersActivity.class));
            }
        }
    }

    /* renamed from: app.messaging.fragments.StickerMenuFragment.4 */
    class C03174 implements OnClickListener {
        final /* synthetic */ StickerMenuFragment f3584a;

        C03174(StickerMenuFragment stickerMenuFragment) {
            this.f3584a = stickerMenuFragment;
        }

        public void onClick(View view) {
            this.f3584a.f3586b.m5706K();
        }
    }

    public static synchronized StickerMenuFragment m6194a() {
        StickerMenuFragment stickerMenuFragment;
        synchronized (StickerMenuFragment.class) {
            if (f3585a == null) {
                f3585a = new StickerMenuFragment();
            }
            stickerMenuFragment = f3585a;
        }
        return stickerMenuFragment;
    }

    public static void m6187M() {
        if (f3585a != null) {
            f3585a = null;
        }
    }

    public StickerMenuFragment() {
        this.am = 0;
        this.an = true;
        this.al = StickerManager.m6491b().m6499d();
    }

    public void m6198a(Bundle bundle) {
        super.m195a(bundle);
    }

    public void m6203q() {
        super.m238q();
        EventBus.m12779a().m12791a((Object) this);
        m6188N();
        this.an = true;
    }

    public void m6204r() {
        EventBus.m12779a().m12794c(this);
        super.m239r();
        this.an = false;
    }

    public void m6197a(Activity activity) {
        super.m188a(activity);
        try {
            this.f3586b = (RecycleFragmentsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement recycleFragmentsListener");
        }
    }

    public void m6199b() {
        super.m204b();
        this.f3586b = null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6200b(this.am);
    }

    public View m6196a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3587c = layoutInflater.inflate(2130903151, viewGroup, false);
        this.aj = (HorizontalScrollView) this.f3587c.findViewById(2131755432);
        this.f3589e = (FrameLayout) this.f3587c.findViewById(2131755437);
        this.f3588d = (RelativeLayout) this.f3587c.findViewById(2131755433);
        this.f3590f = (ImageButton) this.f3587c.findViewById(2131755431);
        this.f3591g = (ImageButton) this.f3587c.findViewById(2131755434);
        this.f3592h = (ImageButton) this.f3587c.findViewById(2131755436);
        this.f3593i = (TextView) this.f3587c.findViewById(2131755435);
        this.an = true;
        m6191Q();
        m6200b(this.am);
        m6189O();
        m6190P();
        return this.f3587c;
    }

    public void m6202e(boolean z) {
        super.m219e(z);
        if (z && this.f3589e != null) {
            m6200b(this.am);
        }
    }

    protected void m6200b(int i) {
        if (this.an) {
            this.ak = m233l().m353a();
            if (i == -1 || i == 0) {
                this.ak.m116b(2131755437, EmojiFragment.m6141a(), "emoji");
                this.f3588d.setVisibility(8);
                this.f3592h.setVisibility(0);
            } else {
                this.ak.m116b(2131755437, StickerFragment.m6180b(((StickerPackEntity) this.al.get(this.al.keyAt(i - 1))).m4480a()), "sticker");
                this.f3588d.setVisibility(0);
                this.f3592h.setVisibility(8);
            }
            this.ak.m109a();
            this.am = i;
        }
    }

    private void m6188N() {
        if (m234m()) {
            m227i().runOnUiThread(new C03141(this));
        }
    }

    private void m6189O() {
        int d = StickerDataSource.m4764d();
        if (d > 0) {
            this.f3593i.setVisibility(0);
            this.f3593i.setText(Integer.toString(d));
            return;
        }
        this.f3593i.setVisibility(8);
    }

    private void m6190P() {
        this.f3590f.setOnClickListener(new C03152(this));
        this.f3591g.setOnClickListener(new C03163(this));
        this.f3592h.setOnClickListener(new C03174(this));
    }

    public void onEvent(StickerPackDownloadedEvent stickerPackDownloadedEvent) {
        m6188N();
    }

    public void onEvent(StickerPackSelectedEvent stickerPackSelectedEvent) {
        if (StickerManager.m6491b().m6492a() != stickerPackSelectedEvent.f2496a) {
            if (stickerPackSelectedEvent.f2496a == -1) {
                m6200b(0);
            } else {
                m6200b(this.al.indexOfKey(stickerPackSelectedEvent.f2496a) + 1);
            }
            StickerManager.m6491b().m6493a(this.al.indexOfKey(stickerPackSelectedEvent.f2496a));
        }
    }

    private void m6191Q() {
        this.am = this.al.indexOfKey(StickerManager.m6491b().m6492a()) + 1;
    }

    public void m6201e(Bundle bundle) {
    }
}
