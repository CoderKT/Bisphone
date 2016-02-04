package app.messaging.vh;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.Constants;
import app.common.CustomImageLoader;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.events.BusUnregisterEvent;
import app.events.net.ImageDownloadProgressEvent;
import app.events.net.MediaDownloadProgressEvent.State;
import app.events.net.MediaUploadProgressEvent;
import app.galley.SelectResourceFromSDCardActivity;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
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
import app.util.ThumbnailUtil;
import app.util.Utils;
import app.xmpp.NormalMessageHandler;
import app.xmpp.NormalMessageManager;
import com.loopj.android.http.RequestParams;
import com.todddavies.components.progressbar.ProgressWheel;
import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.IOException;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import se.emilsjolander.stickylistheaders.C1128R;

public class Photo extends MessageViewHolder {
    ImageView f4082l;
    TextView f4083m;
    ImageView f4084n;
    ImageView f4085o;
    ImageView f4086p;
    ProgressWheel f4087q;
    private String f4088r;

    /* renamed from: app.messaging.vh.Photo.1 */
    class C04071 implements OnClickListener {
        final /* synthetic */ Photo f4060a;

        C04071(Photo photo) {
            this.f4060a = photo;
        }

        public void onClick(View view) {
            this.f4060a.m6565D();
        }
    }

    /* renamed from: app.messaging.vh.Photo.2 */
    class C04092 implements OnClickListener {
        final /* synthetic */ Photo f4062a;

        /* renamed from: app.messaging.vh.Photo.2.1 */
        class C04081 implements Runnable {
            final /* synthetic */ C04092 f4061a;

            C04081(C04092 c04092) {
                this.f4061a = c04092;
            }

            public void run() {
                NormalMessageHandler.m7415a().m7434a(this.f4061a.f4062a.F, NormalMessageManager.m7447a().m7474h(), this.f4061a.f4062a.f4088r, true, this.f4061a.f4062a.G);
            }
        }

        C04092(Photo photo) {
            this.f4062a = photo;
        }

        public void onClick(View view) {
            new Thread(new C04081(this)).start();
        }
    }

    /* renamed from: app.messaging.vh.Photo.3 */
    class C04103 implements OnClickListener {
        final /* synthetic */ Photo f4063a;

        C04103(Photo photo) {
            this.f4063a = photo;
        }

        public void onClick(View view) {
            HttpService.m5570b(this.f4063a.f4088r);
        }
    }

    /* renamed from: app.messaging.vh.Photo.4 */
    class C04114 implements OnClickListener {
        final /* synthetic */ Photo f4064a;

        C04114(Photo photo) {
            this.f4064a = photo;
        }

        public void onClick(View view) {
            try {
                if (new File(Utils.m7079a(this.f4064a.f4088r, Type.PHOTO)).exists()) {
                    String str;
                    Intent intent = new Intent(this.f4064a.s, SelectResourceFromSDCardActivity.class);
                    intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_VIEW_IMAGES");
                    intent.putExtra("message_id", this.f4064a.F.m4412b());
                    if (this.f4064a.G != 1) {
                        str = this.f4064a.I;
                    } else {
                        str = this.f4064a.F.m4414c();
                    }
                    intent.putExtra("message_jid", str);
                    intent.putExtra("first_message_timestamp", RecycleMessagingAdapter.m5961k());
                    intent.putExtra("message_type", this.f4064a.G);
                    intent.putExtra("is_message_Incoming", this.f4064a.F.m4421h());
                    this.f4064a.s.startActivity(intent);
                }
            } catch (StorageException e) {
            }
        }
    }

    /* renamed from: app.messaging.vh.Photo.5 */
    class C04125 implements OnLongClickListener {
        final /* synthetic */ Photo f4065a;

        C04125(Photo photo) {
            this.f4065a = photo;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7003b(this.f4065a.s, this.f4065a.F, this.f4065a.J, this.f4065a.G, this.f4065a.t);
            return true;
        }
    }

    /* renamed from: app.messaging.vh.Photo.6 */
    class C04146 extends FileTaskListener {
        final /* synthetic */ File f4068a;
        final /* synthetic */ Photo f4069b;
        private ImageDownloadProgressEvent f4070c;

        /* renamed from: app.messaging.vh.Photo.6.1 */
        class C04131 implements Runnable {
            final /* synthetic */ File f4066a;
            final /* synthetic */ C04146 f4067b;

            C04131(C04146 c04146, File file) {
                this.f4067b = c04146;
                this.f4066a = file;
            }

            public void run() {
                try {
                    if (this.f4067b.f4069b.f4088r.equals(FileUtil.m7024b(this.f4066a))) {
                        this.f4066a.renameTo(this.f4067b.f4068a);
                        this.f4067b.f4069b.m6568G();
                        this.f4067b.f4070c.m4948a(State.successful);
                    } else {
                        this.f4066a.delete();
                        this.f4067b.f4070c.m4948a(State.failed);
                    }
                } catch (IOException e) {
                    this.f4066a.delete();
                    this.f4067b.f4070c.m4948a(State.failed);
                }
                EventBus.m12779a().m12795d(this.f4067b.f4070c);
            }
        }

        C04146(Photo photo, File file, File file2) {
            this.f4069b = photo;
            this.f4068a = file2;
            super(file);
            this.f4070c = new ImageDownloadProgressEvent(this.f4069b.f4088r);
        }

        public void m6563a(long j, long j2) {
            this.f4070c.m4947a(((float) j) / ((float) j2));
            EventBus.m12779a().m12795d(this.f4070c);
        }

        public void m6561a(int i, Header[] headerArr, File file) {
            new Thread(new C04131(this, file)).start();
        }

        public void m6562a(int i, Header[] headerArr, File file, Throwable th) {
            this.f4070c.m4948a(State.failed);
            EventBus.m12779a().m12795d(this.f4070c);
            file.delete();
        }

        public void m6564g() {
            this.f4070c.m4948a(State.canceled);
            EventBus.m12779a().m12795d(this.f4070c);
        }
    }

    /* renamed from: app.messaging.vh.Photo.7 */
    class C04157 implements Runnable {
        final /* synthetic */ Photo f4071a;

        C04157(Photo photo) {
            this.f4071a = photo;
        }

        public void run() {
            if (SharedPreferencesUtil.m7060b(this.f4071a.s.getResources().getString(2131296917), Boolean.valueOf(false))) {
                try {
                    String a = Utils.m7079a(this.f4071a.f4088r, Type.PHOTO);
                    String str = Storage.m6956j() + File.separator + this.f4071a.f4088r + ".jpg";
                    File file = new File(a);
                    File file2 = new File(str);
                    if (file.exists() && !file2.exists()) {
                        try {
                            if (FileUtil.m7023a(a, str, false)) {
                                MediaScannerConnection.scanFile(Main.f1927b, new String[]{str}, null, null);
                            }
                        } catch (IOException e) {
                        }
                    }
                } catch (StorageException e2) {
                }
            }
        }
    }

    /* renamed from: app.messaging.vh.Photo.8 */
    /* synthetic */ class C04168 {
        static final /* synthetic */ int[] f4072a;
        static final /* synthetic */ int[] f4073b;
        static final /* synthetic */ int[] f4074c;

        static {
            f4074c = new int[Mode.values().length];
            try {
                f4074c[Mode.download.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4074c[Mode.upload.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4074c[Mode.downloading.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4074c[Mode.uploading.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4074c[Mode.view.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4074c[Mode.crap.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            f4073b = new int[State.values().length];
            try {
                f4073b[State.progressing.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4073b[State.successful.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4073b[State.failed.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4073b[State.canceled.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            f4072a = new int[DeliveryStatus.values().length];
            try {
                f4072a[DeliveryStatus.UPLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f4072a[DeliveryStatus.FAILED_TO_UPLOAD.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    public enum Mode {
        download,
        upload,
        downloading,
        uploading,
        view,
        crap
    }

    public Photo(View view, Context context) {
        super(view, context);
        if (!EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12791a((Object) this);
        }
    }

    public void m6574a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        this.f4088r = historyEntity.m4430q();
        LayoutParams layoutParams = this.f4082l.getLayoutParams();
        layoutParams.width = RecycleMessagingAdapter.f3296a;
        layoutParams.height = RecycleMessagingAdapter.f3296a;
        this.f4082l.setLayoutParams(layoutParams);
        this.f4082l.setImageBitmap(null);
        try {
            File file = new File(Utils.m7079a(this.f4088r, Type.PHOTO));
            if (file.exists()) {
                m6571a(file);
            } else {
                m6566E();
                if (NetworkUtil.m7037a(context, true)) {
                    m6565D();
                }
            }
            if (historyEntity.m4426m() == null || historyEntity.m4426m().length() <= 0) {
                this.f4083m.setVisibility(8);
            } else {
                this.f4083m.setText("");
                EmojiHandlerCode.m6125a().m6130a(historyEntity.m4426m(), this.f4083m, EmojiHandlerCode.m6125a().f3543b, context);
                this.f4083m.setVisibility(0);
            }
            switch (C04168.f4072a[historyEntity.m4420g().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m6569a(Mode.uploading);
                    if (HttpService.m5565a(this.f4088r) == null) {
                        Main.f1926a.m5679b("Photo message delivery status is Uploading, but the upload task does not exist.");
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6569a(Mode.upload);
                default:
                    if (file.exists()) {
                        m6569a(Mode.view);
                    } else if (HttpService.m5565a(this.f4088r) == null) {
                        m6569a(Mode.download);
                    } else {
                        m6569a(Mode.downloading);
                    }
            }
        } catch (StorageException e) {
            m6569a(Mode.crap);
        }
    }

    public void m6575y() {
        super.m6522y();
        this.f4084n.setOnClickListener(new C04071(this));
        this.f4085o.setOnClickListener(new C04092(this));
        this.f4086p.setOnClickListener(new C04103(this));
        this.v.setOnClickListener(new C04114(this));
        this.v.setOnLongClickListener(new C04125(this));
    }

    private void m6565D() {
        try {
            File file = new File(Storage.m6952g() + File.separator + this.f4088r);
            File file2 = new File(Storage.m6941b() + File.separator + this.f4088r);
            if (Storage.m6939a(this.s, file.getParentFile()) && Storage.m6939a(this.s, file2.getParentFile())) {
                m6569a(Mode.downloading);
                RequestParams requestParams = new RequestParams("token", this.f4088r);
                HttpService.m5567a(new TaskBuilder().m5590a(this.f4088r).m5585a(this.s).m5592b(Constants.m3960e()).m5589a(requestParams).m5586a(RequestType.post).m5587a(TaskPriority.medium).m5588a(new C04146(this, file, file2)).m5584a());
            }
        } catch (StorageException e) {
        }
    }

    private void m6566E() {
        String j = this.F.m4423j();
        if (j == null) {
            m6567F();
        } else if (j.startsWith("file://")) {
            try {
                File e = FileUtil.m7029e(j);
                if (e == null || !e.exists()) {
                    m6567F();
                } else {
                    m6571a(e);
                }
            } catch (StorageException e2) {
                m6567F();
            }
        } else {
            this.f4082l.setImageBitmap(BitmapUtil.m6976b(this.F.m4423j()));
            new ThumbnailUtil(this.F.m4423j(), this.F.m4407a(), this.G).execute(new Void[0]);
        }
    }

    private void m6571a(File file) {
        CustomImageLoader.m4009a().m4017a(this.f4082l, file, null);
    }

    private void m6567F() {
        this.f4082l.setImageDrawable(this.s.getResources().getDrawable(2130837826));
    }

    private void m6568G() {
        if (PermissionUtil.m7044a(PermissionType.storage) && this.F.m4421h()) {
            new Thread(new C04157(this)).start();
        }
    }

    public void onEventMainThread(ImageDownloadProgressEvent imageDownloadProgressEvent) {
        if (imageDownloadProgressEvent.m4946a().equals(this.f4088r)) {
            switch (C04168.f4073b[imageDownloadProgressEvent.m4950c().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f4087q.setProgress(imageDownloadProgressEvent.m4949b());
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    try {
                        m6571a(new File(Utils.m7079a(this.f4088r, Type.PHOTO)));
                        m6569a(Mode.view);
                    } catch (StorageException e) {
                        m6569a(Mode.crap);
                    }
                    this.f4087q.setProgress(0.0f);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    m6569a(Mode.download);
                    this.f4087q.setProgress(0.0f);
                default:
            }
        }
    }

    public void onEventMainThread(MediaUploadProgressEvent mediaUploadProgressEvent) {
        if (mediaUploadProgressEvent.m4951a().equals(this.f4088r)) {
            this.f4087q.setProgress(mediaUploadProgressEvent.m4953b());
        }
    }

    public void onEvent(BusUnregisterEvent busUnregisterEvent) {
        if (EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12794c(this);
        }
    }

    private void m6569a(Mode mode) {
        switch (C04168.f4074c[mode.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f4084n.setVisibility(0);
                this.f4085o.setVisibility(8);
                this.f4086p.setVisibility(8);
                this.f4087q.setVisibility(8);
                this.f4087q.setProgress(0.0f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f4084n.setVisibility(8);
                this.f4085o.setVisibility(0);
                this.f4086p.setVisibility(8);
                this.f4087q.setVisibility(8);
                this.f4087q.setProgress(0.0f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f4084n.setVisibility(8);
                this.f4085o.setVisibility(8);
                this.f4086p.setVisibility(0);
                this.f4087q.setVisibility(0);
            default:
                this.f4084n.setVisibility(8);
                this.f4085o.setVisibility(8);
                this.f4086p.setVisibility(8);
                this.f4087q.setVisibility(8);
                this.f4087q.setProgress(0.0f);
        }
    }
}
