package src.Activities.ui.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import src.Activities.ui.history_alerts.HistoryAlertsFragment;

public class AlertsFragment extends Fragment {

    private AlertsViewModel alertsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alertsViewModel = new ViewModelProvider(this).get(AlertsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Generar alerta");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_alerts, mainContent, false);
        mainContent.addView(content);

        ((FloatingActionButton) root.findViewById(R.id.button_view_history)).setOnClickListener(
                view -> {
                    mainContent.removeView(content);
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_content, HistoryAlertsFragment.newInstance(), "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                    ((TextView) root.findViewById(R.id.main_title)).setText("Historial de alertas");
                }
        );

        return root;
    }
}