package app.voip;

import android.content.res.AssetManager;
import app.Main;
import app.util.FileUtil;
import app.voip.SipAccount.IncomingCallListener;
import app.voip.SipAccount.RegStateListener;
import app.voip.SipCall.CallMediaStateListener;
import app.voip.SipCall.CallStateListener;
import app.voip.SipEndpoint.OnTransportStateChangeListener;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AccountInfo;
import org.pjsip.pjsua2.AudioMedia;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.CallMediaInfoVector;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.CallSetting;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.OnCallMediaStateParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnRegStateParam;
import org.pjsip.pjsua2.StringVector;
import org.pjsip.pjsua2.TlsConfig;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.pjmedia_srtp_use;
import org.pjsip.pjsua2.pjmedia_type;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_ssl_method;
import org.pjsip.pjsua2.pjsip_status_code;
import org.pjsip.pjsua2.pjsip_transport_state;
import org.pjsip.pjsua2.pjsip_transport_type_e;
import org.pjsip.pjsua2.pjsua_call_media_status;

public class Sip {
    public static boolean f4801a;
    static SipEndpoint f4802b;
    static SipCall f4803j;
    private static boolean f4804k;
    private static boolean f4805l;
    private static ThreadPoolExecutor f4806n;
    EpConfig f4807c;
    TransportConfig f4808d;
    AccountConfig f4809e;
    AuthCredInfo f4810f;
    SipAccount f4811g;
    String f4812h;
    String f4813i;
    private SipCallbacks f4814m;
    private int f4815o;

    /* renamed from: app.voip.Sip.1 */
    class C05201 implements Runnable {
        final /* synthetic */ Sip f4791a;

        C05201(Sip sip) {
            this.f4791a = sip;
        }

        public void run() {
            try {
                this.f4791a.m7218m();
                Main.f1926a.m5683d("Sip Initialized");
                Sip.f4801a = true;
            } catch (Throwable e) {
                Main.f1926a.m5683d("Wooops! Init Exception");
                try {
                    Main.f1926a.m5684d(e);
                } catch (NullPointerException e2) {
                }
                this.f4791a.m7232l();
                Sip.f4801a = false;
            }
            if (this.f4791a.f4814m != null) {
                this.f4791a.f4814m.m7208a(Sip.f4801a);
            }
        }
    }

    /* renamed from: app.voip.Sip.2 */
    class C05212 implements OnTransportStateChangeListener {
        final /* synthetic */ Sip f4792a;

        C05212(Sip sip) {
            this.f4792a = sip;
        }

        public void m7198a(pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state) {
            EventBus.m12779a().m12796e(org_pjsip_pjsua2_pjsip_transport_state);
            if (this.f4792a.f4814m != null) {
                this.f4792a.f4814m.m7207a(org_pjsip_pjsua2_pjsip_transport_state);
            }
        }
    }

    /* renamed from: app.voip.Sip.3 */
    class C05223 implements Runnable {
        final /* synthetic */ Sip f4793a;

        C05223(Sip sip) {
            this.f4793a = sip;
        }

        public void run() {
            try {
                this.f4793a.m7219n();
            } catch (Throwable e) {
                try {
                    Main.f1926a.m5684d(e);
                } catch (NullPointerException e2) {
                }
            }
        }
    }

    /* renamed from: app.voip.Sip.4 */
    class C05234 implements IncomingCallListener {
        final /* synthetic */ Sip f4794a;

        C05234(Sip sip) {
            this.f4794a = sip;
        }

        public void m7200a(OnIncomingCallParam onIncomingCallParam) {
            CallOpParam callOpParam;
            if (Sip.f4803j == null || !Sip.f4803j.isActive()) {
                Main.f1926a.m5681c("Incoming Call");
                callOpParam = new CallOpParam(true);
                callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_RINGING);
                Sip.f4803j = new SipCall(this.f4794a.f4811g, onIncomingCallParam.getCallId());
                this.f4794a.m7220o();
                try {
                    Sip.f4803j.answer(callOpParam);
                    if (this.f4794a.f4814m != null) {
                        this.f4794a.f4814m.m7204a(Sip.f4803j);
                        return;
                    }
                    return;
                } catch (Throwable e) {
                    Main.f1926a.m5683d("Wooops! Incoming Call Exception");
                    try {
                        Main.f1926a.m5684d(e);
                    } catch (NullPointerException e2) {
                    }
                    Sip.f4803j = null;
                    return;
                }
            }
            Main.f1926a.m5681c("Incoming Call, Auto Reject with Busy Signal");
            callOpParam = new CallOpParam(true);
            callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_BUSY_HERE);
            try {
                SipCall sipCall = new SipCall(this.f4794a.f4811g, onIncomingCallParam.getCallId());
                sipCall.hangup(callOpParam);
                sipCall.delete();
            } catch (Throwable e3) {
                Main.f1926a.m5683d("Wooops! Incoming Call Reject Exception");
                try {
                    Main.f1926a.m5684d(e3);
                } catch (NullPointerException e4) {
                }
            }
        }
    }

    /* renamed from: app.voip.Sip.5 */
    class C05245 implements RegStateListener {
        final /* synthetic */ Sip f4795a;

        C05245(Sip sip) {
            this.f4795a = sip;
        }

        public void m7202a(OnRegStateParam onRegStateParam) {
            try {
                AccountInfo info = this.f4795a.f4811g.getInfo();
                if (this.f4795a.f4814m != null) {
                    this.f4795a.f4814m.m7203a(info);
                }
                Main.f1926a.m5681c("Register State = " + info.getRegIsActive());
                Main.f1926a.m5681c("Register Code  = " + onRegStateParam.getCode());
            } catch (Throwable e) {
                Main.f1926a.m5683d("Wooops! RegState AccountInfo Exception");
                try {
                    Main.f1926a.m5684d(e);
                } catch (NullPointerException e2) {
                }
            }
        }
    }

    /* renamed from: app.voip.Sip.6 */
    class C05256 implements Runnable {
        final /* synthetic */ String f4796a;
        final /* synthetic */ Sip f4797b;

        C05256(Sip sip, String str) {
            this.f4797b = sip;
            this.f4796a = str;
        }

        public void run() {
            if (Sip.f4802b != null) {
                if (Sip.f4803j != null && Sip.f4803j.isActive()) {
                    this.f4797b.m7225e();
                }
                Sip.f4803j = new SipCall(this.f4797b.f4811g, -1);
                CallOpParam callOpParam = new CallOpParam();
                CallSetting opt = callOpParam.getOpt();
                opt.setAudioCount(1);
                opt.setVideoCount(0);
                this.f4797b.m7220o();
                try {
                    if (this.f4797b.f4811g == null) {
                        Main.f1926a.m5681c("account state: online " + this.f4797b.f4811g.getInfo().getOnlineStatus());
                        return;
                    }
                    Sip.f4803j.makeCall("sip:" + this.f4796a + ";transport=tls", callOpParam);
                    Main.f1926a.m5681c("Calling " + this.f4796a);
                } catch (Throwable e) {
                    Main.f1926a.m5683d("Wooops! Call Exception");
                    try {
                        Main.f1926a.m5684d(e);
                    } catch (NullPointerException e2) {
                    }
                    Sip.f4803j = null;
                }
            }
        }
    }

    /* renamed from: app.voip.Sip.7 */
    class C05267 implements Runnable {
        final /* synthetic */ Sip f4798a;

        C05267(Sip sip) {
            this.f4798a = sip;
        }

        public void run() {
            if (Sip.f4802b != null) {
                if (Sip.f4803j == null || !Sip.f4803j.isActive()) {
                    Main.f1926a.m5683d("No Calls Active! Nothing to Hang");
                    return;
                }
                CallOpParam callOpParam = new CallOpParam(true);
                callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_DECLINE);
                try {
                    Sip.f4803j.hangup(callOpParam);
                    Main.f1926a.m5681c("Hung up");
                } catch (Throwable e) {
                    Main.f1926a.m5683d("Wooops! Hangup Exception");
                    try {
                        Main.f1926a.m5684d(e);
                    } catch (NullPointerException e2) {
                    }
                }
            }
        }
    }

    /* renamed from: app.voip.Sip.8 */
    class C05278 implements Runnable {
        final /* synthetic */ Sip f4799a;

        C05278(Sip sip) {
            this.f4799a = sip;
        }

        public void run() {
            if (Sip.f4802b != null) {
                CallOpParam callOpParam = new CallOpParam(true);
                callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_OK);
                try {
                    Sip.f4803j.answer(callOpParam);
                    Main.f1926a.m5681c("Call Answered");
                } catch (Throwable e) {
                    Main.f1926a.m5683d("Wooops! Incoming Call Answer Exception");
                    try {
                        Main.f1926a.m5684d(e);
                    } catch (NullPointerException e2) {
                    }
                }
            }
        }
    }

    /* renamed from: app.voip.Sip.9 */
    class C05289 implements Runnable {
        final /* synthetic */ Sip f4800a;

        C05289(Sip sip) {
            this.f4800a = sip;
        }

        public void run() {
            if (Sip.f4802b != null) {
                CallOpParam callOpParam = new CallOpParam(true);
                callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_REQUEST_UPDATED);
                try {
                    Sip.f4803j.setHold(callOpParam);
                } catch (Throwable e) {
                    try {
                        Main.f1926a.m5684d(e);
                    } catch (NullPointerException e2) {
                    }
                }
            }
        }
    }

    public interface SipCallbacks {
        void m7203a(AccountInfo accountInfo);

        void m7204a(Call call);

        void m7205a(CallInfo callInfo);

        void m7206a(CallMediaInfo callMediaInfo);

        void m7207a(pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state);

        void m7208a(boolean z);
    }

    public Sip() {
        this.f4815o = -1;
    }

    static {
        Throwable e;
        f4804k = false;
        f4805l = false;
        String property = System.getProperty("os.arch");
        if (property.startsWith("armv7")) {
            property = "armeabi-v7a";
            f4804k = true;
        } else if (property.startsWith("arm") || property.startsWith("aarch64")) {
            property = "armeabi";
            f4804k = true;
        } else if (property.startsWith("i686")) {
            property = "x86";
            f4804k = true;
        } else {
            Main.f1926a.m5679b(Main.f1927b.getString(2131296867));
            property = null;
        }
        String str = Main.f1927b.getFilesDir().getPath() + File.separator;
        new File(str).mkdirs();
        AssetManager assets = Main.f1927b.getResources().getAssets();
        int a = FileUtil.m7020a(assets, property + File.separator + "libssl.so.1.0.0", str + "libssl.so.1.0.0") & FileUtil.m7020a(assets, property + File.separator + "libcrypto.so.1.0.0", str + "libcrypto.so.1.0.0");
        if (f4804k && a != 0) {
            try {
                System.load(str + "libcrypto.so.1.0.0");
                System.load(str + "libssl.so.1.0.0");
                Main.f1926a.m5681c("Sip: Loaded " + property + " openssl libs");
                System.loadLibrary("pjsua2");
                f4805l = true;
            } catch (UnsatisfiedLinkError e2) {
                e = e2;
                Main.f1926a.m5680b(e);
                f4806n = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(15, false));
                f4801a = false;
                f4803j = null;
            } catch (Exception e3) {
                e = e3;
                Main.f1926a.m5680b(e);
                f4806n = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(15, false));
                f4801a = false;
                f4803j = null;
            }
        }
        f4806n = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(15, false));
        f4801a = false;
        f4803j = null;
    }

    public static boolean m7211a() {
        return f4804k;
    }

    public static boolean m7213b() {
        return f4805l;
    }

    public static boolean m7215c() {
        return f4806n.getQueue().size() > 5;
    }

    public synchronized void m7223a(String str, String str2) {
        if (!f4804k) {
            throw new UnsupportedOperationException("Architecture " + System.getProperty("os.arch") + " not supported!");
        } else if (f4805l) {
            this.f4812h = str;
            this.f4813i = str2;
            f4806n.execute(new C05201(this));
        } else {
            throw new UnsupportedOperationException("Could not load libraries");
        }
    }

    private void m7218m() {
        f4802b = new SipEndpoint();
        f4802b.m7238a(new C05212(this));
        f4802b.libCreate();
        this.f4807c = new EpConfig();
        this.f4807c.getMedConfig().setEcOptions(0);
        this.f4807c.getMedConfig().setEcTailLen(1000);
        StringVector stringVector = new StringVector();
        stringVector.add("8.8.8.8");
        stringVector.add("4.2.2.3");
        this.f4807c.getUaConfig().setNameserver(stringVector);
        f4802b.libInit(this.f4807c);
        f4802b.codecSetPriority("G7221/16000/1", (short) 128);
        f4802b.codecSetPriority("G7221/32000/1", (short) 127);
        f4802b.codecSetPriority("G722/16000/1", (short) 126);
        f4802b.codecSetPriority("PCMU/8000/1", (short) 125);
        f4802b.codecSetPriority("PCMA/8000/1", (short) 124);
        f4802b.codecSetPriority("iLBC/8000/1", (short) 123);
        f4802b.codecSetPriority("GSM/8000/1", (short) 122);
        f4802b.codecSetPriority("speex/16000/1", (short) 121);
        f4802b.codecSetPriority("speex/8000/1", (short) 120);
        f4802b.codecSetPriority("speex/32000/1", (short) 119);
        for (int i = 0; ((long) i) < f4802b.codecEnum().size(); i++) {
            Main.f1926a.m5681c("Codec Priority of " + f4802b.codecEnum().get(i).getCodecId() + " is " + f4802b.codecEnum().get(i).getPriority());
        }
        this.f4808d = new TransportConfig();
        this.f4808d.setPort(5061);
        TlsConfig tlsConfig = new TlsConfig();
        tlsConfig.setMethod(pjsip_ssl_method.PJSIP_TLSV1_METHOD);
        this.f4808d.setTlsConfig(tlsConfig);
        f4802b.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TLS, this.f4808d);
        f4802b.libStart();
    }

    public synchronized void m7224d() {
        f4806n.execute(new C05223(this));
    }

    private void m7219n() {
        this.f4809e = new AccountConfig();
        this.f4809e.setIdUri("sip:" + this.f4812h + "@" + "bisphone.com" + ";transport=tls");
        this.f4809e.getRegConfig().setRegistrarUri("sip:bisphone.com;transport=tls");
        StringVector stringVector = new StringVector();
        stringVector.add("sip:call.bisphone.com;transport=tls;hide");
        this.f4809e.getRegConfig().setProxyUse(3);
        this.f4809e.getSipConfig().setProxies(stringVector);
        this.f4809e.getRegConfig().setTimeoutSec(1800);
        this.f4810f = new AuthCredInfo("digest", "*", this.f4812h, 0, this.f4813i);
        this.f4809e.getSipConfig().getAuthCreds().add(this.f4810f);
        this.f4809e.getMediaConfig().setSrtpUse(pjmedia_srtp_use.PJMEDIA_SRTP_MANDATORY);
        this.f4809e.getMediaConfig().setSrtpSecureSignaling(1);
        this.f4811g = new SipAccount();
        this.f4811g.create(this.f4809e);
        this.f4811g.m7234a(new C05234(this));
        this.f4811g.m7235a(new C05245(this));
    }

    public synchronized void m7222a(String str) {
        f4806n.execute(new C05256(this, str));
    }

    public synchronized void m7225e() {
        f4806n.execute(new C05267(this));
    }

    public synchronized void m7226f() {
        f4806n.execute(new C05278(this));
    }

    public synchronized void m7227g() {
        f4806n.execute(new C05289(this));
    }

    public synchronized void m7228h() {
        f4806n.execute(new Runnable() {
            final /* synthetic */ Sip f4784a;

            {
                this.f4784a = r1;
            }

            public void run() {
                if (Sip.f4802b != null) {
                    CallOpParam callOpParam = new CallOpParam(true);
                    callOpParam.getOpt().setFlag(1);
                    try {
                        Sip.f4803j.reinvite(callOpParam);
                    } catch (Throwable e) {
                        try {
                            Main.f1926a.m5684d(e);
                        } catch (NullPointerException e2) {
                        }
                    }
                }
            }
        });
    }

    public synchronized void m7229i() {
        f4806n.execute(new Runnable() {
            final /* synthetic */ Sip f4785a;

            {
                this.f4785a = r1;
            }

            public void run() {
                if (Sip.f4802b != null && this.f4785a.f4815o != -1) {
                    try {
                        AudioMedia.typecastFromMedia(Sip.f4803j.getMedia((long) this.f4785a.f4815o)).adjustRxLevel(0.0f);
                    } catch (Throwable e) {
                        Main.f1926a.m5683d("Wooops! Mute Call Exception");
                        try {
                            Main.f1926a.m5684d(e);
                        } catch (NullPointerException e2) {
                        }
                    }
                }
            }
        });
    }

    public synchronized void m7230j() {
        f4806n.execute(new Runnable() {
            final /* synthetic */ Sip f4786a;

            {
                this.f4786a = r1;
            }

            public void run() {
                if (Sip.f4802b != null && this.f4786a.f4815o != -1) {
                    try {
                        AudioMedia.typecastFromMedia(Sip.f4803j.getMedia((long) this.f4786a.f4815o)).adjustRxLevel(1.0f);
                    } catch (Throwable e) {
                        Main.f1926a.m5683d("Wooops! UnMute Call Exception");
                        try {
                            Main.f1926a.m5684d(e);
                        } catch (NullPointerException e2) {
                        }
                    }
                }
            }
        });
    }

    public synchronized void m7231k() {
        f4806n.execute(new Runnable() {
            final /* synthetic */ Sip f4787a;

            {
                this.f4787a = r1;
            }

            public void run() {
                if (Sip.f4803j != null && Sip.f4803j.isActive()) {
                    CallOpParam callOpParam = new CallOpParam(true);
                    callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_DECLINE);
                    try {
                        Sip.f4803j.hangup(callOpParam);
                    } catch (Throwable e) {
                        try {
                            Main.f1926a.m5684d(e);
                        } catch (NullPointerException e2) {
                        }
                    }
                }
                Sip.f4803j = null;
                if (this.f4787a.f4811g != null) {
                    this.f4787a.f4811g.delete();
                    this.f4787a.f4811g = null;
                }
            }
        });
    }

    public synchronized void m7232l() {
        f4806n.execute(new Runnable() {
            final /* synthetic */ Sip f4788a;

            {
                this.f4788a = r1;
            }

            public void run() {
                Runtime.getRuntime().gc();
                try {
                    Sip.f4802b.libStopWorkerThreads();
                    Sip.f4802b.libDestroy();
                } catch (Throwable th) {
                    Main.f1926a.m5683d("Wooops! EndPoint libDestroy Exception");
                    try {
                        Main.f1926a.m5684d(th);
                    } catch (NullPointerException e) {
                    }
                }
                if (Sip.f4802b != null) {
                    Sip.f4802b.delete();
                    Sip.f4802b = null;
                }
                Main.f1926a.m5681c("Sip Destroyed");
                Sip.f4801a = false;
            }
        });
    }

    private synchronized void m7220o() {
        f4803j.m7237a(new CallStateListener() {
            final /* synthetic */ Sip f4789a;

            {
                this.f4789a = r1;
            }

            public void m7194a(OnCallStateParam onCallStateParam) {
                try {
                    CallInfo info = Sip.f4803j.getInfo();
                    Main.f1926a.m5681c("Call State: " + info.getState());
                    if (info.getState() == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                        Sip.f4803j = null;
                    } else if (!(info.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED || info.getState() == pjsip_inv_state.PJSIP_INV_STATE_NULL || info.getState() == pjsip_inv_state.PJSIP_INV_STATE_CALLING || info.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING || info.getState() == pjsip_inv_state.PJSIP_INV_STATE_EARLY || info.getState() != pjsip_inv_state.PJSIP_INV_STATE_INCOMING)) {
                    }
                    if (this.f4789a.f4814m != null) {
                        this.f4789a.f4814m.m7205a(info);
                    }
                } catch (Throwable e) {
                    Main.f1926a.m5683d("Wooops! Get Call Info Exception");
                    try {
                        Main.f1926a.m5684d(e);
                    } catch (NullPointerException e2) {
                    }
                }
            }
        });
        f4803j.m7236a(new CallMediaStateListener() {
            final /* synthetic */ Sip f4790a;

            {
                this.f4790a = r1;
            }

            public void m7196a(OnCallMediaStateParam onCallMediaStateParam) {
                try {
                    CallMediaInfoVector media = Sip.f4803j.getInfo().getMedia();
                    this.f4790a.f4815o = -1;
                    for (int i = 0; ((long) i) < media.size(); i++) {
                        CallMediaInfo callMediaInfo = media.get(i);
                        if (callMediaInfo.getType() == pjmedia_type.PJMEDIA_TYPE_AUDIO && (callMediaInfo.getStatus() == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE || callMediaInfo.getStatus() == pjsua_call_media_status.PJSUA_CALL_MEDIA_REMOTE_HOLD)) {
                            AudioMedia typecastFromMedia = AudioMedia.typecastFromMedia(Sip.f4803j.getMedia((long) i));
                            this.f4790a.f4815o = i;
                            try {
                                Sip.f4802b.audDevManager().getCaptureDevMedia().startTransmit(typecastFromMedia);
                                typecastFromMedia.startTransmit(Sip.f4802b.audDevManager().getPlaybackDevMedia());
                            } catch (Throwable e) {
                                Main.f1926a.m5683d("Wooops! Start Transmit Exception");
                                try {
                                    Main.f1926a.m5684d(e);
                                } catch (NullPointerException e2) {
                                }
                            }
                        }
                    }
                    if (this.f4790a.f4814m != null) {
                        try {
                            this.f4790a.f4814m.m7206a(Sip.f4803j.getInfo().getMedia().get(this.f4790a.f4815o));
                        } catch (Throwable e3) {
                            try {
                                Main.f1926a.m5684d(e3);
                            } catch (NullPointerException e4) {
                            }
                        }
                    }
                } catch (Throwable e32) {
                    Main.f1926a.m5683d("Wooops! Get Call Info Exception");
                    try {
                        Main.f1926a.m5684d(e32);
                    } catch (NullPointerException e5) {
                    }
                }
            }
        });
    }

    public synchronized void m7221a(SipCallbacks sipCallbacks) {
        this.f4814m = sipCallbacks;
    }
}
