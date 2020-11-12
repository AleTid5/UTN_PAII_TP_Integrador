package src.Activities.ui.blocked_users;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import components.Snackbar.CustomSnackbar;
import src.Activities.Adapters.BlockedUserAdapter;
import src.Activities.SystemActivity;

public class BlockedUsersFragment extends Fragment {

    private BlockedUsersViewModel blockedUsersViewModel;

    public static BlockedUsersFragment newInstance() {
        return new BlockedUsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        blockedUsersViewModel = new ViewModelProvider(this).get(BlockedUsersViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_blocked_users, container, false);

        blockedUsersViewModel.getBlockedUserList().observe(getViewLifecycleOwner(), userList -> {
            if (userList == null) return;

            try {
                if (userList.get(0) == null) {
                    noUsersBlocked(root);
                    return;
                }
            } catch (Exception ignored) {}

            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new BlockedUserAdapter(userList));

            if (!userList.isEmpty()) {
                try {
                    ((ViewManager) root.findViewById(R.id.loader).getParent()).removeView(root.findViewById(R.id.loader));
                } catch (Exception ignored) {}
            }
        });

        ((Button) root.findViewById(R.id.button_unblock)).setOnClickListener(view -> {
            new AlertDialog.Builder(view.getContext())
                    .setTitle("¿Desbloquear alertas?")
                    .setMessage("¿Desea desbloquear a los usuarios seleccionados?")
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        blockedUsersViewModel.unblockUsers();
                        new CustomSnackbar(view, "¡Usuarios desbloqueados!");
                    })
                    .setNegativeButton("Cancelar", null)
                    .create().show();
        });

        ((TextView) root.findViewById(R.id.link_go_back)).setOnClickListener(view -> SystemActivity.performClick(R.id.nav_setup_account));

        return root;
    }

    private void noUsersBlocked(View root) {
        try {
            ((ViewManager) root.findViewById(R.id.loader).getParent()).removeView(root.findViewById(R.id.loader));
            ((ViewManager) root.findViewById(R.id.grid_view).getParent()).removeView(root.findViewById(R.id.grid_view));
            Button button = root.findViewById(R.id.button_unblock);
            button.setText("No hay usuarios bloqueados");
            button.setEnabled(false);
        } catch (Exception ignored) {}
    }

}