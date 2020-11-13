package src.Activities.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Dashboard");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_dashboard, mainContent, false);
        mainContent.addView(content);

        dashboardViewModel.getLiveTotal().observe(getViewLifecycleOwner(), total -> {
            if (total == null) return;

            ProgressBar progressBar = root.findViewById(R.id.progressBar_history_reports);
            progressBar.setIndeterminate(false);
            progressBar.setProgress(dashboardViewModel.getLiveHistoryReportsTotal());
            progressBar.setMax(total);
            ((TextView) root.findViewById(R.id.text_history_reports)).setText(String.format(
                    "Cantidad de historiales (%s)",
                    dashboardViewModel.getLiveHistoryReportsTotal()
            ));

            ProgressBar progressBar2 = root.findViewById(R.id.progressBar_new_alerts);
            progressBar2.setIndeterminate(false);
            progressBar2.setProgress(dashboardViewModel.getLiveAlertsTotal());
            progressBar2.setMax(total);
            ((TextView) root.findViewById(R.id.text_new_alerts)).setText(String.format(
                    "Alertas totales (%s)",
                    dashboardViewModel.getLiveAlertsTotal()
            ));

            ProgressBar progressBar3 = root.findViewById(R.id.progressBar_blocked_users);
            progressBar3.setIndeterminate(false);
            progressBar3.setProgress(dashboardViewModel.getLiveBlockedUsersTotal());
            progressBar3.setMax(total);
            ((TextView) root.findViewById(R.id.text_blocked_users)).setText(String.format(
                    "Usuarios bloqueados (%s)",
                    dashboardViewModel.getLiveBlockedUsersTotal()
            ));
        });

        return root;
    }
}