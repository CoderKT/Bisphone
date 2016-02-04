package android.support.v7.widget;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.ItemAnimator.ItemHolderInfo;
import android.support.v7.widget.RecyclerView.ViewHolder;

class ViewInfoStore {
    final ArrayMap<ViewHolder, InfoRecord> f1912a;
    final LongSparseArray<ViewHolder> f1913b;

    interface ProcessCallback {
        void m3300a(ViewHolder viewHolder);

        void m3301a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        void m3302b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);

        void m3303c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2);
    }

    class InfoRecord {
        static Pool<InfoRecord> f1908d;
        int f1909a;
        ItemHolderInfo f1910b;
        ItemHolderInfo f1911c;

        static {
            f1908d = new SimplePool(20);
        }

        private InfoRecord() {
        }

        static InfoRecord m3881a() {
            InfoRecord infoRecord = (InfoRecord) f1908d.m784a();
            return infoRecord == null ? new InfoRecord() : infoRecord;
        }

        static void m3882a(InfoRecord infoRecord) {
            infoRecord.f1909a = 0;
            infoRecord.f1910b = null;
            infoRecord.f1911c = null;
            f1908d.m785a(infoRecord);
        }

        static void m3883b() {
            do {
            } while (f1908d.m784a() != null);
        }
    }

    ViewInfoStore() {
        this.f1912a = new ArrayMap();
        this.f1913b = new LongSparseArray();
    }

    void m3886a() {
        this.f1912a.clear();
        this.f1913b.m774c();
    }

    void m3888a(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.f1912a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.m3881a();
            this.f1912a.put(viewHolder, infoRecord);
        }
        infoRecord.f1910b = itemHolderInfo;
        infoRecord.f1909a |= 4;
    }

    ItemHolderInfo m3884a(ViewHolder viewHolder) {
        int a = this.f1912a.m747a((Object) viewHolder);
        if (a < 0) {
            return null;
        }
        InfoRecord infoRecord = (InfoRecord) this.f1912a.m753c(a);
        if (infoRecord == null || (infoRecord.f1909a & 4) == 0) {
            return null;
        }
        infoRecord.f1909a &= -5;
        ItemHolderInfo itemHolderInfo = infoRecord.f1910b;
        if (infoRecord.f1909a == 0) {
            this.f1912a.m754d(a);
            InfoRecord.m3882a(infoRecord);
        }
        return itemHolderInfo;
    }

    void m3887a(long j, ViewHolder viewHolder) {
        this.f1913b.m772b(j, viewHolder);
    }

    void m3891b(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.f1912a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.m3881a();
            this.f1912a.put(viewHolder, infoRecord);
        }
        infoRecord.f1909a |= 2;
        infoRecord.f1910b = itemHolderInfo;
    }

    boolean m3892b(ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f1912a.get(viewHolder);
        return (infoRecord == null || (infoRecord.f1909a & 4) == 0) ? false : true;
    }

    ViewHolder m3885a(long j) {
        return (ViewHolder) this.f1913b.m767a(j);
    }

    void m3894c(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.f1912a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.m3881a();
            this.f1912a.put(viewHolder, infoRecord);
        }
        infoRecord.f1911c = itemHolderInfo;
        infoRecord.f1909a |= 8;
    }

    void m3893c(ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f1912a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.m3881a();
            this.f1912a.put(viewHolder, infoRecord);
        }
        r0.f1909a |= 1;
    }

    void m3895d(ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f1912a.get(viewHolder);
        if (infoRecord != null) {
            infoRecord.f1909a &= -2;
        }
    }

    void m3889a(ProcessCallback processCallback) {
        for (int size = this.f1912a.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) this.f1912a.m752b(size);
            InfoRecord infoRecord = (InfoRecord) this.f1912a.m754d(size);
            if ((infoRecord.f1909a & 3) == 3) {
                processCallback.m3300a(viewHolder);
            } else if ((infoRecord.f1909a & 1) != 0) {
                processCallback.m3301a(viewHolder, infoRecord.f1910b, infoRecord.f1911c);
            } else if ((infoRecord.f1909a & 14) == 14) {
                processCallback.m3302b(viewHolder, infoRecord.f1910b, infoRecord.f1911c);
            } else if ((infoRecord.f1909a & 12) == 12) {
                processCallback.m3303c(viewHolder, infoRecord.f1910b, infoRecord.f1911c);
            } else if ((infoRecord.f1909a & 4) != 0) {
                processCallback.m3301a(viewHolder, infoRecord.f1910b, null);
            } else if ((infoRecord.f1909a & 8) != 0) {
                processCallback.m3302b(viewHolder, infoRecord.f1910b, infoRecord.f1911c);
            } else if ((infoRecord.f1909a & 2) != 0) {
            }
            InfoRecord.m3882a(infoRecord);
        }
    }

    void m3896e(ViewHolder viewHolder) {
        for (int b = this.f1913b.m770b() - 1; b >= 0; b--) {
            if (viewHolder == this.f1913b.m773c(b)) {
                this.f1913b.m769a(b);
                break;
            }
        }
        InfoRecord infoRecord = (InfoRecord) this.f1912a.remove(viewHolder);
        if (infoRecord != null) {
            InfoRecord.m3882a(infoRecord);
        }
    }

    void m3890b() {
        InfoRecord.m3883b();
    }
}
