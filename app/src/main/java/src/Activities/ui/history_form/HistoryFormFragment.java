package src.Activities.ui.history_form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.AlertsAdapter;
import src.Activities.ui.history_alerts.HistoryAlertsViewModel;

public class HistoryFormFragment extends Fragment {

    private HistoryFormViewModel historyFormViewModel;

    public static HistoryFormFragment newInstance() {
        return new HistoryFormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyFormViewModel = new ViewModelProvider(this).get(HistoryFormViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history_form, container, false);

        return root;
    }
}