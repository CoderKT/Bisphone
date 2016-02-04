package app.messaging.stickers;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.StickerPackEntity;
import app.events.sticker.StickerPackSelectedEvent;
import app.storage.StorageException;
import app.util.Background;
import de.greenrobot.event.EventBus;
import java.io.File;
import se.emilsjolander.stickylistheaders.C1128R;

public class StickerPacksContainer extends LinearLayout {

    /* renamed from: app.messaging.stickers.StickerPacksContainer.1 */
    class C03831 implements OnClickListener {
        final /* synthetic */ StickerPackEntity f3982a;
        final /* synthetic */ StickerPacksContainer f3983b;

        C03831(StickerPacksContainer stickerPacksContainer, StickerPackEntity stickerPackEntity) {
            this.f3983b = stickerPacksContainer;
            this.f3982a = stickerPackEntity;
        }

        public void onClick(View view) {
            EventBus.m12779a().m12795d(new StickerPackSelectedEvent(this.f3982a.m4480a()));
            this.f3983b.m6500a(this.f3982a.m4480a());
        }
    }

    /* renamed from: app.messaging.stickers.StickerPacksContainer.2 */
    class C03842 implements OnTouchListener {
        final /* synthetic */ ImageButton f3984a;
        final /* synthetic */ StickerPacksContainer f3985b;

        C03842(StickerPacksContainer stickerPacksContainer, ImageButton imageButton) {
            this.f3985b = stickerPacksContainer;
            this.f3984a = imageButton;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f3984a.setColorFilter(1342177280, Mode.SRC_ATOP);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f3984a.setColorFilter(null);
                    break;
            }
            return false;
        }
    }

    public StickerPacksContainer(Context context) {
        super(context);
        setOrientation(0);
        setLayoutParams(new LayoutParams(-1, -1));
        SparseArray d = StickerManager.m6491b().m6499d();
        for (int i = 0; i < d.size(); i++) {
            m6501a((StickerPackEntity) d.valueAt(i));
        }
    }

    public void m6500a(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (Integer.parseInt(getChildAt(i2).getTag().toString()) == i) {
                getChildAt(i2).setBackgroundColor(getResources().getColor(2131689527));
            } else {
                getChildAt(i2).setBackgroundColor(0);
            }
        }
    }

    public void m6501a(StickerPackEntity stickerPackEntity) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131492966);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131492965);
        ImageView imageButton = new ImageButton(getContext());
        imageButton.setTag(Integer.valueOf(stickerPackEntity.m4480a()));
        ViewGroup.LayoutParams marginLayoutParams = new MarginLayoutParams(dimensionPixelSize, dimensionPixelSize);
        marginLayoutParams.setMargins(dimensionPixelSize2 / 2, dimensionPixelSize2 * 2, dimensionPixelSize2 / 2, dimensionPixelSize2 * 2);
        imageButton.setLayoutParams(marginLayoutParams);
        try {
            CustomImageLoader.m4009a().m4022b(imageButton, new File(StickerPackEntity.m4478c(stickerPackEntity.m4480a())));
        } catch (StorageException e) {
        } catch (Throwable e2) {
            Main.f1926a.m5684d(e2);
        }
        imageButton.setOnClickListener(new C03831(this, stickerPackEntity));
        Background.m6968a(null, imageButton);
        imageButton.setColorFilter(null);
        imageButton.setOnTouchListener(new C03842(this, imageButton));
        addView(imageButton);
    }
}
