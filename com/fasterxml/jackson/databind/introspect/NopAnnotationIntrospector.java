package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;

public abstract class NopAnnotationIntrospector extends AnnotationIntrospector implements Serializable {
    public static final NopAnnotationIntrospector instance;

    /* renamed from: com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector.1 */
    final class C06281 extends NopAnnotationIntrospector {
        C06281() {
        }
    }

    static {
        instance = new C06281();
    }
}
