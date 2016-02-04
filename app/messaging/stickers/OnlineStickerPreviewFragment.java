package app.messaging.stickers;

import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.StickerPackEntity;
import app.database.datasource.StickerDataSource;
import app.events.sticker.StickerPackDownloadedEvent;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.logger.ILog;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.Background;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import de.greenrobot.event.EventBus;
import java.io.File;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class OnlineStickerPreviewFragment extends Fragment implements OnClickListener {
    OnlinePackEntity f3914a;
    TextView f3915b;
    TextView f3916c;
    ImageView f3917d;
    TextView f3918e;
    TextView f3919f;
    View f3920g;
    View f3921h;
    View f3922i;
    Handler f3923j;
    StickerCommunicator f3924k;
    private String f3925l;

    /* renamed from: app.messaging.stickers.OnlineStickerPreviewFragment.1 */
    class C03671 extends FileTaskListener {
        final /* synthetic */ File f3908a;
        final /* synthetic */ OnlineStickerPreviewFragment f3909b;

        /* renamed from: app.messaging.stickers.OnlineStickerPreviewFragment.1.1 */
        class C03651 implements Runnable {
            final /* synthetic */ C03671 f3906a;

            C03651(C03671 c03671) {
                this.f3906a = c03671;
            }

            public void run() {
                if (this.f3906a.f3909b.isAdded()) {
                    this.f3906a.f3909b.f3925l = this.f3906a.f3908a.getAbsolutePath();
                    this.f3906a.f3909b.f3919f.setVisibility(8);
                    this.f3906a.f3909b.m6444a(this.f3906a.f3908a);
                }
            }
        }

        /* renamed from: app.messaging.stickers.OnlineStickerPreviewFragment.1.2 */
        class C03662 implements Runnable {
            final /* synthetic */ C03671 f3907a;

            C03662(C03671 c03671) {
                this.f3907a = c03671;
            }

            public void run() {
                if (this.f3907a.f3909b.isAdded()) {
                    this.f3907a.f3909b.f3917d.setImageDrawable(this.f3907a.f3909b.getResources().getDrawable(2130837804));
                    this.f3907a.f3909b.f3919f.setVisibility(0);
                    this.f3907a.f3909b.f3917d.setAdjustViewBounds(false);
                }
            }
        }

        C03671(OnlineStickerPreviewFragment onlineStickerPreviewFragment, File file, File file2) {
            this.f3909b = onlineStickerPreviewFragment;
            this.f3908a = file2;
            super(file);
        }

        public void m6434a(int i, Header[] headerArr, File file) {
            this.f3909b.f3923j.post(new C03651(this));
        }

        public void m6435a(int i, Header[] headerArr, File file, Throwable th) {
            Main.f1926a.m5685e("Sticker Pack Preview Download Failed");
            this.f3909b.f3923j.post(new C03662(this));
        }
    }

    /* renamed from: app.messaging.stickers.OnlineStickerPreviewFragment.2 */
    class C03682 implements ImageLoadingListener {
        final /* synthetic */ File f3910a;
        final /* synthetic */ OnlineStickerPreviewFragment f3911b;

        C03682(OnlineStickerPreviewFragment onlineStickerPreviewFragment, File file) {
            this.f3911b = onlineStickerPreviewFragment;
            this.f3910a = file;
        }

        public void m6436a(String str, View view) {
            this.f3911b.f3917d.setImageDrawable(this.f3911b.getResources().getDrawable(2130837804));
        }

        public void m6438a(String str, View view, FailReason failReason) {
            if (this.f3911b.isAdded()) {
                this.f3911b.f3917d.setImageDrawable(this.f3911b.getResources().getDrawable(2130837804));
                this.f3911b.f3919f.setVisibility(0);
                this.f3911b.f3917d.setAdjustViewBounds(false);
            }
            if (FailType.DECODING_ERROR.equals(failReason.m11166a())) {
                this.f3910a.delete();
                this.f3911b.m6452a();
            }
        }

        public void m6437a(String str, View view, Bitmap bitmap) {
        }

        public void m6439b(String str, View view) {
        }
    }

    /* renamed from: app.messaging.stickers.OnlineStickerPreviewFragment.3 */
    class C03693 implements DialogInterface.OnClickListener {
        final /* synthetic */ OnlineStickerPreviewFragment f3912a;

        C03693(OnlineStickerPreviewFragment onlineStickerPreviewFragment) {
            this.f3912a = onlineStickerPreviewFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: app.messaging.stickers.OnlineStickerPreviewFragment.4 */
    class C03704 implements DialogInterface.OnClickListener {
        final /* synthetic */ OnlineStickerPreviewFragment f3913a;

        C03704(OnlineStickerPreviewFragment onlineStickerPreviewFragment) {
            this.f3913a = onlineStickerPreviewFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (StickerDataSource.m4750a(this.f3913a.f3914a.f3901a) > 0) {
            }
            if (StickerManager.m6491b().m6492a() == this.f3913a.f3914a.f3901a) {
                StickerManager.m6491b().m6493a(100);
            }
            this.f3913a.f3914a.f3904d = Boolean.valueOf(false);
            this.f3913a.m6445a(this.f3913a.f3914a.f3904d, this.f3913a.f3918e);
            this.f3913a.f3924k.m6440a(this.f3913a.f3914a.f3901a);
        }
    }

    public interface StickerCommunicator {
        void m6440a(int i);
    }

    public void m6453a(StickerCommunicator stickerCommunicator) {
        this.f3924k = stickerCommunicator;
    }

    public OnlineStickerPreviewFragment() {
        this.f3925l = "";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3923j = new Handler();
        if (getArguments() == null) {
        }
    }

    public void onResume() {
        EventBus.m12779a().m12791a((Object) this);
        super.onResume();
    }

    public void onPause() {
        EventBus.m12779a().m12794c(this);
        super.onPause();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3920g = layoutInflater.inflate(2130903141, viewGroup, false);
        this.f3921h = this.f3920g.findViewById(2131755391);
        this.f3922i = this.f3920g.findViewById(2131755395);
        m6450d();
        m6449c();
        m6448b();
        return this.f3920g;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6450d();
        m6449c();
        m6448b();
        if (this.f3925l != null && !this.f3925l.isEmpty()) {
            m6446a(this.f3925l);
        }
    }

    private void m6448b() {
        this.f3914a = (OnlinePackEntity) getArguments().getParcelable(DataPacketExtension.ELEMENT);
        if (HttpService.m5565a("" + this.f3914a.f3901a) != null) {
            this.f3914a.f3904d = null;
        }
        m6445a(this.f3914a.f3904d, this.f3918e);
        this.f3915b.setText(this.f3914a.f3902b);
        this.f3916c.setText(this.f3914a.f3903c);
        this.f3919f.setVisibility(8);
        m6452a();
    }

    private void m6449c() {
        if (getResources().getConfiguration().orientation == 1) {
            this.f3918e = (TextView) this.f3920g.findViewById(2131755394);
            this.f3915b = (TextView) this.f3920g.findViewById(2131755392);
            this.f3916c = (TextView) this.f3920g.findViewById(2131755393);
        } else {
            this.f3918e = (TextView) this.f3920g.findViewById(2131755396);
            this.f3915b = (TextView) this.f3920g.findViewById(2131755397);
            this.f3916c = (TextView) this.f3920g.findViewById(2131755398);
        }
        this.f3919f = (TextView) this.f3920g.findViewById(2131755400);
        this.f3917d = (ImageView) this.f3920g.findViewById(2131755399);
        this.f3918e.setOnClickListener(this);
    }

    private void m6450d() {
        if (getResources().getConfiguration().orientation == 1) {
            this.f3921h.setVisibility(0);
            this.f3922i.setVisibility(8);
            return;
        }
        this.f3921h.setVisibility(8);
        this.f3922i.setVisibility(0);
    }

    private void m6445a(Boolean bool, TextView textView) {
        if (!isAdded()) {
            return;
        }
        if (bool == null) {
            textView.setText(getResources().getString(2131296433));
            Background.m6968a(getResources().getDrawable(2130837932), textView);
            textView.setTextColor(getResources().getColor(2131689548));
        } else if (!bool.booleanValue()) {
            textView.setText(getResources().getString(2131296431));
            Background.m6968a(getResources().getDrawable(2130837930), textView);
            textView.setTextColor(getResources().getColor(2131689550));
        } else if (m6447a(this.f3914a.f3901a)) {
            textView.setText(getResources().getString(2131296432));
            Background.m6968a(getResources().getDrawable(2130837931), textView);
            textView.setTextColor(getResources().getColor(2131689548));
        } else {
            textView.setText(getResources().getString(2131296668));
            Background.m6968a(getResources().getDrawable(2130837933), textView);
            textView.setTextColor(getResources().getColor(2131689550));
        }
    }

    public void m6452a() {
        try {
            File file = new File(StickerPackEntity.m4479d(this.f3914a.f3901a));
            if (!Storage.m6939a(Main.f1927b, file.getParentFile()) || !isAdded()) {
                return;
            }
            if (file.exists()) {
                m6444a(file);
                return;
            }
            String str = this.f3914a.f3901a + ".preview";
            if (HttpService.m5565a(str) == null) {
                Header[] headerArr = new Header[]{new BasicHeader("Accept", "image/png")};
                RequestParams requestParams = new RequestParams();
                requestParams.m10743a("id", this.f3914a.f3901a + "");
                requestParams.m10743a("density", LocalPacksManager.m6428b(getActivity()) + "");
                HttpService.m5567a(new TaskBuilder().m5590a(str).m5585a(getActivity()).m5592b("https://chatng.bisphone.com:443/api/v1/sticker/file").m5591a(headerArr).m5589a(requestParams).m5586a(RequestType.get).m5587a(TaskPriority.medium).m5588a(new C03671(this, file, file)).m5584a());
            }
        } catch (StorageException e) {
        }
    }

    private void m6444a(File file) {
        m6446a(file.getAbsolutePath());
        CustomImageLoader.m4009a().m4017a(this.f3917d, file, new C03682(this, file));
    }

    private void m6446a(String str) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i = options.outHeight;
        int i2 = options.outWidth;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        LayoutParams layoutParams = this.f3917d.getLayoutParams();
        if (i2 == 0) {
            i2 = i3;
        }
        layoutParams.width = i3;
        layoutParams.height = (i3 * i) / i2;
        this.f3917d.setLayoutParams(layoutParams);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onClick(View view) {
        if (this.f3914a != null && this.f3914a.f3904d != null) {
            if (this.f3914a.f3904d.booleanValue()) {
                m6451e();
                return;
            }
            StickerManager.m6491b().m6497a(getActivity(), this.f3914a.f3901a, true);
            this.f3914a.f3904d = null;
            m6445a(this.f3914a.f3904d, this.f3918e);
        }
    }

    private void m6451e() {
        if (!m6447a(this.f3914a.f3901a)) {
            new Builder(getActivity(), 2131558538).setTitle(getString(2131296428)).setMessage(String.format(getString(2131296424), new Object[]{this.f3914a.f3902b})).setPositiveButton(17039379, new C03704(this)).setNegativeButton(17039369, new C03693(this)).setIcon(2130837731).show();
        }
    }

    private boolean m6447a(int i) {
        boolean z = false;
        for (int i2 = 0; i2 < LocalPacksManager.f3895a.length; i2++) {
            boolean z2;
            ILog iLog = Main.f1926a;
            StringBuilder append = new StringBuilder().append("local is ").append(LocalPacksManager.f3895a[i2]).append(" And this pack is ").append(i).append(" and result is ");
            if (i == LocalPacksManager.f3895a[i2]) {
                z2 = true;
            } else {
                z2 = false;
            }
            iLog.m5685e(append.append(z2).toString());
            if (i == LocalPacksManager.f3895a[i2]) {
                z = true;
            }
        }
        return z;
    }

    public void onEventMainThread(StickerPackDownloadedEvent stickerPackDownloadedEvent) {
        if (this.f3914a.f3901a == stickerPackDownloadedEvent.f2494a) {
            this.f3914a.f3904d = Boolean.valueOf(stickerPackDownloadedEvent.m4954a());
            m6445a(this.f3914a.f3904d, this.f3918e);
        }
    }
}
