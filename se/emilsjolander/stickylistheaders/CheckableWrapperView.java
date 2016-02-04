package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

class CheckableWrapperView extends WrapperView implements Checkable {
    public CheckableWrapperView(Context context) {
        super(context);
    }

    public boolean isChecked() {
        return ((Checkable) this.a).isChecked();
    }

    public void setChecked(boolean z) {
        ((Checkable) this.a).setChecked(z);
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
