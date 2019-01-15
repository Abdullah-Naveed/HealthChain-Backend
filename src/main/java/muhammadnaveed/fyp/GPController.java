package muhammadnaveed.fyp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import muhammadnaveed.fyp.DataObjects.GP;
import muhammadnaveed.fyp.Repositories.GPRepository;
import muhammadnaveed.fyp.Repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;

@RestController
@RequestMapping("/gp")
public class GPController {

    private GPRepository gpRepository;
    private PatientRepository patientRepository;


    public GPController(GPRepository gpRepository, PatientRepository patientRepository){
        this.gpRepository = gpRepository;
        this.patientRepository = patientRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/userInfo")
    public Boolean gpInfo(@RequestParam(value = "userName") String userName) {
        UserStatus userStatus = UserStatus.getInstance();

        if(userName.startsWith("Dr")){
            if(userName.equals(gpRepository.findByName(userName).getName())){
                userStatus.setUserName(userName);
                return true;
            }else{
                return false;
            }
        }else {
            if (userName.equals(patientRepository.findByName(userName).getName())) {
                userStatus.setUserName(userName);
                return true;
            } else {
                return false;
            }
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/logout")
    public void logout() {
        UserStatus userStatus = UserStatus.getInstance();
        userStatus.setUserName("");
    }


    //just make 2 GP accounts using postman
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

            GP gp = new GP(id, name, age, gpNumber, gender, address, ethAddress,null);
            gpRepository.save(gp);

            return ResponseEntity.accepted().build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
