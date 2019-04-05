package muhammadnaveed.fyp.Repositories;

import muhammadnaveed.fyp.DataObjects.EthAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthAddressRepository extends MongoRepository<EthAddress, String> {

    EthAddress findByEthAddress(String address);

}
