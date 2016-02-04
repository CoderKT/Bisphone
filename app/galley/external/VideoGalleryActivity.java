package app.galley.external;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Video.Media;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import app.Main;
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

public class VideoGalleryActivity extends LocalizeActivity {
    VideoGalleryItemAdapter f2653A;
    int f2654B;
    String f2655C;
    String f2656D;
    int f2657E;
    long f2658F;
    private Callback f2659G;
    GridView f2660k;
    GridView f2661o;
    HashMap<String, List<GalleryItemModel>> f2662p;
    List<GalleryItemModel> f2663q;
    boolean f2664r;
    boolean f2665s;
    public ActionMode f2666t;
    boolean f2667u;
    boolean f2668v;
    int f2669w;
    SelectedItemGalleryModel f2670x;
    LinkedHashMap<String, ImageGalleryAlbumModel> f2671y;
    VideoGalleryAlbumAdapter f2672z;

    /* renamed from: app.galley.external.VideoGalleryActivity.1 */
    class C01601 implements Callback {
        final /* synthetic */ VideoGalleryActivity f2652a;

        C01601(VideoGalleryActivity videoGalleryActivity) {
            this.f2652a = videoGalleryActivity;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            this.f2652a.f2665s = true;
            actionMode.getMenuInflater().inflate(2131820569, menu);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            Intent intent = new Intent();
            intent.putExtra(DataPacketExtension.ELEMENT, this.f2652a.f2670x);
            this.f2652a.setResult(-1, intent);
            this.f2652a.finish();
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.f2652a.f2665s = false;
            this.f2652a.m5122o();
        }
    }

    public VideoGalleryActivity() {
        this.f2666t = null;
        this.f2669w = 0;
        this.f2654B = 0;
        this.f2657E = 0;
        this.f2659G = new C01601(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903153);
        if (PermissionUtil.m7044a(PermissionType.storage)) {
            if (getActionBar() != null) {
                getActionBar().setDisplayHomeAsUpEnabled(true);
            }
            this.f2670x = (SelectedItemGalleryModel) getIntent().getParcelableExtra(DataPacketExtension.ELEMENT);
            this.f2668v = getIntent().getBooleanExtra("edit", false);
            this.f2667u = getIntent().getBooleanExtra("is_single", false);
            this.f2661o = (GridView) findViewById(2131755446);
            this.f2660k = (GridView) findViewById(2131755447);
            m5119l();
            m5118k();
            if (this.f2670x.m5028a().size() > 0) {
                m5126j();
                this.f2664r = true;
                return;
            }
            return;
        }
        finish();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m5121n();
    }

    private void m5118k() {
        this.f2661o.setVisibility(0);
        this.f2660k.setVisibility(8);
        this.f2664r = true;
        this.f2661o.setAdapter(this.f2672z);
        this.f2661o.smoothScrollToPosition(this.f2654B);
    }

    private void m5119l() {
        this.f2661o.setVisibility(0);
        this.f2660k.setVisibility(8);
        this.f2662p = new HashMap();
        String[] strArr = new String[]{"_id", "bucket_display_name", "bucket_id", "datetaken", "_data"};
        Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, null, null, "datetaken DESC");
        this.f2671y = new LinkedHashMap();
        this.f2671y.put("All Video", new ImageGalleryAlbumModel(new ArrayList(), this.f2657E, "All Video"));
        if (query == null) {
            m5120m();
        } else if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("bucket_display_name");
            do {
                this.f2655C = query.getString(columnIndex);
                this.f2656D = query.getString(query.getColumnIndex("_data"));
                this.f2658F = query.getLong(query.getColumnIndex("bucket_id"));
                GalleryItemModel galleryItemModel = new GalleryItemModel(this.f2658F, this.f2656D, "", null, 0);
                ImageGalleryAlbumModel imageGalleryAlbumModel = (ImageGalleryAlbumModel) this.f2671y.get("All Video");
                imageGalleryAlbumModel.f2626a.add(galleryItemModel);
                this.f2671y.put("All Video", imageGalleryAlbumModel);
                if (this.f2671y.get(this.f2655C) == null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(galleryItemModel);
                    this.f2671y.put(this.f2655C, new ImageGalleryAlbumModel(arrayList, 1, this.f2655C));
                } else {
                    imageGalleryAlbumModel = (ImageGalleryAlbumModel) this.f2671y.get(this.f2655C);
                    imageGalleryAlbumModel.f2626a.add(galleryItemModel);
                    imageGalleryAlbumModel.f2627b++;
                    this.f2671y.put(this.f2655C, imageGalleryAlbumModel);
                }
                this.f2657E++;
            } while (query.moveToNext());
            ((ImageGalleryAlbumModel) this.f2671y.get("All Video")).f2627b = this.f2657E;
            this.f2672z = new VideoGalleryAlbumAdapter(this, this.f2671y);
        } else {
            m5120m();
            query.close();
        }
    }

    private void m5120m() {
        findViewById(2131755448).setVisibility(0);
    }

    public void m5125b(String str) {
        String[] strArr;
        String str2;
        this.f2660k.setVisibility(0);
        this.f2661o.setVisibility(8);
        String[] strArr2 = new String[]{"_id", "datetaken", "_data"};
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        String str3 = "datetaken DESC";
        if (str.equalsIgnoreCase("All Video")) {
            strArr = null;
            str2 = null;
        } else {
            str2 = "bucket_display_name=?";
            strArr = new String[]{str};
        }
        Cursor query = getContentResolver().query(uri, strArr2, str2, strArr, str3);
        this.f2663q = new ArrayList();
        if (query.moveToFirst()) {
            do {
                this.f2663q.add(new GalleryItemModel(query.getLong(query.getColumnIndex("_id")), query.getString(query.getColumnIndex("_data")), "", null, 0));
            } while (query.moveToNext());
        }
        m5121n();
        this.f2664r = false;
    }

    private void m5121n() {
        this.f2669w = DeviceUtils.m7010a(this, 4, 0, 0);
        if (this.f2653A == null) {
            this.f2653A = new VideoGalleryItemAdapter(this, this.f2663q, this.f2670x, this.f2669w, this.f2667u);
            this.f2660k.setAdapter(this.f2653A);
            return;
        }
        this.f2653A.m5136a(this.f2663q);
        this.f2653A.m5135a(this.f2669w);
        this.f2653A.notifyDataSetChanged();
    }

    public void m5126j() {
        if (this.f2666t == null) {
            this.f2666t = startActionMode(this.f2659G);
        }
        this.f2666t.setTitle(this.f2670x.m5028a().size() + "");
    }

    public void m5124b(int i) {
        if (this.f2667u) {
            this.f2670x.m5028a().put(Long.valueOf(((GalleryItemModel) this.f2663q.get(i)).f2572a), this.f2663q.get(i));
            Intent intent = new Intent();
            intent.putExtra(DataPacketExtension.ELEMENT, this.f2670x);
            setResult(-1, intent);
            finish();
            return;
        }
        if (this.f2670x.m5028a().get(Long.valueOf(((GalleryItemModel) this.f2663q.get(i)).f2572a)) != null) {
            this.f2670x.m5028a().remove(Long.valueOf(((GalleryItemModel) this.f2663q.get(i)).f2572a));
        } else if (this.f2670x.m5028a().size() < 8) {
            this.f2670x.m5028a().put(Long.valueOf(((GalleryItemModel) this.f2663q.get(i)).f2572a), this.f2663q.get(i));
        } else {
            Main.m3905a(getString(2131296532));
        }
        this.f2653A.notifyDataSetChanged();
        m5126j();
    }

    private void m5122o() {
        this.f2666t = null;
        this.f2670x.m5028a().clear();
        if (this.f2653A != null) {
            this.f2653A.notifyDataSetChanged();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && this.f2664r) {
            m5123p();
        } else if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            m5118k();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void m5123p() {
        if ((this.f2665s && this.f2668v) || this.f2670x.m5028a().size() == 0) {
            setResult(0, new Intent());
            finish();
        } else if (this.f2665s) {
            this.f2666t.finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                if (!this.f2664r) {
                    m5118k();
                    break;
                }
                m5123p();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
