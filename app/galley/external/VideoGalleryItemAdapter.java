package app.galley.external;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore.Video.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import app.common.CustomImageLoader;
import app.galley.SelectedItemGalleryModel;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.util.List;

public class VideoGalleryItemAdapter extends BaseAdapter implements OnClickListener {
    Context f2694a;
    List<GalleryItemModel> f2695b;
    SelectedItemGalleryModel f2696c;
    LayoutParams f2697d;
    LayoutInflater f2698e;
    Animation f2699f;
    boolean f2700g;

    /* renamed from: app.galley.external.VideoGalleryItemAdapter.1 */
    class C01631 implements ImageLoadingListener {
        final /* synthetic */ MediaItemViewHolder f2692a;
        final /* synthetic */ VideoGalleryItemAdapter f2693b;

        C01631(VideoGalleryItemAdapter videoGalleryItemAdapter, MediaItemViewHolder mediaItemViewHolder) {
            this.f2693b = videoGalleryItemAdapter;
            this.f2692a = mediaItemViewHolder;
        }

        public void m5131a(String str, View view) {
            ((ImageView) view).setImageResource(2130837785);
        }

        public void m5133a(String str, View view, FailReason failReason) {
            ((ImageView) view).setImageResource(2130837785);
        }

        public void m5132a(String str, View view, Bitmap bitmap) {
            this.f2693b.f2699f = AnimationUtils.loadAnimation(this.f2693b.f2694a, 17432576);
            this.f2692a.f2638a.setAnimation(this.f2693b.f2699f);
            this.f2693b.f2699f.start();
        }

        public void m5134b(String str, View view) {
        }
    }

    public VideoGalleryItemAdapter(Context context, List<GalleryItemModel> list, SelectedItemGalleryModel selectedItemGalleryModel, int i, boolean z) {
        this.f2694a = context;
        this.f2695b = list;
        this.f2696c = selectedItemGalleryModel;
        this.f2700g = z;
        this.f2698e = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f2697d = new LayoutParams(i, i);
    }

    public int getCount() {
        return this.f2695b == null ? 0 : this.f2695b.size();
    }

    public Object getItem(int i) {
        return this.f2695b.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MediaItemViewHolder mediaItemViewHolder;
        if (view == null) {
            view = this.f2698e.inflate(2130903221, viewGroup, false);
            mediaItemViewHolder = new MediaItemViewHolder();
            mediaItemViewHolder.f2638a = (ImageView) view.findViewById(2131755583);
            mediaItemViewHolder.f2639b = (ImageView) view.findViewById(2131755584);
            mediaItemViewHolder.f2640c = view.findViewById(2131755585);
            mediaItemViewHolder.f2638a.setLayoutParams(this.f2697d);
            view.setTag(mediaItemViewHolder);
        } else {
            mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
        }
        if (this.f2700g) {
            mediaItemViewHolder.f2639b.setVisibility(4);
        } else if (this.f2696c.m5028a().get(Long.valueOf(((GalleryItemModel) this.f2695b.get(i)).f2572a)) != null) {
            mediaItemViewHolder.f2639b.setVisibility(0);
            mediaItemViewHolder.f2640c.setVisibility(0);
        } else {
            mediaItemViewHolder.f2639b.setVisibility(4);
            mediaItemViewHolder.f2640c.setVisibility(4);
        }
        mediaItemViewHolder.f2641d = i;
        CustomImageLoader.m4009a().m4019a(mediaItemViewHolder.f2638a, ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, ((GalleryItemModel) this.f2695b.get(i)).f2572a).toString(), new C01631(this, mediaItemViewHolder));
        view.setOnClickListener(this);
        return view;
    }

    public void m5136a(List<GalleryItemModel> list) {
        this.f2695b = list;
    }

    public void m5135a(int i) {
        this.f2697d.height = i;
        this.f2697d.width = i;
    }

    public void onClick(View view) {
        int i = ((MediaItemViewHolder) view.getTag()).f2641d;
        if (this.f2694a instanceof ImageGalleryActivity) {
            ((ImageGalleryActivity) this.f2694a).m5086a((MediaItemViewHolder) view.getTag());
        } else if (this.f2694a instanceof VideoGalleryActivity) {
            ((VideoGalleryActivity) this.f2694a).m5124b(i);
        }
    }
}
