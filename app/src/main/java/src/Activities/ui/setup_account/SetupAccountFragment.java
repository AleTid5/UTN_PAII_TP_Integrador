package src.Activities.ui.setup_account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Activities.ui.blocked_users.BlockedUsersFragment;
import src.Builders.UserBuilder;
import src.Models.User;
import src.Services.Entities.UserService;
import src.Services.Entities.UserSessionService;
import src.Validators.NumberValidator;

public class SetupAccountFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Configuración de cuenta");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_setup_account, mainContent, false);
        fillForm(content);
        mainContent.addView(content);

        ((Button) content.findViewById(R.id.button_save)).setOnClickListener(this::onSave);

        ((TextView) content.findViewById(R.id.link_blocked_users)).setOnClickListener(view -> {
            mainContent.removeView(content);

            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, BlockedUsersFragment.newInstance())
                    .commit();

            ((TextView) root.findViewById(R.id.main_title)).setText("Desbloquear usuarios");
        });

        userViewModel.getLiveUser().observe(getViewLifecycleOwner(), user -> {
            if (user == null) return;

            if (user.getId() == null) {
                new CustomSnackbar(requireView(), "Lo sentimos, el E-Mail ingresado ya pertenece a otro usuario").danger();
            } else {
                new CustomSnackbar(requireView(), "¡El usuario ha sido modificado exitosamente!").success();
            }

            UserViewModel.onUserChange(null);
            unblockButton(requireView());
        });

        return root;
    }

    private void fillForm(View view) {
        User user = UserSessionService.getUser();
        ((TextView) view.findViewById(R.id.input_name)).setText(user.getNameAndLastName());
        ((TextView) view.findViewById(R.id.input_dni)).setText(NumberValidator.numberToString(user.getDNI()));
        ((TextView) view.findViewById(R.id.input_born_date)).setText(user.getBornDate());
        ((TextView) view.findViewById(R.id.input_username)).setText(user.getUserName());
        ((TextView) view.findViewById(R.id.input_email)).setText(user.getEmail());
        ((TextView) view.findViewById(R.id.input_password)).setText(user.getPassword());
        ((TextView) view.findViewById(R.id.input_password_repeat)).setText(user.getPassword());
    }

    private void onSave(View view) {
        try {
            // Deep Clone para prevenir el pasaje por referencia y actualizados de sesión cuando no se debe
            User currentUser = new User().unwrap(UserSessionService.getUser().wrap());
            currentUser.setId(UserSessionService.getUser().getId());

            User user = new UserBuilder(currentUser)
                    .setNameAndLastName(requireView().findViewById(R.id.input_name))
                    .setDNI(requireView().findViewById(R.id.input_dni))
                    .setBornDate(requireView().findViewById(R.id.input_born_date))
                    .setUserName(requireView().findViewById(R.id.input_username))
                    .setEmail(requireView().findViewById(R.id.input_email))
                    .setPassword(requireView().findViewById(R.id.input_password), requireView().findViewById(R.id.input_password_repeat))
                    .build();

            blockButton(requireView());
            UserService.updateUser(user);
        } catch (Exception e) {
            new CustomSnackbar(requireView(), Objects.requireNonNull(e.getMessage())).danger();
        }
    }

    private void blockButton(View view) {
        Button button = view.findViewById(R.id.button_save);
        button.setEnabled(false);
        button.setText("Actualizando datos...");
    }

    private void unblockButton(View view) {
        Button button = view.findViewById(R.id.button_save);
        button.setEnabled(true);
        button.setText("Guardar");
    }
}