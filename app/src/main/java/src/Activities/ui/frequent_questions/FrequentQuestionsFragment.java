package src.Activities.ui.frequent_questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.ConsultGuidesAdapter;

public class FrequentQuestionsFragment extends Fragment {

    private FrequentQuestionsViewModel frequentQuestionsViewModel;

    public static FrequentQuestionsFragment newInstance() {
        return new FrequentQuestionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        frequentQuestionsViewModel = new ViewModelProvider(this).get(FrequentQuestionsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_frequent_questions, container, false);

        frequentQuestionsViewModel.getStepList().observe(getViewLifecycleOwner(), stepList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new ConsultGuidesAdapter(stepList));
        });

        return root;
    }
}