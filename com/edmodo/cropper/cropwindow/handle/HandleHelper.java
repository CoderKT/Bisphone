package com.edmodo.cropper.cropwindow.handle;

import android.graphics.Rect;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.cropwindow.edge.EdgePair;
import com.edmodo.cropper.util.AspectRatioUtil;

abstract class HandleHelper {
    private Edge f5606a;
    private Edge f5607b;
    private EdgePair f5608c;

    abstract void m8254a(float f, float f2, float f3, Rect rect, float f4);

    HandleHelper(Edge edge, Edge edge2) {
        this.f5606a = edge;
        this.f5607b = edge2;
        this.f5608c = new EdgePair(this.f5606a, this.f5607b);
    }

    void m8255a(float f, float f2, Rect rect, float f3) {
        EdgePair a = m8252a();
        Edge edge = a.f5604a;
        Edge edge2 = a.f5605b;
        if (edge != null) {
            edge.m8245a(f, f2, rect, f3, 1.0f);
        }
        if (edge2 != null) {
            edge2.m8245a(f, f2, rect, f3, 1.0f);
        }
    }

    EdgePair m8252a() {
        return this.f5608c;
    }

    EdgePair m8253a(float f, float f2, float f3) {
        if (m8251a(f, f2) > f3) {
            this.f5608c.f5604a = this.f5607b;
            this.f5608c.f5605b = this.f5606a;
        } else {
            this.f5608c.f5604a = this.f5606a;
            this.f5608c.f5605b = this.f5607b;
        }
        return this.f5608c;
    }

    private float m8251a(float f, float f2) {
        float a = this.f5607b == Edge.LEFT ? f : Edge.LEFT.m8242a();
        float a2 = this.f5606a == Edge.TOP ? f2 : Edge.TOP.m8242a();
        if (this.f5607b != Edge.RIGHT) {
            f = Edge.RIGHT.m8242a();
        }
        if (this.f5606a != Edge.BOTTOM) {
            f2 = Edge.BOTTOM.m8242a();
        }
        return AspectRatioUtil.m8264a(a, a2, f, f2);
    }
}
