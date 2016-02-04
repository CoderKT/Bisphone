package app.events;

public class AudioPlaybackEvent {
    private String f2400a;
    private State f2401b;

    public enum State {
        playing,
        stopped
    }

    public AudioPlaybackEvent(String str) {
        this.f2400a = str;
        this.f2401b = State.playing;
    }

    public AudioPlaybackEvent(String str, State state) {
        this.f2400a = str;
        this.f2401b = state;
    }

    public String m4844a() {
        return this.f2400a;
    }

    public State m4845b() {
        return this.f2401b;
    }
}
