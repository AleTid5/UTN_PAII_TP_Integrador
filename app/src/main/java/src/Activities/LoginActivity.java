package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_cuatrimestral.R;

import src.Models.User;
import src.Services.ContextManagerService;
import src.Services.SessionService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextManagerService.setContext(this.getBaseContext());
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
        // Logica de inicio de sesión
        User user = new User(null, null, null, null, null, null);
        user.setId("123");
        SessionService.setUser(user);
        startActivity(new Intent(this, SystemActivity.class));
    }

    public void onRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onRecoveryPassword(View view) {
        startActivity(new Intent(this, RecoveryPasswordActivity.class));
    }
}