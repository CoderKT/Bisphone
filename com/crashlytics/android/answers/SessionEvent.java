package com.crashlytics.android.answers;

import android.app.Activity;
import java.util.Collections;
import java.util.Map;

final class SessionEvent {
    public final SessionEventMetadata f5495a;
    public final long f5496b;
    public final Type f5497c;
    public final Map<String, String> f5498d;
    public final String f5499e;
    public final Map<String, Object> f5500f;
    private String f5501g;

    enum Type {
        CREATE,
        START,
        RESUME,
        SAVE_INSTANCE_STATE,
        PAUSE,
        STOP,
        DESTROY,
        ERROR,
        CRASH,
        INSTALL,
        CUSTOM
    }

    public static SessionEvent m8175a(SessionEventMetadata sessionEventMetadata, Type type, Activity activity) {
        return m8176a(sessionEventMetadata, type, Collections.singletonMap("activity", activity.getClass().getName()));
    }

    public static SessionEvent m8174a(SessionEventMetadata sessionEventMetadata) {
        return m8176a(sessionEventMetadata, Type.INSTALL, Collections.emptyMap());
    }

    public static SessionEvent m8178a(SessionEventMetadata sessionEventMetadata, String str) {
        return m8176a(sessionEventMetadata, Type.ERROR, Collections.singletonMap("sessionId", str));
    }

    public static SessionEvent m8179b(SessionEventMetadata sessionEventMetadata, String str) {
        return m8176a(sessionEventMetadata, Type.CRASH, Collections.singletonMap("sessionId", str));
    }

    private static SessionEvent m8176a(SessionEventMetadata sessionEventMetadata, Type type, Map<String, String> map) {
        return m8177a(sessionEventMetadata, type, map, null, Collections.emptyMap());
    }

    private static SessionEvent m8177a(SessionEventMetadata sessionEventMetadata, Type type, Map<String, String> map, String str, Map<String, Object> map2) {
        return new SessionEvent(sessionEventMetadata, System.currentTimeMillis(), type, map, str, map2);
    }

    private SessionEvent(SessionEventMetadata sessionEventMetadata, long j, Type type, Map<String, String> map, String str, Map<String, Object> map2) {
        this.f5495a = sessionEventMetadata;
        this.f5496b = j;
        this.f5497c = type;
        this.f5498d = map;
        this.f5499e = str;
        this.f5500f = map2;
    }

    public String toString() {
        if (this.f5501g == null) {
            this.f5501g = "[" + getClass().getSimpleName() + ": " + "timestamp=" + this.f5496b + ", type=" + this.f5497c + ", details=" + this.f5498d.toString() + ", customType=" + this.f5499e + ", customAttributes=" + this.f5500f.toString() + ", metadata=[" + this.f5495a + "]]";
        }
        return this.f5501g;
    }
}
