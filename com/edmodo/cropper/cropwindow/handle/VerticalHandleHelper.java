package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.util.AspectRatioUtil;

class VerticalHandleHelper extends HandleHelper {
    private Edge f5621a;

    VerticalHandleHelper(Edge edge) {
        super(null, edge);
        this.f5621a = edge;
    }

    void m8262a(float f, float f2, float f3, Rect rect, float f4) {
        this.f5621a.m8245a(f, f2, rect, f4, f3);
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        float a3 = Edge.RIGHT.m8242a();
        float a4 = Edge.BOTTOM.m8242a();
        a = (AspectRatioUtil.m8266b(a, a3, f3) - (a4 - a2)) / 2.0f;
        a2 -= a;
        a += a4;
        Edge.TOP.m8244a(a2);
        Edge.BOTTOM.m8244a(a);
        if (Edge.TOP.m8246a(rect, f4) && !this.f5621a.m8247a(Edge.TOP, rect, f3)) {
            Edge.BOTTOM.m8249b(-Edge.TOP.m8243a(rect));
            this.f5621a.m8250c(f3);
        }
        if (Edge.BOTTOM.m8246a(rect, f4) && !this.f5621a.m8247a(Edge.BOTTOM, rect, f3)) {
            Edge.TOP.m8249b(-Edge.BOTTOM.m8243a(rect));
            this.f5621a.m8250c(f3);
        }
    }
}
