package app.galley.internal;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import app.Main;
import app.common.CustomVideoView;
import app.common.CustomVideoView.VideoStateChangeListener;
import app.common.entity.HistoryEntity.Type;
import app.logger.HandledException;
import app.messaging.emoji.EmojiHandlerCode;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.Background;
import app.util.FileUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.Utils;
import com.crashlytics.android.Crashlytics;
import java.io.File;
import java.io.IOException;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class PreviewVideoHistoryViewer extends Fragment implements OnClickListener, OnTouchListener {
    CustomVideoView f2754a;
    SeekBar f2755b;
    Handler f2756c;
    String f2757d;
    String f2758e;
    LinearLayout f2759f;
    TextView f2760g;
    ImageView f2761h;
    String f2762i;
    boolean f2763j;
    boolean f2764k;

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.1 */
    class C01761 implements OnSeekBarChangeListener {
        final /* synthetic */ PreviewVideoHistoryViewer f2745a;

        C01761(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2745a = previewVideoHistoryViewer;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                this.f2745a.f2754a.seekTo(i);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.f2745a.f2763j = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            this.f2745a.f2763j = false;
            this.f2745a.m5201f();
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.2 */
    class C01772 implements OnPreparedListener {
        final /* synthetic */ PreviewVideoHistoryViewer f2746a;

        C01772(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2746a = previewVideoHistoryViewer;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.f2746a.f2755b.setMax(mediaPlayer.getDuration());
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.3 */
    class C01783 implements OnCompletionListener {
        final /* synthetic */ PreviewVideoHistoryViewer f2747a;

        C01783(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2747a = previewVideoHistoryViewer;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.f2747a.f2755b.setProgress(0);
            this.f2747a.f2754a.seekTo(0);
            this.f2747a.f2764k = true;
            this.f2747a.f2761h.setVisibility(0);
            this.f2747a.m5203a();
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.4 */
    class C01794 implements VideoStateChangeListener {
        final /* synthetic */ PreviewVideoHistoryViewer f2748a;

        C01794(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2748a = previewVideoHistoryViewer;
        }

        public void m5193a() {
            Background.m6968a(null, this.f2748a.f2754a);
        }

        public void m5194b() {
        }

        public void m5195c() {
        }

        public void m5196d() {
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.5 */
    class C01815 implements Runnable {
        final /* synthetic */ PreviewVideoHistoryViewer f2750a;

        /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.5.1 */
        class C01801 implements Runnable {
            final /* synthetic */ C01815 f2749a;

            C01801(C01815 c01815) {
                this.f2749a = c01815;
            }

            public void run() {
                if (!this.f2749a.f2750a.f2763j) {
                    this.f2749a.f2750a.f2755b.setProgress(this.f2749a.f2750a.f2755b.getProgress() + 100);
                }
            }
        }

        C01815(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2750a = previewVideoHistoryViewer;
        }

        public void run() {
            this.f2750a.f2755b.post(new C01801(this));
            if (!this.f2750a.f2763j && !this.f2750a.f2764k) {
                if (!this.f2750a.isAdded() || (this.f2750a.f2755b.getProgress() >= this.f2750a.f2754a.getDuration() && this.f2750a.f2754a.getDuration() > 0)) {
                    this.f2750a.f2755b.setProgress(this.f2750a.f2755b.getMax());
                } else {
                    this.f2750a.f2756c.postDelayed(this, 100);
                }
            }
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.6 */
    class C01826 implements AnimationListener {
        final /* synthetic */ PreviewVideoHistoryViewer f2751a;

        C01826(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2751a = previewVideoHistoryViewer;
        }

        public void onAnimationStart(Animation animation) {
            this.f2751a.f2760g.setVisibility(0);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f2751a.f2760g.clearAnimation();
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.7 */
    class C01837 implements AnimationListener {
        final /* synthetic */ PreviewVideoHistoryViewer f2752a;

        C01837(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2752a = previewVideoHistoryViewer;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f2752a.f2760g.clearAnimation();
            this.f2752a.f2760g.setVisibility(8);
        }
    }

    /* renamed from: app.galley.internal.PreviewVideoHistoryViewer.8 */
    class C01848 implements Runnable {
        final /* synthetic */ PreviewVideoHistoryViewer f2753a;

        C01848(PreviewVideoHistoryViewer previewVideoHistoryViewer) {
            this.f2753a = previewVideoHistoryViewer;
        }

        public void run() {
            this.f2753a.f2755b.setProgress(0);
        }
    }

    public PreviewVideoHistoryViewer() {
        this.f2758e = "";
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903102, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHasOptionsMenu(true);
        m5197a(view);
        m5199d();
    }

    private void m5199d() {
        this.f2756c = new Handler();
        this.f2762i = getArguments().getString("video_caption");
        EmojiHandlerCode.m6125a().m6130a(this.f2762i, this.f2760g, EmojiHandlerCode.m6125a().f3543b, getActivity());
        if (this.f2762i == null || this.f2762i.length() == 0) {
            this.f2760g.setVisibility(8);
        }
        String string = getArguments().getString(DataPacketExtension.ELEMENT);
        try {
            this.f2758e = Utils.m7079a(string, Type.VIDEO);
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
        }
        try {
            this.f2757d = Storage.m6956j() + File.separator + string + ".mp4";
        } catch (StorageException e2) {
            Crashlytics.m7882a(new HandledException("unable to determine save path"));
        }
        m5200e();
        if (new File(this.f2758e).exists()) {
            Bitmap createVideoThumbnail;
            this.f2754a.setVideoURI(Uri.parse(this.f2758e));
            this.f2754a.requestFocus();
            try {
                createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(Utils.m7079a(string, Type.VIDEO), 1);
            } catch (Throwable e3) {
                Main.f1926a.m5682c(e3);
                createVideoThumbnail = null;
            }
            if (createVideoThumbnail != null) {
                Background.m6968a(new BitmapDrawable(getResources(), createVideoThumbnail), this.f2754a);
            }
            this.f2754a.start();
            this.f2764k = false;
            this.f2755b.setMax(this.f2754a.getDuration() / 1000);
            return;
        }
        Main.m3905a(getString(2131296835));
    }

    private void m5200e() {
        this.f2755b.setOnSeekBarChangeListener(new C01761(this));
        this.f2754a.setOnPreparedListener(new C01772(this));
        this.f2754a.setOnCompletionListener(new C01783(this));
        this.f2754a.setVideoStateChangeListener(new C01794(this));
        m5201f();
    }

    private void m5201f() {
        this.f2756c.postDelayed(new C01815(this), 100);
    }

    private void m5197a(View view) {
        this.f2754a = (CustomVideoView) view.findViewById(2131755286);
        this.f2755b = (SeekBar) view.findViewById(2131755289);
        this.f2759f = (LinearLayout) view.findViewById(2131755290);
        this.f2760g = (TextView) view.findViewById(2131755288);
        this.f2761h = (ImageView) view.findViewById(2131755287);
        this.f2761h.setVisibility(8);
        this.f2759f.setVisibility(8);
        m5204b();
        this.f2761h.setOnClickListener(this);
        this.f2754a.setOnTouchListener(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820550, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 2131755635:
                if (!m5202g()) {
                    if (PermissionUtil.m7044a(PermissionType.storage)) {
                        try {
                            if (!FileUtil.m7023a(this.f2758e, this.f2757d, false)) {
                                Storage.m6945c(getActivity());
                                break;
                            }
                            MediaScannerConnection.scanFile(getActivity(), new String[]{this.f2757d}, null, null);
                            Main.m3905a(getString(2131296842));
                            break;
                        } catch (IOException e) {
                            Main.m3905a(getString(2131296841));
                            break;
                        }
                    }
                    PermissionUtil.m7042a(getActivity(), PermissionType.storage, 2);
                    return super.onOptionsItemSelected(menuItem);
                }
                Main.m3905a(getString(2131296834));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private boolean m5202g() {
        return new File(this.f2757d).exists();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755287:
                this.f2754a.start();
                m5201f();
                m5204b();
                this.f2761h.setVisibility(8);
                this.f2764k = !this.f2764k;
            default:
        }
    }

    public void m5203a() {
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), 2130968603);
        loadAnimation.setDuration(400);
        loadAnimation.setFillAfter(true);
        loadAnimation.setFillEnabled(true);
        this.f2760g.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new C01826(this));
    }

    public void m5204b() {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 5.2f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        this.f2760g.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new C01837(this));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.f2764k) {
            this.f2754a.pause();
            this.f2761h.setVisibility(0);
            if (this.f2762i != null && this.f2762i.length() > 0) {
                m5203a();
            }
            this.f2764k = !this.f2764k;
        }
        return false;
    }

    public void m5205c() {
        this.f2754a.pause();
        this.f2754a.seekTo(0);
        this.f2755b.post(new C01848(this));
        this.f2761h.setVisibility(0);
        this.f2764k = true;
    }
}
