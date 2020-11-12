package src.Activities.ui.manage_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import src.Models.History;
import src.Services.Entities.HistoryService;

public class ManageHistoryViewModel extends ViewModel {

    private static MutableLiveData<List<History>> liveHistoryList = new MutableLiveData<>();

    public ManageHistoryViewModel() {
        liveHistoryList.setValue(new ArrayList<>());
        HistoryService.fetchHistoryList();
    }

    public LiveData<List<History>> getHistoryList() {
        return liveHistoryList;
    }

    public void removeHistory(String historyId) {
        HistoryService.remove(historyId);
        List<History> productList = Objects.requireNonNull(liveHistoryList.getValue())
                .stream()
                .filter(p -> !p.getId().equals(historyId))
                .collect(Collectors.toList());
        liveHistoryList.postValue(productList);
    }

    public static void addProduct(History history) {
        try {
            Objects.requireNonNull(liveHistoryList.getValue()).add(history);
            liveHistoryList.postValue(liveHistoryList.getValue());
        } catch (Exception ignored) {}
    }

    public static void updateProduct(History history) {
        List<History> historyList = Objects.requireNonNull(liveHistoryList.getValue());
        // [{Product: {id: 1, name: "algo"}}] => [1, 2, 32, 12, 43]
        int productIndex = historyList.stream().map(History::getId).collect(Collectors.toList()).indexOf(history.getId());
        historyList.set(productIndex, history);
        liveHistoryList.postValue(liveHistoryList.getValue());
    }
}