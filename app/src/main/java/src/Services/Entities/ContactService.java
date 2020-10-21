package src.Services.Entities;

import android.database.Cursor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import src.Database.Internal.DatabaseManager;
import src.Database.Internal.Tables.ContactsTable;
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
                parkingList.add(new Contact()
                        .setId(cursor.getInt(cursor.getColumnIndex(ContactsTable._ID)))
                        .setName(cursor.getString(cursor.getColumnIndex(ContactsTable.NAME)))
                        .setAddress(cursor.getString(cursor.getColumnIndex(ContactsTable.ADDRESS)))
                        .setEmail(cursor.getString(cursor.getColumnIndex(ContactsTable.EMAIL)))
                        .setInformation(cursor.getString(cursor.getColumnIndex(ContactsTable.INFORMATION)))
                );
            } while (cursor.moveToNext());
        }

        return parkingList;
    }
}
