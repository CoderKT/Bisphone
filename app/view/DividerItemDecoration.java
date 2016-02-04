package app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

public class DividerItemDecoration extends ItemDecoration {
    private static final int[] f4656a;
    private Drawable f4657b;
    private int f4658c;
    private Paint f4659d;

    static {
        f4656a = new int[]{16843284};
    }

    public DividerItemDecoration(Context context, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(f4656a);
        this.f4657b = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        m7117a(i);
        this.f4659d = new Paint();
        this.f4659d.setColor(context.getResources().getColor(i2));
        this.f4659d.setStrokeWidth(1.0f);
    }

    public void m7117a(int i) {
        if (i == 0 || i == 1) {
            this.f4658c = i;
            return;
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public void m7118a(Canvas canvas, RecyclerView recyclerView) {
        if (this.f4658c == 1) {
            m7120c(canvas, recyclerView);
        } else {
            m7121d(canvas, recyclerView);
        }
    }

    public void m7120c(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int bottom = layoutParams.bottomMargin + childAt.getBottom();
            canvas.drawLine((float) paddingLeft, (float) bottom, (float) width, (float) bottom, this.f4659d);
        }
    }

    public void m7121d(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int right = layoutParams.rightMargin + childAt.getRight();
            this.f4657b.setBounds(right, paddingTop, this.f4657b.getIntrinsicHeight() + right, height);
            this.f4657b.draw(canvas);
        }
    }

    public void m7119a(Rect rect, int i, RecyclerView recyclerView) {
        if (this.f4658c == 1) {
            rect.set(0, 0, 0, this.f4657b.getIntrinsicHeight());
        } else {
            rect.set(0, 0, this.f4657b.getIntrinsicWidth(), 0);
        }
    }
}
