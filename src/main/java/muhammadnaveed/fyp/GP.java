package muhammadnaveed.fyp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "GPs")
public class GP {

    @Id
    private String id;
    private String name;
    private int age;
    private int gpNumber;
    private String sex;
    private String address;
    private List<Patient> patients;

    public GP(String id, String name, int age, int gpNumber, String sex, String address, List<Patient> patients) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpNumber = gpNumber;
        this.sex = sex;
        this.address = address;
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

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getGpNumber() { return gpNumber; }

    public void setGpNumber(int gpNumber) { this.gpNumber = gpNumber; }

    public List<Patient> getPatients() { return patients; }

    public void setPatients(List<Patient> patients) { this.patients = patients; }

    public GP addPatient(Patient patient) {
        this.patients.add(patient);
        patient.setGp(this);
        return this;
    }

    public GP removePatient(Patient patient) {
        this.patients.remove(patient);
        patient.setGp(null);
        return this;
    }

    @Override
    public String toString() {
        return "GP{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", age=" + getAge() +
                ", sex=" + getSex() +
                ", gpNumber=" + getGpNumber() +
                ", address='" + getAddress() + "'" +
                ", patients='" + getPatients() + "'" +
                "}";
    }

}
