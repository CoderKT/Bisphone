package app.galley.external;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import app.galley.external.GalleryVideoViewPagerFragment.GalleryComminucator;
import java.util.List;

public class GalleryVideoViewPagerAdapter extends FragmentPagerAdapter {
    GalleryComminucator f2579a;
    List<String> f2580b;

    public /* synthetic */ Fragment m5066a(int i) {
        return m5067d(i);
    }

    public GalleryVideoViewPagerAdapter(FragmentManager fragmentManager, GalleryComminucator galleryComminucator, List<String> list) {
        super(fragmentManager);
        this.f2579a = galleryComminucator;
        this.f2580b = list;
    }

    public int m5065a(Object obj) {
        return -2;
    }

    public GalleryVideoViewPagerFragment m5067d(int i) {
        return GalleryVideoViewPagerFragment.m5070a(i, this.f2579a);
    }

    public int getCount() {
        return this.f2580b.size();
    }
}
