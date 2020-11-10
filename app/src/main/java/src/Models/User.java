package src.Models;

public class User extends BaseInformation {
    private String userName;
    private String password;

    public User(String id, String nameAndLastName, Integer DNI, String bornDate, String userName, String password) {
        super(id, nameAndLastName, DNI, bornDate);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
