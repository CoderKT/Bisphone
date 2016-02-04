package com.fasterxml.jackson.databind.util;

public abstract class NameTransformer {
    public static final NameTransformer NOP;

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer.1 */
    final class C06431 extends NameTransformer {
        C06431() {
        }

        public String transform(String str) {
            return str;
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer.2 */
    final class C06442 extends NameTransformer {
        final /* synthetic */ String val$prefix;
        final /* synthetic */ String val$suffix;

        C06442(String str, String str2) {
            this.val$prefix = str;
            this.val$suffix = str2;
        }

        public String transform(String str) {
            return this.val$prefix + str + this.val$suffix;
        }

        public String toString() {
            return "[PreAndSuffixTransformer('" + this.val$prefix + "','" + this.val$suffix + "')]";
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer.3 */
    final class C06453 extends NameTransformer {
        final /* synthetic */ String val$prefix;

        C06453(String str) {
            this.val$prefix = str;
        }

        public String transform(String str) {
            return this.val$prefix + str;
        }

        public String toString() {
            return "[PrefixTransformer('" + this.val$prefix + "')]";
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer.4 */
    final class C06464 extends NameTransformer {
        final /* synthetic */ String val$suffix;

        C06464(String str) {
            this.val$suffix = str;
        }

        public String transform(String str) {
            return str + this.val$suffix;
        }

        public String toString() {
            return "[SuffixTransformer('" + this.val$suffix + "')]";
        }
    }

    public class Chained extends NameTransformer {
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }

        public String toString() {
            return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
        }
    }

    public abstract String transform(String str);

    static {
        NOP = new C06431();
    }

    protected NameTransformer() {
    }

    public static NameTransformer simpleTransformer(String str, String str2) {
        Object obj = 1;
        Object obj2 = (str == null || str.length() <= 0) ? null : 1;
        if (str2 == null || str2.length() <= 0) {
            obj = null;
        }
        if (obj2 == null) {
            return obj != null ? new C06464(str2) : NOP;
        } else {
            if (obj != null) {
                return new C06442(str, str2);
            }
            return new C06453(str);
        }
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }
}
