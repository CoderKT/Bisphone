package app.galley.external;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.HistoryEntity.Type;
import app.galley.ActionEditText;
import app.galley.MessagePreview;
import app.http.HttpService;
import app.http.HttpTask;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.FileTaskListener;
import app.http.listener.ITaskListener;
import app.messaging.emoji.EmojiHandlerCode;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.SharedPreferencesUtil;
import app.util.Utils;
import com.loopj.android.http.RequestParams;
import com.ortiz.touch.TouchImageView;
import cz.msebera.android.httpclient.Header;
import java.io.File;
import java.io.IOException;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class GalleryImageViewPagerFragment extends Fragment implements OnClickListener {
    TouchImageView f2563a;
    boolean aj;
    boolean ak;
    boolean al;
    String am;
    GalleryCommunicator an;
    ImageView f2564b;
    ProgressBar f2565c;
    TextView f2566d;
    ActionEditText f2567e;
    ImageView f2568f;
    String f2569g;
    InputMethodManager f2570h;
    View f2571i;

    public interface GalleryCommunicator {
        void m5002a(long j, String str);

        MessagePreview m5003b(int i);
    }

    /* renamed from: app.galley.external.GalleryImageViewPagerFragment.1 */
    class C01421 implements OnEditorActionListener {
        final /* synthetic */ GalleryImageViewPagerFragment f2554a;

        C01421(GalleryImageViewPagerFragment galleryImageViewPagerFragment) {
            this.f2554a = galleryImageViewPagerFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                this.f2554a.f2564b.performClick();
            }
            return false;
        }
    }

    /* renamed from: app.galley.external.GalleryImageViewPagerFragment.2 */
    class C01432 implements OnClickListener {
        final /* synthetic */ GalleryImageViewPagerFragment f2555a;

        C01432(GalleryImageViewPagerFragment galleryImageViewPagerFragment) {
            this.f2555a = galleryImageViewPagerFragment;
        }

        public void onClick(View view) {
            boolean z = true;
            if (this.f2555a.al) {
                this.f2555a.m5050O();
                this.f2555a.an.m5002a(this.f2555a.m223g().getLong("key"), this.f2555a.f2567e.getText().toString());
                this.f2555a.f2564b.setImageResource(2130837787);
                this.f2555a.f2567e.setEnabled(false);
            } else {
                this.f2555a.m5051P();
                this.f2555a.f2567e.setEnabled(true);
                this.f2555a.f2564b.setImageResource(2130837789);
            }
            GalleryImageViewPagerFragment galleryImageViewPagerFragment = this.f2555a;
            if (this.f2555a.al) {
                z = false;
            }
            galleryImageViewPagerFragment.al = z;
        }
    }

    /* renamed from: app.galley.external.GalleryImageViewPagerFragment.3 */
    class C01443 extends FileTaskListener {
        final /* synthetic */ File f2556a;
        final /* synthetic */ String f2557b;
        final /* synthetic */ GalleryImageViewPagerFragment f2558c;

        C01443(GalleryImageViewPagerFragment galleryImageViewPagerFragment, File file, File file2, String str) {
            this.f2558c = galleryImageViewPagerFragment;
            this.f2556a = file2;
            this.f2557b = str;
            super(file);
        }

        public void m5039a(int i, Header[] headerArr, File file) {
            file.renameTo(this.f2556a);
            CustomImageLoader.m4009a().m4016a(this.f2558c.f2563a, this.f2556a);
            this.f2558c.m5045a(this.f2557b);
            this.f2558c.f2565c.setVisibility(0);
            this.f2558c.f2568f.setVisibility(8);
        }

        public void m5040a(int i, Header[] headerArr, File file, Throwable th) {
            this.f2558c.f2565c.setVisibility(8);
            this.f2558c.f2563a.setVisibility(0);
            this.f2558c.f2563a.setZoom(1.0f);
        }
    }

    /* renamed from: app.galley.external.GalleryImageViewPagerFragment.4 */
    class C01454 implements AnimationListener {
        final /* synthetic */ GalleryImageViewPagerFragment f2559a;

        C01454(GalleryImageViewPagerFragment galleryImageViewPagerFragment) {
            this.f2559a = galleryImageViewPagerFragment;
        }

        public void onAnimationStart(Animation animation) {
            this.f2559a.f2566d.setVisibility(0);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f2559a.f2566d.clearAnimation();
        }
    }

    /* renamed from: app.galley.external.GalleryImageViewPagerFragment.5 */
    class C01465 implements AnimationListener {
        final /* synthetic */ GalleryImageViewPagerFragment f2560a;

        C01465(GalleryImageViewPagerFragment galleryImageViewPagerFragment) {
            this.f2560a = galleryImageViewPagerFragment;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f2560a.f2566d.clearAnimation();
            this.f2560a.f2566d.setVisibility(8);
        }
    }

    /* renamed from: app.galley.external.GalleryImageViewPagerFragment.6 */
    class C01476 implements Runnable {
        final /* synthetic */ String f2561a;
        final /* synthetic */ GalleryImageViewPagerFragment f2562b;

        C01476(GalleryImageViewPagerFragment galleryImageViewPagerFragment, String str) {
            this.f2562b = galleryImageViewPagerFragment;
            this.f2561a = str;
        }

        public void run() {
            if (SharedPreferencesUtil.m7060b(this.f2562b.am, Boolean.valueOf(false))) {
                try {
                    String a = Utils.m7079a(this.f2561a, Type.PHOTO);
                    String str = Storage.m6956j() + File.separator + this.f2561a + ".jpg";
                    if (!this.f2562b.m5047b(str) && this.f2562b.ak && FileUtil.m7023a(a, str, false)) {
                        MediaScannerConnection.scanFile(Main.f1927b, new String[]{str}, null, null);
                    }
                } catch (IOException e) {
                } catch (StorageException e2) {
                }
            }
        }
    }

    public GalleryImageViewPagerFragment() {
        this.al = false;
    }

    public static GalleryImageViewPagerFragment m5043a(int i, long j, GalleryCommunicator galleryCommunicator, boolean z, int i2, boolean z2) {
        GalleryImageViewPagerFragment galleryImageViewPagerFragment = new GalleryImageViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DataPacketExtension.ELEMENT, i);
        bundle.putInt("message_type", i2);
        bundle.putLong("key", j);
        bundle.putBoolean("view", !z);
        bundle.putBoolean("message_is_incoming", z2);
        galleryImageViewPagerFragment.m224g(bundle);
        galleryImageViewPagerFragment.m5055a(galleryCommunicator);
        return galleryImageViewPagerFragment;
    }

    public void m5055a(GalleryCommunicator galleryCommunicator) {
        this.an = galleryCommunicator;
    }

    public View m5052a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903144, viewGroup, false);
    }

    public void m5054a(View view, Bundle bundle) {
        super.m199a(view, bundle);
        this.f2570h = (InputMethodManager) m227i().getSystemService("input_method");
        this.ak = m223g().getBoolean("message_is_incoming");
        this.am = m229j().getString(2131296917);
        this.f2563a = (TouchImageView) view.findViewById(2131755285);
        this.f2565c = (ProgressBar) view.findViewById(2131755405);
        this.f2568f = (ImageView) view.findViewById(2131755406);
        this.f2564b = (ImageView) view.findViewById(2131755292);
        this.f2566d = (TextView) view.findViewById(2131755407);
        this.f2567e = (ActionEditText) view.findViewById(2131755408);
        this.f2571i = view.findViewById(2131755403);
        this.f2565c.setVisibility(8);
        this.f2568f.setVisibility(8);
        this.f2566d.setOnClickListener(this);
        this.f2563a.setOnClickListener(this);
        this.f2568f.setOnClickListener(this);
        this.f2567e.setOnEditorActionListener(new C01421(this));
        this.f2564b.setOnClickListener(new C01432(this));
        m5053a();
        this.aj = true;
    }

    public void m5056e(boolean z) {
        super.m219e(z);
        if (z) {
            m5053a();
        }
    }

    public void m5053a() {
        if (this.f2563a != null && this.an != null) {
            if (m223g().getBoolean("view")) {
                CustomImageLoader.m4009a().m4018a(this.f2563a, Uri.decode(Uri.fromFile(new File(this.an.m5003b(m223g().getInt(DataPacketExtension.ELEMENT)).m4997a())).toString()));
            } else {
                m5041Q();
            }
            this.f2569g = this.an.m5003b(m223g().getInt(DataPacketExtension.ELEMENT)).m4998b();
            if (this.f2569g != null && this.f2569g.length() > 0 && !m223g().getBoolean("view")) {
                this.f2566d.setVisibility(0);
                this.f2571i.setVisibility(8);
                EmojiHandlerCode.m6125a().m6130a(this.f2569g, this.f2566d, EmojiHandlerCode.m6125a().f3543b, m227i());
            } else if (m223g().getBoolean("view")) {
                this.f2566d.setVisibility(8);
                this.f2571i.setVisibility(0);
                this.f2567e.setText(this.f2569g);
            } else {
                this.f2571i.setVisibility(8);
                this.f2566d.setVisibility(8);
            }
        }
    }

    private void m5041Q() {
        if (this.f2563a != null) {
            if (m223g() == null || this.an.m5003b(m223g().getInt(DataPacketExtension.ELEMENT)) == null) {
                this.f2565c.setVisibility(0);
            } else {
                m5042R();
            }
        }
    }

    private void m5042R() {
        String a = this.an.m5003b(m223g().getInt(DataPacketExtension.ELEMENT)).m4997a();
        try {
            File file = new File(Utils.m7079a(a, Type.PHOTO));
            File file2 = new File(Storage.m6952g() + File.separator + a);
            if (file.exists()) {
                CustomImageLoader.m4009a().m4016a(this.f2563a, file);
                return;
            }
            HttpTask a2 = HttpService.m5565a(a);
            ITaskListener c01443 = new C01443(this, file2, file, a);
            if (a2 != null) {
                a2.m5575a(c01443);
                return;
            }
            HttpService.m5567a(new TaskBuilder().m5590a(a).m5585a(null).m5592b(Utils.m7082b(a)).m5589a(new RequestParams("token", a)).m5586a(RequestType.post).m5587a(TaskPriority.medium).m5588a(c01443).m5584a());
        } catch (StorageException e) {
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755406:
                m5042R();
            default:
                if (this.f2569g != null && this.f2569g.length() > 0) {
                    if (this.aj) {
                        m5049N();
                    } else {
                        m5048M();
                    }
                    this.aj = !this.aj;
                }
        }
    }

    public void m5048M() {
        Animation loadAnimation = AnimationUtils.loadAnimation(m227i().getApplicationContext(), 2130968603);
        loadAnimation.setDuration(400);
        loadAnimation.setFillAfter(true);
        loadAnimation.setFillEnabled(true);
        this.f2566d.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new C01454(this));
    }

    public void m5049N() {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 5.2f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        this.f2566d.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new C01465(this));
    }

    private void m5045a(String str) {
        new Thread(new C01476(this, str)).start();
    }

    private boolean m5047b(String str) {
        return new File(str).exists();
    }

    protected void m5050O() {
        this.f2570h.hideSoftInputFromWindow(m237p().getWindowToken(), 0);
    }

    protected void m5051P() {
        this.f2567e.requestFocus();
        this.f2570h.showSoftInput(this.f2567e, 2);
        m227i().getWindow().setSoftInputMode(4);
    }
}
