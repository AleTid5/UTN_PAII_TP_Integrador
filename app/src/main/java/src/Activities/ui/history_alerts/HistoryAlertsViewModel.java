package src.Activities.ui.history_alerts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Alert;
import src.Services.Entities.AlertService;

public class HistoryAlertsViewModel extends ViewModel {
    private static MutableLiveData<List<Alert>> liveAlertList = new MutableLiveData<>();

    public HistoryAlertsViewModel() {
        liveAlertList.setValue(AlertService.getAlertList());
    }

    public LiveData<List<Alert>> getAlertList() {
        return liveAlertList;
    }
}