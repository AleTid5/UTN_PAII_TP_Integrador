package src.Database.Internal.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import src.Database.Internal.Tables.ContactPhonesTable;

public abstract class ContactPhonesMigration {
    public static void execute(SQLiteDatabase sqLiteDatabase) {
        IntStream.range(0, new ArrayList<>(Collections.nCopies(10, 0)).size())
                .boxed().collect(Collectors.toList()).forEach(id ->
                sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s, %s) VALUES (%s, %s)",
                        ContactPhonesTable.TABLE_NAME,
                        ContactPhonesTable.CONTACT_ID,
                        ContactPhonesTable.PHONE_ID,
                        id + 1,
                        id + 1
                )));
    }
}
