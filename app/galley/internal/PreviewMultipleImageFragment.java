package app.galley.internal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import app.Main;
import app.common.CustomImageLoader;
import app.galley.MessagePreview;
import app.galley.SelectedItemGalleryModel;
import app.galley.external.GalleryImageViewPagerAdapter;
import app.galley.external.GalleryImageViewPagerFragment.GalleryCommunicator;
import app.galley.external.GalleryItemModel;
import app.galley.external.ImageGalleryActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class PreviewMultipleImageFragment extends Fragment implements OnClickListener, GalleryCommunicator {
    LinearLayout f2713a;
    HorizontalScrollView f2714b;
    List<View> f2715c;
    ViewPager f2716d;
    SelectedItemGalleryModel f2717e;
    GalleryImageViewPagerAdapter f2718f;
    protected InputMethodManager f2719g;
    PreviewComminucator f2720h;

    public interface PreviewComminucator {
        void m5004a(ArrayList<GalleryItemModel> arrayList);
    }

    /* renamed from: app.galley.internal.PreviewMultipleImageFragment.1 */
    class C01661 implements OnPageChangeListener {
        int f2709a;
        final /* synthetic */ PreviewMultipleImageFragment f2710b;

        /* renamed from: app.galley.internal.PreviewMultipleImageFragment.1.1 */
        class C01651 implements Runnable {
            final /* synthetic */ int f2707a;
            final /* synthetic */ C01661 f2708b;

            C01651(C01661 c01661, int i) {
                this.f2708b = c01661;
                this.f2707a = i;
            }

            public void run() {
                this.f2708b.f2710b.f2714b.scrollTo(((View) this.f2708b.f2710b.f2715c.get(this.f2707a)).getLeft(), 0);
                ((LinearLayout) this.f2708b.f2710b.f2714b.getChildAt(0)).getChildAt(this.f2707a).setBackgroundColor(this.f2708b.f2710b.m229j().getColor(2131689523));
                if (this.f2708b.f2709a > -1 && this.f2708b.f2709a < this.f2708b.f2710b.f2718f.getCount()) {
                    ((LinearLayout) this.f2708b.f2710b.f2714b.getChildAt(0)).getChildAt(this.f2708b.f2709a).setBackgroundColor(Color.parseColor("#00000000"));
                }
                this.f2708b.f2709a = this.f2707a;
            }
        }

        C01661(PreviewMultipleImageFragment previewMultipleImageFragment) {
            this.f2710b = previewMultipleImageFragment;
            this.f2709a = 0;
        }

        public void m5154a(int i, float f, int i2) {
            this.f2710b.m5161a();
        }

        public void m5153a(int i) {
            this.f2710b.f2714b.post(new C01651(this, i));
        }

        public void m5155b(int i) {
        }
    }

    /* renamed from: app.galley.internal.PreviewMultipleImageFragment.2 */
    class C01672 implements AnimationListener {
        final /* synthetic */ int f2711a;
        final /* synthetic */ PreviewMultipleImageFragment f2712b;

        C01672(PreviewMultipleImageFragment previewMultipleImageFragment, int i) {
            this.f2712b = previewMultipleImageFragment;
            this.f2711a = i;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f2712b.f2717e.m5028a().remove((Long) ((View) this.f2712b.f2715c.get(this.f2711a)).getTag(2131296317));
            this.f2712b.f2713a.removeViewAt(this.f2711a);
            this.f2712b.f2715c.remove(this.f2711a);
            this.f2712b.f2718f.m58b();
            if (this.f2712b.f2716d.getCurrentItem() > this.f2711a && this.f2712b.f2716d.getCurrentItem() != this.f2712b.f2718f.getCount()) {
                this.f2712b.f2716d.m1301a(this.f2712b.f2716d.getCurrentItem() - 1, true);
            }
            if (this.f2712b.f2718f.getCount() != 0) {
                ((LinearLayout) this.f2712b.f2714b.getChildAt(0)).getChildAt(this.f2712b.f2716d.getCurrentItem()).setBackgroundColor(this.f2712b.m229j().getColor(2131689523));
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public View m5160a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903077, viewGroup, false);
    }

    public void m5165a(View view, Bundle bundle) {
        super.m199a(view, bundle);
        m215d(true);
        m5158a(view);
        m5156M();
        this.f2716d.m1304a(new C01661(this));
    }

    private void m5156M() {
        this.f2717e = (SelectedItemGalleryModel) m223g().getParcelable(DataPacketExtension.ELEMENT);
        m5157N();
    }

    private void m5158a(View view) {
        view.findViewById(2131755176).setOnClickListener(this);
        this.f2713a = (LinearLayout) view.findViewById(2131755178);
        this.f2714b = (HorizontalScrollView) view.findViewById(2131755177);
        this.f2716d = (ViewPager) view.findViewById(2131755174);
    }

    public void onClick(View view) {
        Log.d("onClick called", "123");
        switch (view.getId()) {
            case 2131755176:
                m5161a();
                this.f2718f = null;
                this.f2716d.setAdapter(null);
                Intent intent = new Intent(m227i(), ImageGalleryActivity.class);
                intent.putExtra(DataPacketExtension.ELEMENT, this.f2717e);
                intent.putExtra("edit", true);
                m227i().startActivityForResult(intent, 100);
            case 2131755587:
                Log.d("onClick called", "scrollIcon");
                int i = 0;
                for (int i2 = 0; i2 < this.f2715c.size(); i2++) {
                    if (((Integer) ((View) this.f2715c.get(i2)).getTag(2131296334)).intValue() == ((Integer) view.getTag(2131296334)).intValue()) {
                        i = i2;
                    }
                }
                this.f2716d.setCurrentItem(i);
            case 2131755588:
                Log.d("onClick called", "delete scroll");
                m5161a();
                m5159a((Long) view.getTag(2131296317));
            default:
        }
    }

    private void m5157N() {
        this.f2713a.removeAllViews();
        this.f2715c = new ArrayList();
        int i = 0;
        for (Entry entry : this.f2717e.m5028a().entrySet()) {
            View inflate = ((LayoutInflater) m227i().getSystemService("layout_inflater")).inflate(2130903222, null);
            ImageView imageView = (ImageView) inflate.findViewById(2131755587);
            View findViewById = inflate.findViewById(2131755588);
            findViewById.setTag(2131296317, entry.getKey());
            imageView.setTag(2131296317, entry.getKey());
            inflate.setTag(2131296317, entry.getKey());
            findViewById.setTag(2131296334, Integer.valueOf(i));
            imageView.setTag(2131296334, Integer.valueOf(i));
            inflate.setTag(2131296334, Integer.valueOf(i));
            i++;
            findViewById.setOnClickListener(this);
            imageView.setOnClickListener(this);
            CustomImageLoader.m4009a().m4018a(imageView, Uri.decode(Uri.fromFile(new File(((GalleryItemModel) entry.getValue()).m5063d())).toString()));
            this.f2713a.addView(inflate);
            this.f2715c.add(inflate);
        }
        this.f2718f = new GalleryImageViewPagerAdapter(m233l(), this, this.f2717e, false, 0, false);
        this.f2716d.setAdapter(this.f2718f);
        if (this.f2713a.getChildCount() > 0) {
            ((LinearLayout) this.f2714b.getChildAt(0)).getChildAt(this.f2716d.getCurrentItem()).setBackgroundColor(m229j().getColor(2131689523));
        }
    }

    private void m5159a(Long l) {
        Log.d("removeFromScroll", "removed from scroll");
        int i = 0;
        for (Entry key : this.f2717e.m5028a().entrySet()) {
            int i2;
            if (((Long) key.getKey()).equals(l)) {
                Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(200);
                ((View) this.f2715c.get(i)).startAnimation(scaleAnimation);
                scaleAnimation.setAnimationListener(new C01672(this, i));
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
    }

    public MessagePreview m5167b(int i) {
        int i2 = 0;
        for (Entry entry : this.f2717e.m5028a().entrySet()) {
            if (i2 == i) {
                return new MessagePreview(((GalleryItemModel) entry.getValue()).m5064e(), ((GalleryItemModel) entry.getValue()).m5063d(), ((GalleryItemModel) entry.getValue()).m5062c(), ((GalleryItemModel) entry.getValue()).m5059a(), ((GalleryItemModel) entry.getValue()).m5061b());
            }
            i2++;
        }
        return null;
    }

    public void m5162a(long j, String str) {
        ((GalleryItemModel) this.f2717e.m5028a().get(Long.valueOf(j))).m5060a(str);
    }

    public void m5164a(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820558, menu);
        super.m198a(menu, menuInflater);
    }

    public boolean m5166a(MenuItem menuItem) {
        m5161a();
        switch (menuItem.getItemId()) {
            case 2131755646:
                if (this.f2717e != null && this.f2717e.m5028a() != null && this.f2717e.m5028a().size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (Entry value : this.f2717e.m5028a().entrySet()) {
                        arrayList.add(value.getValue());
                    }
                    this.f2720h.m5004a(arrayList);
                    break;
                }
                Main.m3905a(a_(2131296843));
                break;
                break;
        }
        return super.m201a(menuItem);
    }

    public void m5163a(Activity activity) {
        super.m188a(activity);
        try {
            this.f2720h = (PreviewComminucator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void m5168b() {
        super.m204b();
        this.f2720h = null;
    }

    protected void m5161a() {
        this.f2719g = (InputMethodManager) m227i().getSystemService("input_method");
        this.f2719g.hideSoftInputFromWindow(m237p().getWindowToken(), 0);
    }
}
