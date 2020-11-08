package src.Builders;

import src.Models.History;

public class HistoryFormBuilder {
    private static History history;

    public HistoryFormBuilder() {
        history = new History();
    }

    public HistoryFormBuilder setImage() {
        return this;
    }

    public History build() {
        return history;
    }
}
