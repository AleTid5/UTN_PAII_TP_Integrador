package src.Models;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseInformation {
    private String userName;
    private String password;
    private List<String> blockedUsers;

    public User(String id, String nameAndLastName, Integer DNI, String bornDate, String userName, String password) {
        super(id, nameAndLastName, DNI, bornDate);
        this.userName = userName;
        this.password = password;
        this.blockedUsers = new ArrayList<>();
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

    public List<String> getBlockedUsers() { return blockedUsers; }

    public void setBlockedUsers(List<String> blockedUsers) { this.blockedUsers = blockedUsers;}
}
