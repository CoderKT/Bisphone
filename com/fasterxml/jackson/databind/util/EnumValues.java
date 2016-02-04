package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class EnumValues {
    private final Class<Enum<?>> _enumClass;
    private final EnumMap<?, SerializableString> _values;

    private EnumValues(Class<Enum<?>> cls, Map<Enum<?>, SerializableString> map) {
        this._enumClass = cls;
        this._values = new EnumMap(map);
    }

    public static EnumValues construct(SerializationConfig serializationConfig, Class<Enum<?>> cls) {
        if (serializationConfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            return constructFromToString(serializationConfig, cls);
        }
        return constructFromName(serializationConfig, cls);
    }

    public static EnumValues constructFromName(MapperConfig<?> mapperConfig, Class<Enum<?>> cls) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType((Class) cls).getEnumConstants();
        if (enumArr != null) {
            Map hashMap = new HashMap();
            for (Enum enumR : enumArr) {
                hashMap.put(enumR, mapperConfig.compileString(mapperConfig.getAnnotationIntrospector().findEnumValue(enumR)));
            }
            return new EnumValues(cls, hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public static EnumValues constructFromToString(MapperConfig<?> mapperConfig, Class<Enum<?>> cls) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType((Class) cls).getEnumConstants();
        if (enumArr != null) {
            Map hashMap = new HashMap();
            for (Enum enumR : enumArr) {
                hashMap.put(enumR, mapperConfig.compileString(enumR.toString()));
            }
            return new EnumValues(cls, hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public SerializableString serializedValueFor(Enum<?> enumR) {
        return (SerializableString) this._values.get(enumR);
    }
}
