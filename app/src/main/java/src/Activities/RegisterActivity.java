package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_cuatrimestral.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Validators.DateValidator;
import src.Validators.EmailValidator;
import src.Validators.PasswordValidator;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRegister(View view) {
        try {
            String name=((TextView)this.findViewById(R.id.input_name)).getText().toString().trim();
            if (name.length()<5){
                throw new Exception("El nombre no puede ser menor a 5 caracteres");
            }
            String dni=((TextView)this.findViewById(R.id.input_dni)).getText().toString().trim();
            if (dni.length()<7) {
                throw new Exception("Ingrese un dni valido");
            }
            String date=((TextView)this.findViewById(R.id.input_born_date)).getText().toString().trim();
            DateValidator.validateDateNotGreaterThanToday(date);

            String username=((TextView)this.findViewById(R.id.input_username)).getText().toString().trim();
            if (username.length()<5) {
                throw new Exception("El Nombre de Usuario no puede ser menor a 5 caracteres");
            }
            String email=((TextView)this.findViewById(R.id.input_email)).getText().toString().trim();
            EmailValidator.validateEmail(email);

            String password=((TextView)this.findViewById(R.id.input_password)).getText().toString().trim();
            PasswordValidator.validatePassword(password);

            String passwordRepeat=((TextView)this.findViewById(R.id.input_password_repeat)).getText().toString().trim();
            if (!password.equals(passwordRepeat)){
                throw new Exception("Las Contraseñas no coinciden");
            }

        }catch (Exception e) {
            new CustomSnackbar(view,Objects.requireNonNull(e.getMessage())).danger();
        }
    }
}