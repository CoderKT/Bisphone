package app.events.net;

import se.emilsjolander.stickylistheaders.C1128R;

public abstract class MediaDownloadProgressEvent {
    protected String f2483a;
    protected float f2484b;
    protected State f2485c;

    /* renamed from: app.events.net.MediaDownloadProgressEvent.1 */
    /* synthetic */ class C01391 {
        static final /* synthetic */ int[] f2486a;

        static {
            f2486a = new int[State.values().length];
            try {
                f2486a[State.successful.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2486a[State.failed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2486a[State.canceled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum State {
        progressing,
        successful,
        failed,
        canceled
    }

    public MediaDownloadProgressEvent(String str) {
        this.f2483a = str;
        this.f2484b = 0.0f;
        this.f2485c = State.progressing;
    }

    public String m4946a() {
        return this.f2483a;
    }

    public float m4949b() {
        return this.f2484b;
    }

    public State m4950c() {
        return this.f2485c;
    }

    public void m4947a(float f) {
        this.f2484b = f;
    }

    public void m4948a(State state) {
        this.f2485c = state;
        switch (C01391.f2486a[state.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f2484b = 1.0f;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f2484b = -1.0f;
            default:
        }
    }
}
