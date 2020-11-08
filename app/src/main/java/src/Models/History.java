package src.Models;

public class History extends BaseInformation {
    private Integer age;
    private String phoneNumber;
    private String observations;
    private String imageURL;

    public History() {}

    public History(Integer id, String nameAndLastName, Integer DNI, String bornDate, Integer age, String phoneNumber, String observations, String imageURL) {
        super(id, nameAndLastName, DNI, bornDate);
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.observations = observations;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
