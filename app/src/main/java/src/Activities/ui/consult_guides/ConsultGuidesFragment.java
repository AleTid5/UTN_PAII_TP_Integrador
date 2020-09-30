package src.Activities.ui.consult_guides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tp_cuatrimestral.R;

public class ConsultGuidesFragment extends Fragment {

    private ConsultGuidesViewModel consultGuidesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultGuidesViewModel =
                ViewModelProviders.of(this).get(ConsultGuidesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_consult_guides, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        consultGuidesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}