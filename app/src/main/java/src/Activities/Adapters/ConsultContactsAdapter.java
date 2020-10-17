package src.Activities.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp_cuatrimestral.R;

import java.util.List;

import components.Accordion.AccordionExpansionCollapseListener;
import components.Accordion.AccordionView;
import src.Activities.ui.consult_contacts.ConsultContactsViewModel;
import src.Models.Contact;

public class ConsultContactsAdapter extends BaseAdapter {
    private ConsultContactsViewModel consultContactsViewModel;
    private List<Contact> elements;
    private static AccordionView accordionView;

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

        AccordionView accordionView = newView.findViewById(R.id.accordion_view);

        if (accordionView == null) {
            return newView;
        }

        Contact contact = getItem(i);

        accordionView.setHeadingString(contact.getName());
        accordionView.setId(i);

        ((TextView) accordionView.findViewById(R.id.textPhone)).setText(contact.getPhones().get(0).getPhoneNumber());
        ((TextView) accordionView.findViewById(R.id.textAddress)).setText(contact.getAddress());
        ((TextView) accordionView.findViewById(R.id.textEmail)).setText(contact.getEmail());

        accordionView.setOnExpandCollapseListener(new AccordionExpansionCollapseListener() {
            @Override
            public void onExpanded(AccordionView newAccordionView) {
                if (ConsultContactsAdapter.accordionView == null) {
                    ConsultContactsAdapter.accordionView = newAccordionView;
                    return;
                }

                if (ConsultContactsAdapter.accordionView.getId() != newAccordionView.getId()) {
                    ConsultContactsAdapter.accordionView.collapse();
                    ConsultContactsAdapter.accordionView = newAccordionView;
                }
            }

            @Override
            public void onCollapsed(AccordionView view) {}
        });

        return newView;
    }
}
