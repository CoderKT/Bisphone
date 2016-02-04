package app.galley.external;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import app.Main;
import app.galley.SelectedItemGalleryModel;
import app.storage.StorageAccessUtil;
import app.util.BitmapUtil;
import com.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class CropSingleImageFragment extends Fragment {
    CropImageView f2542a;
    SelectedItemGalleryModel f2543b;
    SingleImageComminucator f2544c;
    Bitmap f2545d;
    Bitmap f2546e;
    protected InputMethodManager f2547f;
    Uri f2548g;

    public interface SingleImageComminucator {
        void m4999k();

        void m5000l();

        void m5001m();
    }

    public void m5034a(SingleImageComminucator singleImageComminucator) {
        this.f2544c = singleImageComminucator;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903101, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHasOptionsMenu(true);
        m5029a(view);
        m5030b();
        m5031c();
    }

    private void m5030b() {
        this.f2543b = (SelectedItemGalleryModel) getArguments().getParcelable(DataPacketExtension.ELEMENT);
        this.f2548g = Uri.parse(getArguments().getString("output"));
    }

    private void m5029a(View view) {
        this.f2542a = (CropImageView) view.findViewById(2131755285);
        this.f2542a.setFixedAspectRatio(true);
    }

    private void m5031c() {
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = point.x;
        for (Entry value : this.f2543b.m5028a().entrySet()) {
            this.f2546e = BitmapUtil.m6971a(((GalleryItemModel) value.getValue()).f2575d, i);
            this.f2542a.setImageBitmap(this.f2546e);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820559, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        m5033a();
        switch (menuItem.getItemId()) {
            case 2131755644:
                this.f2544c.m4999k();
                break;
            case 2131755645:
                m5032d();
                this.f2544c.m5001m();
                break;
        }
        return true;
    }

    protected void m5033a() {
        this.f2547f = (InputMethodManager) getActivity().getSystemService("input_method");
        this.f2547f.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void m5032d() {
        Exception e;
        Throwable th;
        try {
            this.f2545d = this.f2542a.getCroppedImage();
        } catch (IllegalArgumentException e2) {
            this.f2545d = this.f2546e;
        } catch (NullPointerException e3) {
            Main.m3905a(getString(2131296410));
            this.f2544c.m5000l();
        }
        if (this.f2545d == null) {
            Main.m3905a(getString(2131296410));
            this.f2544c.m5000l();
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(StorageAccessUtil.m6962a(this.f2548g)));
            try {
                this.f2545d.compress(CompressFormat.PNG, 100, fileOutputStream);
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
