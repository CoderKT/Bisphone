package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata implements Serializable {
    public static final PropertyMetadata STD_OPTIONAL;
    public static final PropertyMetadata STD_REQUIRED;
    public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL;
    protected final String _description;
    protected final Integer _index;
    protected final Boolean _required;

    static {
        STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null, null);
        STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null, null);
        STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null, null);
    }

    protected PropertyMetadata(Boolean bool, String str, Integer num) {
        this._required = bool;
        this._description = str;
        this._index = num;
    }

    public static PropertyMetadata construct(boolean z, String str, Integer num) {
        PropertyMetadata propertyMetadata = z ? STD_REQUIRED : STD_OPTIONAL;
        if (str != null) {
            propertyMetadata = propertyMetadata.withDescription(str);
        }
        if (num != null) {
            return propertyMetadata.withIndex(num);
        }
        return propertyMetadata;
    }

    public PropertyMetadata withDescription(String str) {
        return new PropertyMetadata(this._required, str, this._index);
    }

    public PropertyMetadata withIndex(Integer num) {
        return new PropertyMetadata(this._required, this._description, num);
    }
}
