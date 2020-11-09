package src.Activities.ui.history_form;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import src.Activities.SystemActivity;
import src.Builders.HistoryFormBuilder;
import src.Models.History;
import src.Services.Entities.AlertService;
import src.Services.Entities.HistoryService;

public class HistoryFormFragment extends Fragment {

    private HistoryFormViewModel historyFormViewModel;
    private static History history;

    public static HistoryFormFragment newInstance(History history) {
        if (history != null) {
            history = new History(); // Find in Database
        }
        return new HistoryFormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history_form, container, false);

        ((TextView) root.findViewById(R.id.button_save))
                .setOnClickListener(view -> onSave());

        ((TextView) root.findViewById(R.id.link_view_history))
                .setOnClickListener(view -> SystemActivity.performClick(R.id.nav_manage_history));

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        historyFormViewModel = new ViewModelProvider(this).get(HistoryFormViewModel.class);
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

            HistoryService.save(history);
        } catch (Exception e) {
            Snackbar.make(requireView(), Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}