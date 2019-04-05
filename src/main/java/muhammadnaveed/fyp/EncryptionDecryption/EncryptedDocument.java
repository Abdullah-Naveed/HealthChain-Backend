package muhammadnaveed.fyp.EncryptionDecryption;

public class EncryptedDocument {
    private String uuid;
    private String content;

    public EncryptedDocument(String uuid, String content) {
        this.uuid = uuid;
        this.content = content;
    }

    public EncryptedDocument() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
