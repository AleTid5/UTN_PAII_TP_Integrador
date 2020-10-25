package src.Activities.ui.alerts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Alert;
import src.Models.History;
import src.Services.Entities.AlertService;
import src.Services.Entities.HistoryService;

public class AlertsViewModel extends ViewModel {

    private static MutableLiveData<List<Alert>> liveAlertList = new MutableLiveData<>();

    public AlertsViewModel() {
        liveAlertList.setValue(AlertService.getAlertList());
    }

    public LiveData<List<Alert>> getAlertList() {
        return liveAlertList;
    }

}