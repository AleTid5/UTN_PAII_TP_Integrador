package src.Activities.ui.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tp_cuatrimestral.R;

import java.util.Arrays;
import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Activities.ui.history_alerts.HistoryAlertsFragment;
import src.Builders.AlertBuilder;
import src.Models.Alert;
import src.Services.Entities.AlertService;

public class AlertsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Generar alerta");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_alerts, mainContent, false);
        mainContent.addView(content);

        ((TextView) root.findViewById(R.id.button_save)).setOnClickListener(view -> onSave());

        ((TextView) root.findViewById(R.id.link_view_history)).setOnClickListener(
                view -> {
                    mainContent.removeView(content);
                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_content, HistoryAlertsFragment.newInstance())
                            .commit();
                    ((TextView) root.findViewById(R.id.main_title)).setText("Historial de alertas");
                }
        );

        return root;
    }

    private void onSave() {
        try {
            Alert alert = new AlertBuilder()
                    .setNameAndLastName((TextView) requireView().findViewById(R.id.input_name))
                    .setPlace((TextView) requireView().findViewById(R.id.input_place))
                    .setContact((TextView) requireView().findViewById(R.id.input_contact))
                    .setObservations((TextView) requireView().findViewById(R.id.input_observations))
                    .build();

            AlertService.save(alert);
            this.cleanView();
            new CustomSnackbar(requireView(), "¡Se ha generado la alerta exitosamente!").success();
        } catch (Exception e) {
            new CustomSnackbar(requireView(), Objects.requireNonNull(e.getMessage())).danger();
        }
    }

    private void cleanView() {
        Arrays.asList(
                R.id.input_name,
                R.id.input_place,
                R.id.input_contact,
                R.id.input_observations)
                .forEach(id -> ((TextView) requireView().findViewById(id)).setText(""));
    }
}