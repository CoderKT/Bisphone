package app.messaging.vh;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.account.AccountManager;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.RelationType;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.events.view_holder.MessageReplyClickEvent;
import app.events.view_holder.ViewHolderTouchEvent;
import app.messaging.RecycleMessagingAdapter.ReplyHighlightMode;
import app.messaging.emoji.EmojiHandlerCode;
import app.util.Background;
import app.util.BitmapUtil;
import app.util.TimeUtils;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class MessageViewHolder extends BaseViewHolder {
    protected TextView f4010A;
    protected RelativeLayout f4011B;
    TextView f4012C;
    ImageView f4013D;
    ImageView f4014E;
    protected HistoryEntity f4015F;
    protected int f4016G;
    protected String f4017H;
    protected String f4018I;
    protected String f4019J;
    LinearLayout f4020u;
    ViewGroup f4021v;
    ImageView f4022w;
    TextView f4023x;
    protected ImageView f4024y;
    protected TextView f4025z;

    /* renamed from: app.messaging.vh.MessageViewHolder.1 */
    class C04031 implements OnClickListener {
        final /* synthetic */ MessageViewHolder f4055a;

        C04031(MessageViewHolder messageViewHolder) {
            this.f4055a = messageViewHolder;
        }

        public void onClick(View view) {
            EventBus.m12779a().m12795d(new ViewHolderTouchEvent(this.f4055a.t, false));
        }
    }

    /* renamed from: app.messaging.vh.MessageViewHolder.2 */
    class C04042 implements OnLongClickListener {
        final /* synthetic */ MessageViewHolder f4056a;

        C04042(MessageViewHolder messageViewHolder) {
            this.f4056a = messageViewHolder;
        }

        public boolean onLongClick(View view) {
            EventBus.m12779a().m12795d(new ViewHolderTouchEvent(this.f4056a.t, true));
            return true;
        }
    }

    /* renamed from: app.messaging.vh.MessageViewHolder.3 */
    class C04053 implements OnClickListener {
        final /* synthetic */ MessageViewHolder f4057a;

        C04053(MessageViewHolder messageViewHolder) {
            this.f4057a = messageViewHolder;
        }

        public void onClick(View view) {
            EventBus.m12779a().m12795d(new MessageReplyClickEvent(this.f4057a.f4015F.m4402H(), this.f4057a.t));
        }
    }

    /* renamed from: app.messaging.vh.MessageViewHolder.4 */
    /* synthetic */ class C04064 {
        static final /* synthetic */ int[] f4058a;
        static final /* synthetic */ int[] f4059b;

        static {
            f4059b = new int[Type.values().length];
            try {
                f4059b[Type.PHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4059b[Type.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4059b[Type.AUDIO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4059b[Type.MAP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4059b[Type.STICKER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4059b[Type.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            f4058a = new int[ReplyHighlightMode.values().length];
            try {
                f4058a[ReplyHighlightMode.STICKER.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4058a[ReplyHighlightMode.MY_MESSAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4058a[ReplyHighlightMode.OTHER_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public MessageViewHolder(View view, Context context) {
        super(view, context);
        ButterKnife.m7744a(this, view);
    }

    public void m6519a(Context context, HistoryEntity historyEntity, int i, int i2) {
        this.s = context;
        this.f4015F = historyEntity;
        this.t = i;
        this.f4016G = i2;
        if (i2 == 1 && (historyEntity instanceof HistoryNormalMessageEntity)) {
            if (((HistoryNormalMessageEntity) historyEntity).m4457N() && this.f4014E != null) {
                this.f4014E.setVisibility(0);
            } else if (this.f4014E != null) {
                this.f4014E.setVisibility(8);
            }
        }
        m6510D();
        m6516B();
        m6511E();
        m6522y();
        if (historyEntity.m4422i()) {
            MessageStatusHandler.m6556a(historyEntity, context, this.f4013D);
        }
        DisplayDataHandler.m6537a(context, historyEntity, this.f4019J, this.f4012C, this.f4022w, this.f4021v, this.f4020u, i2);
    }

    private void m6510D() {
        this.f4023x.setText(TimeUtils.m7069a(Long.parseLong(this.f4015F.m4419f())));
        this.f4017H = this.f4015F.m4407a() + "";
    }

    private void m6511E() {
        if (this.f4016G == 1 && this.f4015F.m4421h()) {
            this.f4019J = ((HistoryNormalMessageEntity) this.f4015F).m4456M();
        } else if (this.f4016G == 1 || this.f4016G == 4) {
            this.f4019J = AccountManager.m3934a().m3937c();
        } else if (this.f4016G == 2) {
            this.f4019J = ((HistoryGroupEntity) this.f4015F).m4449M();
        }
        if (this.f4016G == 1 || this.f4016G == 4) {
            this.f4018I = this.f4019J;
        } else {
            this.f4018I = this.f4015F.m4414c();
        }
    }

    public void m6523z() {
        this.f4020u.setBackgroundResource(2130837874);
    }

    public void m6515A() {
        this.f4020u.setBackgroundResource(2130837873);
    }

    public void m6520a(ReplyHighlightMode replyHighlightMode) {
        switch (C04064.f4058a[replyHighlightMode.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                ImageView C = m6517C();
                if (C != null) {
                    C.setColorFilter(1342177280, Mode.SRC_ATOP);
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f4021v.setBackgroundResource(2130837627);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f4021v.setBackgroundResource(2130837624);
            default:
        }
    }

    public void m6521b(ReplyHighlightMode replyHighlightMode) {
        switch (C04064.f4058a[replyHighlightMode.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                ImageView C = m6517C();
                if (C != null) {
                    C.setColorFilter(null);
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f4021v.setBackgroundResource(2130837628);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f4021v.setBackgroundResource(2130837625);
            default:
        }
    }

    public void m6522y() {
        this.f4020u.setOnClickListener(new C04031(this));
        this.f4020u.setOnLongClickListener(new C04042(this));
        if (this.f4011B != null) {
            this.f4011B.setOnClickListener(new C04053(this));
        }
    }

    protected void m6516B() {
        if (!m6514a(this.f4015F.m4418e())) {
            if (this.f4015F.m4401G().equals(RelationType.REPLY)) {
                this.f4011B.setVisibility(0);
                m6513a(this.f4024y);
                this.f4025z.setText(m6512F());
                EmojiHandlerCode.m6125a().m6130a(m6518a(this.f4015F.m4404J(), this.f4015F.m4406L()), this.f4010A, EmojiHandlerCode.m6125a().f3544c, this.s);
                if (this.f4015F.m4418e() == Type.STICKER && this.f4015F.m4422i()) {
                    this.f4024y.setVisibility(8);
                    this.f4011B.setBackgroundResource(2130837620);
                    return;
                } else if (this.f4015F.m4418e() == Type.STICKER) {
                    this.f4024y.setVisibility(8);
                    this.f4011B.setBackgroundResource(2130837617);
                    return;
                } else {
                    Background.m6968a(null, this.f4011B);
                    return;
                }
            }
            this.f4024y.setImageBitmap(null);
            this.f4010A.setText("");
            this.f4011B.setVisibility(8);
        }
    }

    private boolean m6514a(Type type) {
        return type == Type.GROUP_STATUS || type == Type.CALL || type == Type.NEW_MESSAGE_DECLARATION || type == Type.CHANGE_GROUP_INFO || type == Type.TIMESTAMP;
    }

    private String m6512F() {
        String str = "";
        switch (this.f4016G) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return str + DisplayDataHandler.m6540b(this.f4015F.m4405K());
            default:
                return str;
        }
    }

    private void m6513a(ImageView imageView) {
        imageView.setVisibility(0);
        switch (C04064.f4059b[this.f4015F.m4406L().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (this.f4015F.m4404J() != null) {
                    imageView.setImageBitmap(BitmapUtil.m6976b(this.f4015F.m4404J()));
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                imageView.setImageResource(2130837796);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                imageView.setImageResource(2130837797);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                imageView.setImageResource(2130837798);
            default:
                imageView.setVisibility(8);
        }
    }

    protected String m6518a(String str, Type type) {
        switch (C04064.f4059b[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return this.s.getString(2131296642);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return this.s.getString(2131296887);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return this.s.getString(2131296337);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return this.s.getString(2131296547);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return this.s.getString(2131296774);
            default:
                return str;
        }
    }

    public ImageView m6517C() {
        int i = 0;
        while (i < this.f4021v.getChildCount()) {
            if ((this.f4021v.getChildAt(i) instanceof ImageView) && this.f4021v.getChildAt(i).getId() == 2131755484) {
                return (ImageView) this.f4021v.getChildAt(i);
            }
            i++;
        }
        return null;
    }
}
