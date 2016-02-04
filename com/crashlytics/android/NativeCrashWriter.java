package com.crashlytics.android;

import com.crashlytics.android.internal.models.BinaryImageData;
import com.crashlytics.android.internal.models.CustomAttributeData;
import com.crashlytics.android.internal.models.SessionEventData;
import com.crashlytics.android.internal.models.SignalData;
import com.crashlytics.android.internal.models.ThreadData;
import com.crashlytics.android.internal.models.ThreadData.FrameData;

class NativeCrashWriter {
    private static final SignalData f5428a;
    private static final ProtobufMessage[] f5429b;
    private static final ThreadMessage[] f5430c;
    private static final FrameMessage[] f5431d;
    private static final BinaryImageMessage[] f5432e;
    private static final CustomAttributeMessage[] f5433f;

    abstract class ProtobufMessage {
        private final int f5407a;
        private final ProtobufMessage[] f5408b;

        public ProtobufMessage(int i, ProtobufMessage... protobufMessageArr) {
            this.f5407a = i;
            if (protobufMessageArr == null) {
                protobufMessageArr = NativeCrashWriter.f5429b;
            }
            this.f5408b = protobufMessageArr;
        }

        public int m8014b() {
            int c = m8016c();
            return (c + CodedOutputStream.m7799l(c)) + CodedOutputStream.m7798j(this.f5407a);
        }

        public int m8016c() {
            int a = m8012a();
            for (ProtobufMessage b : this.f5408b) {
                a += b.m8014b();
            }
            return a;
        }

        public void m8015b(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7826i(this.f5407a, 2);
            codedOutputStream.m7827k(m8016c());
            m8013a(codedOutputStream);
            for (ProtobufMessage b : this.f5408b) {
                b.m8015b(codedOutputStream);
            }
        }

        public int m8012a() {
            return 0;
        }

        public void m8013a(CodedOutputStream codedOutputStream) {
        }
    }

    final class ApplicationMessage extends ProtobufMessage {
        public ApplicationMessage(ExecutionMessage executionMessage, RepeatedMessage repeatedMessage) {
            super(3, executionMessage, repeatedMessage);
        }
    }

    final class BinaryImageMessage extends ProtobufMessage {
        private final long f5409a;
        private final long f5410b;
        private final String f5411c;
        private final String f5412d;

        public BinaryImageMessage(BinaryImageData binaryImageData) {
            super(4, new ProtobufMessage[0]);
            this.f5409a = binaryImageData.f5541a;
            this.f5410b = binaryImageData.f5542b;
            this.f5411c = binaryImageData.f5543c;
            this.f5412d = binaryImageData.f5544d;
        }

        public int m8017a() {
            int b = CodedOutputStream.m7783b(1, this.f5409a);
            return ((b + CodedOutputStream.m7784b(3, ByteString.m7767a(this.f5411c))) + CodedOutputStream.m7783b(2, this.f5410b)) + CodedOutputStream.m7784b(4, ByteString.m7767a(this.f5412d));
        }

        public void m8018a(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7806a(1, this.f5409a);
            codedOutputStream.m7806a(2, this.f5410b);
            codedOutputStream.m7807a(3, ByteString.m7767a(this.f5411c));
            codedOutputStream.m7807a(4, ByteString.m7767a(this.f5412d));
        }
    }

    final class CustomAttributeMessage extends ProtobufMessage {
        private final String f5413a;
        private final String f5414b;

        public CustomAttributeMessage(CustomAttributeData customAttributeData) {
            super(2, new ProtobufMessage[0]);
            this.f5413a = customAttributeData.f5545a;
            this.f5414b = customAttributeData.f5546b;
        }

        public int m8019a() {
            return CodedOutputStream.m7784b(2, ByteString.m7767a(this.f5414b == null ? "" : this.f5414b)) + CodedOutputStream.m7784b(1, ByteString.m7767a(this.f5413a));
        }

        public void m8020a(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7807a(1, ByteString.m7767a(this.f5413a));
            codedOutputStream.m7807a(2, ByteString.m7767a(this.f5414b == null ? "" : this.f5414b));
        }
    }

    final class DeviceMessage extends ProtobufMessage {
        public DeviceMessage() {
            super(5, new ProtobufMessage[0]);
        }

        public int m8021a() {
            return (((((CodedOutputStream.m7782b(1, 0.0f) + 0) + CodedOutputStream.m7791e(2, 0)) + CodedOutputStream.m7785b(3, false)) + CodedOutputStream.m7793f(4, 0)) + CodedOutputStream.m7783b(5, 0)) + CodedOutputStream.m7783b(6, 0);
        }

        public void m8022a(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7804a(1, 0.0f);
            codedOutputStream.m7805a(2, 0);
            codedOutputStream.m7809a(3, false);
            codedOutputStream.m7818b(4, 0);
            codedOutputStream.m7806a(5, 0);
            codedOutputStream.m7806a(6, 0);
        }
    }

    final class EventMessage extends ProtobufMessage {
        private final long f5415a;
        private final String f5416b;

        public EventMessage(long j, String str, ApplicationMessage applicationMessage, DeviceMessage deviceMessage) {
            super(10, applicationMessage, deviceMessage);
            this.f5415a = j;
            this.f5416b = str;
        }

        public int m8023a() {
            return CodedOutputStream.m7783b(1, this.f5415a) + CodedOutputStream.m7784b(2, ByteString.m7767a(this.f5416b));
        }

        public void m8024a(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7806a(1, this.f5415a);
            codedOutputStream.m7807a(2, ByteString.m7767a(this.f5416b));
        }
    }

    final class ExecutionMessage extends ProtobufMessage {
        public ExecutionMessage(SignalMessage signalMessage, RepeatedMessage repeatedMessage, RepeatedMessage repeatedMessage2) {
            super(1, repeatedMessage, signalMessage, repeatedMessage2);
        }
    }

    final class FrameMessage extends ProtobufMessage {
        private final long f5417a;
        private final String f5418b;
        private final String f5419c;
        private final long f5420d;
        private final int f5421e;

        public FrameMessage(FrameData frameData) {
            super(3, new ProtobufMessage[0]);
            this.f5417a = frameData.f5555a;
            this.f5418b = frameData.f5556b;
            this.f5419c = frameData.f5557c;
            this.f5420d = frameData.f5558d;
            this.f5421e = frameData.f5559e;
        }

        public int m8025a() {
            return (((CodedOutputStream.m7783b(1, this.f5417a) + CodedOutputStream.m7784b(2, ByteString.m7767a(this.f5418b))) + CodedOutputStream.m7784b(3, ByteString.m7767a(this.f5419c))) + CodedOutputStream.m7783b(4, this.f5420d)) + CodedOutputStream.m7793f(5, this.f5421e);
        }

        public void m8026a(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7806a(1, this.f5417a);
            codedOutputStream.m7807a(2, ByteString.m7767a(this.f5418b));
            codedOutputStream.m7807a(3, ByteString.m7767a(this.f5419c));
            codedOutputStream.m7806a(4, this.f5420d);
            codedOutputStream.m7818b(5, this.f5421e);
        }
    }

    final class RepeatedMessage extends ProtobufMessage {
        private final ProtobufMessage[] f5422a;

        public RepeatedMessage(ProtobufMessage... protobufMessageArr) {
            super(0, new ProtobufMessage[0]);
            this.f5422a = protobufMessageArr;
        }

        public void m8028b(CodedOutputStream codedOutputStream) {
            for (ProtobufMessage b : this.f5422a) {
                b.m8015b(codedOutputStream);
            }
        }

        public int m8027b() {
            int i = 0;
            ProtobufMessage[] protobufMessageArr = this.f5422a;
            int i2 = 0;
            while (i < protobufMessageArr.length) {
                i2 += protobufMessageArr[i].m8014b();
                i++;
            }
            return i2;
        }
    }

    final class SignalMessage extends ProtobufMessage {
        private final String f5423a;
        private final String f5424b;
        private final long f5425c;

        public SignalMessage(SignalData signalData) {
            super(3, new ProtobufMessage[0]);
            this.f5423a = signalData.f5552a;
            this.f5424b = signalData.f5553b;
            this.f5425c = signalData.f5554c;
        }

        public int m8029a() {
            return (CodedOutputStream.m7784b(1, ByteString.m7767a(this.f5423a)) + CodedOutputStream.m7784b(2, ByteString.m7767a(this.f5424b))) + CodedOutputStream.m7783b(3, this.f5425c);
        }

        public void m8030a(CodedOutputStream codedOutputStream) {
            codedOutputStream.m7807a(1, ByteString.m7767a(this.f5423a));
            codedOutputStream.m7807a(2, ByteString.m7767a(this.f5424b));
            codedOutputStream.m7806a(3, this.f5425c);
        }
    }

    final class ThreadMessage extends ProtobufMessage {
        private final String f5426a;
        private final int f5427b;

        public ThreadMessage(ThreadData threadData, RepeatedMessage repeatedMessage) {
            super(1, repeatedMessage);
            this.f5426a = threadData.f5560a;
            this.f5427b = threadData.f5561b;
        }

        public int m8032a() {
            return (m8031d() ? CodedOutputStream.m7784b(1, ByteString.m7767a(this.f5426a)) : 0) + CodedOutputStream.m7793f(2, this.f5427b);
        }

        public void m8033a(CodedOutputStream codedOutputStream) {
            if (m8031d()) {
                codedOutputStream.m7807a(1, ByteString.m7767a(this.f5426a));
            }
            codedOutputStream.m7818b(2, this.f5427b);
        }

        private boolean m8031d() {
            return this.f5426a != null && this.f5426a.length() > 0;
        }
    }

    static {
        f5428a = new SignalData("", "", 0);
        f5429b = new ProtobufMessage[0];
        f5430c = new ThreadMessage[0];
        f5431d = new FrameMessage[0];
        f5432e = new BinaryImageMessage[0];
        f5433f = new CustomAttributeMessage[0];
    }

    private static EventMessage m8034a(SessionEventData sessionEventData) {
        return new EventMessage(sessionEventData.f5547a, "ndk-crash", new ApplicationMessage(new ExecutionMessage(new SignalMessage(sessionEventData.f5548b != null ? sessionEventData.f5548b : f5428a), m8038a(sessionEventData.f5549c), m8035a(sessionEventData.f5550d)), m8036a(sessionEventData.f5551e)), new DeviceMessage());
    }

    private static RepeatedMessage m8038a(ThreadData[] threadDataArr) {
        ProtobufMessage[] protobufMessageArr = threadDataArr != null ? new ThreadMessage[threadDataArr.length] : f5430c;
        for (int i = 0; i < protobufMessageArr.length; i++) {
            ThreadData threadData = threadDataArr[i];
            protobufMessageArr[i] = new ThreadMessage(threadData, m8037a(threadData.f5562c));
        }
        return new RepeatedMessage(protobufMessageArr);
    }

    private static RepeatedMessage m8037a(FrameData[] frameDataArr) {
        ProtobufMessage[] protobufMessageArr = frameDataArr != null ? new FrameMessage[frameDataArr.length] : f5431d;
        for (int i = 0; i < protobufMessageArr.length; i++) {
            protobufMessageArr[i] = new FrameMessage(frameDataArr[i]);
        }
        return new RepeatedMessage(protobufMessageArr);
    }

    private static RepeatedMessage m8035a(BinaryImageData[] binaryImageDataArr) {
        ProtobufMessage[] protobufMessageArr = binaryImageDataArr != null ? new BinaryImageMessage[binaryImageDataArr.length] : f5432e;
        for (int i = 0; i < protobufMessageArr.length; i++) {
            protobufMessageArr[i] = new BinaryImageMessage(binaryImageDataArr[i]);
        }
        return new RepeatedMessage(protobufMessageArr);
    }

    private static RepeatedMessage m8036a(CustomAttributeData[] customAttributeDataArr) {
        ProtobufMessage[] protobufMessageArr = customAttributeDataArr != null ? new CustomAttributeMessage[customAttributeDataArr.length] : f5433f;
        for (int i = 0; i < protobufMessageArr.length; i++) {
            protobufMessageArr[i] = new CustomAttributeMessage(customAttributeDataArr[i]);
        }
        return new RepeatedMessage(protobufMessageArr);
    }

    public static void m8039a(SessionEventData sessionEventData, CodedOutputStream codedOutputStream) {
        m8034a(sessionEventData).m8015b(codedOutputStream);
    }
}
