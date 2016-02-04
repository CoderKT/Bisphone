package app.messaging.vh;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import app.common.entity.HistoryEntity;
import app.messaging.RecycleMessagingAdapter;
import app.messaging.emoji.EmojiHandlerCode;
import app.util.BubbleDialogUtil;
import se.emilsjolander.stickylistheaders.C1128R;

public class Text extends MessageViewHolder {
    public TextView f4096l;

    /* renamed from: app.messaging.vh.Text.1 */
    class C04201 implements OnLongClickListener {
        final /* synthetic */ Text f4093a;

        C04201(Text text) {
            this.f4093a = text;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7002a(this.f4093a.s, this.f4093a.F, this.f4093a.J, this.f4093a.G, this.f4093a.t);
            return true;
        }
    }

    /* renamed from: app.messaging.vh.Text.2 */
    class C04212 implements OnLongClickListener {
        final /* synthetic */ Text f4094a;

        C04212(Text text) {
            this.f4094a = text;
        }

        public boolean onLongClick(View view) {
            if (this.f4094a.f4096l.getSelectionStart() != -1 || this.f4094a.f4096l.getSelectionEnd() != -1) {
                return false;
            }
            BubbleDialogUtil.m6982a().m7002a(this.f4094a.s, this.f4094a.F, this.f4094a.J, this.f4094a.G, this.f4094a.t);
            return true;
        }
    }

    /* renamed from: app.messaging.vh.Text.3 */
    class C04223 implements OnTouchListener {
        final /* synthetic */ Text f4095a;

        C04223(Text text) {
            this.f4095a = text;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f4095a.v.setPressed(true);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4095a.v.setPressed(false);
                    break;
            }
            return false;
        }
    }

    public Text(View view, Context context) {
        super(view, context);
    }

    public void m6580a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        this.f4096l.setText("");
        this.f4096l.setTextSize(2, (float) RecycleMessagingAdapter.f3297b);
        EmojiHandlerCode.m6125a().m6130a(historyEntity.m4416d(), this.f4096l, EmojiHandlerCode.m6125a().f3543b, context);
    }

    public void m6581y() {
        super.m6522y();
        this.v.setOnLongClickListener(new C04201(this));
        this.f4096l.setOnLongClickListener(new C04212(this));
        this.f4096l.setOnTouchListener(new C04223(this));
    }
}
