package app.voip;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import app.C0110R;
import app.Main;
import app.common.AddressBookHelper;
import app.common.BaseActivity;
import app.common.CustomImageLoader;
import app.common.entity.ContactEntity;
import app.database.datasource.ContactDataSource;
import app.messaging.NormalMessagingActivity;
import app.notification.NotificationCenter;
import app.util.XMPPUtils;
import app.voip.VoipManager.State;
import app.xmpp.JabberId;
import butterknife.ButterKnife;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import se.emilsjolander.stickylistheaders.C1128R;

public class CallActivity extends BaseActivity implements SensorEventListener {
    ImageButton f4682A;
    ImageButton f4683B;
    ImageButton f4684C;
    private UiMode f4685D;
    private EndMode f4686E;
    private boolean f4687F;
    private JabberId f4688G;
    private boolean f4689H;
    private boolean f4690I;
    private boolean f4691J;
    private Handler f4692K;
    private int f4693L;
    private int f4694M;
    private AudioManager f4695N;
    private ToneGenerator f4696O;
    private MediaPlayer f4697P;
    private Timer f4698Q;
    private TimerTask f4699R;
    private SensorManager f4700S;
    private Sensor f4701T;
    private PowerManager f4702U;
    private WakeLock f4703V;
    private int f4704W;
    private Vibrator f4705X;
    private boolean f4706Y;
    ViewGroup f4707o;
    ViewGroup f4708p;
    ImageView f4709q;
    TextView f4710r;
    TextView f4711s;
    TextView f4712t;
    TextView f4713u;
    ImageButton f4714v;
    ImageButton f4715w;
    ImageButton f4716x;
    ImageButton f4717y;
    ImageButton f4718z;

    /* renamed from: app.voip.CallActivity.1 */
    class C05111 extends TimerTask {
        final /* synthetic */ CallActivity f4677a;

        /* renamed from: app.voip.CallActivity.1.1 */
        class C05101 implements Runnable {
            final /* synthetic */ String f4675a;
            final /* synthetic */ C05111 f4676b;

            C05101(C05111 c05111, String str) {
                this.f4676b = c05111;
                this.f4675a = str;
            }

            public void run() {
                this.f4676b.f4677a.f4712t.setText(this.f4675a);
            }
        }

        C05111(CallActivity callActivity) {
            this.f4677a = callActivity;
        }

        public void run() {
            this.f4677a.f4692K.post(new C05101(this, new DecimalFormat("00").format((long) (this.f4677a.f4693L / 60)) + ":" + new DecimalFormat("00").format((long) (this.f4677a.f4693L % 60))));
            this.f4677a.f4693L = this.f4677a.f4693L + 1;
        }
    }

    /* renamed from: app.voip.CallActivity.2 */
    class C05122 extends TimerTask {
        final /* synthetic */ CallActivity f4678a;

        C05122(CallActivity callActivity) {
            this.f4678a = callActivity;
        }

        public void run() {
            this.f4678a.m7139t();
            this.f4678a.finish();
        }
    }

    /* renamed from: app.voip.CallActivity.3 */
    /* synthetic */ class C05133 {
        static final /* synthetic */ int[] f4679a;
        static final /* synthetic */ int[] f4680b;
        static final /* synthetic */ int[] f4681c;

        static {
            f4681c = new int[State.values().length];
            try {
                f4681c[State.OUTGOING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4681c[State.INCOMING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            f4680b = new int[EndMode.values().length];
            try {
                f4680b[EndMode.USER_BUSY.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4680b[EndMode.USER_UNAVAILABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4680b[EndMode.NOT_INIT.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4680b[EndMode.NOT_CONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4680b[EndMode.PREMATURE_AKA_NO_XMPP_RESPONSE.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4680b[EndMode.NO_ANSWER.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4680b[EndMode.CANCELED.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4680b[EndMode.DECLINED.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4680b[EndMode.MISSED.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f4680b[EndMode.NORMAL.ordinal()] = 10;
            } catch (NoSuchFieldError e12) {
            }
            f4679a = new int[UiMode.values().length];
            try {
                f4679a[UiMode.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f4679a[UiMode.RINGING.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f4679a[UiMode.CALLING.ordinal()] = 3;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f4679a[UiMode.IN_CALL.ordinal()] = 4;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f4679a[UiMode.ENDED.ordinal()] = 5;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    public CallActivity() {
        this.f4685D = null;
        this.f4686E = null;
        this.f4687F = false;
        this.f4688G = null;
        this.f4689H = false;
        this.f4690I = false;
        this.f4691J = false;
        this.f4693L = 0;
        this.f4704W = 32;
        this.f4706Y = false;
    }

    protected void onCreate(Bundle bundle) {
        CharSequence e;
        super.onCreate(bundle);
        getWindow().addFlags(524288);
        getWindow().addFlags(2097152);
        setContentView(2130903069);
        ButterKnife.m7741a((Activity) this);
        this.f4695N = (AudioManager) getSystemService("audio");
        this.f4705X = (Vibrator) getSystemService("vibrator");
        if (this.f4695N.requestAudioFocus(null, 3, 2) == 1) {
            Main.f1926a.m5683d("Gained Audio Focus");
        }
        this.f4692K = new Handler(Looper.getMainLooper());
        this.f4698Q = new Timer();
        this.f4688G = JabberId.m7386a(getIntent().getStringExtra("extra_jabber_id"));
        if (this.f4688G == null) {
            finish();
        }
        this.f4694M = getVolumeControlStream();
        ContactEntity b = ContactDataSource.m4553a().m4570b(this.f4688G.m7387a());
        if (b != null) {
            e = b.m4196e();
            if (b.m4212o() != null) {
                CustomImageLoader.m4009a().m4020a(this.f4709q, b.m4212o(), null, 2130837596);
            } else if (b.m4211n() != null) {
                Bitmap a = AddressBookHelper.m3940a(getContentResolver(), Uri.parse(b.m4211n()), true);
                if (a != null) {
                    this.f4709q.setImageBitmap(a);
                } else {
                    this.f4709q.setImageDrawable(getResources().getDrawable(2130837596));
                }
            }
        } else {
            e = null;
        }
        if (e == null) {
            this.f4710r.setText(XMPPUtils.m7089a(this.f4688G));
            this.f4711s.setText(null);
        } else {
            this.f4710r.setText(e);
            this.f4711s.setText(XMPPUtils.m7089a(this.f4688G));
        }
        this.f4713u.setVisibility(4);
        this.f4685D = UiMode.m7254a(getIntent().getStringExtra("extra_ui_mode"));
        this.f4686E = EndMode.m7253a(getIntent().getStringExtra("extra_end_mode"));
        this.f4687F = getIntent().getBooleanExtra("extra_call_out", false);
        switch (C05133.f4679a[this.f4685D.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                m7136q();
                this.f4700S = (SensorManager) getSystemService("sensor");
                this.f4701T = this.f4700S.getDefaultSensor(8);
                this.f4702U = (PowerManager) getSystemService("power");
                try {
                    this.f4704W = PowerManager.class.getClass().getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null);
                } catch (Throwable th) {
                }
                this.f4703V = this.f4702U.newWakeLock(this.f4704W, "com.bistalk.bisphone");
            default:
                finish();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f4685D = UiMode.m7254a(intent.getStringExtra("extra_ui_mode"));
        this.f4686E = EndMode.m7253a(intent.getStringExtra("extra_end_mode"));
        this.f4687F = intent.getBooleanExtra("extra_call_out", false);
        m7136q();
    }

    protected void onResume() {
        NotificationCenter.m6606a().m6627i();
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        if (this.f4685D != UiMode.ENDED) {
            NotificationCenter.m6606a().m6618d(this.f4710r.getText() + "");
        }
        if (this.f4703V.isHeld()) {
            this.f4703V.release();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f4695N.abandonAudioFocus(null);
        setVolumeControlStream(this.f4694M);
        if (this.f4698Q != null) {
            this.f4698Q.cancel();
            this.f4698Q.purge();
            this.f4698Q = null;
        }
        m7139t();
        m7138s();
    }

    public void onBackPressed() {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f4685D != UiMode.RINGING || this.f4706Y) {
            return false;
        }
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_isDrawingListUnderStickyHeader /*24*/:
            case C0110R.styleable.Theme_actionMenuTextAppearance /*25*/:
                this.f4706Y = true;
                m7138s();
                this.f4705X.cancel();
                return true;
            default:
                return false;
        }
    }

    private void m7136q() {
        switch (C05133.f4679a[this.f4685D.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f4707o.setVisibility(0);
                this.f4708p.setVisibility(8);
                this.f4718z.setEnabled(false);
                this.f4683B.setEnabled(false);
                this.f4712t.setText(getString(2131296373));
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f4707o.setVisibility(8);
                this.f4708p.setVisibility(0);
                this.f4712t.setText(getString(2131296363));
                Uri parse = Uri.parse("android.resource://" + getPackageName() + "/raw/" + "ringtone");
                if (this.f4697P == null) {
                    this.f4697P = new MediaPlayer();
                }
                setVolumeControlStream(2);
                this.f4697P.setAudioStreamType(2);
                this.f4697P.setLooping(true);
                try {
                    this.f4697P.setDataSource(this, parse);
                    this.f4697P.prepare();
                    this.f4697P.start();
                } catch (Throwable e) {
                    Main.f1926a.m5682c(e);
                }
                if (this.f4695N.getRingerMode() != 0 && this.f4705X.hasVibrator()) {
                    this.f4705X.vibrate(new long[]{200, 1200, 1800, 1200}, 2);
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f4712t.setText(getString(2131296374));
                setVolumeControlStream(0);
                this.f4695N.setSpeakerphoneOn(false);
                if (!this.f4687F) {
                    this.f4696O = new ToneGenerator(0, 100);
                    this.f4696O.startTone(23);
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f4700S.registerListener(this, this.f4701T, 2);
                m7139t();
                setVolumeControlStream(0);
                this.f4707o.setVisibility(0);
                this.f4708p.setVisibility(8);
                this.f4718z.setEnabled(true);
                this.f4683B.setEnabled(true);
                if (VoipManager.m7265b().m7306h() == State.OUTGOING && this.f4705X.hasVibrator()) {
                    this.f4705X.vibrate(100);
                }
                this.f4699R = new C05111(this);
                this.f4698Q.scheduleAtFixedRate(this.f4699R, 0, 1000);
            default:
                m7137r();
        }
    }

    private void m7137r() {
        this.f4707o.setVisibility(8);
        this.f4708p.setVisibility(8);
        this.f4700S.unregisterListener(this);
        if (this.f4703V.isHeld()) {
            this.f4703V.release();
        }
        if (this.f4699R != null) {
            this.f4699R.cancel();
            this.f4699R = null;
        }
        m7139t();
        m7138s();
        this.f4705X.cancel();
        this.f4698Q.schedule(new C05122(this), 3000);
        this.f4712t.setText(getString(2131296375));
        Main.f1926a.m5681c(this.f4686E.toString());
        try {
            this.f4696O = new ToneGenerator(0, 100);
            switch (C05133.f4680b[this.f4686E.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f4696O.startTone(17);
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f4696O.startTone(20);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                    this.f4696O.startTone(27);
                default:
            }
        } catch (RuntimeException e) {
        }
    }

    void m7140j() {
        m7138s();
        this.f4695N.setSpeakerphoneOn(false);
        this.f4705X.cancel();
        VoipManager.m7265b().m7303e();
    }

    void m7141k() {
        m7138s();
        this.f4705X.cancel();
        VoipManager.m7265b().m7304f();
    }

    void m7142l() {
        m7141k();
        Intent intent = new Intent(this, NormalMessagingActivity.class);
        intent.putExtra("extra_jabber_id", this.f4688G.m7391e());
        startActivity(intent);
    }

    void m7143m() {
        Main.f1926a.m5681c("cancel call " + this.f4685D);
        switch (C05133.f4679a[this.f4685D.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                VoipManager.m7265b().m7301c();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                VoipManager.m7265b().m7302d();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                switch (C05133.f4681c[VoipManager.m7265b().m7306h().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        VoipManager.m7265b().m7302d();
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        VoipManager.m7265b().m7305g();
                        break;
                    default:
                        break;
                }
            default:
                return;
        }
        this.f4717y.setEnabled(false);
    }

    void m7144n() {
        this.f4689H = !this.f4689H;
        if (this.f4689H) {
            this.f4718z.setImageDrawable(getResources().getDrawable(2130837743));
        } else {
            this.f4718z.setImageDrawable(getResources().getDrawable(2130837742));
        }
        VoipManager.m7265b().m7300b(this.f4689H);
    }

    void m7145o() {
        this.f4690I = !this.f4690I;
        if (this.f4690I) {
            this.f4682A.setImageDrawable(getResources().getDrawable(2130837749));
        } else {
            this.f4682A.setImageDrawable(getResources().getDrawable(2130837748));
        }
        this.f4695N.setSpeakerphoneOn(this.f4690I);
    }

    void m7146p() {
        this.f4691J = !this.f4691J;
        if (this.f4691J) {
            this.f4683B.setImageDrawable(getResources().getDrawable(2130837746));
        } else {
            this.f4683B.setImageDrawable(getResources().getDrawable(2130837745));
        }
        VoipManager.m7265b().m7298a(this.f4691J);
    }

    private void m7138s() {
        if (this.f4697P != null) {
            if (this.f4697P.isPlaying()) {
                this.f4697P.stop();
            }
            this.f4697P.release();
            this.f4697P = null;
        }
    }

    private void m7139t() {
        if (this.f4696O != null) {
            this.f4696O.stopTone();
            this.f4696O.release();
            this.f4696O = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] >= sensorEvent.sensor.getMaximumRange()) {
            if (!this.f4703V.isHeld()) {
                this.f4703V.acquire();
            }
        } else if (this.f4703V.isHeld()) {
            this.f4703V.release();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
