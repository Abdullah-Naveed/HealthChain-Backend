package muhammadnaveed.fyp.Repositories;

import muhammadnaveed.fyp.DataObjects.GP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPRepository extends MongoRepository<GP, String> {

    GP findByName(String name);

}
