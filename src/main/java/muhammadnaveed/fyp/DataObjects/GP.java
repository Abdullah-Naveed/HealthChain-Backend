package muhammadnaveed.fyp.DataObjects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "GPs")
public class GP {

    @Id
    private String id;
    @Field
    private String name;
    private int age;
    private String gpNumber;
    private String gender;
    private String address;
    private String ethAddress;
    private List<Patient> patients;

    public GP(String id, String name, int age, String gpNumber, String gender, String address, String ethAddress, List<Patient> patients) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpNumber = gpNumber;
        this.gender = gender;
        this.address = address;
        this.ethAddress = ethAddress;
        this.patients = patients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getGpNumber() { return gpNumber; }

    public void setGpNumber(String gpNumber) { this.gpNumber = gpNumber; }

    public String getEthAddress() { return ethAddress; }

    public void setEthAddress(String ethAddress) { this.ethAddress = ethAddress; }

    public List<Patient> getPatients() { return patients; }

    public void setPatients(List<Patient> patients) { this.patients = patients; }

    public GP addPatient(Patient patient) {
        this.patients.add(patient);
        patient.setGpNumber(this.gpNumber);
        return this;
    }

    public GP removePatient(Patient patient) {
        this.patients.remove(patient);
        patient.setGpNumber(null);
        return this;
    }

    @Override
    public String toString() {
        return "GP{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ", gpNumber=" + getGpNumber() +
                ", address='" + getAddress() + "'" +
                ", patients='" + getPatients() + "'" +
                "}";
    }

}
