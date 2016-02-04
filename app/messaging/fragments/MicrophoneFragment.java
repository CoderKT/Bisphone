package app.messaging.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import app.Main;
import app.common.AudioPlayer;
import app.messaging.AudioRecorder;
import app.messaging.RecycleMessagingAdapter;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.StringUtil;
import app.view.CircularProgressView;
import app.view.CircularProgressView.ProgressChangeListener;
import java.io.File;
import java.io.IOException;

public class MicrophoneFragment extends Fragment {
    private static MicrophoneFragment f3569a;
    private RecycleFragmentsListener f3570b;
    private AudioRecorder f3571c;
    private View f3572d;
    private CircularProgressView f3573e;

    /* renamed from: app.messaging.fragments.MicrophoneFragment.1 */
    class C03111 implements ProgressChangeListener {
        final /* synthetic */ MicrophoneFragment f3567a;

        C03111(MicrophoneFragment microphoneFragment) {
            this.f3567a = microphoneFragment;
        }

        public void m6158a() {
            if (this.f3567a.m234m()) {
                this.f3567a.f3573e.m7113c();
                this.f3567a.f3573e.m7110a();
                this.f3567a.f3573e.setInnerCircleColor(this.f3567a.m229j().getColor(2131689536));
                this.f3567a.f3573e.setBitmap(2130837778);
                if (this.f3567a.f3571c != null) {
                    this.f3567a.f3571c.m5698a();
                }
                RecycleMessagingAdapter.m5956b(false);
                this.f3567a.f3570b.m5708e(this.f3567a.f3571c.m5700b().getAbsolutePath());
            }
        }
    }

    /* renamed from: app.messaging.fragments.MicrophoneFragment.2 */
    class C03122 implements OnTouchListener {
        final /* synthetic */ MicrophoneFragment f3568a;

        C03122(MicrophoneFragment microphoneFragment) {
            this.f3568a = microphoneFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!Storage.m6942b(this.f3568a.m225h())) {
                Storage.m6945c(this.f3568a.m225h());
            } else if (motionEvent.getAction() == 0) {
                if (!PermissionUtil.m7044a(PermissionType.microphone)) {
                    PermissionUtil.m7042a(this.f3568a.m227i(), PermissionType.microphone, 5);
                    return true;
                } else if (!this.f3568a.f3573e.m7111a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    return true;
                } else {
                    this.f3568a.f3573e.setInnerCircleColor(this.f3568a.m229j().getColor(2131689521));
                    this.f3568a.f3573e.m7110a();
                    this.f3568a.f3573e.setBitmap(2130837779);
                    this.f3568a.f3573e.m7112b();
                    if (this.f3568a.f3571c == null) {
                        this.f3568a.f3571c = new AudioRecorder();
                    }
                    this.f3568a.m6161O();
                    return true;
                }
            } else if (motionEvent.getAction() == 1) {
                if (!this.f3568a.f3573e.m7111a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    return true;
                }
                this.f3568a.f3573e.m7113c();
                this.f3568a.f3573e.m7110a();
                this.f3568a.f3573e.setInnerCircleColor(this.f3568a.m229j().getColor(2131689536));
                this.f3568a.f3573e.setBitmap(2130837778);
                if (this.f3568a.f3571c != null) {
                    this.f3568a.f3571c.m5698a();
                }
                RecycleMessagingAdapter.m5956b(false);
                if (this.f3568a.f3573e.getRecordTime() <= 0) {
                    return true;
                }
                this.f3568a.f3570b.m5708e(this.f3568a.f3571c.m5700b().getAbsolutePath());
                return true;
            } else if (motionEvent.getAction() == 2 && !this.f3568a.f3573e.m7111a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.f3568a.f3573e.m7113c();
                this.f3568a.f3573e.setInnerCircleColor(this.f3568a.m229j().getColor(2131689536));
                this.f3568a.f3573e.setBitmap(2130837778);
                this.f3568a.f3573e.m7110a();
                if (this.f3568a.f3571c != null) {
                    this.f3568a.f3571c.m5698a();
                }
            }
            return false;
        }
    }

    public static synchronized MicrophoneFragment m6163a() {
        MicrophoneFragment microphoneFragment;
        synchronized (MicrophoneFragment.class) {
            if (f3569a == null) {
                f3569a = new MicrophoneFragment();
            }
            microphoneFragment = f3569a;
        }
        return microphoneFragment;
    }

    public static void m6159M() {
        if (f3569a != null) {
            f3569a = null;
        }
    }

    public void m6170a(Bundle bundle) {
        super.m195a(bundle);
    }

    public View m6168a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3572d = layoutInflater.inflate(2130903139, viewGroup, false);
        this.f3573e = (CircularProgressView) this.f3572d.findViewById(2131755387);
        this.f3573e.m7110a();
        m6160N();
        return this.f3572d;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f3573e.m7110a();
        if (this.f3571c != null) {
            this.f3571c.m5698a();
        }
    }

    private void m6160N() {
        this.f3573e.setProgressChangeListener(new C03111(this));
        this.f3573e.setOnTouchListener(new C03122(this));
    }

    private void m6161O() {
        Throwable e;
        AudioPlayer.m3946a().m3953b();
        RecycleMessagingAdapter.m5956b(true);
        ((Vibrator) m227i().getSystemService("vibrator")).vibrate(80);
        try {
            String a = StringUtil.m7062a();
            if (a.length() > 10) {
                a = a.substring(0, 9);
            }
            this.f3571c.m5699a(new File(Storage.m6948e() + File.separator + a));
        } catch (StorageException e2) {
            e = e2;
            Main.f1926a.m5680b(e);
        } catch (IOException e3) {
            e = e3;
            Main.f1926a.m5680b(e);
        } catch (IllegalStateException e4) {
            e = e4;
            Main.f1926a.m5680b(e);
        }
    }

    public void m6169a(Activity activity) {
        super.m188a(activity);
        try {
            this.f3570b = (RecycleFragmentsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement recycleFragmentsListener");
        }
    }

    public void m6171b() {
        super.m204b();
        this.f3570b = null;
    }

    public void m6172e(Bundle bundle) {
    }
}
