package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FragmentController {
    private final FragmentHostCallback<?> f161a;

    public static final FragmentController m315a(FragmentHostCallback<?> fragmentHostCallback) {
        return new FragmentController(fragmentHostCallback);
    }

    private FragmentController(FragmentHostCallback<?> fragmentHostCallback) {
        this.f161a = fragmentHostCallback;
    }

    public FragmentManager m316a() {
        return this.f161a.m287i();
    }

    public LoaderManager m328b() {
        return this.f161a.m288j();
    }

    public int m331c() {
        List list = this.f161a.f140d.f184f;
        return list == null ? 0 : list.size();
    }

    public List<Fragment> m318a(List<Fragment> list) {
        if (this.f161a.f140d.f184f == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList(m331c());
        }
        list.addAll(this.f161a.f140d.f184f);
        return list;
    }

    public void m321a(Fragment fragment) {
        this.f161a.f140d.m390a(this.f161a, this.f161a, fragment);
    }

    public View m317a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f161a.f140d.m378a(view, str, context, attributeSet);
    }

    public void m332d() {
        this.f161a.f140d.m420k();
    }

    public Parcelable m333e() {
        return this.f161a.f140d.m419j();
    }

    public void m320a(Parcelable parcelable, List<Fragment> list) {
        this.f161a.f140d.m386a(parcelable, (List) list);
    }

    public List<Fragment> m334f() {
        return this.f161a.f140d.m418i();
    }

    public void m335g() {
        this.f161a.f140d.m421l();
    }

    public void m336h() {
        this.f161a.f140d.m422m();
    }

    public void m337i() {
        this.f161a.f140d.m423n();
    }

    public void m338j() {
        this.f161a.f140d.m424o();
    }

    public void m339k() {
        this.f161a.f140d.m425p();
    }

    public void m340l() {
        this.f161a.f140d.m426q();
    }

    public void m341m() {
        this.f161a.f140d.m427r();
    }

    public void m342n() {
        this.f161a.f140d.m429t();
    }

    public void m319a(Configuration configuration) {
        this.f161a.f140d.m384a(configuration);
    }

    public void m343o() {
        this.f161a.f140d.m430u();
    }

    public boolean m326a(Menu menu, MenuInflater menuInflater) {
        return this.f161a.f140d.m395a(menu, menuInflater);
    }

    public boolean m325a(Menu menu) {
        return this.f161a.f140d.m394a(menu);
    }

    public boolean m327a(MenuItem menuItem) {
        return this.f161a.f140d.m396a(menuItem);
    }

    public boolean m330b(MenuItem menuItem) {
        return this.f161a.f140d.m403b(menuItem);
    }

    public void m329b(Menu menu) {
        this.f161a.f140d.m401b(menu);
    }

    public boolean m344p() {
        return this.f161a.f140d.m416g();
    }

    public void m345q() {
        this.f161a.m290l();
    }

    public void m324a(boolean z) {
        this.f161a.m275a(z);
    }

    public void m346r() {
        this.f161a.m291m();
    }

    public void m347s() {
        this.f161a.m292n();
    }

    public SimpleArrayMap<String, LoaderManager> m348t() {
        return this.f161a.m293o();
    }

    public void m322a(SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
        this.f161a.m272a((SimpleArrayMap) simpleArrayMap);
    }

    public void m323a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f161a.m280b(str, fileDescriptor, printWriter, strArr);
    }
}
