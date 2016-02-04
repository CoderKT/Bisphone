package app.events.net;

public class MediaUploadProgressEvent {
    protected String f2492a;
    protected float f2493b;

    public MediaUploadProgressEvent(String str) {
        this.f2492a = str;
        this.f2493b = 0.0f;
    }

    public String m4951a() {
        return this.f2492a;
    }

    public float m4953b() {
        return this.f2493b;
    }

    public void m4952a(float f) {
        this.f2493b = f;
    }
}
