package muhammadnaveed.fyp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import muhammadnaveed.fyp.DataObjects.Patient;
import muhammadnaveed.fyp.EncryptionDecryption.Decryption;
import muhammadnaveed.fyp.EncryptionDecryption.Encryption;
import muhammadnaveed.fyp.Repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientRepository patientRepository;
    private static ObjectMapper mapper = new ObjectMapper();

    public PatientController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/name")
    public Patient getPatient(@RequestParam(value = "userName") String userName){
        return patientRepository.findByName(userName);
    }

//    @CrossOrigin(origins = "http://localhost:3000")
//    @PostMapping(value = "/addPatient", consumes = "application/json")
//    public ResponseEntity addPatient(@RequestBody String json) {
//        JsonObject jsonObj;
//        Patient.PatientBuilder patientBuilder = new Patient.PatientBuilder();
//
//        try {
//            JsonParser parser = new JsonParser();
//            JsonReader reader = new JsonReader(new StringReader(json));
//            reader.setLenient(true);
//            jsonObj = parser.parse(json).getAsJsonObject();
//            String gpNumber = jsonObj.get("gpNumber").getAsString();
//            String gpName = jsonObj.get("gpName").getAsString();
//            String gpEthAddress = jsonObj.get("gpEthAddress").getAsString();
//            String userName = jsonObj.get("name").getAsString();
//
//            patientBuilder.id(jsonObj.get("id").getAsString())
//                    .name(userName)
//                    .age(jsonObj.get("age").getAsInt())
//                    .gender(jsonObj.get("gender").getAsString())
//                    .address(jsonObj.get("address").getAsString())
//                    .nationality(jsonObj.get("nationality").getAsString())
//                    .ppsNumber(jsonObj.get("pps").getAsString())
//                    .gpNumber(gpNumber)
//                    .ethAddress(jsonObj.get("ethAddress").getAsString())
//                    .secretKey(jsonObj.get("secretKey").getAsString());
//
//            //save patient
//            patientRepository.save(patientBuilder.build());
//
//            //add gp as trusted gp for patient...
//            Patient patient = patientRepository.findByName(userName);
//            patient.addTrustedGP(gpName, gpEthAddress);
//            patientRepository.save(patient);
//
//            return ResponseEntity.accepted().build();
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//
//    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getSecret")
    public String getSecret(@RequestParam(value = "userName") String userName){
        Patient patient = patientRepository.findByName(userName);
        if(patient.getSecretKey() == null){
            return null;
        }else{
            return patient.getSecretKey();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/setSecret", consumes = "application/json")
    public ResponseEntity setSecret(@RequestBody String json){
        JsonObject jsonObj;

        try {

            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();
            String userName = jsonObj.get("userName").getAsString();
            String secret = jsonObj.get("secret").getAsString();

            Patient patient = patientRepository.findByName(userName);
            if(patient.getSecretKey() == null){
                patient.setSecretKey(secret);
                patientRepository.save(patient);
                return ResponseEntity.accepted().build();
            }else{
                return ResponseEntity.badRequest().build();
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getTrustedGPs")
    public String getTrustedGPs(@RequestParam(value = "userName") String userName){
        String json = "";
        Patient patient = patientRepository.findByName(userName);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            json = ow.writeValueAsString(patient.getTrustedGPs());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/encryptRecord")
    public String encryptMedicalRecord(@RequestBody String json) {
        JsonObject jsonObj;
        String encryptedDocument;
        Encryption enc = new Encryption();

        JsonParser parser = new JsonParser();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        jsonObj = parser.parse(json).getAsJsonObject();

        String pps = jsonObj.get("pps").getAsString();
        Patient patient = patientRepository.findByPpsNumber(pps);
        String patientSecret = patient.getSecretKey();
        String record = jsonObj.get("record").getAsString();

        try {
            encryptedDocument = enc.encrypt2layer(record, patientSecret);
            return encryptedDocument;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/decryptRecord")
    public String decryptMedicalRecord(@RequestBody String json) {
        JsonObject jsonObj;
        String record;
        Decryption dec = new Decryption();

        JsonParser parser = new JsonParser();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        jsonObj = parser.parse(json).getAsJsonObject();

        String ppsNumber = jsonObj.get("ppsNumber").getAsString();
        Patient patient = patientRepository.findByPpsNumber(ppsNumber);
        String patientSecret = patient.getSecretKey();
        String encryptedRecord = jsonObj.get("encryptedRecord").getAsString();

        try {
            record = dec.decrypt2layer(encryptedRecord, patientSecret);
            return record;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
