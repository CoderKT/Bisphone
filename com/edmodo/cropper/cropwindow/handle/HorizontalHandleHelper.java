package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.util.AspectRatioUtil;

class HorizontalHandleHelper extends HandleHelper {
    private Edge f5620a;

    HorizontalHandleHelper(Edge edge) {
        super(edge, null);
        this.f5620a = edge;
    }

    void m8261a(float f, float f2, float f3, Rect rect, float f4) {
        this.f5620a.m8245a(f, f2, rect, f4, f3);
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        float a3 = Edge.RIGHT.m8242a();
        a2 = (AspectRatioUtil.m8263a(a2, Edge.BOTTOM.m8242a(), f3) - (a3 - a)) / 2.0f;
        a -= a2;
        a2 += a3;
        Edge.LEFT.m8244a(a);
        Edge.RIGHT.m8244a(a2);
        if (Edge.LEFT.m8246a(rect, f4) && !this.f5620a.m8247a(Edge.LEFT, rect, f3)) {
            Edge.RIGHT.m8249b(-Edge.LEFT.m8243a(rect));
            this.f5620a.m8250c(f3);
        }
        if (Edge.RIGHT.m8246a(rect, f4) && !this.f5620a.m8247a(Edge.RIGHT, rect, f3)) {
            Edge.LEFT.m8249b(-Edge.RIGHT.m8243a(rect));
            this.f5620a.m8250c(f3);
        }
    }
}
