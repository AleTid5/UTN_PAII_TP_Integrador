package src.Activities.ui.frequent_questions;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.ConsultGuideAdapter;
import src.Activities.SystemActivity;

public class FrequentQuestionsFragment extends Fragment {

    private FrequentQuestionsViewModel frequentQuestionsViewModel;

    public static FrequentQuestionsFragment newInstance() {
        return new FrequentQuestionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_frequent_questions, container, false);

        frequentQuestionsViewModel.getStepList().observe(getViewLifecycleOwner(), stepList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new ConsultGuideAdapter(stepList));
        });

        ((TextView) root.findViewById(R.id.link_go_back)).setOnClickListener(
                (View.OnClickListener) view -> SystemActivity.performClick(R.id.nav_consult_guides)
        );

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        frequentQuestionsViewModel = new ViewModelProvider(this).get(FrequentQuestionsViewModel.class);
    }
}