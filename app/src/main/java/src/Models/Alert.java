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
    private User user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        map.put("user_id", this.getUser().getId());
        map.put("user_email", this.getUser().getEmail());
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
        this.createdDate = (String) map.get("created_date");
        this.user = new User();
        this.user.setId((String) map.get("user_id"));
        this.user.setEmail((String) map.get("user_email"));

        return this;
    }
}
