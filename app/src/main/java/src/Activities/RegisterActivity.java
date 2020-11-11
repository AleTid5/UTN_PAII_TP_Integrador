package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Activities.ui.setup_account.UserViewModel;
import src.Builders.UserBuilder;
import src.Models.User;
import src.Services.Entities.UserService;

public class RegisterActivity extends AppCompatActivity {

    private View contextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getLiveUser().observe(this, user -> {
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
}