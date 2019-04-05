package muhammadnaveed.fyp;

import muhammadnaveed.fyp.DataObjects.EthAddress;
import muhammadnaveed.fyp.Repositories.EthAddressRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eth")
public class EthAddressController {

    private EthAddressRepository ethAddressRepository;

    private EthAddressController(EthAddressRepository ethAddressRepository) {
        this.ethAddressRepository = ethAddressRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getFreeAddress")
    public String getFreeAddress() {
        List<EthAddress> addresses = ethAddressRepository.findAll();
        EthAddress usedAddress;

        for (EthAddress address : addresses) {
            if (!address.isBeingUsed()) {
                usedAddress = new EthAddress(address.getEthAddress(), true);
                ethAddressRepository.save(usedAddress);
                return address.getEthAddress();
            }
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/addEthAddress")
    public void addEthAddress(@RequestParam(value = "address") String address) {
        EthAddress ethAddress = new EthAddress(address, false);
        ethAddressRepository.save(ethAddress);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getMetaName")
    public String getMetaName(@RequestParam(value = "address") String address) {
        return ethAddressRepository.findByEthAddress(address).getMetaName();
    }

}
