package src.Models;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private Integer id;
    private String name;
    private String information;
    private String email;
    private String address;
    private List<Phone> phones = new ArrayList<>();

    public Contact() {}

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

    public Contact setId(Integer id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;

        return this;
    }

    public String getInformation() {
        return information;
    }

    public Contact setInformation(String information) {
        this.information = information;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;

        return this;
    }

    public String getAddress() {
        return address;
    }

    public Contact setAddress(String address) {
        this.address = address;

        return this;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Contact setPhones(List<Phone> phones) {
        this.phones = phones;

        return this;
    }

    public Contact addPhone(Phone phone) {
        this.phones.add(phone);

        return this;
    }
}
