package src.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import src.Database.Migrations.ContactPhonesMigration;
import src.Database.Migrations.ContactsMigration;
import src.Database.Migrations.PhonesMigration;
import src.Database.Migrations.SkeletonMigration;
import src.Interfaces.Entity;
import src.Services.ContextManagerService;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "obras_en_la_calle.db";

    public DatabaseManager() {
        super(ContextManagerService.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        //ContextManagerService.getContext().deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        SkeletonMigration.execute(sqLiteDatabase);
        ContactsMigration.execute(sqLiteDatabase);
        PhonesMigration.execute(sqLiteDatabase);
        ContactPhonesMigration.execute(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long save(String tableName, Entity entity) {
        return this.getWritableDatabase().insert(
                tableName,
                null,
                entity.toContentValues());
    }

    public long save(String tableName, ContentValues contentValues) {
        return this.getWritableDatabase().insert(
                tableName,
                null,
                contentValues);
    }

    public Cursor find(String query) {
        return this.getReadableDatabase().rawQuery(query, null);
    }

    public void remove(String table, String where, String[] values) {
        this.getWritableDatabase().delete(table, where, values);
    }
}
