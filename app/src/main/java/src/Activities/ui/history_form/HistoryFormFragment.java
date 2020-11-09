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

import src.Activities.SystemActivity;
import src.Builders.HistoryFormBuilder;
import src.Models.History;
import src.Services.Entities.AlertService;

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
                .setOnClickListener(view -> {
                    AlertService.save();
                });

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
        History history = new HistoryFormBuilder()
                .build();
    }
}