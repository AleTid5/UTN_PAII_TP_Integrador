package src.Database.Internal.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Internal.Tables.ContactPhonesTable;

public abstract class ContactPhonesMigration {
    private static final List<List<String>> dataToInsert = Arrays.asList(
            Arrays.asList("1", PhonesMigration.dataToInsert.get(0)),
            Arrays.asList("2", PhonesMigration.dataToInsert.get(1)),
            Arrays.asList("3", PhonesMigration.dataToInsert.get(2)),
            Arrays.asList("4", PhonesMigration.dataToInsert.get(3)),
            Arrays.asList("5", PhonesMigration.dataToInsert.get(4)),
            Arrays.asList("6", PhonesMigration.dataToInsert.get(5)),
            Arrays.asList("7", PhonesMigration.dataToInsert.get(6)),
            Arrays.asList("8", PhonesMigration.dataToInsert.get(7)),
            Arrays.asList("9", PhonesMigration.dataToInsert.get(8)),
            Arrays.asList("10", PhonesMigration.dataToInsert.get(9))
    );

    public static void execute(SQLiteDatabase sqLiteDatabase) {
        dataToInsert.forEach(data -> sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES (%s, %s)",
                ContactPhonesTable.TABLE_NAME,
                ContactPhonesTable.CONTACT_ID,
                ContactPhonesTable.PHONE_ID,
                data.get(0),
                data.get(1)
        )));
    }
}
