package app.galley.external;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import app.Main;
import app.common.CustomImageLoader;
import app.galley.SelectedItemGalleryModel;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.io.File;
import java.util.List;

public class ImageGalleryItemAdapter extends BaseAdapter implements OnClickListener {
    Context f2631a;
    List<GalleryItemModel> f2632b;
    SelectedItemGalleryModel f2633c;
    LayoutParams f2634d;
    LayoutInflater f2635e;
    Animation f2636f;
    boolean f2637g;

    /* renamed from: app.galley.external.ImageGalleryItemAdapter.1 */
    class C01571 implements ImageLoadingListener {
        final /* synthetic */ MediaItemViewHolder f2629a;
        final /* synthetic */ ImageGalleryItemAdapter f2630b;

        C01571(ImageGalleryItemAdapter imageGalleryItemAdapter, MediaItemViewHolder mediaItemViewHolder) {
            this.f2630b = imageGalleryItemAdapter;
            this.f2629a = mediaItemViewHolder;
        }

        public void m5097a(String str, View view) {
        }

        public void m5099a(String str, View view, FailReason failReason) {
        }

        public void m5098a(String str, View view, Bitmap bitmap) {
            this.f2630b.f2636f = AnimationUtils.loadAnimation(this.f2630b.f2631a, 17432576);
            this.f2629a.f2638a.setAnimation(this.f2630b.f2636f);
            this.f2630b.f2636f.start();
        }

        public void m5100b(String str, View view) {
        }
    }

    public ImageGalleryItemAdapter(Context context, List<GalleryItemModel> list, SelectedItemGalleryModel selectedItemGalleryModel, int i, boolean z) {
        this.f2631a = context;
        this.f2632b = list;
        this.f2633c = selectedItemGalleryModel;
        this.f2637g = z;
        this.f2635e = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f2634d = new LayoutParams(i, i);
    }

    public int getCount() {
        if (this.f2632b == null) {
            return 0;
        }
        return this.f2632b.size();
    }

    public Object getItem(int i) {
        return this.f2632b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MediaItemViewHolder mediaItemViewHolder;
        if (view == null) {
            view = this.f2635e.inflate(2130903221, viewGroup, false);
            mediaItemViewHolder = new MediaItemViewHolder();
            mediaItemViewHolder.f2638a = (ImageView) view.findViewById(2131755583);
            mediaItemViewHolder.f2639b = (ImageView) view.findViewById(2131755584);
            mediaItemViewHolder.f2640c = view.findViewById(2131755585);
            mediaItemViewHolder.f2638a.setLayoutParams(this.f2634d);
            view.setTag(mediaItemViewHolder);
        } else {
            mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
        }
        if (this.f2637g) {
            mediaItemViewHolder.f2639b.setVisibility(4);
        } else if (this.f2633c.m5028a().get(Long.valueOf(((GalleryItemModel) this.f2632b.get(i)).f2572a)) != null) {
            mediaItemViewHolder.f2639b.setVisibility(0);
            mediaItemViewHolder.f2640c.setVisibility(0);
        } else {
            mediaItemViewHolder.f2639b.setVisibility(4);
            mediaItemViewHolder.f2640c.setVisibility(4);
        }
        mediaItemViewHolder.f2641d = i;
        try {
            mediaItemViewHolder.f2638a.setImageResource(2130837785);
            mediaItemViewHolder.f2638a.setTag(Integer.valueOf(i));
            CustomImageLoader.m4009a().m4019a(mediaItemViewHolder.f2638a, Uri.decode(Uri.fromFile(new File(((GalleryItemModel) this.f2632b.get(i)).f2575d)).toString()), new C01571(this, mediaItemViewHolder));
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
        }
        view.setOnClickListener(this);
        return view;
    }

    public void m5102a(List<GalleryItemModel> list) {
        this.f2632b = list;
    }

    public void m5101a(int i) {
        this.f2634d.height = i;
        this.f2634d.width = i;
    }

    public void onClick(View view) {
        MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
        if (this.f2631a instanceof ImageGalleryActivity) {
            ((ImageGalleryActivity) this.f2631a).m5086a(mediaItemViewHolder);
        } else if (this.f2631a instanceof VideoGalleryActivity) {
            ((VideoGalleryActivity) this.f2631a).m5124b(mediaItemViewHolder.f2641d);
        }
    }
}
