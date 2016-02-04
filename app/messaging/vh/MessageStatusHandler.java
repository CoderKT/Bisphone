package app.messaging.vh;

import android.content.Context;
import android.widget.ImageView;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import se.emilsjolander.stickylistheaders.C1128R;

public class MessageStatusHandler {

    /* renamed from: app.messaging.vh.MessageStatusHandler.1 */
    /* synthetic */ class C04021 {
        static final /* synthetic */ int[] f4054a;

        static {
            f4054a = new int[DeliveryStatus.values().length];
            try {
                f4054a[DeliveryStatus.UPLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4054a[DeliveryStatus.SENDING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4054a[DeliveryStatus.FAILED_TO_SEND.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4054a[DeliveryStatus.SENT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4054a[DeliveryStatus.FAILED_TO_UPLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4054a[DeliveryStatus.DELIVERED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static void m6556a(HistoryEntity historyEntity, Context context, ImageView imageView) {
        if (imageView != null) {
            switch (C04021.f4054a[historyEntity.m4420g().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    imageView.setImageDrawable(context.getResources().getDrawable(2130837928));
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    imageView.setImageDrawable(context.getResources().getDrawable(2130837929));
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    imageView.setImageDrawable(context.getResources().getDrawable(2130837927));
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    imageView.setImageDrawable(context.getResources().getDrawable(2130837926));
                default:
                    imageView.setImageBitmap(null);
            }
        }
    }
}
