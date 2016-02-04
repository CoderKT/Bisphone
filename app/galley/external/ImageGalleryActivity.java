package app.galley.external;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.RecyclerListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import app.common.LocalizeActivity;
import app.galley.SelectedItemGalleryModel;
import app.util.DeviceUtils;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class ImageGalleryActivity extends LocalizeActivity {
    String f2591A;
    String f2592B;
    int f2593C;
    long f2594D;
    private boolean f2595E;
    private Callback f2596F;
    GridView f2597k;
    GridView f2598o;
    HashMap<String, List<GalleryItemModel>> f2599p;
    List<GalleryItemModel> f2600q;
    boolean f2601r;
    boolean f2602s;
    public ActionMode f2603t;
    boolean f2604u;
    SelectedItemGalleryModel f2605v;
    ImageGalleryAlbumAdapter f2606w;
    ImageGalleryItemAdapter f2607x;
    int f2608y;
    int f2609z;

    /* renamed from: app.galley.external.ImageGalleryActivity.1 */
    class C01521 implements RecyclerListener {
        final /* synthetic */ ImageGalleryActivity f2588a;

        C01521(ImageGalleryActivity imageGalleryActivity) {
            this.f2588a = imageGalleryActivity;
        }

        public void onMovedToScrapHeap(View view) {
            ImageView imageView = (ImageView) view.findViewById(2131755579);
            ImageView imageView2 = (ImageView) view.findViewById(2131755578);
            this.f2588a.m5077a((ImageView) view.findViewById(2131755580));
            this.f2588a.m5077a(imageView);
            this.f2588a.m5077a(imageView2);
        }
    }

    /* renamed from: app.galley.external.ImageGalleryActivity.2 */
    class C01532 implements RecyclerListener {
        final /* synthetic */ ImageGalleryActivity f2589a;

        C01532(ImageGalleryActivity imageGalleryActivity) {
            this.f2589a = imageGalleryActivity;
        }

        public void onMovedToScrapHeap(View view) {
            this.f2589a.m5077a((ImageView) view.findViewById(2131755583));
        }
    }

    /* renamed from: app.galley.external.ImageGalleryActivity.3 */
    class C01543 implements Callback {
        final /* synthetic */ ImageGalleryActivity f2590a;

        C01543(ImageGalleryActivity imageGalleryActivity) {
            this.f2590a = imageGalleryActivity;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.f2590a.f2602s = true;
            actionMode.getMenuInflater().inflate(2131820569, menu);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Intent intent = this.f2590a.getIntent();
            intent.putExtra(DataPacketExtension.ELEMENT, this.f2590a.f2605v);
            this.f2590a.setResult(-1, intent);
            this.f2590a.finish();
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.f2590a.f2602s = false;
            this.f2590a.m5084o();
        }
    }

    public ImageGalleryActivity() {
        this.f2603t = null;
        this.f2608y = 0;
        this.f2609z = 0;
        this.f2593C = 0;
        this.f2596F = new C01543(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903153);
        if (PermissionUtil.m7044a(PermissionType.storage)) {
            if (getActionBar() != null) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }
            this.f2605v = (SelectedItemGalleryModel) getIntent().getParcelableExtra(DataPacketExtension.ELEMENT);
            this.f2604u = getIntent().getBooleanExtra("edit", false);
            this.f2595E = getIntent().getBooleanExtra("is_single", false);
            this.f2598o = (GridView) findViewById(2131755446);
            this.f2597k = (GridView) findViewById(2131755447);
            m5081l();
            m5080k();
            this.f2598o.setRecyclerListener(new C01521(this));
            this.f2597k.setRecyclerListener(new C01532(this));
            if (this.f2605v == null) {
                this.f2605v = new SelectedItemGalleryModel();
            }
            if (this.f2605v.m5028a().size() > 0) {
                m5088j();
                this.f2601r = true;
                return;
            }
            return;
        }
        finish();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m5083n();
    }

    private void m5077a(ImageView imageView) {
        if (((BitmapDrawable) imageView.getDrawable()) != null) {
            ((BitmapDrawable) imageView.getDrawable()).setCallback(null);
        }
    }

    private void m5080k() {
        this.f2598o.setVisibility(0);
        this.f2597k.setVisibility(8);
        this.f2601r = true;
        this.f2598o.setAdapter(this.f2606w);
        this.f2598o.smoothScrollToPosition(this.f2608y);
    }

    private void m5081l() {
        this.f2598o.setVisibility(0);
        this.f2597k.setVisibility(8);
        this.f2599p = new HashMap();
        String[] strArr = new String[]{"_id", "bucket_display_name", "bucket_id", "datetaken", "_data"};
        Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, null, null, "datetaken DESC");
        if (query == null) {
            m5082m();
        } else if (query.moveToFirst()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("All Images", new ImageGalleryAlbumModel(new ArrayList(), this.f2593C, "All Images"));
            int columnIndex = query.getColumnIndex("bucket_display_name");
            do {
                this.f2591A = query.getString(columnIndex);
                this.f2592B = query.getString(query.getColumnIndex("_data"));
                this.f2594D = query.getLong(query.getColumnIndex("bucket_id"));
                GalleryItemModel galleryItemModel = new GalleryItemModel(this.f2594D, this.f2592B, "", null, 0);
                ImageGalleryAlbumModel imageGalleryAlbumModel = (ImageGalleryAlbumModel) linkedHashMap.get("All Images");
                imageGalleryAlbumModel.f2626a.add(galleryItemModel);
                linkedHashMap.put("All Images", imageGalleryAlbumModel);
                if (linkedHashMap.get(this.f2591A) == null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(galleryItemModel);
                    linkedHashMap.put(this.f2591A, new ImageGalleryAlbumModel(arrayList, 1, this.f2591A));
                } else {
                    imageGalleryAlbumModel = (ImageGalleryAlbumModel) linkedHashMap.get(this.f2591A);
                    imageGalleryAlbumModel.f2626a.add(galleryItemModel);
                    imageGalleryAlbumModel.f2627b++;
                    linkedHashMap.put(this.f2591A, imageGalleryAlbumModel);
                }
                this.f2593C++;
            } while (query.moveToNext());
            ((ImageGalleryAlbumModel) linkedHashMap.get("All Images")).f2627b = this.f2593C;
            this.f2606w = new ImageGalleryAlbumAdapter(this, linkedHashMap);
            query.close();
        } else {
            m5082m();
            query.close();
        }
    }

    private void m5082m() {
        findViewById(2131755448).setVisibility(0);
    }

    public void m5087b(String str) {
        String[] strArr;
        String str2;
        this.f2597k.setVisibility(0);
        this.f2598o.setVisibility(8);
        String[] strArr2 = new String[]{"_id", "datetaken", "_data"};
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        String str3 = "datetaken DESC";
        if (str.equalsIgnoreCase("All Images")) {
            strArr = null;
            str2 = null;
        } else {
            str2 = "bucket_display_name=?";
            strArr = new String[]{str};
        }
        Cursor query = getContentResolver().query(uri, strArr2, str2, strArr, str3);
        this.f2600q = new ArrayList();
        if (query == null || !query.moveToFirst()) {
            m5083n();
            this.f2601r = false;
        }
        do {
            String string = query.getString(query.getColumnIndex("_data"));
            this.f2600q.add(new GalleryItemModel(query.getLong(query.getColumnIndex("_id")), string, "", null, 0));
        } while (query.moveToNext());
        m5083n();
        this.f2601r = false;
    }

    private void m5083n() {
        this.f2609z = DeviceUtils.m7010a(this, 4, 0, 0);
        if (this.f2607x == null) {
            this.f2607x = new ImageGalleryItemAdapter(this, this.f2600q, this.f2605v, this.f2609z, this.f2595E);
            this.f2597k.setAdapter(this.f2607x);
            return;
        }
        this.f2607x.m5102a(this.f2600q);
        this.f2607x.m5101a(this.f2609z);
        this.f2607x.notifyDataSetChanged();
    }

    public void m5088j() {
        if (this.f2603t == null) {
            this.f2603t = startActionMode(this.f2596F);
        }
        this.f2603t.setTitle(this.f2605v.m5028a().size() + " " + getString(2131296533));
    }

    public void m5086a(MediaItemViewHolder mediaItemViewHolder) {
        if (this.f2595E) {
            this.f2605v.m5028a().put(Long.valueOf(((GalleryItemModel) this.f2600q.get(mediaItemViewHolder.f2641d)).f2572a), this.f2600q.get(mediaItemViewHolder.f2641d));
            Intent intent = new Intent();
            intent.putExtra(DataPacketExtension.ELEMENT, this.f2605v);
            setResult(-1, intent);
            finish();
            return;
        }
        if (this.f2605v.m5028a().get(Long.valueOf(((GalleryItemModel) this.f2600q.get(mediaItemViewHolder.f2641d)).f2572a)) != null) {
            this.f2605v.m5028a().remove(Long.valueOf(((GalleryItemModel) this.f2600q.get(mediaItemViewHolder.f2641d)).f2572a));
            mediaItemViewHolder.f2639b.setVisibility(4);
            mediaItemViewHolder.f2640c.setVisibility(4);
        } else if (this.f2605v.m5028a().size() < 8) {
            this.f2605v.m5028a().put(Long.valueOf(((GalleryItemModel) this.f2600q.get(mediaItemViewHolder.f2641d)).f2572a), this.f2600q.get(mediaItemViewHolder.f2641d));
            mediaItemViewHolder.f2639b.setVisibility(0);
            mediaItemViewHolder.f2640c.setVisibility(0);
        } else {
            Toast.makeText(this, getString(2131296532), 1).show();
        }
        m5088j();
    }

    private void m5084o() {
        this.f2603t = null;
        this.f2605v.m5028a().clear();
        if (this.f2607x != null) {
            this.f2607x.notifyDataSetChanged();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && this.f2601r) {
            m5085p();
        } else if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && this.f2606w != null) {
            m5080k();
            return true;
        } else if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            finish();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void m5085p() {
        if (this.f2602s && this.f2604u) {
            setResult(0, new Intent());
            finish();
        } else if (this.f2602s) {
            this.f2603t.finish();
        } else {
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (!this.f2601r) {
                    m5080k();
                    break;
                }
                m5085p();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
