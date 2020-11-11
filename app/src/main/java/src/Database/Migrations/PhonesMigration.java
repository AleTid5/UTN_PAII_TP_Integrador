package src.Database.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Tables.PhonesTable;

public abstract class PhonesMigration {
    public static final List<String> dataToInsert = Arrays.asList(
            "+541137925599",
            "+541145124258",
            "*103 / 52827510",
            "+541152827500",
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
