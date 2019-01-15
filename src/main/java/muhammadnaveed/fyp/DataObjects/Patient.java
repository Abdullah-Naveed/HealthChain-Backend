package muhammadnaveed.fyp.DataObjects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "Patients")
public class Patient {

    @Id
    private String id;
    @Field
    private String name;
    private int age;
    private String gender;
    private String address;
    private String nationality;
    private String ppsNumber;
    private String gpNumber;
    private String ethAddress;
    private List<MedicalRecord> medicalRecords;

    public Patient(String id, String name, int age, String gender, String address, String nationality, String ppsNumber, String gpNumber, String ethAddress, List<MedicalRecord> medicalRecords) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.nationality = nationality;
        this.ppsNumber = ppsNumber;
        this.gpNumber = gpNumber;
        this.ethAddress = ethAddress;
        this.medicalRecords = medicalRecords;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPpsNumber() { return ppsNumber; }

    public void setPpsNumber(String ppsNumber) { this.ppsNumber = ppsNumber; }

    public String getGpNumber() { return gpNumber; }

    public void setGpNumber(String gpNumber) { this.gpNumber = gpNumber; }

    public String getEthAddress() { return ethAddress; }

    public void setEthAddress(String ethAddress) { this.ethAddress = ethAddress; }

    public List<MedicalRecord> getMedicalRecords() { return medicalRecords; }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) { this.medicalRecords = medicalRecords; }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", age=" + getAge() +
                ", gender='" + getGender() + "'" +
                ", address='" + getAddress() + "'" +
                ", nationality='" + getNationality() + "'" +
                ", ppsNumber='" + getPpsNumber() + "'" +
                ", gpNumber='" + getGpNumber() + "'" +
                "}";
    }
}
