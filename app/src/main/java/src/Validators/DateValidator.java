package src.Validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public abstract class DateValidator {

    public static String getThisMomentAsDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES")).format(new Date());
    }

    public static String validateDateNotGreaterThanToday(String dateToValidate) throws Exception {
        dateCannotBeNull(dateToValidate);
        final String DATE_PATTERN = "dd/MM/yyyy";
        final Integer MAX_YEARS = 100;

        try {
            if (dateToValidate.length() != DATE_PATTERN.length()) {
                throw new ParseException(null, 0);
            }

            final Date parsedDate = new SimpleDateFormat(DATE_PATTERN, new Locale("es", "ES")).parse(dateToValidate);

            if (Objects.requireNonNull(parsedDate).compareTo(new Date()) > 0) {
                throw new Exception("La fecha ingresada debe ser menor a la fecha actual");
            }

            Calendar oldCalendar = getCalendarMinusYears(MAX_YEARS);

            if (Objects.requireNonNull(parsedDate).compareTo(oldCalendar.getTime()) < 0) {
                throw new Exception(String.format(
                        "El año debe ser mayor a %s",
                        oldCalendar.get(Calendar.YEAR)
                ));
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

    private static Calendar getCalendarMinusYears(Integer year) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -year);
        return c;
    }
}
