package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.ActivityCompatApi23.RequestPermissionsRequestCodeValidator;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import se.emilsjolander.stickylistheaders.C1128R;

public class FragmentActivity extends BaseFragmentActivityHoneycomb implements OnRequestPermissionsResultCallback, RequestPermissionsRequestCodeValidator {
    final Handler f151a;
    final FragmentController f152b;
    boolean f153c;
    boolean f154d;
    boolean f155e;
    boolean f156f;
    boolean f157g;
    boolean f158h;
    boolean f159i;
    MediaControllerCompat f160j;

    /* renamed from: android.support.v4.app.FragmentActivity.1 */
    class C00071 extends Handler {
        final /* synthetic */ FragmentActivity f136a;

        C00071(FragmentActivity fragmentActivity) {
            this.f136a = fragmentActivity;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (this.f136a.f155e) {
                        this.f136a.m309a(false);
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    this.f136a.m311b();
                    this.f136a.f152b.m344p();
                default:
                    super.handleMessage(message);
            }
        }
    }

    class HostCallbacks extends FragmentHostCallback<FragmentActivity> {
        final /* synthetic */ FragmentActivity f147a;

        public HostCallbacks(FragmentActivity fragmentActivity) {
            this.f147a = fragmentActivity;
            super(fragmentActivity);
        }

        public void m296a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            this.f147a.dump(str, fileDescriptor, printWriter, strArr);
        }

        public boolean m298a(Fragment fragment) {
            return !this.f147a.isFinishing();
        }

        public LayoutInflater m299b() {
            return this.f147a.getLayoutInflater().cloneInContext(this.f147a);
        }

        public void m301c() {
            this.f147a.m313d();
        }

        public void m295a(Fragment fragment, Intent intent, int i) {
            this.f147a.m308a(fragment, intent, i);
        }

        public boolean m302d() {
            return this.f147a.getWindow() != null;
        }

        public int m303e() {
            Window window = this.f147a.getWindow();
            return window == null ? 0 : window.getAttributes().windowAnimations;
        }

        public void m300b(Fragment fragment) {
            this.f147a.m307a(fragment);
        }

        public View m294a(int i) {
            return this.f147a.findViewById(i);
        }

        public boolean m297a() {
            Window window = this.f147a.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    final class NonConfigurationInstances {
        Object f148a;
        List<Fragment> f149b;
        SimpleArrayMap<String, LoaderManager> f150c;

        NonConfigurationInstances() {
        }
    }

    public FragmentActivity() {
        this.f151a = new C00071(this);
        this.f152b = FragmentController.m315a(new HostCallbacks(this));
    }

    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f152b.m332d();
        int i3 = i >> 16;
        if (i3 != 0) {
            i3--;
            int c = this.f152b.m331c();
            if (c == 0 || i3 < 0 || i3 >= c) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.f152b.m318a(new ArrayList(c)).get(i3);
            if (fragment == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
                return;
            } else {
                fragment.m185a(InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.f152b.m316a().m358c()) {
            a_();
        }
    }

    public final void setSupportMediaController(MediaControllerCompat mediaControllerCompat) {
        this.f160j = mediaControllerCompat;
        if (VERSION.SDK_INT >= 21) {
            ActivityCompat21.m95a((Activity) this, mediaControllerCompat.m690a());
        }
    }

    public final MediaControllerCompat getSupportMediaController() {
        return this.f160j;
    }

    public void a_() {
        ActivityCompat.m101a((Activity) this);
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.m102a(this, sharedElementCallback);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback) {
        ActivityCompat.m104b(this, sharedElementCallback);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f152b.m319a(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.f152b.m321a(null);
        super.onCreate(bundle);
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            this.f152b.m322a(nonConfigurationInstances.f150c);
        }
        if (bundle != null) {
            List list;
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            FragmentController fragmentController = this.f152b;
            if (nonConfigurationInstances != null) {
                list = nonConfigurationInstances.f149b;
            } else {
                list = null;
            }
            fragmentController.m320a(parcelable, list);
        }
        this.f152b.m335g();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu) | this.f152b.m326a(menu, getMenuInflater());
        if (VERSION.SDK_INT >= 11) {
            return onCreatePanelMenu;
        }
        return true;
    }

    final View m306a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f152b.m317a(view, str, context, attributeSet);
    }

    protected void onDestroy() {
        super.onDestroy();
        m309a(false);
        this.f152b.m342n();
        this.f152b.m346r();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f152b.m343o();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return this.f152b.m327a(menuItem);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return this.f152b.m330b(menuItem);
            default:
                return false;
        }
    }

    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                this.f152b.m329b(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        super.onPause();
        this.f154d = false;
        if (this.f151a.hasMessages(2)) {
            this.f151a.removeMessages(2);
            m311b();
        }
        this.f152b.m339k();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f152b.m332d();
    }

    public void onStateNotSaved() {
        this.f152b.m332d();
    }

    protected void onResume() {
        super.onResume();
        this.f151a.sendEmptyMessage(2);
        this.f154d = true;
        this.f152b.m344p();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.f151a.removeMessages(2);
        m311b();
        this.f152b.m344p();
    }

    protected void m311b() {
        this.f152b.m338j();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.f158h) {
            this.f158h = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return m310a(view, menu) | this.f152b.m325a(menu);
    }

    protected boolean m310a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public final Object onRetainNonConfigurationInstance() {
        if (this.f155e) {
            m309a(true);
        }
        Object c = m312c();
        List f = this.f152b.m334f();
        SimpleArrayMap t = this.f152b.m348t();
        if (f == null && t == null && c == null) {
            return null;
        }
        Object nonConfigurationInstances = new NonConfigurationInstances();
        nonConfigurationInstances.f148a = c;
        nonConfigurationInstances.f149b = f;
        nonConfigurationInstances.f150c = t;
        return nonConfigurationInstances;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable e = this.f152b.m333e();
        if (e != null) {
            bundle.putParcelable("android:support:fragments", e);
        }
    }

    protected void onStart() {
        super.onStart();
        this.f155e = false;
        this.f156f = false;
        this.f151a.removeMessages(1);
        if (!this.f153c) {
            this.f153c = true;
            this.f152b.m336h();
        }
        this.f152b.m332d();
        this.f152b.m344p();
        this.f152b.m345q();
        this.f152b.m337i();
        this.f152b.m347s();
    }

    protected void onStop() {
        super.onStop();
        this.f155e = true;
        this.f151a.sendEmptyMessage(1);
        this.f152b.m340l();
    }

    public Object m312c() {
        return null;
    }

    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
        return nonConfigurationInstances != null ? nonConfigurationInstances.f148a : null;
    }

    public void m313d() {
        if (VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.m106a(this);
        } else {
            this.f158h = true;
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        if (VERSION.SDK_INT >= 11) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.f153c);
            printWriter.print("mResumed=");
            printWriter.print(this.f154d);
            printWriter.print(" mStopped=");
            printWriter.print(this.f155e);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.f156f);
            this.f152b.m323a(str2, fileDescriptor, printWriter, strArr);
            this.f152b.m316a().m356a(str, fileDescriptor, printWriter, strArr);
            printWriter.print(str);
            printWriter.println("View Hierarchy:");
            m305a(str + "  ", printWriter, getWindow().getDecorView());
        } else {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.f153c);
            printWriter.print("mResumed=");
            printWriter.print(this.f154d);
            printWriter.print(" mStopped=");
            printWriter.print(this.f155e);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.f156f);
            this.f152b.m323a(str2, fileDescriptor, printWriter, strArr);
            this.f152b.m316a().m356a(str, fileDescriptor, printWriter, strArr);
            printWriter.print(str);
            printWriter.println("View Hierarchy:");
            m305a(str + "  ", printWriter, getWindow().getDecorView());
        }
    }

    private static String m304a(View view) {
        char c;
        char c2 = 'F';
        char c3 = '.';
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(view.getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringBuilder.append(' ');
        switch (view.getVisibility()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                stringBuilder.append('V');
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                stringBuilder.append('I');
                break;
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        if (view.isFocusable()) {
            c = 'F';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isEnabled()) {
            c = 'E';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(view.willNotDraw() ? '.' : 'D');
        if (view.isHorizontalScrollBarEnabled()) {
            c = 'H';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isVerticalScrollBarEnabled()) {
            c = 'V';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isClickable()) {
            c = 'C';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isLongClickable()) {
            c = 'L';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(' ');
        if (!view.isFocused()) {
            c2 = '.';
        }
        stringBuilder.append(c2);
        if (view.isSelected()) {
            c = 'S';
        } else {
            c = '.';
        }
        stringBuilder.append(c);
        if (view.isPressed()) {
            c3 = 'P';
        }
        stringBuilder.append(c3);
        stringBuilder.append(' ');
        stringBuilder.append(view.getLeft());
        stringBuilder.append(',');
        stringBuilder.append(view.getTop());
        stringBuilder.append('-');
        stringBuilder.append(view.getRight());
        stringBuilder.append(',');
        stringBuilder.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            stringBuilder.append(" #");
            stringBuilder.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                String str;
                switch (-16777216 & id) {
                    case 16777216:
                        str = "android";
                        break;
                    case 2130706432:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(":");
                stringBuilder.append(resourceTypeName);
                stringBuilder.append("/");
                stringBuilder.append(resourceEntryName);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void m305a(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(m304a(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < childCount; i++) {
                    m305a(str2, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    void m309a(boolean z) {
        if (!this.f156f) {
            this.f156f = true;
            this.f157g = z;
            this.f151a.removeMessages(1);
            m314e();
        }
    }

    void m314e() {
        this.f152b.m324a(this.f157g);
        this.f152b.m341m();
    }

    public void m307a(Fragment fragment) {
    }

    public FragmentManager getSupportFragmentManager() {
        return this.f152b.m316a();
    }

    public LoaderManager getSupportLoaderManager() {
        return this.f152b.m328b();
    }

    public void startActivityForResult(Intent intent, int i) {
        if (i == -1 || (-65536 & i) == 0) {
            super.startActivityForResult(intent, i);
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }

    public final void b_(int i) {
        if (this.f159i) {
            this.f159i = false;
        } else if ((i & -256) != 0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        int i2 = (i >> 8) & 255;
        if (i2 != 0) {
            i2--;
            int c = this.f152b.m331c();
            if (c == 0 || i2 < 0 || i2 >= c) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.f152b.m318a(new ArrayList(c)).get(i2);
            if (fragment == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
            } else {
                fragment.m187a(i & 255, strArr, iArr);
            }
        }
    }

    public void m308a(Fragment fragment, Intent intent, int i) {
        if (i == -1) {
            super.startActivityForResult(intent, -1);
        } else if ((-65536 & i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        } else {
            super.startActivityForResult(intent, ((fragment.f114p + 1) << 16) + (InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i));
        }
    }
}
