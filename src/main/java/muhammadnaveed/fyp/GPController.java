package muhammadnaveed.fyp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import muhammadnaveed.fyp.DataObjects.GP;
import muhammadnaveed.fyp.DataObjects.Patient;
import muhammadnaveed.fyp.Repositories.GPRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;

@RestController
@RequestMapping("/gp")
public class GPController {

    private GPRepository gpRepository;

    public GPController(GPRepository gpRepository){
        this.gpRepository = gpRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getGp")
    public String getGP(@RequestParam(value = "userName") String userName){
        String json = "";
        GP gp = gpRepository.findByName(userName);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            json = ow.writeValueAsString(gp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getListOfPatients")
    public String getListOfPatients(@RequestParam(value = "userName") String userName){
        String json = "";
        GP gp = gpRepository.findByName(userName);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            json = ow.writeValueAsString(gp.getPatients());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    //TODO need to make sure they are created on start of application!
    @PostMapping(value = "/addGP", consumes = "application/json")
    public ResponseEntity addGP(@RequestBody String json){
        JsonObject jsonObj;
        String id;
        String name;
        int age;
        String gpNumber;
        String gender;
        String address;
        String ethAddress;
        try {
            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();

            id = jsonObj.get("id").getAsString();
            name = jsonObj.get("name").getAsString();
            age = jsonObj.get("age").getAsInt();
            gpNumber = jsonObj.get("gpNumber").getAsString();
            gender = jsonObj.get("gender").getAsString();
            address = jsonObj.get("address").getAsString();
            ethAddress = jsonObj.get("ethAddress").getAsString();

            GP gp = new GP(id, name, age, gpNumber, gender, address, ethAddress);
            gpRepository.save(gp);

            return ResponseEntity.accepted().build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/savePatient", consumes = "application/json")
    public ResponseEntity savePatient(@RequestBody String json){
        JsonObject jsonObj;
        Patient.PatientBuilder patientBuilder = new Patient.PatientBuilder();
        GP gp;

        try {
            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();

            String gpName = jsonObj.get("gpName").getAsString();
            gp = gpRepository.findByName(gpName);

            patientBuilder.id(jsonObj.get("id").getAsString())
                    .name(jsonObj.get("name").getAsString())
                    .age(jsonObj.get("age").getAsInt())
                    .gender(jsonObj.get("gender").getAsString())
                    .address(jsonObj.get("address").getAsString())
                    .nationality(jsonObj.get("nationality").getAsString())
                    .ppsNumber(jsonObj.get("pps").getAsString())
                    .gpNumber(jsonObj.get("gpNumber").getAsString())
                    .ethAddress(jsonObj.get("ethAddress").getAsString());

            gp.addPatient(patientBuilder.build());
            gpRepository.save(gp);

            return ResponseEntity.accepted().build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getEthAddress")
    public String getEthAddress(@RequestParam(value = "userName") String userName){
        GP gp = gpRepository.findByName(userName);
        return gp.getEthAddress();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getGPs")
    public String getAllGPs() {
        String json = "";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(gpRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        List<GP> gps = new ArrayList<>(gpRepository.findAll());
//        return new Gson().toJson(gps);
        return json;
    }

}
