package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_cuatrimestral.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import src.Validators.EmailValidator;

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
            String name=((TextView)view.findViewById(R.id.input_name)).getText().toString().trim();
            if (name.length()<5){
                throw new Exception("El nombre no puede ser menor a 5 caracteres");
            }
            String dni=((TextView)view.findViewById(R.id.input_dni)).getText().toString().trim();
            if (dni.length()<7) {
                throw new Exception("Ingrese un dni valido");
            }
            String username=((TextView)view.findViewById(R.id.input_username)).getText().toString().trim();
            if (username.length()<5) {
                throw new Exception("El Nombre de Usuario no puede ser menor a 5 caracteres");
            }
            String email=((TextView)view.findViewById(R.id.input_email)).getText().toString().trim();
            EmailValidator.validateEmail(email);

        }catch (Exception e) {
            Snackbar.make(view, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}