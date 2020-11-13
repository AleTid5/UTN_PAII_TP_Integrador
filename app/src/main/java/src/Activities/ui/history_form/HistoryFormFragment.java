package src.Activities.ui.history_form;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tp_cuatrimestral.R;

import java.util.Objects;

import components.Snackbar.CustomSnackbar;
import src.Activities.SystemActivity;
import src.Builders.HistoryFormBuilder;
import src.Models.History;
import src.Services.Entities.HistoryService;
import src.Validators.DateValidator;
import src.Validators.NumberValidator;

public class HistoryFormFragment extends Fragment {

    private static History history;

    public static HistoryFormFragment newInstance(History history) {
        HistoryFormFragment.history = history;

        return new HistoryFormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history_form, container, false);

        this.fillForm(root);

        ((TextView) root.findViewById(R.id.button_save)).setOnClickListener(v -> onSave());

        ((TextView) root.findViewById(R.id.link_cancel))
                .setOnClickListener(v -> SystemActivity.performClick(R.id.nav_manage_history));

        return root;
    }

    private void onSave() {
        try {
            History history = new HistoryFormBuilder()
                    .setNameAndLastName((TextView) requireView().findViewById(R.id.input_name))
                    .setDNI((TextView) requireView().findViewById(R.id.input_dni))
                    .setBornDate((TextView) requireView().findViewById(R.id.input_born_date))
                    .setPhoneNumber((TextView) requireView().findViewById(R.id.input_phone))
                    .setObservations((TextView) requireView().findViewById(R.id.input_observations))
                    .build();

            if (HistoryFormFragment.history == null) {
                history.setCreatedDate(DateValidator.getThisMomentAsDate());
                HistoryService.save(history);
            } else {
                history.setId(HistoryFormFragment.history.getId());
                HistoryService.update(history);
            }

            new CustomSnackbar(requireView(), "¡La operación ha sido exitosa!").success();
            SystemActivity.performClick(R.id.nav_manage_history);
        } catch (Exception e) {
            new CustomSnackbar(requireView(), Objects.requireNonNull(e.getMessage())).danger();
        }
    }

    @SuppressLint("SetTextI18n")
    private void fillForm(View root) {
        if (HistoryFormFragment.history != null) {
            ((TextView) root.findViewById(R.id.input_name)).setText(HistoryFormFragment.history.getNameAndLastName());
            ((TextView) root.findViewById(R.id.input_dni)).setText(NumberValidator.numberToString(HistoryFormFragment.history.getDNI()));
            ((TextView) root.findViewById(R.id.input_born_date)).setText(HistoryFormFragment.history.getBornDate());
            ((TextView) root.findViewById(R.id.input_phone)).setText(HistoryFormFragment.history.getPhoneNumber());
            ((TextView) root.findViewById(R.id.input_observations)).setText(HistoryFormFragment.history.getObservations());
        }
    }
}