package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.Fragment.SavedState;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager {

    public interface OnBackStackChangedListener {
        void m349a();
    }

    public abstract SavedState m350a(Fragment fragment);

    public abstract Fragment m351a(Bundle bundle, String str);

    public abstract Fragment m352a(String str);

    public abstract FragmentTransaction m353a();

    public abstract void m354a(int i, int i2);

    public abstract void m355a(Bundle bundle, String str, Fragment fragment);

    public abstract void m356a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean m357b();

    public abstract boolean m358c();

    public abstract List<Fragment> m359d();

    public abstract boolean m360e();
}
