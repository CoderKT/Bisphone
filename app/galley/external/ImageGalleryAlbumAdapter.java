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

public class ImageGalleryAlbumAdapter extends BaseAdapter implements OnClickListener {
    Context f2621a;
    LinkedHashMap<String, ImageGalleryAlbumModel> f2622b;
    LayoutInflater f2623c;
    Animation f2624d;
    ArrayList<Rotation> f2625e;

    /* renamed from: app.galley.external.ImageGalleryAlbumAdapter.1 */
    class C01551 extends ArrayList<Rotation> {
        final /* synthetic */ ImageGalleryAlbumAdapter f2610a;

        C01551(ImageGalleryAlbumAdapter imageGalleryAlbumAdapter) {
            this.f2610a = imageGalleryAlbumAdapter;
            add(new Rotation(this.f2610a, 14, -20, -18));
            add(new Rotation(this.f2610a, 6, -18, 18));
            add(new Rotation(this.f2610a, 10, -10, -30));
            add(new Rotation(this.f2610a, 2, -26, 6));
            add(new Rotation(this.f2610a, -6, 18, -18));
            add(new Rotation(this.f2610a, 18, 6, -6));
            add(new Rotation(this.f2610a, -10, 10, -30));
        }
    }

    /* renamed from: app.galley.external.ImageGalleryAlbumAdapter.2 */
    class C01562 implements ImageLoadingListener {
        final /* synthetic */ ImageGalleryAlbumAdapter f2611a;

        C01562(ImageGalleryAlbumAdapter imageGalleryAlbumAdapter) {
            this.f2611a = imageGalleryAlbumAdapter;
        }

        public void m5093a(String str, View view) {
        }

        public void m5095a(String str, View view, FailReason failReason) {
        }

        public void m5094a(String str, View view, Bitmap bitmap) {
            view.setVisibility(0);
            this.f2611a.f2624d = AnimationUtils.loadAnimation(this.f2611a.f2621a, 17432576);
            view.setAnimation(this.f2611a.f2624d);
            this.f2611a.f2624d.start();
        }

        public void m5096b(String str, View view) {
        }
    }

    class Rotation {
        int[] f2612a;
        final /* synthetic */ ImageGalleryAlbumAdapter f2613b;

        Rotation(ImageGalleryAlbumAdapter imageGalleryAlbumAdapter, int i, int i2, int i3) {
            this.f2613b = imageGalleryAlbumAdapter;
            this.f2612a = new int[3];
            this.f2612a[0] = i;
            this.f2612a[1] = i2;
            this.f2612a[2] = i3;
        }
    }

    class ViewHolder {
        TextView f2614a;
        TextView f2615b;
        ImageView f2616c;
        ImageView f2617d;
        ImageView f2618e;
        int f2619f;
        final /* synthetic */ ImageGalleryAlbumAdapter f2620g;

        ViewHolder(ImageGalleryAlbumAdapter imageGalleryAlbumAdapter) {
            this.f2620g = imageGalleryAlbumAdapter;
        }
    }

    public ImageGalleryAlbumAdapter(Context context, LinkedHashMap<String, ImageGalleryAlbumModel> linkedHashMap) {
        this.f2625e = new C01551(this);
        this.f2621a = context;
        this.f2622b = linkedHashMap;
        this.f2623c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.f2622b.size();
    }

    public Object getItem(int i) {
        return this.f2622b.get("" + i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.f2623c.inflate(2130903220, viewGroup, false);
            viewHolder = new ViewHolder(this);
            viewHolder.f2615b = (TextView) view.findViewById(2131755582);
            viewHolder.f2616c = (ImageView) view.findViewById(2131755580);
            viewHolder.f2617d = (ImageView) view.findViewById(2131755579);
            viewHolder.f2618e = (ImageView) view.findViewById(2131755578);
            viewHolder.f2614a = (TextView) view.findViewById(2131755581);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageGalleryAlbumModel imageGalleryAlbumModel = (ImageGalleryAlbumModel) new ArrayList(this.f2622b.values()).get(i);
        viewHolder.f2619f = i;
        viewHolder.f2614a.setText(imageGalleryAlbumModel.f2628c);
        viewHolder.f2615b.setText(Integer.toString(imageGalleryAlbumModel.f2627b));
        ArrayList arrayList = new ArrayList();
        arrayList.add(viewHolder.f2616c);
        arrayList.add(viewHolder.f2617d);
        arrayList.add(viewHolder.f2618e);
        try {
            int i2;
            if (imageGalleryAlbumModel.f2626a.size() > 2) {
                i2 = 3;
            } else {
                i2 = imageGalleryAlbumModel.f2626a.size();
            }
            for (int i3 = 0; i3 < i2; i3++) {
                ((ImageView) arrayList.get(i3)).setImageResource(2130837785);
                ((ImageView) arrayList.get(i3)).setTag(2131296317, Integer.valueOf(i));
                ((ImageView) arrayList.get(i3)).setTag(2131296334, Integer.valueOf(i3));
                ((ImageView) arrayList.get(i3)).setRotation((float) ((Rotation) this.f2625e.get(i % 7)).f2612a[i3]);
                if (imageGalleryAlbumModel.f2626a.size() > 0) {
                    CustomImageLoader.m4009a().m4019a((ImageView) arrayList.get(i3), Uri.decode(Uri.fromFile(new File(((GalleryItemModel) imageGalleryAlbumModel.f2626a.get(i3)).f2575d)).toString()), new C01562(this));
                    Log.d("icon 1 seted", " selected " + i);
                } else {
                    ((ImageView) arrayList.get(i3)).setVisibility(4);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (this.f2621a instanceof ImageGalleryActivity) {
            ((ImageGalleryActivity) this.f2621a).f2608y = viewHolder.f2619f;
            ((ImageGalleryActivity) this.f2621a).m5087b(((ImageGalleryAlbumModel) new ArrayList(this.f2622b.values()).get(viewHolder.f2619f)).f2628c);
        } else if (this.f2621a instanceof VideoGalleryActivity) {
            ((VideoGalleryActivity) this.f2621a).f2654B = viewHolder.f2619f;
            ((VideoGalleryActivity) this.f2621a).m5125b(((ImageGalleryAlbumModel) new ArrayList(this.f2622b.values()).get(viewHolder.f2619f)).f2628c);
        }
    }
}
