package src.Database.Migrations;

import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import src.Database.Tables.ContactsTable;

public abstract class ContactsMigration {
    private static final List<List<String>> dataToInsert = Arrays.asList(
            Arrays.asList("Duchas Santa Teresa", "09:00 - 12:30 sábados | Receso en verano por reacondicionamiento | Servicio de ducha caliente (por el momento, únicamente para hombres), desayuno, almuerzo, peluquería, seguimiento médico, ropa limpia y vacunatorio", "duchasensantateresa@gmail.com", "Islas Orcadas y Neuquén 1617, General Pacheco (Tigre)"),
            Arrays.asList("El Refugio", "17:00 - 08:00 (hasta el día siguiente) | Capacidad de 45 camas (hombres y mujeres) | Cuenta con un comedor, una biblioteca y distintos baños", "-", "Enciso 425, Tigre"),
            Arrays.asList("Secretaría de Desarrollo Social", "-", "direcciontrabajosocial@tigre.gob.ar/refugionocturno@tigre.gob.ar", "Av. Hipólito Yrigoyen 566, Tigre"),
            Arrays.asList("COT (Centro de operacion de Tigre)", "Servicio de Emergencias Tigre", "sirve@tigre.gov.ar", "Av. Cazón 1514, Tigre")
    );

    public static void execute(SQLiteDatabase sqLiteDatabase) {
        dataToInsert.forEach(data -> sqLiteDatabase.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s')",
                ContactsTable.TABLE_NAME,
                ContactsTable.NAME,
                ContactsTable.INFORMATION,
                ContactsTable.EMAIL,
                ContactsTable.ADDRESS,
                data.get(0),
                data.get(1),
                data.get(2),
                data.get(3)
        )));
    }
}
