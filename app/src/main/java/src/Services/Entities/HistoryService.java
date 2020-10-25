package src.Services.Entities;

import java.util.Arrays;
import java.util.List;

import src.Models.History;

public abstract class HistoryService {
    private static List<History> historyList = Arrays.asList(
            new History(1, "Alejandro Tidele", 39100507, "12/08/1995", 25, "1132045137", null),
            new History(2, "Martin D'angelo", 284352323, "13/08/1995", 25, "1132045137", null),
            new History(2, "Martin D'angelo", 284352323, "13/08/1995", 25, "1132045137", null),
            new History(2, "Martin D'angelo", 284352323, "13/08/1995", 25, "1132045137", null),
            new History(2, "Martin D'angelo", 284352323, "13/08/1995", 25, "1132045137", null),
            new History(2, "Martin D'angelo", 284352323, "13/08/1995", 25, "1132045137", null),
            new History(2, "Martin D'angelo", 284352323, "13/08/1995", 25, "1132045137", null)
    );

    public static List<History> getHistoryList() {
        return historyList;
    }
}
