package app.galley.external;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.common.CustomImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class VideoGalleryAlbumAdapter extends BaseAdapter implements OnClickListener {
    Context f2687a;
    LinkedHashMap<String, ImageGalleryAlbumModel> f2688b;
    LayoutInflater f2689c;
    Animation f2690d;
    ArrayList<Rotation> f2691e;

    /* renamed from: app.galley.external.VideoGalleryAlbumAdapter.1 */
    class C01611 extends ArrayList<Rotation> {
        final /* synthetic */ VideoGalleryAlbumAdapter f2673a;

        C01611(VideoGalleryAlbumAdapter videoGalleryAlbumAdapter) {
            this.f2673a = videoGalleryAlbumAdapter;
            add(new Rotation(this.f2673a, 14, -20, -18));
            add(new Rotation(this.f2673a, 6, -18, 18));
            add(new Rotation(this.f2673a, 10, -10, -30));
            add(new Rotation(this.f2673a, 2, -26, 6));
            add(new Rotation(this.f2673a, -6, 18, -18));
            add(new Rotation(this.f2673a, 18, 6, -6));
            add(new Rotation(this.f2673a, -10, 10, -30));
        }
    }

    /* renamed from: app.galley.external.VideoGalleryAlbumAdapter.2 */
    class C01622 implements ImageLoadingListener {
        final /* synthetic */ ViewHolder f2674a;
        final /* synthetic */ VideoGalleryAlbumAdapter f2675b;

        C01622(VideoGalleryAlbumAdapter videoGalleryAlbumAdapter, ViewHolder viewHolder) {
            this.f2675b = videoGalleryAlbumAdapter;
            this.f2674a = viewHolder;
        }

        public void m5127a(String str, View view) {
        }

        public void m5129a(String str, View view, FailReason failReason) {
        }

        public void m5128a(String str, View view, Bitmap bitmap) {
            this.f2675b.f2690d = AnimationUtils.loadAnimation(this.f2675b.f2687a, 17432576);
            this.f2674a.f2682c.setAnimation(this.f2675b.f2690d);
            this.f2675b.f2690d.start();
        }

        public void m5130b(String str, View view) {
        }
    }

    class Rotation {
        int f2676a;
        int f2677b;
        int f2678c;
        final /* synthetic */ VideoGalleryAlbumAdapter f2679d;

        Rotation(VideoGalleryAlbumAdapter videoGalleryAlbumAdapter, int i, int i2, int i3) {
            this.f2679d = videoGalleryAlbumAdapter;
            this.f2676a = i;
            this.f2677b = i2;
            this.f2678c = i3;
        }
    }

    class ViewHolder {
        TextView f2680a;
        TextView f2681b;
        ImageView f2682c;
        ImageView f2683d;
        ImageView f2684e;
        int f2685f;
        final /* synthetic */ VideoGalleryAlbumAdapter f2686g;

        ViewHolder(VideoGalleryAlbumAdapter videoGalleryAlbumAdapter) {
            this.f2686g = videoGalleryAlbumAdapter;
        }
    }

    public VideoGalleryAlbumAdapter(Context context, LinkedHashMap<String, ImageGalleryAlbumModel> linkedHashMap) {
        this.f2691e = new C01611(this);
        this.f2687a = context;
        this.f2688b = linkedHashMap;
        this.f2689c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.f2688b.size();
    }

    public Object getItem(int i) {
        return this.f2688b.get("" + i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f2689c.inflate(2130903220, viewGroup, false);
            viewHolder = new ViewHolder(this);
            viewHolder.f2681b = (TextView) view.findViewById(2131755582);
            viewHolder.f2682c = (ImageView) view.findViewById(2131755578);
            viewHolder.f2683d = (ImageView) view.findViewById(2131755579);
            viewHolder.f2684e = (ImageView) view.findViewById(2131755580);
            viewHolder.f2680a = (TextView) view.findViewById(2131755581);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.f2683d.setVisibility(4);
        viewHolder.f2684e.setVisibility(4);
        ImageGalleryAlbumModel imageGalleryAlbumModel = (ImageGalleryAlbumModel) new ArrayList(this.f2688b.values()).get(i);
        viewHolder.f2685f = i;
        viewHolder.f2680a.setText(imageGalleryAlbumModel.f2628c);
        viewHolder.f2681b.setText(Integer.toString(imageGalleryAlbumModel.f2627b));
        if (imageGalleryAlbumModel.f2626a.size() > 0) {
            viewHolder.f2682c.setVisibility(0);
            viewHolder.f2682c.setImageResource(2130837785);
            CustomImageLoader.m4009a().m4019a(viewHolder.f2682c, Uri.decode(Uri.fromFile(new File(((GalleryItemModel) imageGalleryAlbumModel.f2626a.get(0)).f2575d)).toString()), new C01622(this, viewHolder));
            Log.d("icon 1 seted", " selected " + i);
        } else {
            viewHolder.f2682c.setImageBitmap(null);
            viewHolder.f2682c.setVisibility(4);
        }
        view.setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (this.f2687a instanceof ImageGalleryActivity) {
            ((ImageGalleryActivity) this.f2687a).f2608y = viewHolder.f2685f;
            ((ImageGalleryActivity) this.f2687a).m5087b(((ImageGalleryAlbumModel) new ArrayList(this.f2688b.values()).get(viewHolder.f2685f)).f2628c);
        } else if (this.f2687a instanceof VideoGalleryActivity) {
            ((VideoGalleryActivity) this.f2687a).f2654B = viewHolder.f2685f;
            ((VideoGalleryActivity) this.f2687a).m5125b(((ImageGalleryAlbumModel) new ArrayList(this.f2688b.values()).get(viewHolder.f2685f)).f2628c);
        }
    }
}
