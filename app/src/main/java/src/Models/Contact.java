package src.Models;

import java.util.List;

public class Contact {
    private Integer id;
    private String name;
    private String information;
    private String email;
    private String address;
    private List<Phone> phones;

    public Contact(Integer id, String name, String information, String email, String address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.email = email;
        this.address = address;
        this.phones = phones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
