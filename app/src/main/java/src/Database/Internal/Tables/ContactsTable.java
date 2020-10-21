package src.Database.Internal.Tables;

import android.provider.BaseColumns;

public abstract class ContactsTable implements BaseColumns {
    public static final String TABLE_NAME = "contactos";

    public static final String NAME = "nombre";
    public static final String INFORMATION = "informacion";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "direccion";
}
