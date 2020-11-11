package src.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Interfaces.Wrappable;
import src.Validators.NumberValidator;
import src.Validators.PasswordValidator;

public class User extends BaseInformation implements Wrappable {
    private String email;
    private String userName;
    private String password;
    private List<String> blockedUsers;
    private String createdDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCreatedDate() {
        return createdDate;
    }

    @Override
    public Map<String, Object> wrap() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", this.getNameAndLastName());
        map.put("dni", this.getDNI());
        map.put("born_date", this.getBornDate());
        map.put("username", this.getUserName());
        map.put("email", this.getEmail());
        map.put("password", PasswordValidator.encrypt(this.getPassword()));

        return map;
    }

    @Override
    public User unwrap(Map<String, Object> map) {
        this.setId((String) map.get("id"));
        this.setNameAndLastName((String) map.get("name"));
        this.setBornDate((String) map.get("born_date"));
        this.setBornDate((String) map.get("born_date"));
        this.setUserName((String) map.get("username"));
        this.setEmail((String) map.get("email"));
        this.setPassword(PasswordValidator.decrypt((String) map.get("password")));

        if (map.containsKey("dni")) {
            this.setDNI(NumberValidator.wrapNumber(map.get("dni")));
        }

        return this;
    }
}
