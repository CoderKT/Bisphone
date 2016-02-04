package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class BeanUtil {
    public static String okNameForGetter(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, name);
        if (okNameForIsGetter == null) {
            return okNameForRegularGetter(annotatedMethod, name);
        }
        return okNameForIsGetter;
    }

    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        if (!str.startsWith("get")) {
            return null;
        }
        if ("getCallbacks".equals(str)) {
            if (isCglibGetCallbacks(annotatedMethod)) {
                return null;
            }
        } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
            return null;
        }
        return manglePropertyName(str.substring(3));
    }

    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        if (!str.startsWith("is")) {
            return null;
        }
        Class rawType = annotatedMethod.getRawType();
        if (rawType == Boolean.class || rawType == Boolean.TYPE) {
            return manglePropertyName(str.substring(2));
        }
        return null;
    }

    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str) {
        String name = annotatedMethod.getName();
        if (name.startsWith(str)) {
            return manglePropertyName(name.substring(str.length()));
        }
        return null;
    }

    protected static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        Class rawType = annotatedMethod.getRawType();
        if (rawType == null || !rawType.isArray()) {
            return false;
        }
        Package packageR = rawType.getComponentType().getPackage();
        if (packageR == null) {
            return false;
        }
        String name = packageR.getName();
        if (name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib")) {
            return true;
        }
        return false;
    }

    protected static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        Class rawType = annotatedMethod.getRawType();
        if (rawType == null || rawType.isArray()) {
            return false;
        }
        Package packageR = rawType.getPackage();
        if (packageR == null || !packageR.getName().startsWith("groovy.lang")) {
            return false;
        }
        return true;
    }

    protected static String manglePropertyName(String str) {
        StringBuilder stringBuilder = null;
        int length = str.length();
        if (length == 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            char toLowerCase = Character.toLowerCase(charAt);
            if (charAt == toLowerCase) {
                break;
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder(str);
            }
            stringBuilder.setCharAt(i, toLowerCase);
        }
        if (stringBuilder != null) {
            return stringBuilder.toString();
        }
        return str;
    }
}
