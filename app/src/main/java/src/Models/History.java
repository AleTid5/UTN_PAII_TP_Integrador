package src.Models;

public class History extends BaseInformation {
    private Integer age;
    private String phoneNumber;
    private String observations;

    public History(Integer id, String nameAndLastName, Integer DNI, String bornDate, Integer age, String phoneNumber, String observations) {
        super(id, nameAndLastName, DNI, bornDate);
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.observations = observations;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
