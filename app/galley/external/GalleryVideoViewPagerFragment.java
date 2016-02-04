package app.galley.external;

import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import app.Main;
import app.common.CustomVideoView;
import app.common.entity.HistoryEntity.Type;
import app.util.Background;
import app.util.Utils;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class GalleryVideoViewPagerFragment extends Fragment {
    GalleryComminucator f2584a;
    MediaController f2585b;
    CustomVideoView f2586c;
    Handler f2587d;

    /* renamed from: app.galley.external.GalleryVideoViewPagerFragment.1 */
    class C01511 implements Runnable {
        final /* synthetic */ GalleryVideoViewPagerFragment f2583a;

        /* renamed from: app.galley.external.GalleryVideoViewPagerFragment.1.1 */
        class C01491 implements OnPreparedListener {
            final /* synthetic */ C01511 f2581a;

            C01491(C01511 c01511) {
                this.f2581a = c01511;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.f2581a.f2583a.f2585b.show(0);
            }
        }

        /* renamed from: app.galley.external.GalleryVideoViewPagerFragment.1.2 */
        class C01502 implements OnCompletionListener {
            final /* synthetic */ C01511 f2582a;

            C01502(C01511 c01511) {
                this.f2582a = c01511;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                this.f2582a.f2583a.f2585b.show(0);
            }
        }

        C01511(GalleryVideoViewPagerFragment galleryVideoViewPagerFragment) {
            this.f2583a = galleryVideoViewPagerFragment;
        }

        public void run() {
            this.f2583a.f2585b = new MediaController(this.f2583a.m227i());
            this.f2583a.f2585b.setMediaPlayer(this.f2583a.f2586c);
            this.f2583a.f2585b.setAnchorView(this.f2583a.f2586c);
            this.f2583a.f2586c.setMediaController(this.f2583a.f2585b);
            this.f2583a.f2586c.setOnPreparedListener(new C01491(this));
            this.f2583a.f2586c.setOnCompletionListener(new C01502(this));
            this.f2583a.m5071a();
        }
    }

    public interface GalleryComminucator {
        String m5068a(int i);
    }

    public void m5075a(GalleryComminucator galleryComminucator) {
        this.f2584a = galleryComminucator;
    }

    public static GalleryVideoViewPagerFragment m5070a(int i, GalleryComminucator galleryComminucator) {
        GalleryVideoViewPagerFragment galleryVideoViewPagerFragment = new GalleryVideoViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DataPacketExtension.ELEMENT, i);
        galleryVideoViewPagerFragment.m224g(bundle);
        galleryVideoViewPagerFragment.m5075a(galleryComminucator);
        return galleryVideoViewPagerFragment;
    }

    public View m5073a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903145, viewGroup, false);
    }

    public void m5074a(View view, Bundle bundle) {
        super.m199a(view, bundle);
        this.f2586c = (CustomVideoView) view.findViewById(2131755409);
        m5069M();
    }

    public void m5076e(boolean z) {
        super.m219e(z);
        if (z) {
            m5069M();
        }
    }

    private void m5071a() {
        try {
            this.f2586c.setVideoURI(Uri.parse(Utils.m7079a(this.f2584a.m5068a(m223g().getInt(DataPacketExtension.ELEMENT)), Type.VIDEO)));
            this.f2586c.requestFocus();
            Background.m6968a(new BitmapDrawable(m229j(), ThumbnailUtils.createVideoThumbnail(Utils.m7079a(this.f2584a.m5068a(m223g().getInt(DataPacketExtension.ELEMENT)), Type.VIDEO), 1)), this.f2586c);
            this.f2586c.pause();
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
        }
    }

    private void m5069M() {
        this.f2587d = new Handler();
        this.f2587d.postDelayed(new C01511(this), 1000);
    }
}
