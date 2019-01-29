package muhammadnaveed.fyp.Repositories;

import muhammadnaveed.fyp.DataObjects.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    Patient findByName(String name);

    Patient findByPpsNumber(String ppsNumber);

}
