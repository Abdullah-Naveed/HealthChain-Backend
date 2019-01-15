package muhammadnaveed.fyp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import muhammadnaveed.fyp.DataObjects.GP;
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
