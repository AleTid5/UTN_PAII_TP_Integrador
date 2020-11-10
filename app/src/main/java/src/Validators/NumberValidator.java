package src.Validators;

import java.util.Objects;

public abstract class NumberValidator {

    public static Integer validateDNI(String number) throws Exception {
        numberCannotBeNull(number);

        try {
            number = number.replace(" ", "");

            if (!number.chars().allMatch(Character::isDigit)) {
                throw new Exception("El DNI solo puede contener caracteres numéricos");
            }

            if (number.length() > 8) {
                throw new Exception("El DNI puede contener hasta 8 dígitos");
            }

            return Integer.parseInt(number);
        } catch (Exception e) {
            throw new Exception("El DNI solo puede contener caracteres numéricos de hasta 8 digitos");
        }
    }

    public static Integer wrapNumber(Object number) {
        try {
            return ((Long) number).intValue();
        } catch (Exception e) {
            return null;
        }
    }

    public static String numberToString(Object number) {
        try {
            return Integer.toString((int) number);
        } catch (Exception e) {
            return null;
        }
    }

    private static void numberCannotBeNull(String number) throws Exception {
        if (number == null) {
            throw new Exception("No se ha ingresado fecha de nacimiento");
        }
    }
}
