package app.galley;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import app.Main;
import app.common.LocalizeActivity;
import app.galley.external.CropSingleImageFragment;
import app.galley.external.CropSingleImageFragment.SingleImageComminucator;
import app.galley.external.GalleryImageViewPagerFragment.GalleryCommunicator;
import app.galley.external.GalleryItemModel;
import app.galley.external.ImageGalleryActivity;
import app.galley.external.PreviewImageCaptureFragment;
import app.galley.external.SystemCropper;
import app.galley.external.VideoGalleryActivity;
import app.galley.internal.PreviewImageHistoryViewer;
import app.galley.internal.PreviewMultipleImageFragment;
import app.galley.internal.PreviewMultipleImageFragment.PreviewComminucator;
import app.galley.internal.PreviewMultipleVideoFragment;
import app.galley.internal.PreviewSingleVideoFragment;
import app.galley.internal.PreviewSingleVideoFragment.SinglePreviewComminucator;
import app.galley.internal.PreviewVideoHistoryViewer;
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class SelectResourceFromSDCardActivity extends LocalizeActivity implements SingleImageComminucator, GalleryCommunicator, PreviewComminucator, SinglePreviewComminucator {
    PreviewImageCaptureFragment f2534k;
    PreviewSingleVideoFragment f2535o;
    PreviewVideoHistoryViewer f2536p;
    SelectedItemGalleryModel f2537q;
    String f2538r;
    private final String f2539s;
    private boolean f2540t;

    public SelectResourceFromSDCardActivity() {
        this.f2539s = "sourceUri";
        this.f2540t = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903239);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (bundle == null) {
            String stringExtra = getIntent().getStringExtra(Action.ATTRIBUTE_NAME);
            if ("BisPhone.ACTION_CROP_FIT_SCREEN".equals(getIntent().getStringExtra("crop_status"))) {
                this.f2540t = true;
            }
            this.f2537q = new SelectedItemGalleryModel();
            Intent intent;
            if ("BisPhone.ACTION_IMAGE_MULTIPLE_PICK".equals(stringExtra)) {
                intent = new Intent(this, ImageGalleryActivity.class);
                intent.putExtra(DataPacketExtension.ELEMENT, this.f2537q);
                intent.putExtra("is_single", false);
                startActivityForResult(intent, 100);
                return;
            } else if ("BisPhone.ACTION_IMAGE_SINGLE_PICK".equals(stringExtra)) {
                this.f2538r = getIntent().getStringExtra("output");
                m5010q();
                return;
            } else if ("BisPhone.ACTION_VIDEO_SINGLE_PICK".equals(stringExtra)) {
                m5011r();
                return;
            } else if ("BisPhone.ACTION_VIDEO_MULTIPLE_PICK".equals(stringExtra)) {
                intent = new Intent(this, VideoGalleryActivity.class);
                intent.putExtra(DataPacketExtension.ELEMENT, this.f2537q);
                intent.putExtra("is_single", false);
                startActivityForResult(intent, 300);
                return;
            } else if ("BisPhone.ACTION_VIEW_IMAGES".equals(stringExtra)) {
                m5008o();
                return;
            } else if ("BisPhone.ACTION_VIEW_VIDEOS".equals(stringExtra)) {
                m5009p();
                return;
            } else if ("BisPhone.ACTION_CROP".equals(stringExtra)) {
                String stringExtra2 = getIntent().getStringExtra(DataPacketExtension.ELEMENT);
                this.f2538r = getIntent().getStringExtra("output");
                this.f2537q = new SelectedItemGalleryModel();
                this.f2537q.m5028a().put(Long.valueOf(0), new GalleryItemModel(-1, stringExtra2, "", null, 0));
                m5012s();
                return;
            } else if ("BisPhone.ACTION_IMAGE_CAPTURE".equals(stringExtra)) {
                this.f2538r = getIntent().getStringExtra(DataPacketExtension.ELEMENT);
                this.f2534k = new PreviewImageCaptureFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString(DataPacketExtension.ELEMENT, this.f2538r);
                this.f2534k.m224g(bundle2);
                getSupportFragmentManager().m353a().m115b(2131755221, this.f2534k).m114b();
                return;
            } else {
                return;
            }
        }
        this.f2537q = (SelectedItemGalleryModel) bundle.getParcelable("temp");
        this.f2538r = bundle.getString("sourceUri");
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("temp", this.f2537q);
        bundle.putString("sourceUri", this.f2538r);
    }

    private void m5008o() {
        Fragment previewImageHistoryViewer = new PreviewImageHistoryViewer();
        Bundle bundle = new Bundle();
        bundle.putString("message_jid", getIntent().getStringExtra("message_jid"));
        bundle.putBoolean("is_message_Incoming", getIntent().getBooleanExtra("is_message_Incoming", false));
        bundle.putString("message_id", getIntent().getStringExtra("message_id"));
        bundle.putLong("first_message_timestamp", getIntent().getLongExtra("first_message_timestamp", 0));
        bundle.putInt("message_type", getIntent().getIntExtra("message_type", 0));
        previewImageHistoryViewer.m224g(bundle);
        getSupportFragmentManager().m353a().m115b(2131755221, previewImageHistoryViewer).m114b();
    }

    private void m5009p() {
        this.f2536p = new PreviewVideoHistoryViewer();
        Bundle bundle = new Bundle();
        bundle.putString(DataPacketExtension.ELEMENT, getIntent().getStringExtra(DataPacketExtension.ELEMENT));
        bundle.putString("video_caption", getIntent().getStringExtra("video_caption"));
        this.f2536p.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(2131755221, this.f2536p).commit();
    }

    private void m5010q() {
        Intent intent = new Intent(this, ImageGalleryActivity.class);
        intent.putExtra(DataPacketExtension.ELEMENT, this.f2537q);
        intent.putExtra("is_single", true);
        startActivityForResult(intent, 200);
    }

    private void m5011r() {
        Intent intent = new Intent(this, VideoGalleryActivity.class);
        intent.putExtra(DataPacketExtension.ELEMENT, this.f2537q);
        intent.putExtra("is_single", true);
        startActivityForResult(intent, 300);
    }

    protected void onPause() {
        super.onPause();
        if (this.f2535o != null) {
            this.f2535o.m5192c();
        } else if (this.f2536p != null) {
            this.f2536p.m5205c();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == PacketWriter.QUEUE_SIZE) {
            if (i2 == 0) {
                finish();
            } else {
                m5024m();
            }
        }
        if (this.f2537q == null || this.f2537q.m5028a() == null) {
            finish();
        } else if (i == 100) {
            if (i2 == -1) {
                this.f2537q = (SelectedItemGalleryModel) intent.getParcelableExtra(DataPacketExtension.ELEMENT);
                m5014u();
            } else if (this.f2537q.m5028a().size() > 0) {
                m5014u();
            } else {
                finish();
            }
        } else if (i == 200 && i2 == -1) {
            this.f2537q = (SelectedItemGalleryModel) intent.getParcelableExtra(DataPacketExtension.ELEMENT);
            m5013t();
        } else if (i == 600 && i2 == -1) {
            this.f2534k.m5106N();
        } else if (i == 200) {
            finish();
        } else if (i == 300) {
            if (i2 == -1) {
                this.f2537q = (SelectedItemGalleryModel) intent.getParcelableExtra(DataPacketExtension.ELEMENT);
                m5016w();
            } else {
                finish();
            }
        } else if (i == 400) {
            if (i2 == -1) {
                this.f2537q = (SelectedItemGalleryModel) intent.getParcelableExtra(DataPacketExtension.ELEMENT);
                m5015v();
            } else if (this.f2537q == null || this.f2537q.m5028a().size() <= 0) {
                finish();
            } else {
                m5015v();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private void m5012s() {
        android.app.Fragment cropSingleImageFragment = new CropSingleImageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DataPacketExtension.ELEMENT, this.f2537q);
        bundle.putString("output", this.f2538r);
        cropSingleImageFragment.setArguments(bundle);
        cropSingleImageFragment.m5034a((SingleImageComminucator) this);
        getFragmentManager().beginTransaction().replace(2131755221, cropSingleImageFragment).commit();
    }

    private void m5013t() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        String str = "";
        for (Entry value : this.f2537q.m5028a().entrySet()) {
            str = ((GalleryItemModel) value.getValue()).m5063d();
        }
        File file = new File(str);
        if (file.exists()) {
            intent.setData(Uri.fromFile(file));
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            if (getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
                m5012s();
            } else {
                SystemCropper.m5115a((Activity) this, str, this.f2538r, (int) PacketWriter.QUEUE_SIZE, this.f2540t);
            }
        }
    }

    private void m5014u() {
        Fragment previewMultipleImageFragment = new PreviewMultipleImageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DataPacketExtension.ELEMENT, this.f2537q);
        previewMultipleImageFragment.m224g(bundle);
        getSupportFragmentManager().m353a().m115b(2131755221, previewMultipleImageFragment).m114b();
    }

    private void m5015v() {
        Fragment previewMultipleVideoFragment = new PreviewMultipleVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DataPacketExtension.ELEMENT, this.f2537q);
        previewMultipleVideoFragment.m224g(bundle);
        getSupportFragmentManager().m353a().m115b(2131755221, previewMultipleVideoFragment).m114b();
    }

    private void m5016w() {
        this.f2535o = new PreviewSingleVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DataPacketExtension.ELEMENT, this.f2537q);
        this.f2535o.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(2131755221, this.f2535o).commit();
    }

    public void m5021j() {
        this.f2537q = new SelectedItemGalleryModel();
        m5011r();
    }

    public void m5022k() {
        this.f2537q = new SelectedItemGalleryModel();
        m5010q();
    }

    public void m5023l() {
        setResult(0, getIntent());
        finish();
    }

    public void m5024m() {
        String str = "";
        if (this.f2537q.m5028a() != null && this.f2537q.m5028a().size() > 0) {
            for (Entry value : this.f2537q.m5028a().entrySet()) {
                str = ((GalleryItemModel) value.getValue()).m5063d();
            }
        }
        Intent intent = getIntent();
        intent.putExtra(DataPacketExtension.ELEMENT, str);
        setResult(-1, intent);
        finish();
    }

    public void m5025n() {
        if (this.f2537q.m5028a() == null || this.f2537q.m5028a().size() <= 0) {
            Main.m3905a(getString(2131296844));
        } else if (this.f2537q == null || this.f2537q.m5028a() == null || this.f2537q.m5028a().size() <= 0) {
            Main.m3905a(getString(2131296843));
        } else {
            ArrayList arrayList = new ArrayList();
            for (Entry value : this.f2537q.m5028a().entrySet()) {
                arrayList.add(value.getValue());
            }
            Intent intent = getIntent();
            intent.putParcelableArrayListExtra(DataPacketExtension.ELEMENT, arrayList);
            setResult(-1, intent);
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void m5018a(ArrayList<GalleryItemModel> arrayList) {
        Intent intent = getIntent();
        intent.putParcelableArrayListExtra(DataPacketExtension.ELEMENT, arrayList);
        setResult(-1, intent);
        finish();
    }

    public MessagePreview m5019b(int i) {
        return null;
    }

    public void m5017a(long j, String str) {
    }

    public void m5020b(long j, String str) {
        ((GalleryItemModel) this.f2537q.m5028a().get(Long.valueOf(j))).m5060a(str);
    }
}
