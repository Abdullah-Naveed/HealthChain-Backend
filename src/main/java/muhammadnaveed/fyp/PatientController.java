package muhammadnaveed.fyp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import muhammadnaveed.fyp.DataObjects.MedicalRecord;
import muhammadnaveed.fyp.DataObjects.Patient;
import muhammadnaveed.fyp.Repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/name")
    public String patient(@RequestParam(value = "userName") String userName){
        return patientRepository.findByName(userName).toString();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/addPatient", consumes = "application/json")
    public ResponseEntity addPatient(@RequestBody String json) {
        JsonObject jsonObj;
        String id;
        String name;
        int age;
        String gender;
        String address;
        String nationality;
        String ppsNumber;
        String gpNumber;

        try {
            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();
            id = jsonObj.get("id").getAsString();
            name = jsonObj.get("name").getAsString();
            age = jsonObj.get("age").getAsInt();
            gender = jsonObj.get("gender").getAsString();
            address = jsonObj.get("address").getAsString();
            nationality = jsonObj.get("nationality").getAsString();
            ppsNumber = jsonObj.get("pps").getAsString();
            gpNumber = jsonObj.get("gpNumber").getAsString();

            Patient patient = new Patient(id, name, age, gender, address, nationality, ppsNumber, gpNumber, null);
            patientRepository.save(patient);

            return ResponseEntity.accepted().build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
