package src.Activities.ui.consult_contacts;

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

public class ConsultContactsFragment extends Fragment {

    private ConsultContactsViewModel consultContactsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultContactsViewModel =
                ViewModelProviders.of(this).get(ConsultContactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_consult_contacts, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        consultContactsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}