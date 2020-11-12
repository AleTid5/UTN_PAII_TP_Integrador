package src.Database.Tables;

import android.provider.BaseColumns;

public abstract class BlockedUsersTable implements BaseColumns {
    public static final String TABLE_NAME = "blocked_users";

    public static final String USER_FROM = "user_from";
    public static final String USER_TO = "user_to";
}
