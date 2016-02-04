package app.common;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import app.Main;
import app.common.entity.HistoryEntity.Type;
import app.events.AudioPlaybackEvent;
import app.events.AudioPlaybackEvent.State;
import app.util.Utils;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayer implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    static MediaPlayer f1971a;
    private static AudioPlayer f1972f;
    Map<String, Integer> f1973b;
    String f1974c;
    String f1975d;
    Timer f1976e;

    /* renamed from: app.common.AudioPlayer.1 */
    class C01161 extends TimerTask {
        final /* synthetic */ AudioPlayer f1970a;

        C01161(AudioPlayer audioPlayer) {
            this.f1970a = audioPlayer;
        }

        public void run() {
            if (this.f1970a.f1975d != null) {
                this.f1970a.f1973b.put(this.f1970a.f1975d, Integer.valueOf(AudioPlayer.f1971a.getCurrentPosition()));
                EventBus.m12779a().m12795d(new AudioPlaybackEvent(this.f1970a.f1975d));
            }
        }
    }

    static {
        f1972f = null;
    }

    private AudioPlayer() {
        this.f1973b = new HashMap(2);
        this.f1974c = null;
        this.f1975d = null;
        m3947d();
    }

    private void m3947d() {
        f1971a = new MediaPlayer();
        f1971a.setAudioStreamType(3);
        f1971a.setOnPreparedListener(this);
        f1971a.setOnCompletionListener(this);
        f1971a.setOnErrorListener(this);
    }

    public static synchronized AudioPlayer m3946a() {
        AudioPlayer audioPlayer;
        synchronized (AudioPlayer.class) {
            if (f1972f == null) {
                f1972f = new AudioPlayer();
            }
            audioPlayer = f1972f;
        }
        return audioPlayer;
    }

    public int m3950a(String str) {
        Integer num = (Integer) this.f1973b.get(str);
        return num == null ? -1 : num.intValue();
    }

    public void m3951a(String str, int i) {
        if (this.f1973b.get(str) != null) {
            this.f1973b.put(str, Integer.valueOf(i));
        }
        if (str.equals(this.f1975d)) {
            f1971a.seekTo(i);
        } else {
            this.f1973b.put(str, Integer.valueOf(i));
        }
    }

    public void m3952a(String str, String str2) {
        m3949f();
        if (str.equals(this.f1974c)) {
            Integer num = (Integer) this.f1973b.get(this.f1974c);
            if (num != null) {
                f1971a.seekTo(num.intValue());
            }
            f1971a.start();
            m3948e();
        } else {
            if (f1971a == null) {
                m3947d();
            } else {
                if (this.f1975d != null) {
                    EventBus.m12779a().m12795d(new AudioPlaybackEvent(this.f1975d, State.stopped));
                }
                f1971a.reset();
            }
            f1971a.setDataSource(Utils.m7079a(str2, Type.AUDIO));
            f1971a.prepareAsync();
            if (this.f1973b.get(str) == null) {
                this.f1973b.put(str, Integer.valueOf(0));
            }
            this.f1974c = str;
        }
        this.f1975d = str;
    }

    public void m3953b() {
        m3949f();
        if (f1971a.isPlaying()) {
            f1971a.pause();
        }
        this.f1975d = null;
    }

    public void m3955c() {
        m3949f();
        this.f1973b.clear();
        if (f1971a.isPlaying()) {
            f1971a.stop();
        }
        f1971a.release();
        this.f1975d = null;
        this.f1974c = null;
        f1972f = null;
    }

    public boolean m3954b(String str) {
        return str.equals(this.f1975d);
    }

    private void m3948e() {
        this.f1976e = new Timer(false);
        this.f1976e.scheduleAtFixedRate(new C01161(this), 0, 200);
    }

    private void m3949f() {
        if (this.f1976e != null) {
            this.f1976e.cancel();
            this.f1976e.purge();
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        f1971a.start();
        Integer num = (Integer) this.f1973b.get(this.f1975d);
        if (num != null) {
            f1971a.seekTo(num.intValue());
        }
        m3948e();
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        m3949f();
        EventBus.m12779a().m12795d(new AudioPlaybackEvent(this.f1975d, State.stopped));
        f1971a.stop();
        this.f1973b.remove(this.f1975d);
        this.f1975d = null;
        this.f1974c = null;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        m3949f();
        this.f1973b.remove(this.f1975d);
        this.f1975d = null;
        this.f1974c = null;
        f1971a.reset();
        Main.f1926a.m5679b("AudioPlayer onError. what: " + i + " - extra: " + i2);
        return true;
    }
}
