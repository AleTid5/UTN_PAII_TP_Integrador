package src.Activities.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import components.Snackbar.CustomSnackbar;
import src.Activities.Transporter.ManageHistoryTransporter;
import src.Activities.ui.history_form.HistoryFormFragment;
import src.Activities.ui.manage_history.ManageHistoryViewModel;
import src.Models.History;
import src.Services.Entities.HistoryService;

public class ManageHistoryAdapter extends BaseAdapter {
    private List<History> elements;
    private ManageHistoryViewModel manageHistoryViewModel;

    public ManageHistoryAdapter(List<History> elements, ManageHistoryViewModel manageHistoryViewModel) {
        this.elements = elements;
        this.manageHistoryViewModel = manageHistoryViewModel;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public History getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View newView = view;

        if (newView == null){
            newView = inflater.inflate(R.layout.adapter_manager_history,null);
        }

        History history = getItem(i);

        ((TextView) newView.findViewById(R.id.text_name)).setText("Historial " + (i + 1));

        ((ImageView) newView.findViewById(R.id.link_remove)).setOnClickListener(v -> new AlertDialog.Builder(v.getContext())
                .setTitle("¿Eliminar el historial?")
                .setMessage(String.format("¿Realmente desea eliminar el historial \"%s\"?", i + 1))
                .setPositiveButton("Aceptar", (dialog, which) -> this.manageHistoryViewModel.removeHistory(history.getId()))
                .setNegativeButton("Cancelar", null)
                .create().show());

        ((ImageView) newView.findViewById(R.id.link_edit)).setOnClickListener(v -> {
            ManageHistoryTransporter.getFrameLayout().removeView(ManageHistoryTransporter.getCurrentContent());

            ManageHistoryTransporter.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, HistoryFormFragment.newInstance(history))
                    .commit();

            ((TextView) ManageHistoryTransporter.getRoot().findViewById(R.id.main_title)).setText(String.format("Editar historial %s", i + 1));
        });

        ((ImageView) newView.findViewById(R.id.link_observation_popup)).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Title");

            final EditText input = new EditText(v.getContext());
            input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            input.setBackgroundColor(Color.LTGRAY);
            input.setHeight(150);
            input.setText(history.getObservations());
            input.setEms(10);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                history.setObservations(input.getText().toString().trim());
                HistoryService.update(history);
                new CustomSnackbar(v, "La observación ha sido modificada exitosamente").success();
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        return newView;
    }
}
