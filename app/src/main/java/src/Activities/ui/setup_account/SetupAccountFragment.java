package src.Activities.ui.setup_account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tp_cuatrimestral.R;

public class SetupAccountFragment extends Fragment {

    private SetupAccountViewModel setupAccountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setupAccountViewModel =
                ViewModelProviders.of(this).get(SetupAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        final TextView textView = root.findViewById(R.id.main_title);

        setupAccountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }
}