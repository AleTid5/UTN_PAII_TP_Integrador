package src.Validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public abstract class DateValidator {

    public static String getThisMomentAsDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES")).format(new Date());
    }

    public static String validateDateNotGreaterThanToday(String dateToValidate) throws Exception {
        dateCannotBeNull(dateToValidate);
        final String datePattern = "dd/MM/yyyy";

        try {
            if (dateToValidate.length() != datePattern.length()) {
                throw new ParseException(null, 0);
            }

            final Date parsedDate = new SimpleDateFormat(datePattern, new Locale("es", "ES")).parse(dateToValidate);

            if (Objects.requireNonNull(parsedDate).compareTo(new Date()) > 0) {
                throw new Exception("La fecha ingresada debe ser menor a la fecha actual");
            }
        } catch (ParseException e) {
            throw new Exception("La fecha ingresada es incorrecta. Recuerde que el formato debe ser dd/mm/aaaa, ej: 25/05/1981");
        }

        return dateToValidate;
    }

    private static void dateCannotBeNull(String dateToValidate) throws Exception {
        if (dateToValidate == null) {
            throw new Exception("No se ha ingresado fecha de nacimiento");
        }
    }
}
