package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;

public abstract class LoaderManager {

    public interface LoaderCallbacks<D> {
        Loader<D> m480a(int i, Bundle bundle);

        void m481a(Loader<D> loader);

        void m482a(Loader<D> loader, D d);
    }

    public boolean m483a() {
        return false;
    }
}
