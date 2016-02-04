package app.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomVideoView extends VideoView {
    private VideoStateChangeListener f2002a;

    public interface VideoStateChangeListener {
        void m4023a();

        void m4024b();

        void m4025c();

        void m4026d();
    }

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setVideoStateChangeListener(VideoStateChangeListener videoStateChangeListener) {
        this.f2002a = videoStateChangeListener;
    }

    public void start() {
        super.start();
        if (this.f2002a != null) {
            this.f2002a.m4023a();
        }
    }

    public void resume() {
        super.resume();
        if (this.f2002a != null) {
            this.f2002a.m4024b();
        }
    }

    public void pause() {
        super.pause();
        if (this.f2002a != null) {
            this.f2002a.m4025c();
        }
    }

    public void stopPlayback() {
        super.stopPlayback();
        if (this.f2002a != null) {
            this.f2002a.m4026d();
        }
    }
}
