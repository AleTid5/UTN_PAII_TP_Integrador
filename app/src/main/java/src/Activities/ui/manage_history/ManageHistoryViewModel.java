package src.Activities.ui.manage_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.History;
import src.Services.Entities.HistoryService;

public class ManageHistoryViewModel extends ViewModel {


    private static MutableLiveData<List<History>> liveHistoryList = new MutableLiveData<>();

    public ManageHistoryViewModel() {
        liveHistoryList.setValue(HistoryService.getHistoryList());
    }

    public LiveData<List<History>> getHistoryList() {
        return liveHistoryList;
    }
}