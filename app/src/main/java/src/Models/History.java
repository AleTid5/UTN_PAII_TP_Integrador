package src.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import src.Interfaces.Wrappable;
import src.Services.Entities.UserService;
import src.Validators.NumberValidator;

public class History extends BaseInformation implements Wrappable {
    private String phoneNumber;
    private String observations;
    private User user;

    public History() {}

    public History(String id, String nameAndLastName, Integer DNI, String bornDate, String phoneNumber, String observations) {
        super(id, nameAndLastName, DNI, bornDate);
        this.phoneNumber = phoneNumber;
        this.observations = observations;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public Map<String, Object> wrap() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", this.getNameAndLastName());
        map.put("dni", this.getDNI());
        map.put("born_date", this.getBornDate());
        map.put("phone", this.getPhoneNumber());
        map.put("observation", this.getObservations());
        map.put("userId", this.user != null ? this.user.getId() : null);

        return map;
    }

    @Override
    public History unwrap(Map<String, Object> map) {
        this.setId((String) map.get("id"));
        this.setNameAndLastName((String) map.get("name"));
        this.setBornDate((String) map.get("born_date"));
        this.setPhoneNumber((String) map.get("phone"));
        this.setObservations((String) map.get("observation"));

        if (map.containsKey("dni")) {
            this.setDNI(NumberValidator.wrapNumber(map.get("dni")));
        }

        if (map.containsKey("userId")) {
            this.setUser(UserService.findUserById(NumberValidator.wrapNumber(map.get("userId"))));
        }

        return this;
    }
}
