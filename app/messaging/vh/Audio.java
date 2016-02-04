package app.messaging.vh;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import app.Main;
import app.common.AudioPlayer;
import app.common.Constants;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.events.AudioPlaybackEvent;
import app.events.BusUnregisterEvent;
import app.events.net.AudioDownloadProgressEvent;
import app.events.net.MediaDownloadProgressEvent.State;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.messaging.RecycleMessagingAdapter;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.BubbleDialogUtil;
import app.util.Utils;
import app.xmpp.NormalMessageHandler;
import app.xmpp.NormalMessageManager;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.IOException;
import se.emilsjolander.stickylistheaders.C1128R;

public class Audio extends MessageViewHolder {
    private String f4026K;
    private String f4027L;
    ImageView f4028l;
    ImageView f4029m;
    ImageView f4030n;
    TextView f4031o;
    SeekBar f4032p;
    ProgressBar f4033q;
    ImageView f4034r;

    /* renamed from: app.messaging.vh.Audio.1 */
    class C03851 implements OnClickListener {
        final /* synthetic */ Audio f3986a;

        C03851(Audio audio) {
            this.f3986a = audio;
        }

        public void onClick(View view) {
            Exception e;
            if (!RecycleMessagingAdapter.m5959j()) {
                try {
                    if (new File(Utils.m7079a(this.f3986a.f4026K, Type.AUDIO)).exists()) {
                        this.f3986a.m6526a(Mode.playing);
                        AudioPlayer.m3946a().m3952a(this.f3986a.f4027L, this.f3986a.f4026K);
                        return;
                    }
                    this.f3986a.m6526a(Mode.download);
                } catch (StorageException e2) {
                    e = e2;
                    Main.f1926a.m5683d("exception happened in play audio " + e.getMessage());
                    this.f3986a.m6526a(Mode.crap);
                } catch (IOException e3) {
                    e = e3;
                    Main.f1926a.m5683d("exception happened in play audio " + e.getMessage());
                    this.f3986a.m6526a(Mode.crap);
                }
            }
        }
    }

    /* renamed from: app.messaging.vh.Audio.2 */
    class C03862 implements OnClickListener {
        final /* synthetic */ Audio f3987a;

        C03862(Audio audio) {
            this.f3987a = audio;
        }

        public void onClick(View view) {
            if (AudioPlayer.m3946a().m3954b(this.f3987a.f4027L)) {
                AudioPlayer.m3946a().m3953b();
            }
            this.f3987a.m6526a(Mode.paused);
        }
    }

    /* renamed from: app.messaging.vh.Audio.3 */
    class C03883 implements OnClickListener {
        final /* synthetic */ Audio f3991a;

        /* renamed from: app.messaging.vh.Audio.3.1 */
        class C03871 extends FileTaskListener {
            final /* synthetic */ File f3988a;
            final /* synthetic */ C03883 f3989b;
            private AudioDownloadProgressEvent f3990c;

            C03871(C03883 c03883, File file, File file2) {
                this.f3989b = c03883;
                this.f3988a = file2;
                super(file);
                this.f3990c = new AudioDownloadProgressEvent(this.f3989b.f3991a.f4026K);
            }

            public void m6504e() {
                this.f3989b.f3991a.m6526a(Mode.downloading);
            }

            public void m6502a(int i, Header[] headerArr, File file) {
                if (Storage.m6939a(Main.f1927b, this.f3988a.getParentFile()) && file.renameTo(this.f3988a)) {
                    this.f3990c.m4948a(State.successful);
                } else {
                    this.f3990c.m4948a(State.failed);
                }
                EventBus.m12779a().m12795d(this.f3990c);
            }

            public void m6503a(int i, Header[] headerArr, File file, Throwable th) {
                this.f3990c.m4948a(State.failed);
                EventBus.m12779a().m12795d(this.f3990c);
                file.delete();
            }
        }

        C03883(Audio audio) {
            this.f3991a = audio;
        }

        public void onClick(View view) {
            try {
                File file = new File(Utils.m7079a(this.f3991a.f4026K, Type.AUDIO));
                if (file.exists()) {
                    this.f3991a.m6526a(Mode.paused);
                    return;
                }
                RequestParams requestParams = new RequestParams("token", this.f3991a.f4026K);
                File file2 = new File(Storage.m6952g() + File.separator + this.f3991a.f4026K);
                if (Storage.m6939a(this.f3991a.s, file2.getParentFile())) {
                    HttpService.m5567a(new TaskBuilder().m5590a(this.f3991a.f4026K).m5585a(this.f3991a.s).m5592b(Constants.m3960e()).m5589a(requestParams).m5586a(RequestType.post).m5587a(TaskPriority.immediate).m5588a(new C03871(this, file2, file)).m5584a());
                }
            } catch (StorageException e) {
            }
        }
    }

    /* renamed from: app.messaging.vh.Audio.4 */
    class C03904 implements OnClickListener {
        final /* synthetic */ Audio f3993a;

        /* renamed from: app.messaging.vh.Audio.4.1 */
        class C03891 implements Runnable {
            final /* synthetic */ C03904 f3992a;

            C03891(C03904 c03904) {
                this.f3992a = c03904;
            }

            public void run() {
                NormalMessageHandler.m7415a().m7434a(this.f3992a.f3993a.F, NormalMessageManager.m7447a().m7474h(), this.f3992a.f3993a.f4026K, true, this.f3992a.f3993a.G);
            }
        }

        C03904(Audio audio) {
            this.f3993a = audio;
        }

        public void onClick(View view) {
            new Thread(new C03891(this)).start();
        }
    }

    /* renamed from: app.messaging.vh.Audio.5 */
    class C03915 implements OnSeekBarChangeListener {
        final /* synthetic */ Audio f3994a;

        C03915(Audio audio) {
            this.f3994a = audio;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f3994a.m6530c(i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            AudioPlayer.m3946a().m3951a(this.f3994a.f4027L, seekBar.getProgress());
            this.f3994a.m6530c(seekBar.getProgress());
        }
    }

    /* renamed from: app.messaging.vh.Audio.6 */
    class C03926 implements OnLongClickListener {
        final /* synthetic */ Audio f3995a;

        C03926(Audio audio) {
            this.f3995a = audio;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7003b(this.f3995a.s, this.f3995a.F, this.f3995a.J, this.f3995a.G, this.f3995a.t);
            return true;
        }
    }

    /* renamed from: app.messaging.vh.Audio.7 */
    /* synthetic */ class C03937 {
        static final /* synthetic */ int[] f3996a;
        static final /* synthetic */ int[] f3997b;
        static final /* synthetic */ int[] f3998c;
        static final /* synthetic */ int[] f3999d;

        static {
            f3999d = new int[Mode.values().length];
            try {
                f3999d[Mode.download.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3999d[Mode.upload.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3999d[Mode.downloading.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3999d[Mode.uploading.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3999d[Mode.paused.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3999d[Mode.playing.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3999d[Mode.crap.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            f3998c = new int[State.values().length];
            try {
                f3998c[State.successful.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3998c[State.failed.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f3998c[State.canceled.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
            f3997b = new int[AudioPlaybackEvent.State.values().length];
            try {
                f3997b[AudioPlaybackEvent.State.playing.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f3997b[AudioPlaybackEvent.State.stopped.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            f3996a = new int[DeliveryStatus.values().length];
            try {
                f3996a[DeliveryStatus.UPLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f3996a[DeliveryStatus.FAILED_TO_UPLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    public enum Mode {
        download,
        upload,
        downloading,
        uploading,
        paused,
        playing,
        crap
    }

    public Audio(View view, Context context) {
        super(view, context);
    }

    public void m6531a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        if (!EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12791a((Object) this);
        }
        this.f4032p.setMax(historyEntity.m4428o() * 1000);
        this.f4026K = historyEntity.m4430q();
        this.f4027L = historyEntity.m4430q() + historyEntity.m4407a();
        try {
            File file = new File(Utils.m7079a(this.f4026K, Type.AUDIO));
            switch (C03937.f3996a[historyEntity.m4420g().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m6526a(Mode.uploading);
                    if (HttpService.m5565a(this.f4026K) == null) {
                        Main.f1926a.m5679b("Audio message delivery status is Uploading, but the upload task does not exist.");
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6526a(Mode.upload);
                default:
                    if (file.exists()) {
                        if (AudioPlayer.m3946a().m3954b(this.f4027L)) {
                            m6526a(Mode.playing);
                        } else {
                            m6526a(Mode.paused);
                        }
                    } else if (HttpService.m5565a(this.f4026K) == null) {
                        m6526a(Mode.download);
                    } else {
                        m6526a(Mode.downloading);
                    }
                    m6524D();
            }
        } catch (StorageException e) {
            m6526a(Mode.crap);
        }
    }

    public void m6532y() {
        super.m6522y();
        this.f4029m.setOnClickListener(new C03851(this));
        this.f4031o.setOnClickListener(new C03862(this));
        this.f4028l.setOnClickListener(new C03883(this));
        this.f4030n.setOnClickListener(new C03904(this));
        this.f4032p.setOnSeekBarChangeListener(new C03915(this));
        this.v.setOnLongClickListener(new C03926(this));
    }

    public void onEventMainThread(AudioPlaybackEvent audioPlaybackEvent) {
        if (audioPlaybackEvent.m4844a().equals(this.f4027L)) {
            switch (C03937.f3997b[audioPlaybackEvent.m4845b().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m6524D();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6526a(Mode.paused);
                default:
            }
        }
    }

    public void onEventMainThread(AudioDownloadProgressEvent audioDownloadProgressEvent) {
        if (audioDownloadProgressEvent.m4946a().equals(this.f4026K)) {
            switch (C03937.f3998c[audioDownloadProgressEvent.m4950c().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m6526a(Mode.paused);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m6526a(Mode.download);
                default:
            }
        }
    }

    public void onEvent(BusUnregisterEvent busUnregisterEvent) {
        if (EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12794c(this);
        }
    }

    private void m6524D() {
        int a = AudioPlayer.m3946a().m3950a(this.f4027L);
        if (a >= 0) {
            m6530c(a);
            this.f4032p.setProgress(a);
            return;
        }
        m6530c(0);
        this.f4032p.setProgress(0);
    }

    private void m6530c(int i) {
        this.f4031o.setText(String.format("%02d", new Object[]{Integer.valueOf(this.F.m4428o() - (i / 1000))}));
    }

    private void m6526a(Mode mode) {
        switch (C03937.f3999d[mode.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f4028l.setVisibility(0);
                this.f4030n.setVisibility(8);
                this.f4029m.setVisibility(8);
                this.f4031o.setVisibility(8);
                this.f4034r.setVisibility(8);
                this.f4033q.setVisibility(8);
                this.f4032p.setEnabled(false);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f4028l.setVisibility(8);
                this.f4030n.setVisibility(0);
                this.f4029m.setVisibility(8);
                this.f4031o.setVisibility(8);
                this.f4034r.setVisibility(8);
                this.f4033q.setVisibility(8);
                this.f4032p.setEnabled(false);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f4028l.setVisibility(8);
                this.f4030n.setVisibility(8);
                this.f4029m.setVisibility(8);
                this.f4031o.setVisibility(8);
                this.f4034r.setVisibility(0);
                this.f4033q.setVisibility(0);
                this.f4032p.setEnabled(false);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                this.f4028l.setVisibility(8);
                this.f4030n.setVisibility(8);
                this.f4029m.setVisibility(0);
                this.f4031o.setVisibility(8);
                this.f4034r.setVisibility(8);
                this.f4033q.setVisibility(8);
                this.f4032p.setEnabled(false);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                this.f4028l.setVisibility(8);
                this.f4030n.setVisibility(8);
                this.f4029m.setVisibility(8);
                this.f4031o.setVisibility(0);
                this.f4034r.setVisibility(8);
                this.f4033q.setVisibility(8);
                this.f4032p.setEnabled(true);
            default:
                this.f4028l.setVisibility(8);
                this.f4030n.setVisibility(8);
                this.f4029m.setVisibility(8);
                this.f4031o.setVisibility(0);
                this.f4034r.setVisibility(8);
                this.f4033q.setVisibility(8);
                this.f4032p.setEnabled(false);
                this.f4031o.setText("");
        }
    }
}
