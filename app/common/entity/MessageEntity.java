package app.common.entity;

public class MessageEntity {

    public enum DeliveryStatus {
        UPLOADING,
        FAILED_TO_UPLOAD,
        SENDING,
        FAILED_TO_SEND,
        SENT,
        DELIVERED,
        DRAFT,
        RECEIVED
    }
}
