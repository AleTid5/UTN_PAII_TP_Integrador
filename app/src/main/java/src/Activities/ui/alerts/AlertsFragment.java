package src.Activities.ui.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.AlertsAdapter;
import src.Activities.Adapters.ManageHistoryAdapter;

public class AlertsFragment extends Fragment {

    private AlertsViewModel alertsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alertsViewModel = new ViewModelProvider(this).get(AlertsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Historial de alertas");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_alerts, mainContent, false);
        mainContent.addView(content);

        alertsViewModel.getAlertList().observe(getViewLifecycleOwner(), alertList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new AlertsAdapter(alertList));
        });

        return root;
    }
}