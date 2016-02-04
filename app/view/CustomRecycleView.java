package app.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class CustomRecycleView extends RecyclerView {
    private View f4651i;

    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomRecycleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean m7114b(int i, int i2) {
        return super.m3612b(i, (int) (((double) i2) * 0.75d));
    }

    public void setEmptyView(View view) {
        setEmptyViewVisibility(8);
        if (this.f4651i != view) {
            this.f4651i = view;
        }
    }

    public void setEmptyViewVisibility(int i) {
        if (this.f4651i != null) {
            if (i == 0) {
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            this.f4651i.setVisibility(i);
        }
    }
}
