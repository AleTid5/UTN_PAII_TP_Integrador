package src.Database.Migrations;

import android.database.sqlite.SQLiteDatabase;

import src.Database.Tables.ContactPhonesTable;
import src.Database.Tables.ContactsTable;
import src.Database.Tables.PhonesTable;
import src.Database.Tables.UserTable;

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

        sqLiteDatabase.execSQL("CREATE TABLE " + UserTable.TABLE_NAME + " ("
                + UserTable.ID + " TEXT PRIMARY KEY,"
                + UserTable.NAME + " TEXT NOT NULL,"
                + UserTable.DNI + " INTEGER NOT NULL,"
                + UserTable.EMAIL + " TEXT NOT NULL,"
                + UserTable.BORN_DATE + " TEXT NOT NULL,"
                + UserTable.USERNAME + " TEXT NOT NULL,"
                + UserTable.PASSWORD + " TEXT NOT NULL)");
    }
}
