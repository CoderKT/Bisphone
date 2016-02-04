package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

class ActionBarBackgroundDrawable extends Drawable {
    final ActionBarContainer f1140a;

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.f1140a = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        if (!this.f1140a.f1144d) {
            if (this.f1140a.f1141a != null) {
                this.f1140a.f1141a.draw(canvas);
            }
            if (this.f1140a.f1142b != null && this.f1140a.f1145e) {
                this.f1140a.f1142b.draw(canvas);
            }
        } else if (this.f1140a.f1143c != null) {
            this.f1140a.f1143c.draw(canvas);
        }
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return 0;
    }
}
