package app.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import app.Main;
import app.storage.StorageAccessUtil;
import com.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropActivity extends Activity implements OnClickListener {
    Bitmap f4219a;
    CropImageView f4220b;
    Uri f4221c;
    Bitmap f4222d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903118);
        this.f4220b = (CropImageView) findViewById(2131755306);
        this.f4220b.setFixedAspectRatio(true);
        findViewById(2131755308).setOnClickListener(this);
        findViewById(2131755309).setOnClickListener(this);
        this.f4221c = (Uri) getIntent().getParcelableExtra("output");
        Uri uri = (Uri) getIntent().getParcelableExtra("source");
        this.f4222d = null;
        try {
            this.f4222d = Media.getBitmap(getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            Main.m3905a(getString(2131296410));
            setResult(0, getIntent());
            finish();
        }
        if (this.f4222d != null) {
            this.f4220b.setImageBitmap(this.f4222d);
        }
    }

    public void onClick(View view) {
        Intent intent = getIntent();
        switch (view.getId()) {
            case 2131755308:
                setResult(0, intent);
                break;
            case 2131755309:
                m6694a();
                setResult(-1, intent);
                break;
        }
        finish();
    }

    private void m6694a() {
        Exception e;
        Throwable th;
        try {
            this.f4219a = this.f4220b.getCroppedImage();
        } catch (IllegalArgumentException e2) {
            this.f4219a = this.f4222d;
        } catch (NullPointerException e3) {
            Main.m3905a(getString(2131296410));
            setResult(0, getIntent());
            finish();
        }
        if (this.f4219a == null) {
            Main.m3905a(getString(2131296410));
            setResult(0, getIntent());
            finish();
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(StorageAccessUtil.m6962a(this.f4221c)));
            try {
                this.f4219a.compress(CompressFormat.PNG, 100, fileOutputStream);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    Main.m3905a(getString(2131296410));
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            fileOutputStream = null;
            e.printStackTrace();
            Main.m3905a(getString(2131296410));
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }
}
