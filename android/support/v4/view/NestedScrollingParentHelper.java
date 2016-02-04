package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;

public class NestedScrollingParentHelper {
    private final ViewGroup f443a;
    private int f444b;

    public NestedScrollingParentHelper(ViewGroup viewGroup) {
        this.f443a = viewGroup;
    }

    public void m1012a(View view, View view2, int i) {
        this.f444b = i;
    }

    public int m1010a() {
        return this.f444b;
    }

    public void m1011a(View view) {
        this.f444b = 0;
    }
}
