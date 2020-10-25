package src.Activities.ui.manage_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.tp_cuatrimestral.R;

public class ManageHistoryFragment extends Fragment {

    private ManageHistoryViewModel manageHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        manageHistoryViewModel = new ViewModelProvider(this).get(ManageHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        final TextView textView = root.findViewById(R.id.main_title);

        manageHistoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }
}