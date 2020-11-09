package src.Activities.ui.history_alerts;

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

import src.Activities.Adapters.AlertAdapter;

public class HistoryAlertsFragment extends Fragment {

    private HistoryAlertsViewModel historyAlertsViewModel;

    public static HistoryAlertsFragment newInstance() {
        return new HistoryAlertsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyAlertsViewModel = new ViewModelProvider(this).get(HistoryAlertsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_alerts, container, false);

        historyAlertsViewModel.getAlertList().observe(getViewLifecycleOwner(), alertList -> {
            if (alertList != null) {
                GridView gridView = requireView().findViewById(R.id.grid_view);
                gridView.setAdapter(new AlertAdapter(alertList));
            }
        });

        return root;
    }
}