package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;

public enum Handle {
    TOP_LEFT(new CornerHandleHelper(Edge.TOP, Edge.LEFT)),
    TOP_RIGHT(new CornerHandleHelper(Edge.TOP, Edge.RIGHT)),
    BOTTOM_LEFT(new CornerHandleHelper(Edge.BOTTOM, Edge.LEFT)),
    BOTTOM_RIGHT(new CornerHandleHelper(Edge.BOTTOM, Edge.RIGHT)),
    LEFT(new VerticalHandleHelper(Edge.LEFT)),
    TOP(new HorizontalHandleHelper(Edge.TOP)),
    RIGHT(new VerticalHandleHelper(Edge.RIGHT)),
    BOTTOM(new HorizontalHandleHelper(Edge.BOTTOM)),
    CENTER(new CenterHandleHelper());
    
    private HandleHelper f5619j;

    private Handle(HandleHelper handleHelper) {
        this.f5619j = handleHelper;
    }

    public void m8260a(float f, float f2, Rect rect, float f3) {
        this.f5619j.m8255a(f, f2, rect, f3);
    }

    public void m8259a(float f, float f2, float f3, Rect rect, float f4) {
        this.f5619j.m8254a(f, f2, f3, rect, f4);
    }
}
