package src.Activities.ui.manage_history;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
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
            if (stepList != null) {
                GridView gridView = requireView().findViewById(R.id.grid_view);
                if (gridView != null) {
                    gridView.setAdapter(new ManageHistoryAdapter(stepList, manageHistoryViewModel));
                }
                if (! stepList.isEmpty()) {
                    try {
                        ((ViewManager) root.findViewById(R.id.loader).getParent()).removeView(root.findViewById(R.id.loader));
                    } catch (Exception ignored) {}
                }
            }
        });

        ((TextView) content.findViewById(R.id.link_add_history)).setOnClickListener(view -> {
            mainContent.removeView(content);

            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, HistoryFormFragment.newInstance(null))
                    .commit();

            ((TextView) root.findViewById(R.id.main_title)).setText("Agregar historial");
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        manageHistoryViewModel = new ViewModelProvider(this).get(ManageHistoryViewModel.class);
    }
}