package src.Activities.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import src.Activities.ui.history_alerts.HistoryAlertsViewModel;
import src.Models.Alert;
import src.Services.Entities.UserSessionService;

public class AlertAdapter extends BaseAdapter {
    private List<Alert> elements;
    private ProgressBar progressBarRef;
    private Boolean isLoading = true;

    public AlertAdapter(List<Alert> elements, ProgressBar progressBarRef) {
        this.elements = getFilteredList(elements);
        this.progressBarRef = progressBarRef;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Alert getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View newView = view != null ? view : inflater.inflate(R.layout.adapter_alerts,null);

        Alert alert = getItem(i);

        newView.findViewById(R.id.link_view_alert).setOnClickListener(v -> {
            Dialog dialog = new Dialog(newView.getContext());
            dialog.setContentView(R.layout.dialog_alert);
            dialog.setCancelable(false);
            dialog.findViewById(R.id.button_accept).setOnClickListener(v2 -> dialog.dismiss());
            fillPopup(dialog, alert, i);
            dialog.show();
        });

        if (UserSessionService.getUser().getId().equals(alert.getUser().getId())) {
            ((TextView) newView.findViewById(R.id.text_name)).setText(String.format("Alerta %s", i + 1));
            ((ViewGroup) newView.findViewById(R.id.adapter_alert_layout)).removeView(newView.findViewById(R.id.link_block_user));
        } else {
            ((TextView) newView.findViewById(R.id.text_name)).setText(String.format("Alerta %s | %s", i + 1, alert.getUser().getEmail()));
            newView.findViewById(R.id.link_block_user).setOnClickListener(v -> new AlertDialog.Builder(v.getContext())
                    .setTitle("¿Bloquear las alertas de éste usuario?")
                    .setMessage("¿Desea bloquear al usuario que genera este tipo de alertas? (Recuerde que puede desbloquearlo desde el menú de configuración de cuenta)")
                    .setPositiveButton("Aceptar", (dialog, which) -> HistoryAlertsViewModel.blockUser(alert.getUser().getId()))
                    .setNegativeButton("Cancelar", null)
                    .create().show());
        }

        if (this.isLoading) {
            this.isLoading = false;
            try {
                ((ViewManager) this.progressBarRef.getParent()).removeView(this.progressBarRef);
            } catch (Exception ignored) {}
        }

        return newView;
    }

    private List<Alert> getFilteredList(List<Alert> elements) {
        return Objects.requireNonNull(elements)
                .stream()
                .filter(alert -> HistoryAlertsViewModel.getOwnerStatusList().getValue() == alert.getUser().getId().equals(UserSessionService.getUser().getId()))
                .collect(Collectors.toList());
    }

    private void fillPopup(Dialog dialog, Alert alert, Integer viewIndex) {
        if (UserSessionService.getUser().getId().equals(alert.getUser().getId())) {
        ((TextView) dialog.findViewById(R.id.dialog_alert_title)).setText(String.format("Alerta %s", viewIndex + 1));
        } else {
        ((TextView) dialog.findViewById(R.id.dialog_alert_title)).setText(String.format("Alerta %s\n%s", viewIndex + 1, alert.getUser().getEmail()));
        }

        try {
            if (alert.getNameAndLastName().length() == 0) throw new Exception();
            ((TextView) dialog.findViewById(R.id.text_name)).setText(alert.getNameAndLastName());
        } catch (Exception e) {
            ((ViewGroup) dialog.findViewById(R.id.dialog_alert)).removeView(dialog.findViewById(R.id.dialog_alert_name));
        }

        try {
            if (alert.getPlace().length() == 0) throw new Exception();
            ((TextView) dialog.findViewById(R.id.text_place)).setText(alert.getPlace());
        } catch (Exception e) {
            ((ViewGroup) dialog.findViewById(R.id.dialog_alert)).removeView(dialog.findViewById(R.id.dialog_alert_place));
        }

        try {
            if (alert.getContact().length() == 0) throw new Exception();
            ((TextView) dialog.findViewById(R.id.text_contact)).setText(alert.getContact());
        } catch (Exception e) {
            ((ViewGroup) dialog.findViewById(R.id.dialog_alert)).removeView(dialog.findViewById(R.id.dialog_alert_contact));
        }

        try {
            if (alert.getObservations().length() == 0) throw new Exception();
            ((TextView) dialog.findViewById(R.id.text_observations)).setText(alert.getObservations());
        } catch (Exception e) {
            ((ViewGroup) dialog.findViewById(R.id.dialog_alert)).removeView(dialog.findViewById(R.id.dialog_alert_observations));
        }
    }
}
