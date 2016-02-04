package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;

class CenterHandleHelper extends HandleHelper {
    CenterHandleHelper() {
        super(null, null);
    }

    void m8257a(float f, float f2, Rect rect, float f3) {
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        a = f - ((a + Edge.RIGHT.m8242a()) / 2.0f);
        a2 = f2 - ((a2 + Edge.BOTTOM.m8242a()) / 2.0f);
        Edge.LEFT.m8249b(a);
        Edge.TOP.m8249b(a2);
        Edge.RIGHT.m8249b(a);
        Edge.BOTTOM.m8249b(a2);
        if (Edge.LEFT.m8246a(rect, f3)) {
            Edge.RIGHT.m8249b(Edge.LEFT.m8243a(rect));
        } else if (Edge.RIGHT.m8246a(rect, f3)) {
            Edge.LEFT.m8249b(Edge.RIGHT.m8243a(rect));
        }
        if (Edge.TOP.m8246a(rect, f3)) {
            Edge.BOTTOM.m8249b(Edge.TOP.m8243a(rect));
        } else if (Edge.BOTTOM.m8246a(rect, f3)) {
            Edge.TOP.m8249b(Edge.BOTTOM.m8243a(rect));
        }
    }

    void m8256a(float f, float f2, float f3, Rect rect, float f4) {
        m8257a(f, f2, rect, f4);
    }
}
