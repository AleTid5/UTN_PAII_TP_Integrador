package src.Activities.ui.history_form;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_cuatrimestral.R;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import components.WithImageFragment.WithImageFragment;
import src.Activities.SystemActivity;
import src.Builders.HistoryFormBuilder;
import src.Models.History;

public class HistoryFormFragment extends WithImageFragment {

    private HistoryFormViewModel historyFormViewModel;
    private static History history;

    public static HistoryFormFragment newInstance(History history) {
        if (history != null) {
            history = new History(); // Find in Database
        }
        return new HistoryFormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_history_form, container, false);

        ((TextView) root.findViewById(R.id.button_image))
                .setOnClickListener(view -> openGallery(1));

        ((TextView) root.findViewById(R.id.link_view_loaded_image))
                .setOnClickListener(view -> showImage());

        ((TextView) root.findViewById(R.id.button_save))
                .setOnClickListener(view -> SystemActivity.performClick(R.id.nav_manage_history));

        ((TextView) root.findViewById(R.id.link_cancel))
                .setOnClickListener(view -> SystemActivity.performClick(R.id.nav_manage_history));

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        historyFormViewModel = new ViewModelProvider(this).get(HistoryFormViewModel.class);
    }

    private void beforeSave() {
        if (this.getImage() == null) {
            // ToDo: Ask if is ok continue without an image.
        }
    }

    private void onSave() {
        this.beforeSave();

        History history = new HistoryFormBuilder()
                .build();
    }
}