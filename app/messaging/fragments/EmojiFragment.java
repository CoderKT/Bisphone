package app.messaging.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import app.messaging.emoji.EmojiAdapter;
import app.messaging.emoji.SmilyMaper;
import app.util.DeviceUtils;

public class EmojiFragment extends Fragment {
    private RecycleFragmentsListener f3550a;
    private RelativeLayout f3551b;
    private EmojiAdapter f3552c;
    private GridView f3553d;

    /* renamed from: app.messaging.fragments.EmojiFragment.1 */
    class C03091 implements OnItemClickListener {
        final /* synthetic */ EmojiFragment f3549a;

        C03091(EmojiFragment emojiFragment) {
            this.f3549a = emojiFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f3549a.f3550a.m5707d(this.f3549a.f3552c.m6121a(i).m6135b());
        }
    }

    public static EmojiFragment m6141a() {
        EmojiFragment emojiFragment = new EmojiFragment();
        emojiFragment.m224g(new Bundle());
        return emojiFragment;
    }

    public View m6143a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3551b = (RelativeLayout) layoutInflater.inflate(2130903134, viewGroup, false);
        this.f3553d = (GridView) this.f3551b.findViewById(2131755377);
        m6137M();
        SmilyMaper.m6131a();
        m6138N();
        m6139O();
        return this.f3551b;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6137M();
    }

    private void m6137M() {
        DisplayMetrics a = DeviceUtils.m7011a(m227i());
        this.f3553d.setNumColumns((int) (((float) a.widthPixels) / (a.density * 60.0f)));
    }

    private void m6138N() {
        this.f3552c = new EmojiAdapter(m227i());
        this.f3553d.setAdapter(this.f3552c);
    }

    private void m6139O() {
        this.f3553d.setOnItemClickListener(new C03091(this));
    }

    public void m6144a(Activity activity) {
        super.m188a(activity);
        try {
            this.f3550a = (RecycleFragmentsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement recycleFragmentsListener");
        }
    }

    public void m6145b() {
        super.m204b();
        this.f3550a = null;
    }

    public void m6146e(Bundle bundle) {
    }
}
