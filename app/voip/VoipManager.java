package app.voip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import app.Main;
import app.account.AccountManager;
import app.common.entity.HistoryEntity.CallStatus;
import app.events.xmpp.AbstractSendPacketEvent.PacketStatusListener;
import app.events.xmpp.SendPacketEvent;
import app.notification.NotificationCenter;
import app.util.CallUtil;
import app.util.StringUtil;
import app.util.XMPPUtils;
import app.voip.Sip.SipCallbacks;
import app.xmpp.JabberId;
import app.xmpp.packet.CallSignalPX;
import app.xmpp.packet.CallSignalPX.Type;
import de.greenrobot.event.EventBus;
import java.util.Timer;
import java.util.TimerTask;
import org.pjsip.pjsua2.AccountInfo;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsip_status_code;
import org.pjsip.pjsua2.pjsip_transport_state;
import se.emilsjolander.stickylistheaders.C1128R;

public class VoipManager {
    private static VoipManager f4861t;
    private Timer f4862a;
    private TimerTask f4863b;
    private volatile State f4864c;
    private final Object f4865d;
    private final Object f4866e;
    private boolean f4867f;
    private boolean f4868g;
    private long f4869h;
    private String f4870i;
    private JabberId f4871j;
    private boolean f4872k;
    private boolean f4873l;
    private int f4874m;
    private TimerTask f4875n;
    private boolean f4876o;
    private Context f4877p;
    private Sip f4878q;
    private final IntentFilter f4879r;
    private BroadcastReceiver f4880s;

    /* renamed from: app.voip.VoipManager.1 */
    class C05291 extends BroadcastReceiver {
        final /* synthetic */ VoipManager f4822a;

        C05291(VoipManager voipManager) {
            this.f4822a = voipManager;
        }

        public void onReceive(Context context, Intent intent) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            telephonyManager.listen(new PhoneStateListener(), 32);
            switch (telephonyManager.getCallState()) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f4822a.m7300b(false);
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f4822a.m7300b(true);
                default:
            }
        }
    }

    /* renamed from: app.voip.VoipManager.2 */
    class C05342 implements SipCallbacks {
        final /* synthetic */ JabberId f4827a;
        final /* synthetic */ Context f4828b;
        final /* synthetic */ VoipManager f4829c;

        /* renamed from: app.voip.VoipManager.2.1 */
        class C05311 implements PacketStatusListener {
            final /* synthetic */ C05342 f4824a;

            /* renamed from: app.voip.VoipManager.2.1.1 */
            class C05301 extends TimerTask {
                final /* synthetic */ C05311 f4823a;

                C05301(C05311 c05311) {
                    this.f4823a = c05311;
                }

                public void run() {
                    synchronized (this.f4823a.f4824a.f4829c.f4865d) {
                        if (this.f4823a.f4824a.f4829c.f4867f) {
                            Main.f1926a.m5681c("Outgoing Call, mutex_one, WAIT_TIME_TO_RCV_XMPP_SIGNAL_RESPONSE TimerTask");
                            return;
                        }
                        this.f4823a.f4824a.f4829c.f4867f = true;
                        this.f4823a.f4824a.f4829c.m7281k();
                        this.f4823a.f4824a.f4829c.m7261a(this.f4823a.f4824a.f4827a, UiMode.ENDED, EndMode.PREMATURE_AKA_NO_XMPP_RESPONSE);
                        this.f4823a.f4824a.f4829c.m7283l();
                        CallUtil.m7004a(this.f4823a.f4824a.f4828b, CallStatus.OUTGOING, this.f4823a.f4824a.f4827a, 0, this.f4823a.f4824a.f4829c.f4869h, this.f4823a.f4824a.f4829c.f4876o);
                        this.f4823a.f4824a.f4829c.m7279j();
                    }
                }
            }

            C05311(C05342 c05342) {
                this.f4824a = c05342;
            }

            public void m7239a(String str) {
                this.f4824a.f4829c.m7261a(this.f4824a.f4827a, UiMode.CALLING, null);
                this.f4824a.f4829c.m7281k();
                this.f4824a.f4829c.f4863b = new C05301(this);
                synchronized (this.f4824a.f4829c.f4865d) {
                    this.f4824a.f4829c.f4867f = false;
                }
                this.f4824a.f4829c.f4862a.schedule(this.f4824a.f4829c.f4863b, 20000);
            }

            public void m7240b(String str) {
                this.f4824a.f4829c.m7261a(this.f4824a.f4827a, UiMode.ENDED, EndMode.NOT_CONNECTED);
                this.f4824a.f4829c.m7283l();
                this.f4824a.f4829c.m7279j();
            }
        }

        /* renamed from: app.voip.VoipManager.2.2 */
        class C05322 extends TimerTask {
            final /* synthetic */ C05342 f4825a;

            C05322(C05342 c05342) {
                this.f4825a = c05342;
            }

            public void run() {
                this.f4825a.f4829c.f4874m = this.f4825a.f4829c.f4874m + 1;
            }
        }

        /* renamed from: app.voip.VoipManager.2.3 */
        class C05333 extends TimerTask {
            final /* synthetic */ C05342 f4826a;

            C05333(C05342 c05342) {
                this.f4826a = c05342;
            }

            public void run() {
                synchronized (this.f4826a.f4829c.f4865d) {
                    if (this.f4826a.f4829c.f4867f) {
                        Main.f1926a.m5681c("Outgoing Call, mutex_one, WAIT_TIME_TO_RCV_XMPP_SIGNAL_RESPONSE TimerTask");
                        return;
                    }
                    this.f4826a.f4829c.f4867f = true;
                    this.f4826a.f4829c.m7281k();
                    this.f4826a.f4829c.m7261a(this.f4826a.f4827a, UiMode.ENDED, EndMode.PREMATURE_AKA_NO_XMPP_RESPONSE);
                    this.f4826a.f4829c.m7283l();
                    CallUtil.m7004a(this.f4826a.f4828b, CallStatus.OUTGOING, this.f4826a.f4827a, 0, this.f4826a.f4829c.f4869h, this.f4826a.f4829c.f4876o);
                    this.f4826a.f4829c.m7279j();
                }
            }
        }

        C05342(VoipManager voipManager, JabberId jabberId, Context context) {
            this.f4829c = voipManager;
            this.f4827a = jabberId;
            this.f4828b = context;
        }

        public void m7246a(boolean z) {
            if (!z) {
                this.f4829c.m7261a(this.f4827a, UiMode.ENDED, EndMode.NOT_INIT);
                this.f4829c.m7279j();
            } else if (this.f4829c.f4878q != null) {
                this.f4829c.f4878q.m7224d();
            }
        }

        public void m7242a(Call call) {
        }

        public void m7241a(AccountInfo accountInfo) {
            if (accountInfo.getRegIsActive()) {
                this.f4829c.f4869h = System.currentTimeMillis() * 1000;
                if (this.f4829c.f4876o) {
                    this.f4829c.m7276i();
                    return;
                }
                SendPacketEvent sendPacketEvent = new SendPacketEvent(XMPPUtils.m7092a(this.f4827a, Type.call, this.f4829c.f4870i));
                sendPacketEvent.m4977a(new C05311(this));
                EventBus.m12779a().m12795d(sendPacketEvent);
                return;
            }
            this.f4829c.m7261a(this.f4827a, UiMode.ENDED, EndMode.NOT_CONNECTED);
            this.f4829c.m7283l();
            this.f4829c.m7279j();
        }

        public void m7243a(CallInfo callInfo) {
            if (callInfo.getState() == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                synchronized (this.f4829c.f4866e) {
                    if (this.f4829c.f4868g) {
                        return;
                    }
                    Main.f1926a.m5681c("Outgoing Call, mutex_two, onCallStateChange DISCONNECTED");
                    this.f4829c.f4868g = true;
                    this.f4829c.m7281k();
                    if (callInfo.getLastStatusCode() == pjsip_status_code.PJSIP_SC_BUSY_HERE || callInfo.getLastStatusCode() == pjsip_status_code.PJSIP_SC_BUSY_EVERYWHERE) {
                        this.f4829c.m7261a(this.f4827a, UiMode.ENDED, EndMode.USER_BUSY);
                    } else {
                        this.f4829c.m7261a(this.f4827a, UiMode.ENDED, EndMode.NORMAL);
                    }
                    this.f4829c.m7283l();
                    CallUtil.m7004a(this.f4828b, CallStatus.OUTGOING, this.f4827a, this.f4829c.f4874m, this.f4829c.f4869h, this.f4829c.f4876o);
                    this.f4829c.m7279j();
                }
            } else if (callInfo.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                synchronized (this.f4829c.f4866e) {
                    if (this.f4829c.f4868g) {
                        return;
                    }
                    Main.f1926a.m5681c("Outgoing Call, mutex_two, onCallStateChange CONFIRMED");
                    this.f4829c.m7281k();
                    this.f4829c.f4872k = true;
                    this.f4829c.m7261a(this.f4827a, UiMode.IN_CALL, null);
                    this.f4829c.f4874m = 0;
                    this.f4829c.f4875n = new C05322(this);
                    this.f4829c.f4862a.scheduleAtFixedRate(this.f4829c.f4875n, 0, 1000);
                }
            } else if (callInfo.getState() == pjsip_inv_state.PJSIP_INV_STATE_EARLY && this.f4829c.f4876o) {
                this.f4829c.m7261a(this.f4827a, UiMode.CALLING, null);
                this.f4829c.m7281k();
                this.f4829c.f4863b = new C05333(this);
                synchronized (this.f4829c.f4865d) {
                    this.f4829c.f4867f = false;
                }
                this.f4829c.f4862a.schedule(this.f4829c.f4863b, 20000);
            }
        }

        public void m7244a(CallMediaInfo callMediaInfo) {
        }

        public void m7245a(pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state) {
        }
    }

    /* renamed from: app.voip.VoipManager.3 */
    class C05353 extends TimerTask {
        final /* synthetic */ VoipManager f4830a;

        C05353(VoipManager voipManager) {
            this.f4830a = voipManager;
        }

        public void run() {
            synchronized (this.f4830a.f4866e) {
                if (this.f4830a.f4868g) {
                    return;
                }
                Main.f1926a.m5683d("Outgoing Call, mutex_two, WAIT_TIME_CALLING TimerTask");
                this.f4830a.f4868g = true;
                Main.f1926a.m5681c("Outgoing Call, mutex_two, WAIT_TIME_CALLING TimerTask");
                this.f4830a.f4868g = true;
                this.f4830a.m7281k();
                this.f4830a.m7285m();
                this.f4830a.m7261a(this.f4830a.f4871j, UiMode.ENDED, EndMode.NO_ANSWER);
                this.f4830a.m7283l();
                CallUtil.m7004a(this.f4830a.f4877p, CallStatus.OUTGOING, this.f4830a.f4871j, 0, this.f4830a.f4869h, this.f4830a.f4876o);
                this.f4830a.m7279j();
            }
        }
    }

    /* renamed from: app.voip.VoipManager.4 */
    class C05394 implements SipCallbacks {
        final /* synthetic */ JabberId f4834a;
        final /* synthetic */ String f4835b;
        final /* synthetic */ Context f4836c;
        final /* synthetic */ VoipManager f4837d;

        /* renamed from: app.voip.VoipManager.4.1 */
        class C05361 extends TimerTask {
            final /* synthetic */ C05394 f4831a;

            C05361(C05394 c05394) {
                this.f4831a = c05394;
            }

            public void run() {
                synchronized (this.f4831a.f4837d.f4866e) {
                    if (this.f4831a.f4837d.f4868g) {
                        return;
                    }
                    Main.f1926a.m5681c("Incoming Call, mutex_two, WAIT_TIME_RINGING TimerTask");
                    this.f4831a.f4837d.f4868g = true;
                    this.f4831a.f4837d.m7285m();
                    this.f4831a.f4837d.m7261a(this.f4831a.f4834a, UiMode.ENDED, EndMode.MISSED);
                    this.f4831a.f4837d.m7283l();
                    CallUtil.m7004a(this.f4831a.f4836c, CallStatus.MISSED, this.f4831a.f4834a, 0, this.f4831a.f4837d.f4869h, this.f4831a.f4837d.f4876o);
                    this.f4831a.f4837d.m7279j();
                }
            }
        }

        /* renamed from: app.voip.VoipManager.4.2 */
        class C05372 extends TimerTask {
            final /* synthetic */ C05394 f4832a;

            C05372(C05394 c05394) {
                this.f4832a = c05394;
            }

            public void run() {
                synchronized (this.f4832a.f4837d.f4865d) {
                    if (this.f4832a.f4837d.f4867f) {
                        return;
                    }
                    this.f4832a.f4837d.f4867f = true;
                    this.f4832a.f4837d.m7281k();
                    this.f4832a.f4837d.m7283l();
                    this.f4832a.f4837d.m7279j();
                }
            }
        }

        /* renamed from: app.voip.VoipManager.4.3 */
        class C05383 extends TimerTask {
            final /* synthetic */ C05394 f4833a;

            C05383(C05394 c05394) {
                this.f4833a = c05394;
            }

            public void run() {
                this.f4825a.f4829c.f4874m = this.f4833a.f4837d.f4874m + 1;
            }
        }

        C05394(VoipManager voipManager, JabberId jabberId, String str, Context context) {
            this.f4837d = voipManager;
            this.f4834a = jabberId;
            this.f4835b = str;
            this.f4836c = context;
        }

        public void m7252a(boolean z) {
            if (z) {
                this.f4837d.f4878q.m7224d();
                return;
            }
            EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(this.f4834a, Type.unavailable, this.f4835b)));
            this.f4837d.m7279j();
        }

        public void m7248a(Call call) {
            synchronized (this.f4837d.f4865d) {
                try {
                    String remoteUri = call.getInfo().getRemoteUri();
                    if (!this.f4834a.equals(JabberId.m7386a(remoteUri.substring(remoteUri.indexOf("<sip:") + 5, remoteUri.indexOf(">"))))) {
                        this.f4837d.m7285m();
                        return;
                    } else if (this.f4837d.f4867f) {
                        return;
                    } else {
                        this.f4837d.f4867f = true;
                        this.f4837d.f4869h = System.currentTimeMillis() * 1000;
                        this.f4837d.m7281k();
                        this.f4837d.m7261a(this.f4834a, UiMode.RINGING, null);
                        this.f4837d.f4863b = new C05361(this);
                        synchronized (this.f4837d.f4866e) {
                            this.f4837d.f4868g = false;
                        }
                        this.f4837d.f4862a.schedule(this.f4837d.f4863b, 45000);
                        return;
                    }
                } catch (Throwable e) {
                    Main.f1926a.m5682c(e);
                    this.f4837d.m7285m();
                }
            }
        }

        public void m7247a(AccountInfo accountInfo) {
            if (accountInfo.getRegIsActive()) {
                EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(this.f4834a, Type.answer, this.f4835b)));
                this.f4837d.m7281k();
                this.f4837d.f4863b = new C05372(this);
                synchronized (this.f4837d.f4865d) {
                    this.f4837d.f4867f = false;
                }
                this.f4837d.f4862a.schedule(this.f4837d.f4863b, 10000);
                return;
            }
            this.f4837d.m7283l();
            EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(this.f4834a, Type.unavailable, this.f4835b)));
            this.f4837d.m7279j();
        }

        public void m7249a(CallInfo callInfo) {
            if (callInfo.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                this.f4837d.f4872k = true;
                this.f4837d.m7261a(this.f4834a, UiMode.IN_CALL, null);
                this.f4837d.f4874m = 0;
                this.f4837d.f4875n = new C05383(this);
                this.f4837d.f4862a.scheduleAtFixedRate(this.f4837d.f4875n, 0, 1000);
            } else if (callInfo.getState() == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                synchronized (this.f4837d.f4866e) {
                    if (this.f4837d.f4868g) {
                        return;
                    }
                    Main.f1926a.m5681c("Incoming Call, mutex_two, onCallStateChanged DISCONNECTED");
                    this.f4837d.f4868g = true;
                    this.f4837d.m7281k();
                    if (this.f4837d.f4872k) {
                        this.f4837d.m7261a(this.f4834a, UiMode.ENDED, EndMode.NORMAL);
                    } else {
                        this.f4837d.m7261a(this.f4834a, UiMode.ENDED, EndMode.MISSED);
                    }
                    this.f4837d.m7283l();
                    CallUtil.m7004a(this.f4836c, this.f4837d.f4872k ? CallStatus.INCOMING : CallStatus.MISSED, this.f4834a, this.f4837d.f4874m, this.f4837d.f4869h, this.f4837d.f4876o);
                    this.f4837d.m7279j();
                }
            }
        }

        public void m7250a(CallMediaInfo callMediaInfo) {
        }

        public void m7251a(pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state) {
        }
    }

    /* renamed from: app.voip.VoipManager.5 */
    /* synthetic */ class C05405 {
        static final /* synthetic */ int[] f4838a;
        static final /* synthetic */ int[] f4839b;

        static {
            f4839b = new int[State.values().length];
            try {
                f4839b[State.OUTGOING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4839b[State.INCOMING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4839b[State.IDLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f4838a = new int[Type.values().length];
            try {
                f4838a[Type.answer.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4838a[Type.busy.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4838a[Type.unavailable.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4838a[Type.call.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    enum EndMode {
        NOT_INIT,
        NOT_CONNECTED,
        PREMATURE_AKA_NO_XMPP_RESPONSE,
        USER_UNAVAILABLE,
        USER_BUSY,
        NO_ANSWER,
        CANCELED,
        NORMAL,
        MISSED,
        DECLINED;

        public static EndMode m7253a(String str) {
            try {
                return valueOf(str.toUpperCase());
            } catch (Exception e) {
                return NORMAL;
            }
        }
    }

    public enum State {
        IDLE,
        INCOMING,
        OUTGOING
    }

    enum UiMode {
        CONNECTING,
        CALLING,
        RINGING,
        IN_CALL,
        ENDED;

        public static UiMode m7254a(String str) {
            try {
                return valueOf(str.toUpperCase());
            } catch (Exception e) {
                return ENDED;
            }
        }
    }

    static {
        f4861t = null;
    }

    private VoipManager() {
        this.f4862a = new Timer();
        this.f4863b = null;
        this.f4864c = State.IDLE;
        this.f4865d = new Object();
        this.f4866e = new Object();
        this.f4867f = true;
        this.f4868g = true;
        this.f4869h = 0;
        this.f4870i = null;
        this.f4871j = null;
        this.f4872k = false;
        this.f4873l = false;
        this.f4874m = 0;
        this.f4875n = null;
        this.f4876o = false;
        this.f4879r = new IntentFilter("android.intent.action.PHONE_STATE");
        this.f4880s = new C05291(this);
    }

    public boolean m7299a() {
        return this.f4876o;
    }

    public static synchronized VoipManager m7265b() {
        VoipManager voipManager;
        synchronized (VoipManager.class) {
            if (f4861t == null) {
                f4861t = new VoipManager();
            }
            voipManager = f4861t;
        }
        return voipManager;
    }

    public void m7296a(Context context, JabberId jabberId, boolean z) {
        this.f4876o = z;
        if (this.f4864c != State.IDLE) {
            Main.m3905a(context.getString(2131296865));
        } else if (!Sip.m7211a()) {
            Main.m3905a(Main.f1927b.getString(2131296867));
        } else if (Sip.m7213b()) {
            this.f4864c = State.OUTGOING;
            this.f4877p = context;
            this.f4877p.registerReceiver(this.f4880s, this.f4879r);
            this.f4871j = jabberId;
            this.f4870i = StringUtil.m7063a(5);
            m7261a(jabberId, UiMode.CONNECTING, null);
            this.f4878q = new Sip();
            this.f4878q.m7221a(m7257a(this.f4877p, jabberId));
            this.f4878q.m7223a(AccountManager.m3934a().m3937c(), AccountManager.m3934a().m3938d());
        } else {
            Main.m3905a(context.getString(2131296866));
        }
    }

    private SipCallbacks m7257a(Context context, JabberId jabberId) {
        return new C05342(this, jabberId, context);
    }

    public void m7297a(JabberId jabberId, CallSignalPX callSignalPX) {
        synchronized (this.f4865d) {
            if (this.f4864c != State.OUTGOING) {
            } else if (!jabberId.equals(this.f4871j) || !callSignalPX.m7526b().equals(this.f4870i)) {
            } else if (this.f4867f) {
            } else {
                Main.f1926a.m5681c("Outgoing Call, mutex_one, receivedXmppSignalResponse");
                this.f4867f = true;
                m7281k();
                switch (C05405.f4838a[callSignalPX.m7525a().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        m7276i();
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        if (callSignalPX.m7525a() == Type.busy) {
                            m7261a(jabberId, UiMode.ENDED, EndMode.USER_BUSY);
                        } else {
                            m7261a(jabberId, UiMode.ENDED, EndMode.USER_UNAVAILABLE);
                        }
                        m7283l();
                        CallUtil.m7004a(this.f4877p, CallStatus.OUTGOING, jabberId, 0, this.f4869h, this.f4876o);
                        m7279j();
                    default:
                        throw new IllegalArgumentException("invalid response");
                }
            }
        }
    }

    private void m7276i() {
        m7262a(this.f4871j.m7387a() + (this.f4876o ? "*@" : "@") + this.f4871j.m7388b());
        m7281k();
        this.f4863b = new C05353(this);
        synchronized (this.f4866e) {
            this.f4868g = false;
        }
        this.f4862a.schedule(this.f4863b, 45000);
    }

    public void m7301c() {
        synchronized (this.f4865d) {
            if (this.f4864c != State.OUTGOING) {
            } else if (this.f4873l) {
            } else {
                this.f4873l = true;
                Main.f1926a.m5681c("Outgoing Call, mutex_one, hangupEarlyOutgoingCall");
                this.f4867f = true;
                m7281k();
                m7261a(this.f4871j, UiMode.ENDED, EndMode.CANCELED);
                m7283l();
                CallUtil.m7004a(this.f4877p, CallStatus.OUTGOING, this.f4871j, 0, this.f4869h, this.f4876o);
                m7279j();
            }
        }
    }

    public void m7302d() {
        synchronized (this.f4866e) {
            if (this.f4864c != State.OUTGOING) {
            } else if (this.f4873l) {
            } else {
                this.f4873l = true;
                Main.f1926a.m5681c("Outgoing Call, mutex_two, hangupOutgoingCall");
                this.f4868g = true;
                m7281k();
                m7285m();
                if (this.f4872k) {
                    m7261a(this.f4871j, UiMode.ENDED, EndMode.NORMAL);
                } else {
                    m7261a(this.f4871j, UiMode.ENDED, EndMode.CANCELED);
                }
                m7283l();
                CallUtil.m7004a(this.f4877p, CallStatus.OUTGOING, this.f4871j, this.f4874m, this.f4869h, this.f4876o);
                m7279j();
            }
        }
    }

    public void m7295a(Context context, JabberId jabberId, String str) {
        switch (C05405.f4839b[this.f4864c.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(jabberId, Type.busy, str)));
                return;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (jabberId.equals(this.f4871j)) {
                    try {
                        if (Endpoint.instance() != null) {
                            Endpoint.instance().libRegisterThread(Thread.currentThread().getName());
                            if (this.f4878q.f4811g.getInfo().getRegIsActive()) {
                                this.f4870i = str;
                                EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(jabberId, Type.answer, str)));
                                return;
                            }
                            m7281k();
                            m7283l();
                            m7279j();
                            break;
                        }
                        return;
                    } catch (Exception e) {
                    }
                } else {
                    EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(jabberId, Type.busy, str)));
                    return;
                }
        }
        if (Sip.m7211a() && Sip.m7213b()) {
            this.f4864c = State.INCOMING;
            this.f4877p = context;
            this.f4877p.registerReceiver(this.f4880s, this.f4879r);
            this.f4871j = jabberId;
            this.f4870i = str;
            this.f4878q = new Sip();
            this.f4878q.m7221a(m7264b(this.f4877p, jabberId, str));
            this.f4878q.m7223a(AccountManager.m3934a().m3937c(), AccountManager.m3934a().m3938d());
            return;
        }
        EventBus.m12779a().m12795d(new SendPacketEvent(XMPPUtils.m7092a(jabberId, Type.unavailable, str)));
    }

    private SipCallbacks m7264b(Context context, JabberId jabberId, String str) {
        return new C05394(this, jabberId, str, context);
    }

    public void m7303e() {
        synchronized (this.f4866e) {
            if (this.f4864c != State.INCOMING) {
            } else if (this.f4868g) {
            } else {
                Main.f1926a.m5681c("Incoming Call, mutex_two, acceptIncomingCall");
                m7281k();
                m7286n();
            }
        }
    }

    public void m7304f() {
        synchronized (this.f4866e) {
            if (this.f4864c != State.INCOMING) {
            } else if (this.f4868g) {
            } else if (this.f4873l) {
            } else {
                this.f4873l = true;
                Main.f1926a.m5681c("Incoming Call, mutex_two, declineIncomingCall");
                this.f4868g = true;
                m7281k();
                m7285m();
                m7261a(this.f4871j, UiMode.ENDED, EndMode.DECLINED);
                m7283l();
                CallUtil.m7004a(this.f4877p, CallStatus.INCOMING, this.f4871j, 0, this.f4869h, this.f4876o);
                m7279j();
            }
        }
    }

    public void m7305g() {
        if (this.f4864c == State.INCOMING && !this.f4873l) {
            this.f4873l = true;
            m7285m();
            m7261a(this.f4871j, UiMode.ENDED, EndMode.NORMAL);
            m7283l();
            CallUtil.m7004a(this.f4877p, CallStatus.INCOMING, this.f4871j, this.f4874m, this.f4869h, this.f4876o);
            m7279j();
        }
    }

    public State m7306h() {
        return this.f4864c;
    }

    private void m7279j() {
        if (this.f4864c != State.IDLE) {
            this.f4878q = null;
            this.f4870i = null;
            this.f4871j = null;
            this.f4872k = false;
            this.f4876o = false;
            this.f4873l = false;
            if (this.f4875n != null) {
                this.f4875n.cancel();
                this.f4875n = null;
            }
            this.f4874m = 0;
            this.f4869h = 0;
            try {
                if (this.f4877p != null) {
                    this.f4877p.unregisterReceiver(this.f4880s);
                }
            } catch (IllegalArgumentException e) {
            }
            this.f4877p = null;
            NotificationCenter.m6606a().m6627i();
            this.f4864c = State.IDLE;
        }
    }

    private void m7281k() {
        if (this.f4863b != null) {
            this.f4863b.cancel();
            this.f4863b = null;
        }
    }

    private void m7261a(JabberId jabberId, UiMode uiMode, EndMode endMode) {
        if (this.f4877p != null) {
            Intent intent = new Intent(this.f4877p, CallActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("extra_jabber_id", jabberId.m7390d());
            intent.putExtra("extra_ui_mode", uiMode.toString());
            intent.putExtra("extra_call_out", this.f4876o);
            if (endMode != null) {
                intent.putExtra("extra_end_mode", endMode.toString());
            }
            this.f4877p.startActivity(intent);
        }
    }

    private synchronized void m7283l() {
        if (this.f4878q != null) {
            this.f4878q.m7231k();
            this.f4878q.m7232l();
        }
    }

    private synchronized void m7285m() {
        if (this.f4878q != null) {
            this.f4878q.m7225e();
        }
    }

    private synchronized void m7286n() {
        if (this.f4878q != null) {
            this.f4878q.m7226f();
        }
    }

    private synchronized void m7262a(String str) {
        if (this.f4878q != null) {
            this.f4878q.m7222a(str);
        }
    }

    public void m7298a(boolean z) {
        if (this.f4878q != null) {
            if (z) {
                this.f4878q.m7229i();
            } else {
                this.f4878q.m7230j();
            }
        }
    }

    public void m7300b(boolean z) {
        if (this.f4878q != null) {
            if (z) {
                this.f4878q.m7227g();
            } else {
                this.f4878q.m7228h();
            }
        }
    }
}
