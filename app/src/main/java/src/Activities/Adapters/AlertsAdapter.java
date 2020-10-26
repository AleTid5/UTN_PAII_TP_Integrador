package src.Activities.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import src.Models.Alert;

public class AlertsAdapter extends BaseAdapter {
    private List<Alert> elements;

    public AlertsAdapter(List<Alert> elements) {
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Alert getItem(int i) {
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
            newView = inflater.inflate(R.layout.adapter_alerts,null);
        }

        Alert step = getItem(i);

        ((TextView) newView.findViewById(R.id.text_name)).setText("Alerta " + (i + 1));

        return newView;
    }
}
