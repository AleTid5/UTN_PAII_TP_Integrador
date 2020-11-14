package src.Database.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Tables.PhonesTable;

public abstract class PhonesMigration {
    public static final List<String> dataToInsert = Arrays.asList(
            "+541137925599",
            "+541145124258",
            "5282-7500",
            "*103"
    );

    public static void execute(SQLiteDatabase sqLiteDatabase) {
        dataToInsert.forEach(phoneNumber -> sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s) VALUES ('%s')",
                PhonesTable.TABLE_NAME,
                PhonesTable.PHONE_NUMBER,
                phoneNumber
        )));
    }
}
