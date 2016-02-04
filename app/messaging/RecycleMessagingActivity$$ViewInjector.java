package app.messaging;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.messaging.RecycleMessagingActivity$$ViewInjector$RecycleMessagingActivity$.ViewInjector;
import app.view.CustomRecycleView;
import app.view.MessagingEditText;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class RecycleMessagingActivity$$ViewInjector<T extends RecycleMessagingActivity> implements Injector<T> {

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3233a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3234b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3234b = recycleMessagingActivity$$ViewInjector;
            this.f3233a = recycleMessagingActivity;
        }

        public void m5935a(View view) {
            this.f3233a.m5832z();
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3235a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3236b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3236b = recycleMessagingActivity$$ViewInjector;
            this.f3235a = recycleMessagingActivity;
        }

        public void m5936a(View view) {
            this.f3235a.m5767A();
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3237a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3238b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3238b = recycleMessagingActivity$$ViewInjector;
            this.f3237a = recycleMessagingActivity;
        }

        public void m5937a(View view) {
            this.f3237a.m5768B();
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3239a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3240b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3240b = recycleMessagingActivity$$ViewInjector;
            this.f3239a = recycleMessagingActivity;
        }

        public void m5938a(View view) {
            this.f3239a.m5771E();
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3241a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3242b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3242b = recycleMessagingActivity$$ViewInjector;
            this.f3241a = recycleMessagingActivity;
        }

        public void m5939a(View view) {
            this.f3241a.m5769C();
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.6 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3243a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3244b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3244b = recycleMessagingActivity$$ViewInjector;
            this.f3243a = recycleMessagingActivity;
        }

        public void m5940a(View view) {
            this.f3243a.m5831y();
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity$.ViewInjector.7 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ RecycleMessagingActivity f3245a;
        final /* synthetic */ RecycleMessagingActivity$$ViewInjector f3246b;

        ViewInjector(RecycleMessagingActivity$$ViewInjector recycleMessagingActivity$$ViewInjector, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3246b = recycleMessagingActivity$$ViewInjector;
            this.f3245a = recycleMessagingActivity;
        }

        public void m5941a(View view) {
            this.f3245a.m5770D();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f3196o = (RelativeLayout) finder.m7731a((View) finder.m7732a(obj, 2131755225, "field 'messagingRootLayout'"), 2131755225, "field 'messagingRootLayout'");
        t.f3197p = (CustomRecycleView) finder.m7731a((View) finder.m7732a(obj, 2131755228, "field 'recyclerView'"), 2131755228, "field 'recyclerView'");
        t.f3198q = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755226, "field 'parentRecycleView'"), 2131755226, "field 'parentRecycleView'");
        t.f3199r = (View) finder.m7732a(obj, 2131755229, "field 'replyView'");
        t.f3200s = (FrameLayout) finder.m7731a((View) finder.m7732a(obj, 2131755236, "field 'fragmentContainer'"), 2131755236, "field 'fragmentContainer'");
        t.f3201t = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755235, "field 'optionLayout'"), 2131755235, "field 'optionLayout'");
        View view = (View) finder.m7732a(obj, 2131755231, "field 'newMessageLayout' and method 'onNewMessageClicked'");
        t.f3202u = (LinearLayout) finder.m7731a(view, 2131755231, "field 'newMessageLayout'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3203v = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755232, "field 'messagingUnavailableLayout'"), 2131755232, "field 'messagingUnavailableLayout'");
        t.f3204w = (MessagingEditText) finder.m7731a((View) finder.m7732a(obj, 2131755240, "field 'messageEditText'"), 2131755240, "field 'messageEditText'");
        view = (View) finder.m7732a(obj, 2131755238, "field 'extraMenuButton' and method 'onExtraMenuClicked'");
        t.f3205x = (Button) finder.m7731a(view, 2131755238, "field 'extraMenuButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755239, "field 'StickerButton' and method 'onStickerButtonClicked'");
        t.f3206y = (Button) finder.m7731a(view, 2131755239, "field 'StickerButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755241, "field 'sendButton' and method 'onSendClicked'");
        t.f3207z = (Button) finder.m7731a(view, 2131755241, "field 'sendButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755242, "field 'microphoneButton' and method 'onMicrophoneButtonClicked'");
        t.f3170A = (Button) finder.m7731a(view, 2131755242, "field 'microphoneButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3171B = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755230, "field 'progressBar'"), 2131755230, "field 'progressBar'");
        t.f3172C = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755234, "field 'messagingUnavailableTextView'"), 2131755234, "field 'messagingUnavailableTextView'");
        t.f3173D = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755454, "field 'replyImageView'"), 2131755454, "field 'replyImageView'");
        t.f3174E = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755455, "field 'replyUsernameTextView'"), 2131755455, "field 'replyUsernameTextView'");
        t.f3175F = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755456, "field 'replyMessageTextView'"), 2131755456, "field 'replyMessageTextView'");
        view = (View) finder.m7732a(obj, 2131755233, "field 'ivBackToRelateMessage' and method 'onBackToRelateMessageClicked'");
        t.f3176G = (ImageView) finder.m7731a(view, 2131755233, "field 'ivBackToRelateMessage'");
        view.setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755457, "method 'onReplyCancleClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f3196o = null;
        t.f3197p = null;
        t.f3198q = null;
        t.f3199r = null;
        t.f3200s = null;
        t.f3201t = null;
        t.f3202u = null;
        t.f3203v = null;
        t.f3204w = null;
        t.f3205x = null;
        t.f3206y = null;
        t.f3207z = null;
        t.f3170A = null;
        t.f3171B = null;
        t.f3172C = null;
        t.f3173D = null;
        t.f3174E = null;
        t.f3175F = null;
        t.f3176G = null;
    }
}
