package src.Models;

import java.util.HashMap;
import java.util.Map;

import src.Interfaces.Wrappable;
import src.Validators.NumberValidator;

public class History extends BaseInformation implements Wrappable {
    private String phoneNumber;
    private String observations;
    private String userId;
    private String createdDate;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Map<String, Object> wrap() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", this.getNameAndLastName());
        map.put("dni", this.getDNI());
        map.put("born_date", this.getBornDate());
        map.put("phone", this.getPhoneNumber());
        map.put("observation", this.getObservations());
        map.put("user_id", this.getUserId());
        map.put("created_date", this.getCreatedDate());

        return map;
    }

    @Override
    public History unwrap(Map<String, Object> map) {
        this.setId((String) map.get("id"));
        this.setNameAndLastName((String) map.get("name"));
        this.setBornDate((String) map.get("born_date"));
        this.setPhoneNumber((String) map.get("phone"));
        this.setObservations((String) map.get("observation"));
        this.setUserId((String) map.get("user_id"));
        this.createdDate = (String) map.get("created_date");

        if (map.containsKey("dni")) {
            this.setDNI(NumberValidator.wrapNumber(map.get("dni")));
        }

        return this;
    }
}
