package app.messaging;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import app.C0110R;
import app.account.AccountManager;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.CallStatus;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.events.view_holder.ViewHolderTouchEvent;
import app.messaging.vh.Audio;
import app.messaging.vh.BaseViewHolder;
import app.messaging.vh.Call;
import app.messaging.vh.GroupStatusHolder;
import app.messaging.vh.Map;
import app.messaging.vh.NewMessageDeclarationViewHolder;
import app.messaging.vh.Photo;
import app.messaging.vh.Sticker;
import app.messaging.vh.Text;
import app.messaging.vh.TimestampViewHolder;
import app.messaging.vh.Video;
import app.util.DeviceUtils;
import app.util.SharedPreferencesUtil;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import org.json.JSONException;
import se.emilsjolander.stickylistheaders.C1128R;

public class RecycleMessagingAdapter extends Adapter<BaseViewHolder> {
    public static int f3296a;
    public static int f3297b;
    public static boolean f3298c;
    private static long f3299e;
    final Object f3300d;
    private Context f3301f;
    private Handler f3302g;
    private LayoutInflater f3303h;
    private RecycleAdapterListener f3304i;
    private ArrayList<HistoryEntity> f3305j;
    private ArrayList<Integer> f3306k;
    private int f3307l;
    private boolean f3308m;
    private int f3309n;
    private int f3310o;

    public interface RecycleAdapterListener {
        void m5943a(int i);

        void m5944a(boolean z);

        void m5945b(int i);
    }

    /* renamed from: app.messaging.RecycleMessagingAdapter.1 */
    /* synthetic */ class C02671 {
        static final /* synthetic */ int[] f3291a;

        static {
            f3291a = new int[CallStatus.values().length];
            try {
                f3291a[CallStatus.OUTGOING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3291a[CallStatus.INCOMING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3291a[CallStatus.MISSED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum ReplyHighlightMode {
        MY_MESSAGE,
        OTHER_MESSAGE,
        STICKER
    }

    public /* synthetic */ ViewHolder m5964a(ViewGroup viewGroup, int i) {
        return m5973c(viewGroup, i);
    }

    static {
        f3296a = 220;
        f3299e = 0;
        f3297b = 16;
        f3298c = false;
    }

    public RecycleMessagingAdapter(Context context) {
        this.f3308m = false;
        this.f3309n = -1;
        this.f3300d = new Object();
        this.f3301f = context;
        this.f3303h = (LayoutInflater) context.getSystemService("layout_inflater");
        EventBus.m12779a().m12791a((Object) this);
        f3299e = 0;
        this.f3310o = 0;
        f3297b = SharedPreferencesUtil.m7057b(context.getString(2131296926), 16);
        DisplayMetrics a = DeviceUtils.m7011a((RecycleMessagingActivity) context);
        f3296a = (Math.min(a.heightPixels, a.widthPixels) / 2) - ((int) (a.density * 12.0f));
        this.f3305j = new ArrayList();
        this.f3306k = new ArrayList();
        this.f3302g = new Handler();
    }

    public void m5969a(ArrayList<HistoryEntity> arrayList) {
        this.f3305j = arrayList;
    }

    public BaseViewHolder m5973c(ViewGroup viewGroup, int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return new Text(this.f3303h.inflate(2130903174, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return new Photo(this.f3303h.inflate(2130903172, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return new Video(this.f3303h.inflate(2130903175, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return new Audio(this.f3303h.inflate(2130903170, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return new Map(this.f3303h.inflate(2130903171, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return new Sticker(this.f3303h.inflate(2130903173, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return new Text(this.f3303h.inflate(2130903180, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return new Photo(this.f3303h.inflate(2130903178, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_scrollingCache /*12*/:
                return new Video(this.f3303h.inflate(2130903181, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                return new Audio(this.f3303h.inflate(2130903176, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                return new Map(this.f3303h.inflate(2130903177, viewGroup, false), this.f3301f);
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                return new Sticker(this.f3303h.inflate(2130903179, viewGroup, false), this.f3301f);
            case C0110R.styleable.Theme_actionModeSplitBackground /*30*/:
                return new Call(this.f3303h.inflate(2130903168, viewGroup, false), this.f3301f);
            case C0110R.styleable.Theme_actionModeCloseDrawable /*31*/:
                return new Call(this.f3303h.inflate(2130903166, viewGroup, false), this.f3301f);
            case C0110R.styleable.Theme_actionModeCutDrawable /*32*/:
                return new Call(this.f3303h.inflate(2130903167, viewGroup, false), this.f3301f);
            case C0110R.styleable.Theme_textAppearanceLargePopupMenu /*40*/:
                return new NewMessageDeclarationViewHolder(this.f3303h.inflate(2130903165, viewGroup, false));
            case C0110R.styleable.Theme_textAppearanceSmallPopupMenu /*41*/:
                return new TimestampViewHolder(this.f3303h.inflate(2130903182, viewGroup, false));
            default:
                return new GroupStatusHolder(this.f3303h.inflate(2130903169, viewGroup, false));
        }
    }

    public void m5968a(BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.m6505a(this.f3301f, (HistoryEntity) this.f3305j.get(i), i, this.f3307l);
        if (m5960j(i)) {
            baseViewHolder.m6509z();
        } else {
            baseViewHolder.m6506A();
        }
        if (this.f3309n == i) {
            baseViewHolder.m6507a(m5965a(((HistoryEntity) this.f3305j.get(i)).m4420g(), ((HistoryEntity) this.f3305j.get(i)).m4418e()));
        } else {
            baseViewHolder.m6508b(m5965a(((HistoryEntity) this.f3305j.get(i)).m4420g(), ((HistoryEntity) this.f3305j.get(i)).m4418e()));
        }
    }

    public int m5963a(int i) {
        int i2 = 0;
        Type e = ((HistoryEntity) this.f3305j.get(i)).m4418e();
        if (e == Type.CALL) {
            try {
                switch (C02671.f3291a[CallStatus.valueOf(((HistoryEntity) this.f3305j.get(i)).m4424k().getString("call_status")).ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        return 30;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        return 31;
                    default:
                        return 32;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                return 32;
            }
        } else if (e == Type.GROUP_STATUS) {
            return 50;
        } else {
            if (e == Type.NEW_MESSAGE_DECLARATION) {
                return 40;
            }
            if (e == Type.TIMESTAMP) {
                return 41;
            }
            boolean z;
            if (this.f3307l == 2 && (this.f3305j.get(i) instanceof HistoryGroupEntity)) {
                HistoryGroupEntity historyGroupEntity = (HistoryGroupEntity) this.f3305j.get(i);
                z = historyGroupEntity.m4449M() == null || "".equals(historyGroupEntity.m4449M()) || historyGroupEntity.m4449M().contains("-") || historyGroupEntity.m4449M().equals(AccountManager.m3934a().m3937c());
            } else {
                z = m5971a(((HistoryEntity) this.f3305j.get(i)).m4420g());
            }
            if (!z) {
                i2 = 1;
            }
            return (i2 * 10) + e.ordinal();
        }
    }

    public boolean m5971a(DeliveryStatus deliveryStatus) {
        if (deliveryStatus == null) {
            return false;
        }
        if (deliveryStatus == DeliveryStatus.UPLOADING || deliveryStatus == DeliveryStatus.FAILED_TO_UPLOAD || deliveryStatus == DeliveryStatus.SENDING || deliveryStatus == DeliveryStatus.FAILED_TO_SEND || deliveryStatus == DeliveryStatus.SENT || deliveryStatus == DeliveryStatus.DELIVERED) {
            return true;
        }
        return false;
    }

    public ReplyHighlightMode m5965a(DeliveryStatus deliveryStatus, Type type) {
        if (type == Type.STICKER) {
            return ReplyHighlightMode.STICKER;
        }
        if (deliveryStatus == null) {
            return ReplyHighlightMode.OTHER_MESSAGE;
        }
        return m5971a(deliveryStatus) ? ReplyHighlightMode.MY_MESSAGE : ReplyHighlightMode.OTHER_MESSAGE;
    }

    public int m5962a() {
        return this.f3305j.size();
    }

    public long m5972b(int i) {
        return ((HistoryEntity) this.f3305j.get(i)).m4407a();
    }

    public void onEventMainThread(ViewHolderTouchEvent viewHolderTouchEvent) {
        synchronized (this.f3300d) {
            if (this.f3308m) {
                m5957h(viewHolderTouchEvent.m4972a());
            } else if (viewHolderTouchEvent.m4973b()) {
                this.f3308m = true;
                this.f3304i.m5944a(this.f3308m);
                m5957h(viewHolderTouchEvent.m4972a());
            }
        }
    }

    private void m5957h(int i) {
        boolean i2 = m5958i(i);
        if (m5960j(i)) {
            this.f3306k.remove(this.f3306k.indexOf(Integer.valueOf(i)));
            if (i2) {
                this.f3310o--;
            }
            if (this.f3306k.isEmpty()) {
                m5976f();
                this.f3310o = 0;
            }
        } else {
            this.f3306k.add(Integer.valueOf(i));
            if (i2) {
                this.f3310o++;
            }
        }
        m3346c(i);
        this.f3304i.m5943a(this.f3306k.size());
        this.f3304i.m5945b(this.f3310o);
    }

    private boolean m5958i(int i) {
        if (i >= this.f3305j.size()) {
            return false;
        }
        Type e = ((HistoryEntity) this.f3305j.get(i)).m4418e();
        return e.equals(Type.TEXT) || e.equals(Type.PHOTO) || e.equals(Type.VIDEO) || e.equals(Type.MAP) || e.equals(Type.AUDIO);
    }

    public int m5974d() {
        return this.f3310o;
    }

    public void m5975e() {
        if (this.f3308m) {
            this.f3306k.clear();
            this.f3310o = 0;
            int i = 0;
            while (i < this.f3305j.size()) {
                if (!(((HistoryEntity) this.f3305j.get(i)).m4418e() == Type.TIMESTAMP || ((HistoryEntity) this.f3305j.get(i)).m4418e() == Type.NEW_MESSAGE_DECLARATION)) {
                    this.f3306k.add(Integer.valueOf(i));
                    if (m5958i(i)) {
                        this.f3310o++;
                    }
                }
                i++;
            }
            this.f3304i.m5943a(this.f3306k.size());
            this.f3304i.m5945b(this.f3310o);
            m3345c();
        }
    }

    public void m5976f() {
        this.f3306k.clear();
        this.f3308m = false;
        m3345c();
        this.f3310o = 0;
        this.f3304i.m5944a(this.f3308m);
        this.f3304i.m5943a(this.f3306k.size());
        this.f3304i.m5945b(this.f3310o);
    }

    public void m5978g() {
        this.f3306k.clear();
    }

    private boolean m5960j(int i) {
        return this.f3306k.indexOf(Integer.valueOf(i)) > -1;
    }

    public void m5977f(int i) {
        this.f3307l = i;
    }

    public int m5980h() {
        return this.f3307l;
    }

    public void m5970a(boolean z) {
        this.f3308m = z;
        this.f3304i.m5944a(z);
    }

    public boolean m5981i() {
        return this.f3308m;
    }

    public static boolean m5959j() {
        return f3298c;
    }

    public static void m5956b(boolean z) {
        f3298c = z;
    }

    public static long m5961k() {
        return f3299e;
    }

    public static void m5955a(long j) {
        f3299e = j;
    }

    public void m5979g(int i) {
        this.f3309n = i;
    }

    public ArrayList<Integer> m5982l() {
        return this.f3306k;
    }

    public int m5983m() {
        return this.f3309n;
    }

    public void m5967a(RecycleAdapterListener recycleAdapterListener) {
        this.f3304i = recycleAdapterListener;
    }

    void m5984n() {
        EventBus.m12779a().m12794c(this);
    }
}
