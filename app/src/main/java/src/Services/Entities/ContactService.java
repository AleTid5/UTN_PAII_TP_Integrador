package src.Services.Entities;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import src.Database.Internal.DatabaseManager;
import src.Database.Internal.Tables.ContactPhonesTable;
import src.Database.Internal.Tables.ContactsTable;
import src.Database.Internal.Tables.PhonesTable;
import src.Models.Contact;
import src.Models.Phone;

public abstract class ContactService {
    public static List<Contact> findContacts() {
        List<Contact> parkingList = new LinkedList<>(); // Linked list is faster when adding and deleting objects

        Cursor cursor = new DatabaseManager().find(String.format(
                "SELECT * FROM %s",
                ContactsTable.TABLE_NAME
        ));

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact()
                        .setId(cursor.getInt(cursor.getColumnIndex(ContactsTable._ID)))
                        .setName(cursor.getString(cursor.getColumnIndex(ContactsTable.NAME)))
                        .setAddress(cursor.getString(cursor.getColumnIndex(ContactsTable.ADDRESS)))
                        .setEmail(cursor.getString(cursor.getColumnIndex(ContactsTable.EMAIL)))
                        .setInformation(cursor.getString(cursor.getColumnIndex(ContactsTable.INFORMATION)));

                Cursor cursor2 = new DatabaseManager().find(String.format(
                        "SELECT %s, %s FROM %s A INNER JOIN %s B ON A.%s = B.%s WHERE A.%s = %s",
                        ContactPhonesTable.PHONE_ID,
                        PhonesTable.PHONE_NUMBER,
                        ContactPhonesTable.TABLE_NAME,
                        PhonesTable.TABLE_NAME,
                        ContactPhonesTable.PHONE_ID,
                        PhonesTable._ID,
                        ContactPhonesTable.CONTACT_ID,
                        contact.getId()
                ));

                if (cursor2.moveToFirst()) {
                    do {
                        contact.addPhone(new Phone(
                                cursor2.getInt(cursor2.getColumnIndex(ContactPhonesTable.PHONE_ID)),
                                cursor2.getString(cursor2.getColumnIndex(PhonesTable.PHONE_NUMBER))
                        ));
                    } while (cursor2.moveToNext());
                }

                parkingList.add(contact);
            } while (cursor.moveToNext());
        }

        return parkingList;
    }
}
