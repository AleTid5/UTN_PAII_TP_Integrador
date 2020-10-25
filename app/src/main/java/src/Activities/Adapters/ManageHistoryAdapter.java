package src.Activities.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import components.Accordion.AccordionExpansionCollapseListener;
import components.Accordion.AccordionView;
import src.Models.History;
import src.Models.Step;

public class ManageHistoryAdapter extends BaseAdapter {
    private List<History> elements;

    public ManageHistoryAdapter(List<History> elements) {
        this.elements = elements;
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

        History step = getItem(i);

        ((TextView) newView.findViewById(R.id.text_name)).setText("Historial " + (i + 1));

        return newView;
    }
}
