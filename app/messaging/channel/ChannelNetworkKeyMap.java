package app.messaging.channel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChannelNetworkKeyMap {
    public static Object m6120a(Class<?> cls, String str) {
        return new ObjectMapper().readValue(str, cls);
    }
}
