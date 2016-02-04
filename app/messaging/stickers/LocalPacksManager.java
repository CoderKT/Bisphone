package app.messaging.stickers;

import android.content.Context;
import android.content.res.AssetManager;
import app.Main;
import app.common.entity.StickerEntity;
import app.common.entity.StickerPackEntity;
import app.database.datasource.StickerDataSource;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.FileUtil;
import app.util.Unzipper;
import java.io.File;

public class LocalPacksManager {
    public static final int[] f3895a;
    private static final int[] f3896b;
    private static final String[] f3897c;
    private static final String[] f3898d;

    /* renamed from: app.messaging.stickers.LocalPacksManager.1 */
    final class C03621 implements Runnable {
        final /* synthetic */ Context f3894a;

        C03621(Context context) {
            this.f3894a = context;
        }

        public void run() {
            for (int i = 0; i < 1; i++) {
                if (StickerDataSource.m4763c(LocalPacksManager.f3895a[i])) {
                    Main.f1926a.m5683d("validateLocalStickers, pack " + LocalPacksManager.f3895a[i] + " is available");
                } else if (LocalPacksManager.m6429b(this.f3894a, i)) {
                    Main.f1926a.m5681c("validateLocalStickers, pack " + LocalPacksManager.f3895a[i] + " successfully added");
                } else {
                    Main.f1926a.m5681c("validateLocalStickers, failed to add pack " + LocalPacksManager.f3895a[i]);
                }
            }
        }
    }

    static {
        f3895a = new int[]{100};
        f3896b = new int[]{24, 18};
        f3897c = new String[]{"Mr. Bald", "Worms"};
        f3898d = new String[]{"<layout><row><col w=\"1\"><sticker id=\"10001\"/></col><col w=\"1\"><sticker id=\"10002\"/></col></row><row><col w=\"1\"><sticker id=\"10003\"/></col><col w=\"1\"><sticker id=\"10004\"/></col><col w=\"1\"><sticker id=\"10005\"/></col></row><row><col w=\"1\"><sticker id=\"10006\"/></col><col w=\"1\"><sticker id=\"10007\"/></col></row><row><col w=\"1\"><sticker id=\"10008\"/></col><col w=\"1\"><sticker id=\"10009\"/></col><col w=\"1\"><sticker id=\"10010\"/></col></row><row><col w=\"1\"><sticker id=\"10011\"/></col><col w=\"1\"><sticker id=\"10012\"/></col><col w=\"1\"><sticker id=\"10013\"/></col></row><row><col w=\"1\"><sticker id=\"10014\"/></col><col w=\"1\"><sticker id=\"10015\"/></col><col w=\"1\"><sticker id=\"10016\"/></col></row><row><col w=\"1\"><sticker id=\"10017\"/></col><col w=\"1\"><sticker id=\"10018\"/></col><col w=\"1\"><sticker id=\"10019\"/></col></row><row><col w=\"1\"><sticker id=\"10020\"/></col><col w=\"1\"><sticker id=\"10021\"/></col></row><row><col w=\"1\"><sticker id=\"10022\"/></col><col w=\"1\"><sticker id=\"10023\"/></col><col w=\"1\"><sticker id=\"10024\"/></col></row></layout>", "<layout><row><col w=\"1\"><sticker id=\"10101\"/></col><col w=\"1\"><sticker id=\"10102\"/></col></row><row><col w=\"1\"><sticker id=\"10103\"/></col><col w=\"1\"><sticker id=\"10104\"/></col></row><row><col w=\"1\"><sticker id=\"10105\"/></col><col w=\"1\"><sticker id=\"10106\"/></col><col w=\"1\"><sticker id=\"10107\"/></col></row><row><col w=\"1\"><sticker id=\"10108\"/></col><col w=\"1\"><sticker id=\"10109\"/></col><col w=\"1\"><sticker id=\"10110\"/></col></row><row><col w=\"1\"><sticker id=\"10111\"/></col><col w=\"1\"><sticker id=\"10112\"/></col></row><row><col w=\"1\"><sticker id=\"10113\"/></col><col w=\"1\"><sticker id=\"10114\"/></col><col w=\"1\"><sticker id=\"10115\"/></col></row><row><col w=\"1\"><sticker id=\"10116\"/></col><col w=\"1\"><sub-row w=\"1\"><sticker id=\"10117\"/></sub-row><sub-row w=\"1\"><sticker id=\"10118\"/></sub-row></col></row></layout>"};
    }

    private static boolean m6429b(Context context, int i) {
        AssetManager assets = context.getResources().getAssets();
        try {
            String str = Storage.m6952g() + File.separator + "pack";
            String str2 = f3895a[i] + "_" + m6428b(context) + ".zip";
            Storage.m6937a(context);
            if (FileUtil.m7020a(assets, "stickers" + File.separator + str2, str)) {
                try {
                    boolean a = Unzipper.m7076a(new File(Storage.m6955i() + File.separator + f3895a[i]), str);
                    new File(str).delete();
                    if (a) {
                        for (int i2 = 1; i2 <= f3896b[i]; i2++) {
                            StickerDataSource.m4751a(new StickerEntity((f3895a[i] * 100) + i2, f3895a[i]));
                        }
                        StickerDataSource.m4752a(new StickerPackEntity(f3895a[i], f3897c[i], f3898d[i], false, true, i, true));
                        return true;
                    }
                } catch (StorageException e) {
                }
            }
            return false;
        } catch (StorageException e2) {
            return false;
        }
    }

    public static void m6426a(Context context) {
        new Thread(new C03621(context)).start();
    }

    public static int m6428b(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        switch (i) {
            case 120:
            case 160:
                return 10;
            case 213:
            case 240:
                return 15;
            case 320:
                return 20;
            case 400:
            case 480:
            case 560:
            case 640:
                return 30;
            default:
                if (i < 160) {
                    return 10;
                }
                if (i < 240) {
                    return 15;
                }
                if (i < 320) {
                    return 20;
                }
                return 30;
        }
    }
}
