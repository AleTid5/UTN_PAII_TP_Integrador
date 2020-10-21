package src.Database.Internal.Migrations;

import android.database.sqlite.SQLiteDatabase;

import src.Database.Internal.Tables.ContactPhonesTable;
import src.Database.Internal.Tables.ContactsTable;
import src.Database.Internal.Tables.PhonesTable;

public abstract class SkeletonMigration {
    public static void execute(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PhonesTable.TABLE_NAME + " ("
                + PhonesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PhonesTable.PHONE_NUMBER + " TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + ContactsTable.TABLE_NAME + " ("
                + ContactsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ContactsTable.NAME + " TEXT NOT NULL,"
                + ContactsTable.INFORMATION + " TEXT NOT NULL,"
                + ContactsTable.EMAIL + " TEXT NOT NULL,"
                + ContactsTable.ADDRESS + " TEXT NOT NULL,"
                + " UNIQUE (" + ContactsTable.EMAIL + "))");

        sqLiteDatabase.execSQL("CREATE TABLE " + ContactPhonesTable.TABLE_NAME + " ("
                + ContactPhonesTable.CONTACT_ID + " INTEGER NOT NULL,"
                + ContactPhonesTable.PHONE_ID + " INTEGER NOT NULL,"
                + " PRIMARY KEY(" + ContactPhonesTable.CONTACT_ID + ", " + ContactPhonesTable.PHONE_ID + "), "
                + " FOREIGN KEY(" + ContactPhonesTable.CONTACT_ID + ") REFERENCES " + ContactsTable.TABLE_NAME + "(" + ContactsTable._ID + "), "
                + " FOREIGN KEY(" + ContactPhonesTable.PHONE_ID + ") REFERENCES " + PhonesTable.TABLE_NAME + "(" + ContactsTable._ID + "))");
    }
}
