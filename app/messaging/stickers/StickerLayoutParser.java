package app.messaging.stickers;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import app.Main;
import app.common.CustomImageLoader;
import app.events.sticker.StickerSelectedEvent;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.Background;
import de.greenrobot.event.EventBus;
import java.io.File;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class StickerLayoutParser {
    Context f3956a;
    int f3957b;
    private boolean f3958c;

    /* renamed from: app.messaging.stickers.StickerLayoutParser.1 */
    class C03731 implements OnTouchListener {
        final /* synthetic */ ImageView f3945a;
        final /* synthetic */ StickerLayoutParser f3946b;

        C03731(StickerLayoutParser stickerLayoutParser, ImageView imageView) {
            this.f3946b = stickerLayoutParser;
            this.f3945a = imageView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f3945a.setColorFilter(1342177280, Mode.SRC_ATOP);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f3945a.setColorFilter(null);
                    break;
            }
            return false;
        }
    }

    /* renamed from: app.messaging.stickers.StickerLayoutParser.2 */
    class C03752 implements OnClickListener {
        final /* synthetic */ int f3948a;
        final /* synthetic */ StickerLayoutParser f3949b;

        /* renamed from: app.messaging.stickers.StickerLayoutParser.2.1 */
        class C03741 implements AnimationListener {
            final /* synthetic */ C03752 f3947a;

            C03741(C03752 c03752) {
                this.f3947a = c03752;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.f3947a.f3949b.f3958c = true;
            }

            public void onAnimationRepeat(Animation animation) {
            }
        }

        C03752(StickerLayoutParser stickerLayoutParser, int i) {
            this.f3949b = stickerLayoutParser;
            this.f3948a = i;
        }

        public void onClick(View view) {
            if (this.f3949b.f3958c) {
                Animation loadAnimation = AnimationUtils.loadAnimation(Main.f1927b, 2130968590);
                loadAnimation.setAnimationListener(new C03741(this));
                view.startAnimation(loadAnimation);
                EventBus.m12779a().m12795d(new StickerSelectedEvent(this.f3948a, this.f3948a / 100));
            }
            this.f3949b.f3958c = false;
        }
    }

    /* renamed from: app.messaging.stickers.StickerLayoutParser.3 */
    /* synthetic */ class C03763 {
        static final /* synthetic */ int[] f3950a;

        static {
            f3950a = new int[Elements.values().length];
            try {
                f3950a[Elements.COLUMN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3950a[Elements.SUB_ROW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3950a[Elements.SUB_COLUMN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3950a[Elements.ROW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    enum Elements {
        ROW,
        COLUMN,
        SUB_ROW,
        SUB_COLUMN
    }

    public StickerLayoutParser(Context context) {
        this.f3956a = context;
        this.f3957b = context.getResources().getDimensionPixelSize(2131492970);
        this.f3958c = true;
    }

    public ViewGroup m6478a(XmlPullParser xmlPullParser) {
        ViewGroup linearLayout = new LinearLayout(this.f3956a);
        ViewGroup linearLayout2 = new LinearLayout(this.f3956a);
        ViewGroup linearLayout3 = new LinearLayout(this.f3956a);
        ViewGroup linearLayout4 = new LinearLayout(this.f3956a);
        ViewGroup linearLayout5 = new LinearLayout(this.f3956a);
        Elements elements = Elements.ROW;
        int i = 0;
        while (i == 0) {
            int next = xmlPullParser.next();
            if (next == 2 && "layout".equals(xmlPullParser.getName())) {
                linearLayout = new LinearLayout(this.f3956a);
                linearLayout.setOrientation(1);
                linearLayout.setLayoutParams(new LayoutParams(-1, -2));
            } else if (next == 2 && "row".equals(xmlPullParser.getName())) {
                linearLayout2 = m6472a(linearLayout);
                elements = Elements.ROW;
            } else if (next == 2 && "col".equals(xmlPullParser.getName())) {
                linearLayout3 = m6473a(linearLayout2, Float.parseFloat(xmlPullParser.getAttributeValue("", "w")));
                elements = Elements.COLUMN;
            } else if (next == 2 && "sub-row".equals(xmlPullParser.getName())) {
                linearLayout4 = m6476b(linearLayout3, Float.parseFloat(xmlPullParser.getAttributeValue("", "w")));
                elements = Elements.SUB_ROW;
            } else if (next != 2 || !"sub-col".equals(xmlPullParser.getName())) {
                if (next == 2 && "sticker".equals(xmlPullParser.getName())) {
                    next = Integer.parseInt(xmlPullParser.getAttributeValue("", "id"));
                    switch (C03763.f3950a[elements.ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            m6471a(linearLayout3, next);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            m6471a(linearLayout4, next);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            m6471a(linearLayout5, next);
                            break;
                        default:
                            m6471a(linearLayout2, next);
                            break;
                    }
                } else if (next == 3 && "layout".equals(xmlPullParser.getName())) {
                    i = 1;
                }
            } else {
                linearLayout5 = m6477c(linearLayout4, Float.parseFloat(xmlPullParser.getAttributeValue("", "w")));
                elements = Elements.SUB_COLUMN;
            }
        }
        return linearLayout;
    }

    private LinearLayout m6472a(ViewGroup viewGroup) {
        View linearLayout = new LinearLayout(this.f3956a);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    private LinearLayout m6473a(ViewGroup viewGroup, float f) {
        View linearLayout = new LinearLayout(this.f3956a);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(0, -1, f));
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    private LinearLayout m6476b(ViewGroup viewGroup, float f) {
        View linearLayout = new LinearLayout(this.f3956a);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new LayoutParams(-1, 0, f));
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    private LinearLayout m6477c(ViewGroup viewGroup, float f) {
        View linearLayout = new LinearLayout(this.f3956a);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(0, -1, f));
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    private FrameLayout m6471a(ViewGroup viewGroup, int i) {
        this.f3958c = true;
        View frameLayout = new FrameLayout(this.f3956a);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setPadding(this.f3957b, this.f3957b, this.f3957b, this.f3957b);
        View imageView = new ImageView(this.f3956a);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        try {
            imageView.setImageBitmap(CustomImageLoader.m4009a().m4014a(new File(Storage.m6955i() + File.separator + (i / 100) + File.separator + i + ".png")));
            Background.m6968a(null, imageView);
            imageView.setColorFilter(null);
            imageView.setOnTouchListener(new C03731(this, imageView));
        } catch (StorageException e) {
        }
        imageView.setScaleType(ScaleType.CENTER_INSIDE);
        imageView.setOnClickListener(new C03752(this, i));
        frameLayout.addView(imageView);
        viewGroup.addView(frameLayout);
        return frameLayout;
    }
}
