package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.net.InetAddress;

public class InetAddressSerializer extends StdScalarSerializer<InetAddress> {
    public InetAddressSerializer() {
        super(InetAddress.class);
    }

    public void serialize(InetAddress inetAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        String trim = inetAddress.toString().trim();
        int indexOf = trim.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                trim = trim.substring(1);
            } else {
                trim = trim.substring(0, indexOf);
            }
        }
        jsonGenerator.writeString(trim);
    }

    public void serializeWithType(InetAddress inetAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForScalar(inetAddress, jsonGenerator, InetAddress.class);
        serialize(inetAddress, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(inetAddress, jsonGenerator);
    }
}
