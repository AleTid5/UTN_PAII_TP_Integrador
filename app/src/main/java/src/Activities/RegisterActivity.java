package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Models.User;
import src.Services.Entities.UserService;
import src.Validators.DateValidator;
import src.Validators.EmailValidator;
import src.Validators.NumberValidator;
import src.Validators.PasswordValidator;

public class RegisterActivity extends AppCompatActivity {

    private View contextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UnauthorizedViewModel unauthorizedViewModel = new ViewModelProvider(this).get(UnauthorizedViewModel.class);

        unauthorizedViewModel.getLiveUser().observe(this, user -> {
            if (this.contextView == null) return;

            if (user == null) {
                new CustomSnackbar(this.contextView, "Lo sentimos, el E-Mail ingresado ya pertenece a otro usuario").danger();
            } else {
                new CustomSnackbar(this.contextView, "¡El usuario ha sido registrado exitosamente!").success();
            }

            unblockButton();
        });
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRegister(View view) {
        try {
            User user = new User();
            String name = ((TextView)this.findViewById(R.id.input_name)).getText().toString().trim();
            String textDNI = ((TextView)this.findViewById(R.id.input_dni)).getText().toString().trim();
            String date = ((TextView)this.findViewById(R.id.input_born_date)).getText().toString().trim();
            String username = ((TextView)this.findViewById(R.id.input_username)).getText().toString().trim();
            String email = ((TextView)this.findViewById(R.id.input_email)).getText().toString().trim();
            String password = ((TextView)this.findViewById(R.id.input_password)).getText().toString().trim();
            String passwordRepeat = ((TextView)this.findViewById(R.id.input_password_repeat)).getText().toString().trim();

            if (name.length() < 5) {
                throw new Exception("El nombre no puede ser menor a 5 caracteres");
            }

            Integer DNI = NumberValidator.validateDNI(textDNI);
            DateValidator.validateDateNotGreaterThanToday(date);

            if (username.length() < 5) {
                throw new Exception("El Nombre de Usuario no puede ser menor a 5 caracteres");
            }

            EmailValidator.validateEmail(email);
            PasswordValidator.validatePassword(password);

            if (!password.equals(passwordRepeat)){
                throw new Exception("Las contraseñas no coinciden");
            }

            user.setNameAndLastName(name);
            user.setDNI(DNI);
            user.setBornDate(date);
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);

            blockButton();
            this.contextView = view;
            UserService.registerUser(user);
        } catch (Exception e) {
            new CustomSnackbar(view,Objects.requireNonNull(e.getMessage())).danger();
        }
    }

    private void blockButton() {
        Button button = findViewById(R.id.button_register);
        button.setEnabled(false);
        button.setText("Registrando usuario...");
    }

    private void unblockButton() {
        Button button = findViewById(R.id.button_register);
        button.setEnabled(true);
        button.setText("Registrarme");
    }
}