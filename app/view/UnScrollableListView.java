package app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class UnScrollableListView extends ListView {
    public UnScrollableListView(Context context) {
        super(context);
    }

    public UnScrollableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UnScrollableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
