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
import src.Models.Step;

public class FrequentQuestionAdapter extends BaseAdapter {
    private List<Step> elements;
    private static AccordionView accordionView;

    public FrequentQuestionAdapter(List<Step> elements) {
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Step getItem(int i) {
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
            newView = inflater.inflate(R.layout.adapter_consult_guides,null);
        }

        AccordionView accordionView = newView.findViewById(R.id.accordion_view);

        if (accordionView == null) {
            return newView;
        }

        Step step = getItem(i);

        accordionView.setHeadingString(step.getName());
        accordionView.setId(i);

        ((TextView) accordionView.findViewById(R.id.textDescription)).setText(step.getDescription());

        accordionView.setOnExpandCollapseListener(new AccordionExpansionCollapseListener() {
            @Override
            public void onExpanded(AccordionView newAccordionView) {
                if (FrequentQuestionAdapter.accordionView == null) {
                    FrequentQuestionAdapter.accordionView = newAccordionView;
                    return;
                }

                if (FrequentQuestionAdapter.accordionView.getId() != newAccordionView.getId()) {
                    FrequentQuestionAdapter.accordionView.collapse();
                    FrequentQuestionAdapter.accordionView = newAccordionView;
                }
            }

            @Override
            public void onCollapsed(AccordionView view) {}
        });

        return newView;
    }
}
