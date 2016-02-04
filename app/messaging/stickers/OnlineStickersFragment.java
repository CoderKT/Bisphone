package app.messaging.stickers;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.StickerPackEntity;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.storage.Storage;
import app.storage.StorageException;
import butterknife.ButterKnife;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OnlineStickersFragment extends Fragment {
    static List<OnlinePackEntity> f3941a;
    ListView f3942b;
    private OnlineStickersAdapter f3943c;
    private CommunicateStickyListPreview f3944d;

    public interface CommunicateStickyListPreview {
        void m6456a(OnlinePackEntity onlinePackEntity);
    }

    class OnlineStickersAdapter extends BaseAdapter implements OnClickListener {
        final /* synthetic */ OnlineStickersFragment f3933a;
        private Context f3934b;
        private List<OnlinePackEntity> f3935c;

        /* renamed from: app.messaging.stickers.OnlineStickersFragment.OnlineStickersAdapter.1 */
        class C03721 extends FileTaskListener {
            final /* synthetic */ ImageView f3930a;
            final /* synthetic */ File f3931b;
            final /* synthetic */ OnlineStickersAdapter f3932c;

            C03721(OnlineStickersAdapter onlineStickersAdapter, File file, ImageView imageView, File file2) {
                this.f3932c = onlineStickersAdapter;
                this.f3930a = imageView;
                this.f3931b = file2;
                super(file);
            }

            public void m6462a(int i, Header[] headerArr, File file) {
                CustomImageLoader.m4009a().m4022b(this.f3930a, this.f3931b);
            }

            public void m6463a(int i, Header[] headerArr, File file, Throwable th) {
                Main.f1926a.m5685e("Sticker Pack Icon Download Failed");
            }
        }

        public /* synthetic */ Object getItem(int i) {
            return m6464a(i);
        }

        public OnlineStickersAdapter(OnlineStickersFragment onlineStickersFragment, Context context, List<OnlinePackEntity> list) {
            this.f3933a = onlineStickersFragment;
            this.f3934b = context;
            this.f3935c = list;
        }

        public void m6465a(List<OnlinePackEntity> list) {
            this.f3935c = list;
        }

        public int getCount() {
            return this.f3935c.size();
        }

        public OnlinePackEntity m6464a(int i) {
            return (OnlinePackEntity) this.f3935c.get((this.f3935c.size() - i) - 1);
        }

        public long getItemId(int i) {
            return (long) ((OnlinePackEntity) this.f3935c.get(i)).f3901a;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            LayoutInflater layoutInflater = (LayoutInflater) this.f3934b.getSystemService("layout_inflater");
            if (view == null) {
                view = layoutInflater.inflate(2130903230, viewGroup, false);
                ViewHolder viewHolder2 = new ViewHolder(this.f3933a);
                viewHolder2.f3936a = (ImageView) view.findViewById(2131755607);
                viewHolder2.f3937b = (TextView) view.findViewById(2131755608);
                viewHolder2.f3939d = view.findViewById(2131755610);
                viewHolder2.f3938c = (TextView) view.findViewById(2131755609);
                view.setTag(viewHolder2);
                viewHolder = viewHolder2;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            OnlinePackEntity a = m6464a(i);
            try {
                File file = new File(StickerPackEntity.m4478c(a.f3901a));
                viewHolder.f3936a.setImageResource(2130837783);
                ImageLoader.m11076a().m11081a(viewHolder.f3936a);
                if (file.exists()) {
                    CustomImageLoader.m4009a().m4022b(viewHolder.f3936a, file);
                } else if (Storage.m6939a(this.f3934b, file.getParentFile())) {
                    String str = a.f3901a + ".icon";
                    if (HttpService.m5565a(str) == null) {
                        Header[] headerArr = new Header[]{new BasicHeader("Accept", "application/icon")};
                        RequestParams requestParams = new RequestParams();
                        requestParams.m10743a("id", a.f3901a + "");
                        requestParams.m10743a("density", LocalPacksManager.m6428b(this.f3934b) + "");
                        HttpService.m5567a(new TaskBuilder().m5590a(str).m5585a(this.f3934b).m5592b("https://chatng.bisphone.com:443/api/v1/sticker/file").m5591a(headerArr).m5589a(requestParams).m5586a(RequestType.get).m5587a(TaskPriority.medium).m5588a(new C03721(this, file, viewHolder.f3936a, file)).m5584a());
                    } else {
                        m6464a(i).f3904d = null;
                    }
                }
            } catch (StorageException e) {
            }
            viewHolder.f3937b.setText(a.f3902b);
            viewHolder.f3937b.setTag(Integer.valueOf(i));
            if (!m6464a(i).f3905e.booleanValue() || m6464a(i).f3904d == null || m6464a(i).f3904d.booleanValue()) {
                viewHolder.f3938c.setVisibility(8);
            } else {
                viewHolder.f3938c.setVisibility(0);
            }
            view.setOnClickListener(this);
            return view;
        }

        public void onClick(View view) {
            int intValue = ((Integer) view.findViewById(2131755608).getTag()).intValue();
            if (this.f3933a.f3944d == null || this.f3933a.f3943c == null) {
                this.f3933a.getActivity().finish();
            } else {
                this.f3933a.f3944d.m6456a(this.f3933a.f3943c.m6464a(intValue));
            }
        }
    }

    class ViewHolder {
        ImageView f3936a;
        TextView f3937b;
        TextView f3938c;
        View f3939d;
        final /* synthetic */ OnlineStickersFragment f3940e;

        ViewHolder(OnlineStickersFragment onlineStickersFragment) {
            this.f3940e = onlineStickersFragment;
        }
    }

    public void m6469a(CommunicateStickyListPreview communicateStickyListPreview) {
        this.f3944d = communicateStickyListPreview;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903142, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        if (f3941a != null) {
            m6470a(f3941a);
        }
        return inflate;
    }

    public void m6470a(List<OnlinePackEntity> list) {
        f3941a = list;
        this.f3943c = new OnlineStickersAdapter(this, getActivity(), new ArrayList(0));
        this.f3943c.m6465a((List) list);
        this.f3942b.setAdapter(this.f3943c);
    }

    public void m6468a(int i) {
        for (int i2 = 0; i2 < f3941a.size(); i2++) {
            if (((OnlinePackEntity) f3941a.get(i2)).f3901a == i) {
                this.f3944d.m6456a(this.f3943c.m6464a((f3941a.size() - i2) - 1));
            }
        }
    }
}
