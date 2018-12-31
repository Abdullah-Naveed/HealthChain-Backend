package muhammadnaveed.fyp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Patients")
public class Patient {

    @Id
    private String id;
    private String name;
    private int age;
    private String sex;
    private String address;
    private String nationality;
    private String ppsNumber;
    private GP gp;

    public Patient(String id, String name, int age, String sex, String address, String nationality, String ppsNumber, GP gp) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.nationality = nationality;
        this.ppsNumber = ppsNumber;
        this.gp = gp;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPpsNumber() { return ppsNumber; }

    public void setPpsNumber(String ppsNumber) { this.ppsNumber = ppsNumber; }

    public GP getGp() { return gp; }

    public void setGp(GP gp) { this.gp = gp; }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", age=" + getAge() +
                ", sex='" + getSex() + "'" +
                ", address='" + getAddress() + "'" +
                ", nationality='" + getNationality() + "'" +
                ", ppsNumber='" + getPpsNumber() + "'" +
                ", gp='" + getGp() + "'" +
                "}";
    }
}
