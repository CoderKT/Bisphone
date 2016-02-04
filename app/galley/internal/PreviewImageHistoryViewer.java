package app.galley.internal;

import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import app.Main;
import app.common.entity.HistoryEntity.Type;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.galley.MessagePreview;
import app.galley.external.GalleryImageViewPagerAdapter;
import app.galley.external.GalleryImageViewPagerFragment.GalleryCommunicator;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.Utils;
import app.util.XMPPUtils;
import app.xmpp.JabberId;
import java.io.File;
import java.io.IOException;

public class PreviewImageHistoryViewer extends Fragment implements GalleryCommunicator {
    private ViewPager f2705a;
    private ImagePreviewModel f2706b;

    /* renamed from: app.galley.internal.PreviewImageHistoryViewer.1 */
    class C01641 implements OnPageChangeListener {
        final /* synthetic */ GalleryImageViewPagerAdapter f2703a;
        final /* synthetic */ PreviewImageHistoryViewer f2704b;

        C01641(PreviewImageHistoryViewer previewImageHistoryViewer, GalleryImageViewPagerAdapter galleryImageViewPagerAdapter) {
            this.f2704b = previewImageHistoryViewer;
            this.f2703a = galleryImageViewPagerAdapter;
        }

        public void m5143a(int i, float f, int i2) {
        }

        public void m5142a(int i) {
            if (this.f2704b.m227i().getActionBar() != null) {
                this.f2704b.m227i().getActionBar().setTitle((i + 1) + " " + this.f2704b.a_(2131296785) + " " + this.f2703a.getCount());
            }
        }

        public void m5144b(int i) {
        }
    }

    public View m5147a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903076, viewGroup, false);
    }

    public void m5150a(View view, Bundle bundle) {
        super.m199a(view, bundle);
        m215d(true);
        m5145a(view);
    }

    private void m5145a(View view) {
        this.f2705a = (ViewPager) view.findViewById(2131755174);
        String string = m223g().getString("message_jid");
        if (string == null) {
            m227i().finish();
            return;
        }
        JabberId a;
        if (string.contains("@")) {
            a = JabberId.m7386a(string);
        } else {
            a = new JabberId(string, "bisphone.com", null);
        }
        if (a == null) {
            m227i().finish();
            return;
        }
        if (XMPPUtils.m7095c(a)) {
            this.f2706b = HistoryChannelDataSource.m4668a(a.m7391e(), m223g().getString("message_id"), m223g().getLong("first_message_timestamp"));
        } else if (XMPPUtils.m7094b(a)) {
            this.f2706b = HistoryGroupDataSource.m4692a(a.m7391e(), m223g().getString("message_id"), m223g().getLong("first_message_timestamp"));
        } else if (XMPPUtils.m7096d(a)) {
            this.f2706b = HistoryNormalMessageDataSource.m4721a(a.m7387a(), m223g().getString("message_id"), m223g().getLong("first_message_timestamp"));
        } else {
            throw new RuntimeException("invalid jabber id, your jabber id is " + a + " and it's seems not valid");
        }
        PagerAdapter galleryImageViewPagerAdapter = new GalleryImageViewPagerAdapter(m233l(), this, this.f2706b.m5141b(), true, m223g().getInt("message_type"), m223g().getBoolean("is_message_Incoming"));
        this.f2705a.setAdapter(galleryImageViewPagerAdapter);
        this.f2705a.setCurrentItem(this.f2706b.m5138a());
        this.f2705a.setOnPageChangeListener(new C01641(this, galleryImageViewPagerAdapter));
        if (m227i().getActionBar() != null) {
            m227i().getActionBar().setTitle((this.f2706b.m5138a() + 1) + " " + a_(2131296785) + " " + galleryImageViewPagerAdapter.getCount());
        }
    }

    public MessagePreview m5152b(int i) {
        return (MessagePreview) this.f2706b.m5141b().get(i);
    }

    public void m5148a(long j, String str) {
    }

    public void m5149a(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820550, menu);
        super.m198a(menu, menuInflater);
    }

    public boolean m5151a(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755635:
                if (this.f2706b.m5141b() != null && this.f2706b.m5141b().size() != 0) {
                    if (PermissionUtil.m7044a(PermissionType.storage)) {
                        try {
                            String str = Storage.m6956j() + File.separator + ((MessagePreview) this.f2706b.m5141b().get(this.f2705a.getCurrentItem())).m4997a() + ".jpg";
                            Main.f1926a.m5683d("image path is " + str);
                            if (!m5146a(str)) {
                                Log.d("pager current item", this.f2705a.getCurrentItem() + "");
                                if (!FileUtil.m7023a(Utils.m7079a(((MessagePreview) this.f2706b.m5141b().get(this.f2705a.getCurrentItem())).m4997a(), Type.PHOTO), str, false)) {
                                    Main.m3905a(a_(2131296841));
                                    break;
                                }
                                MediaScannerConnection.scanFile(m227i(), new String[]{str}, null, null);
                                Main.m3905a(a_(2131296842));
                                break;
                            }
                            Main.m3905a(a_(2131296834));
                            break;
                        } catch (StorageException e) {
                            Storage.m6945c(m225h());
                            break;
                        } catch (IOException e2) {
                            Main.m3905a(a_(2131296841));
                            break;
                        }
                    }
                    PermissionUtil.m7042a(m227i(), PermissionType.storage, 2);
                    return super.m201a(menuItem);
                }
                return super.m201a(menuItem);
                break;
        }
        return super.m201a(menuItem);
    }

    private boolean m5146a(String str) {
        return new File(str).exists();
    }
}
