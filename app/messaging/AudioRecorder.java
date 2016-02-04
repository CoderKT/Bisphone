package app.messaging;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import app.Main;
import app.storage.Storage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class AudioRecorder {
    MediaRecorder f3165a;
    File f3166b;
    TickTakListener f3167c;
    Timer f3168d;
    private volatile boolean f3169e;

    /* renamed from: app.messaging.AudioRecorder.1 */
    class C02491 implements OnErrorListener {
        final /* synthetic */ AudioRecorder f3162a;

        C02491(AudioRecorder audioRecorder) {
            this.f3162a = audioRecorder;
        }

        public void onError(MediaRecorder mediaRecorder, int i, int i2) {
            this.f3162a.m5698a();
        }
    }

    /* renamed from: app.messaging.AudioRecorder.2 */
    class C02502 extends TimerTask {
        volatile int f3163a;
        final /* synthetic */ AudioRecorder f3164b;

        C02502(AudioRecorder audioRecorder) {
            this.f3164b = audioRecorder;
            this.f3163a = 0;
        }

        public void run() {
            this.f3163a++;
            if (this.f3164b.f3167c != null) {
                this.f3164b.f3167c.m5697a(this.f3163a);
            }
        }

        public boolean cancel() {
            this.f3163a = 0;
            return super.cancel();
        }
    }

    public interface TickTakListener {
        void m5697a(int i);
    }

    public AudioRecorder() {
        this.f3165a = null;
        this.f3166b = null;
        this.f3167c = null;
        this.f3169e = false;
    }

    public synchronized void m5699a(File file) {
        if (!this.f3169e) {
            if (Storage.m6939a(Main.f1927b, file.getParentFile())) {
                this.f3169e = true;
                this.f3166b = file;
                this.f3165a = new MediaRecorder();
                this.f3165a.setAudioSource(1);
                this.f3165a.setOutputFormat(2);
                this.f3165a.setAudioEncoder(3);
                this.f3165a.setOnErrorListener(new C02491(this));
                this.f3165a.setOutputFile(this.f3166b.getAbsolutePath());
                this.f3165a.prepare();
                this.f3165a.start();
                this.f3168d = new Timer(true);
                this.f3168d.scheduleAtFixedRate(new C02502(this), 1000, 1000);
            }
        }
    }

    public synchronized void m5698a() {
        if (this.f3168d != null) {
            this.f3168d.cancel();
        }
        if (this.f3165a != null) {
            try {
                this.f3165a.stop();
            } catch (Throwable e) {
                Main.f1926a.m5680b(e);
            }
            this.f3165a.release();
            this.f3165a = null;
        }
        this.f3169e = false;
    }

    public File m5700b() {
        return this.f3166b;
    }
}
