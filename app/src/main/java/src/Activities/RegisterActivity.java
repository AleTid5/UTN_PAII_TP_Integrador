package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import java.util.Arrays;
import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Activities.ui.setup_account.UserViewModel;
import src.Builders.UserBuilder;
import src.Enums.StatusEnum;
import src.Models.User;
import src.Services.Entities.UserService;

public class RegisterActivity extends AppCompatActivity {

    private View contextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StatusViewModel statusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);

        statusViewModel.getLiveStatus().observe(this, status -> {
            if (this.contextView == null || status == null || StatusEnum.NO_ACTION.equals(status)) return;

            if (StatusEnum.DUPLICATED_EMAIL.equals(status)) {
                new CustomSnackbar(this.contextView, "Lo sentimos, el E-Mail ingresado se encuentra registrado").danger();
            } else if (StatusEnum.DUPLICATED_DNI.equals(status)) {
                new CustomSnackbar(this.contextView, "Lo sentimos, el DNI ingresado pertenece a otro usuario").danger();
            }  else if (StatusEnum.TRANSACTION_FAILED.equals(status)) {
                new CustomSnackbar(this.contextView, "Lo sentimos, hubo un problema en el registro. Intente nuevamente.").danger();
            } else {
                new CustomSnackbar(this.contextView, "¡El usuario ha sido registrado exitosamente!").success();
                // this.cleanView(); // Todo: Uncomment this.
            }

            unblockButton();
        });
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onRegister(View view) {
        try {
            User user = new UserBuilder()
                    .setNameAndLastName(this.findViewById(R.id.input_name))
                    .setDNI(this.findViewById(R.id.input_dni))
                    .setBornDate(this.findViewById(R.id.input_born_date))
                    .setUserName(this.findViewById(R.id.input_username))
                    .setEmail(this.findViewById(R.id.input_email))
                    .setPassword(this.findViewById(R.id.input_password), this.findViewById(R.id.input_password_repeat))
                    .build();

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

    private void cleanView() {
        Arrays.asList(
                R.id.input_name,
                R.id.input_dni,
                R.id.input_born_date,
                R.id.input_username,
                R.id.input_email,
                R.id.input_password,
                R.id.input_password_repeat)
                .forEach(id -> ((TextView) this.findViewById(id)).setText(""));
    }
}