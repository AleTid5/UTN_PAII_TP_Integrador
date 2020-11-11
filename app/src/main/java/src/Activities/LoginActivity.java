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
import src.Activities.ui.setup_account.UserViewModel;
import src.Services.ContextManagerService;
import src.Services.Entities.UserService;
import src.Services.Entities.UserSessionService;
import src.Validators.EmailValidator;

public class LoginActivity extends AppCompatActivity {

    private View contextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextManagerService.setContext(this.getBaseContext());
        setContentView(R.layout.activity_login);
        checkUserIsLoggedIn();
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getLiveUser().observe(this, user -> {
            if (this.contextView == null) return;

            if (user == null) {
                new CustomSnackbar(this.contextView, "Las credenciales ingresadas son inválidas").danger();
            } else {
                startActivity(new Intent(this, SystemActivity.class));
            }

            unblockButton();
        });
    }

    public void onLogin(View view) {
        try {
            String email = ((TextView) this.findViewById(R.id.input_email)).getText().toString().trim();
            String password = ((TextView) this.findViewById(R.id.input_password)).getText().toString().trim();

            EmailValidator.validateEmail(email);

            if (password.length() == 0) {
                throw new Exception("Debe completar el campo contraseña");
            }

            blockButton();
            this.contextView = view;
            UserService.authenticateUser(email, password);
        } catch (Exception ex) {
            new CustomSnackbar(view, Objects.requireNonNull(ex.getMessage())).danger();
        }
    }

    public void onRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onRecoveryPassword(View view) {
        startActivity(new Intent(this, RecoveryPasswordActivity.class));
    }

    private void blockButton() {
        Button button = findViewById(R.id.button_login);
        button.setEnabled(false);
        button.setText("Iniciando sesión...");
    }

    private void unblockButton() {
        Button button = findViewById(R.id.button_login);
        button.setEnabled(true);
        button.setText("Iniciar sesión");
    }

    private void checkUserIsLoggedIn() {
        try {
            UserSessionService.getUser();
            startActivity(new Intent(this, SystemActivity.class));
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
    }
}