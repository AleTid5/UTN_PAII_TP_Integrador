package src.Activities.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import components.Accordion.AccordionView;
import src.Models.User;

public class BlockedUserAdapter extends BaseAdapter {
    private List<User> elements;
    private static AccordionView accordionView;

    public BlockedUserAdapter(List<User> elements) {
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public User getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View newView = view;

        if (newView == null){
            newView = inflater.inflate(R.layout.adapter_blocked_user,null);
        }

        User step = getItem(i);

        ((TextView) newView.findViewById(R.id.text_name)).setText("Usuario " + (i + 1));

        return newView;
    }
}
