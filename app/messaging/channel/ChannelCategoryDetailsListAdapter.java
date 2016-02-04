package app.messaging.channel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.common.CustomImageLoader;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ChannelCategoryDetailsListAdapter extends BaseAdapter implements OnClickListener {
    Context f3438a;
    List<ChannelDetailAdapterModel> f3439b;
    LayoutInflater f3440c;
    int f3441d;

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsListAdapter.1 */
    class C02941 implements DialogInterface.OnClickListener {
        final /* synthetic */ ChannelCategoryDetailsListAdapter f3427a;

        C02941(ChannelCategoryDetailsListAdapter channelCategoryDetailsListAdapter) {
            this.f3427a = channelCategoryDetailsListAdapter;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.messaging.channel.ChannelCategoryDetailsListAdapter.2 */
    class C02952 implements DialogInterface.OnClickListener {
        final /* synthetic */ ViewHolder f3428a;
        final /* synthetic */ ChannelCategoryDetailsListAdapter f3429b;

        C02952(ChannelCategoryDetailsListAdapter channelCategoryDetailsListAdapter, ViewHolder viewHolder) {
            this.f3429b = channelCategoryDetailsListAdapter;
            this.f3428a = viewHolder;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f3429b.m6084a(this.f3429b.m6086a(this.f3428a.f3436g).f3501a.getId(), this.f3428a);
        }
    }

    class ViewHolder {
        TextView f3430a;
        TextView f3431b;
        ImageView f3432c;
        TextView f3433d;
        View f3434e;
        ProgressBar f3435f;
        int f3436g;
        final /* synthetic */ ChannelCategoryDetailsListAdapter f3437h;

        ViewHolder(ChannelCategoryDetailsListAdapter channelCategoryDetailsListAdapter) {
            this.f3437h = channelCategoryDetailsListAdapter;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m6086a(i);
    }

    public ChannelCategoryDetailsListAdapter(Context context, List<ChannelDetailAdapterModel> list) {
        this.f3438a = context;
        this.f3439b = list;
        this.f3440c = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f3441d = VERSION.SDK_INT;
    }

    public int getCount() {
        return this.f3439b.size();
    }

    public ChannelDetailAdapterModel m6086a(int i) {
        return (ChannelDetailAdapterModel) this.f3439b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"NewApi"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f3440c.inflate(2130903215, viewGroup, false);
            viewHolder = new ViewHolder(this);
            viewHolder.f3432c = (ImageView) view.findViewById(2131755563);
            viewHolder.f3431b = (TextView) view.findViewById(2131755565);
            viewHolder.f3433d = (TextView) view.findViewById(2131755568);
            viewHolder.f3430a = (TextView) view.findViewById(2131755564);
            viewHolder.f3435f = (ProgressBar) view.findViewById(2131755567);
            viewHolder.f3434e = view.findViewById(2131755566);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f3436g = i;
        viewHolder.f3430a.setText(((ChannelDetailAdapterModel) this.f3439b.get(i)).f3501a.getTitle());
        viewHolder.f3431b.setText(((ChannelDetailAdapterModel) this.f3439b.get(i)).f3501a.getDescription());
        CustomImageLoader.m4009a().m4020a(viewHolder.f3432c, ((ChannelDetailAdapterModel) this.f3439b.get(i)).f3501a.getAvatar(), null, 2130837647);
        if (((ChannelDetailAdapterModel) this.f3439b.get(i)).f3502b == null) {
            viewHolder.f3433d.setVisibility(4);
            viewHolder.f3435f.setVisibility(0);
        } else if (((ChannelDetailAdapterModel) this.f3439b.get(i)).f3502b.booleanValue()) {
            viewHolder.f3433d.setVisibility(0);
            viewHolder.f3435f.setVisibility(4);
            viewHolder.f3433d.setText(this.f3438a.getResources().getString(2131296387));
            viewHolder.f3433d.setTextColor(this.f3438a.getResources().getColor(2131689507));
            if (this.f3441d < 16) {
                viewHolder.f3434e.setBackgroundDrawable(this.f3438a.getResources().getDrawable(2130837646));
            } else {
                viewHolder.f3434e.setBackground(this.f3438a.getResources().getDrawable(2130837646));
            }
        } else {
            viewHolder.f3433d.setVisibility(0);
            viewHolder.f3435f.setVisibility(4);
            viewHolder.f3433d.setText(this.f3438a.getResources().getString(2131296384));
            viewHolder.f3433d.setTextColor(this.f3438a.getResources().getColor(2131689503));
            if (this.f3441d < 16) {
                viewHolder.f3434e.setBackgroundDrawable(this.f3438a.getResources().getDrawable(2130837644));
            } else {
                viewHolder.f3434e.setBackground(this.f3438a.getResources().getDrawable(2130837644));
            }
        }
        viewHolder.f3434e.setTag(viewHolder);
        viewHolder.f3434e.setOnClickListener(this);
        view.setOnClickListener(this);
        if (((ChannelDetailAdapterModel) this.f3439b.get(i)).f3501a.getPrice() > 0.0f) {
            viewHolder.f3434e.setVisibility(8);
        } else {
            viewHolder.f3434e.setVisibility(8);
        }
        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755566:
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (((ChannelDetailAdapterModel) this.f3439b.get(viewHolder.f3436g)).f3502b == null) {
                    return;
                }
                if (((ChannelDetailAdapterModel) this.f3439b.get(viewHolder.f3436g)).f3502b.booleanValue()) {
                    m6082a(viewHolder);
                } else {
                    m6085b(m6086a(viewHolder.f3436g).f3501a.getId(), viewHolder);
                }
            default:
                Intent intent = new Intent(this.f3438a, ChannelDetailActivity.class);
                intent.putExtra(DataPacketExtension.ELEMENT, (Parcelable) this.f3439b.get(((ViewHolder) view.getTag()).f3436g));
                this.f3438a.startActivity(intent);
        }
    }

    private void m6082a(ViewHolder viewHolder) {
        new Builder(this.f3438a, 2131558538).m1980a(this.f3438a.getString(2131296389)).m1986b(String.format(this.f3438a.getString(2131296425), new Object[]{((ChannelDetailAdapterModel) this.f3439b.get(viewHolder.f3436g)).f3501a.getTitle()})).m1975a(17039379, new C02952(this, viewHolder)).m1985b(17039369, new C02941(this)).m1989c(2130837731).m1992c();
    }

    private void m6084a(String str, ViewHolder viewHolder) {
        viewHolder.f3435f.setVisibility(0);
        viewHolder.f3433d.setVisibility(4);
        ((ChannelDetailAdapterModel) this.f3439b.get(viewHolder.f3436g)).f3502b = null;
        ChannelCommunication.getInstance().m6095a(str, viewHolder, false);
    }

    private void m6085b(String str, ViewHolder viewHolder) {
        ((ChannelDetailAdapterModel) this.f3439b.get(viewHolder.f3436g)).f3502b = null;
        viewHolder.f3435f.setVisibility(0);
        viewHolder.f3433d.setVisibility(4);
        ChannelCommunication.getInstance().m6094a(((ChannelDetailAdapterModel) this.f3439b.get(viewHolder.f3436g)).f3501a, str, viewHolder);
    }
}
