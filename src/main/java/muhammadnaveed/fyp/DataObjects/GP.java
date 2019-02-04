package muhammadnaveed.fyp.DataObjects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
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

    public GP(String id, String name, int age, String gpNumber, String gender, String address, String ethAddress) {
        this.patients = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpNumber = gpNumber;
        this.gender = gender;
        this.address = address;
        this.ethAddress = ethAddress;
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

    public void addPatient(Patient patient) {
        this.patients.add(patient);
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
                ", gpNumber=" + getGpNumber() +
                ", gender=" + getGender() +
                ", address='" + getAddress() + "'" +
                ", ethAddress='" + getEthAddress() + "'" +
                ", patients='" + getPatients() + "'" +
                "}";
    }


    public static final class GPBuilder {
        private String id;
        private String name;
        private int age;
        private String gpNumber;
        private String gender;
        private String address;
        private String ethAddress;

        public GPBuilder() {
        }

        public GPBuilder id(String id) {
            this.id = id;
            return this;
        }

        public GPBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GPBuilder age(int age) {
            this.age = age;
            return this;
        }

        public void gpNumber(String gpNumber) {
            this.gpNumber = gpNumber;
        }

        public GPBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public GPBuilder address(String address) {
            this.address = address;
            return this;
        }

        public GPBuilder ethAddress(String ethAddress) {
            this.ethAddress = ethAddress;
            return this;
        }

        public GP build() {
            return new GP(id, name, age, gpNumber, gender, address, ethAddress);
        }
    }
}
