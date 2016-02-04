package app.galley.external;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import app.galley.SelectedItemGalleryModel;
import app.galley.external.GalleryImageViewPagerFragment.GalleryCommunicator;
import java.util.List;
import java.util.Map.Entry;

public class GalleryImageViewPagerAdapter extends FragmentStatePagerAdapter {
    GalleryCommunicator f2549a;
    Object f2550b;
    int f2551c;
    boolean f2552d;
    boolean f2553e;

    public /* synthetic */ Fragment m5036a(int i) {
        return m5037b(i);
    }

    public GalleryImageViewPagerAdapter(FragmentManager fragmentManager, GalleryCommunicator galleryCommunicator, Object obj, boolean z, int i, boolean z2) {
        super(fragmentManager);
        this.f2549a = galleryCommunicator;
        this.f2550b = obj;
        this.f2552d = z;
        this.f2551c = i;
        this.f2553e = z2;
    }

    public int m5035a(Object obj) {
        return -2;
    }

    public GalleryImageViewPagerFragment m5037b(int i) {
        return GalleryImageViewPagerFragment.m5043a(i, m5038d(i), this.f2549a, this.f2552d, this.f2551c, this.f2553e);
    }

    public int getCount() {
        if (this.f2550b instanceof SelectedItemGalleryModel) {
            return ((SelectedItemGalleryModel) this.f2550b).m5028a().size();
        }
        if (this.f2550b instanceof List) {
            return ((List) this.f2550b).size();
        }
        return 0;
    }

    public long m5038d(int i) {
        if (!(this.f2550b instanceof SelectedItemGalleryModel)) {
            return -1;
        }
        int i2 = 0;
        for (Entry entry : ((SelectedItemGalleryModel) this.f2550b).m5028a().entrySet()) {
            if (i2 == i) {
                return ((Long) entry.getKey()).longValue();
            }
            i2++;
        }
        return -1;
    }
}
