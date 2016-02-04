package cz.msebera.android.httpclient.impl.client;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedirectLocations extends AbstractList<Object> {
    private final Set<URI> f7623a;
    private final List<URI> f7624b;

    public /* synthetic */ Object get(int i) {
        return m12156a(i);
    }

    public /* synthetic */ Object remove(int i) {
        return m12158b(i);
    }

    public RedirectLocations() {
        this.f7623a = new HashSet();
        this.f7624b = new ArrayList();
    }

    public boolean m12157a(URI uri) {
        return this.f7623a.contains(uri);
    }

    public void m12159b(URI uri) {
        this.f7623a.add(uri);
        this.f7624b.add(uri);
    }

    public URI m12156a(int i) {
        return (URI) this.f7624b.get(i);
    }

    public int size() {
        return this.f7624b.size();
    }

    public Object set(int i, Object obj) {
        URI uri = (URI) this.f7624b.set(i, (URI) obj);
        this.f7623a.remove(uri);
        this.f7623a.add((URI) obj);
        if (this.f7624b.size() != this.f7623a.size()) {
            this.f7623a.addAll(this.f7624b);
        }
        return uri;
    }

    public void add(int i, Object obj) {
        this.f7624b.add(i, (URI) obj);
        this.f7623a.add((URI) obj);
    }

    public URI m12158b(int i) {
        URI uri = (URI) this.f7624b.remove(i);
        this.f7623a.remove(uri);
        if (this.f7624b.size() != this.f7623a.size()) {
            this.f7623a.addAll(this.f7624b);
        }
        return uri;
    }

    public boolean contains(Object obj) {
        return this.f7623a.contains(obj);
    }
}
