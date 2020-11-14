package src.Database.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Tables.ContactsTable;

public abstract class ContactsMigration {
    private static final List<List<String>> dataToInsert = Arrays.asList(
            Arrays.asList("Duchas Santa Teresa", "Lorem Ipsum", "duchasensantateresa@gmail.com", "Islas Orcadas y Neuquén 1617, General Pacheco (Tigre)"),
            Arrays.asList("El Refugio", "Lorem Ipsum", "-", "Enciso 425, Tigre"),
            Arrays.asList("Secretaría de Desarrollo Social", "Lorem Ipsum", "direcciontrabajosocial@tigre.gob.ar/refugionocturno@tigre.gob.ar", "Av. Presidente Perón 567, San Fernando")
    );

    public static void execute(SQLiteDatabase sqLiteDatabase) {
        dataToInsert.forEach(data -> sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s')",
                ContactsTable.TABLE_NAME,
                ContactsTable.NAME,
                ContactsTable.INFORMATION,
                ContactsTable.EMAIL,
                ContactsTable.ADDRESS,
                data.get(0),
                data.get(1),
                data.get(2),
                data.get(3)
        )));
    }
}
