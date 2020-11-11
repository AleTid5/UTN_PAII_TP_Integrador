package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_cuatrimestral.R;

import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Services.ContextManagerService;
import src.Services.Entities.UserService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextManagerService.setContext(this.getBaseContext());
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
        try
        {
            String userName=((TextView)this.findViewById(R.id.input_dni)).getText().toString().trim();
            if (userName.length() == 0){
                throw new Exception("Debe completar el campo del nombre de usuario");
            }
            String password=((TextView)this.findViewById(R.id.input_password)).getText().toString().trim();
            if (password.length() == 0) {
                throw new Exception("Debe completar el campo contraseña");
            }

            UserService.authenticateUser(userName,password);

            startActivity(new Intent(this, SystemActivity.class));
        }
        catch (Exception ex)
        {
            new CustomSnackbar(view, Objects.requireNonNull(ex.getMessage())).danger();
        }
    }



    public void onRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onRecoveryPassword(View view) {
        startActivity(new Intent(this, RecoveryPasswordActivity.class));
    }
}