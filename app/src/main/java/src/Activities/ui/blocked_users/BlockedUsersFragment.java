package src.Activities.ui.blocked_users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.BlockedUserAdapter;

public class BlockedUsersFragment extends Fragment {

    private BlockedUsersViewModel blockedUsersViewModel;

    public static BlockedUsersFragment newInstance() {
        return new BlockedUsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        blockedUsersViewModel = new ViewModelProvider(this).get(BlockedUsersViewModel.class);

        View root = inflater.inflate(R.layout.fragment_blocked_users, container, false);

        blockedUsersViewModel.getBlockedUserList().observe(getViewLifecycleOwner(), userList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new BlockedUserAdapter(userList));
        });

        return root;
    }

}