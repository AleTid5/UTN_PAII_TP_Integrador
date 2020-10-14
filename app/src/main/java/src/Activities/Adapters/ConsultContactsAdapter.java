package src.Activities.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import src.Activities.ui.consult_contacts.ConsultContactsViewModel;
import src.Models.Contact;

public class ConsultContactsAdapter extends BaseAdapter {
    private ConsultContactsViewModel consultContactsViewModel;
    private List<Contact> elements;

    public ConsultContactsAdapter(ConsultContactsViewModel consultContactsViewModel, List<Contact> elements) {
        this.elements = elements;
        this.consultContactsViewModel = consultContactsViewModel;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Contact getItem(int i) {
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
            newView = inflater.inflate(R.layout.adapter_consult_contacts,null);
        }



        return newView;
    }
}
