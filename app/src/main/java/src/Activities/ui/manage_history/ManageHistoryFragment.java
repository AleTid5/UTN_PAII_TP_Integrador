package src.Activities.ui.manage_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.ManageHistoryAdapter;
import src.Activities.ui.frequent_questions.FrequentQuestionsFragment;
import src.Activities.ui.history_form.HistoryFormFragment;

public class ManageHistoryFragment extends Fragment {

    private ManageHistoryViewModel manageHistoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        manageHistoryViewModel = new ViewModelProvider(this).get(ManageHistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Administrar historial");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_manage_history, mainContent, false);
        mainContent.addView(content);

        manageHistoryViewModel.getHistoryList().observe(getViewLifecycleOwner(), stepList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new ManageHistoryAdapter(stepList));
        });

        ((TextView) content.findViewById(R.id.link_add_history)).setOnClickListener(view -> {
            mainContent.removeView(content);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, HistoryFormFragment.newInstance(), "findThisFragment")
                    .addToBackStack(null)
                    .commit();
            ((TextView) root.findViewById(R.id.main_title)).setText("Agregar historial");
        });

        return root;
    }
}