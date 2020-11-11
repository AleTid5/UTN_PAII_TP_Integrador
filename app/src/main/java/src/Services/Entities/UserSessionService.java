package src.Services.Entities;

import android.database.Cursor;

import src.Database.DatabaseManager;
import src.Database.Tables.UserTable;
import src.Models.User;

public abstract class UserSessionService {
    private static User user = null;

    public static void setUser(User user) {
        cleanSession();
        new DatabaseManager().save(UserTable.TABLE_NAME, user);
    }

    public static User getUser() {
        if (user != null) {
            return user;
        }

        Cursor cursor = new DatabaseManager().find(String.format("SELECT * FROM %s", UserTable.TABLE_NAME));

        cursor.moveToFirst();

        user = new User();
        user.setId(cursor.getString(cursor.getColumnIndex(UserTable.ID)));
        user.setNameAndLastName(cursor.getString(cursor.getColumnIndex(UserTable.NAME)));
        user.setDNI(cursor.getInt(cursor.getColumnIndex(UserTable.DNI)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.EMAIL)));
        user.setBornDate(cursor.getString(cursor.getColumnIndex(UserTable.BORN_DATE)));
        user.setUserName(cursor.getString(cursor.getColumnIndex(UserTable.USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.PASSWORD)));

        return user;
    }

    public static void cleanSession() {
        user = null;
        new DatabaseManager().remove(UserTable.TABLE_NAME, null, null);
    }
}
