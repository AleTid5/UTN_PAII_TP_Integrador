package src.Database.Internal.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Internal.Tables.PhonesTable;

public abstract class PhonesMigration {
    public static final List<String> dataToInsert = Arrays.asList(
            "+541132045137",
            "+541156765432",
            "+541134564322",
            "+541156765323",
            "+541134534256",
            "+541153132234",
            "+541153563542",
            "+541153671909",
            "+541153871977",
            "+541153343900"
    );

    public static void execute(SQLiteDatabase sqLiteDatabase) {
        dataToInsert.forEach(phoneNumber -> sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s) VALUES ('%s')",
                PhonesTable.TABLE_NAME,
                PhonesTable.PHONE_NUMBER,
                phoneNumber
        )));
    }
}
