package muhammadnaveed.fyp;

import muhammadnaveed.fyp.DataObjects.GP;
import muhammadnaveed.fyp.DataObjects.Patient;
import muhammadnaveed.fyp.Repositories.GPRepository;
import muhammadnaveed.fyp.Repositories.PatientRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private GPRepository gpRepository;
    private PatientRepository patientRepository;


    public UserController(GPRepository gpRepository, PatientRepository patientRepository) {
        this.gpRepository = gpRepository;
        this.patientRepository = patientRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/login")
    public Boolean login(@RequestParam(value = "userName") String userName) {
        UserStatus userStatus = UserStatus.getInstance();

        if (userName == null) {
            return false;
        } else if (userName.startsWith("Dr")) {
            if (userName.equals(gpRepository.findByName(userName).getName())) {
                userStatus.setUserName(userName);
                return true;
            } else {
                return false;
            }
        } else if (userName.equals(patientRepository.findByName(userName).getName())) {
            userStatus.setUserName(userName);
            return true;
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
