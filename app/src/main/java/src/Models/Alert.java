package src.Models;

import java.util.HashMap;
import java.util.Map;

import src.Interfaces.Wrappable;

public class Alert implements Wrappable {
    private String id;
    private String nameAndLastName;
    private String place;
    private String contact;
    private String observations;
    private String userId;
    private String createdDate;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        map.put("place", this.getPlace());
        map.put("contact", this.getContact());
        map.put("observation", this.getObservations());
        map.put("user_id", this.getUserId());
        map.put("created_date", this.getCreatedDate());

        return map;
    }

    @Override
    public Alert unwrap(Map<String, Object> map) {
        this.setId((String) map.get("id"));
        this.setNameAndLastName((String) map.get("name"));
        this.setPlace((String) map.get("place"));
        this.setContact((String) map.get("contact"));
        this.setObservations((String) map.get("observation"));
        this.setUserId((String) map.get("user_id"));
        this.createdDate = (String) map.get("created_date");

        return this;
    }
}
