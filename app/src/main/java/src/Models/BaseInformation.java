package src.Models;

public class BaseInformation {
    private String id;
    private String nameAndLastName;
    private Integer DNI;
    private String bornDate;

    public BaseInformation() {}

    public BaseInformation(String id, String nameAndLastName, Integer DNI, String bornDate) {
        this.id = id;
        this.nameAndLastName = nameAndLastName;
        this.DNI = DNI;
        this.bornDate = bornDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAndLastName() {
        return nameAndLastName;
    }

    public void setNameAndLastName(String nameAndLastName) {
        this.nameAndLastName = nameAndLastName;
    }

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }
}
