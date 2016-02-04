package app.galley.internal;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import app.common.CustomVideoView;
import app.common.CustomVideoView.VideoStateChangeListener;
import app.galley.ActionEditText;
import app.galley.SelectedItemGalleryModel;
import app.galley.external.GalleryItemModel;
import app.messaging.MediaFileValidator;
import app.util.Background;
import java.util.Map.Entry;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class PreviewSingleVideoFragment extends Fragment implements OnClickListener, OnTouchListener {
    protected SeekBar f2731a;
    Handler f2732b;
    boolean f2733c;
    protected TextView f2734d;
    boolean f2735e;
    boolean f2736f;
    SelectedItemGalleryModel f2737g;
    SinglePreviewComminucator f2738h;
    protected InputMethodManager f2739i;
    private CustomVideoView f2740j;
    private ImageView f2741k;
    private ImageView f2742l;
    private ActionEditText f2743m;
    private long f2744n;

    public interface SinglePreviewComminucator {
        void m5005b(long j, String str);

        void m5006j();

        void m5007n();
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.1 */
    class C01681 implements OnClickListener {
        final /* synthetic */ PreviewSingleVideoFragment f2723a;

        C01681(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2723a = previewSingleVideoFragment;
        }

        public void onClick(View view) {
            boolean z = true;
            if (this.f2723a.f2733c) {
                this.f2723a.m5190a();
                this.f2723a.f2738h.m5005b(this.f2723a.f2744n, this.f2723a.f2743m.getText().toString());
                this.f2723a.f2741k.setImageResource(2130837787);
                this.f2723a.f2743m.setEnabled(false);
            } else {
                this.f2723a.m5191b();
                this.f2723a.f2743m.setEnabled(true);
                this.f2723a.f2741k.setImageResource(2130837789);
            }
            PreviewSingleVideoFragment previewSingleVideoFragment = this.f2723a;
            if (this.f2723a.f2733c) {
                z = false;
            }
            previewSingleVideoFragment.f2733c = z;
        }
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.2 */
    class C01692 implements OnSeekBarChangeListener {
        final /* synthetic */ PreviewSingleVideoFragment f2724a;

        C01692(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2724a = previewSingleVideoFragment;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                this.f2724a.f2740j.seekTo(i);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.f2724a.f2735e = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            this.f2724a.f2735e = false;
            this.f2724a.m5188f();
        }
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.3 */
    class C01703 implements OnPreparedListener {
        final /* synthetic */ PreviewSingleVideoFragment f2725a;

        C01703(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2725a = previewSingleVideoFragment;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.f2725a.f2731a.setMax(mediaPlayer.getDuration());
        }
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.4 */
    class C01714 implements OnCompletionListener {
        final /* synthetic */ PreviewSingleVideoFragment f2726a;

        C01714(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2726a = previewSingleVideoFragment;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.f2726a.f2731a.setProgress(0);
            this.f2726a.f2740j.seekTo(0);
            this.f2726a.f2736f = true;
            this.f2726a.f2742l.setVisibility(0);
        }
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.5 */
    class C01725 implements VideoStateChangeListener {
        final /* synthetic */ PreviewSingleVideoFragment f2727a;

        C01725(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2727a = previewSingleVideoFragment;
        }

        public void m5174a() {
            Background.m6968a(null, this.f2727a.f2740j);
        }

        public void m5175b() {
        }

        public void m5176c() {
        }

        public void m5177d() {
        }
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.6 */
    class C01746 implements Runnable {
        final /* synthetic */ PreviewSingleVideoFragment f2729a;

        /* renamed from: app.galley.internal.PreviewSingleVideoFragment.6.1 */
        class C01731 implements Runnable {
            final /* synthetic */ C01746 f2728a;

            C01731(C01746 c01746) {
                this.f2728a = c01746;
            }

            public void run() {
                if (!this.f2728a.f2729a.f2735e) {
                    this.f2728a.f2729a.f2731a.setProgress(this.f2728a.f2729a.f2731a.getProgress() + 100);
                }
            }
        }

        C01746(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2729a = previewSingleVideoFragment;
        }

        public void run() {
            this.f2729a.f2731a.post(new C01731(this));
            if (!this.f2729a.f2735e && !this.f2729a.f2736f) {
                if (!this.f2729a.isAdded() || (this.f2729a.f2731a.getProgress() >= this.f2729a.f2740j.getDuration() && this.f2729a.f2740j.getDuration() > 0)) {
                    this.f2729a.f2731a.setProgress(this.f2729a.f2731a.getMax());
                } else {
                    this.f2729a.f2740j.postDelayed(this, 100);
                }
            }
        }
    }

    /* renamed from: app.galley.internal.PreviewSingleVideoFragment.7 */
    class C01757 implements Runnable {
        final /* synthetic */ PreviewSingleVideoFragment f2730a;

        C01757(PreviewSingleVideoFragment previewSingleVideoFragment) {
            this.f2730a = previewSingleVideoFragment;
        }

        public void run() {
            this.f2730a.f2731a.setProgress(0);
        }
    }

    public PreviewSingleVideoFragment() {
        this.f2733c = true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.f2736f) {
            this.f2740j.pause();
            this.f2742l.setVisibility(0);
            this.f2736f = !this.f2736f;
        }
        return false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755287:
                this.f2740j.start();
                m5188f();
                this.f2742l.setVisibility(8);
                this.f2736f = !this.f2736f;
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903102, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHasOptionsMenu(true);
        this.f2739i = (InputMethodManager) getActivity().getSystemService("input_method");
        m5189g();
        m5179a(view);
        m5184d();
        this.f2741k.setOnClickListener(new C01681(this));
    }

    private void m5184d() {
        for (Entry entry : this.f2737g.m5028a().entrySet()) {
            m5180a(((GalleryItemModel) entry.getValue()).m5063d());
            this.f2744n = ((Long) entry.getKey()).longValue();
        }
    }

    private void m5179a(View view) {
        this.f2741k = (ImageView) view.findViewById(2131755292);
        this.f2742l = (ImageView) view.findViewById(2131755287);
        this.f2743m = (ActionEditText) view.findViewById(2131755291);
        this.f2734d = (TextView) view.findViewById(2131755288);
        this.f2734d.setVisibility(8);
        this.f2740j = (CustomVideoView) view.findViewById(2131755286);
        this.f2731a = (SeekBar) view.findViewById(2131755289);
        this.f2740j.setOnTouchListener(this);
        this.f2742l.setOnClickListener(this);
        this.f2740j.start();
        this.f2736f = true;
        this.f2731a.setMax(this.f2740j.getDuration() / 1000);
        m5185e();
    }

    private void m5185e() {
        this.f2731a.setOnSeekBarChangeListener(new C01692(this));
        this.f2740j.setOnPreparedListener(new C01703(this));
        this.f2740j.setOnCompletionListener(new C01714(this));
        this.f2740j.setVideoStateChangeListener(new C01725(this));
    }

    private void m5188f() {
        this.f2732b.postDelayed(new C01746(this), 100);
    }

    private void m5180a(String str) {
        if (MediaFileValidator.m5897a(str)) {
            this.f2740j.setVideoURI(Uri.parse(str));
            this.f2740j.setBackgroundDrawable(new BitmapDrawable(getResources(), ThumbnailUtils.createVideoThumbnail(str, 1)));
            this.f2740j.pause();
        }
    }

    private void m5189g() {
        this.f2737g = (SelectedItemGalleryModel) getArguments().getParcelable(DataPacketExtension.ELEMENT);
        this.f2732b = new Handler();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820559, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        m5190a();
        switch (menuItem.getItemId()) {
            case 2131755644:
                this.f2738h.m5006j();
                break;
            case 2131755645:
                this.f2738h.m5007n();
                break;
        }
        return true;
    }

    protected void m5190a() {
        this.f2739i.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    protected void m5191b() {
        this.f2743m.requestFocus();
        this.f2739i.showSoftInput(this.f2743m, 2);
        getActivity().getWindow().setSoftInputMode(4);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2738h = (SinglePreviewComminucator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.f2738h = null;
    }

    public void m5192c() {
        this.f2740j.pause();
        this.f2740j.seekTo(0);
        this.f2731a.post(new C01757(this));
        this.f2742l.setVisibility(0);
        this.f2736f = true;
    }
}
