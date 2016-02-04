package app.messaging;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import app.Main;
import app.account.AccountManager;
import app.common.AudioPlayer;
import app.common.BaseActivity;
import app.common.Constants;
import app.common.CustomImageLoader;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.RelationType;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.common.entity.LocationEntity;
import app.common.entity.StickerEntity;
import app.common.runnabe.RunnableStorageAlert;
import app.common.runnabe.RunnableToast;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.ConversationNormalDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.BusUnregisterEvent;
import app.events.NewMessageEvent;
import app.events.ReplyCancelerEvent;
import app.events.sticker.StickerSelectedEvent;
import app.events.view_holder.MessageReplyClickEvent;
import app.events.view_holder.MessageReplyEvent;
import app.events.view_holder.ViewHolderDeleteEvent;
import app.events.xmpp.MessageSendStatusEvent;
import app.galley.SelectResourceFromSDCardActivity;
import app.galley.external.GalleryItemModel;
import app.localization.Language;
import app.localization.Localize;
import app.messaging.RecycleMessagingAdapter.RecycleAdapterListener;
import app.messaging.emoji.EmojiHandlerCode;
import app.messaging.emoji.SmilyMaper;
import app.messaging.fragments.DummyFragment;
import app.messaging.fragments.ExtraKeyboardFragment;
import app.messaging.fragments.MicrophoneFragment;
import app.messaging.fragments.RecycleFragmentsListener;
import app.messaging.fragments.StickerMenuFragment;
import app.messaging.selector.SelectRecipientActivity;
import app.messaging.selector.SelectRecipientActivity.ForwardHistoryType;
import app.messaging.stickers.StickerManager;
import app.messaging.vh.DisplayDataHandler;
import app.notification.NotificationCenter;
import app.setting.MessageBackgroundGridAdapter;
import app.storage.Storage;
import app.storage.StorageAccessUtil;
import app.storage.StorageException;
import app.util.AudioUtil;
import app.util.Background;
import app.util.BitmapUtil;
import app.util.DeviceUtils;
import app.util.Dimension;
import app.util.FileUtil;
import app.util.MediaPickerUtil;
import app.util.PermissionUtil;
import app.util.PermissionUtil.PermissionType;
import app.util.PixelConverter;
import app.util.SharedPreferencesUtil;
import app.util.StringUtil;
import app.util.TimeUtils;
import app.util.Utils;
import app.util.VideoUtil;
import app.util.XMPPUtils;
import app.view.CustomRecycleView;
import app.view.MessagingEditText;
import app.view.MessagingEditText.handleDismissingKeyboard;
import app.xmpp.ChatStateManager;
import app.xmpp.GroupManager;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageHandler;
import app.xmpp.NormalMessageManager;
import app.xmpp.packet.channel.CastPX;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.chatstates.ChatState;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class RecycleMessagingActivity extends BaseActivity implements RecycleFragmentsListener, handleDismissingKeyboard {
    private static boolean aD;
    Button f3170A;
    ProgressBar f3171B;
    TextView f3172C;
    ImageView f3173D;
    TextView f3174E;
    TextView f3175F;
    ImageView f3176G;
    protected FragmentManager f3177H;
    protected FragmentTransaction f3178I;
    protected LinearLayoutManager f3179J;
    protected final ArrayList<HistoryEntity> f3180K;
    public ArrayList<String> f3181L;
    protected ArrayList<HistoryEntity> f3182M;
    protected Set<String> f3183N;
    protected LoadEarlier f3184O;
    protected RecycleMessagingAdapter f3185P;
    protected Handler f3186Q;
    protected MediaPlayer f3187R;
    protected MessageLoader f3188S;
    protected ActionMode f3189T;
    protected Callback f3190U;
    protected HistoryEntity f3191V;
    protected InputMethodManager f3192W;
    protected ActivityState f3193X;
    protected MenuState f3194Y;
    protected File f3195Z;
    private int aA;
    private int aB;
    private int aC;
    private MenuItem aE;
    private final Object aF;
    private final Object aG;
    protected File aa;
    protected String ab;
    protected String ac;
    protected long ad;
    protected long ae;
    protected final int af;
    protected final int ag;
    protected final int ah;
    protected int ai;
    protected int aj;
    protected int ak;
    protected boolean al;
    protected boolean am;
    protected boolean an;
    protected boolean ao;
    protected boolean ap;
    protected boolean aq;
    protected long ar;
    protected long as;
    protected boolean at;
    protected long au;
    protected boolean av;
    private final String aw;
    private final String ax;
    private int ay;
    private int az;
    RelativeLayout f3196o;
    CustomRecycleView f3197p;
    LinearLayout f3198q;
    View f3199r;
    FrameLayout f3200s;
    LinearLayout f3201t;
    LinearLayout f3202u;
    LinearLayout f3203v;
    MessagingEditText f3204w;
    Button f3205x;
    Button f3206y;
    Button f3207z;

    /* renamed from: app.messaging.RecycleMessagingActivity.1 */
    class C02581 implements Callback {
        final /* synthetic */ RecycleMessagingActivity f3247a;

        C02581(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3247a = recycleMessagingActivity;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            RecycleMessagingActivity.aD = false;
            actionMode.getMenuInflater().inflate(2131820571, menu);
            this.f3247a.aE = menu.findItem(2131755658);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case 2131755656:
                    Main.f1926a.m5681c("action mode delete call");
                    this.f3247a.m5817k();
                    this.f3247a.f3185P.m5976f();
                    return true;
                case 2131755657:
                    Main.f1926a.m5681c("action mode select all call");
                    this.f3247a.f3185P.m5975e();
                    return true;
                case 2131755658:
                    Main.f1926a.m5681c("action mode multiple forward call");
                    if (this.f3247a.f3185P.m5974d() > 10 || this.f3247a.f3185P.m5974d() == 0) {
                        return true;
                    }
                    this.f3247a.m5716V();
                    return true;
                default:
                    return false;
            }
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.f3247a.f3189T = null;
            RecycleMessagingActivity.aD = true;
            this.f3247a.f3185P.m5976f();
            this.f3247a.f3193X = ActivityState.NONE;
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.2 */
    class C02592 extends OnScrollListener {
        int f3248a;
        int f3249b;
        final /* synthetic */ RecycleMessagingActivity f3250c;

        C02592(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3250c = recycleMessagingActivity;
        }

        public void m5942a(RecyclerView recyclerView, int i) {
            super.m3381a(recyclerView, i);
            if (this.f3250c.f3179J.m3238h() < this.f3250c.m5781P().size()) {
                if (this.f3250c.as > 0 && this.f3250c.as <= Long.parseLong(((HistoryEntity) this.f3250c.m5781P().get(this.f3250c.f3179J.m3240j())).m4419f())) {
                    this.f3250c.f3176G.setVisibility(8);
                }
                if (this.f3250c.f3185P.m5983m() != -1) {
                    this.f3250c.f3185P.m3346c(this.f3250c.f3185P.m5983m());
                    this.f3250c.f3185P.m5979g(-1);
                }
                this.f3248a = this.f3250c.f3179J.m3238h();
                this.f3249b = this.f3250c.f3179J.m3241k();
                if (this.f3250c.f3185P != null && this.f3250c.f3184O != null) {
                    if (this.f3250c.f3202u.getVisibility() == 0 && this.f3249b >= this.f3250c.m5781P().size() - 2) {
                        this.f3250c.f3202u.setVisibility(8);
                    }
                    if (this.f3250c.f3185P.m5983m() != -1) {
                        int m = this.f3250c.f3185P.m5983m();
                        this.f3250c.f3185P.m5979g(-1);
                        this.f3250c.f3185P.m3346c(m);
                    }
                    if (this.f3250c.f3184O.m5894e() && !this.f3250c.ap && this.f3248a > this.f3250c.m5781P().size() - 10 && !this.f3250c.al && !this.f3250c.f3185P.m5981i()) {
                        this.f3250c.m5800a(LoadDirection.upToDown, true, this.f3250c.getIntent());
                    } else if (this.f3250c.f3184O.m5893d() && this.f3248a < 20 && !this.f3250c.al && !this.f3250c.f3185P.m5981i()) {
                        this.f3250c.m5800a(LoadDirection.downToUp, true, this.f3250c.getIntent());
                    }
                }
            }
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.3 */
    class C02603 implements RecycleAdapterListener {
        final /* synthetic */ RecycleMessagingActivity f3251a;

        C02603(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3251a = recycleMessagingActivity;
        }

        public void m5947a(boolean z) {
            if (z) {
                Main.f1926a.m5681c("selection start");
                this.f3251a.f3189T = this.f3251a.startActionMode(this.f3251a.f3190U);
                this.f3251a.m5780N();
                this.f3251a.ac();
                this.f3251a.f3193X = ActivityState.ACTION_MODE;
                this.f3251a.f3176G.setVisibility(8);
                this.f3251a.ar = -1;
                this.f3251a.as = -1;
                return;
            }
            this.f3251a.m5715U();
        }

        public void m5946a(int i) {
            if (this.f3251a.f3189T != null) {
                this.f3251a.f3189T.setTitle(i + "");
            }
        }

        public void m5948b(int i) {
            if (this.f3251a.aE != null) {
                if (i > 10 || i == 0) {
                    this.f3251a.aE.setIcon(2130837722);
                } else {
                    this.f3251a.aE.setIcon(2130837723);
                }
            }
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.4 */
    class C02614 implements TextWatcher {
        final /* synthetic */ RecycleMessagingActivity f3252a;

        C02614(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3252a = recycleMessagingActivity;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f3252a.m5801a(charSequence, i, i2, i3);
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.5 */
    class C02625 implements OnGlobalLayoutListener {
        final /* synthetic */ RecycleMessagingActivity f3253a;
        private int f3254b;
        private int f3255c;

        C02625(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3253a = recycleMessagingActivity;
            this.f3255c = -1;
        }

        public void onGlobalLayout() {
            int height = this.f3253a.f3196o.getHeight();
            if (this.f3253a.getResources().getConfiguration().orientation == 1 && this.f3253a.ay == 0) {
                this.f3253a.ay = height;
            }
            if (this.f3253a.getResources().getConfiguration().orientation == 2 && this.f3253a.az == 0) {
                this.f3253a.aB = (this.f3253a.f3196o.getMeasuredHeight() * 2) / 3;
                this.f3253a.m5750f(this.f3253a.aB);
            }
            if (this.f3254b != 0) {
                if (this.f3255c != this.f3253a.getResources().getConfiguration().orientation) {
                    this.f3255c = this.f3253a.getResources().getConfiguration().orientation;
                    this.f3253a.ac();
                    this.f3253a.m5780N();
                    this.f3253a.ag();
                    this.f3253a.f3193X = ActivityState.NONE;
                    return;
                } else if (this.f3254b > height) {
                    int d = this.f3253a.m5753g(this.f3254b - height);
                    if (this.f3253a.getResources().getConfiguration().orientation == 1) {
                        this.f3253a.aA = this.f3253a.aA == 0 ? d : this.f3253a.aA;
                    } else {
                        this.f3253a.aB = this.f3253a.aB == 0 ? d : this.f3253a.aB;
                    }
                    if (this.f3253a.f3193X.equals(ActivityState.KEYBOARD)) {
                        if (this.f3253a.getResources().getConfiguration().orientation == 1) {
                            this.f3253a.m5748e(d);
                        } else {
                            this.f3253a.m5750f(d);
                        }
                        this.f3253a.ae();
                    }
                } else if (this.f3254b >= height) {
                    this.f3253a.ag();
                } else if (this.f3253a.f3193X.equals(ActivityState.OPTION_MENU)) {
                    this.f3253a.ab();
                } else {
                    this.f3253a.m5740c(true);
                    this.f3253a.f3193X = ActivityState.NONE;
                }
            }
            this.f3254b = height;
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.6 */
    class C02636 implements OnTouchListener {
        final /* synthetic */ RecycleMessagingActivity f3256a;

        C02636(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3256a = recycleMessagingActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f3256a.f3193X != ActivityState.KEYBOARD) {
                this.f3256a.al();
                this.f3256a.ad();
            }
            return false;
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.7 */
    class C02647 implements OnEditorActionListener {
        final /* synthetic */ RecycleMessagingActivity f3257a;

        C02647(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3257a = recycleMessagingActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            this.f3257a.m5829w();
            return true;
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.8 */
    class C02658 implements Runnable {
        final /* synthetic */ RecycleMessagingActivity f3258a;

        C02658(RecycleMessagingActivity recycleMessagingActivity) {
            this.f3258a = recycleMessagingActivity;
        }

        public void run() {
            this.f3258a.f3191V = null;
            this.f3258a.aq = false;
            this.f3258a.f3174E.setText("");
            this.f3258a.f3175F.setText("");
            this.f3258a.f3199r.setVisibility(8);
            this.f3258a.f3173D.setImageDrawable(null);
            this.f3258a.m5737c(-1);
        }
    }

    /* renamed from: app.messaging.RecycleMessagingActivity.9 */
    /* synthetic */ class C02669 {
        static final /* synthetic */ int[] f3259a;
        static final /* synthetic */ int[] f3260b;
        static final /* synthetic */ int[] f3261c;

        static {
            f3261c = new int[Type.values().length];
            try {
                f3261c[Type.PHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3261c[Type.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3261c[Type.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3261c[Type.STICKER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3261c[Type.TEXT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3261c[Type.AUDIO.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            f3260b = new int[MenuState.values().length];
            try {
                f3260b[MenuState.EXTRA_KEYBOARD.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3260b[MenuState.STICKER.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3260b[MenuState.MICROPHONE.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            f3259a = new int[ForwardHistoryType.values().length];
            try {
                f3259a[ForwardHistoryType.broadcast.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f3259a[ForwardHistoryType.chat.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f3259a[ForwardHistoryType.group.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f3259a[ForwardHistoryType.contact.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    public enum ActivityState {
        NONE,
        KEYBOARD,
        OPTION_MENU,
        ACTION_MODE
    }

    public enum LoadDirection {
        upToDown,
        downToUp
    }

    public enum MenuState {
        EXTRA_KEYBOARD,
        STICKER,
        MICROPHONE,
        NONE
    }

    class MessageLoader extends AsyncTask<Void, Void, ArrayList<HistoryEntity>> {
        private final WeakReference<RecycleMessagingActivity> f3275a;
        private boolean f3276b;
        private LoadDirection f3277c;
        private Intent f3278d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5949a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m5950a((ArrayList) obj);
        }

        public MessageLoader(boolean z, LoadDirection loadDirection, RecycleMessagingActivity recycleMessagingActivity, Intent intent) {
            this.f3276b = false;
            this.f3275a = new WeakReference(recycleMessagingActivity);
            this.f3276b = z;
            this.f3277c = loadDirection;
            this.f3278d = intent;
            recycleMessagingActivity.al = true;
            if (!z) {
                recycleMessagingActivity.f3197p.setEmptyViewVisibility(0);
            }
        }

        protected ArrayList<HistoryEntity> m5949a(Void... voidArr) {
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3275a.get();
            if (recycleMessagingActivity == null) {
                return new ArrayList();
            }
            return recycleMessagingActivity.m5793a(this.f3277c, recycleMessagingActivity.ad, recycleMessagingActivity.ae);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void m5950a(ArrayList<HistoryEntity> arrayList) {
            int i = 0;
            super.onPostExecute(arrayList);
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3275a.get();
            if (recycleMessagingActivity != null) {
                recycleMessagingActivity.f3197p.setEmptyViewVisibility(8);
                recycleMessagingActivity.an = true;
                if (arrayList == null || arrayList.isEmpty()) {
                    if (!this.f3276b) {
                        recycleMessagingActivity.m5798a(this.f3278d, "");
                    }
                    recycleMessagingActivity.al = false;
                    return;
                }
                int i2 = 0;
                while (i2 < arrayList.size()) {
                    if (((HistoryEntity) arrayList.get(i2)).m4414c() == null) {
                        i2++;
                    } else {
                        if (!recycleMessagingActivity.m5807a((HistoryEntity) arrayList.get(i2))) {
                            return;
                        }
                        if (recycleMessagingActivity.m5781P().isEmpty()) {
                            recycleMessagingActivity.m5781P().addAll(arrayList);
                            if (!recycleMessagingActivity.at) {
                                recycleMessagingActivity.f3197p.m3599a(arrayList.size() - 1);
                            }
                        } else if (!this.f3276b && this.f3277c == LoadDirection.downToUp) {
                            recycleMessagingActivity.am = true;
                            recycleMessagingActivity.m5714T();
                            recycleMessagingActivity.m5781P().addAll(0, arrayList);
                        } else if (this.f3276b) {
                            recycleMessagingActivity.am = true;
                            recycleMessagingActivity.m5781P().addAll(arrayList);
                        }
                        if (this.f3277c != LoadDirection.downToUp) {
                            recycleMessagingActivity.m5794a(0, arrayList.size());
                        } else {
                            recycleMessagingActivity.m5794a(recycleMessagingActivity.m5781P().size() - arrayList.size(), recycleMessagingActivity.m5781P().size());
                        }
                        recycleMessagingActivity.al = false;
                        if (!recycleMessagingActivity.f3184O.m5894e()) {
                            arrayList.addAll(recycleMessagingActivity.f3182M);
                        }
                        recycleMessagingActivity.f3182M.clear();
                        if (recycleMessagingActivity.at) {
                            recycleMessagingActivity.at = false;
                            while (i < recycleMessagingActivity.m5781P().size()) {
                                if (((HistoryEntity) recycleMessagingActivity.m5781P().get(i)).m4407a() != recycleMessagingActivity.au) {
                                    break;
                                }
                                i++;
                            }
                        }
                        i = -1;
                        if (i > -1) {
                            recycleMessagingActivity.f3197p.m3599a(i);
                            recycleMessagingActivity.f3185P.m5979g(i);
                        }
                        recycleMessagingActivity.m5798a(this.f3278d, "");
                    }
                }
                if (recycleMessagingActivity.m5781P().isEmpty()) {
                    recycleMessagingActivity.m5781P().addAll(arrayList);
                    if (recycleMessagingActivity.at) {
                        recycleMessagingActivity.f3197p.m3599a(arrayList.size() - 1);
                    }
                } else {
                    if (!this.f3276b) {
                    }
                    if (this.f3276b) {
                        recycleMessagingActivity.am = true;
                        recycleMessagingActivity.m5781P().addAll(arrayList);
                    }
                }
                if (this.f3277c != LoadDirection.downToUp) {
                    recycleMessagingActivity.m5794a(recycleMessagingActivity.m5781P().size() - arrayList.size(), recycleMessagingActivity.m5781P().size());
                } else {
                    recycleMessagingActivity.m5794a(0, arrayList.size());
                }
                recycleMessagingActivity.al = false;
                if (recycleMessagingActivity.f3184O.m5894e()) {
                    arrayList.addAll(recycleMessagingActivity.f3182M);
                }
                recycleMessagingActivity.f3182M.clear();
                if (recycleMessagingActivity.at) {
                    recycleMessagingActivity.at = false;
                    while (i < recycleMessagingActivity.m5781P().size()) {
                        if (((HistoryEntity) recycleMessagingActivity.m5781P().get(i)).m4407a() != recycleMessagingActivity.au) {
                            i++;
                        } else {
                            break;
                            if (i > -1) {
                                recycleMessagingActivity.f3197p.m3599a(i);
                                recycleMessagingActivity.f3185P.m5979g(i);
                            }
                            recycleMessagingActivity.m5798a(this.f3278d, "");
                        }
                    }
                }
                i = -1;
                if (i > -1) {
                    recycleMessagingActivity.f3197p.m3599a(i);
                    recycleMessagingActivity.f3185P.m5979g(i);
                }
                recycleMessagingActivity.m5798a(this.f3278d, "");
            }
        }
    }

    class RunnableEnable implements Runnable {
        private final WeakReference<RecycleMessagingActivity> f3279a;
        private int f3280b;

        public RunnableEnable(RecycleMessagingActivity recycleMessagingActivity, int i) {
            this.f3279a = new WeakReference(recycleMessagingActivity);
            this.f3280b = i;
        }

        public void run() {
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3279a.get();
            if (recycleMessagingActivity != null) {
                switch (this.f3280b) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        recycleMessagingActivity.f3205x.setEnabled(true);
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        recycleMessagingActivity.f3206y.setEnabled(true);
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        recycleMessagingActivity.f3204w.setClickable(true);
                    default:
                }
            }
        }
    }

    class RunnableMediaSend implements Runnable {
        private final WeakReference<RecycleMessagingActivity> f3281a;
        private int f3282b;
        private String f3283c;
        private String f3284d;

        public RunnableMediaSend(RecycleMessagingActivity recycleMessagingActivity, int i, String str, String str2) {
            this.f3281a = new WeakReference(recycleMessagingActivity);
            this.f3282b = i;
            this.f3283c = str;
            this.f3284d = str2;
        }

        public void run() {
            if (this.f3281a.get() != null) {
                switch (this.f3282b) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        m5951a();
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        m5952b();
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        m5953c();
                    default:
                }
            }
        }

        private void m5951a() {
            Context context = (RecycleMessagingActivity) this.f3281a.get();
            try {
                Bitmap a = BitmapUtil.m6971a(this.f3283c, 1080);
                if (a == null) {
                    context.runOnUiThread(new RunnableToast(context.getString(2131296837)));
                    return;
                }
                if (this.f3283c.startsWith(Storage.m6952g())) {
                    new File(this.f3283c).delete();
                }
                if (Storage.m6939a(context, new File(Storage.m6952g()))) {
                    File file = new File(Storage.m6952g() + File.separator + StringUtil.m7063a(4));
                    Main.f1926a.m5683d("number byte is " + a.getByteCount());
                    OutputStream fileOutputStream = new FileOutputStream(file, false);
                    BitmapUtil.m6975a(a, fileOutputStream);
                    a.recycle();
                    fileOutputStream.close();
                    String b = FileUtil.m7024b(file);
                    File file2 = new File(Storage.m6941b() + File.separator + b);
                    if (file2.exists()) {
                        file.delete();
                    } else {
                        file.renameTo(file2);
                    }
                    HistoryEntity a2 = context.m5789a(b, BitmapUtil.m6977b(file2), BitmapUtil.m6973a(file2), this.f3284d);
                    context.m5770D();
                    NormalMessageHandler.m7415a().m7434a(a2, context.m5818l(), b, false, context.f3185P.m5980h());
                    return;
                }
                context.runOnUiThread(new RunnableStorageAlert(context));
            } catch (IOException e) {
                Main.f1926a.m5679b("cannot cache media");
            } catch (StorageException e2) {
                Main.f1926a.m5679b("cannot cache media");
            }
        }

        private void m5952b() {
            int i;
            int i2 = 0;
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3281a.get();
            try {
                String b = FileUtil.m7025b(this.f3283c);
                String str = Storage.m6944c() + File.separator + b;
                File file = new File(str);
                if (!file.exists()) {
                    FileUtil.m7023a(this.f3283c, str, false);
                }
                if (file.exists()) {
                    Dimension dimension;
                    Bitmap bitmap;
                    int i3;
                    Dimension dimension2 = new Dimension(0, 0);
                    Bitmap bitmap2 = null;
                    String str2 = "";
                    try {
                        i2 = VideoUtil.m7083a(this.f3283c);
                        dimension2 = VideoUtil.m7085a(Main.f1927b, this.f3283c);
                        bitmap2 = VideoUtil.m7084a(new File(this.f3283c));
                        i = i2;
                        dimension = dimension2;
                        bitmap = bitmap2;
                        str = BitmapUtil.m6972a(bitmap2);
                        i3 = i;
                    } catch (Exception e) {
                        i = i2;
                        dimension = dimension2;
                        bitmap = bitmap2;
                        str = str2;
                        i3 = i;
                    }
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    int length = (int) file.length();
                    if (str == null || str.length() <= 0) {
                        recycleMessagingActivity.runOnUiThread(new RunnableToast(recycleMessagingActivity.getString(2131296820)));
                        return;
                    }
                    HistoryEntity a = recycleMessagingActivity.m5788a(b, dimension, str, length, i3, this.f3284d);
                    NormalMessageHandler.m7415a().m7434a(a, recycleMessagingActivity.m5818l(), b, false, recycleMessagingActivity.f3185P.m5980h());
                    recycleMessagingActivity.m5770D();
                    return;
                }
                recycleMessagingActivity.runOnUiThread(new RunnableStorageAlert(recycleMessagingActivity));
            } catch (IOException e2) {
                Main.f1926a.m5679b("cannot cache media");
            } catch (StorageException e3) {
                Main.f1926a.m5679b("cannot cache media");
            }
        }

        private void m5953c() {
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3281a.get();
            try {
                String b = FileUtil.m7025b(this.f3283c);
                String str = Storage.m6948e() + File.separator + b;
                File file = new File(str);
                File file2 = new File(this.f3283c);
                if (file.exists()) {
                    file2.delete();
                } else {
                    file2.renameTo(file);
                }
                HistoryEntity a = recycleMessagingActivity.m5785a(b, (int) file.length(), AudioUtil.m6967a(Main.f1927b, str));
                recycleMessagingActivity.m5770D();
                NormalMessageHandler.m7415a().m7434a(a, recycleMessagingActivity.m5818l(), b, false, recycleMessagingActivity.f3185P.m5980h());
            } catch (IOException e) {
                Main.m3905a(recycleMessagingActivity.getString(2131296819));
            } catch (StorageException e2) {
                Main.m3905a(recycleMessagingActivity.getString(2131296818));
            }
        }
    }

    class RunnableNotifyRecycleAdapter implements Runnable {
        private final WeakReference<RecycleMessagingActivity> f3285a;
        private int f3286b;

        public RunnableNotifyRecycleAdapter(RecycleMessagingActivity recycleMessagingActivity, int i) {
            this.f3285a = new WeakReference(recycleMessagingActivity);
            this.f3286b = i;
        }

        public void run() {
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3285a.get();
            if (recycleMessagingActivity != null) {
                recycleMessagingActivity.f3185P.m3346c(this.f3286b);
            }
        }
    }

    class SendMultipleForwardMessageInBackground extends AsyncTask<Void, Void, Void> {
        List<String> f3287a;
        int f3288b;
        ArrayList<String> f3289c;
        private final WeakReference<RecycleMessagingActivity> f3290d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m5954a((Void[]) objArr);
        }

        public SendMultipleForwardMessageInBackground(List<String> list, ArrayList<String> arrayList, int i, RecycleMessagingActivity recycleMessagingActivity) {
            this.f3287a = list;
            this.f3288b = i;
            this.f3289c = arrayList;
            this.f3290d = new WeakReference(recycleMessagingActivity);
        }

        protected Void m5954a(Void... voidArr) {
            RecycleMessagingActivity recycleMessagingActivity = (RecycleMessagingActivity) this.f3290d.get();
            if (recycleMessagingActivity != null) {
                List a;
                switch (recycleMessagingActivity.f3185P.m5980h()) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        a = HistoryNormalMessageDataSource.m4720a().m4735a(this.f3287a);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        a = HistoryGroupDataSource.m4691a().m4708a(this.f3287a);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        a = HistoryChannelDataSource.m4667a().m4682a(this.f3287a);
                        break;
                    default:
                        a = null;
                        break;
                }
                if (r1 != null) {
                    for (HistoryEntity historyEntity : r1) {
                        switch (C02669.f3261c[historyEntity.m4418e().ordinal()]) {
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                                NormalMessageHandler.m7415a().m7433a(recycleMessagingActivity.m5787a(historyEntity.m4430q(), historyEntity.m4425l(), historyEntity.m4427n(), recycleMessagingActivity.m5808b(historyEntity), historyEntity.m4426m()), this.f3288b, this.f3289c);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                                LocationEntity locationEntity = new LocationEntity();
                                locationEntity.m4466b(historyEntity.m4433t().doubleValue());
                                locationEntity.m4467b(historyEntity.m4432s());
                                locationEntity.m4464a(historyEntity.m4431r());
                                locationEntity.m4463a(historyEntity.m4434u().doubleValue());
                                locationEntity.m4469c(historyEntity.m4423j());
                                recycleMessagingActivity.m5725a(locationEntity, this.f3288b, this.f3289c);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                                NormalMessageHandler.m7415a().m7433a(recycleMessagingActivity.m5786a(historyEntity.m4430q(), historyEntity.m4425l(), historyEntity.m4427n(), recycleMessagingActivity.m5808b(historyEntity), historyEntity.m4429p(), historyEntity.m4428o(), historyEntity.m4426m()), this.f3288b, this.f3289c);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                                recycleMessagingActivity.m5803a(historyEntity.m4416d(), this.f3289c, this.f3288b);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                                NormalMessageHandler.m7415a().m7433a(recycleMessagingActivity.m5785a(historyEntity.m4430q(), historyEntity.m4429p(), historyEntity.m4428o()), this.f3288b, this.f3289c);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            return null;
        }
    }

    protected abstract HistoryNormalMessageEntity m5784a(Builder builder);

    protected abstract ArrayList<HistoryEntity> m5791a(long j, long j2);

    protected abstract ArrayList<HistoryEntity> m5792a(long j, String str);

    protected abstract ArrayList<HistoryEntity> m5793a(LoadDirection loadDirection, long j, long j2);

    protected abstract void m5797a(Intent intent);

    protected abstract void m5806a(String[] strArr);

    protected abstract boolean m5807a(HistoryEntity historyEntity);

    protected abstract ArrayList<String> m5818l();

    protected abstract void m5819m();

    public RecycleMessagingActivity() {
        this.aw = "portraitKeyboardHeight";
        this.ax = "landScapeKeyboardHeight";
        this.f3180K = new ArrayList();
        this.f3182M = new ArrayList();
        this.f3189T = null;
        this.f3191V = null;
        this.f3193X = ActivityState.NONE;
        this.f3194Y = MenuState.NONE;
        this.f3195Z = null;
        this.aa = null;
        this.ad = 0;
        this.ae = 0;
        this.ay = 0;
        this.az = 0;
        this.aA = 0;
        this.aB = 0;
        this.af = 1500;
        this.ag = 10;
        this.ah = 40;
        this.ai = 1000;
        this.aj = 0;
        this.ak = -1;
        this.al = false;
        this.am = false;
        this.an = false;
        this.ao = false;
        this.ap = false;
        this.at = false;
        this.au = 0;
        this.aF = new Object();
        this.aG = new Object();
    }

    static {
        aD = true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903089);
        ButterKnife.m7741a((Activity) this);
        this.f3182M = new ArrayList();
        this.f3183N = new HashSet();
        this.f3186Q = new Handler();
        this.f3177H = getFragmentManager();
        this.f3192W = (InputMethodManager) getSystemService("input_method");
        this.f3204w.setImeOptions(4);
        this.f3204w.setHandleEditTextKeyboard(this);
        this.aC = DeviceUtils.m7011a((Activity) this).heightPixels / 4;
        setVolumeControlStream(5);
        m5712R();
        m5822p();
        m5797a(getIntent());
        m5819m();
        m5719Y();
    }

    protected void onResume() {
        super.onResume();
        Background.m6968a(m5828v(), this.f3196o);
        m5826t();
        m5780N();
        ac();
        m5715U();
        this.av = true;
    }

    protected void onPause() {
        super.onPause();
        NotificationCenter.m6606a().m6628i(null);
        AudioPlayer.m3946a().m3953b();
        if (this.f3187R != null) {
            this.f3187R.release();
        }
        ExtraKeyboardFragment.m6147M();
        StickerMenuFragment.m6187M();
        MicrophoneFragment.m6159M();
        m5780N();
        aD = true;
        this.av = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        m5781P().clear();
        EventBus.m12779a().m12795d(new BusUnregisterEvent());
        EventBus.m12779a().m12794c(this);
        this.f3185P.m5984n();
        AudioPlayer.m3946a().m3955c();
        Storage.m6957k();
    }

    @SuppressLint({"NewApi"})
    protected void onActivityResult(int i, int i2, Intent intent) {
        Throwable e;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Iterator it;
            GalleryItemModel galleryItemModel;
            String stringExtra;
            switch (i) {
                case 30001:
                    it = intent.getParcelableArrayListExtra(DataPacketExtension.ELEMENT).iterator();
                    while (it.hasNext()) {
                        galleryItemModel = (GalleryItemModel) it.next();
                        m5802a(galleryItemModel.m5063d(), galleryItemModel.m5064e());
                    }
                    m5796a(-1, true);
                    break;
                case 30002:
                    it = intent.getParcelableArrayListExtra(DataPacketExtension.ELEMENT).iterator();
                    while (it.hasNext()) {
                        galleryItemModel = (GalleryItemModel) it.next();
                        m5733b(galleryItemModel.m5063d(), galleryItemModel.m5064e());
                    }
                    m5796a(-1, true);
                    break;
                case 30003:
                    if (this.f3195Z != null) {
                        Intent intent2 = new Intent(this, SelectResourceFromSDCardActivity.class);
                        intent2.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_CAPTURE");
                        intent2.putExtra(DataPacketExtension.ELEMENT, this.f3195Z.getAbsolutePath());
                        startActivityForResult(intent2, 30006);
                        break;
                    }
                    break;
                case 30004:
                    String[] strArr = new String[]{"_data"};
                    Cursor query = getContentResolver().query(intent.getData(), strArr, null, null, null);
                    if (query != null && query.moveToFirst()) {
                        m5733b(query.getString(query.getColumnIndex(strArr[0])), "");
                    }
                    if (query != null) {
                        query.close();
                    }
                    m5796a(-1, true);
                    break;
                case 30005:
                    LocationEntity locationEntity = new LocationEntity();
                    locationEntity.m4469c(intent.getStringExtra("extra_location_thumbnail"));
                    locationEntity.m4467b(intent.getStringExtra("extra_location_address"));
                    locationEntity.m4464a(intent.getStringExtra("extra_location_title"));
                    locationEntity.m4466b(intent.getDoubleExtra("extra_location_latitude", 0.0d));
                    locationEntity.m4463a(intent.getDoubleExtra("extra_location_longitude", 0.0d));
                    m5725a(locationEntity, this.f3185P.m5980h(), m5818l());
                    m5796a(-1, true);
                    break;
                case 30006:
                    stringExtra = intent.getStringExtra("image_capture_path");
                    String stringExtra2 = intent.getStringExtra("image_capture_caption");
                    this.aa = new File(stringExtra);
                    if (this.aa != null) {
                        try {
                            File file = new File(Utils.m7079a(FileUtil.m7025b(this.aa.getAbsolutePath()), Type.PHOTO));
                            if (this.aa.renameTo(file)) {
                                m5802a(file.getAbsolutePath(), stringExtra2);
                            } else {
                                m5802a(this.aa.getAbsolutePath(), stringExtra2);
                            }
                            m5796a(-1, true);
                        } catch (IOException e2) {
                            e = e2;
                            Main.m3905a(getString(2131296816));
                            Main.f1926a.m5682c(e);
                            m5796a(-1, true);
                            m5816j();
                        } catch (StorageException e3) {
                            e = e3;
                            Main.m3905a(getString(2131296816));
                            Main.f1926a.m5682c(e);
                            m5796a(-1, true);
                            m5816j();
                        }
                    }
                    m5796a(-1, true);
                case 30007:
                    if (this.f3181L != null) {
                        int i3;
                        Bundle bundleExtra = intent.getBundleExtra("multiple_forward_message");
                        ForwardHistoryType forwardHistoryType = ForwardHistoryType.values()[bundleExtra.getInt("multiple_forward_message_type")];
                        ArrayList arrayList = new ArrayList();
                        switch (C02669.f3259a[forwardHistoryType.ordinal()]) {
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                                i3 = 4;
                                arrayList = bundleExtra.getStringArrayList("forward_recipients_id");
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                                stringExtra = bundleExtra.getString("forward_recipients_id");
                                JabberId a = JabberId.m7386a(stringExtra);
                                if (a != null) {
                                    arrayList.add(stringExtra);
                                    m5732b(a.m7387a(), this.f3181L.size());
                                    i3 = 1;
                                    break;
                                }
                                return;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                                i3 = 2;
                                String string = bundleExtra.getString("forward_recipients_id");
                                arrayList.add(string);
                                m5729a(string, this.f3181L.size());
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                                arrayList = bundleExtra.getStringArrayList("forward_recipients_id");
                                if (arrayList != null) {
                                    Iterator it2 = arrayList.iterator();
                                    while (it2.hasNext()) {
                                        m5732b((String) it2.next(), this.f3181L.size());
                                    }
                                    i3 = 1;
                                    break;
                                }
                                return;
                            default:
                                i3 = -1;
                                break;
                        }
                        new SendMultipleForwardMessageInBackground(this.f3181L, arrayList, i3, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        Main.m3905a(getResources().getString(2131296565));
                        break;
                    }
                    return;
                default:
                    return;
            }
            m5816j();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0 && iArr[0] == 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m5772F();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m5773G();
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    m5774H();
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    m5769C();
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    m5775I();
                default:
            }
        }
    }

    private void m5729a(String str, int i) {
        if (GroupManager.m7323a().m7358b(str) != null && GroupManager.m7323a().m7358b(str).m4277c() != 0) {
            GroupManager.m7323a().m7358b(str).m4275b(GroupManager.m7323a().m7358b(str).m4306r() + i);
            ConversationGroupDataSource.m4587a().m4600a(str, i);
        }
    }

    private void m5732b(String str, int i) {
        if (NormalMessageManager.m7447a().m7470f(str) != null && NormalMessageManager.m7447a().m7470f(str).m4172f() != 0) {
            NormalMessageManager.m7447a().m7470f(str).m4170d().m4329b(NormalMessageManager.m7447a().m7470f(str).m4170d().m4339i() + this.f3181L.size());
            ConversationNormalDataSource.m4623a().m4631a(str, i);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.ay = 0;
        this.az = 0;
        m5821o();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        int identifier = Resources.getSystem().getIdentifier("action_bar_subtitle", "id", "android");
        TextView textView = identifier > 0 ? (TextView) findViewById(identifier) : null;
        if (!(textView == null || textView.getAnimation() == null)) {
            textView.getAnimation().cancel();
        }
        return false;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (getActionBar() != null) {
            getActionBar().setTitle(this.ab);
            if (this.ac != null) {
                getActionBar().setSubtitle((Localize.m5603d() == Language.PERSIAN ? "     " : "") + this.ac + (Localize.m5603d() == Language.ENGLISH ? "     " : ""));
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void m5712R() {
        this.f3179J = new LinearLayoutManager(this);
        this.f3197p.setLayoutManager(this.f3179J);
        this.f3197p.setEmptyView(this.f3171B);
        this.f3197p.setItemAnimator(null);
        this.f3199r.setVisibility(8);
        m5713S();
    }

    private void m5713S() {
        if (this.f3185P == null) {
            this.f3185P = new RecycleMessagingAdapter(this);
            this.f3197p.setAdapter(this.f3185P);
        }
    }

    private void m5714T() {
        if (m5781P() != null && m5781P().size() != 0 && ((HistoryEntity) m5781P().get(0)).m4418e() == Type.TIMESTAMP) {
            m5781P().remove(0);
        }
    }

    protected void m5794a(int i, int i2) {
        this.f3185P.m5969a(m5781P());
        this.f3185P.m3339b(i, i2);
        this.f3185P.m3331a(this.f3179J.m3238h() + i2, (this.f3179J.m3240j() - this.f3179J.m3238h()) + 4);
        m5826t();
        m5812c(i, i + i2);
        if (!this.am) {
            this.f3197p.m3599a(this.ak);
        }
    }

    protected void m5795a(int i, int i2, boolean z) {
        this.f3185P.m5969a(m5781P());
        this.f3185P.m3339b(i, i2);
        this.f3185P.m3331a(this.f3179J.m3238h() + i2, (this.f3179J.m3240j() - this.f3179J.m3238h()) + 4);
        m5826t();
        m5812c(i, i + i2);
    }

    protected void m5796a(int i, boolean z) {
        this.f3185P.m5969a(m5781P());
        this.f3185P.m3348d(i >= 0 ? i : m5781P().size() - 1);
        if (i < 0) {
            i = m5781P().size() - 1;
        }
        m5812c(i, m5781P().size() - 1);
        if (z) {
            this.f3197p.m3611b(m5781P().size());
        } else if (m5781P().size() <= 4 || this.f3179J.m3240j() >= m5781P().size() - 2) {
            this.f3197p.m3611b(m5781P().size());
        } else {
            this.f3202u.setVisibility(0);
        }
    }

    protected void m5809b(int i) {
        this.f3186Q.post(new RunnableNotifyRecycleAdapter(this, i));
    }

    protected void m5820n() {
        if (this.f3185P != null) {
            this.f3185P.m3345c();
        }
    }

    protected void m5821o() {
        if (this.av && !isFinishing() && !isChangingConfigurations()) {
            this.f3178I = getSupportFragmentManager().m353a();
            switch (C02669.f3260b[this.f3194Y.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f3178I.m116b(2131755236, ExtraKeyboardFragment.m6149a(), "extra menu");
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f3178I.m116b(2131755236, StickerMenuFragment.m6194a(), "sticker menu");
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f3178I.m116b(2131755236, MicrophoneFragment.m6163a(), "Microphone");
                    break;
                default:
                    this.f3178I.m116b(2131755236, new DummyFragment(), "dummy");
                    break;
            }
            this.f3178I.m109a();
        }
    }

    protected void m5822p() {
        this.f3190U = new C02581(this);
    }

    private void m5715U() {
        if (this.f3189T != null) {
            this.f3189T.finish();
        }
    }

    private void m5716V() {
        this.f3181L = new ArrayList();
        ArrayList arrayList = new ArrayList(this.f3185P.m5982l());
        for (int i = 0; i < arrayList.size(); i++) {
            String b = ((HistoryEntity) m5781P().get(((Integer) arrayList.get(i)).intValue())).m4412b();
            if (b != null) {
                this.f3181L.add(b);
            }
        }
        Intent intent = new Intent(this, SelectRecipientActivity.class);
        intent.putExtra("multiple_forward_message_type", this.f3185P.m5980h());
        intent.putExtra("extra_is_forward_mode", true);
        startActivityForResult(intent, 30007);
    }

    protected void m5817k() {
        int verticalScrollbarPosition = this.f3197p.getVerticalScrollbarPosition();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(this.f3185P.m5982l());
        Collections.sort(arrayList2);
        this.f3185P.m5978g();
        Main.f1926a.m5681c(arrayList2.toString());
        for (int size = arrayList2.size() - 1; size >= 0; size--) {
            if (((Integer) arrayList2.get(size)).intValue() < m5781P().size()) {
                this.f3183N.remove(((HistoryEntity) m5781P().get(((Integer) arrayList2.get(size)).intValue())).m4412b());
                arrayList.add(((HistoryEntity) m5781P().get(((Integer) arrayList2.get(size)).intValue())).m4412b());
                m5781P().remove(((Integer) arrayList2.get(size)).intValue());
            }
        }
        m5806a((String[]) arrayList.toArray(new String[arrayList.size()]));
        this.f3185P.m5970a(false);
        m5823q();
        m5825s();
        m5820n();
        this.f3197p.setVerticalScrollbarPosition(verticalScrollbarPosition);
        m5717W();
    }

    private void m5717W() {
        if (m5781P().isEmpty()) {
            this.ad = 0;
            this.ae = 0;
            m5800a(this.ap ? LoadDirection.downToUp : LoadDirection.upToDown, true, getIntent());
        }
    }

    protected void m5823q() {
        this.ak = m5718X();
        if (this.ak != -1) {
            m5781P().remove(this.ak);
            this.f3185P.m3350e(this.ak);
            this.ak = -1;
        }
    }

    private int m5718X() {
        for (int size = m5781P().size() - 1; size >= 0; size--) {
            if (((HistoryEntity) m5781P().get(size)).m4418e().equals(Type.NEW_MESSAGE_DECLARATION)) {
                return size;
            }
        }
        return -1;
    }

    protected void m5810b(int i, int i2) {
        if (m5781P().size() != 0) {
            Object string;
            int size = m5781P().size() - 1;
            if (Long.parseLong(((HistoryEntity) m5781P().get(size)).m4419f()) == 1000) {
                string = Main.f1927b.getString(2131296792);
            } else {
                string = TimeUtils.m7070a(Long.parseLong(((HistoryEntity) m5781P().get(size)).m4419f()), true, true);
            }
            if (i < 0) {
                i = 0;
            }
            int i3 = -1;
            while (size >= i) {
                int i4;
                Object obj;
                if (size == 0) {
                    i4 = i3;
                    obj = string;
                } else if (size < m5781P().size() && ((HistoryEntity) m5781P().get(size)).m4418e().equals(Type.TIMESTAMP) && ((HistoryEntity) m5781P().get(size)).m4416d().equals(string)) {
                    String string2 = Long.parseLong(((HistoryEntity) m5781P().get(size)).m4419f()) == 1000 ? Main.f1927b.getString(2131296792) : TimeUtils.m7070a(Long.parseLong(((HistoryEntity) m5781P().get(size)).m4419f()), true, true);
                    if (i3 == -1) {
                        r1 = string2;
                        i4 = size;
                    } else {
                        m5781P().remove(size);
                        this.f3185P.m3350e(size);
                        r1 = string2;
                        i4 = size;
                    }
                } else if (((HistoryEntity) m5781P().get(size)).m4418e().equals(Type.TIMESTAMP)) {
                    r1 = Long.parseLong(((HistoryEntity) m5781P().get(size)).m4419f()) == 1000 ? Main.f1927b.getString(2131296792) : TimeUtils.m7070a(Long.parseLong(((HistoryEntity) m5781P().get(size)).m4419f()), true, true);
                    i4 = size;
                } else {
                    i4 = i3;
                    obj = string;
                }
                size--;
                string = obj;
                i3 = i4;
            }
        }
    }

    protected void m5812c(int i, int i2) {
        int size;
        if (i2 > m5781P().size() - 1) {
            size = m5781P().size() - 1;
        } else {
            size = i2;
        }
        if (i < 0) {
            i = 0;
        }
        for (int i3 = size; i3 >= i; i3--) {
            String string = Long.parseLong(((HistoryEntity) m5781P().get(i3)).m4419f()) == 1000 ? Main.f1927b.getString(2131296792) : TimeUtils.m7070a(Long.parseLong(((HistoryEntity) m5781P().get(i3)).m4419f()), true, true);
            if (i3 == 0) {
                m5781P().add(0, new HistoryEntity.Builder().m4360a(((HistoryEntity) m5781P().get(i3)).m4419f(), string));
                this.f3185P.m3348d(0);
            } else if (((HistoryEntity) m5781P().get(i3 - 1)).m4418e() != Type.TIMESTAMP) {
                String string2 = Long.parseLong(((HistoryEntity) m5781P().get(i3 + -1)).m4419f()) == 1000 ? Main.f1927b.getString(2131296792) : TimeUtils.m7070a(Long.parseLong(((HistoryEntity) m5781P().get(i3 - 1)).m4419f()), true, true);
                if (i3 < m5781P().size() && !string2.equals(string)) {
                    m5781P().add(i3, new HistoryEntity.Builder().m4360a(((HistoryEntity) m5781P().get(i3)).m4419f(), string));
                    this.f3185P.m3348d(i3);
                }
            }
        }
        m5810b(i, size);
        m5824r();
    }

    protected void m5824r() {
        for (int size = m5781P().size() - 1; size >= 0; size--) {
            if (((HistoryEntity) m5781P().get(size)).m4418e() == Type.NEW_MESSAGE_DECLARATION) {
                this.ak = size;
                break;
            }
        }
        if (this.ak == -1) {
            this.ak = m5781P().size() - 1;
        }
    }

    protected void m5825s() {
        int size = m5781P().size() - 1;
        while (size >= 0) {
            if (((HistoryEntity) m5781P().get(size)).m4418e().equals(Type.TIMESTAMP)) {
                if (size == m5781P().size() - 1) {
                    m5781P().remove(size);
                } else if (size > 0 && ((HistoryEntity) m5781P().get(size - 1)).m4418e().equals(Type.TIMESTAMP)) {
                    m5781P().remove(size - 1);
                }
            }
            size--;
        }
    }

    protected void m5826t() {
        if (m5781P().size() > 0) {
            this.ad = Long.parseLong(((HistoryEntity) m5781P().get(0)).m4419f());
            this.ae = Long.parseLong(((HistoryEntity) m5781P().get(m5781P().size() - 1)).m4419f());
            RecycleMessagingAdapter.m5955a(this.ad);
            return;
        }
        this.ad = 0;
        this.ae = 0;
    }

    private void m5719Y() {
        this.f3197p.m3605a(new C02592(this));
        this.f3185P.m5967a(new C02603(this));
        this.f3204w.addTextChangedListener(new C02614(this));
        this.f3196o.getViewTreeObserver().addOnGlobalLayoutListener(new C02625(this));
        this.f3204w.setOnTouchListener(new C02636(this));
        this.f3204w.setOnEditorActionListener(new C02647(this));
    }

    protected void m5811b(String str) {
        EmojiHandlerCode a = EmojiHandlerCode.m6125a();
        if (this.f3204w.getText().toString().length() > 0) {
            str = this.f3204w.getText().toString();
        }
        a.m6130a(str, this.f3204w, EmojiHandlerCode.m6125a().f3543b, this);
        m5813c(this.f3204w.getText().toString());
    }

    protected void m5816j() {
    }

    protected void m5804a(String str, boolean z) {
        this.f3201t.setVisibility(8);
        if (z) {
            this.f3203v.setVisibility(0);
            this.f3172C.setText(str);
            af();
        }
    }

    protected void m5827u() {
        this.f3201t.setVisibility(0);
        this.f3203v.setVisibility(8);
    }

    protected void m5798a(Intent intent, String str) {
        if (!this.am) {
            if (intent.getExtras().containsKey("share_text_extra")) {
                String string = intent.getExtras().getString("share_text_extra");
                if (string != null) {
                    this.f3204w.setText(string);
                    this.f3204w.setSelection(string.length());
                    if (string.length() > 0) {
                        this.f3207z.setVisibility(0);
                        this.f3207z.setEnabled(true);
                    }
                }
            } else if (intent.getExtras().containsKey("share_video_extra")) {
                m5733b(StorageAccessUtil.m6962a(Uri.parse(intent.getExtras().getString("share_video_extra"))), str);
            } else if (intent.getExtras().containsKey("share_image_extra")) {
                m5802a(StorageAccessUtil.m6962a(Uri.parse(intent.getExtras().getString("share_image_extra"))), str);
            }
        }
    }

    protected Drawable m5828v() {
        int b = SharedPreferencesUtil.m7057b(getResources().getString(2131296920), 1);
        if (b != -1) {
            return getResources().getDrawable(MessageBackgroundGridAdapter.m6773a(b));
        }
        try {
            return new BitmapDrawable(getResources(), FileUtil.m7015a(Storage.m6954h() + "/background.png"));
        } catch (StorageException e) {
            return getResources().getDrawable(2130837844);
        }
    }

    public void onEvent(MessageSendStatusEvent messageSendStatusEvent) {
        int size = m5781P().size() - 1;
        while (size >= 0) {
            if (!messageSendStatusEvent.m4987a().equals(((HistoryEntity) m5781P().get(size)).m4412b())) {
                size--;
            } else if (messageSendStatusEvent.m4988b() != DeliveryStatus.SENT || ((HistoryEntity) m5781P().get(size)).m4420g() != DeliveryStatus.DELIVERED) {
                if (this.f3185P.m5980h() == 2) {
                    ((HistoryEntity) m5781P().get(size)).m4415c(messageSendStatusEvent.m4989c());
                }
                ((HistoryEntity) m5781P().get(size)).m4409a(messageSendStatusEvent.m4988b());
                m5809b(size);
                m5799a(messageSendStatusEvent.m4988b());
                return;
            } else {
                return;
            }
        }
    }

    public void onEventMainThread(NewMessageEvent newMessageEvent) {
        if (this.an && !this.f3183N.contains(newMessageEvent.f2412b.m4412b())) {
            if (this.f3184O.m5894e()) {
                this.f3202u.setVisibility(0);
            } else if (this.al) {
                this.f3182M.add(newMessageEvent.f2412b);
            } else {
                synchronized (this.aF) {
                    this.f3183N.add(newMessageEvent.f2412b.m4412b());
                    if (m5781P().isEmpty()) {
                        m5781P().add(0, newMessageEvent.f2412b);
                        m5796a(0, false);
                    } else {
                        for (int size = m5781P().size() - 1; size >= 0; size--) {
                            if (newMessageEvent.f2412b.m4419f().compareTo(((HistoryEntity) m5781P().get(size)).m4419f()) > 0) {
                                m5781P().add(size + 1, newMessageEvent.f2412b);
                                m5796a(size + 1, false);
                                break;
                            }
                        }
                    }
                    m5830x();
                }
            }
        }
    }

    public void onEvent(StickerSelectedEvent stickerSelectedEvent) {
        if (StickerManager.m6491b().m6498c() != null && this.av) {
            StickerEntity stickerEntity = (StickerEntity) StickerManager.m6491b().m6498c().get(stickerSelectedEvent.f2497a);
            m5745d(stickerEntity.m4473a(), stickerEntity.m4475b());
        }
    }

    public void onEvent(ViewHolderDeleteEvent viewHolderDeleteEvent) {
        m5781P().remove(viewHolderDeleteEvent.m4971a());
        int verticalScrollbarPosition = this.f3197p.getVerticalScrollbarPosition();
        m5823q();
        m5825s();
        m5820n();
        this.f3197p.setVerticalScrollbarPosition(verticalScrollbarPosition);
        m5717W();
    }

    public void onEventMainThread(MessageReplyEvent messageReplyEvent) {
        if (messageReplyEvent.m4968a() == this.f3185P.m5980h()) {
            this.aq = true;
            this.f3191V = messageReplyEvent.m4969b();
            this.f3199r.setVisibility(0);
            m5737c((int) PixelConverter.m7048a(50.0f, this));
            EmojiHandlerCode.m6125a().m6130a(m5790a(messageReplyEvent.m4969b().m4416d(), this.f3191V.m4418e()), this.f3175F, EmojiHandlerCode.m6125a().f3543b, this);
            this.f3174E.setText(DisplayDataHandler.m6540b(messageReplyEvent.m4970c()));
            switch (C02669.f3261c[messageReplyEvent.m4969b().m4418e().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f3173D.setVisibility(0);
                    m5752f(messageReplyEvent.m4969b().m4430q());
                    try {
                        File file = new File(Storage.m6941b() + File.separator + messageReplyEvent.m4969b().m4430q());
                        if (file.exists()) {
                            CustomImageLoader.m4009a().m4016a(this.f3173D, file);
                        }
                    } catch (StorageException e) {
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f3173D.setImageResource(2130837842);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f3173D.setVisibility(0);
                    try {
                        this.f3173D.setImageBitmap(BitmapUtil.m6976b(messageReplyEvent.m4969b().m4423j()));
                    } catch (Exception e2) {
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    int B = this.f3191V.m4396B();
                    if (B == 0) {
                        return;
                    }
                    if (StickerManager.m6491b().m6498c() == null || StickerManager.m6491b().m6498c().get(B) == null) {
                        StickerManager.m6491b().m6496a((Context) this, B, this.f3173D);
                        return;
                    }
                    try {
                        File file2 = new File(((StickerEntity) StickerManager.m6491b().m6498c().get(B)).m4477c());
                        if (file2.exists()) {
                            CustomImageLoader.m4009a().m4022b(this.f3173D, file2);
                        } else {
                            StickerManager.m6491b().m6496a((Context) this, B, this.f3173D);
                        }
                    } catch (Throwable e3) {
                        StickerManager.m6491b().m6496a((Context) this, B, this.f3173D);
                        Main.f1926a.m5682c(e3);
                    } catch (StorageException e4) {
                    }
                default:
                    this.f3173D.setVisibility(8);
            }
        }
    }

    private void m5752f(String str) {
        Constants.m3960e() + "?token=" + str;
    }

    public void onEventMainThread(MessageReplyClickEvent messageReplyClickEvent) {
        if (messageReplyClickEvent.m4967b() < m5781P().size() && ((HistoryEntity) m5781P().get(messageReplyClickEvent.m4967b())).m4401G() == RelationType.REPLY) {
            if (this.f3185P.m5983m() != -1) {
                this.f3185P.m3346c(this.f3185P.m5983m());
                this.f3185P.m5979g(-1);
            }
            int i;
            if (this.f3183N.contains(messageReplyClickEvent.m4966a())) {
                this.ar = Long.parseLong(((HistoryEntity) m5781P().get(m5760i(messageReplyClickEvent.m4967b()))).m4419f());
                this.as = Long.parseLong(((HistoryEntity) m5781P().get(messageReplyClickEvent.m4967b())).m4419f());
                for (i = 0; i < m5781P().size(); i++) {
                    if (messageReplyClickEvent.m4966a().equals(((HistoryEntity) m5781P().get(i)).m4412b())) {
                        if (Long.parseLong(((HistoryEntity) m5781P().get(this.f3179J.m3239i())).m4419f()) > Long.parseLong(((HistoryEntity) m5781P().get(i)).m4419f())) {
                            this.f3176G.setVisibility(0);
                        }
                        this.f3179J.m3211a(i, this.aC);
                        this.f3185P.m5979g(i);
                        this.f3185P.m3346c(i);
                        return;
                    }
                }
                return;
            }
            this.ar = Long.parseLong(((HistoryEntity) m5781P().get(m5760i(messageReplyClickEvent.m4967b()))).m4419f());
            this.as = Long.parseLong(((HistoryEntity) m5781P().get(messageReplyClickEvent.m4967b())).m4419f());
            ArrayList a = m5792a(Long.parseLong(((HistoryEntity) m5781P().get(messageReplyClickEvent.m4967b())).m4419f()), messageReplyClickEvent.m4966a());
            if (a != null && a.size() != 0) {
                if (Long.parseLong(((HistoryEntity) a.get(a.size() - 1)).m4419f()) > Long.parseLong(((HistoryEntity) this.f3180K.get(0)).m4419f())) {
                    while (a.size() > 0 && Long.parseLong(((HistoryEntity) a.get(a.size() - 1)).m4419f()) > Long.parseLong(((HistoryEntity) this.f3180K.get(0)).m4419f())) {
                        a.remove(a.size() - 1);
                    }
                    m5781P().addAll(0, a);
                    m5794a(0, a.size());
                    int i2 = 0;
                    for (i = 0; i < m5781P().size(); i++) {
                        if (((HistoryEntity) m5781P().get(i)).m4412b() != null) {
                            if (!this.f3183N.add(((HistoryEntity) m5781P().get(i)).m4412b())) {
                                break;
                            } else if (messageReplyClickEvent.m4966a().equals(((HistoryEntity) m5781P().get(i)).m4412b())) {
                                i2 = i;
                            }
                        }
                    }
                    this.f3179J.m3211a(i2, this.aC);
                    this.f3185P.m5979g(i2);
                    this.f3176G.setVisibility(0);
                    m5826t();
                    return;
                }
                m5805a(a);
                int a2 = m5783a(m5781P(), messageReplyClickEvent.m4966a());
                this.f3179J.m3211a(a2, this.aC);
                this.f3185P.m5979g(a2);
                this.f3185P.m3346c(a2);
                this.f3176G.setVisibility(0);
            }
        }
    }

    protected void m5829w() {
        if (this.f3204w.getText() != null) {
            String trim = this.f3204w.getText().toString().trim();
            if (trim.length() > 1500) {
                while (trim.length() / 1500 > 0) {
                    int length = trim.length() - 1;
                    while (this.ai < length && trim.charAt(this.ai) != ' ' && trim.charAt(this.ai) != '\n') {
                        this.ai++;
                    }
                    m5803a(trim.substring(0, this.ai), m5818l(), this.f3185P.m5980h());
                    trim = trim.substring(this.ai).trim();
                    this.ai = 1000;
                }
                this.f3204w.setText("");
                m5803a(trim, m5818l(), this.f3185P.m5980h());
                return;
            }
            this.f3204w.setText("");
            m5803a(trim, m5818l(), this.f3185P.m5980h());
        }
    }

    protected void m5803a(String str, ArrayList<String> arrayList, int i) {
        if (str != null && str.length() != 0) {
            String str2 = (System.currentTimeMillis() * 1000) + "";
            if (i == 1) {
                ChatStateManager.m7307a().m7309b().put(m5818l().get(0), ChatState.active);
            }
            NormalMessageHandler.m7415a().m7439a(str, arrayList, str2, false, i, this.f3191V);
            m5770D();
        }
    }

    protected void m5802a(String str, String str2) {
        new Thread(new RunnableMediaSend(this, 0, str, str2), "Thread-Send-Photo").start();
    }

    private void m5756g(String str) {
        if (str == null) {
            Main.m3905a(getString(2131296819));
        } else {
            new Thread(new RunnableMediaSend(this, 2, str, null), "Thread-Send-Audio").start();
        }
    }

    private void m5733b(String str, String str2) {
        if (str == null) {
            Main.m3905a(getString(2131296819));
        } else {
            new Thread(new RunnableMediaSend(this, 1, str, str2), "Thread-Send-Video").start();
        }
    }

    private void m5725a(LocationEntity locationEntity, int i, ArrayList<String> arrayList) {
        String e = locationEntity.m4471e();
        ArrayList arrayList2 = arrayList;
        NormalMessageHandler.m7415a().m7438a(HistoryEntity.m4394a(locationEntity.m4468c(), locationEntity.m4470d(), Double.valueOf(locationEntity.m4465b()), Double.valueOf(locationEntity.m4462a())), e, arrayList2, (System.currentTimeMillis() * 1000) + "", false, i, this.f3191V);
        m5770D();
    }

    private void m5745d(int i, int i2) {
        NormalMessageHandler.m7415a().m7440a(m5818l(), (System.currentTimeMillis() * 1000) + "", false, i, i2, this.f3185P.m5980h(), this.f3191V);
        m5770D();
    }

    protected HistoryNormalMessageEntity m5789a(String str, Dimension dimension, String str2, String str3) {
        Builder a = m5724a(Type.PHOTO);
        a.m4380h(str).m4350a(dimension.f4606a).m4361b(dimension.f4607b).m4376e(str2).m4379g(str3);
        return m5784a(a);
    }

    protected HistoryNormalMessageEntity m5787a(String str, int i, int i2, String str2, String str3) {
        Builder a = m5724a(Type.PHOTO);
        a.m4380h(str).m4350a(i).m4361b(i2).m4379g(str3).m4376e(str2);
        return m5784a(a);
    }

    protected HistoryNormalMessageEntity m5785a(String str, int i, int i2) {
        Builder a = m5724a(Type.AUDIO);
        a.m4380h(str).m4371d(i).m4368c(i2);
        return m5784a(a);
    }

    protected HistoryNormalMessageEntity m5788a(String str, Dimension dimension, String str2, int i, int i2, String str3) {
        Builder a = m5724a(Type.VIDEO);
        a.m4380h(str).m4350a(dimension.f4606a).m4361b(dimension.f4607b).m4376e(str2).m4371d(i).m4368c(i2).m4379g(str3);
        return m5784a(a);
    }

    protected HistoryNormalMessageEntity m5786a(String str, int i, int i2, String str2, int i3, int i4, String str3) {
        Builder a = m5724a(Type.VIDEO);
        a.m4380h(str).m4350a(i).m4361b(i2).m4376e(str2).m4371d(i3).m4368c(i4).m4379g(str3);
        return m5784a(a);
    }

    private Builder m5724a(Type type) {
        long currentTimeMillis = 1000 * System.currentTimeMillis();
        String str = currentTimeMillis + "";
        Builder builder = new Builder();
        builder.m4356a(type).m4369c(StringUtil.m7064a(type.toString().toLowerCase()) + " Message").m4372d(currentTimeMillis + "").m4353a(DeliveryStatus.UPLOADING).m4359a(str).m4355a(this.f3191V != null ? RelationType.REPLY : RelationType.NONE);
        if (this.f3191V != null) {
            builder.m4389q(this.f3191V.m4412b()).m4362b(this.f3191V.m4418e()).m4391s(m5743d(this.f3191V)).m4363b(m5736c(this.f3191V));
            if (this.f3191V.m4418e() == Type.AUDIO) {
                return builder;
            }
            str = this.f3191V.m4418e() == Type.TEXT ? this.f3191V.m4416d() : this.f3191V.m4418e() == Type.STICKER ? this.f3191V.m4396B() + "" : BitmapUtil.m6981d(this.f3191V.m4423j());
            builder.m4390r(str);
        }
        return builder;
    }

    private CastPX.Type m5736c(HistoryEntity historyEntity) {
        JabberId a = JabberId.m7386a(historyEntity.m4414c());
        if (a == null || !XMPPUtils.m7095c(a) || historyEntity.m4422i()) {
            return null;
        }
        return historyEntity.m4398D();
    }

    protected synchronized void m5799a(DeliveryStatus deliveryStatus) {
    }

    protected void m5830x() {
    }

    protected void m5801a(CharSequence charSequence, int i, int i2, int i3) {
        m5813c(charSequence.toString());
        if (StringUtil.m7066b(charSequence.toString())) {
            this.f3207z.setEnabled(false);
        } else {
            this.f3207z.setEnabled(true);
        }
        if (charSequence.length() > 0 && this.aj > charSequence.length() && !this.ao) {
            int length = this.f3204w.getText().toString().length();
            int selectionStart = this.f3204w.getSelectionStart();
            ImageSpan[] imageSpanArr = (ImageSpan[]) this.f3204w.getText().getSpans(i - 1, i + i2, ImageSpan.class);
            if (imageSpanArr != null && imageSpanArr.length > 0) {
                int spanStart = this.f3204w.getText().getSpanStart(imageSpanArr[0]);
                int spanEnd = this.f3204w.getText().getSpanEnd(imageSpanArr[0]);
                String str = "";
                if (spanStart > 0) {
                    str = this.f3204w.getText().toString().substring(0, spanStart);
                }
                String str2 = "";
                if (spanEnd <= charSequence.toString().length()) {
                    str2 = this.f3204w.getText().toString().substring(spanEnd, charSequence.toString().length());
                }
                str = str + str2;
                this.f3204w.setText("");
                EmojiHandlerCode.m6125a().m6130a(str, this.f3204w, EmojiHandlerCode.m6125a().f3543b, this);
                this.f3204w.setSelection(selectionStart - (length - str.length()) >= 0 ? selectionStart - (length - str.length()) : 0);
            }
        }
        this.ao = false;
        this.aj = charSequence.length();
    }

    protected String m5808b(HistoryEntity historyEntity) {
        if (historyEntity == null) {
            return null;
        }
        if (historyEntity.m4423j() == null) {
            return m5757h(historyEntity.m4430q());
        }
        if (!historyEntity.m4423j().startsWith("file://")) {
            return historyEntity.m4423j();
        }
        try {
            File e = FileUtil.m7029e(historyEntity.m4423j());
            if (e != null && e.exists()) {
                Bitmap decodeFile = BitmapFactory.decodeFile(e.getPath());
                if (decodeFile == null) {
                    return m5757h(historyEntity.m4430q());
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                decodeFile.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            }
        } catch (Throwable e2) {
            Main.f1926a.m5678a(e2);
        }
        return m5757h(historyEntity.m4430q());
    }

    private String m5757h(String str) {
        Throwable e;
        try {
            File file = new File(Storage.m6941b() + File.separator + str);
            if (file.exists()) {
                return FileUtil.m7024b(file);
            }
            return null;
        } catch (StorageException e2) {
            e = e2;
            Main.f1926a.m5682c(e);
            return null;
        } catch (IOException e3) {
            e = e3;
            Main.f1926a.m5682c(e);
            return null;
        }
    }

    protected void m5813c(String str) {
        if (str.length() > 0) {
            this.f3207z.setEnabled(true);
        } else {
            this.f3207z.setEnabled(false);
        }
    }

    void m5831y() {
        this.f3176G.setVisibility(8);
        am();
    }

    void m5832z() {
        this.f3202u.setVisibility(8);
        if (this.f3184O != null) {
            if (this.f3184O.m5894e()) {
                this.ad = 0;
                ArrayList a = m5793a(LoadDirection.downToUp, this.ad, this.ae);
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    this.f3183N.add(((HistoryEntity) it.next()).m4412b());
                }
                if (a.size() == 0) {
                    m5782Q();
                    return;
                } else if (m5781P().size() == 0) {
                    m5781P().addAll(a);
                    m5782Q();
                    m5826t();
                    return;
                } else if (Long.parseLong(((HistoryEntity) m5781P().get(m5781P().size() - 1)).m4419f()) > Long.parseLong(((HistoryEntity) a.get(0)).m4419f())) {
                    m5734b(a);
                    m5781P().addAll(a);
                    m5794a(m5781P().size() - a.size(), a.size());
                    this.f3184O.m5887a(true);
                    this.f3184O.m5890b(false);
                    m5782Q();
                    m5826t();
                    return;
                } else {
                    m5805a(a);
                    this.f3184O.m5890b(false);
                    this.f3179J.m3230d(m5781P().size() - 1);
                    return;
                }
            }
            m5782Q();
        }
    }

    protected void m5805a(ArrayList<HistoryEntity> arrayList) {
        m5781P().clear();
        this.f3183N.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HistoryEntity historyEntity = (HistoryEntity) it.next();
            m5781P().add(historyEntity);
            this.f3183N.add(historyEntity.m4412b());
        }
        m5794a(0, m5781P().size());
        this.f3184O.m5890b(true);
        this.f3184O.m5887a(true);
        this.ap = false;
        m5826t();
    }

    private void m5734b(ArrayList<HistoryEntity> arrayList) {
        if (arrayList != null && m5781P() != null) {
            while (arrayList.size() > 0 && Long.parseLong(((HistoryEntity) m5781P().get(m5781P().size() - 1)).m4419f()) > Long.parseLong(((HistoryEntity) arrayList.get(0)).m4419f())) {
                arrayList.remove(0);
            }
        }
    }

    private void m5737c(int i) {
        LayoutParams layoutParams = this.f3197p.getLayoutParams();
        if (i < 0) {
            layoutParams.height = i;
        } else {
            layoutParams.height = DeviceUtils.m7011a((Activity) this).heightPixels - i;
        }
        this.f3197p.setLayoutParams(layoutParams);
    }

    void m5767A() {
        if (!this.f3194Y.equals(MenuState.EXTRA_KEYBOARD)) {
            aj();
            this.f3194Y = MenuState.EXTRA_KEYBOARD;
            m5821o();
            aa();
        }
    }

    void m5768B() {
        if (!this.f3194Y.equals(MenuState.STICKER)) {
            ak();
            this.f3194Y = MenuState.STICKER;
            m5821o();
            aa();
        }
    }

    void m5769C() {
        if (!this.f3194Y.equals(MenuState.MICROPHONE)) {
            this.f3194Y = MenuState.MICROPHONE;
            m5821o();
            aa();
        }
    }

    void m5770D() {
        runOnUiThread(new C02658(this));
    }

    void m5771E() {
        m5829w();
    }

    public void m5772F() {
        if (PermissionUtil.m7044a(PermissionType.storage)) {
            this.f3195Z = MediaPickerUtil.m7032a((Activity) this, 30003);
            ac();
            return;
        }
        PermissionUtil.m7042a((Activity) this, PermissionType.storage, 2);
    }

    public void m5773G() {
        if (PermissionUtil.m7044a(PermissionType.gallery)) {
            Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
            intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_IMAGE_MULTIPLE_PICK");
            startActivityForResult(intent, 30001);
            ac();
            return;
        }
        PermissionUtil.m7042a((Activity) this, PermissionType.gallery, 3);
    }

    public void m5774H() {
        if (PermissionUtil.m7044a(PermissionType.video)) {
            Intent intent = new Intent(this, SelectResourceFromSDCardActivity.class);
            intent.putExtra(Action.ATTRIBUTE_NAME, "BisPhone.ACTION_VIDEO_SINGLE_PICK");
            startActivityForResult(intent, 30002);
            ac();
            return;
        }
        PermissionUtil.m7042a((Activity) this, PermissionType.video, 4);
    }

    public void m5775I() {
        if (PermissionUtil.m7044a(PermissionType.location)) {
            MediaPickerUtil.m7035b(this, 30005);
            ac();
            return;
        }
        PermissionUtil.m7042a((Activity) this, PermissionType.location, 6);
    }

    public void m5776J() {
        this.f3170A.performClick();
    }

    public void m5814d(String str) {
        CharSequence a = EmojiHandlerCode.m6125a().m6128a((SmilyMaper) SmilyMaper.m6131a().get(str), EmojiHandlerCode.m6125a().f3543b, (Context) this);
        this.f3204w.getText().insert(this.f3204w.getSelectionStart(), a);
        this.f3204w.append(" ");
    }

    public void m5777K() {
        this.ao = true;
        this.f3204w.dispatchKeyEvent(new KeyEvent(0, 67));
    }

    public void m5815e(String str) {
        m5756g(str);
    }

    public void m5778L() {
        if (this.f3193X.equals(ActivityState.KEYBOARD)) {
            m5780N();
            if (getResources().getConfiguration().orientation == 2) {
                ac();
            }
            this.f3193X = ActivityState.NONE;
        } else if (this.f3193X.equals(ActivityState.OPTION_MENU)) {
            ac();
            m5821o();
        } else if (this.f3193X.equals(ActivityState.ACTION_MODE)) {
            m5715U();
            this.f3193X = ActivityState.NONE;
        } else {
            this.f3199r.setVisibility(8);
            finish();
        }
    }

    public void m5779M() {
        String obj = this.f3204w.getText().toString();
        this.f3204w.setText("");
        EmojiHandlerCode.m6125a().m6130a(obj, this.f3204w, EmojiHandlerCode.m6125a().f3543b, this);
        this.f3204w.setSelection(this.f3204w.length());
    }

    private void m5720Z() {
        LayoutParams layoutParams = this.f3200s.getLayoutParams();
        if (getResources().getConfiguration().orientation == 1) {
            if (this.aA > 0) {
                layoutParams.height = this.aA;
            }
        } else if (getResources().getConfiguration().orientation == 2 && this.aB > 0) {
            layoutParams.height = this.aB;
        }
        this.f3200s.setLayoutParams(layoutParams);
    }

    private void aa() {
        if (!this.f3193X.equals(ActivityState.ACTION_MODE)) {
            this.f3200s.setVisibility(4);
            m5780N();
            if (!this.f3193X.equals(ActivityState.KEYBOARD)) {
                ab();
            }
            this.f3193X = ActivityState.OPTION_MENU;
        }
    }

    private void ab() {
        m5720Z();
        m5740c(false);
        this.f3200s.setVisibility(0);
        this.f3193X = ActivityState.OPTION_MENU;
    }

    private void ac() {
        LayoutParams layoutParams = this.f3200s.getLayoutParams();
        layoutParams.height = 0;
        this.f3200s.setLayoutParams(layoutParams);
        m5740c(true);
        this.f3193X = ActivityState.NONE;
        this.f3194Y = MenuState.NONE;
    }

    private void ad() {
        this.f3204w.requestFocus();
        this.f3192W.showSoftInput(this.f3204w, 2);
        getWindow().setSoftInputMode(4);
        m5715U();
        if (getResources().getConfiguration().orientation == 2) {
            this.f3200s.setVisibility(4);
        }
        this.f3193X = ActivityState.KEYBOARD;
        this.f3194Y = MenuState.NONE;
        m5821o();
    }

    private void ae() {
        m5740c(false);
        m5744d(getResources().getConfiguration().orientation == 1 ? this.aA : this.aB);
        ac();
        m5744d(0);
        this.f3193X = ActivityState.KEYBOARD;
        this.f3194Y = MenuState.NONE;
        m5821o();
    }

    private void m5744d(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f3201t.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, i);
        this.f3201t.setLayoutParams(layoutParams);
    }

    private void m5740c(boolean z) {
        LayoutParams layoutParams = this.f3197p.getLayoutParams();
        if (z) {
            layoutParams.height = -1;
        } else {
            layoutParams.height = DeviceUtils.m7011a((Activity) this).heightPixels - (getResources().getConfiguration().orientation == 1 ? this.aA : this.aB);
            if (this.f3184O == null || !this.f3184O.m5894e()) {
                m5782Q();
            }
        }
        this.f3197p.setLayoutParams(layoutParams);
    }

    private void af() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, 2131755232);
        this.f3198q.setLayoutParams(layoutParams);
    }

    protected void m5780N() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            this.f3192W.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void ag() {
        if (getResources().getConfiguration().orientation == 1) {
            ah();
        } else if (getResources().getConfiguration().orientation == 2) {
            ai();
        }
    }

    private void ah() {
        this.aA = SharedPreferencesUtil.m7057b("portraitKeyboardHeight", 0);
        if (getResources().getConfiguration().orientation == 1 && this.aA == 0) {
            ad();
        }
    }

    private void ai() {
        this.aB = SharedPreferencesUtil.m7057b("landScapeKeyboardHeight", 0);
        if (getResources().getConfiguration().orientation == 2 && this.aB == 0) {
            ad();
        }
    }

    private void m5748e(int i) {
        if (m5759h(i)) {
            SharedPreferencesUtil.m7053a("portraitKeyboardHeight", i);
        }
    }

    private void m5750f(int i) {
        if (m5759h(i)) {
            SharedPreferencesUtil.m7053a("landScapeKeyboardHeight", i);
        }
    }

    private int m5753g(int i) {
        return m5759h(i) ? i : this.ay / 3;
    }

    private boolean m5759h(int i) {
        return i > (getResources().getConfiguration().orientation == 1 ? this.ay / 3 : this.az / 3);
    }

    private void aj() {
        this.f3205x.setEnabled(false);
        this.f3186Q.postDelayed(new RunnableEnable(this, 0), 1500);
    }

    private void ak() {
        this.f3206y.setEnabled(false);
        this.f3186Q.postDelayed(new RunnableEnable(this, 1), 1500);
    }

    private void al() {
        this.f3204w.setClickable(false);
        this.f3186Q.postDelayed(new RunnableEnable(this, 2), 1000);
    }

    protected void m5800a(LoadDirection loadDirection, boolean z, Intent intent) {
        this.f3188S = new MessageLoader(z, loadDirection, this, intent);
        this.f3188S.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static boolean m5711O() {
        return aD;
    }

    protected ArrayList<HistoryEntity> m5781P() {
        ArrayList<HistoryEntity> arrayList;
        synchronized (this.aG) {
            arrayList = this.f3180K;
        }
        return arrayList;
    }

    protected String m5790a(String str, Type type) {
        switch (C02669.f3261c[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return getString(2131296642);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return getString(2131296547);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return getString(2131296887);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return getString(2131296774);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return getString(2131296337);
            default:
                return str;
        }
    }

    private String m5743d(HistoryEntity historyEntity) {
        String str = "";
        switch (this.f3185P.m5980h()) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                str = ((HistoryNormalMessageEntity) historyEntity).m4456M();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                str = ((HistoryGroupEntity) historyEntity).m4449M();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                str = "fake username";
                break;
        }
        if (str == null || str.isEmpty() || str.equals(AccountManager.m3934a().m3937c()) || ((historyEntity.m4422i() && this.f3185P.m5980h() == 1) || ((str.contains("-") && this.f3185P.m5980h() != 3) || (str.contains("-") && this.f3185P.m5980h() == 3 && historyEntity.m4422i())))) {
            return AccountManager.m3934a().m3937c();
        }
        return str;
    }

    protected void m5782Q() {
        if (m5781P().size() != 0) {
            this.f3197p.m3599a(m5781P().size() - 1);
            this.f3176G.setVisibility(8);
            this.ar = -1;
            this.as = -1;
        }
    }

    private void am() {
        if (m5781P().size() != 0) {
            HistoryEntity historyEntity = (HistoryEntity) m5781P().get(m5781P().size() - 1);
            if (this.f3185P.m5983m() != -1) {
                this.f3185P.m3346c(this.f3185P.m5983m());
                this.f3185P.m5979g(-1);
            }
            int a;
            if (this.ar > Long.parseLong(historyEntity.m4419f()) || this.as > Long.parseLong(historyEntity.m4419f())) {
                ArrayList a2 = m5791a(40, this.ar);
                if (a2.size() == 0) {
                    m5782Q();
                    this.ar = -1;
                    this.as = -1;
                    return;
                } else if (Long.parseLong(historyEntity.m4419f()) >= Long.parseLong(((HistoryEntity) a2.get(0)).m4419f())) {
                    while (a2.size() > 0 && Long.parseLong(((HistoryEntity) a2.get(0)).m4419f()) < Long.parseLong(historyEntity.m4419f())) {
                        a2.remove(0);
                    }
                    m5781P().addAll(a2);
                    a = m5722a(m5781P(), this.as);
                    this.f3185P.m5979g(a);
                    this.f3185P.m3346c(a);
                    this.f3179J.m3211a(a, this.aC);
                    m5826t();
                    this.ar = -1;
                    this.as = -1;
                    return;
                } else {
                    m5805a(a2);
                    a = m5722a(m5781P(), this.as);
                    this.f3185P.m5979g(a);
                    this.f3185P.m3346c(a);
                    m5826t();
                    this.ar = -1;
                    this.as = -1;
                    return;
                }
            }
            a = m5722a(m5781P(), this.as);
            this.f3185P.m5979g(a);
            this.f3185P.m3346c(a);
            this.f3179J.m3211a(a, this.aC);
            this.ar = -1;
            this.as = -1;
        }
    }

    private int m5722a(List<HistoryEntity> list, long j) {
        for (int i = 0; i < list.size(); i++) {
            if (j == Long.parseLong(((HistoryEntity) list.get(i)).m4419f())) {
                return i;
            }
        }
        return list.size() - 1;
    }

    protected int m5783a(List<HistoryEntity> list, String str) {
        if (str == null || list == null) {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(((HistoryEntity) list.get(i)).m4412b())) {
                return i;
            }
        }
        return list.size() - 1;
    }

    private int m5760i(int i) {
        int size = m5781P().size() - i;
        if (size > 20) {
            size = i - 20;
            if (size < 0) {
                return 0;
            }
            return size;
        }
        size = (i - 20) - (20 - size);
        if (size >= 0) {
            return size;
        }
        return 0;
    }

    public void onEventMainThread(ReplyCancelerEvent replyCancelerEvent) {
        m5770D();
    }
}
