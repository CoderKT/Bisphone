package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.cropwindow.edge.EdgePair;

class CornerHandleHelper extends HandleHelper {
    CornerHandleHelper(Edge edge, Edge edge2) {
        super(edge, edge2);
    }

    void m8258a(float f, float f2, float f3, Rect rect, float f4) {
        EdgePair a = m8253a(f, f2, f3);
        Edge edge = a.f5604a;
        Edge edge2 = a.f5605b;
        edge.m8245a(f, f2, rect, f4, f3);
        edge2.m8250c(f3);
        if (edge2.m8246a(rect, f4)) {
            edge2.m8243a(rect);
            edge.m8250c(f3);
        }
    }
}
