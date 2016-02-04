package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.List;

public class LazyStringBuilder implements Appendable, CharSequence {
    static final /* synthetic */ boolean $assertionsDisabled;
    private String cache;
    private final List<CharSequence> list;

    static {
        $assertionsDisabled = !LazyStringBuilder.class.desiredAssertionStatus();
    }

    private void invalidateCache() {
        this.cache = null;
    }

    public LazyStringBuilder() {
        this.list = new ArrayList(20);
    }

    public LazyStringBuilder append(LazyStringBuilder lazyStringBuilder) {
        this.list.addAll(lazyStringBuilder.list);
        invalidateCache();
        return this;
    }

    public LazyStringBuilder append(CharSequence charSequence) {
        if ($assertionsDisabled || charSequence != null) {
            this.list.add(charSequence);
            invalidateCache();
            return this;
        }
        throw new AssertionError();
    }

    public LazyStringBuilder append(CharSequence charSequence, int i, int i2) {
        this.list.add(charSequence.subSequence(i, i2));
        invalidateCache();
        return this;
    }

    public LazyStringBuilder append(char c) {
        this.list.add(Character.toString(c));
        invalidateCache();
        return this;
    }

    public int length() {
        if (this.cache != null) {
            return this.cache.length();
        }
        int i = 0;
        for (CharSequence length : this.list) {
            i = length.length() + i;
        }
        return i;
    }

    public char charAt(int i) {
        if (this.cache != null) {
            return this.cache.charAt(i);
        }
        for (CharSequence charSequence : this.list) {
            if (i < charSequence.length()) {
                return charSequence.charAt(i);
            }
            i -= charSequence.length();
        }
        throw new IndexOutOfBoundsException();
    }

    public CharSequence subSequence(int i, int i2) {
        return toString().subSequence(i, i2);
    }

    public String toString() {
        if (this.cache == null) {
            StringBuilder stringBuilder = new StringBuilder(length());
            for (CharSequence append : this.list) {
                stringBuilder.append(append);
            }
            this.cache = stringBuilder.toString();
        }
        return this.cache;
    }
}
