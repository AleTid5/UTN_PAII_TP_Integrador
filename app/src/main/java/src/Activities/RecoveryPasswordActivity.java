package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_cuatrimestral.R;

import components.Snackbar.CustomSnackbar;
import src.Services.Entities.PasswordRecoveryService;
import src.Validators.NumberValidator;

public class RecoveryPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password);
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRecoveryPassword(View view) {
        try{
            String textDNI=((TextView)this.findViewById(R.id.input_dni)).getText().toString().trim();
            Integer DNI=NumberValidator.validateDNI(textDNI);
            PasswordRecoveryService.recoverPassword(DNI);

        } catch(Exception ex){
            new CustomSnackbar(view,ex.getMessage()).danger();
        }
    }
}