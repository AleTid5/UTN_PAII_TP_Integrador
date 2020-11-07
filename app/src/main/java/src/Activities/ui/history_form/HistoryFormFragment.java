package src.Activities.ui.history_form;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

public class HistoryFormFragment extends Fragment {

    private HistoryFormViewModel historyFormViewModel;
    private Runnable runnable;
    private View view;
    private FrameLayout frameLayout;

    public HistoryFormFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState, FrameLayout frameLayout, Runnable runnable) {
        this.view = this.onCreateView(inflater, container, savedInstanceState);
        this.frameLayout = frameLayout;
        this.runnable = runnable;
    }

    public View getView() {
        return this.view;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history_form, container, false);

        ((TextView) root.findViewById(R.id.link_cancel)).setOnClickListener(
                (View.OnClickListener) view -> {
                    this.frameLayout.removeView(this.view);
                    this.runnable.run();
                }
        );

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        historyFormViewModel = new ViewModelProvider(this).get(HistoryFormViewModel.class);
    }
}