package app.messaging.channel;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v13.app.FragmentPagerAdapter;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ChannelCategoryViewPagerAdapter extends FragmentPagerAdapter {
    List<ChannelModel> f3454a;

    public ChannelCategoryViewPagerAdapter(FragmentManager fragmentManager, List<ChannelModel> list) {
        super(fragmentManager);
        this.f3454a = list;
    }

    public Fragment m6087a(int i) {
        Fragment channelCategoryViewPagerFragment = new ChannelCategoryViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DataPacketExtension.ELEMENT, (Parcelable) this.f3454a.get(i));
        bundle.putInt("position", i);
        channelCategoryViewPagerFragment.setArguments(bundle);
        return channelCategoryViewPagerFragment;
    }

    public int getCount() {
        return this.f3454a.size();
    }
}
