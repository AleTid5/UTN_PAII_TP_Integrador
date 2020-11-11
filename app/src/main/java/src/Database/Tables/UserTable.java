package src.Database.Tables;

import android.provider.BaseColumns;

public abstract class UserTable implements BaseColumns {
    public static final String TABLE_NAME = "users";

    public static final String ID = "id";
    public static final String BORN_DATE = "born_date";
    public static final String DNI = "dni";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
}
