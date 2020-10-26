package src.Activities.ui.consult_guides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import src.Activities.Adapters.ConsultGuideAdapter;
import src.Activities.ui.frequent_questions.FrequentQuestionsFragment;

public class ConsultGuidesFragment extends Fragment {

    private ConsultGuidesViewModel consultGuidesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultGuidesViewModel = new ViewModelProvider(this).get(ConsultGuidesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Consultar guías");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_consult_guides, mainContent, false);
        mainContent.addView(content);

        consultGuidesViewModel.getStepList().observe(getViewLifecycleOwner(), stepList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new ConsultGuideAdapter(stepList));
        });

        ((FloatingActionButton) root.findViewById(R.id.button_frequent_questions)).setOnClickListener(
                view -> {
                    mainContent.removeView(content);
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_content, FrequentQuestionsFragment.newInstance(), "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                    ((TextView) root.findViewById(R.id.main_title)).setText("Preguntas frecuentes");
                }
        );

        return root;
    }
}