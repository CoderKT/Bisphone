package app.messaging.vh;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.Constants;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.runnabe.RunnableToast;
import app.events.BusUnregisterEvent;
import app.events.net.MediaDownloadProgressEvent.State;
import app.events.net.MediaUploadProgressEvent;
import app.events.net.VideoDownloadProgressEvent;
import app.galley.SelectResourceFromSDCardActivity;
import app.http.HttpService;
import app.http.HttpTask;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.http.listener.ITaskListener;
import app.messaging.RecycleMessagingAdapter;
import app.messaging.emoji.EmojiHandlerCode;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.BitmapUtil;
import app.util.BubbleDialogUtil;
import app.util.FileUtil;
import app.util.NetworkUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.SharedPreferencesUtil;
import app.util.Utils;
import app.util.VideoUtil;
import app.xmpp.NormalMessageHandler;
import app.xmpp.NormalMessageManager;
import com.loopj.android.http.RequestParams;
import com.todddavies.components.progressbar.ProgressWheel;
import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.IOException;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import se.emilsjolander.stickylistheaders.C1128R;

public class Video extends MessageViewHolder {
    TextView f4110K;
    public String f4111L;
    Handler f4112M;
    public ImageView f4113l;
    public ImageView f4114m;
    public ProgressWheel f4115n;
    public ImageView f4116o;
    public TextView f4117p;
    public ImageView f4118q;
    public ImageView f4119r;

    /* renamed from: app.messaging.vh.Video.1 */
    class C04231 implements Runnable {
        final /* synthetic */ Video f4098a;

        C04231(Video video) {
            this.f4098a = video;
        }

        public void run() {
            if (SharedPreferencesUtil.m7060b(this.f4098a.s.getResources().getString(2131296918), Boolean.valueOf(false))) {
                try {
                    if (FileUtil.m7023a(Utils.m7079a(this.f4098a.f4111L, Type.VIDEO), Storage.m6956j() + File.separator + this.f4098a.f4111L + ".mp4", false)) {
                        MediaScannerConnection.scanFile(this.f4098a.s, new String[]{r1}, null, null);
                    }
                } catch (IOException e) {
                } catch (StorageException e2) {
                }
            }
        }
    }

    /* renamed from: app.messaging.vh.Video.2 */
    class C04252 extends FileTaskListener {
        final /* synthetic */ File f4101a;
        final /* synthetic */ Video f4102b;
        private VideoDownloadProgressEvent f4103c;

        /* renamed from: app.messaging.vh.Video.2.1 */
        class C04241 implements Runnable {
            final /* synthetic */ File f4099a;
            final /* synthetic */ C04252 f4100b;

            C04241(C04252 c04252, File file) {
                this.f4100b = c04252;
                this.f4099a = file;
            }

            public void run() {
                try {
                    if (Main.f1929d.get(FileUtil.m7024b(this.f4099a)) == null) {
                        this.f4100b.f4102b.f4112M.post(new RunnableToast(this.f4100b.f4102b.s.getString(2131296814)));
                        this.f4099a.delete();
                        this.f4100b.f4103c.m4948a(State.failed);
                    } else {
                        this.f4099a.renameTo(this.f4100b.f4101a);
                        this.f4100b.f4102b.m6589E();
                        this.f4100b.f4103c.m4948a(State.successful);
                    }
                } catch (IOException e) {
                    Main.m3905a(this.f4100b.f4102b.s.getString(2131296815));
                    this.f4100b.f4103c.m4948a(State.failed);
                }
                EventBus.m12779a().m12795d(this.f4100b.f4103c);
            }
        }

        C04252(Video video, File file, File file2) {
            this.f4102b = video;
            this.f4101a = file2;
            super(file);
            this.f4103c = new VideoDownloadProgressEvent(this.f4102b.f4111L);
        }

        public void m6586a(long j, long j2) {
            this.f4103c.m4947a(((float) j) / ((float) j2));
            EventBus.m12779a().m12795d(this.f4103c);
        }

        public void m6584a(int i, Header[] headerArr, File file) {
            new Thread(new C04241(this, file)).start();
        }

        public void m6585a(int i, Header[] headerArr, File file, Throwable th) {
            this.f4103c.m4948a(State.failed);
            EventBus.m12779a().m12795d(this.f4103c);
            Main.m3905a(this.f4102b.s.getString(2131296815));
            file.delete();
        }

        public void m6587g() {
            Main.f1926a.m5679b("onCancel");
            this.f4103c.m4948a(State.canceled);
            EventBus.m12779a().m12795d(this.f4103c);
        }
    }

    /* renamed from: app.messaging.vh.Video.3 */
    class C04273 implements OnClickListener {
        final /* synthetic */ Video f4105a;

        /* renamed from: app.messaging.vh.Video.3.1 */
        class C04261 implements Runnable {
            final /* synthetic */ C04273 f4104a;

            C04261(C04273 c04273) {
                this.f4104a = c04273;
            }

            public void run() {
                NormalMessageHandler.m7415a().m7434a(this.f4104a.f4105a.F, NormalMessageManager.m7447a().m7474h(), this.f4104a.f4105a.f4111L, true, this.f4104a.f4105a.G);
            }
        }

        C04273(Video video) {
            this.f4105a = video;
        }

        public void onClick(View view) {
            new Thread(new C04261(this)).start();
            this.f4105a.f4118q.setVisibility(8);
        }
    }

    /* renamed from: app.messaging.vh.Video.4 */
    class C04284 implements OnClickListener {
        final /* synthetic */ Video f4106a;

        C04284(Video video) {
            this.f4106a = video;
        }

        public void onClick(View view) {
            if (this.f4106a.f4116o.getVisibility() == 0) {
                Intent intent = new Intent(this.f4106a.s, SelectResourceFromSDCardActivity.class);
                intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_VIEW_VIDEOS");
                intent.putExtra(DataPacketExtension.ELEMENT, this.f4106a.f4111L);
                intent.putExtra("video_caption", this.f4106a.F.m4426m());
                this.f4106a.s.startActivity(intent);
            } else if (this.f4106a.f4114m.getVisibility() == 0) {
                this.f4106a.m6590F();
            } else if (this.f4106a.f4119r.getVisibility() == 0) {
                HttpService.m5570b(this.f4106a.f4111L);
            }
        }
    }

    /* renamed from: app.messaging.vh.Video.5 */
    class C04295 implements OnLongClickListener {
        final /* synthetic */ Video f4107a;

        C04295(Video video) {
            this.f4107a = video;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7003b(this.f4107a.s, this.f4107a.F, this.f4107a.J, this.f4107a.G, this.f4107a.t);
            return true;
        }
    }

    /* renamed from: app.messaging.vh.Video.6 */
    /* synthetic */ class C04306 {
        static final /* synthetic */ int[] f4108a;
        static final /* synthetic */ int[] f4109b;

        static {
            f4109b = new int[State.values().length];
            try {
                f4109b[State.progressing.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4109b[State.successful.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4109b[State.failed.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4109b[State.canceled.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            f4108a = new int[DeliveryStatus.values().length];
            try {
                f4108a[DeliveryStatus.RECEIVED.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4108a[DeliveryStatus.FAILED_TO_SEND.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4108a[DeliveryStatus.SENDING.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4108a[DeliveryStatus.SENT.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4108a[DeliveryStatus.DELIVERED.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4108a[DeliveryStatus.UPLOADING.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4108a[DeliveryStatus.FAILED_TO_UPLOAD.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public Video(View view, Context context) {
        super(view, context);
        this.f4111L = "-1";
        if (!EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12791a((Object) this);
        }
    }

    public void m6595a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        this.f4112M = new Handler();
        this.f4111L = historyEntity.m4430q();
        m6591G();
        m6588D();
        if (historyEntity.m4423j() != null) {
            this.f4113l.setImageBitmap(BitmapUtil.m6976b(historyEntity.m4423j()));
        }
        switch (C04306.f4108a[historyEntity.m4420g().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                File file;
                HttpTask a = HttpService.m5565a(this.f4111L);
                try {
                    file = new File(Utils.m7079a(this.f4111L, Type.VIDEO));
                } catch (StorageException e) {
                    file = null;
                }
                if (this.f4118q != null) {
                    this.f4118q.setVisibility(8);
                }
                if (a != null) {
                    this.f4115n.setVisibility(0);
                    this.f4115n.setProgress(a.m5583i());
                    this.f4117p.setText(FileUtil.m7017a(historyEntity.m4429p()));
                    this.f4116o.setVisibility(4);
                    this.f4114m.setVisibility(4);
                    this.f4119r.setVisibility(0);
                } else if (file == null || !file.exists()) {
                    this.f4115n.setVisibility(4);
                    this.f4117p.setText(FileUtil.m7017a(historyEntity.m4429p()));
                    this.f4116o.setVisibility(4);
                    this.f4114m.setVisibility(0);
                    this.f4119r.setVisibility(4);
                } else {
                    this.f4115n.setVisibility(4);
                    this.f4115n.setEnabled(false);
                    this.f4117p.setText(VideoUtil.m7086a(historyEntity.m4428o()));
                    this.f4116o.setVisibility(0);
                    this.f4114m.setVisibility(4);
                    this.f4119r.setVisibility(4);
                }
                if ((file == null || !file.exists()) && NetworkUtil.m7037a(context, false) && NetworkUtil.m7038b(context)) {
                    m6590F();
                } else if (NetworkUtil.m7037a(context, false)) {
                    m6589E();
                }
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                this.f4115n.setVisibility(0);
                this.f4117p.setText(FileUtil.m7017a(historyEntity.m4429p()));
                this.f4116o.setVisibility(4);
                this.f4114m.setVisibility(4);
                this.f4119r.setVisibility(4);
                if (this.f4118q != null) {
                    this.f4118q.setVisibility(8);
                }
                HttpTask a2 = HttpService.m5565a(this.f4111L);
                if (a2 != null) {
                    if (a2.m5583i() == 0.0f) {
                        this.f4115n.m11358a();
                        this.f4115n.m11359b();
                        this.f4115n.setSpinSpeed(1);
                    }
                    this.f4115n.setProgress(a2.m5583i());
                    this.f4115n.setVisibility(0);
                }
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                this.f4115n.setVisibility(4);
                this.f4117p.setText(FileUtil.m7017a(historyEntity.m4429p()));
                this.f4116o.setVisibility(0);
                this.f4114m.setVisibility(4);
                this.f4119r.setVisibility(4);
                if (this.f4118q != null) {
                    this.f4118q.setVisibility(0);
                }
            default:
        }
    }

    private void m6588D() {
        if (this.F.m4426m() == null || this.F.m4426m().length() <= 0) {
            this.f4110K.setVisibility(8);
            return;
        }
        this.f4110K.setText("");
        EmojiHandlerCode.m6125a().m6130a(this.F.m4426m(), this.f4110K, EmojiHandlerCode.m6125a().f3543b, this.s);
        this.f4110K.setVisibility(0);
    }

    private void m6589E() {
        if (PermissionUtil.m7044a(PermissionType.storage)) {
            new Thread(new C04231(this)).start();
        }
    }

    private void m6590F() {
        try {
            File file = new File(Storage.m6952g() + File.separator + this.f4111L);
            File file2 = new File(Storage.m6944c() + File.separator + this.f4111L);
            if (Storage.m6939a(this.s, file.getParentFile()) && Storage.m6939a(this.s, file2.getParentFile())) {
                this.f4115n.setProgress(0.0f);
                this.f4115n.setVisibility(0);
                this.f4119r.setVisibility(0);
                this.f4116o.setVisibility(4);
                this.f4114m.setVisibility(4);
                Main.f1929d.put(this.f4111L, Boolean.valueOf(false));
                RequestParams requestParams = new RequestParams("token", this.f4111L);
                HttpService.m5567a(new TaskBuilder().m5590a(this.f4111L).m5585a(this.s).m5592b(Constants.m3960e()).m5589a(requestParams).m5586a(RequestType.post).m5587a(TaskPriority.medium).m5588a(m6592a(file, file2)).m5584a());
            }
        } catch (StorageException e) {
        }
    }

    private ITaskListener m6592a(File file, File file2) {
        return new C04252(this, file, file2);
    }

    public void onEventMainThread(VideoDownloadProgressEvent videoDownloadProgressEvent) {
        if (videoDownloadProgressEvent.m4946a().equals(this.f4111L)) {
            switch (C04306.f4109b[videoDownloadProgressEvent.m4950c().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f4115n.setProgress(videoDownloadProgressEvent.m4949b());
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f4115n.setVisibility(4);
                    this.f4119r.setVisibility(4);
                    this.f4116o.setVisibility(0);
                    this.f4114m.setVisibility(4);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4115n.setVisibility(4);
                    this.f4119r.setVisibility(4);
                    this.f4116o.setVisibility(4);
                    this.f4114m.setVisibility(0);
                default:
            }
        }
    }

    public void onEventMainThread(MediaUploadProgressEvent mediaUploadProgressEvent) {
        if (mediaUploadProgressEvent.m4951a().equals(this.f4111L)) {
            this.f4115n.setProgress(mediaUploadProgressEvent.m4953b());
        }
    }

    public void m6596y() {
        super.m6522y();
        if (this.f4118q != null) {
            this.f4118q.setOnClickListener(new C04273(this));
        }
        this.v.setOnClickListener(new C04284(this));
        this.v.setOnLongClickListener(new C04295(this));
    }

    private void m6591G() {
        LayoutParams layoutParams = this.f4113l.getLayoutParams();
        layoutParams.width = RecycleMessagingAdapter.f3296a;
        layoutParams.height = RecycleMessagingAdapter.f3296a;
        this.f4113l.setLayoutParams(layoutParams);
    }

    public void onEvent(BusUnregisterEvent busUnregisterEvent) {
        if (EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12794c(this);
        }
    }
}
