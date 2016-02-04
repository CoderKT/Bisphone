package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class AppCompatRatingBar extends RatingBar {
    private AppCompatProgressBarHelper f1371a;
    private TintManager f1372b;

    public AppCompatRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.ratingBarStyle);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1372b = TintManager.m3737a(context);
        this.f1371a = new AppCompatProgressBarHelper(this, this.f1372b);
        this.f1371a.m2794a(attributeSet, i);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap a = this.f1371a.m2793a();
        if (a != null) {
            setMeasuredDimension(ViewCompat.m1153a(a.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }
}
