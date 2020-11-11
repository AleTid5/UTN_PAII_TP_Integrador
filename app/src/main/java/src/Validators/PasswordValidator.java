package src.Validators;

import java.util.regex.Pattern;

public abstract class PasswordValidator {
    private static final Pattern VALID_PASSWORD_ADDRESS_REGEX = Pattern.compile
            ("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

    public static void validatePassword(String password) throws Exception {
        if (!VALID_PASSWORD_ADDRESS_REGEX.matcher(password).find()) {
            throw new Exception("Debe ingresar Una Contraseña válida");
        }
    }
}