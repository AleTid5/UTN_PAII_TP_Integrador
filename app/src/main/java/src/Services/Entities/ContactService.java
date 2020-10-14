package src.Services.Entities;

import java.util.Arrays;
import java.util.List;

import src.Models.Contact;
import src.Models.Phone;

public abstract class ContactService {
    private static List<Contact> contacts = Arrays.asList(
            new Contact(1, "Some 1", "Lorem Ipsum", "asd@asd.com", "Av. Maip", Arrays.asList(new Phone(1, "1554324542"))),
            new Contact(2, "Some 2", "Lorem Ipsum", "asd2@asd.com", "Av. Maip 2", Arrays.asList(new Phone(2, "1545486542")))
    );

    public static List<Contact> findContacts() {
        return contacts;
    }
}
