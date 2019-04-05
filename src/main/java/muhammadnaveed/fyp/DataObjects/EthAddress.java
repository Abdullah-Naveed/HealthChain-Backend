package muhammadnaveed.fyp.DataObjects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "EthAddresses")
public class EthAddress {

    @Id
    private String ethAddress;
    @Field
    private boolean beingUsed;
    private String metaName;

    public EthAddress(String ethAddress, boolean beingUsed) {
        this.ethAddress = ethAddress;
        this.beingUsed = beingUsed;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    public boolean isBeingUsed() {
        return beingUsed;
    }

    public void setBeingUsed(boolean beingUsed) {
        this.beingUsed = beingUsed;
    }

    public String getMetaName() {
        return metaName;
    }

    public void setMetaName(String metaName) {
        this.metaName = metaName;
    }
}
