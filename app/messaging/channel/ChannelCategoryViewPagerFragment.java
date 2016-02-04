package app.messaging.channel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import app.common.CustomImageLoader;
import app.database.datasource.ChannelDataSource;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ChannelCategoryViewPagerFragment extends Fragment {

    /* renamed from: app.messaging.channel.ChannelCategoryViewPagerFragment.1 */
    class C02961 implements OnClickListener {
        final /* synthetic */ ChannelModel f3455a;
        final /* synthetic */ ChannelCategoryViewPagerFragment f3456b;

        C02961(ChannelCategoryViewPagerFragment channelCategoryViewPagerFragment, ChannelModel channelModel) {
            this.f3456b = channelCategoryViewPagerFragment;
            this.f3455a = channelModel;
        }

        public void onClick(View view) {
            Parcelable channelDetailAdapterModel = new ChannelDetailAdapterModel();
            channelDetailAdapterModel.f3501a = this.f3455a;
            channelDetailAdapterModel.f3502b = Boolean.valueOf(ChannelDataSource.m4550d(this.f3455a.getId()));
            Intent intent = new Intent(this.f3456b.getActivity(), ChannelDetailActivity.class);
            intent.putExtra(DataPacketExtension.ELEMENT, channelDetailAdapterModel);
            this.f3456b.startActivity(intent);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903129, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle == null) {
            ImageView imageView = (ImageView) view.findViewById(2131755330);
            ChannelModel channelModel = (ChannelModel) getArguments().getParcelable(DataPacketExtension.ELEMENT);
            CustomImageLoader.m4009a().m4020a(imageView, channelModel.getCover(), null, 2130837946);
            imageView.setOnClickListener(new C02961(this, channelModel));
        }
    }
}
