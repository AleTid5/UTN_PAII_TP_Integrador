package src.Activities.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import src.Services.Entities.DashboardService;

public class DashboardViewModel extends ViewModel {
    private static Integer liveHistoryReportsTotal;
    private static Integer liveAlertsTotal;
    private static Integer liveBlockedUsersTotal;
    private static MutableLiveData<Integer> liveTotalCount = new MutableLiveData<>();

    public DashboardViewModel() {
        liveTotalCount.setValue(null);
        DashboardService.initializeDashboard();
    }

    public LiveData<Integer> getLiveTotal() {
        return liveTotalCount;
    }

    public Integer getLiveHistoryReportsTotal() {
        return liveHistoryReportsTotal;
    }

    public Integer getLiveAlertsTotal() {
        return liveAlertsTotal;
    }

    public Integer getLiveBlockedUsersTotal() {
        return liveBlockedUsersTotal;
    }

    public static void setLiveHistoryReportsTotal(Integer count) {
        liveHistoryReportsTotal = count;
    }

    public static void setLiveAlertsTotal(Integer count) {
        liveAlertsTotal = count;
    }

    public static void setLiveBlockedUsersTotal(Integer count) {
        liveBlockedUsersTotal = count;
    }

    public static void updateTotal(Integer total) {
        if (total > 0) {
            liveTotalCount.postValue(total);
        }
    }
}