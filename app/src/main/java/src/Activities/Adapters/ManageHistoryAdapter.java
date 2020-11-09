package src.Activities.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import src.Activities.ui.manage_history.ManageHistoryViewModel;
import src.Models.History;

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

        return newView;
    }
}
