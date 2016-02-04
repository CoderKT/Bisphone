package app.messaging;

import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import app.Main;
import app.common.LocalizeActivity;
import app.storage.Storage;
import app.storage.StorageException;
import java.io.File;

public class VideoCapture extends LocalizeActivity implements Callback, OnClickListener {
    MediaRecorder f3311k;
    SurfaceHolder f3312o;
    boolean f3313p;

    public VideoCapture() {
        this.f3313p = false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setRequestedOrientation(0);
        this.f3311k = new MediaRecorder();
        m5985j();
        setContentView(2130903104);
        SurfaceView surfaceView = (SurfaceView) findViewById(2131755296);
        this.f3312o = surfaceView.getHolder();
        this.f3312o.addCallback(this);
        this.f3312o.setType(3);
        surfaceView.setClickable(true);
        surfaceView.setOnClickListener(this);
    }

    private void m5985j() {
        try {
            this.f3311k.setAudioSource(0);
            this.f3311k.setVideoSource(0);
            this.f3311k.setProfile(CamcorderProfile.get(1));
            this.f3311k.setOutputFile(Storage.m6952g() + File.separator + "temp_record.mp4");
            this.f3311k.setMaxDuration(50000);
            this.f3311k.setMaxFileSize(5242880);
        } catch (StorageException e) {
            Storage.m6945c(this);
        }
    }

    private void m5986k() {
        this.f3311k.setPreviewDisplay(this.f3312o.getSurface());
        try {
            this.f3311k.prepare();
        } catch (Throwable e) {
            Main.f1926a.m5680b(e);
            finish();
        } catch (Throwable e2) {
            Main.f1926a.m5680b(e2);
            finish();
        }
    }

    public void onClick(View view) {
        if (this.f3313p) {
            this.f3311k.stop();
            this.f3313p = false;
            m5985j();
            m5986k();
            return;
        }
        this.f3313p = true;
        this.f3311k.start();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        m5986k();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.f3313p) {
            this.f3311k.stop();
            this.f3313p = false;
        }
        this.f3311k.release();
        finish();
    }
}
