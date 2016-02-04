package app.messaging.vh;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import app.Main;
import app.common.CustomImageLoader;
import app.common.entity.HistoryEntity;
import app.common.entity.StickerEntity;
import app.common.entity.StickerPackEntity;
import app.messaging.stickers.OnlineStickersActivity;
import app.messaging.stickers.StickerManager;
import app.storage.StorageException;
import app.util.Background;
import app.util.BubbleDialogUtil;
import butterknife.ButterKnife;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.File;
import se.emilsjolander.stickylistheaders.C1128R;

public class Sticker extends MessageViewHolder {
    ImageView f4092l;

    /* renamed from: app.messaging.vh.Sticker.1 */
    class C04171 implements OnLongClickListener {
        final /* synthetic */ Sticker f4089a;

        C04171(Sticker sticker) {
            this.f4089a = sticker;
        }

        public boolean onLongClick(View view) {
            BubbleDialogUtil.m6982a().m7002a(this.f4089a.s, this.f4089a.F, this.f4089a.J, this.f4089a.G, this.f4089a.t);
            return true;
        }
    }

    /* renamed from: app.messaging.vh.Sticker.2 */
    class C04182 implements OnClickListener {
        final /* synthetic */ Sticker f4090a;

        C04182(Sticker sticker) {
            this.f4090a = sticker;
        }

        public void onClick(View view) {
            if (!this.f4090a.m6577c(this.f4090a.F.m4397C())) {
                Intent intent = new Intent(this.f4090a.s, OnlineStickersActivity.class);
                intent.putExtra("selected_sticker_id", this.f4090a.F.m4397C());
                this.f4090a.s.startActivity(intent);
            }
        }
    }

    /* renamed from: app.messaging.vh.Sticker.3 */
    class C04193 implements OnTouchListener {
        final /* synthetic */ Sticker f4091a;

        C04193(Sticker sticker) {
            this.f4091a = sticker;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f4091a.f4092l.setColorFilter(1342177280, Mode.SRC_ATOP);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4091a.f4092l.setColorFilter(null);
                    break;
            }
            return false;
        }
    }

    public Sticker(View view, Context context) {
        super(view, context);
        ButterKnife.m7744a(this, view);
        m6579y();
    }

    public void m6578a(Context context, HistoryEntity historyEntity, int i, int i2) {
        super.m6519a(context, historyEntity, i, i2);
        Background.m6968a(null, this.v);
        this.f4092l.setColorFilter(null);
        this.f4092l.setImageDrawable(null);
        ImageLoader.m11076a().m11081a(this.f4092l);
        if (StickerManager.m6491b().m6498c() == null || StickerManager.m6491b().m6498c().get(historyEntity.m4396B()) == null) {
            StickerManager.m6491b().m6496a(context, historyEntity.m4396B(), this.f4092l);
            return;
        }
        try {
            File file = new File(((StickerEntity) StickerManager.m6491b().m6498c().get(historyEntity.m4396B())).m4477c());
            if (file.exists()) {
                CustomImageLoader.m4009a().m4022b(this.f4092l, file);
            } else {
                StickerManager.m6491b().m6496a(context, historyEntity.m4396B(), this.f4092l);
            }
        } catch (Throwable e) {
            StickerManager.m6491b().m6496a(context, historyEntity.m4396B(), this.f4092l);
            Main.f1926a.m5682c(e);
        } catch (StorageException e2) {
        }
    }

    public void m6579y() {
        super.m6522y();
        this.v.setOnLongClickListener(new C04171(this));
        this.v.setOnClickListener(new C04182(this));
        this.v.setOnTouchListener(new C04193(this));
    }

    private boolean m6577c(int i) {
        SparseArray d = StickerManager.m6491b().m6499d();
        for (int i2 = 0; i2 < StickerManager.m6491b().m6499d().size(); i2++) {
            if (i == ((StickerPackEntity) d.get(d.keyAt(i2))).m4480a()) {
                return true;
            }
        }
        return false;
    }
}
