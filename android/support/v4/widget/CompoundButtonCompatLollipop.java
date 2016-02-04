package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.widget.CompoundButton;

class CompoundButtonCompatLollipop {
    static void m1717a(CompoundButton compoundButton, ColorStateList colorStateList) {
        compoundButton.setButtonTintList(colorStateList);
    }

    static void m1718a(CompoundButton compoundButton, Mode mode) {
        compoundButton.setButtonTintMode(mode);
    }
}
