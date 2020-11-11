package src.Validators;

import java.util.regex.Pattern;

public abstract class EmailValidator {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String validateEmail(String email) throws Exception {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
            throw new Exception("Debe ingresar un E-Mail válido");
        }

        return email;
    }
}
