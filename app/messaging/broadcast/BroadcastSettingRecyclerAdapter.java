package app.messaging.broadcast;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntityLite;
import app.profile.ProfileModel;
import app.profile.ProfileViewer;
import java.util.ArrayList;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class BroadcastSettingRecyclerAdapter extends Adapter<BroadcastViewHolder> {
    private Context f3371a;
    private LayoutInflater f3372b;
    private ArrayList<ContactEntityLite> f3373c;

    class BroadcastViewHolder extends ViewHolder implements OnClickListener {
        TextView f3366l;
        ImageView f3367m;
        View f3368n;
        int f3369o;
        final /* synthetic */ BroadcastSettingRecyclerAdapter f3370p;

        public BroadcastViewHolder(BroadcastSettingRecyclerAdapter broadcastSettingRecyclerAdapter, View view) {
            this.f3370p = broadcastSettingRecyclerAdapter;
            super(view);
        }

        private void m6037c(int i) {
            this.f3369o = i;
            this.f3366l = (TextView) this.a.findViewById(2131755117);
            this.f3367m = (ImageView) this.a.findViewById(2131755116);
            this.f3368n = this.a.findViewById(2131755535);
            if (i >= this.f3370p.f3373c.size()) {
                this.f3368n.setVisibility(8);
                return;
            }
            this.f3368n.setVisibility(0);
            this.f3366l.setText(((ContactEntityLite) this.f3370p.f3373c.get(i)).m4238e());
            CustomImageLoader.m4009a().m4020a(this.f3367m, ((ContactEntityLite) this.f3370p.f3373c.get(i)).m4241h(), ((ContactEntityLite) this.f3370p.f3373c.get(i)).m4239f(), 2130837592);
            this.f3368n.setOnClickListener(this);
        }

        public void onClick(View view) {
            ContactEntityLite contactEntityLite = (ContactEntityLite) this.f3370p.f3373c.get(this.f3369o);
            Parcelable profileModel = new ProfileModel(contactEntityLite.m4241h(), contactEntityLite.m4239f(), contactEntityLite.m4237d(), contactEntityLite.m4238e(), contactEntityLite.m4234b(), contactEntityLite.m4232a(), -1, contactEntityLite.m4240g());
            Intent intent = new Intent(this.f3370p.f3371a, ProfileViewer.class);
            intent.putExtra(DataPacketExtension.ELEMENT, profileModel);
            this.f3370p.f3371a.startActivity(intent);
        }
    }

    public /* synthetic */ ViewHolder m6041a(ViewGroup viewGroup, int i) {
        return m6045c(viewGroup, i);
    }

    public BroadcastSettingRecyclerAdapter(Context context) {
        this.f3371a = context;
        this.f3373c = new ArrayList();
        this.f3372b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public BroadcastViewHolder m6045c(ViewGroup viewGroup, int i) {
        return new BroadcastViewHolder(this, this.f3372b.inflate(2130903206, viewGroup, false));
    }

    public void m6043a(BroadcastViewHolder broadcastViewHolder, int i) {
        broadcastViewHolder.m6037c(i);
    }

    public int m6040a() {
        return this.f3373c.size() + 1;
    }

    public void m6044a(ArrayList<ContactEntityLite> arrayList) {
        this.f3373c = arrayList;
    }
}
