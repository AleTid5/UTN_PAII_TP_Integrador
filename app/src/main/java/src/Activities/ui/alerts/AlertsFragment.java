package src.Activities.ui.alerts;

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

public class AlertsFragment extends Fragment {

    private AlertsViewModel alertsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alertsViewModel = new ViewModelProvider(this).get(AlertsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        final TextView textView = root.findViewById(R.id.main_title);

        alertsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }
}