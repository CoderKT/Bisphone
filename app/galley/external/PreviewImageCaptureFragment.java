package app.galley.external;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import app.common.CustomImageLoader;
import app.galley.ActionEditText;
import app.util.MediaPickerUtil;
import com.ortiz.touch.TouchImageView;
import java.io.File;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class PreviewImageCaptureFragment extends Fragment {
    String f2644a;
    File f2645b;
    ActionEditText f2646c;
    TouchImageView f2647d;
    ImageView f2648e;
    String f2649f;
    boolean f2650g;
    protected InputMethodManager f2651h;

    /* renamed from: app.galley.external.PreviewImageCaptureFragment.1 */
    class C01581 implements OnClickListener {
        final /* synthetic */ PreviewImageCaptureFragment f2642a;

        C01581(PreviewImageCaptureFragment previewImageCaptureFragment) {
            this.f2642a = previewImageCaptureFragment;
        }

        public void onClick(View view) {
            boolean z = true;
            if (this.f2642a.f2650g) {
                this.f2642a.m5108a();
                this.f2642a.f2649f = this.f2642a.f2646c.getText().toString();
                this.f2642a.f2648e.setImageResource(2130837787);
                this.f2642a.f2646c.setEnabled(false);
            } else {
                this.f2642a.m5105M();
                this.f2642a.f2646c.setEnabled(true);
                this.f2642a.f2648e.setImageResource(2130837789);
            }
            PreviewImageCaptureFragment previewImageCaptureFragment = this.f2642a;
            if (this.f2642a.f2650g) {
                z = false;
            }
            previewImageCaptureFragment.f2650g = z;
        }
    }

    /* renamed from: app.galley.external.PreviewImageCaptureFragment.2 */
    class C01592 implements OnEditorActionListener {
        final /* synthetic */ PreviewImageCaptureFragment f2643a;

        C01592(PreviewImageCaptureFragment previewImageCaptureFragment) {
            this.f2643a = previewImageCaptureFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                this.f2643a.f2648e.performClick();
            }
            return false;
        }
    }

    public PreviewImageCaptureFragment() {
        this.f2645b = null;
        this.f2650g = false;
    }

    public View m5107a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130903143, viewGroup, false);
    }

    public void m5110a(View view, Bundle bundle) {
        super.m199a(view, bundle);
        m215d(true);
        m5104a(view);
        m5103O();
    }

    private void m5103O() {
        this.f2651h = (InputMethodManager) m227i().getSystemService("input_method");
        this.f2648e.setOnClickListener(new C01581(this));
        this.f2644a = m223g().getString(DataPacketExtension.ELEMENT);
        if (this.f2644a != null) {
            this.f2645b = new File(this.f2644a);
            if (!this.f2645b.exists()) {
                m227i().finish();
            }
            CustomImageLoader.m4009a().m4016a(this.f2647d, this.f2645b);
        }
    }

    private void m5104a(View view) {
        this.f2646c = (ActionEditText) view.findViewById(2131755404);
        this.f2647d = (TouchImageView) view.findViewById(2131755402);
        this.f2648e = (ImageView) view.findViewById(2131755292);
        this.f2646c.setOnEditorActionListener(new C01592(this));
    }

    public void m5109a(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131820556, menu);
        super.m198a(menu, menuInflater);
    }

    public boolean m5111a(MenuItem menuItem) {
        m5108a();
        switch (menuItem.getItemId()) {
            case 2131755644:
                this.f2645b = null;
                this.f2645b = MediaPickerUtil.m7032a(m227i(), 600);
                break;
            case 2131755645:
                Intent intent = m227i().getIntent();
                intent.putExtra("image_capture_path", this.f2644a);
                intent.putExtra("image_capture_caption", this.f2649f);
                m227i().setResult(-1, intent);
                m227i().finish();
                break;
        }
        return super.m201a(menuItem);
    }

    protected void m5108a() {
        this.f2651h.hideSoftInputFromWindow(m237p().getWindowToken(), 0);
    }

    protected void m5105M() {
        this.f2646c.requestFocus();
        this.f2651h.showSoftInput(this.f2646c, 2);
        m227i().getWindow().setSoftInputMode(4);
    }

    public void m5106N() {
        if (this.f2645b != null) {
            this.f2644a = this.f2645b.getAbsolutePath();
            CustomImageLoader.m4009a().m4016a(this.f2647d, this.f2645b);
        }
    }
}
