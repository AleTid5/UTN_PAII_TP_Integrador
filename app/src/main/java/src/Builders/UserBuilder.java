package src.Builders;

import android.widget.TextView;

import src.Models.User;
import src.Validators.DateValidator;
import src.Validators.EmailValidator;
import src.Validators.NumberValidator;
import src.Validators.PasswordValidator;

public class UserBuilder {
    private static User user;

    public UserBuilder(User user) {
        UserBuilder.user = user;
    }

    public UserBuilder() {
        UserBuilder.user = new User();
    }

    public UserBuilder setNameAndLastName(TextView textName) throws Exception {
        String name = textName.getText().toString().trim();

        if (name.length() < 5) {
            throw new Exception("El nombre no puede ser menor a 5 caracteres");
        }

        user.setNameAndLastName(name);

        return this;
    }

    public UserBuilder setDNI(TextView textDNI) throws Exception {
        Integer DNI = NumberValidator.validateDNI(textDNI.getText().toString().trim());

        user.setDNI(DNI);

        return this;
    }

    public UserBuilder setBornDate(TextView textDate) throws Exception {
        String date = textDate.getText().toString().trim();

        user.setBornDate(DateValidator.validateDateNotGreaterThanToday(date));

        return this;
    }

    public UserBuilder setUserName(TextView textUserName) throws Exception {
        String userName = textUserName.getText().toString().trim();

        if (userName.length() < 5) {
            throw new Exception("El nombre de usuario no puede ser menor a 5 caracteres");
        }

        if (userName.contains(" ")) {
            throw new Exception("El nombre de usuario no puede contener espacios");
        }

        user.setUserName(userName);

        return this;
    }

    public UserBuilder setEmail(TextView textEmail) throws Exception {
        String email = textEmail.getText().toString().trim();

        user.setEmail(EmailValidator.validateEmail(email));

        return this;
    }

    public UserBuilder setPassword(TextView textPassword, TextView textPasswordRepeat) throws Exception {
        String password = textPassword.getText().toString().trim();
        String passwordRepeat = textPasswordRepeat.getText().toString().trim();

        PasswordValidator.validatePassword(password);

        if (!password.equals(passwordRepeat)){
            throw new Exception("Las contraseñas no coinciden");
        }

        user.setPassword(password);

        return this;
    }

    public User build() {
        return user;
    }
}
