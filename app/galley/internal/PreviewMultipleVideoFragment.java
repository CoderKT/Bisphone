package app.galley.internal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.galley.external.GalleryVideoViewPagerAdapter;
import app.galley.external.GalleryVideoViewPagerFragment.GalleryComminucator;
import app.view.CustomViewPager;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class PreviewMultipleVideoFragment extends Fragment implements GalleryComminucator {
    CustomViewPager f2721a;
    List<String> f2722b;

    public View m5171a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903077, viewGroup, false);
    }

    public void m5173a(View view, Bundle bundle) {
        super.m199a(view, bundle);
        m5170a(view);
        m5169a();
    }

    private void m5169a() {
        this.f2722b = m223g().getStringArrayList(DataPacketExtension.ELEMENT);
        this.f2721a.setAdapter(new GalleryVideoViewPagerAdapter(m233l(), this, this.f2722b));
    }

    private void m5170a(View view) {
        view.findViewById(2131755175).setVisibility(8);
        this.f2721a = (CustomViewPager) view.findViewById(2131755174);
    }

    public String m5172a(int i) {
        return (String) this.f2722b.get(i);
    }
}
