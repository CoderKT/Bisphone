package android.support.v7.widget;

import android.graphics.Outline;

class ActionBarBackgroundDrawableV21 extends ActionBarBackgroundDrawable {
    public ActionBarBackgroundDrawableV21(ActionBarContainer actionBarContainer) {
        super(actionBarContainer);
    }

    public void getOutline(Outline outline) {
        if (this.a.f1144d) {
            if (this.a.f1143c != null) {
                this.a.f1143c.getOutline(outline);
            }
        } else if (this.a.f1141a != null) {
            this.a.f1141a.getOutline(outline);
        }
    }
}
