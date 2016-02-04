package app.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog.Builder;
import app.Main;
import app.account.AccountManager;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.ConversationNormalDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.ReplyCancelerEvent;
import app.events.view_holder.MessageReplyEvent;
import app.events.view_holder.ViewHolderDeleteEvent;
import app.messaging.RecycleMessagingActivity;
import app.messaging.broadcast.BroadcastMessageInfoActivity;
import app.messaging.selector.SelectRecipientActivity;
import app.messaging.vh.DisplayDataHandler;
import app.storage.Storage;
import app.storage.StorageException;
import app.xmpp.GroupManager;
import app.xmpp.NormalMessageManager;
import app.xmpp.packet.groupv2.GroupElement;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.ArrayList;
import se.emilsjolander.stickylistheaders.C1128R;

public class BubbleDialogUtil {
    private static BubbleDialogUtil f4604a;

    /* renamed from: app.util.BubbleDialogUtil.11 */
    class AnonymousClass11 implements OnClickListener {
        final /* synthetic */ ArrayList f4533a;
        final /* synthetic */ Context f4534b;
        final /* synthetic */ HistoryEntity f4535c;
        final /* synthetic */ int f4536d;
        final /* synthetic */ String f4537e;
        final /* synthetic */ int f4538f;
        final /* synthetic */ BubbleDialogUtil f4539g;

        /* renamed from: app.util.BubbleDialogUtil.11.1 */
        class C04931 extends ArrayList<String> {
            final /* synthetic */ AnonymousClass11 f4532a;

            C04931(AnonymousClass11 anonymousClass11) {
                this.f4532a = anonymousClass11;
                add(this.f4532a.f4535c.m4412b());
            }
        }

        AnonymousClass11(BubbleDialogUtil bubbleDialogUtil, ArrayList arrayList, Context context, HistoryEntity historyEntity, int i, String str, int i2) {
            this.f4539g = bubbleDialogUtil;
            this.f4533a = arrayList;
            this.f4534b = context;
            this.f4535c = historyEntity;
            this.f4536d = i;
            this.f4537e = str;
            this.f4538f = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (AnonymousClass19.f4564b[((DialogOption) this.f4533a.get(i)).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    ClipboardUtil.m7007a(this.f4534b, this.f4535c.m4432s());
                    Main.m3905a(this.f4534b.getString(2131296847));
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    ((RecycleMessagingActivity) this.f4534b).f3181L = new C04931(this);
                    Intent intent = new Intent(this.f4534b, SelectRecipientActivity.class);
                    intent.putExtra("multiple_forward_message_type", this.f4536d);
                    intent.putExtra("extra_is_forward_mode", true);
                    ((RecycleMessagingActivity) this.f4534b).startActivityForResult(intent, 30007);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f4539g.m6997c(this.f4534b, this.f4535c, this.f4537e, this.f4536d, this.f4538f);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4539g.m6989a(this.f4535c, this.f4537e, this.f4536d);
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f4539g.m6987a(this.f4534b, (HistoryGroupEntity) this.f4535c);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    if (this.f4535c instanceof HistoryNormalMessageEntity) {
                        this.f4539g.m6988a(this.f4534b, this.f4539g.m6983a((HistoryNormalMessageEntity) this.f4535c), this.f4535c.m4420g());
                    }
                default:
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.12 */
    class AnonymousClass12 extends ArrayList<String> {
        final /* synthetic */ Context f4540a;
        final /* synthetic */ BubbleDialogUtil f4541b;

        AnonymousClass12(BubbleDialogUtil bubbleDialogUtil, Context context) {
            this.f4541b = bubbleDialogUtil;
            this.f4540a = context;
            add(this.f4540a.getResources().getString(2131296733));
            add(this.f4540a.getResources().getString(2131296449));
            add(this.f4540a.getResources().getString(2131296412));
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.14 */
    class AnonymousClass14 implements OnClickListener {
        final /* synthetic */ ArrayList f4544a;
        final /* synthetic */ Context f4545b;
        final /* synthetic */ HistoryEntity f4546c;
        final /* synthetic */ int f4547d;
        final /* synthetic */ String f4548e;
        final /* synthetic */ int f4549f;
        final /* synthetic */ BubbleDialogUtil f4550g;

        /* renamed from: app.util.BubbleDialogUtil.14.1 */
        class C04941 extends ArrayList<String> {
            final /* synthetic */ AnonymousClass14 f4543a;

            C04941(AnonymousClass14 anonymousClass14) {
                this.f4543a = anonymousClass14;
                add(this.f4543a.f4546c.m4412b());
            }
        }

        AnonymousClass14(BubbleDialogUtil bubbleDialogUtil, ArrayList arrayList, Context context, HistoryEntity historyEntity, int i, String str, int i2) {
            this.f4550g = bubbleDialogUtil;
            this.f4544a = arrayList;
            this.f4545b = context;
            this.f4546c = historyEntity;
            this.f4547d = i;
            this.f4548e = str;
            this.f4549f = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (AnonymousClass19.f4564b[((DialogOption) this.f4544a.get(i)).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    ((RecycleMessagingActivity) this.f4545b).f3181L = new C04941(this);
                    Intent intent = new Intent(this.f4545b, SelectRecipientActivity.class);
                    intent.putExtra("multiple_forward_message_type", this.f4547d);
                    intent.putExtra("extra_is_forward_mode", true);
                    ((RecycleMessagingActivity) this.f4545b).startActivityForResult(intent, 30007);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f4550g.m6997c(this.f4545b, this.f4546c, this.f4548e, this.f4547d, this.f4549f);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4550g.m6989a(this.f4546c, this.f4548e, this.f4547d);
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f4550g.m6987a(this.f4545b, (HistoryGroupEntity) this.f4546c);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    if (this.f4546c instanceof HistoryNormalMessageEntity) {
                        this.f4550g.m6988a(this.f4545b, this.f4550g.m6983a((HistoryNormalMessageEntity) this.f4546c), this.f4546c.m4420g());
                    }
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    this.f4550g.m6985a(this.f4545b, this.f4546c);
                default:
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.15 */
    class AnonymousClass15 extends ArrayList<String> {
        final /* synthetic */ Context f4551a;
        final /* synthetic */ BubbleDialogUtil f4552b;

        AnonymousClass15(BubbleDialogUtil bubbleDialogUtil, Context context) {
            this.f4552b = bubbleDialogUtil;
            this.f4551a = context;
            add(this.f4551a.getResources().getString(2131296733));
            add(this.f4551a.getResources().getString(2131296412));
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.17 */
    class AnonymousClass17 implements OnClickListener {
        final /* synthetic */ ArrayList f4554a;
        final /* synthetic */ Context f4555b;
        final /* synthetic */ HistoryEntity f4556c;
        final /* synthetic */ String f4557d;
        final /* synthetic */ int f4558e;
        final /* synthetic */ int f4559f;
        final /* synthetic */ BubbleDialogUtil f4560g;

        AnonymousClass17(BubbleDialogUtil bubbleDialogUtil, ArrayList arrayList, Context context, HistoryEntity historyEntity, String str, int i, int i2) {
            this.f4560g = bubbleDialogUtil;
            this.f4554a = arrayList;
            this.f4555b = context;
            this.f4556c = historyEntity;
            this.f4557d = str;
            this.f4558e = i;
            this.f4559f = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (AnonymousClass19.f4564b[((DialogOption) this.f4554a.get(i)).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f4560g.m6997c(this.f4555b, this.f4556c, this.f4557d, this.f4558e, this.f4559f);
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f4560g.m6987a(this.f4555b, (HistoryGroupEntity) this.f4556c);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    if (this.f4556c instanceof HistoryNormalMessageEntity) {
                        this.f4560g.m6988a(this.f4555b, this.f4560g.m6983a((HistoryNormalMessageEntity) this.f4556c), this.f4556c.m4420g());
                    }
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    this.f4560g.m6985a(this.f4555b, this.f4556c);
                default:
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.18 */
    class AnonymousClass18 implements OnClickListener {
        final /* synthetic */ HistoryGroupEntity f4561a;
        final /* synthetic */ BubbleDialogUtil f4562b;

        AnonymousClass18(BubbleDialogUtil bubbleDialogUtil, HistoryGroupEntity historyGroupEntity) {
            this.f4562b = bubbleDialogUtil;
            this.f4561a = historyGroupEntity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            GroupManager.m7323a().m7351a(this.f4561a.m4414c(), this.f4561a.m4449M(), null);
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.19 */
    /* synthetic */ class AnonymousClass19 {
        static final /* synthetic */ int[] f4563a;
        static final /* synthetic */ int[] f4564b;

        static {
            f4564b = new int[DialogOption.values().length];
            try {
                f4564b[DialogOption.COPY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4564b[DialogOption.FORWARD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4564b[DialogOption.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4564b[DialogOption.REPLY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4564b[DialogOption.KICK.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4564b[DialogOption.INFO.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4564b[DialogOption.SHARE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            f4563a = new int[Type.values().length];
            try {
                f4563a[Type.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4563a[Type.STICKER.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4563a[Type.CALL.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.1 */
    class C04951 extends ArrayList<String> {
        final /* synthetic */ Context f4565a;
        final /* synthetic */ BubbleDialogUtil f4566b;

        C04951(BubbleDialogUtil bubbleDialogUtil, Context context) {
            this.f4566b = bubbleDialogUtil;
            this.f4565a = context;
            add(this.f4565a.getResources().getString(2131296404));
            add(this.f4565a.getResources().getString(2131296449));
            add(this.f4565a.getResources().getString(2131296412));
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.2 */
    class C04962 extends ArrayList<DialogOption> {
        final /* synthetic */ BubbleDialogUtil f4567a;

        C04962(BubbleDialogUtil bubbleDialogUtil) {
            this.f4567a = bubbleDialogUtil;
            add(DialogOption.COPY);
            add(DialogOption.FORWARD);
            add(DialogOption.DELETE);
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.3 */
    class C04983 implements OnClickListener {
        final /* synthetic */ ArrayList f4569a;
        final /* synthetic */ Context f4570b;
        final /* synthetic */ HistoryEntity f4571c;
        final /* synthetic */ int f4572d;
        final /* synthetic */ String f4573e;
        final /* synthetic */ int f4574f;
        final /* synthetic */ BubbleDialogUtil f4575g;

        /* renamed from: app.util.BubbleDialogUtil.3.1 */
        class C04971 extends ArrayList<String> {
            final /* synthetic */ C04983 f4568a;

            C04971(C04983 c04983) {
                this.f4568a = c04983;
                add(this.f4568a.f4571c.m4412b());
            }
        }

        C04983(BubbleDialogUtil bubbleDialogUtil, ArrayList arrayList, Context context, HistoryEntity historyEntity, int i, String str, int i2) {
            this.f4575g = bubbleDialogUtil;
            this.f4569a = arrayList;
            this.f4570b = context;
            this.f4571c = historyEntity;
            this.f4572d = i;
            this.f4573e = str;
            this.f4574f = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (AnonymousClass19.f4564b[((DialogOption) this.f4569a.get(i)).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    ClipboardUtil.m7007a(this.f4570b, this.f4571c.m4416d());
                    Main.m3905a(this.f4570b.getString(2131296847));
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    ((RecycleMessagingActivity) this.f4570b).f3181L = new C04971(this);
                    Intent intent = new Intent(this.f4570b, SelectRecipientActivity.class);
                    intent.putExtra("multiple_forward_message_type", this.f4572d);
                    intent.putExtra("extra_is_forward_mode", true);
                    ((RecycleMessagingActivity) this.f4570b).startActivityForResult(intent, 30007);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f4575g.m6997c(this.f4570b, this.f4571c, this.f4573e, this.f4572d, this.f4574f);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4575g.m6989a(this.f4571c, this.f4573e, this.f4572d);
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f4575g.m6987a(this.f4570b, (HistoryGroupEntity) this.f4571c);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    if (this.f4571c instanceof HistoryNormalMessageEntity) {
                        this.f4575g.m6988a(this.f4570b, this.f4575g.m6983a((HistoryNormalMessageEntity) this.f4571c), this.f4571c.m4420g());
                    }
                default:
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.4 */
    class C04994 extends ArrayList<String> {
        final /* synthetic */ Context f4576a;
        final /* synthetic */ BubbleDialogUtil f4577b;

        C04994(BubbleDialogUtil bubbleDialogUtil, Context context) {
            this.f4577b = bubbleDialogUtil;
            this.f4576a = context;
            add(this.f4576a.getResources().getString(2131296412));
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.5 */
    class C05005 extends ArrayList<DialogOption> {
        final /* synthetic */ BubbleDialogUtil f4578a;

        C05005(BubbleDialogUtil bubbleDialogUtil) {
            this.f4578a = bubbleDialogUtil;
            add(DialogOption.DELETE);
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.6 */
    class C05016 implements OnClickListener {
        final /* synthetic */ ArrayList f4579a;
        final /* synthetic */ HistoryEntity f4580b;
        final /* synthetic */ String f4581c;
        final /* synthetic */ int f4582d;
        final /* synthetic */ Context f4583e;
        final /* synthetic */ int f4584f;
        final /* synthetic */ BubbleDialogUtil f4585g;

        C05016(BubbleDialogUtil bubbleDialogUtil, ArrayList arrayList, HistoryEntity historyEntity, String str, int i, Context context, int i2) {
            this.f4585g = bubbleDialogUtil;
            this.f4579a = arrayList;
            this.f4580b = historyEntity;
            this.f4581c = str;
            this.f4582d = i;
            this.f4583e = context;
            this.f4584f = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (AnonymousClass19.f4564b[((DialogOption) this.f4579a.get(i)).ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    this.f4585g.m6997c(this.f4583e, this.f4580b, this.f4581c, this.f4582d, this.f4584f);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    this.f4585g.m6989a(this.f4580b, this.f4581c, this.f4582d);
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    this.f4585g.m6987a(this.f4583e, (HistoryGroupEntity) this.f4580b);
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    if (this.f4580b instanceof HistoryNormalMessageEntity) {
                        this.f4585g.m6988a(this.f4583e, this.f4585g.m6983a((HistoryNormalMessageEntity) this.f4580b), this.f4580b.m4420g());
                    }
                default:
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.7 */
    class C05027 extends ArrayList<String> {
        final /* synthetic */ Context f4586a;
        final /* synthetic */ BubbleDialogUtil f4587b;

        C05027(BubbleDialogUtil bubbleDialogUtil, Context context) {
            this.f4587b = bubbleDialogUtil;
            this.f4586a = context;
            add(this.f4586a.getResources().getString(2131296412));
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.8 */
    class C05038 implements OnClickListener {
        final /* synthetic */ Context f4588a;
        final /* synthetic */ HistoryEntity f4589b;
        final /* synthetic */ String f4590c;
        final /* synthetic */ int f4591d;
        final /* synthetic */ int f4592e;
        final /* synthetic */ BubbleDialogUtil f4593f;

        C05038(BubbleDialogUtil bubbleDialogUtil, Context context, HistoryEntity historyEntity, String str, int i, int i2) {
            this.f4593f = bubbleDialogUtil;
            this.f4588a = context;
            this.f4589b = historyEntity;
            this.f4590c = str;
            this.f4591d = i;
            this.f4592e = i2;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    this.f4593f.m6997c(this.f4588a, this.f4589b, this.f4590c, this.f4591d, this.f4592e);
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f4593f.m6987a(this.f4588a, (HistoryGroupEntity) this.f4589b);
                default:
            }
        }
    }

    /* renamed from: app.util.BubbleDialogUtil.9 */
    class C05049 extends ArrayList<String> {
        final /* synthetic */ Context f4594a;
        final /* synthetic */ BubbleDialogUtil f4595b;

        C05049(BubbleDialogUtil bubbleDialogUtil, Context context) {
            this.f4595b = bubbleDialogUtil;
            this.f4594a = context;
            add(this.f4594a.getResources().getString(2131296405));
            add(this.f4594a.getResources().getString(2131296449));
            add(this.f4594a.getResources().getString(2131296412));
        }
    }

    public enum DialogOption {
        COPY,
        FORWARD,
        SHARE,
        REPLY,
        DELETE,
        KICK,
        INFO
    }

    public static BubbleDialogUtil m6982a() {
        if (f4604a == null) {
            f4604a = new BubbleDialogUtil();
        }
        return f4604a;
    }

    public void m7002a(Context context, HistoryEntity historyEntity, String str, int i, int i2) {
        if (RecycleMessagingActivity.m5711O()) {
            boolean z = i == 2 && historyEntity.m4420g() == DeliveryStatus.RECEIVED && GroupManager.m7323a().m7366d().m4302n() == GroupElement.Type.moderated && GroupManager.m7323a().m7366d().m4304p().equals(AccountManager.m3934a().m3937c());
            switch (AnonymousClass19.f4563a[historyEntity.m4418e().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    m6986a(context, historyEntity, str, i, i2, z);
                    EventBus.m12779a().m12795d(new ReplyCancelerEvent());
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m6996b(context, historyEntity, str, i, i2, z);
                    EventBus.m12779a().m12795d(new ReplyCancelerEvent());
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m6998c(context, historyEntity, str, i, i2, z);
                default:
                    throw new IllegalArgumentException("Invalid Type");
            }
        }
    }

    private void m6986a(Context context, HistoryEntity historyEntity, String str, int i, int i2, boolean z) {
        Builder builder = new Builder(context, 2131558535);
        ArrayList c04951 = new C04951(this, context);
        ArrayList c04962 = new C04962(this);
        if (!(i == 3 || i == 4 || (historyEntity.m4420g() != DeliveryStatus.RECEIVED && historyEntity.m4420g() != DeliveryStatus.DELIVERED && historyEntity.m4420g() != DeliveryStatus.SENT))) {
            c04951.add(2, context.getResources().getString(2131296670));
            c04962.add(2, DialogOption.REPLY);
        }
        if (z) {
            c04951.add(context.getResources().getString(2131296482));
            c04962.add(DialogOption.KICK);
        }
        if (i == 4 || (i == 1 && ((HistoryNormalMessageEntity) historyEntity).m4457N())) {
            c04951.add(context.getResources().getString(2131296352));
            c04962.add(DialogOption.INFO);
        }
        builder.m1980a(context.getResources().getString(2131296392));
        builder.m1983a((CharSequence[]) c04951.toArray(new String[c04951.size()]), new C04983(this, c04962, context, historyEntity, i, str, i2));
        builder.m1992c();
    }

    private void m6996b(Context context, HistoryEntity historyEntity, String str, int i, int i2, boolean z) {
        Builder builder = new Builder(context, 2131558535);
        ArrayList c04994 = new C04994(this, context);
        ArrayList c05005 = new C05005(this);
        if (!(i == 3 || i == 4 || (historyEntity.m4420g() != DeliveryStatus.DELIVERED && historyEntity.m4420g() != DeliveryStatus.RECEIVED && historyEntity.m4420g() != DeliveryStatus.SENT))) {
            c04994.add(0, context.getResources().getString(2131296670));
            c05005.add(0, DialogOption.REPLY);
        }
        if (z) {
            c04994.add(context.getResources().getString(2131296482));
            c05005.add(DialogOption.KICK);
        }
        if (i == 4 || (i == 1 && ((HistoryNormalMessageEntity) historyEntity).m4457N())) {
            c04994.add(context.getResources().getString(2131296352));
            c05005.add(DialogOption.INFO);
        }
        builder.m1980a(context.getResources().getString(2131296392));
        builder.m1983a((CharSequence[]) c04994.toArray(new String[c04994.size()]), new C05016(this, c05005, historyEntity, str, i, context, i2));
        builder.m1992c();
    }

    private void m6998c(Context context, HistoryEntity historyEntity, String str, int i, int i2, boolean z) {
        Builder builder = new Builder(context, 2131558535);
        ArrayList c05027 = new C05027(this, context);
        if (z) {
            c05027.add(context.getResources().getString(2131296482));
        }
        builder.m1980a(context.getResources().getString(2131296392));
        builder.m1983a((CharSequence[]) c05027.toArray(new String[c05027.size()]), new C05038(this, context, historyEntity, str, i, i2));
        builder.m1992c();
    }

    public void m7003b(Context context, HistoryEntity historyEntity, String str, int i, int i2) {
        if (RecycleMessagingActivity.m5711O()) {
            EventBus.m12779a().m12795d(new ReplyCancelerEvent());
            boolean z = i == 2 && historyEntity.m4420g() == DeliveryStatus.RECEIVED && GroupManager.m7323a().m7366d().m4302n() == GroupElement.Type.moderated && GroupManager.m7323a().m7366d().m4304p().equals(AccountManager.m3934a().m3937c());
            if (historyEntity.m4418e() == Type.MAP) {
                m6999d(context, historyEntity, str, i, i2, z);
            } else if (historyEntity.m4420g().equals(DeliveryStatus.RECEIVED) || historyEntity.m4420g().equals(DeliveryStatus.DELIVERED) || historyEntity.m4420g().equals(DeliveryStatus.SENT)) {
                m7000e(context, historyEntity, str, i, i2, z);
            } else {
                m7001f(context, historyEntity, str, i, i2, z);
            }
        }
    }

    private void m6999d(Context context, HistoryEntity historyEntity, String str, int i, int i2, boolean z) {
        ArrayList c05049 = new C05049(this, context);
        ArrayList anonymousClass10 = new ArrayList<DialogOption>() {
            final /* synthetic */ BubbleDialogUtil f4531a;

            {
                this.f4531a = r2;
                add(DialogOption.COPY);
                add(DialogOption.FORWARD);
                add(DialogOption.DELETE);
            }
        };
        if (!(i == 3 || i == 4 || (historyEntity.m4420g() != DeliveryStatus.RECEIVED && historyEntity.m4420g() != DeliveryStatus.DELIVERED && historyEntity.m4420g() != DeliveryStatus.SENT))) {
            c05049.add(2, context.getResources().getString(2131296670));
            anonymousClass10.add(2, DialogOption.REPLY);
        }
        if (z) {
            c05049.add(context.getResources().getString(2131296482));
            anonymousClass10.add(DialogOption.KICK);
        }
        if (i == 4 || (i == 1 && ((HistoryNormalMessageEntity) historyEntity).m4457N())) {
            c05049.add(context.getResources().getString(2131296352));
            anonymousClass10.add(DialogOption.INFO);
        }
        Builder builder = new Builder(context, 2131558535);
        builder.m1980a(context.getResources().getString(2131296392));
        builder.m1983a((CharSequence[]) c05049.toArray(new String[c05049.size()]), new AnonymousClass11(this, anonymousClass10, context, historyEntity, i, str, i2));
        builder.m1992c();
    }

    private void m7000e(Context context, HistoryEntity historyEntity, String str, int i, int i2, boolean z) {
        ArrayList anonymousClass12 = new AnonymousClass12(this, context);
        ArrayList anonymousClass13 = new ArrayList<DialogOption>() {
            final /* synthetic */ BubbleDialogUtil f4542a;

            {
                this.f4542a = r2;
                add(DialogOption.SHARE);
                add(DialogOption.FORWARD);
                add(DialogOption.DELETE);
            }
        };
        if (!(i == 3 || i == 4 || (historyEntity.m4420g() != DeliveryStatus.RECEIVED && historyEntity.m4420g() != DeliveryStatus.DELIVERED && historyEntity.m4420g() != DeliveryStatus.SENT))) {
            anonymousClass12.add(2, context.getResources().getString(2131296670));
            anonymousClass13.add(2, DialogOption.REPLY);
        }
        if (z) {
            anonymousClass12.add(context.getResources().getString(2131296482));
            anonymousClass13.add(DialogOption.KICK);
        }
        if (i == 4 || (i == 1 && ((HistoryNormalMessageEntity) historyEntity).m4457N())) {
            anonymousClass12.add(context.getResources().getString(2131296352));
            anonymousClass13.add(DialogOption.INFO);
        }
        Builder builder = new Builder(context, 2131558535);
        builder.m1980a(context.getResources().getString(2131296392));
        builder.m1983a((CharSequence[]) anonymousClass12.toArray(new String[anonymousClass12.size()]), new AnonymousClass14(this, anonymousClass13, context, historyEntity, i, str, i2));
        builder.m1992c();
    }

    private void m7001f(Context context, HistoryEntity historyEntity, String str, int i, int i2, boolean z) {
        ArrayList anonymousClass15 = new AnonymousClass15(this, context);
        ArrayList anonymousClass16 = new ArrayList<DialogOption>() {
            final /* synthetic */ BubbleDialogUtil f4553a;

            {
                this.f4553a = r2;
                add(DialogOption.SHARE);
                add(DialogOption.DELETE);
            }
        };
        if (z) {
            anonymousClass15.add(context.getResources().getString(2131296482));
            anonymousClass16.add(DialogOption.KICK);
        }
        if (i == 4 || (i == 1 && ((HistoryNormalMessageEntity) historyEntity).m4457N())) {
            anonymousClass15.add(context.getResources().getString(2131296352));
            anonymousClass16.add(DialogOption.INFO);
        }
        Builder builder = new Builder(context, 2131558535);
        builder.m1980a(context.getResources().getString(2131296392));
        builder.m1983a((CharSequence[]) anonymousClass15.toArray(new String[anonymousClass15.size()]), new AnonymousClass17(this, anonymousClass16, context, historyEntity, str, i, i2));
        builder.m1992c();
    }

    private void m6985a(Context context, HistoryEntity historyEntity) {
        try {
            Parcelable fromFile;
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            if (historyEntity.m4418e() == Type.PHOTO) {
                fromFile = Uri.fromFile(new File(Utils.m7079a(historyEntity.m4430q(), Type.PHOTO)));
                intent.setType("image/*");
            } else if (historyEntity.m4418e() == Type.VIDEO) {
                fromFile = Uri.fromFile(new File(Utils.m7079a(historyEntity.m4430q(), Type.VIDEO)));
                intent.setType("video/*");
            } else if (historyEntity.m4418e() == Type.AUDIO) {
                fromFile = Uri.fromFile(new File(Utils.m7079a(historyEntity.m4430q(), Type.AUDIO)));
                intent.setType("audio/*");
            } else {
                throw new IllegalArgumentException("Invalid Type: " + historyEntity.m4418e());
            }
            intent.putExtra("android.intent.extra.STREAM", fromFile);
            context.startActivity(Intent.createChooser(intent, context.getResources().getString(2131296737)));
        } catch (StorageException e) {
            Storage.m6945c(context);
        }
    }

    private void m6997c(Context context, HistoryEntity historyEntity, String str, int i, int i2) {
        if (i == 1) {
            HistoryNormalMessageDataSource.m4725a(context, new String[]{historyEntity.m4412b()});
            ConversationNormalDataSource.m4623a().m4630a(historyEntity.m4414c());
        } else if (i == 4) {
            HistoryNormalMessageDataSource.m4720a().m4743a(new String[]{historyEntity.m4412b()});
            BroadcastListDataSource.m4504a().m4514b(NormalMessageManager.m7447a().m7473g());
        } else if (i == 3) {
            HistoryChannelDataSource.m4671a(context, new String[]{historyEntity.m4412b()});
        } else if (i == 2) {
            HistoryGroupDataSource.m4695a(context, new String[]{historyEntity.m4412b()});
            ConversationGroupDataSource.m4587a().m4614c(historyEntity.m4414c());
        }
        if (historyEntity.m4418e() == Type.PHOTO || historyEntity.m4418e() == Type.VIDEO) {
            Type type;
            if (historyEntity.m4418e() == Type.PHOTO) {
                type = Type.PHOTO;
            } else if (historyEntity.m4418e() == Type.VIDEO) {
                type = Type.VIDEO;
            } else if (historyEntity.m4418e() == Type.AUDIO) {
                type = Type.AUDIO;
            } else {
                throw new IllegalArgumentException("Invalid Type: " + historyEntity.m4418e());
            }
            try {
                File file = new File(Utils.m7079a(historyEntity.m4430q(), type));
                if (file.exists()) {
                    file.delete();
                }
            } catch (StorageException e) {
            }
        }
        EventBus.m12779a().m12795d(new ViewHolderDeleteEvent(i2));
    }

    private void m6987a(Context context, HistoryGroupEntity historyGroupEntity) {
        String string = (DisplayDataHandler.f4039a.get(historyGroupEntity.m4449M()) == null || ((ContactEntity) DisplayDataHandler.f4039a.get(historyGroupEntity.m4449M())).m4210m() != TYPE.LOCAL) ? (DisplayDataHandler.f4039a.get(historyGroupEntity.m4449M()) == null || ((ContactEntity) DisplayDataHandler.f4039a.get(historyGroupEntity.m4449M())).m4209l() == null) ? context.getString(2131296307) : ((ContactEntity) DisplayDataHandler.f4039a.get(historyGroupEntity.m4449M())).m4209l() : ((ContactEntity) DisplayDataHandler.f4039a.get(historyGroupEntity.m4449M())).m4196e();
        Builder a = new Builder(context, 2131558538).m1980a(context.getString(2131296482)).m1989c(2130837731).m1986b(String.format(context.getString(2131296422), new Object[]{string})).m1981a(context.getString(2131296786), new AnonymousClass18(this, historyGroupEntity));
        a.m1985b(2131296784, null);
        a.m1992c();
    }

    private String m6983a(HistoryNormalMessageEntity historyNormalMessageEntity) {
        if (historyNormalMessageEntity.m4457N()) {
            return historyNormalMessageEntity.m4412b().substring(0, historyNormalMessageEntity.m4412b().indexOf("_"));
        }
        return historyNormalMessageEntity.m4412b();
    }

    private void m6988a(Context context, String str, DeliveryStatus deliveryStatus) {
        Intent intent = new Intent(context, BroadcastMessageInfoActivity.class);
        intent.putExtra("message_id", str);
        intent.putExtra("message_delivery_status", deliveryStatus.ordinal());
        context.startActivity(intent);
    }

    private void m6989a(HistoryEntity historyEntity, String str, int i) {
        EventBus.m12779a().m12795d(new MessageReplyEvent(historyEntity, str, m6995a(historyEntity), i));
    }

    private boolean m6995a(HistoryEntity historyEntity) {
        if (historyEntity instanceof HistoryGroupEntity) {
            HistoryGroupEntity historyGroupEntity = (HistoryGroupEntity) historyEntity;
            if (historyGroupEntity.m4449M() == null || "".equals(historyGroupEntity.m4449M()) || historyGroupEntity.m4449M().contains("-") || historyGroupEntity.m4449M().equals(AccountManager.m3934a().m3937c())) {
                return true;
            }
            return false;
        } else if (historyEntity.m4420g() == null || historyEntity.m4420g() == DeliveryStatus.RECEIVED || historyEntity.m4420g() == DeliveryStatus.DRAFT) {
            return false;
        } else {
            return true;
        }
    }
}
