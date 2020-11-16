package src.Activities.ui.manage_history;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.ManageHistoryAdapter;
import src.Activities.Transporter.ManageHistoryTransporter;
import src.Activities.ui.history_form.HistoryFormFragment;

public class ManageHistoryFragment extends Fragment {

    private ManageHistoryViewModel manageHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Administrar historial");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_manage_history, mainContent, false);
        mainContent.addView(content);
        ManageHistoryTransporter.transport(root, content, getChildFragmentManager(), mainContent);

        manageHistoryViewModel.getHistoryList().observe(getViewLifecycleOwner(), stepList -> {
            if (stepList == null) {
                this.removeLoader(root);
                this.addNoResultsLabel(root);
                return;
            }

            GridView gridView = requireView().findViewById(R.id.grid_view);
            if (gridView != null) {
                gridView.setAdapter(new ManageHistoryAdapter(stepList, manageHistoryViewModel));

                if (! stepList.isEmpty()) {
                    this.removeLoader(root);
                }
            }
        });

        manageHistoryViewModel.getFilteredHistoryList().observe(getViewLifecycleOwner(), stepList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            if (gridView != null) {
                gridView.setAdapter(new ManageHistoryAdapter(stepList, manageHistoryViewModel));
            }
        });

        content.findViewById(R.id.link_add_history).setOnClickListener(view -> {
            mainContent.removeView(content);

            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, HistoryFormFragment.newInstance(null))
                    .commit();

            ((TextView) root.findViewById(R.id.main_title)).setText("Agregar historial");
        });

        ((EditText) content.findViewById(R.id.input_history_filter)).addTextChangedListener(filterList(manageHistoryViewModel));

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        manageHistoryViewModel = new ViewModelProvider(this).get(ManageHistoryViewModel.class);
    }

    private void removeLoader(View root) {
        try {
            ((ViewManager) root.findViewById(R.id.loader).getParent()).removeView(root.findViewById(R.id.loader));
        } catch (Exception ignored) {}
    }

    private void addNoResultsLabel(View root) {
        View view = root.findViewById(R.id.text_no_results);

        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private TextWatcher filterList(ManageHistoryViewModel manageHistoryViewModel) {
        return new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                manageHistoryViewModel.filterByName(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };
    }
}