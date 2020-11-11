package src.Validators;

import com.scottyab.aescrypt.AESCrypt;

import java.util.regex.Pattern;

public abstract class PasswordValidator {
    private static final String hasher = "p4s5w0rd";
    private static final Pattern VALID_PASSWORD_ADDRESS_REGEX = Pattern.compile
            ("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", Pattern.CASE_INSENSITIVE);

    public static void validatePassword(String password) throws Exception {
        if (!VALID_PASSWORD_ADDRESS_REGEX.matcher(password).find()) {
            throw new Exception("La contraseña debe contener 1 número, 1 mayúscula, 1 minúscula y un simbolo especial");
        }
    }

    public static String encrypt(String password) {
        try {
            password = AESCrypt.encrypt(hasher, password);;
        } catch (Exception ignored) {}

        return password;
    }

    public static String decrypt(String password) {
        try {
            password = AESCrypt.decrypt(hasher, password);;
        } catch (Exception ignored) {}

        return password;
    }
}