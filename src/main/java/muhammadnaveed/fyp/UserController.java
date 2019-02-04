package muhammadnaveed.fyp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import muhammadnaveed.fyp.DataObjects.GP;
import muhammadnaveed.fyp.DataObjects.Patient;
import muhammadnaveed.fyp.DataObjects.User;
import muhammadnaveed.fyp.EncryptionDecryption.Decryption;
import muhammadnaveed.fyp.EncryptionDecryption.Encryption;
import muhammadnaveed.fyp.Repositories.GPRepository;
import muhammadnaveed.fyp.Repositories.PatientRepository;
import muhammadnaveed.fyp.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private GPRepository gpRepository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;


    public UserController(GPRepository gpRepository, PatientRepository patientRepository, UserRepository userRepository) {
        this.gpRepository = gpRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/registerGP", consumes = "application/json")
    public ResponseEntity registerGP(@RequestBody String json){
        JsonObject jsonObj;
        GP.GPBuilder gpBuilder =  new GP.GPBuilder();
        Encryption enc = new Encryption();

        try {

            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();
            gpBuilder.id(jsonObj.get("id").getAsString())
                    .name(jsonObj.get("name").getAsString())
                    .address(jsonObj.get("address").getAsString())
                    .age(jsonObj.get("age").getAsInt())
                    .ethAddress(jsonObj.get("ethAddress").getAsString())
                    .gender(jsonObj.get("gender").getAsString())
                    .gpNumber(jsonObj.get("gpNumber").getAsString());

            gpRepository.save(gpBuilder.build());

            //now encrypt password and store with gp user name in user repo
            String password = jsonObj.get("password").getAsString();
            //encrypt the password using their gpNumber
            String encryptedPassword = enc.encrypt2layer(password, jsonObj.get("ethAddress").getAsString());
            //save user in user repo
            userRepository.save(new User(jsonObj.get("name").getAsString(), encryptedPassword));

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/addPatient", consumes = "application/json")
    public ResponseEntity addPatient(@RequestBody String json) {
        JsonObject jsonObj;
        Patient.PatientBuilder patientBuilder = new Patient.PatientBuilder();
        Encryption enc = new Encryption();

        try {
            JsonParser parser = new JsonParser();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            jsonObj = parser.parse(json).getAsJsonObject();
            String gpNumber = jsonObj.get("gpNumber").getAsString();
            String gpName = jsonObj.get("gpName").getAsString();
            String gpEthAddress = jsonObj.get("gpEthAddress").getAsString();
            String userName = jsonObj.get("name").getAsString();

            patientBuilder.id(jsonObj.get("id").getAsString())
                    .name(userName)
                    .age(jsonObj.get("age").getAsInt())
                    .gender(jsonObj.get("gender").getAsString())
                    .address(jsonObj.get("address").getAsString())
                    .nationality(jsonObj.get("nationality").getAsString())
                    .ppsNumber(jsonObj.get("pps").getAsString())
                    .gpNumber(gpNumber)
                    .ethAddress(jsonObj.get("ethAddress").getAsString())
                    .secretKey(jsonObj.get("secretKey").getAsString());

            //save patient
            patientRepository.save(patientBuilder.build());

            //add gp as trusted gp for patient...
            Patient patient = patientRepository.findByName(userName);
            patient.addTrustedGP(gpName, gpEthAddress);
            patientRepository.save(patient);

            //ecrypt password and save
            String encryptedPassword = enc.encrypt2layer(patient.getSecretKey(), jsonObj.get("ethAddress").getAsString());
            userRepository.save(new User(patient.getName(), encryptedPassword));

            return ResponseEntity.accepted().build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/login")
    public Boolean login(@RequestParam Map<String,String> requestParams) {
        UserStatus userStatus = UserStatus.getInstance();
        String userName = requestParams.get("userName");
        String password = requestParams.get("password");
        Decryption dec = new Decryption();

        if (userName == null) {
            return false;
        } else if (userName.equals(userRepository.findByName(userName).getName())) {
            if(userName.startsWith("Dr")){
                try {
                    if(password.equals(dec.decrypt2layer(userRepository.findByName(userName).getPassword(), gpRepository.findByName(userName).getEthAddress()))){
                        //if equal then log in or else throw exception....
                        userStatus.setUserName(userName);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Patient patient = patientRepository.findByName(userName);
                    if(password.equals(dec.decrypt2layer(userRepository.findByName(userName).getPassword(),patient.getEthAddress()))){
                        //if equal then log in or else throw exception....
                        userStatus.setUserName(userName);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return false;
        } else {
            return false;
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/logout")
    public void logout() {
        UserStatus userStatus = UserStatus.getInstance();
        userStatus.setUserName("");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/loggedIn")
    public String loggedIn() {
        UserStatus userStatus = UserStatus.getInstance();
        return userStatus.getUserName();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getUserEthAddress")
    public String getUserEthAddress(@RequestParam(value = "userName") String userName) {
        GP gp;
        Patient patient;

        if (userName.startsWith("Dr")) {
            gp = gpRepository.findByName(userName);
            return gp.getEthAddress();
        }else{
            patient = patientRepository.findByName(userName);
            return patient.getEthAddress();
        }
    }

}
