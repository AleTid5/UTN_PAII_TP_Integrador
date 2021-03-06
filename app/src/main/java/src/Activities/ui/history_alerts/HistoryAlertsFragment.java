package src.Activities.ui.history_alerts;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        ProgressBar progressBar = root.findViewById(R.id.loader);

        historyAlertsViewModel.getAlertList().observe(getViewLifecycleOwner(), alertList -> {
            if (alertList != null) {
                GridView gridView = requireView().findViewById(R.id.grid_view);
                gridView.setAdapter(new AlertAdapter(alertList, progressBar));
            }
        });

        HistoryAlertsViewModel.getOwnerStatusList().observe(getViewLifecycleOwner(), status -> {
            ((EditText) root.findViewById(R.id.input_history_filter)).setText("");
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new AlertAdapter(historyAlertsViewModel.getAlertList().getValue(), progressBar));
        });

        historyAlertsViewModel.getFilteredAlertList().observe(getViewLifecycleOwner(), alertList -> {
            if (alertList != null) {
                GridView gridView = requireView().findViewById(R.id.grid_view);
                gridView.setAdapter(new AlertAdapter(alertList, progressBar));
            }
        });

        root.findViewById(R.id.link_my_alerts).setOnClickListener(v -> {
            ((EditText) root.findViewById(R.id.input_history_filter)).setEnabled(false);
            ((TextView) root.findViewById(R.id.link_other_alerts)).setTypeface(null, Typeface.NORMAL);
            ((TextView) root.findViewById(R.id.link_my_alerts)).setTypeface(null, Typeface.BOLD);
            historyAlertsViewModel.toggleOwnerVisibilityAlerts(true);
        });

        root.findViewById(R.id.link_other_alerts).setOnClickListener(v -> {
            ((EditText) root.findViewById(R.id.input_history_filter)).setEnabled(true);
            ((TextView) root.findViewById(R.id.link_other_alerts)).setTypeface(null, Typeface.BOLD);
            ((TextView) root.findViewById(R.id.link_my_alerts)).setTypeface(null, Typeface.NORMAL);
            historyAlertsViewModel.toggleOwnerVisibilityAlerts(false);
        });

        ((EditText) root.findViewById(R.id.input_history_filter)).addTextChangedListener(filterList(historyAlertsViewModel));

        return root;
    }

    private TextWatcher filterList(HistoryAlertsViewModel historyAlertsViewModel) {
        return new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                historyAlertsViewModel.filterByName(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };
    }
}