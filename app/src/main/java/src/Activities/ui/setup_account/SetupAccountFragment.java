package src.Activities.ui.setup_account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

public class SetupAccountFragment extends Fragment {

    private SetupAccountViewModel setupAccountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setupAccountViewModel = new ViewModelProvider(this).get(SetupAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Configuración de cuenta");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_setup_account, mainContent, false);
        mainContent.addView(content);

        return root;
    }
}