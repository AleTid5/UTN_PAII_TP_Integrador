package src.Database.Internal.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Internal.Tables.ContactsTable;

public abstract class ContactsMigration {
    private static final List<List<String>> dataToInsert = Arrays.asList(
            Arrays.asList("Refugio 1", "Lorem Ipsum", "refugio1@gmail.com", "Av. Cazón 3321, Tigre"),
            Arrays.asList("Refugio 2", "Lorem Ipsum", "refugio2@gmail.com", "Av. Dardo Rocha 214, Tigre"),
            Arrays.asList("Refugio 3", "Lorem Ipsum", "refugio3@gmail.com", "Comandante Yacas 142, Tigre"),
            Arrays.asList("Refugio 4", "Lorem Ipsum", "refugio4@gmail.com", "Av. Presidente Perón 567, San Fernando"),
            Arrays.asList("Refugio 5", "Lorem Ipsum", "refugio5@gmail.com", "Av. Sobremonte 5436, San Fernando"),
            Arrays.asList("Refugio 6", "Lorem Ipsum", "refugio6@gmail.com", "Av. Nicolás Avellaneda 840, San Fernando"),
            Arrays.asList("Refugio 7", "Lorem Ipsum", "refugio7@gmail.com", "Av. Hipólito Yrigoyen 4456, Barrio San Jorge"),
            Arrays.asList("Refugio 8", "Lorem Ipsum", "refugio8@gmail.com", "Miguel Cané 367, Virreyes"),
            Arrays.asList("Refugio 9", "Lorem Ipsum", "refugio9@gmail.com", "Garibaldi 1567, Virreyes"),
            Arrays.asList("Refugio 10", "Lorem Ipsum", "refugio10@gmail.com", "Malvinas Argentina 747, Victoria")
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
