package app.messaging.vh;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import app.messaging.RecycleMessagingAdapter.ReplyHighlightMode;

public abstract class BaseViewHolder extends ViewHolder implements IViewHolder {
    protected Context f4008s;
    protected int f4009t;

    public abstract void m6506A();

    public abstract void m6507a(ReplyHighlightMode replyHighlightMode);

    public abstract void m6508b(ReplyHighlightMode replyHighlightMode);

    public abstract void m6509z();

    public BaseViewHolder(View view) {
        super(view);
    }

    public BaseViewHolder(View view, Context context) {
        super(view);
        this.f4008s = context;
    }
}
