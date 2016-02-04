package com.nostra13.universalimageloader.core.download;

import java.io.InputStream;
import java.util.Locale;

public interface ImageDownloader {

    public enum Scheme {
        HTTP("http"),
        HTTPS("https"),
        FILE("file"),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN("");
        
        private String f7079h;
        private String f7080i;

        private Scheme(String str) {
            this.f7079h = str;
            this.f7080i = str + "://";
        }

        public static Scheme m11222a(String str) {
            if (str != null) {
                for (Scheme scheme : values()) {
                    if (scheme.m11223d(str)) {
                        return scheme;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean m11223d(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.f7080i);
        }

        public String m11224b(String str) {
            return this.f7080i + str;
        }

        public String m11225c(String str) {
            if (m11223d(str)) {
                return str.substring(this.f7080i.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.f7079h}));
        }
    }

    InputStream m11120a(String str, Object obj);
}
