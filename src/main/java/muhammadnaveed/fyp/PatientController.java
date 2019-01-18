package muhammadnaveed.fyp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
        Patient.PatientBuilder patientBuilder = new Patient.PatientBuilder();

        try {
            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();
            String gpNumber = jsonObj.get("gpNumber").getAsString();

            patientBuilder.id(jsonObj.get("id").getAsString())
                    .name(jsonObj.get("name").getAsString())
                    .age(jsonObj.get("age").getAsInt())
                    .gender(jsonObj.get("gender").getAsString())
                    .address(jsonObj.get("address").getAsString())
                    .nationality(jsonObj.get("nationality").getAsString())
                    .ppsNumber(jsonObj.get("pps").getAsString())
                    .gpNumber(gpNumber)
                    .ethAddress(jsonObj.get("ethAddress").getAsString());

            patientRepository.save(patientBuilder.build());

            return ResponseEntity.accepted().build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

}
