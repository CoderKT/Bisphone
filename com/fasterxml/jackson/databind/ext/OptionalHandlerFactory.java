package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;

public class OptionalHandlerFactory implements Serializable {
    public static final OptionalHandlerFactory instance;

    static {
        instance = new OptionalHandlerFactory();
    }

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        Class rawClass = javaType.getRawClass();
        if (!rawClass.getName().startsWith("javax.xml.") && !hasSupertypeStartingWith(rawClass, "javax.xml.")) {
            return doesImplement(rawClass, "org.w3c.dom.Node") ? (JsonSerializer) instantiate("com.fasterxml.jackson.databind.ext.DOMSerializer") : null;
        } else {
            Object instantiate = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLSerializers");
            if (instantiate == null) {
                return null;
            }
            return ((Serializers) instantiate).findSerializer(serializationConfig, javaType, beanDescription);
        }
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        Class rawClass = javaType.getRawClass();
        if (rawClass.getName().startsWith("javax.xml.") || hasSupertypeStartingWith(rawClass, "javax.xml.")) {
            Object instantiate = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers");
            if (instantiate == null) {
                return null;
            }
            return ((Deserializers) instantiate).findBeanDeserializer(javaType, deserializationConfig, beanDescription);
        } else if (doesImplement(rawClass, "org.w3c.dom.Node")) {
            return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer");
        } else {
            return doesImplement(rawClass, "org.w3c.dom.Node") ? (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer") : null;
        }
    }

    private Object instantiate(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (LinkageError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private boolean doesImplement(Class<?> cls, String str) {
        while (cls != null) {
            if (cls.getName().equals(str) || hasInterface(cls, str)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    private boolean hasInterface(Class<?> cls, String str) {
        Class[] interfaces = cls.getInterfaces();
        for (Class name : interfaces) {
            if (name.getName().equals(str)) {
                return true;
            }
        }
        for (Class name2 : interfaces) {
            if (hasInterface(name2, str)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSupertypeStartingWith(Class<?> cls, String str) {
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            if (superclass.getName().startsWith(str)) {
                return true;
            }
        }
        while (cls != null) {
            if (hasInterfaceStartingWith(cls, str)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    private boolean hasInterfaceStartingWith(Class<?> cls, String str) {
        Class[] interfaces = cls.getInterfaces();
        for (Class name : interfaces) {
            if (name.getName().startsWith(str)) {
                return true;
            }
        }
        for (Class name2 : interfaces) {
            if (hasInterfaceStartingWith(name2, str)) {
                return true;
            }
        }
        return false;
    }
}
