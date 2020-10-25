package src.Activities.ui.consult_contacts;

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
import androidx.lifecycle.ViewModelProviders;

import com.example.tp_cuatrimestral.R;

import src.Activities.Adapters.ConsultContactsAdapter;

public class ConsultContactsFragment extends Fragment {

    private ConsultContactsViewModel consultContactsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultContactsViewModel = new ViewModelProvider(this).get(ConsultContactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_layout, container, false);

        ((TextView) root.findViewById(R.id.main_title)).setText("Consultar contactos");

        FrameLayout mainContent = root.findViewById(R.id.main_content);
        View content = getLayoutInflater().inflate(R.layout.fragment_consult_contacts, mainContent, false);
        mainContent.addView(content);

        consultContactsViewModel.getContactList().observe(getViewLifecycleOwner(), contactList -> {
            GridView gridView = requireView().findViewById(R.id.grid_view);
            gridView.setAdapter(new ConsultContactsAdapter(contactList));
        });

        return root;
    }
}