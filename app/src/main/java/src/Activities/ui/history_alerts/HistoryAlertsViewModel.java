package src.Activities.ui.history_alerts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import src.Models.Alert;
import src.Services.Entities.AlertService;

public class HistoryAlertsViewModel extends ViewModel {
    private static MutableLiveData<List<Alert>> liveAlertList = new MutableLiveData<>();
    private static MutableLiveData<Boolean> liveShowOwnAlerts = new MutableLiveData<>(true);

    public HistoryAlertsViewModel() {
        liveShowOwnAlerts.setValue(false);
        liveAlertList.setValue(new ArrayList<>());
        AlertService.fetchAlertList();
    }

    public LiveData<List<Alert>> getAlertList() {
        return liveAlertList;
    }

    public void toggleOwnerVisibilityAlerts(Boolean value) {
        if (! value.equals(liveShowOwnAlerts.getValue())) {
            liveShowOwnAlerts.setValue(value);
        }
    }

    public static LiveData<Boolean> getOwnerStatusList() {
        return liveShowOwnAlerts;
    }

    public static void addAlert(Alert alert) {
        try {
            Objects.requireNonNull(liveAlertList.getValue()).add(alert);
            liveAlertList.postValue(liveAlertList.getValue());
        } catch (Exception ignored) {}
    }

    public static void blockUser(String userId) {
        AlertService.blockUser(userId);
        List<Alert> alertList = Objects.requireNonNull(liveAlertList.getValue())
                .stream()
                .filter(p -> !p.getUserId().equals(userId))
                .collect(Collectors.toList());
        liveAlertList.postValue(alertList);
    }
}