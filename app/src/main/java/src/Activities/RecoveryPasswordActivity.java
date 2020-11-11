package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import components.Snackbar.CustomSnackbar;
import src.Activities.ui.setup_account.UserViewModel;
import src.Services.Entities.PasswordRecoveryService;
import src.Validators.NumberValidator;

public class RecoveryPasswordActivity extends AppCompatActivity {

    private View contextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getLiveUser().observe(this, user -> {
            if (this.contextView == null) return;

            if (user == null) {
                new CustomSnackbar(this.contextView, "El DNI ingresado no pertenece a ninguna cuenta").danger();
            } else {
                new CustomSnackbar(this.contextView, "Revise su casilla de correos, le hemos enviado su contraseña").success();
            }

            unblockButton();
        });
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRecoveryPassword(View view) {
        try {
            String textDNI = ((TextView)this.findViewById(R.id.input_dni)).getText().toString().trim();
            Integer DNI = NumberValidator.validateDNI(textDNI);
            blockButton();
            this.contextView = view;
            PasswordRecoveryService.recoverPassword(DNI);
        } catch(Exception ex) {
            new CustomSnackbar(view,ex.getMessage()).danger();
        }
    }

    private void blockButton() {
        Button button = findViewById(R.id.button_recover_password);
        button.setEnabled(false);
        button.setText("Recuperando contraseña...");
    }

    private void unblockButton() {
        Button button = findViewById(R.id.button_recover_password);
        button.setEnabled(true);
        button.setText("Recuperar contraseña");
    }
}